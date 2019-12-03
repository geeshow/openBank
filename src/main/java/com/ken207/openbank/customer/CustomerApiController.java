package com.ken207.openbank.customer;

import com.ken207.openbank.common.ErrorsResource;
import com.ken207.openbank.consts.ConstEmployee;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.repository.CustomerRepository;
import com.ken207.openbank.repository.EmployeeRepository;
import com.ken207.openbank.service.CustomerService;
import lombok.Data;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/ibk/customer", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class CustomerApiController {

    @Autowired private CustomerService customerService;
    //@Autowired private BranchRepository branchRepository;
    @Autowired private EmployeeRepository employeeRepository;

    @Autowired private CustomerRepository customerRepository;
    @Autowired private ModelMapper modelMapper;
    @Autowired private CustomerValidator customerValidator;

    private Employee employee;

    public void start() {
        employee = employeeRepository.findByEmployeeCode(ConstEmployee.INTERNET);
    }

    @PostMapping
    public ResponseEntity createIbkCustomer(@RequestBody @Valid CustomerCreateRequest customerCreateRequest, Errors errors) {

        if ( errors.hasErrors()) {
            return badRequest(errors);
        }

        customerValidator.validate(customerCreateRequest, errors);
        if ( errors.hasErrors()) {
            return badRequest(errors);
        }

        //DB 저장
        Customer customer = new Customer(customerCreateRequest.getName(), customerCreateRequest.getEmail(), customerCreateRequest.getNation());
        Long customerId = customerService.createCustomer(customer, employee.getId());

        //응답 설정
        Customer newCustomer = customerRepository.findById(customerId).get();
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
        ControllerLinkBuilder selfLinkBuilder = linkTo(CustomerApiController.class).slash(customerCreateResponse.getId());

        CustomerResource customerResource = new CustomerResource(customerCreateResponse);
        customerResource.add(linkTo(CustomerApiController.class).withRel(("query-customers")));
        customerResource.add(selfLinkBuilder.withRel("update-customer"));
        customerResource.add(new Link("/docs/index.html#resources-customers-create").withRel("profile"));

        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(customerResource);
    }

    private ResponseEntity badRequest(Errors errors) {
        return ResponseEntity.badRequest().body(new ErrorsResource(errors) );
    }

    @GetMapping
    public ResponseEntity queryCustomers(Pageable pageable, PagedResourcesAssembler<Customer> assembler) {
        Page<Customer> page = this.customerRepository.findAll(pageable);
        var pagedResources = assembler.toResource(page);
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
