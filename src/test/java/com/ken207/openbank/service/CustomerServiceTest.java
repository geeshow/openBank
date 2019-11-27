package com.ken207.openbank.service;

import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.Customer;
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
        Customer customer = new Customer("박규태", "한국",
                new Employee("임사원", EmployeeType.인터넷뱅킹,
                        new Branch("99", "서울지점", BranchType.인터넷)));

        //when
        Long customer_id = customerService.createCustomer(customer);
        Customer findCustomer = customerRepository.findById(customer_id).get();

        //then
        assertEquals("박규태", findCustomer.getName());
        assertEquals("서울지점", findCustomer.getNewBranch().getName());
        assertEquals("99", findCustomer.getNewBranch().getId());
        assertEquals("서울지점", findCustomer.getMngBranch().getName());
        assertEquals("99", findCustomer.getMngBranch().getId());
        assertEquals("임사원", findCustomer.getRegEmployee().getName());
        assertEquals(EmployeeType.인터넷뱅킹, findCustomer.getRegEmployee().getEmployeeType());
    }


}