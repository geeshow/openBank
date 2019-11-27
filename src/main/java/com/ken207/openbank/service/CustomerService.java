package com.ken207.openbank.service;

import com.ken207.openbank.domain.Customer;
import com.ken207.openbank.repository.CustomerRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Long createCustomer(@RequestBody CreateCustomerDto c) {

    }


}
