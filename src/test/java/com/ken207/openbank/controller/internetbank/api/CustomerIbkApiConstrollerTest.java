package com.ken207.openbank.controller.internetbank.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken207.openbank.common.TestDescription;
import com.ken207.openbank.consts.ConstBranch;
import com.ken207.openbank.customer.CustomerDto;
import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.customer.Customer;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.domain.enums.EmployeeType;
import com.ken207.openbank.repository.CustomerRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerIbkApiConstrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @TestDescription("정상적으로 고객을 생성하는 테스트")
    public void createIbkCustomer() throws Exception {
        //given
        CustomerDto customerDto = CustomerDto.builder()
                .name("박규태")
                .email("test@korea.com")
                .nation("KOR")
                .build();
        //when

        //then
        mockMvc.perform(post("/api/ibk/customer")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("regDt").exists())
                .andExpect(jsonPath("newBranch").exists())
                .andExpect(jsonPath("mngBranch").exists())
                .andExpect(jsonPath("regEmployee").exists())
                .andExpect(jsonPath("email").value("test@korea.com"))
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
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
