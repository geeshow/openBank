package com.ken207.openbank.controller.internetbank.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken207.openbank.common.RestDocsConfiguration;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.customer.CustomerDto;
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
        String email = "test@korea.com";
        String nation = "KOR";
        CustomerDto customerDto = CustomerDto.builder()
                .name("박규태")
                .email(email)
                .nation(nation)
                .build();
        //when

        //then
        mockMvc.perform(post("/api/ibk/customer")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("regDt").exists())
                .andExpect(jsonPath("newBranch").exists())
                .andExpect(jsonPath("mngBranch").exists())
                .andExpect(jsonPath("regEmployee").exists())
                .andExpect(jsonPath("email").value(email))
                .andExpect(jsonPath("nation").value(nation))
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.query-customers").exists())
                .andExpect(jsonPath("_links.update-customer").exists())
                .andDo(document("create-customer",
                        links(
                                linkWithRel("self").description("link to self"),
                                linkWithRel("query-customers").description("link to query customers"),
                                linkWithRel("update-customer").description("link to update an existing customer")
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
                        // TODO ResponseFields로 변경
                        relaxedResponseFields(
                                fieldWithPath("id").description("identifier of new customer"),
                                fieldWithPath("name").description("name of new customer"),
                                fieldWithPath("email").description("email of new customer"),
                                fieldWithPath("nation").description("nation of new customer"),
                                fieldWithPath("regDt").description("registration date of new customer"),
                                fieldWithPath("newBranch").description("registration branch of new customer"),
                                fieldWithPath("mngBranch").description("management branch of new customer"),
                                fieldWithPath("regEmployee").description("registration employee of new customer.")
                        )

                ))
        ;
    }



    @Test
    @TestDescription("빈값으로 고객 생성할때 에러가 발생하는 테스트")
    public void createIbkCustomerEmpty() throws Exception {
        //given
        CustomerDto customerDto = CustomerDto.builder().build();

        //when

        //then
        mockMvc.perform(post("/api/ibk/customer")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    @TestDescription("잘못된 이메일을 입력 받을때 에러가 발생하는 테스트")
    public void createIbkCustomerWrongEmail() throws Exception {
        //given
        CustomerDto customerDto = CustomerDto.builder()
                .name("Park")
                .email("park.com")
                .nation("KR")
                .build();

        //when

        //then
        mockMvc.perform(post("/api/ibk/customer")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }


    @Test
    @TestDescription("잘못된 국가 정보를 입력 받을때 에러가 발생하는 테스트")
    public void createIbkCustomerWrongNation() throws Exception {
        //given
        CustomerDto customerDto = CustomerDto.builder()
                .name("Park")
                .email("park@asdf.com")
                .nation("KR")
                .build();

        //when

        //then
        mockMvc.perform(post("/api/ibk/customer")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].objectName").exists())
                .andExpect(jsonPath("$[0].defaultMessage").exists())
                .andExpect(jsonPath("$[0].code").exists())
        ;
    }
}
