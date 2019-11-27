package com.ken207.openbank.controller.api;

import com.ken207.openbank.domain.Customer;
import com.ken207.openbank.repository.BranchRepository;
import com.ken207.openbank.repository.EmployeeRepository;
import com.ken207.openbank.service.CustomerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerApiConstroller {

    @Autowired private CustomerService customerService;
    @Autowired private BranchRepository branchRepository;
    @Autowired private EmployeeRepository employeeRepository;

    @PostMapping(value = "/api/ibk/customer", consumes= "text/csv" )
    public CreateCustomerRes createIbkCustomer(@RequestBody @Valid CreateCustomerReq createCustomerReq) {
//public Customer(String name, String nation, Branch newBranch, Employee regEmployee) {


        Customer customer = new Customer(createCustomerReq.getName(), createCustomerReq.get)
        Long id = customerService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateCustomerReq {
        private String name;
        private String nation;
    }

    @Data
    static class CreateCustomerRes {
        private Long customer_id;
    }
}
