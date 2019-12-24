package com.ken207.openbank.repository;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.ProductEntity;
import com.ken207.openbank.domain.TradeEntity;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.enums.TaxationCode;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.ProductDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.service.AccountService;
import com.ken207.openbank.service.ProductService;
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
public class TradeRepositoryTest {


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

    @Before
    public void setup() {
        String productName = "온라인 보통예금";
        String regDate = "20191214";
        SubjectCode subjectCode = SubjectCode.REGULAR;

        ProductEntity product = productRepository.findByProductCode(PRODUCT_CODE);

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
    @TestDescription("실제 거래일(BzDate) 조건으로 조회하는 쿼리 테스트")
    public void findByAccountIdAndBzDateGreaterThan() throws Exception {
        //given
        String tradeDate = "20191015";
        TaxationCode taxation = TaxationCode.REGULAR;

        AccountEntity account = createAccount(tradeDate, taxation);
        AccountEntity otherAccount = createAccount(tradeDate, taxation);

        IntStream.range(1,10).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e, account.getAccountNum());
        });
        IntStream.range(1,5).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e, otherAccount.getAccountNum());
        });
        //when
        List<TradeEntity> tradeList1 = tradeRepository.findByAccountIdAndBzDateGreaterThan(account.getId(), tradeDate);
        List<TradeEntity> tradeList2 = tradeRepository.findByAccountIdAndBzDateGreaterThan(account.getId(), OBDateUtils.addDays(tradeDate, 1));
        List<TradeEntity> tradeList3 = tradeRepository.findByAccountIdAndBzDateGreaterThan(account.getId(), OBDateUtils.addDays(OBDateUtils.getToday(), 1));
        List<TradeEntity> tradeList4 = tradeRepository.findByAccountIdAndBzDateGreaterThan(account.getId(), OBDateUtils.getToday());
        List<TradeEntity> tradeList2_1 = tradeRepository.findByAccountIdAndBzDateGreaterThan(otherAccount.getId(),tradeDate);

        //then
        assertEquals(10, tradeList1.size());
        assertEquals(10, tradeList2.size());
        assertEquals(0, tradeList3.size());
        assertEquals(0, tradeList4.size());
        assertEquals(5, tradeList2_1.size());
    }


    @Test
    @TestDescription("기산일 거래(TradeDate) 조건으로 조회하는 쿼리 테스트")
    public void findByAccountIdAndTradeDateGreaterThan() throws Exception {
        //given
        String tradeDate = "20191015";
        TaxationCode taxation = TaxationCode.REGULAR;

        AccountEntity account = createAccount(tradeDate, taxation);
        AccountEntity otherAccount = createAccount(tradeDate, taxation);

        IntStream.range(1,10).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e, account.getAccountNum());
        });
        IntStream.range(1,5).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e, otherAccount.getAccountNum());
        });
        //when
        List<TradeEntity> tradeList1 = tradeRepository.findByAccountIdAndTradeDateGreaterThan(account.getId(), tradeDate);
        List<TradeEntity> tradeList2 = tradeRepository.findByAccountIdAndTradeDateGreaterThan(account.getId(), OBDateUtils.addDays(tradeDate, -1));
        List<TradeEntity> tradeList3 = tradeRepository.findByAccountIdAndTradeDateGreaterThan(account.getId(), OBDateUtils.addDays(tradeDate, 1));
        List<TradeEntity> tradeList4 = tradeRepository.findByAccountIdAndTradeDateGreaterThan(account.getId(), OBDateUtils.getToday());
        List<TradeEntity> tradeList2_1 = tradeRepository.findByAccountIdAndTradeDateGreaterThan(otherAccount.getId(),tradeDate);

        //then
        assertEquals(9, tradeList1.size());
        assertEquals(10, tradeList2.size());
        assertEquals(8, tradeList3.size());
        assertEquals(0, tradeList4.size());
        assertEquals(4, tradeList2_1.size());
    }

    private AccountEntity createAccount(String tradeDate, TaxationCode taxation) {
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