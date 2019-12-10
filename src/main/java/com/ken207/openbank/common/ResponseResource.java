package com.ken207.openbank.common;

import com.ken207.openbank.dto.response.CreateResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ResponseResource extends Resource {

    public ResponseResource(CreateResponse createResponse, Link... links) {

        super(createResponse, links);
        add(linkTo(CreateResponse.class).slash(createResponse.getId()).withSelfRel());

    }
}





