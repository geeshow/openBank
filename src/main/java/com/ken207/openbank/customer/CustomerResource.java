package com.ken207.openbank.customer;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class CustomerResource extends Resource<CustomerCreateResponse> {

    public CustomerResource(CustomerCreateResponse customerCreateResponse, Link... links) {
        super(customerCreateResponse, links);
        add(linkTo(CustomerIbkApiConstroller.class).slash(customerCreateResponse.getId()).withSelfRel());
    }

}
