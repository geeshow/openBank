package com.ken207.openbank.service;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.*;
import com.ken207.openbank.domain.enums.PeriodType;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.enums.TaxationCode;
import com.ken207.openbank.domain.enums.TradeCd;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.ProductDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.exception.BizRuntimeException;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    private final String PRODUCT_CODE = "130999";

    @Before
    public void setup() {
        String productName = "온라인 보통예금";
        String regDate = "20191214";
        SubjectCode subjectCode = SubjectCode.REGULAR;

        Product product = productRepository.findByProductCode(PRODUCT_CODE);

        if ( product == null ) {
            ProductDto.Create createProductDto = ProductDto.Create.builder()
                    .productCode(PRODUCT_CODE)
                    .subjectCode(subjectCode)
                    .name(productName)
                    .basicRate(1.2)
                    .startDate(regDate)
                    .endDate(OBDateUtils.MAX_DATE)
                    .build();
            productService.createProduct(createProductDto);
        }
    }

    @Test
    @TestDescription("보통예금 계좌 정상 신규 테스트")
    public void openAccount() throws Exception {
        //given
        String regDate = OBDateUtils.getToday();
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate(regDate)
                .taxationCode(TaxationCode.REGULAR)
                .build();

        //when
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        Account account = accountRepository.findById(accountId).get();

        //then
        assertThat(account.getAccountNum().contains("1310000"));
        assertEquals(OBDateUtils.getToday(), account.getRegDate());
        assertEquals(OBDateUtils.addDays(OBDateUtils.getToday(), -1), account.getLastIntsDt());
        assertEquals(OBDateUtils.getToday(), account.getReckonDt());
        assertEquals(TaxationCode.REGULAR, account.getTaxationCode());
        assertEquals(0, account.getBalance());
    }


    @Test
    @TestDescription("보통예금 계좌 기산일 신규 테스트")
    public void openAccountWithReckonDt() throws Exception {
        //given
        String regDate = "20101010";
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate(regDate)
                .taxationCode(TaxationCode.REGULAR)
                .build();

        //when
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        Account account = accountRepository.findById(accountId).get();

        //then
        assertThat(account.getAccountNum().contains("1310000"));
        assertEquals(regDate, account.getRegDate());
        assertEquals(OBDateUtils.addDays(regDate, -1), account.getLastIntsDt());
        assertEquals(regDate, account.getReckonDt());
        assertEquals(TaxationCode.REGULAR, account.getTaxationCode());
        assertEquals(0, account.getBalance());
    }

    @Test
    @TestDescription("비밀번호 변경 정상 테스트")
    public void changePassword() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        Account account = accountRepository.findById(accountId).get();

        //when
        String newPassword = "1234";
        accountService.setPassword(account.getAccountNum(), newPassword);
        Account accountAfterChange = accountRepository.findById(account.getId()).get();

        //then
        assertEquals(OBDateUtils.getToday(), account.getRegDate());
        assertEquals(OBDateUtils.addDays(account.getRegDate(), -1), account.getLastIntsDt());
        assertEquals(OBDateUtils.getToday(), account.getReckonDt());
        assertEquals(newPassword, accountAfterChange.getPassword());
        assertEquals(0, accountAfterChange.getBalance());
    }


    @Test
    @TestDescription("정상 입금 테스트")
    public void depositAccount() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        String accountNum = accountRepository.findById(accountId).get().getAccountNum();

        int trnAmt1 = 30000;
        int trnAmt2 = 200000;
        int trnAmt3 = 1000000;
        TradeDto.RequestDeposit request1 = TradeDto.RequestDeposit.builder()
                .tradeDate(OBDateUtils.getToday())
                .amount(trnAmt1)
                .build();
        TradeDto.RequestDeposit request2 = TradeDto.RequestDeposit.builder()
                .tradeDate(OBDateUtils.getToday())
                .amount(trnAmt2)
                .build();
        TradeDto.RequestDeposit request3 = TradeDto.RequestDeposit.builder()
                .tradeDate(OBDateUtils.getToday())
                .amount(trnAmt3)
                .build();

        //when
        Trade result1 = accountService.deposit(accountNum, request1);
        Trade result2 = accountService.deposit(accountNum, request2);
        Trade result3 = accountService.deposit(accountNum, request3);
        Account account = accountRepository.findByAccountNum(accountNum);

        //then
        assertThat(result1.getSrno()).isEqualTo(2L);
        assertThat(result2.getSrno()).isEqualTo(3L);
        assertThat(result3.getSrno()).isEqualTo(4L);
        assertEquals(request1.getAmount(), result1.getBlncAfter());
        assertEquals(trnAmt1+trnAmt2, result2.getBlncAfter());
        assertEquals(trnAmt1+trnAmt2+trnAmt3, result3.getBlncAfter());
        assertEquals(trnAmt1+trnAmt2+trnAmt3, account.getBalance());
        assertEquals(4, account.getTradeList().size());

        assertEquals(0, account.getTradeList().get(1).getBlncBefore());
        assertEquals(trnAmt1, account.getTradeList().get(1).getBlncAfter());
        assertEquals(trnAmt1, account.getTradeList().get(1).getAmount());
        assertEquals(TradeCd.DEPOSIT, account.getTradeList().get(1).getTradeCd());

        assertEquals(trnAmt1, account.getTradeList().get(2).getBlncBefore());
        assertEquals(trnAmt1+trnAmt2, account.getTradeList().get(2).getBlncAfter());
        assertEquals(trnAmt2, account.getTradeList().get(2).getAmount());

        assertEquals(trnAmt1+trnAmt2, account.getTradeList().get(3).getBlncBefore());
        assertEquals(trnAmt1+trnAmt2+trnAmt3, account.getTradeList().get(3).getBlncAfter());
        assertEquals(trnAmt3, account.getTradeList().get(3).getAmount());

    }


    @Test
    @TestDescription("정상 출금 테스트")
    public void withdraw() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        String accountNum = accountRepository.findById(accountId).get().getAccountNum();

        long trnAmt1 = 1000000;
        long trnAmt2 = 30000;
        long trnAmt3 = trnAmt1 - trnAmt2;
        TradeDto.RequestDeposit request1 = TradeDto.RequestDeposit.builder()
                .tradeDate(OBDateUtils.getToday())
                .amount(trnAmt1)
                .build();
        TradeDto.RequestDeposit request2 = TradeDto.RequestDeposit.builder()
                .tradeDate(OBDateUtils.getToday())
                .amount(trnAmt2)
                .build();
        TradeDto.RequestDeposit request3 = TradeDto.RequestDeposit.builder()
                .tradeDate(OBDateUtils.getToday())
                .amount(trnAmt3)
                .build();
        //when
        Trade result1 = accountService.deposit(accountNum, request1);
        Trade result2 = accountService.withdraw(accountNum, request2);
        Trade result3 = accountService.withdraw(accountNum, request3);
        Account account = accountRepository.findByAccountNum(accountNum);

        //then
        assertEquals(trnAmt1, result1.getBlncAfter());
        assertEquals(trnAmt1-trnAmt2, result2.getBlncAfter());
        assertEquals(trnAmt1-trnAmt2-trnAmt3, result3.getBlncAfter());
        assertEquals(trnAmt1-trnAmt2-trnAmt3, account.getBalance());
        assertEquals(4, account.getTradeList().size());

        assertEquals(0, account.getTradeList().get(1).getBlncBefore());
        assertEquals(trnAmt1, account.getTradeList().get(1).getBlncAfter());
        assertEquals(trnAmt1, account.getTradeList().get(1).getAmount());
        assertEquals(TradeCd.DEPOSIT, account.getTradeList().get(1).getTradeCd());

        assertEquals(trnAmt1, account.getTradeList().get(2).getBlncBefore());
        assertEquals(trnAmt1-trnAmt2, account.getTradeList().get(2).getBlncAfter());
        assertEquals(trnAmt2, account.getTradeList().get(2).getAmount());

        assertEquals(trnAmt1-trnAmt2, account.getTradeList().get(3).getBlncBefore());
        assertEquals(trnAmt1-trnAmt2-trnAmt3, account.getTradeList().get(3).getBlncAfter());
        assertEquals(trnAmt3, account.getTradeList().get(3).getAmount());
    }

    @Test(expected = BizRuntimeException.class)
    @TestDescription("잔액 초과 출금 테스트")
    public void withdraw_BizRuntimeException() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        String accountNum = accountRepository.findById(accountId).get().getAccountNum();

        long trnAmt1 = 1000000;
        long trnAmt2 = 1000001;
        TradeDto.RequestDeposit request1 = TradeDto.RequestDeposit.builder()
                .tradeDate(OBDateUtils.getToday())
                .amount(trnAmt1)
                .build();
        TradeDto.RequestDeposit request2 = TradeDto.RequestDeposit.builder()
                .tradeDate(OBDateUtils.getToday())
                .amount(trnAmt2)
                .build();

        //when
        accountService.deposit(accountNum, request1);
        accountService.withdraw(accountNum, request2);

        //then
        fail("잔액 이상 출금 오류");
    }


    @Test
    @TestDescription("정상 이자지급 테스트")
    public void payInterest() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate("20170101")
                .taxationCode(TaxationCode.REGULAR)
                .build();
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        String accountNum = accountRepository.findById(accountId).get().getAccountNum();

        long trnAmt1 = 1000000;
        long trnAmt2 = 30000;
        long trnAmt3 = 500000;
        TradeDto.RequestDeposit request1 = TradeDto.RequestDeposit.builder()
                .tradeDate("20170101")
                .amount(trnAmt1)
                .build();
        TradeDto.RequestDeposit request2 = TradeDto.RequestDeposit.builder()
                .tradeDate("20180101")
                .amount(trnAmt2)
                .build();
        TradeDto.RequestDeposit request3 = TradeDto.RequestDeposit.builder()
                .tradeDate("20190101")
                .amount(trnAmt3)
                .build();

        accountService.deposit(accountNum, request1);
        accountService.deposit(accountNum, request2);
        accountService.deposit(accountNum, request3);

        //when
        Trade trade1 = accountService.payInterest(accountNum, "20191231", "20200101");
        Account account = accountRepository.findById(accountId).get();
        List<Interest> interestEntities = account.getInterestEntities();

        Interest interest1 = interestEntities.get(0);

        List<InterestDetail> interestDetails1 = interest1.getInterestDetails();

        //then
        assertEquals(TradeCd.INTEREST, trade1.getTradeCd());
        assertEquals("20200101", trade1.getTradeDate());
        assertEquals(1530000, trade1.getBlncBefore());
        assertEquals(42720, trade1.getAmount());
        assertEquals(1572720, trade1.getBlncAfter());

        assertEquals(1, interestEntities.size());
        assertEquals(1.2, interest1.getBasicRate(), 0);
        assertEquals(42720, interest1.getInterestAmount());
        assertEquals("20170101", interest1.getFromDate());
        assertEquals("20191231", interest1.getToDate());
        assertEquals(PeriodType.DAILY, interest1.getPeriodType());

        assertEquals(3, interestDetails1.size());

        assertEquals(18360, interestDetails1.get(0).getInterestAmount(), 0.01);
        assertEquals("20190101", interestDetails1.get(0).getFromDate());
        assertEquals("20191231", interestDetails1.get(0).getToDate());
        assertEquals(1530000, interestDetails1.get(0).getBalance());
        assertEquals(365, interestDetails1.get(0).getDays());
        assertEquals(0, interestDetails1.get(0).getMonths());

        assertEquals(12360, interestDetails1.get(1).getInterestAmount(), 0.01);
        assertEquals("20180101", interestDetails1.get(1).getFromDate());
        assertEquals("20181231", interestDetails1.get(1).getToDate());
        assertEquals(1030000, interestDetails1.get(1).getBalance());
        assertEquals(365, interestDetails1.get(1).getDays());
        assertEquals(0, interestDetails1.get(1).getMonths());

        assertEquals(12000, interestDetails1.get(2).getInterestAmount(), 0.01);
        assertEquals("20170101", interestDetails1.get(2).getFromDate());
        assertEquals("20171231", interestDetails1.get(2).getToDate());
        assertEquals(1000000, interestDetails1.get(2).getBalance());
        assertEquals(365, interestDetails1.get(2).getDays());
        assertEquals(0, interestDetails1.get(2).getMonths());
    }

    @Test
    @TestDescription("정상 이자지급 테스트")
    public void payInterestMulti() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate("20170101")
                .taxationCode(TaxationCode.REGULAR)
                .build();
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        String accountNum = accountRepository.findById(accountId).get().getAccountNum();

        long trnAmt1 = 1000000;
        long trnAmt2 = 30000;
        long trnAmt3 = 500000;
        TradeDto.RequestDeposit request1 = TradeDto.RequestDeposit.builder()
                .tradeDate("20170101")
                .amount(trnAmt1)
                .build();
        TradeDto.RequestDeposit request2 = TradeDto.RequestDeposit.builder()
                .tradeDate("20180101")
                .amount(trnAmt2)
                .build();
        TradeDto.RequestDeposit request3 = TradeDto.RequestDeposit.builder()
                .tradeDate("20190101")
                .amount(trnAmt3)
                .build();

        accountService.deposit(accountNum, request1);
        accountService.deposit(accountNum, request2);
        accountService.deposit(accountNum, request3);

        //when
        Trade trade1 = accountService.payInterest(accountNum, "20191231", "20200101");
        Trade trade2 = accountService.payInterest(accountNum, "20201231", "20210101");
        Account account = accountRepository.findById(accountId).get();
        List<Interest> interestEntities = account.getInterestEntities();

        Interest interest1 = interestEntities.get(0);
        Interest interest2 = interestEntities.get(1);

        List<InterestDetail> interestDetails1 = interest1.getInterestDetails();
        List<InterestDetail> interestDetails2 = interest2.getInterestDetails();

        //then
        assertEquals(TradeCd.INTEREST, trade1.getTradeCd());
        assertEquals("20200101", trade1.getTradeDate());
        assertEquals(1530000, trade1.getBlncBefore());
        assertEquals(42720, trade1.getAmount());
        assertEquals(1572720, trade1.getBlncAfter());

        assertEquals(TradeCd.INTEREST, trade2.getTradeCd());
        assertEquals("20210101", trade2.getTradeDate());
        assertEquals(1572720, trade2.getBlncBefore());
        assertEquals(18925, trade2.getAmount());
        assertEquals(1591645, trade2.getBlncAfter());

        assertEquals(2, interestEntities.size());
        assertEquals(1.2, interest1.getBasicRate(), 0);
        assertEquals(42720, interest1.getInterestAmount());
        assertEquals("20170101", interest1.getFromDate());
        assertEquals("20191231", interest1.getToDate());
        assertEquals(PeriodType.DAILY, interest1.getPeriodType());

        assertEquals(1.2, interest2.getBasicRate(), 0);
        assertEquals(18925, interest2.getInterestAmount());
        assertEquals("20200101", interest2.getFromDate());
        assertEquals("20201231", interest2.getToDate());
        assertEquals(PeriodType.DAILY, interest2.getPeriodType());

        assertEquals(3, interestDetails1.size());
        assertEquals(1, interestDetails2.size());

        assertEquals(18924.34, interestDetails2.get(0).getInterestAmount(), 0.01);
        assertEquals("20200101", interestDetails2.get(0).getFromDate());
        assertEquals("20201231", interestDetails2.get(0).getToDate());
        assertEquals(1572720, interestDetails2.get(0).getBalance());
        assertEquals(366, interestDetails2.get(0).getDays());
        assertEquals(0, interestDetails2.get(0).getMonths());

        assertEquals(18360, interestDetails1.get(0).getInterestAmount(), 0.01);
        assertEquals("20190101", interestDetails1.get(0).getFromDate());
        assertEquals("20191231", interestDetails1.get(0).getToDate());
        assertEquals(1530000, interestDetails1.get(0).getBalance());
        assertEquals(365, interestDetails1.get(0).getDays());
        assertEquals(0, interestDetails1.get(0).getMonths());

        assertEquals(12360, interestDetails1.get(1).getInterestAmount(), 0.01);
        assertEquals("20180101", interestDetails1.get(1).getFromDate());
        assertEquals("20181231", interestDetails1.get(1).getToDate());
        assertEquals(1030000, interestDetails1.get(1).getBalance());
        assertEquals(365, interestDetails1.get(1).getDays());
        assertEquals(0, interestDetails1.get(1).getMonths());

        assertEquals(12000, interestDetails1.get(2).getInterestAmount(), 0.01);
        assertEquals("20170101", interestDetails1.get(2).getFromDate());
        assertEquals("20171231", interestDetails1.get(2).getToDate());
        assertEquals(1000000, interestDetails1.get(2).getBalance());
        assertEquals(365, interestDetails1.get(2).getDays());
        assertEquals(0, interestDetails1.get(2).getMonths());
    }
}