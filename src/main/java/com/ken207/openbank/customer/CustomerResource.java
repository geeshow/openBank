package com.ken207.openbank.customer;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class CustomerResource extends Resource<Customer> {

    public CustomerResource(Customer customer, Link... links) {
        super(customer, links);
        add(linkTo(CustomerIbkApiConstroller.class).slash(customer.getId()).withSelfRel());
    }

}
