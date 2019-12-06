package com.ken207.openbank.controller.api;

import com.ken207.openbank.consts.ConstEmployee;
import com.ken207.openbank.domain.CustomerEntity;
import com.ken207.openbank.domain.EmployeeEntity;
import com.ken207.openbank.dto.request.CustomerRequest;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.dto.response.CustomerResponse;
import com.ken207.openbank.common.ResponseResource;
import com.ken207.openbank.repository.CustomerRepository;
import com.ken207.openbank.repository.EmployeeRepository;
import com.ken207.openbank.service.CustomerService;
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

    private EmployeeEntity employeeEntity;

    public void start() {
        employeeEntity = employeeRepository.findByEmployeeCode(ConstEmployee.INTERNET);
    }

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody @Valid CustomerRequest customerRequest, Errors errors) {

        //Request Data Validation
        ResponseEntity validate = RequestValidator.validate(customerRequest, errors);
        if ( errors.hasErrors()) {
            return validate;
        }

        //Data Save
        CustomerEntity customerEntity = new CustomerEntity(customerRequest.getName(), customerRequest.getEmail(), customerRequest.getNation());
        Long customerId = customerService.createCustomer(customerEntity, employeeEntity.getId());

        //Set response data
        CustomerEntity newCustomerEntity = customerRepository.findById(customerId).get();
        CustomerResponse customerResponse = CustomerResponse.transform(newCustomerEntity);

        //HATEOAS REST API
        ResponseResource responseResource = new ResponseResource(customerResponse,
                linkTo(this.getClass()).slash(newCustomerEntity.getId()).withRel("update-customer"),
                linkTo(this.getClass()).withRel(("query-customers")),
                new Link("/docs/index.html#resources-customers-create").withRel("profile")
        );

        //redirect
        ControllerLinkBuilder selfLinkBuilder = linkTo(this.getClass()).slash(customerResponse.getId());
        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(responseResource);
    }


    @GetMapping
    public ResponseEntity queryCustomers(Pageable pageable, PagedResourcesAssembler<CustomerEntity> assembler) {
        Page<CustomerEntity> page = this.customerRepository.findAll(pageable);
        PagedResources<ResponseResource> pagedResources = assembler.toResource(page,
                e -> new ResponseResource(
                        CustomerResponse.transform(e)
                ));
        pagedResources.add(new Link("/docs/index.html#resources-customers-list").withRel("profile"));
        return ResponseEntity.ok(pagedResources);
    }

}
