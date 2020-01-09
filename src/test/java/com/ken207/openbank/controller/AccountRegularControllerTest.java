package com.ken207.openbank.controller;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.Product;
import com.ken207.openbank.domain.enums.AccountStatusCode;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.enums.TaxationCode;
import com.ken207.openbank.domain.enums.TradeCd;
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
import org.springframework.http.MediaType;
import org.springframework.restdocs.hypermedia.LinkDescriptor;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import java.util.stream.IntStream;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccountRegularControllerTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Autowired
    ProductService productService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProductRepository productRepository;

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
    @TestDescription("보통예금 계좌생성 정상 테스트")
    public void openAccount() throws Exception {
        //given
        String productCode = "130999";
        String regDate = "20191214";
        String productName = "온라인 보통예금";
        SubjectCode subjectCode = SubjectCode.REGULAR;

        TaxationCode taxation = TaxationCode.REGULAR;
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(productCode)
                .regDate(regDate)
                .taxationCode(taxation)
                .build();

        //when & then
        mockMvc.perform(post("/api/account/regular")
                        .header(HttpHeaders.AUTHORIZATION, this.getBearerToken())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(accountRequestOpen))
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("accountNum").exists())
                .andExpect(jsonPath("basicRate").exists())
                .andExpect(jsonPath("regDate").value(regDate))
                .andExpect(jsonPath("taxationCode").value(taxation.toString()))
                .andExpect(jsonPath("closeDate").isEmpty())
                .andExpect(jsonPath("lastIntsDt").value(OBDateUtils.addDays(regDate, -1)))
                .andExpect(jsonPath("balance").value(0))
                .andExpect(jsonPath("accountStatusCode").value(AccountStatusCode.ACTIVE.toString()))
                .andExpect(jsonPath("productCode").value(productCode))
                .andExpect(jsonPath("productName").value(productName))
                .andExpect(jsonPath("subjectCode").value(subjectCode.toString()))
                .andDo(document("create-account",
                        getLinksOfAccount(),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("productCode").description("product code of account"),
                                fieldWithPath("regDate").description("Registration Date of new account"),
                                fieldWithPath("taxationCode").description("way to tax in interest")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        getResponseFieldsOfAccount()
                ));
    }

    @Test
    @TestDescription("인증없이 보통예금 계좌생성 오류 테스트")
    public void openAccountWithoutAuthorization() throws Exception {
        //given
        String regDate = "20191214";
        TaxationCode taxation = TaxationCode.REGULAR;
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate(regDate)
                .taxationCode(taxation)
                .build();

        //when & then
        mockMvc.perform(post("/api/account/regular")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(accountRequestOpen)))
                .andDo(print())
                .andExpect(status().isUnauthorized())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("error").value("unauthorized"))
                .andExpect(jsonPath("error_description").value("Full authentication is required to access this resource"))
        ;
    }

    @Test
    @TestDescription("빈 입력값 오류 테스트")
    public void openAccountEmptyTest() throws Exception {
        //given
        String regDate = "";
        TaxationCode taxation = TaxationCode.REGULAR;
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate(regDate)
                .taxationCode(taxation)
                .build();

        //when & then
        mockMvc.perform(post("/api/account/regular")
                    .header(HttpHeaders.AUTHORIZATION, this.getBearerToken())
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(accountRequestOpen)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("content[0].field").exists())
                .andExpect(jsonPath("content[0].objectName").exists())
                .andExpect(jsonPath("content[0].code").exists())
                .andExpect(jsonPath("content[0].defaultMessage").exists())
                .andExpect(jsonPath("content[0].rejectedValue").isEmpty())
                .andExpect(jsonPath("_links.index.href").exists())
        ;
    }

    @Test
    @TestDescription("계좌 목록 조회")
    public void queryAccounts() throws Exception {
        //given
        String regDate = "20191215";
        TaxationCode taxation = TaxationCode.REGULAR;
        IntStream.range(0,15).forEach(
                e -> createAccount(regDate, taxation)
        );

        //when & then
        this.mockMvc.perform(get("/api/account/regular")
                    .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .param("page", "1")
                    .param("size", "5")
                    .param("sort", "accountNum,DESC"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page").exists())
                .andExpect(jsonPath("_embedded.responseList[0]._links.self").exists())
                .andExpect(jsonPath("_embedded.responseList[0].accountNum").exists())
                .andExpect(jsonPath("_embedded.responseList[0].basicRate").exists())
                .andExpect(jsonPath("_embedded.responseList[0].regDate").value(regDate))
                .andExpect(jsonPath("_embedded.responseList[0].taxationCode").value(taxation.toString()))
                .andExpect(jsonPath("_embedded.responseList[0].closeDate").isEmpty())
                .andExpect(jsonPath("_embedded.responseList[0].lastIntsDt").value(OBDateUtils.addDays(regDate, -1)))
                .andExpect(jsonPath("_embedded.responseList[0].balance").value(0))
                .andExpect(jsonPath("_embedded.responseList[0].accountStatusCode").value(AccountStatusCode.ACTIVE.toString()))
                .andExpect(jsonPath("_embedded.responseList[0].productCode").value(PRODUCT_CODE))
                .andExpect(jsonPath("_embedded.responseList[0].productName").value("온라인 보통예금"))
                .andExpect(jsonPath("_embedded.responseList[0].subjectCode").value(SubjectCode.REGULAR.toString()))
                .andDo(document("query-accounts",
                        links(
                                linkWithRel("first").description("link to first page"),
                                linkWithRel("prev").description("link to prev page"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("next").description("link to next page"),
                                linkWithRel("last").description("link to last page"),
                                linkWithRel("create-account").description("link to open account."),
                                linkWithRelAsProfile()
                        ),
                        requestParameters(
                                parameterWithName("page").description("현재 페이지 번호. 0페이지 부터 시작."),
                                parameterWithName("size").description("한 페이지의 사이즈"),
                                parameterWithName("sort").description("데이터 정렬. ex)name, DESC")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("_embedded.responseList[0].accountNum").description("identification number of account"),
                                fieldWithPath("_embedded.responseList[0].basicRate").description("interest rate of this account"),
                                fieldWithPath("_embedded.responseList[0].regDate").description("Registration Date of new account"),
                                fieldWithPath("_embedded.responseList[0].closeDate").description("Close Date of account"),
                                fieldWithPath("_embedded.responseList[0].taxationCode").description("way to tax in interest"),
                                fieldWithPath("_embedded.responseList[0].lastIntsDt").description("the last calculated date of account interest"),
                                fieldWithPath("_embedded.responseList[0].balance").description("balance of account"),
                                fieldWithPath("_embedded.responseList[0].productCode").description("product code of account"),
                                fieldWithPath("_embedded.responseList[0].productName").description("name of product"),
                                fieldWithPath("_embedded.responseList[0].subjectCode").description("code of account type"),
                                fieldWithPath("_embedded.responseList[0].accountStatusCode").description("status of account"),
                                fieldWithPath("_embedded.responseList[0]._links.self.href").description("link to self."),
                                fieldWithPathAsSelf(),
                                fieldWithPathAsProfile(),
                                fieldWithPath("_links.first.href").description("link to first."),
                                fieldWithPath("_links.prev.href").description("link to prev."),
                                fieldWithPath("_links.next.href").description("link to next."),
                                fieldWithPath("_links.last.href").description("link to last."),
                                fieldWithPath("_links.create-account.href").description("link to open account."),
                                fieldWithPath("page.size").description("size of one page."),
                                fieldWithPath("page.totalElements").description("amount of datas."),
                                fieldWithPath("page.totalPages").description("amount of pages."),
                                fieldWithPath("page.number").description("current page number.")
                        )
                ));
    }

    @Test
    @TestDescription("인증된 사용자의 계좌 단건 조회")
    public void getAccount() throws Exception {
        //given
        String regDate = "20191215";
        TaxationCode taxation = TaxationCode.REGULAR;
        String accountNum = createAccount(regDate, taxation);

        //when & then
        mockMvc.perform(get("/api/account/regular/{accountNum}", accountNum)
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("accountNum").exists())
                .andExpect(jsonPath("basicRate").exists())
                .andExpect(jsonPath("regDate").value(regDate))
                .andExpect(jsonPath("taxationCode").value(taxation.toString()))
                .andExpect(jsonPath("closeDate").isEmpty())
                .andExpect(jsonPath("lastIntsDt").value(OBDateUtils.addDays(regDate, -1)))
                .andExpect(jsonPath("balance").value(0))
                .andExpect(jsonPath("accountStatusCode").value(AccountStatusCode.ACTIVE.toString()))
                .andDo(document("get-account",
                        getLinksOfAccount(),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Authorization header")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        getResponseFieldsOfAccount()
                ));
    }

    private ResponseFieldsSnippet getResponseFieldsOfAccount() {
        return responseFields(
                fieldWithPath("accountNum").description("identification number of new account"),
                fieldWithPath("basicRate").description("interest rate of this account"),
                fieldWithPath("regDate").description("Registration Date of new account"),
                fieldWithPath("closeDate").description("Close Date of account"),
                fieldWithPath("taxationCode").description("way to tax in interest"),
                fieldWithPath("lastIntsDt").description("the last calculated date of account interest"),
                fieldWithPath("balance").description("balance of account"),
                fieldWithPath("productCode").description("product code of account"),
                fieldWithPath("productName").description("name of product"),
                fieldWithPath("subjectCode").description("code of account type"),
                fieldWithPath("accountStatusCode").description("status of account"),
                fieldWithPathAsSelf(),
                fieldWithPathAsQuery(),
                fieldWithPathAsDeposit(),
                fieldWithPathAsWithdwar(),
                fieldWithPathAsClose(),
                fieldWithPathAsProfile()
        );
    }

    private LinksSnippet getLinksOfAccount() {
        return links(
                linkWithRel("self").description("link to self"),
                linkWithRelAsList(),
                linkWithRel("deposit").description("link to deposit an existing account"),
                linkWithRel("withdraw").description("link to withdraw an existing account"),
                linkWithRel("close").description("link to close an existing account"),
                linkWithRelAsProfile());
    }

    private FieldDescriptor fieldWithPathAsSelf() {
        return fieldWithPath("_links.self.href").description("link to self.");
    }
    private FieldDescriptor fieldWithPathAsQuery() {
        return fieldWithPath("_links.query-accounts.href").description("link to query accountes.");
    }
    private FieldDescriptor fieldWithPathAsDeposit() {
        return fieldWithPath("_links.deposit.href").description("link to deposit existing account.");
    }
    private FieldDescriptor fieldWithPathAsWithdwar() {
        return fieldWithPath("_links.withdraw.href").description("link to withdraw existing account.");
    }
    private FieldDescriptor fieldWithPathAsClose() {
        return fieldWithPath("_links.close.href").description("link to close existing account.");
    }
    private FieldDescriptor fieldWithPathAsProfile() {
        return fieldWithPath("_links.profile.href").description("link to profile.");
    }
    private LinkDescriptor linkWithRelAsList() {
        return linkWithRel("query-accounts").description("link to query accounts");
    }
    private LinkDescriptor linkWithRelAsProfile() {
        return linkWithRel("profile").description("link to profile.");
    }

    @Test
    @TestDescription("정상 입금 테스트")
    public void accountDeposit() throws Exception {
        //given
        String tradeDate = "20191215";
        long amount = 100000;
        TaxationCode taxation = TaxationCode.REGULAR;

        String accountNum = createAccount(tradeDate, taxation);

        TradeDto.RequestDeposit requestDeposit = TradeDto.RequestDeposit.builder()
                .amount(amount)
                .tradeDate(tradeDate)
                .build();

        //when & then
        mockMvc.perform(put("/api/account/regular/{accountNum}/deposit", accountNum)
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.objectMapper.writeValueAsString(requestDeposit))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("srno").exists())
                .andExpect(jsonPath("tradeDate").value(tradeDate))
                .andExpect(jsonPath("bzDate").value(OBDateUtils.getToday()))
                .andExpect(jsonPath("amount").value(amount))
                .andExpect(jsonPath("blncBefore").value(0))
                .andExpect(jsonPath("blncAfter").value(amount))
                .andExpect(jsonPath("tradeCd").value(TradeCd.DEPOSIT.toString()))
                .andDo(document("account-deposit",
                        getLinksOfAccount(),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Authorization header")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("srno").description("serial number of trade"),
                                fieldWithPath("tradeDate").description("trade date as a request"),
                                fieldWithPath("bzDate").description("real trade date or system date"),
                                fieldWithPath("amount").description("trade amount"),
                                fieldWithPath("blncBefore").description("the balance before trade"),
                                fieldWithPath("blncAfter").description("the balance after trade"),
                                fieldWithPath("tradeCd").description("type of trade"),
                                fieldWithPathAsSelf(),
                                fieldWithPathAsQuery(),
                                fieldWithPathAsDeposit(),
                                fieldWithPathAsWithdwar(),
                                fieldWithPathAsClose(),
                                fieldWithPathAsProfile()
                        )
                ));
    }


    @Test
    @TestDescription("다건 입금 정상 테스트")
    public void accountDepositMulti() throws Exception {
        //given
        long amount1 = 100000;
        long amount2 = 10000;

        String tradeDate = "20191215";
        TaxationCode taxation = TaxationCode.REGULAR;
        String accountNum = createAccount(tradeDate, taxation);

        deposit(tradeDate, amount1, accountNum);

        TradeDto.RequestDeposit requestDeposit = TradeDto.RequestDeposit.builder()
                .amount(amount2)
                .tradeDate(tradeDate)
                .build();

        //when & then
        mockMvc.perform(put("/api/account/regular/{accountNum}/deposit", accountNum)
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.objectMapper.writeValueAsString(requestDeposit))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("srno").value(3L))
                .andExpect(jsonPath("tradeDate").value(tradeDate))
                .andExpect(jsonPath("bzDate").value(OBDateUtils.getToday()))
                .andExpect(jsonPath("amount").value(amount2))
                .andExpect(jsonPath("blncBefore").value(amount1))
                .andExpect(jsonPath("blncAfter").value(amount1+amount2))
                .andExpect(jsonPath("tradeCd").value(TradeCd.DEPOSIT.toString()));
    }

    @Test
    @TestDescription("정상 출금 테스트")
    public void accountWithdraw() throws Exception {
        //given
        long depositAmount = 100000;
        long withdrawAmount = 30000;

        String tradeDate = "20191215";
        TaxationCode taxation = TaxationCode.REGULAR;
        String accountNum = createAccount(tradeDate, taxation);

        deposit(tradeDate, depositAmount, accountNum);

        TradeDto.RequestDeposit requestDeposit = TradeDto.RequestDeposit.builder()
                .amount(withdrawAmount)
                .tradeDate(tradeDate)
                .build();

        //when & then
        mockMvc.perform(put("/api/account/regular/{accountNum}/withdraw", accountNum)
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.objectMapper.writeValueAsString(requestDeposit))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("srno").value(3L))
                .andExpect(jsonPath("tradeDate").value(tradeDate))
                .andExpect(jsonPath("bzDate").value(OBDateUtils.getToday()))
                .andExpect(jsonPath("amount").value(withdrawAmount))
                .andExpect(jsonPath("blncBefore").value(depositAmount))
                .andExpect(jsonPath("blncAfter").value(depositAmount-withdrawAmount))
                .andExpect(jsonPath("tradeCd").value(TradeCd.WITHDRAW.toString()))
                .andDo(document("account-withdraw",
                        getLinksOfAccount(),
                        requestHeaders(
                                headerWithName(HttpHeaders.AUTHORIZATION).description("Authorization header")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("srno").description("serial number of trade"),
                                fieldWithPath("tradeDate").description("trade date as a request"),
                                fieldWithPath("bzDate").description("real trade date or system date"),
                                fieldWithPath("amount").description("trade amount"),
                                fieldWithPath("blncBefore").description("the balance before trade"),
                                fieldWithPath("blncAfter").description("the balance after trade"),
                                fieldWithPath("tradeCd").description("type of trade"),
                                fieldWithPathAsSelf(),
                                fieldWithPathAsQuery(),
                                fieldWithPathAsDeposit(),
                                fieldWithPathAsWithdwar(),
                                fieldWithPathAsClose(),
                                fieldWithPathAsProfile()
                        )
                ));
    }

    @Test
    @TestDescription("잔액 초과 출금 오류 테스트")
    public void accountWithdrawOverBalance() throws Exception {
        //given
        long depositAmount = 100000;
        long withdrawAmount = 130000;

        String tradeDate = "20191215";
        TaxationCode taxation = TaxationCode.REGULAR;
        String accountNum = createAccount(tradeDate, taxation);

        deposit(tradeDate, depositAmount, accountNum);

        TradeDto.RequestDeposit requestDeposit = TradeDto.RequestDeposit.builder()
                .amount(withdrawAmount)
                .tradeDate(tradeDate)
                .build();

        //when & then
        mockMvc.perform(put("/api/account/regular/{accountNum}/withdraw", accountNum)
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(this.objectMapper.writeValueAsString(requestDeposit))
                )
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(jsonPath("message").value("출금가능금액 부족"))
                .andExpect(jsonPath("status").value("INTERNAL_SERVER_ERROR"))
                .andDo(document("errors",
                        responseFields(
                                fieldWithPath("message").description("error message"),
                                fieldWithPath("status").description("result")
                        )
                ));
    }


    @Test
    @TestDescription("지정일자 거래 오류 테스트 - 지정일 이 후 거래 존재시 오류 발생")
    public void reckonDepositAfterTrade_500error() throws Exception {
        //given
        long depositAmount = 130000;
        String depositDate = "20191215";
        long withdrawAmount = 100000;
        String withdrawDate = "20191210";

        TaxationCode taxation = TaxationCode.REGULAR;
        String accountNum = createAccount(depositDate, taxation);

        deposit(depositDate, depositAmount, accountNum);

        TradeDto.RequestDeposit requestDeposit = TradeDto.RequestDeposit.builder()
                .amount(withdrawAmount)
                .tradeDate(withdrawDate)
                .build();

        //when & then
        mockMvc.perform(put("/api/account/regular/{accountNum}/withdraw", accountNum)
                .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(this.objectMapper.writeValueAsString(requestDeposit))
        )
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8.toString()))
                .andExpect(jsonPath("message").value("지정일 이 후 거래가 존재. 기산일 거래를 요청해야 함."))
                .andExpect(jsonPath("status").value("INTERNAL_SERVER_ERROR"))
                .andDo(document("errors",
                        responseFields(
                                fieldWithPath("message").description("error message"),
                                fieldWithPath("status").description("result")
                        )
                ));
    }

    private String createAccount(String tradeDate, TaxationCode taxation) {
        AccountDto.RequestOpen accountRequestOpen = AccountDto.RequestOpen.builder()
                .productCode(PRODUCT_CODE)
                .regDate(tradeDate)
                .taxationCode(taxation)
                .build();
        Long accountId = accountService.openRegularAccount(accountRequestOpen);
        return accountRepository.findById(accountId).get().getAccountNum();
    }

    @Test
    @TestDescription("거래내역 조회 정상 테스트")
    public void getTradeList() throws Exception {
        //given
        // TODO
        String tradeDate = "20191015";
        TaxationCode taxation = TaxationCode.REGULAR;

        String accountNum = createAccount(tradeDate, taxation);

        IntStream.range(1,15).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e, accountNum);
        });

        //when & then
        mockMvc.perform(get("/api/account/regular/{accountNum}/trade", accountNum)
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                        .param("page", "1")
                        .param("size", "5")
                        .param("sort", "srno,DESC")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.responseList[0].srno").exists())
                .andExpect(jsonPath("_embedded.responseList[0].tradeDate").exists())
                .andExpect(jsonPath("_embedded.responseList[0].bzDate").exists())
                .andExpect(jsonPath("_embedded.responseList[0].amount").exists())
                .andExpect(jsonPath("_embedded.responseList[0].blncBefore").exists())
                .andExpect(jsonPath("_embedded.responseList[0].blncAfter").exists())
                .andExpect(jsonPath("_embedded.responseList[0].tradeCd").exists())
                .andDo(document("query-trade",
                        links(
                                linkWithRel("first").description("link to first page"),
                                linkWithRel("prev").description("link to prev page"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("next").description("link to next page"),
                                linkWithRel("last").description("link to last page"),
                                linkWithRelAsProfile(),
                                linkWithRelAsList(),
                                linkWithRel("deposit").description("link to deposit an existing account"),
                                linkWithRel("withdraw").description("link to withdraw an existing account"),
                                linkWithRel("close").description("link to close an existing account")
                        ),
                        requestParameters(
                                parameterWithName("page").description("현재 페이지 번호. 0페이지 부터 시작."),
                                parameterWithName("size").description("한 페이지의 사이즈"),
                                parameterWithName("sort").description("데이터 정렬. ex)name, DESC")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("_embedded.responseList[0]srno").description("serial number of trade"),
                                fieldWithPath("_embedded.responseList[0]tradeDate").description("trade date as a request"),
                                fieldWithPath("_embedded.responseList[0]bzDate").description("real trade date or system date"),
                                fieldWithPath("_embedded.responseList[0]amount").description("trade amount"),
                                fieldWithPath("_embedded.responseList[0]blncBefore").description("the balance before trade"),
                                fieldWithPath("_embedded.responseList[0]blncAfter").description("the balance after trade"),
                                fieldWithPath("_embedded.responseList[0]tradeCd").description("type of trade"),
                                fieldWithPath("_links.first.href").description("link to first."),
                                fieldWithPath("_links.prev.href").description("link to prev."),
                                fieldWithPath("_links.next.href").description("link to next."),
                                fieldWithPath("_links.last.href").description("link to last."),
                                fieldWithPath("page.size").description("size of one page."),
                                fieldWithPath("page.totalElements").description("amount of datas."),
                                fieldWithPath("page.totalPages").description("amount of pages."),
                                fieldWithPath("page.number").description("current page number."),
                                fieldWithPathAsSelf(),
                                fieldWithPathAsQuery(),
                                fieldWithPathAsDeposit(),
                                fieldWithPathAsWithdwar(),
                                fieldWithPathAsClose(),
                                fieldWithPathAsProfile()

                        )
                ));
    }


    @Test
    @TestDescription("해당 계좌의 거래내역만 조회되는지 테스트")
    public void getTradeListWith500() throws Exception {
        //given
        // TODO
        String tradeDate = "20191015";
        TaxationCode taxation = TaxationCode.REGULAR;

        String accountNum = createAccount(tradeDate, taxation);
        String otherAccountNum = createAccount(tradeDate, taxation);

        IntStream.range(1,5).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e, accountNum);
        });

        IntStream.range(1,10).forEach(e -> {
            deposit(OBDateUtils.addDays(tradeDate, e), e, otherAccountNum);
        });

        //when & then
        mockMvc.perform(get("/api/account/regular/{accountNum}/trade", accountNum)
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "srno,DESC")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.responseList[0].srno").value("5"))
                .andExpect(jsonPath("_embedded.responseList[4].srno").value("1"))
                .andExpect(jsonPath("_embedded.responseList[0].bzDate").value(OBDateUtils.getToday()))
                .andExpect(jsonPath("_embedded.responseList[4].bzDate").value(OBDateUtils.getToday()))
                .andExpect(jsonPath("_embedded.responseList[5].srno").doesNotExist());
    }

    private void deposit(String tradeDate, long amount1, String accountNum) {
        TradeDto.RequestDeposit requestDeposit = TradeDto.RequestDeposit.builder()
                .amount(amount1)
                .tradeDate(tradeDate)
                .build();
        accountService.deposit(accountNum, requestDeposit);
    }
}