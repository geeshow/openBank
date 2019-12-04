package com.ken207.openbank.common;

import com.ken207.openbank.dto.response.CreateResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ResponseListResource extends Resource {

    public ResponseListResource(CreateResponse createResponse, Link... links) {

        super(createResponse, links);

        ControllerLinkBuilder linkBuilder = linkTo(CreateResponse.class);
        add(linkBuilder.slash(createResponse.getId()).withSelfRel());
    }

    public ResponseEntity getResponse(Class<?> controller) {

        CreateResponse content = (CreateResponse) this.getContent();
        ControllerLinkBuilder selfLinkBuilder = linkTo(controller).slash(content.getId());
        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(this);

    }

}





