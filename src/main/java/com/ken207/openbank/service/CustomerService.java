package com.ken207.openbank.service;

import com.ken207.openbank.domain.CustomerEntity;
import com.ken207.openbank.domain.EmployeeEntity;
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
    public Long createCustomer(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity).getId();
    }

    @Transactional
    public Long createCustomer(CustomerEntity customerEntity, Long employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        customerEntity.setRegEmployeeEntity(employeeEntity);
        return customerRepository.save(customerEntity).getId();
    }

}
