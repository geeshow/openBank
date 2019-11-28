package com.ken207.openbank.controller.internetbank.api;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
@WebMvcTest
public class CustomerIbApiConstrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    public void createIbkCustomer() throws Exception {
        //given
        Branch internetBranch = new Branch(ConstBranch.INTERNET_ID20,"인터넷뱅킹", BranchType.인터넷);
        Employee internetEmployee = new Employee("인터넷사용자", EmployeeType.인터넷뱅킹, internetBranch);
        Customer customer = new Customer("박규태", "test@korea.com", internetEmployee);
        CustomerDto customerDto = new CustomerDto("박규태", "test@korea.com");

        //when
        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        //then
        mockMvc.perform(post("/api/ibk/customer")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(customerDto)))
                .andDo(print())
                .andExpect(status().isCreated())
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

}
