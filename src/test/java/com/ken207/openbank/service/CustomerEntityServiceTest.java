package com.ken207.openbank.service;

import com.ken207.openbank.domain.CustomerEntity;
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
public class CustomerEntityServiceTest {

    @Autowired CustomerService customerService;
    @Autowired CustomerRepository customerRepository;

    @Test
    public void createCustomerServiceTest() throws Exception {
        //given
        CustomerEntity customerEntity = new CustomerEntity("박규태", "test@korea.com", "KOR");

        //when
        Long customer_id = customerService.createCustomer(customerEntity);
        CustomerEntity findCustomerEntity = customerRepository.findById(customer_id).get();

        //then
        assertEquals("박규태", findCustomerEntity.getName());
        assertNull(findCustomerEntity.getRegBranchEntity());
        assertNull(findCustomerEntity.getMngBranchEntity());
        assertNull(findCustomerEntity.getRegEmployeeEntity());
        assertNull(findCustomerEntity.getRegEmployeeEntity());
    }


}