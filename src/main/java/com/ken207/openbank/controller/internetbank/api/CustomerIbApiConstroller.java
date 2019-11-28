package com.ken207.openbank.controller.internetbank.api;

import com.ken207.openbank.annotation.OpenBankService;
import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.Customer;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.domain.Product;
import com.ken207.openbank.repository.BranchRepository;
import com.ken207.openbank.repository.CustomerRepository;
import com.ken207.openbank.repository.EmployeeRepository;
import com.ken207.openbank.service.CustomerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.xml.ws.Response;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/ibk/customer", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class CustomerIbApiConstroller {

    //@Autowired private CustomerService customerService;
    //@Autowired private BranchRepository branchRepository;
    //@Autowired private EmployeeRepository employeeRepository;

    private final CustomerRepository customerRepository;

    public CustomerIbApiConstroller(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private Branch internetBranch;
    private Employee internetEmployee;

    public void setInternetBankEmployee(Employee employee) {
        this.internetBranch = employee.getBelongBranch();
        this.internetEmployee = employee;
    }

    @PostMapping
    public ResponseEntity createIbkCustomer(@RequestBody Customer customer) {
        Customer newCustomer = this.customerRepository.save(customer);
        URI createdUri = linkTo(CustomerIbApiConstroller.class).slash(newCustomer.getId()).toUri();
        return ResponseEntity.created(createdUri).body(customer);

    }

//    @OpenBankService
//    @PostMapping("/api/ibk/customer2")
//    public CreateCustomerRes createIbkCustomer2(@RequestBody @Valid CreateCustomerReq createCustomerReq) {
//
//        Customer customer = new Customer(createCustomerReq.getName(), createCustomerReq.getNation(), internetEmployee);
//        Long id = customerService.createCustomer(customer);
//        return new CreateCustomerRes(id);
//
//    }

    @Data
    static class CreateCustomerReq {
        private String name;
        private String nation;
    }

    @Data
    static class CreateCustomerRes {
        private Long customer_id;

        public CreateCustomerRes(Long customer_id) {
            this.customer_id = customer_id;
        }
    }
}
