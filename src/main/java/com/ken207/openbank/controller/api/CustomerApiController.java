package com.ken207.openbank.controller.api;

import com.ken207.openbank.consts.ConstEmployee;
import com.ken207.openbank.customer.*;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.dto.request.CustomerCreateRequest;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.dto.response.CustomerResponse;
import com.ken207.openbank.common.ResponseResource;
import com.ken207.openbank.repository.CustomerRepository;
import com.ken207.openbank.repository.EmployeeRepository;
import com.ken207.openbank.service.CustomerService;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/customer", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class CustomerApiController {

    @Autowired private CustomerService customerService;
    //@Autowired private BranchRepository branchRepository;
    @Autowired private EmployeeRepository employeeRepository;

    @Autowired private CustomerRepository customerRepository;
    @Autowired private ModelMapper modelMapper;

    private Employee employee;

    public void start() {
        employee = employeeRepository.findByEmployeeCode(ConstEmployee.INTERNET);
    }

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody @Valid CustomerCreateRequest customerCreateRequest, Errors errors) {

        //Request Data Validation
        ResponseEntity validate = RequestValidator.validate(customerCreateRequest, errors);
        if ( errors.hasErrors()) {
            return validate;
        }

        //Data Save
        Customer customer = new Customer(customerCreateRequest.getName(), customerCreateRequest.getEmail(), customerCreateRequest.getNation());
        Long customerId = customerService.createCustomer(customer, employee.getId());

        //Set response data
        Customer newCustomer = customerRepository.findById(customerId).get();
        CustomerResponse customerResponse = CustomerResponse.transform(newCustomer);

        //HATEOAS REST API
        ResponseResource responseResource = new ResponseResource(customerResponse,
                linkTo(this.getClass()).slash(newCustomer.getId()).withRel("update-customer"),
                linkTo(this.getClass()).withRel(("query-customers")),
                new Link("/docs/index.html#resources-customers-create").withRel("profile")
        );

        //redirect
        ControllerLinkBuilder selfLinkBuilder = linkTo(this.getClass()).slash(customerResponse.getId());
        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(responseResource);
    }


    @GetMapping
    public ResponseEntity queryCustomers(Pageable pageable, PagedResourcesAssembler<Customer> assembler) {
        Page<Customer> page = this.customerRepository.findAll(pageable);
        PagedResources<ResponseResource> pagedResources = assembler.toResource(page,
                e -> new ResponseResource(
                        CustomerResponse.transform(e)
                ));
        pagedResources.add(new Link("/docs/index.html#resources-customers-list").withRel("profile"));
        return ResponseEntity.ok(pagedResources);
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
