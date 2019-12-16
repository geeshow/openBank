package com.ken207.openbank.controller;

import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.enums.AccountStatusCode;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.enums.TaxationCode;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.service.AccountService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.hypermedia.LinksSnippet;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import java.util.stream.IntStream;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AccountRegularControllerTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Test
    @TestDescription("보통예금 계좌생성 정상 테스트")
    public void openAccount() throws Exception {
        //given
        String regDate = "20191214";
        TaxationCode taxation = TaxationCode.REGULAR;
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(regDate)
                .taxationCode(taxation)
                .build();

        //when & then
        mockMvc.perform(post("/api/account/regular")
                .header(HttpHeaders.AUTHORIZATION, this.getBearerToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(accountRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("accountNum").exists())
                .andExpect(jsonPath("regDate").value(regDate))
                .andExpect(jsonPath("taxationCode").value(taxation.toString()))
                .andExpect(jsonPath("closeDate").isEmpty())
                .andExpect(jsonPath("lastIntsDt").value(regDate))
                .andExpect(jsonPath("accoBlnc").value(0))
                .andExpect(jsonPath("subjectCode").value(SubjectCode.REGULAR.toString()))
                .andExpect(jsonPath("accountStatusCode").value(AccountStatusCode.ACTIVE.toString()))
                .andDo(document("create-account",
                        getLinksOfAccount(),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
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
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(regDate)
                .taxationCode(taxation)
                .build();

        //when & then
        mockMvc.perform(post("/api/account/regular")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(accountRequest)))
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
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(regDate)
                .taxationCode(taxation)
                .build();

        //when & then
        mockMvc.perform(post("/api/account/regular")
                    .header(HttpHeaders.AUTHORIZATION, this.getBearerToken())
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(accountRequest)))
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
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(regDate)
                .taxationCode(taxation)
                .build();

        IntStream.range(0,50).forEach(
                e -> accountService.openRegularAccount(accountRequest)
        );

        //when & then
        this.mockMvc.perform(get("/api/account/regular")
                    .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .param("page", "1")
                    .param("size", "10")
                    .param("sort", "accountNum,DESC"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page").exists())
                .andExpect(jsonPath("_embedded.responseList[0]._links.self").exists())
                .andExpect(jsonPath("_embedded.responseList[0].accountNum").exists())
                .andExpect(jsonPath("_embedded.responseList[0].regDate").value(regDate))
                .andExpect(jsonPath("_embedded.responseList[0].taxationCode").value(taxation.toString()))
                .andExpect(jsonPath("_embedded.responseList[0].closeDate").isEmpty())
                .andExpect(jsonPath("_embedded.responseList[0].lastIntsDt").value(regDate))
                .andExpect(jsonPath("_embedded.responseList[0].accoBlnc").value(0))
                .andExpect(jsonPath("_embedded.responseList[0].subjectCode").value(SubjectCode.REGULAR.toString()))
                .andExpect(jsonPath("_embedded.responseList[0].accountStatusCode").value(AccountStatusCode.ACTIVE.toString()))
                .andDo(document("query-accounts",
                        links(
                                linkWithRel("first").description("link to first page"),
                                linkWithRel("prev").description("link to prev page"),
                                linkWithRel("self").description("link to self"),
                                linkWithRel("next").description("link to next page"),
                                linkWithRel("last").description("link to last page"),
                                linkWithRel("create-account").description("link to open account."),
                                linkWithRel("profile").description("link to profile.")
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
                                fieldWithPath("_embedded.responseList[0].accountNum").description("Number of new account"),
                                fieldWithPath("_embedded.responseList[0].regDate").description("Registration Date of new account"),
                                fieldWithPath("_embedded.responseList[0].closeDate").description("Close Date of account"),
                                fieldWithPath("_embedded.responseList[0].taxationCode").description("way to tax in interest"),
                                fieldWithPath("_embedded.responseList[0].lastIntsDt").description("the last calculated date of account interest"),
                                fieldWithPath("_embedded.responseList[0].accoBlnc").description("balance of account"),
                                fieldWithPath("_embedded.responseList[0].subjectCode").description("code of account type"),
                                fieldWithPath("_embedded.responseList[0].accountStatusCode").description("status of account"),
                                fieldWithPath("_embedded.responseList[0]._links.self.href").description("link to self."),
                                fieldWithPath("_links.first.href").description("link to first."),
                                fieldWithPath("_links.prev.href").description("link to prev."),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.next.href").description("link to next."),
                                fieldWithPath("_links.last.href").description("link to last."),
                                fieldWithPath("_links.create-account.href").description("link to open account."),
                                fieldWithPath("_links.profile.href").description("link to profile."),
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
        AccountDto.Request accountRequest = AccountDto.Request.builder()
                .regDate(regDate)
                .taxationCode(taxation)
                .build();
        String accountNum = accountService.openRegularAccount(accountRequest);

        //when & then
        mockMvc.perform(get("/api/account/regular/{accountNum}", accountNum)
                        .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("accountNum").exists())
                .andExpect(jsonPath("regDate").value(regDate))
                .andExpect(jsonPath("taxationCode").value(taxation.toString()))
                .andExpect(jsonPath("closeDate").isEmpty())
                .andExpect(jsonPath("lastIntsDt").value(regDate))
                .andExpect(jsonPath("accoBlnc").value(0))
                .andExpect(jsonPath("subjectCode").value(SubjectCode.REGULAR.toString()))
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

    private LinksSnippet getLinksOfAccount() {
        return links(
                linkWithRel("self").description("link to self"),
                linkWithRel("query-accounts").description("link to query accounts"),
                linkWithRel("deposit").description("link to deposit an existing account"),
                linkWithRel("withdraw").description("link to withdraw an existing account"),
                linkWithRel("close").description("link to close an existing account"),
                linkWithRel("profile").description("link to profile."));
    }

    private ResponseFieldsSnippet getResponseFieldsOfAccount() {
        return responseFields(
                fieldWithPath("accountNum").description("Number of new account"),
                fieldWithPath("regDate").description("Registration Date of new account"),
                fieldWithPath("closeDate").description("Close Date of account"),
                fieldWithPath("taxationCode").description("way to tax in interest"),
                fieldWithPath("lastIntsDt").description("the last calculated date of account interest"),
                fieldWithPath("accoBlnc").description("balance of account"),
                fieldWithPath("subjectCode").description("code of account type"),
                fieldWithPath("accountStatusCode").description("status of account"),
                fieldWithPath("_links.self.href").description("link to self."),
                fieldWithPath("_links.query-accounts.href").description("link to query accountes."),
                fieldWithPath("_links.deposit.href").description("link to deposit existing account."),
                fieldWithPath("_links.withdraw.href").description("link to withdraw existing account."),
                fieldWithPath("_links.close.href").description("link to close existing account."),
                fieldWithPath("_links.profile.href").description("link to profile.")
        );
    }


}