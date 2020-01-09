package com.ken207.openbank.domain;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.enums.PeriodType;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.enums.TaxationCode;
import com.ken207.openbank.domain.enums.TradeCd;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.ProductDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.repository.ProductRepository;
import com.ken207.openbank.repository.TradeRepository;
import com.ken207.openbank.service.AccountService;
import com.ken207.openbank.service.ProductService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DtoEntityTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    ProductService productService;

    private final String PRODUCT_CODE = "130999";
    private Long ACCOUNT_ID;

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

        if ( ACCOUNT_ID == null ) {
            AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                    .productCode(PRODUCT_CODE)
                    .regDate(regDate)
                    .taxationCode(TaxationCode.REGULAR)
                    .build();

            ACCOUNT_ID = accountService.openRegularAccount(accountRequestOpen);
        }
    }

    @Test
    @TestDescription("Interest Entity 생성 테스트")
    public void createInterest() throws Exception {
        //given
        Account account = accountRepository.findById(ACCOUNT_ID).get();

        //when
        Interest interest = Interest.createInterest(account);

        //then
        assertEquals(1, account.getInterestEntities().size());
        assertEquals(interest, account.getInterestEntities().get(0));
        assertEquals(interest.getAccount(), account);
    }

    @Test
    @TestDescription("거래내역 정렬 테스트")
    public void sortedTradeList() throws Exception {
        //given
        String tradeDate = "20191015";
        TaxationCode taxation = TaxationCode.REGULAR;

        Account account = createAccount(tradeDate, taxation);

        IntStream.range(1,10).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e, account.getAccountNum());
        });

        List<Trade> tradeList1 = tradeRepository.findByAccountIdAndBzDateGreaterThan(account.getId(), tradeDate);
        Interest interest = Interest.createInterest(account);
        interest.setTradeListForInterest(tradeList1);

        //when
        assertEquals(10, tradeList1.size());
        assertEquals(1, tradeList1.get(0).getSrno());
        assertEquals(10, tradeList1.get(tradeList1.size()-1).getSrno());
        Assert.assertFalse(interest.isSorted());
        interest.sortedTradeList();
        List<Trade> tradeList2 = interest.getTradeListForInterest();

        //then
        assertEquals(10, tradeList2.size());
        assertEquals(10, tradeList2.get(0).getSrno());
        assertEquals(1, tradeList2.get(tradeList2.size()-1).getSrno());
        assertEquals(tradeList1.get(0), tradeList2.get(tradeList2.size()-1));
        assertEquals(tradeList1.get(tradeList1.size()-1), tradeList2.get(0));
        Assert.assertTrue(interest.isSorted());
    }

    @Test
    @TestDescription("이자계산에 불필요한 거래내역 제거 테스트")
    public void remainLastTradeOfDays() throws Exception {
        //given
        String tradeDate = "20191015";
        TaxationCode taxation = TaxationCode.REGULAR;

        Account account = createAccount(tradeDate, taxation);

        IntStream.range(1,10).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e, account.getAccountNum());
            deposit(OBDateUtils.addDays(tradeDate, e), e, account.getAccountNum());
        });

        List<Trade> tradeList1 = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), OBDateUtils.MIN_DATE);
        List<Trade> tradeList2 = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), OBDateUtils.MIN_DATE);
        List<Trade> tradeList3 = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), OBDateUtils.MIN_DATE);
        Interest interest1 = Interest.createInterest(account);
        Interest interest2 = Interest.createInterest(account);
        Interest interest3 = Interest.createInterest(account);

        interest1.setTradeListForInterest(tradeList1);
        interest2.setTradeListForInterest(tradeList2);
        interest3.setTradeListForInterest(tradeList3);

        interest1.setPeriod(tradeDate, OBDateUtils.addDays(tradeDate, 5), PeriodType.DAILY);
        interest2.setPeriod(OBDateUtils.addDays(tradeDate,1), OBDateUtils.addDays(tradeDate, 5), PeriodType.DAILY);
        interest3.setPeriod(OBDateUtils.addDays(tradeDate,1), OBDateUtils.addDays(tradeDate, 20), PeriodType.DAILY);

        //when
        interest1.remainLastTradeOfDays();
        interest2.remainLastTradeOfDays();
        interest3.remainLastTradeOfDays();
        List<Trade> tradeEntityList1 = interest1.getTradeListForInterest();
        List<Trade> tradeEntityList2 = interest2.getTradeListForInterest();
        List<Trade> tradeEntityList3 = interest3.getTradeListForInterest();

        //then
        assertEquals(6, tradeEntityList1.size());
        assertEquals(30, tradeEntityList1.get(0).getBlncAfter());
        assertEquals(20, tradeEntityList1.get(1).getBlncAfter());
        assertEquals(12, tradeEntityList1.get(2).getBlncAfter());
        assertEquals(6, tradeEntityList1.get(3).getBlncAfter());
        assertEquals(2, tradeEntityList1.get(4).getBlncAfter());
        assertEquals(0, tradeEntityList1.get(5).getBlncAfter());
        assertEquals(5, tradeEntityList2.size());
        assertEquals(9, tradeEntityList3.size());
    }

    @Test
    @TestDescription("이자계산상세 테이블 생성")
    public void makeInterestDetail() throws Exception {
        //given
        String tradeDate = "20191015";
        TaxationCode taxation = TaxationCode.REGULAR;

        Account account = createAccount(tradeDate, taxation);

        IntStream.range(1,10).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e, account.getAccountNum());
            deposit(OBDateUtils.addDays(tradeDate, e), e, account.getAccountNum());
        });

        List<Trade> tradeList1 = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), OBDateUtils.MIN_DATE);
        List<Trade> tradeList2 = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), OBDateUtils.MIN_DATE);
        List<Trade> tradeList3 = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), OBDateUtils.MIN_DATE);
        Interest interest1 = Interest.createInterest(account);
        Interest interest2 = Interest.createInterest(account);
        Interest interest3 = Interest.createInterest(account);

        //when
        interest1.setTradeListForInterest(tradeList1);
        interest2.setTradeListForInterest(tradeList2);
        interest3.setTradeListForInterest(tradeList3);

        interest1.setPeriod(tradeDate, OBDateUtils.addDays(tradeDate, 5), PeriodType.DAILY);
        interest2.setPeriod(OBDateUtils.addDays(tradeDate,1), OBDateUtils.addDays(tradeDate, 5), PeriodType.DAILY);
        interest3.setPeriod(OBDateUtils.addDays(tradeDate,1), OBDateUtils.addDays(tradeDate, 20), PeriodType.DAILY);

        interest1.remainLastTradeOfDays();
        interest2.remainLastTradeOfDays();
        interest3.remainLastTradeOfDays();

        interest1.makeInterestDetail();
        interest2.makeInterestDetail();
        interest3.makeInterestDetail();

        List<InterestDetail> interestDetails1 = interest1.getInterestDetails();
        List<InterestDetail> interestDetails2 = interest2.getInterestDetails();
        List<InterestDetail> interestDetails3 = interest3.getInterestDetails();

        //then
        assertEquals(6, interestDetails1.size());
        assertEquals(0, interestDetails1.get(0).getDays());
        assertEquals(0, interestDetails1.get(0).getMonths());
        assertEquals("20191020", interestDetails1.get(0).getFromDate());
        assertEquals("20191020", interestDetails1.get(0).getToDate());
        assertEquals(30, interestDetails1.get(0).getBalance());
        assertEquals(20, interestDetails1.get(1).getBalance());
        assertEquals(12, interestDetails1.get(2).getBalance());
        assertEquals(6, interestDetails1.get(3).getBalance());
        assertEquals(2, interestDetails1.get(4).getBalance());
        assertEquals(0, interestDetails1.get(5).getBalance());
        assertEquals(account.getBasicRate().getRate(), interestDetails1.get(0).getInterestRate(), 0);
        assertEquals(5, interestDetails2.size());
        assertEquals(9, interestDetails3.size());
    }

    @Test
    @TestDescription("이자계산결과 테스트")
    public void calculate() throws Exception {
        //given
        String tradeDate = "20191015";
        TaxationCode taxation = TaxationCode.REGULAR;

        Account account = createAccount(tradeDate, taxation);

        IntStream.range(1,10).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e*1000, account.getAccountNum());
            deposit(OBDateUtils.addDays(tradeDate, e), e*10000, account.getAccountNum());
        });

        List<Trade> tradeList1 = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), OBDateUtils.MIN_DATE);
        List<Trade> tradeList2 = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), OBDateUtils.MIN_DATE);
        List<Trade> tradeList3 = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), OBDateUtils.MIN_DATE);
        Interest interest1 = Interest.createInterest(account);
        Interest interest2 = Interest.createInterest(account);
        Interest interest3 = Interest.createInterest(account);

        //when
        interest1.setTradeListForInterest(tradeList1);
        interest2.setTradeListForInterest(tradeList2);
        interest3.setTradeListForInterest(tradeList3);

        interest1.setPeriod(tradeDate, OBDateUtils.addDays(tradeDate, 5), PeriodType.DAILY);
        interest2.setPeriod(OBDateUtils.addDays(tradeDate,1), OBDateUtils.addDays(tradeDate, 5), PeriodType.DAILY);
        interest3.setPeriod(OBDateUtils.addDays(tradeDate,1), OBDateUtils.addDays(tradeDate, 20), PeriodType.DAILY);

        interest1.remainLastTradeOfDays();
        interest2.remainLastTradeOfDays();
        interest3.remainLastTradeOfDays();

        interest1.makeInterestDetail();
        interest2.makeInterestDetail();
        interest3.makeInterestDetail();

        interest1.calculate();
        interest2.calculate();
        interest3.calculate();

        List<InterestDetail> interestDetails1 = interest1.getInterestDetails();
        List<InterestDetail> interestDetails2 = interest2.getInterestDetails();
        List<InterestDetail> interestDetails3 = interest3.getInterestDetails();

        //then
        assertEquals(6, interestDetails1.size());
        assertEquals(1, interestDetails1.get(0).getDays());
        assertEquals(165000, interestDetails1.get(0).getBalance());
        assertEquals(5.42, interestDetails1.get(0).getInterestAmount(), 0.01);
        assertEquals(1, interestDetails1.get(1).getDays());
        assertEquals(1, interestDetails1.get(2).getDays());
        assertEquals(1, interestDetails2.get(0).getDays());
        assertEquals(165000, interestDetails2.get(0).getBalance());
        assertEquals(5.42, interestDetails2.get(0).getInterestAmount(), 0.01);
        assertEquals(12, interestDetails3.get(0).getDays());
        assertEquals("20191024", interestDetails3.get(0).getFromDate());
        assertEquals("20191104", interestDetails3.get(0).getToDate());
        assertEquals(495000, interestDetails3.get(0).getBalance());
        assertEquals(195.28, interestDetails3.get(0).getInterestAmount(), 0.01);
    }


    @Test
    @TestDescription("이자지급 테스트")
    public void payInterest() throws Exception {
        //given
        String tradeDate = "20191015";
        TaxationCode taxation = TaxationCode.REGULAR;

        Account account = createAccount(tradeDate, taxation);

        IntStream.range(1,10).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e*1000, account.getAccountNum());
            deposit(OBDateUtils.addDays(tradeDate, e), e*10000, account.getAccountNum());
        });

        List<Trade> tradeList1 = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(account.getId(), OBDateUtils.MIN_DATE);
        Interest interest1 = Interest.createInterest(account);

        //when
        interest1.setTradeListForInterest(tradeList1);

        interest1.setPeriod(OBDateUtils.addDays(tradeDate,1), OBDateUtils.addDays(tradeDate, 20), PeriodType.DAILY);

        interest1.remainLastTradeOfDays();

        interest1.makeInterestDetail();

        interest1.calculate();

        long interestSum1 = interest1.getInterestAmount();

        Trade resultTrade = interest1.payInterest(OBDateUtils.getToday());

        Trade trade = account.getTradeList().get(account.getTradeList().size() - 1);

        //then
        assertEquals(trade, resultTrade);
        assertEquals(239, interestSum1);
        assertEquals("20191104", account.getLastIntsDt());
        assertEquals(OBDateUtils.getToday(), account.getLastTradeDate());
        assertEquals(495239, account.getBalance());
        assertEquals(239, trade.getAmount());
        assertEquals(495000, trade.getBlncBefore());
        assertEquals(TradeCd.INTEREST, trade.getTradeCd());
    }

    private Account createAccount(String tradeDate, TaxationCode taxation) {
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate(tradeDate)
                .taxationCode(taxation)
                .build();
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        return accountRepository.findById(accountId).get();
    }

    private void deposit(String tradeDate, long amount1, String accountNum) {
        TradeDto.RequestDeposit requestDeposit = TradeDto.RequestDeposit.builder()
                .amount(amount1)
                .tradeDate(tradeDate)
                .build();
        accountService.deposit(accountNum, requestDeposit);
    }
}