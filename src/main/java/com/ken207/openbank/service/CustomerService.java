package com.ken207.openbank.service;

import com.ken207.openbank.domain.Customer;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.repository.CustomerRepository;
import com.ken207.openbank.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public Long createCustomer(Customer customer) {
        return customerRepository.save(customer).getId();
    }

    @Transactional
    public Long createCustomer(Customer customer, Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).get();
        customer.setRegEmployee(employee);
        return customerRepository.save(customer).getId();
    }

}
