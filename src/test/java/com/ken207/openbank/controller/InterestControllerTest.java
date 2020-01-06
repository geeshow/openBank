package com.ken207.openbank.controller;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.ProductEntity;
import com.ken207.openbank.domain.enums.PeriodType;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.enums.TaxationCode;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.ProductDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.repository.ProductRepository;
import com.ken207.openbank.service.AccountService;
import com.ken207.openbank.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.hypermedia.LinkDescriptor;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class InterestControllerTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    private final String PRODUCT_CODE = "130998";

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
    @TestDescription("이자계산 인덱스 페이지 조회 테스트")
    public void indexInterest() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate("20170101")
                .taxationCode(TaxationCode.REGULAR)
                .build();
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        String accountNum = accountRepository.findById(accountId).get().getAccountNum();

        String untilDate = OBDateUtils.getToday();
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

        AccountEntity account = accountRepository.findById(accountId).get();

        //when & then
        mockMvc.perform(get("/api/interest/{accountNum}", accountNum)
                .header(HttpHeaders.AUTHORIZATION, getBearerToken())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("accountNum").value(accountNum))
                .andExpect(jsonPath("lastIntsDt").value(account.getLastIntsDt()))
                .andExpect(jsonPath("balance").value(account.getBalance()))
                .andExpect(jsonPath("fromDate").value(OBDateUtils.addDays(account.getLastIntsDt(),1)))
                .andExpect(jsonPath("toDate").value(OBDateUtils.addDays(untilDate,-1)))
                .andExpect(jsonPath("basicRate").value(account.getBasicRate().getRate()))
                .andExpect(jsonPath("expectedInterest").exists())
                .andExpect(jsonPath("periodType").value(PeriodType.DAILY.toString()))
                .andExpect(jsonPath("details[0].id").doesNotExist())
                .andExpect(jsonPath("details[0].fromDate").value("20190101"))
                .andExpect(jsonPath("details[0].toDate").value(OBDateUtils.addDays(untilDate, -1)))
                .andExpect(jsonPath("details[0].interestRate").value(1.2))
                .andExpect(jsonPath("details[0].taxRate").value(0))
                .andExpect(jsonPath("details[0].balance").value(1530000))
                .andExpect(jsonPath("details[0].months").value(0))
                .andExpect(jsonPath("details[0].days").exists())
                .andExpect(jsonPath("details[0].interest").exists())
                .andExpect(jsonPath("details[0].tax").exists())
                .andDo(document("interest-index",
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("interest-calculate").description("link to check how much the interest is"),
                                linkWithRel("receive").description("link to withdraw interest from an existing account"),
                                linkWithRel("interest-list").description("link to received interest list"),
                                linkWithRelAsProfile())
                        ,
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Authorization header")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("accountNum").description("identification number of new account."),
                                fieldWithPath("lastIntsDt").description("the last calculated date of account interest."),
                                fieldWithPath("balance").description("balance of account."),
                                fieldWithPath("fromDate").description("start date of interest to calculate."),
                                fieldWithPath("toDate").description("end date of interest to calculate."),
                                fieldWithPath("basicRate").description("interest rate of this account."),
                                fieldWithPath("expectedInterest").description("result of interest."),
                                fieldWithPath("periodType").description("method of calculate about period, such as daily, monthly"),
                                fieldWithPath("details[0].fromDate").description("start date of interest to calculate."),
                                fieldWithPath("details[0].toDate").description("end date of interest to calculate."),
                                fieldWithPath("details[0].interestRate").description("interest rate of this account."),
                                fieldWithPath("details[0].taxRate").description("tax rate of this account."),
                                fieldWithPath("details[0].balance").description("balance of account at the time"),
                                fieldWithPath("details[0].months").description("calculate period in month"),
                                fieldWithPath("details[0].days").description("calculate period in day"),
                                fieldWithPath("details[0].interest").description("result of interest in this period."),
                                fieldWithPath("details[0].tax").description("result of tax in this period."),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.interest-calculate.href").description("link to check how much the interest is."),
                                fieldWithPath("_links.receive.href").description("link to withdraw interest from an existing account."),
                                fieldWithPath("_links.interest-list.href").description("link to received interest list."),
                                fieldWithPath("_links.profile.href").description("link to self.")
                        )
                ));
    }


    @Test
    @TestDescription("지정일 이자계산 후 예상 이자 조회 정상 테스트")
    public void calculateInterest() throws Exception {
        //given
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate("20170101")
                .taxationCode(TaxationCode.REGULAR)
                .build();
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        String accountNum = accountRepository.findById(accountId).get().getAccountNum();

        String untilDate = "20200101";
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

        AccountEntity account = accountRepository.findById(accountId).get();

        //when & then
        mockMvc.perform(get("/api/interest/{accountNum}/{until}", accountNum, untilDate)
                .header(HttpHeaders.AUTHORIZATION, getBearerToken())
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("accountNum").value(accountNum))
                .andExpect(jsonPath("lastIntsDt").value(account.getLastIntsDt()))
                .andExpect(jsonPath("balance").value(account.getBalance()))
                .andExpect(jsonPath("fromDate").value(OBDateUtils.addDays(account.getLastIntsDt(),1)))
                .andExpect(jsonPath("toDate").value(OBDateUtils.addDays(untilDate,-1)))
                .andExpect(jsonPath("basicRate").value(account.getBasicRate().getRate()))
                .andExpect(jsonPath("expectedInterest").value(42720))
                .andExpect(jsonPath("periodType").value(PeriodType.DAILY.toString()))
                .andExpect(jsonPath("details[0].id").doesNotExist())
                .andExpect(jsonPath("details[0].fromDate").value("20190101"))
                .andExpect(jsonPath("details[0].toDate").value("20191231"))
                .andExpect(jsonPath("details[0].interestRate").value(1.2))
                .andExpect(jsonPath("details[0].taxRate").value(0))
                .andExpect(jsonPath("details[0].balance").value(1530000))
                .andExpect(jsonPath("details[0].months").value(0))
                .andExpect(jsonPath("details[0].days").value(365))
                .andExpect(jsonPath("details[0].interest").value(18360))
                .andExpect(jsonPath("details[0].tax").value(0))
                .andDo(document("interest-calculate",
                        getLinksOfInterest(),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Authorization header")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("accountNum").description("identification number of new account."),
                                fieldWithPath("lastIntsDt").description("the last calculated date of account interest."),
                                fieldWithPath("balance").description("balance of account."),
                                fieldWithPath("fromDate").description("start date of interest to calculate."),
                                fieldWithPath("toDate").description("end date of interest to calculate."),
                                fieldWithPath("basicRate").description("interest rate of this account."),
                                fieldWithPath("expectedInterest").description("이자 계산으로 계산된 예상 지급 이자."),
                                fieldWithPath("periodType").description("method of calculate about period, such as daily, monthly"),
                                fieldWithPath("details[0].fromDate").description("start date of interest to calculate."),
                                fieldWithPath("details[0].toDate").description("end date of interest to calculate."),
                                fieldWithPath("details[0].interestRate").description("interest rate of this account."),
                                fieldWithPath("details[0].taxRate").description("tax rate of this account."),
                                fieldWithPath("details[0].balance").description("balance of account at the time"),
                                fieldWithPath("details[0].months").description("calculate period in month"),
                                fieldWithPath("details[0].days").description("calculate period in day"),
                                fieldWithPath("details[0].interest").description("result of interest in this period."),
                                fieldWithPath("details[0].tax").description("result of tax in this period."),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.interest-index.href").description("이자계산 기본 링크."),
                                fieldWithPath("_links.interest-calculate.href").description("지정일 기준 이자 예상 조회 링크"),
                                fieldWithPath("_links.receive.href").description("지정 계좌 이자 출금 처리 링크"),
                                fieldWithPath("_links.interest-list.href").description("link to received interest list."),
                                fieldWithPath("_links.profile.href").description("link to self.")
                        )
                ));
    }

    @Test
    @TestDescription("이자계산 결과 목록 조회 정상 테스트")
    public void getInterestList() throws Exception {
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
        accountService.payInterest(accountNum, "20170630", "20170701");
        accountService.payInterest(accountNum, "20171231", "20180101");
        accountService.deposit(accountNum, request2);
        accountService.payInterest(accountNum, "20180630", "20180701");
        accountService.payInterest(accountNum, "20191231", "20190101");
        accountService.deposit(accountNum, request3);
        accountService.payInterest(accountNum, "20190630", "20190701");
        accountService.payInterest(accountNum, "20201231", "20200101");
        accountService.payInterest(accountNum, "20200630", "20200701");
        accountService.payInterest(accountNum, "20211231", "20210101");
        accountService.payInterest(accountNum, "20210630", "20210701");
        accountService.payInterest(accountNum, "20221231", "20220101");
        accountService.payInterest(accountNum, "20220630", "20220701");
        accountService.payInterest(accountNum, "20231231", "20230101");

        AccountEntity account = accountRepository.findById(accountId).get();

        //when & then
        mockMvc.perform(get("/api/interest/{accountNum}/log", accountNum)
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                        .param("page", "1")
                        .param("size", "5")
                        .param("sort", "id,ASC")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("_embedded.dtoList[0].id").exists())
                .andExpect(jsonPath("_embedded.dtoList[0].accountNum").value(accountNum))
                .andExpect(jsonPath("_embedded.dtoList[0].fromDate").value("20190701"))
                .andExpect(jsonPath("_embedded.dtoList[0].toDate").value("20201231"))
                .andExpect(jsonPath("_embedded.dtoList[0].basicRate").value(1.2))
                .andExpect(jsonPath("_embedded.dtoList[0].interest").value(28338))
                .andExpect(jsonPath("_embedded.dtoList[0].periodType").value(PeriodType.DAILY.toString()))
                .andExpect(jsonPath("_embedded.dtoList[0]._links.interest-detail.href").exists())
                .andDo(document("interest-list",
                        links(
                                linkWithRel("first").description("link to first page"),
                                linkWithRel("prev").description("link to prev page"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("next").description("link to next page"),
                                linkWithRel("last").description("link to last page"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("interest-index").description("이자계산 기본 링크 주소."),
                                linkWithRel("interest-list").description("이자계산내역을 확인하는 링크 주소"),
                                linkWithRel("interest-calculate").description("지정된 날짜까지 이자를 계산/결과를 확인하는 링크 주소"),
                                linkWithRel("interest-index").description("이자관련 첫 화면 링크 주소"),
                                linkWithRelAsProfile()
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Authorization header")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("_links.first.href").description("link to first."),
                                fieldWithPath("_links.prev.href").description("link to prev."),
                                fieldWithPath("_links.next.href").description("link to next."),
                                fieldWithPath("_links.last.href").description("link to last."),
                                fieldWithPath("page.size").description("size of one page."),
                                fieldWithPath("page.totalElements").description("amount of datas."),
                                fieldWithPath("page.totalPages").description("amount of pages."),
                                fieldWithPath("page.number").description("current page number."),
                                fieldWithPath("_embedded.dtoList[0].id").description("이자계산 상세 정보 기본키."),
                                fieldWithPath("_embedded.dtoList[0].accountNum").description("해당 이자계산 결과의 계좌번호"),
                                fieldWithPath("_embedded.dtoList[0].fromDate").description("이자계산 시작일자."),
                                fieldWithPath("_embedded.dtoList[0].toDate").description("이자계산 종료일자."),
                                fieldWithPath("_embedded.dtoList[0].basicRate").description("계좌의 기본 이율."),
                                fieldWithPath("_embedded.dtoList[0].interest").description("계산 결과 이자."),
                                fieldWithPath("_embedded.dtoList[0].periodType").description("이자계산의 기간 산정 방법. 일수, 월수, 일/월수"),
                                fieldWithPath("_embedded.dtoList[0]._links.interest-detail.href").description("이자계산결과의 상세 정보 링크"),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.interest-index.href").description("이자계산 기본 링크 주소."),
                                fieldWithPath("_links.interest-calculate.href").description("link to check how much the interest is."),
                                fieldWithPath("_links.interest-list.href").description("link to received interest list."),
                                fieldWithPath("_links.profile.href").description("link to self.")
                        )
                ));
    }

    private LinksSnippet getLinksOfInterest() {
        return links(
                linkWithRel("self").description("link to self"),
                linkWithRel("interest-index").description("이자계산 기본 링크."),
                linkWithRel("interest-calculate").description("link to check how much the interest is"),
                linkWithRel("receive").description("link to withdraw interest from an existing account"),
                linkWithRel("interest-list").description("link to received interest list"),
                linkWithRelAsProfile());
    }

    private LinkDescriptor linkWithRelAsProfile() {
        return linkWithRel("profile").description("link to profile.");
    }

    private FieldDescriptor fieldWithPathAsSelf() {
        return fieldWithPath("_links.self.href").description("link to self.");
    }
}