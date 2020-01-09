package com.ken207.openbank.controller;

import com.ken207.openbank.common.ErrorsResource;
import com.ken207.openbank.common.ResponseResource;
import com.ken207.openbank.consts.ConstEmployee;
import com.ken207.openbank.domain.Customer;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.dto.CustomerDto;
import com.ken207.openbank.dto.request.CustomerRequest;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.dto.response.CustomerResponse;
import com.ken207.openbank.mapper.CustomerMapper;
import com.ken207.openbank.repository.CustomerRepository;
import com.ken207.openbank.repository.EmployeeRepository;
import com.ken207.openbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/customer", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class CustomerController {

    @Autowired private CustomerService customerService;
    @Autowired private EmployeeRepository employeeRepository;
    @Autowired private CustomerRepository customerRepository;
    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    private final ControllerLinkBuilder controllerLinkBuilder = linkTo(CustomerController.class);

    private Employee employee;

    public void start() {
        employee = employeeRepository.findByEmployeeCode(ConstEmployee.INTERNET);
    }

    @PostMapping
    public ResponseEntity createCustomer(@RequestBody @Valid CustomerRequest customerRequest, Errors errors) {

        //Request Data Validation
        HttpStatus httpStatus = RequestValidator.createCustomer(customerRequest, errors);
        if ( errors.hasErrors()) {
            return new ResponseEntity(new ErrorsResource(errors), httpStatus);
        }

        //Data Save
        Customer customer = new Customer(customerRequest.getName(), customerRequest.getEmail(), customerRequest.getNation());
        Long customerId = customerService.createCustomer(customer, employee.getId());

        //Set response data
        Customer newCustomer = customerRepository.findById(customerId).get();
        CustomerDto.Response customerResponse = customerMapper.entityToDto(newCustomer);

        //HATEOAS REST API
        Resource responseResource = new Resource(customerResponse,
                controllerLinkBuilder.slash(newCustomer.getId()).withSelfRel(),
                getLinkForUpdate(newCustomer.getId()),
                getLinkOfList(),
                new Link("/docs/index.html#resources-customers-create").withRel("profile")
        );

        //redirect
        ControllerLinkBuilder selfLinkBuilder = controllerLinkBuilder.slash(customerResponse.getId());
        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(responseResource);
    }


    @GetMapping
    public ResponseEntity queryCustomers(Pageable pageable, PagedResourcesAssembler<Customer> assembler) {
        Page<Customer> page = this.customerRepository.findAll(pageable);
        PagedResources<ResponseResource> pagedResources = assembler.toResource(page,
                e -> new ResponseResource(
                        CustomerResponse.transform(e),
                        controllerLinkBuilder.slash(e.getId()).withSelfRel()
                ));
        pagedResources.add(new Link("/docs/index.html#resources-customers-list").withRel("profile"));
        return ResponseEntity.ok(pagedResources);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity getCustomer(@PathVariable Long customerId) {
        Optional<Customer> customer = this.customerRepository.findById(customerId);

        if ( !customer.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        //Set response data
        CustomerDto.Response response = customerMapper.entityToDto(customer.get());

        //HATEOAS REST API
        Resource resource = new Resource(response,
                controllerLinkBuilder.slash(customerId).withSelfRel(),
                getLinkForUpdate(customerId),
                getLinkOfList(),
                getLinkOfProfile("#resources-branch-get")
        );

        return ResponseEntity.ok().body(resource);
    }

    private Link getLinkForUpdate(Long customerId) {
        return controllerLinkBuilder.slash(customerId).withRel("customer-update");
    }

    private Link getLinkOfList() {
        return controllerLinkBuilder.withRel(("customer-list"));
    }

    private Link getLinkOfProfile(String resourceUri) {
        return new Link("/docs/index.html"+resourceUri).withRel("profile");
    }
}
