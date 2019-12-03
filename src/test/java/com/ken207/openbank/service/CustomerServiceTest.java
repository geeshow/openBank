package com.ken207.openbank.service;

import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.customer.Customer;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.domain.enums.EmployeeType;
import com.ken207.openbank.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CustomerServiceTest {

    @Autowired CustomerService customerService;
    @Autowired CustomerRepository customerRepository;

    @Test
    public void 고객등록테스트() throws Exception {
        //given
        Customer customer = new Customer("박규태", "test@korea.com", "KOR");

        //when
        Long customer_id = customerService.createCustomer(customer);
        Customer findCustomer = customerRepository.findById(customer_id).get();

        //then
        assertEquals("박규태", findCustomer.getName());
        assertNull(findCustomer.getRegBranch());
        assertNull(findCustomer.getMngBranch());
        assertNull(findCustomer.getRegEmployee());
        assertNull(findCustomer.getRegEmployee());
    }


}