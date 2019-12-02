package com.ken207.openbank.controller.internetbank.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken207.openbank.common.RestDocsConfiguration;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.customer.CustomerCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import(RestDocsConfiguration.class)
public class CustomerIbkApiConstrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @TestDescription("정상적으로 고객을 생성하는 테스트")
    public void createIbkCustomer() throws Exception {
        //given
        String email = "masterhong@gil.dong.com";
        String nation = "KOREA";
        String name = "홍길동";
        CustomerCreateRequest customerCreateRequest = CustomerCreateRequest.builder()
                .name(name)
                .email(email)
                .nation(nation)
                .build();
        //when

        //then
        mockMvc.perform(post("/api/ibk/customer")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(customerCreateRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("nation").exists())
                .andExpect(jsonPath("regDateTime").exists())
                .andExpect(jsonPath("regBranchName").exists())
                .andExpect(jsonPath("mngBranchName").exists())
                .andExpect(jsonPath("regEmployeeName").exists())
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("nation").value(nation))
                .andExpect(jsonPath("name").value(name))
                .andDo(document("create-customer",
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
    @TestDescription("빈값으로 고객 생성할때 에러가 발생하는 테스트")
    public void createIbkCustomerEmpty() throws Exception {
        //given
        CustomerCreateRequest customerCreateRequest = CustomerCreateRequest.builder().build();

        //when

        //then
        mockMvc.perform(post("/api/ibk/customer")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(customerCreateRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @TestDescription("잘못된 이메일을 입력 받을때 에러가 발생하는 테스트")
    public void createIbkCustomerWrongEmail() throws Exception {
        //given
        CustomerCreateRequest customerCreateRequest = CustomerCreateRequest.builder()
                .name("Park")
                .email("park.com")
                .nation("KR")
                .build();

        //when

        //then
        mockMvc.perform(post("/api/ibk/customer")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(customerCreateRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }


    @Test
    @TestDescription("잘못된 국가 정보를 입력 받을때 에러가 발생하는 테스트")
    public void createIbkCustomerWrongNation() throws Exception {
        //given
        CustomerCreateRequest customerCreateRequest = CustomerCreateRequest.builder()
                .name("Park")
                .email("park@asdf.com")
                .nation("KR")
                .build();

        //when

        //then
        mockMvc.perform(post("/api/ibk/customer")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(customerCreateRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].objectName").exists())
                .andExpect(jsonPath("$[0].defaultMessage").exists())
                .andExpect(jsonPath("$[0].code").exists())
        ;
    }
}
