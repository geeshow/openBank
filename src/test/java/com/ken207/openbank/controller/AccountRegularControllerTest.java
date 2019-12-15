package com.ken207.openbank.controller;

import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.domain.enums.AccountStatusCode;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.enums.TaxationCode;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.user.MemberRole;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.Jackson2JsonParser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Set;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class AccountRegularControllerTest extends BaseControllerTest {

    @Autowired
    AccountRegularController accountRegularController;

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
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("query-accounts").description("link to query accounts"),
                                linkWithRel("deposit").description("link to deposit an existing account"),
                                linkWithRel("withdraw").description("link to withdraw an existing account"),
                                linkWithRel("close").description("link to close an existing account"),
                                linkWithRel("profile").description("link to profile.")
                        ),
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
                        responseFields(
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
                        )

                ))
        ;
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
        ;
    }

    @Test
    @TestDescription("등록일 입력값 오류 테스트")
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
        ;
    }
}