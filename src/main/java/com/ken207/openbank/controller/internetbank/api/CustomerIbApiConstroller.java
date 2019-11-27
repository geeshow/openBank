package com.ken207.openbank.controller.internetbank.api;

import com.ken207.openbank.annotation.OpenBankService;
import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.Customer;
import com.ken207.openbank.domain.Employee;
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
@OpenBankService
public class CustomerIbApiConstroller {

    @Autowired private CustomerService customerService;
    @Autowired private BranchRepository branchRepository;
    @Autowired private EmployeeRepository employeeRepository;

    private Branch internetBranch;
    private Employee internetEmployee;

    public void setInternetBankEmployee(Employee employee) {
        this.internetBranch = employee.getBelongBranch();
        this.internetEmployee = employee;
    }

    @PostMapping("/api/ibk/customer")
    public CreateCustomerRes createIbkCustomer(@RequestBody @Valid CreateCustomerReq createCustomerReq) {

        Customer customer = new Customer(createCustomerReq.getName(), createCustomerReq.getNation(), internetEmployee);
        Long id = customerService.createCustomer(customer);
        return new CreateCustomerRes(id);

    }

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
