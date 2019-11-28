package com.ken207.openbank.service;

import com.ken207.openbank.customer.Customer;
import com.ken207.openbank.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Long createCustomer(Customer customer) {
        Customer result = customerRepository.save(customer);
        return result.getId();
    }

}
