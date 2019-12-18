package com.ken207.openbank.service;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.TradeEntity;
import com.ken207.openbank.domain.enums.TaxationCode;
import com.ken207.openbank.domain.enums.TradeCd;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.exception.BizRuntimeException;
import com.ken207.openbank.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Test
    @TestDescription("보통예금 계좌 정상 신규 테스트")
    public void openAccount() throws Exception {
        //given
        String regDate = OBDateUtils.getToday();
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .regDate(regDate)
                .taxationCode(TaxationCode.REGULAR)
                .build();

        //when
        String acno = accountService.openRegularAccount(accountRequestOpen);
        AccountEntity accountEntity = accountRepository.findByAccountNum(acno);

        //then
        assertThat(accountEntity.getAccountNum().contains("1310000"));
        assertEquals(OBDateUtils.getToday(), accountEntity.getRegDate());
        assertEquals(OBDateUtils.getToday(), accountEntity.getLastIntsDt());
        assertEquals(OBDateUtils.getToday(), accountEntity.getReckonDt());
        assertEquals(TaxationCode.REGULAR, accountEntity.getTaxationCode());
        assertEquals(0, accountEntity.getBalance());
    }


    @Test
    @TestDescription("보통예금 계좌 기산일 신규 테스트")
    public void openAccountWithReckonDt() throws Exception {
        //given
        String regDate = "20101010";
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .regDate(regDate)
                .taxationCode(TaxationCode.REGULAR)
                .build();

        //when
        String acno = accountService.openRegularAccount(accountRequestOpen);
        AccountEntity accountEntity = accountRepository.findByAccountNum(acno);

        //then
        assertThat(accountEntity.getAccountNum().contains("1310000"));
        assertEquals(regDate, accountEntity.getRegDate());
        assertEquals(regDate, accountEntity.getLastIntsDt());
        assertEquals(regDate, accountEntity.getReckonDt());
        assertEquals(TaxationCode.REGULAR, accountEntity.getTaxationCode());
        assertEquals(0, accountEntity.getBalance());
    }

    @Test
    @TestDescription("비밀번호 변경 정상 테스트")
    public void changePassword() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        String acno = accountService.openRegularAccount(accountRequestOpen);

        //when
        String newPassword = "1234";
        accountService.setPassword(acno, newPassword);
        AccountEntity accountEntity = accountRepository.findByAccountNum(acno);

        //then
        assertEquals(OBDateUtils.getToday(), accountEntity.getRegDate());
        assertEquals(OBDateUtils.getToday(), accountEntity.getLastIntsDt());
        assertEquals(OBDateUtils.getToday(), accountEntity.getReckonDt());
        assertEquals(newPassword, accountEntity.getPassword());
        assertEquals(0, accountEntity.getBalance());
    }


    @Test
    @TestDescription("정상 입금 테스트")
    public void depositAccount() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        String acno = accountService.openRegularAccount(accountRequestOpen);

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
        TradeEntity result1 = accountService.deposit(acno, request1);
        TradeEntity result2 = accountService.deposit(acno, request2);
        TradeEntity result3 = accountService.deposit(acno, request3);
        AccountEntity accountEntity = accountRepository.findByAccountNum(acno);

        //then
        assertThat(result1.getSrno()).isEqualTo(2L);
        assertThat(result2.getSrno()).isEqualTo(3L);
        assertThat(result3.getSrno()).isEqualTo(4L);
        assertEquals(request1.getAmount(), result1.getBlncAfter());
        assertEquals(trnAmt1+trnAmt2, result2.getBlncAfter());
        assertEquals(trnAmt1+trnAmt2+trnAmt3, result3.getBlncAfter());
        assertEquals(trnAmt1+trnAmt2+trnAmt3, accountEntity.getBalance());
        assertEquals(4, accountEntity.getTradeEntities().size());

        assertEquals(0, accountEntity.getTradeEntities().get(1).getBlncBefore());
        assertEquals(trnAmt1, accountEntity.getTradeEntities().get(1).getBlncAfter());
        assertEquals(trnAmt1, accountEntity.getTradeEntities().get(1).getAmount());
        assertEquals(TradeCd.DEPOSIT, accountEntity.getTradeEntities().get(1).getTradeCd());

        assertEquals(trnAmt1, accountEntity.getTradeEntities().get(2).getBlncBefore());
        assertEquals(trnAmt1+trnAmt2, accountEntity.getTradeEntities().get(2).getBlncAfter());
        assertEquals(trnAmt2, accountEntity.getTradeEntities().get(2).getAmount());

        assertEquals(trnAmt1+trnAmt2, accountEntity.getTradeEntities().get(3).getBlncBefore());
        assertEquals(trnAmt1+trnAmt2+trnAmt3, accountEntity.getTradeEntities().get(3).getBlncAfter());
        assertEquals(trnAmt3, accountEntity.getTradeEntities().get(3).getAmount());

    }


    @Test
    @TestDescription("정상 출금 테스트")
    public void outAccount() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        String acno = accountService.openRegularAccount(accountRequestOpen);
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
        TradeEntity result1 = accountService.deposit(acno, request1);
        TradeEntity result2 = accountService.withdraw(acno, request2);
        TradeEntity result3 = accountService.withdraw(acno, request3);
        AccountEntity accountEntity = accountRepository.findByAccountNum(acno);

        //then
        assertEquals(trnAmt1, result1.getBlncAfter());
        assertEquals(trnAmt1-trnAmt2, result2.getBlncAfter());
        assertEquals(trnAmt1-trnAmt2-trnAmt3, result3.getBlncAfter());
        assertEquals(trnAmt1-trnAmt2-trnAmt3, accountEntity.getBalance());
        assertEquals(4, accountEntity.getTradeEntities().size());

        assertEquals(0, accountEntity.getTradeEntities().get(1).getBlncBefore());
        assertEquals(trnAmt1, accountEntity.getTradeEntities().get(1).getBlncAfter());
        assertEquals(trnAmt1, accountEntity.getTradeEntities().get(1).getAmount());
        assertEquals(TradeCd.DEPOSIT, accountEntity.getTradeEntities().get(1).getTradeCd());

        assertEquals(trnAmt1, accountEntity.getTradeEntities().get(2).getBlncBefore());
        assertEquals(trnAmt1-trnAmt2, accountEntity.getTradeEntities().get(2).getBlncAfter());
        assertEquals(trnAmt2, accountEntity.getTradeEntities().get(2).getAmount());

        assertEquals(trnAmt1-trnAmt2, accountEntity.getTradeEntities().get(3).getBlncBefore());
        assertEquals(trnAmt1-trnAmt2-trnAmt3, accountEntity.getTradeEntities().get(3).getBlncAfter());
        assertEquals(trnAmt3, accountEntity.getTradeEntities().get(3).getAmount());
    }

    @Test(expected = BizRuntimeException.class)
    @TestDescription("잔액 초과 출금 테스트")
    public void outAccount_BizRuntimeException() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .regDate(OBDateUtils.getToday())
                .taxationCode(TaxationCode.REGULAR)
                .build();
        String acno = accountService.openRegularAccount(accountRequestOpen);
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
        accountService.deposit(acno, request1);
        accountService.withdraw(acno, request2);

        //then
        fail("잔액 이상 출금 오류");
    }
}