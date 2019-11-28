package com.ken207.openbank.controller.internetbank.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ken207.openbank.consts.ConstBranch;
import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.Customer;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.domain.Product;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.domain.enums.EmployeeType;
import com.ken207.openbank.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
        Customer customer = new Customer("박규태", "한국",
                new Employee("인터넷사용자", EmployeeType.인터넷뱅킹,
                        new Branch(ConstBranch.INTERNET_ID20,"인터넷뱅킹", BranchType.인터넷)));

        customer.setId((long) 10);

        Mockito.when(customerRepository.save(customer)).thenReturn(customer);

        mockMvc.perform(post("/api/ibk/customer")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(customer)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_UTF8_VALUE))
        ;
    }

}
