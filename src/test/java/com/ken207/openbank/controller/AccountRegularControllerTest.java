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


    public String getBearerToken2() throws Exception {
        //given
        MemberEntity member = MemberEntity.builder()
                .email(appSecurityProperties.getUserUsername())
                .password(appSecurityProperties.getUserPassword())
                .roles(Set.of(MemberRole.USER))
                .build();
        memberService.createUser(member);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("username", appSecurityProperties.getUserUsername());
        params.add("password", appSecurityProperties.getUserPassword());

        this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(appSecurityProperties.getDefaultClientId(), appSecurityProperties.getDefaultClientSecret()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params)
        );
        ResultActions perform = this.mockMvc.perform(post("/oauth/token")
                .with(httpBasic(appSecurityProperties.getDefaultClientId(), appSecurityProperties.getDefaultClientSecret()))
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .params(params)
        );
        String responseBody = perform.andReturn().getResponse().getContentAsString();
        Jackson2JsonParser parser = new Jackson2JsonParser();
        return "Bearer " + parser.parseMap(responseBody).get("access_token").toString();
    }

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
                .header(HttpHeaders.AUTHORIZATION, getBearerToken2())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(accountRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("accoutNum").exists())
                .andExpect(jsonPath("regDate").value(regDate))
                .andExpect(jsonPath("taxationCode").value(taxation))
                .andExpect(jsonPath("closeDate").value(regDate))
                .andExpect(jsonPath("lastIntsDt").value(regDate))
                .andExpect(jsonPath("accoBlnc").value(0))
                .andExpect(jsonPath("subjectCode").value(SubjectCode.REGULAR))
                .andExpect(jsonPath("accoStcd").value(AccountStatusCode.ACTIVE))
                .andDo(document("create-account",
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("query-accounts").description("link to query accounts"),
                                linkWithRel("update-account").description("link to update an existing account"),
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
                                fieldWithPath("accoutNum").description("Number of new account"),
                                fieldWithPath("regDate").description("Registration Date of new account"),
                                fieldWithPath("taxationCode").description("way to tax in interest"),
                                fieldWithPath("lastIntsDt").description("the last calculated date of account interest"),
                                fieldWithPath("accoBlnc").description("balance of account"),
                                fieldWithPath("subjectCode").description("code of account type"),
                                fieldWithPath("accoStcd").description("status of account"),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.query-accounts.href").description("link to query accountes."),
                                fieldWithPath("_links.update-account.href").description("link to update existing account."),
                                fieldWithPath("_links.profile.href").description("link to profile.")
                        )

                ))
        ;
    }
}