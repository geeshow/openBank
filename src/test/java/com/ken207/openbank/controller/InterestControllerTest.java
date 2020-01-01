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
    @TestDescription("이자계산 결과 조회 정상 테스트")
    public void checkInterest() throws Exception {
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
                .andExpect(jsonPath("interest").value(42720))
                .andExpect(jsonPath("periodType").value(PeriodType.DAILY.toString()))
                .andExpect(jsonPath("details[0].id").exists())
                .andExpect(jsonPath("details[0].fromDate").value("20190101"))
                .andExpect(jsonPath("details[0].toDate").value("20191231"))
                .andExpect(jsonPath("details[0].interestRate").value(1.2))
                .andExpect(jsonPath("details[0].taxRate").value(0))
                .andExpect(jsonPath("details[0].balance").value(1530000))
                .andExpect(jsonPath("details[0].months").value(0))
                .andExpect(jsonPath("details[0].days").value(365))
                .andExpect(jsonPath("details[0].interest").value(18360))
                .andExpect(jsonPath("details[0].tax").value(0))
                .andDo(document("interest-check",
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
                                fieldWithPath("interest").description("result of interest."),
                                fieldWithPath("periodType").description("method of calculate about period, such as daily, monthly"),
                                fieldWithPath("details[0].id").description("identification number about interest detail information."),
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
                                fieldWithPath("_links.check.href").description("link to check how much the interest is."),
                                fieldWithPath("_links.receive.href").description("link to withdraw interest from an existing account."),
                                fieldWithPath("_links.received-list.href").description("link to received interest list."),
                                fieldWithPath("_links.profile.href").description("link to self.")
                        )
                ));
    }

    private LinksSnippet getLinksOfInterest() {
        return links(
                linkWithRel("self").description("link to self"),
                linkWithRel("check").description("link to check how much the interest is"),
                linkWithRel("receive").description("link to withdraw interest from an existing account"),
                linkWithRel("received-list").description("link to received interest list"),
                linkWithRelAsProfile());
    }

    private LinkDescriptor linkWithRelAsProfile() {
        return linkWithRel("profile").description("link to profile.");
    }

    private FieldDescriptor fieldWithPathAsSelf() {
        return fieldWithPath("_links.self.href").description("link to self.");
    }
}