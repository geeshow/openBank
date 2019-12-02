package com.ken207.openbank.customer;

import com.ken207.openbank.consts.ConstBranch;
import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.domain.enums.EmployeeType;
import com.ken207.openbank.repository.CustomerRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/ibk/customer", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class CustomerIbkApiConstroller {

    //@Autowired private CustomerService customerService;
    //@Autowired private BranchRepository branchRepository;
    //@Autowired private EmployeeRepository employeeRepository;

    @Autowired private CustomerRepository customerRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private CustomerValidator customerValidator;

    private Branch internetBranch = new Branch(ConstBranch.INTERNET_ID20,"인터넷뱅킹", BranchType.인터넷);
    private Employee internetEmployee = new Employee("인터넷사용자", EmployeeType.인터넷뱅킹, internetBranch);

    public void setInternetBankEmployee(Employee employee) {
        this.internetBranch = employee.getBelongBranch();
        this.internetEmployee = employee;
    }

    @PostMapping
    public ResponseEntity createIbkCustomer(@RequestBody @Valid CustomerCreateRequest customerCreateRequest, Errors errors) {

        if ( errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        customerValidator.validate(customerCreateRequest, errors);

        if ( errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        //DB 저장
        Customer customer = new Customer(customerCreateRequest.getName(), customerCreateRequest.getEmail(), customerCreateRequest.getNation(), internetEmployee);
        Customer newCustomer = this.customerRepository.save(customer);

        //응답 설정
        CustomerCreateResponse customerCreateResponse = CustomerCreateResponse.builder()
                .id(newCustomer.getId())
                .name(newCustomer.getName())
                .email(newCustomer.getEmail())
                .nation(newCustomer.getNation())
                .regBranchName(newCustomer.getRegBranch().getName())
                .mngBranchName(newCustomer.getMngBranch().getName())
                .regEmployeeName(newCustomer.getRegEmployee().getName())
                .regDateTime(newCustomer.getRegDateTime())
                .build();

        //HATEOAS REST API
        ControllerLinkBuilder selfLinkBuilder = linkTo(CustomerIbkApiConstroller.class).slash(customerCreateResponse.getId());

        CustomerResource customerResource = new CustomerResource(customerCreateResponse);
        customerResource.add(linkTo(CustomerIbkApiConstroller.class).withRel(("query-customers")));
        customerResource.add(selfLinkBuilder.withRel("update-customer"));
        customerResource.add(new Link("/docs/index.html#resources-customers-create").withRel("profile"));

        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(customerResource);
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
