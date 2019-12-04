package com.ken207.openbank.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.dto.request.BranchCreateRequest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class BranchApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @TestDescription("정상적으로 지점을 생성하는 테스트")
    public void createBranch() throws Exception {
        String branchName = "테스트지점";
        BranchCreateRequest branchCreateRequest = BranchCreateRequest.builder()
                .name(branchName)
                .businessNumber("123-12-12345")
                .taxOfficeCode("112")
                .telNumber("02-1234-1234")
                .branchType(BranchType.지점)
                .build();

        mockMvc.perform(post("/api/branch")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(branchCreateRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("businessNumber").exists())
                .andExpect(jsonPath("taxOfficeCode").exists())
                .andExpect(jsonPath("telNumber").exists())
                .andExpect(jsonPath("regDateTime").exists())
                .andExpect(jsonPath("branchType").exists())
                .andDo(document("create-branch",
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("query-customers").description("link to query customers"),
                                linkWithRel("update-customer").description("link to update an existing customer"),
                                linkWithRel("profile").description("link to profile.")
                        ),
                        requestHeaders(
                                headerWithName(HttpHeaders.ACCEPT).description("accept header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("content type header")
                        ),
                        requestFields(
                                fieldWithPath("name").description("Name of new customer"),
                                fieldWithPath("email").description("Email of new customer"),
                                fieldWithPath("nation").description("Nation of new customer")
                        ),
                        responseHeaders(
                                headerWithName(HttpHeaders.LOCATION).description("Location header"),
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identifier of new customer"),
                                fieldWithPath("name").description("name of new customer"),
                                fieldWithPath("email").description("email of new customer"),
                                fieldWithPath("nation").description("nation of new customer"),
                                fieldWithPath("regDateTime").description("registration date of new customer"),
                                fieldWithPath("regBranchName").description("registration branch of new customer"),
                                fieldWithPath("mngBranchName").description("management branch of new customer"),
                                fieldWithPath("regEmployeeName").description("registration employee of new customer."),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.query-customers.href").description("link to query customers."),
                                fieldWithPath("_links.update-customer.href").description("link to update existing customer."),
                                fieldWithPath("_links.profile.href").description("link to profile.")
                        )

                ))
        ;
    }

    @Test
    @TestDescription("30개의 지점을 10개씩 두번째 페이지 조회하기")
    public void queryBranches() {
    }
}