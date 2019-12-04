package com.ken207.openbank.index;

import com.ken207.openbank.controller.api.CustomerApiController;
import lombok.var;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
public class IndexController {

    @GetMapping("/")
    public ResourceSupport index() {
        var index = new ResourceSupport();
        index.add(linkTo(CustomerApiController.class).withRel("customers"));
        return index;
    }
}
