package com.ken207.openbank.controller;

import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.user.MemberRole;
import com.ken207.openbank.common.AppSecurityProperties;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.consts.ConstEmployee;
import com.ken207.openbank.controller.BaseControllerTest;
import com.ken207.openbank.domain.CustomerEntity;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.dto.request.CustomerRequest;
import com.ken207.openbank.domain.EmployeeEntity;
import com.ken207.openbank.repository.CustomerRepository;
import com.ken207.openbank.repository.EmployeeRepository;
import com.ken207.openbank.repository.MemberRepository;
import com.ken207.openbank.service.CustomerService;
import com.ken207.openbank.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.common.util.Jackson2JsonParser;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.IntStream;

import static org.springframework.restdocs.headers.HeaderDocumentation.*;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.links;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest extends BaseControllerTest  {

    @Autowired
    CustomerService customerService;

    @Autowired CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    private EmployeeEntity employeeEntity;

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AppSecurityProperties appSecurityProperties;

    /** init 은 Test 전 항상 수행 */
    @Before
    public void init() throws Exception {
        employeeEntity = employeeRepository.findByEmployeeCode(ConstEmployee.INTERNET);
    }

    @Test
    @TestDescription("정상적으로 고객을 생성하는 테스트")
    public void createCustomer() throws Exception {
        //given
        String email = "masterhong@gil.dong.com";
        String nation = "KOREA";
        String name = "홍길동";
        CustomerRequest customerRequest = CustomerRequest.builder()
                .name(name)
                .email(email)
                .nation(nation)
                .build();
        //when

        //then
        mockMvc.perform(post("/api/customer")
                    .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(customerRequest)))
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
                                linkWithRel("customer-list").description("link to query customers"),
                                linkWithRel("customer-update").description("link to update an existing customer"),
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
                                fieldWithPath("_links.customer-list.href").description("link to query customers."),
                                fieldWithPath("_links.customer-update.href").description("link to update existing customer."),
                                fieldWithPath("_links.profile.href").description("link to profile.")
                        )

                ))
        ;
    }



    @Test
    @TestDescription("빈값으로 고객 생성할때 에러가 발생하는 테스트")
    public void createCustomerEmpty() throws Exception {
        //given
        CustomerRequest customerRequest = CustomerRequest.builder().build();

        //when

        //then
        mockMvc.perform(post("/api/customer")
                .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(customerRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("_links.index").exists())
        ;
    }

    @Test
    @TestDescription("잘못된 이메일을 입력 받을때 에러가 발생하는 테스트")
    public void createCustomerWrongEmail() throws Exception {
        //given
        CustomerRequest customerRequest = CustomerRequest.builder()
                .name("Park")
                .email("park.com")
                .nation("KR")
                .build();

        //when

        //then
        mockMvc.perform(post("/api/customer")
                .header(HttpHeaders.AUTHORIZATION, getBearerToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(customerRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("_links.index").exists())
        ;
    }


    @Test
    @TestDescription("잘못된 국가 정보를 입력 받을때 에러가 발생하는 테스트")
    public void createCustomerWrongNation() throws Exception {
        //given
        CustomerRequest customerRequest = CustomerRequest.builder()
                .name("Park")
                .email("park@asdf.com")
                .nation("KR")
                .build();

        //when

        //then
        mockMvc.perform(post("/api/customer")
                .header(HttpHeaders.AUTHORIZATION, this.getBearerToken())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(customerRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("content[0].objectName").exists())
                .andExpect(jsonPath("content[0].defaultMessage").exists())
                .andExpect(jsonPath("content[0].code").exists())
                .andExpect(jsonPath("_links.index").exists())
        ;
    }

    @Test
    @TestDescription("30개의 고객을 10개씩 두번째 페이지 조회하기")
    public void customerList() throws Exception {
        //given
        String page = "1";
        String size = "10";

        IntStream.range(0,30).forEach(this::generateCustomer);

        //when
        this.mockMvc.perform(get("/api/customer")
                    .param("page", page)
                    .param("size", size)
                    .param("sort", "name,DESC")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("page.size").value(size))
                .andExpect(jsonPath("page.number").value(page))
                .andExpect(jsonPath("page.totalPages").exists())
                .andExpect(jsonPath("page.totalElements").exists())
                .andExpect(jsonPath("_embedded.customerResponseList[0].id").exists())
                .andExpect(jsonPath("_embedded.customerResponseList[0].name").exists())
                .andExpect(jsonPath("_embedded.customerResponseList[0].email").exists())
                .andExpect(jsonPath("_embedded.customerResponseList[0].nation").exists())
                .andExpect(jsonPath("_embedded.customerResponseList[0].regBranchName").exists())
                .andExpect(jsonPath("_embedded.customerResponseList[0].mngBranchName").exists())
                .andExpect(jsonPath("_embedded.customerResponseList[0].regEmployeeName").exists())
                .andExpect(jsonPath("_embedded.customerResponseList[0].regDateTime").exists())
                .andExpect(jsonPath("_embedded.customerResponseList[0]._links.self.href").exists())
                .andExpect(jsonPath("_links.first.href").exists())
                .andExpect(jsonPath("_links.prev.href").exists())
                .andExpect(jsonPath("_links.self.href").exists())
                .andExpect(jsonPath("_links.next.href").exists())
                .andExpect(jsonPath("_links.last.href").exists())
                .andExpect(jsonPath("_links.self.href").exists())
                .andExpect(jsonPath("_links.profile.href").exists())
                .andDo(document("customer-list",
                        links(
                            linkWithRel("first").description("link to first page"),
                            linkWithRel("prev").description("link to prev page"),
                            linkWithRel("self").description("link to self"),
                            linkWithRel("next").description("link to next page"),
                            linkWithRel("last").description("link to last page"),
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
                                fieldWithPath("_embedded.customerResponseList[0].").description("identifier of new customer"),
                                fieldWithPath("_embedded.customerResponseList[0].id").description("identifier of new customer"),
                                fieldWithPath("_embedded.customerResponseList[0].name").description("name of new customer"),
                                fieldWithPath("_embedded.customerResponseList[0].email").description("email of new customer"),
                                fieldWithPath("_embedded.customerResponseList[0].nation").description("nation of new customer"),
                                fieldWithPath("_embedded.customerResponseList[0].regDateTime").description("registration date of new customer"),
                                fieldWithPath("_embedded.customerResponseList[0].regBranchName").description("registration branch of new customer"),
                                fieldWithPath("_embedded.customerResponseList[0].mngBranchName").description("management branch of new customer"),
                                fieldWithPath("_embedded.customerResponseList[0].regEmployeeName").description("registration employee of new customer."),
                                fieldWithPath("_embedded.customerResponseList[0]._links.self.href").description("link to self."),
                                fieldWithPath("_links.first.href").description("link to first."),
                                fieldWithPath("_links.prev.href").description("link to prev."),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.next.href").description("link to next."),
                                fieldWithPath("_links.last.href").description("link to last."),
                                fieldWithPath("_links.profile.href").description("link to profile."),
                                fieldWithPath("page.size").description("size of one page."),
                                fieldWithPath("page.totalElements").description("amount of datas."),
                                fieldWithPath("page.totalPages").description("amount of pages."),
                                fieldWithPath("page.number").description("current page number.")
                        )
                ))
        ;

        //then
    }

    private Long generateCustomer(int index) {
        CustomerEntity customerEntity = new CustomerEntity("고객 " + index, "customer" + index + "@gmail.com", "KOREA");
        return customerService.createCustomer(customerEntity, employeeEntity.getId());
    }

    @Test
    @TestDescription("고객정보 단건 정상 조회")
    public void getCustomer() throws Exception {
        //given
        int index = 500;
        Long customerId = generateCustomer(index);

        //when & then
        mockMvc.perform(get("/api/customer/{id}", customerId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("고객 " + index))
                .andExpect(jsonPath("email").value("customer" + index + "@gmail.com"))
                .andExpect(jsonPath("nation").value("KOREA"))
                .andExpect(jsonPath("regBranchName").exists())
                .andExpect(jsonPath("mngBranchName").exists())
                .andExpect(jsonPath("regEmployeeName").exists())
                .andExpect(jsonPath("regDateTime").exists())
                .andDo(document("get-customer",
                        responseHeaders(
                                headerWithName(HttpHeaders.CONTENT_TYPE).description("HAL/JSON type content type")
                        ),
                        responseFields(
                                fieldWithPath("id").description("identifier of customer"),
                                fieldWithPath("name").description("name of customer"),
                                fieldWithPath("email").description("email of customer"),
                                fieldWithPath("nation").description("nation of customer"),
                                fieldWithPath("regBranchName").description("name of registration branch"),
                                fieldWithPath("mngBranchName").description("name of management branch"),
                                fieldWithPath("regEmployeeName").description("name of registration employee"),
                                fieldWithPath("regDateTime").description("registration time"),
                                fieldWithPath("_links.self.href").description("link to self."),
                                fieldWithPath("_links.customer-update.href").description("link to update."),
                                fieldWithPath("_links.customer-list.href").description("link to list."),
                                fieldWithPath("_links.profile.href").description("link to profile.")
                        )))
        ;
    }
}
