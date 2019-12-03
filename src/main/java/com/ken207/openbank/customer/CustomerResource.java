package com.ken207.openbank.customer;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class CustomerResource extends Resource<CustomerCreateResponse> {

    public CustomerResource(CustomerCreateResponse customerCreateResponse, Link... links) {
        super(customerCreateResponse, links);
        add(linkTo(CustomerApiController.class).slash(customerCreateResponse.getId()).withSelfRel());
    }

}
