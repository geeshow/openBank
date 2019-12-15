package com.ken207.openbank.common;

import com.ken207.openbank.dto.response.BaseResponse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ResponseResource extends Resource {

    public ResponseResource(BaseResponse baseResponse, Link... links) {

        super(baseResponse, links);


    }
}





