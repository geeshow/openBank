package com.ken207.openbank.controller;

import com.ken207.openbank.common.ResponseResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class IndexController {

    @GetMapping("/api")
    public ResponseEntity index() {
        //HATEOAS REST API
        ResourceSupport index = new ResourceSupport();
        index.add(linkTo(BranchController.class).withRel("branches"));
        index.add(linkTo(CustomerController.class).withRel("customers"));
        index.add(new Link("/docs/index.html#resources-index-access").withRel("profile"));

        return ResponseEntity.ok().body(index);
    }
}
