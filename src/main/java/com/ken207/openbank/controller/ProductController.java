package com.ken207.openbank.controller;

import com.ken207.openbank.annotation.CurrentUser;
import com.ken207.openbank.common.ErrorsResource;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.domain.ProductEntity;
import com.ken207.openbank.dto.ProductDto;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.mapper.ProductMapper;
import com.ken207.openbank.repository.ProductRepository;
import com.ken207.openbank.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/product", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final ControllerLinkBuilder controllerLinkBuilder = linkTo(ProductController.class);

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid ProductDto.Create createProductDto, Errors errors,
                                        @CurrentUser MemberEntity currentMember) {


        //Request Data Validation
        HttpStatus httpStatus = RequestValidator.createProduct(createProductDto, errors, currentMember);
        if (errors.hasErrors()) {
            return new ResponseEntity(new ErrorsResource(errors), httpStatus);
        }

        //Create Entity and save to database
        Long productId = productService.createProduct(createProductDto);
        ProductEntity newProduct = productRepository.findById(productId).get();

        //Set response data
        ProductDto.Create response = productMapper.entityToDto(newProduct);

        //HATEOAS REST API
        Resource responseResource = new Resource(response,
                controllerLinkBuilder.slash(newProduct.getProductCode()).withSelfRel(),
                getLinkToCreate(newProduct.getProductCode()),
                getLinkToAdjust(newProduct.getProductCode()),
                getLinkToList(),
                getLinkOfProfile("#resources-products-create")
        );

        //redirect
        URI createdUri = controllerLinkBuilder.slash(newProduct.getProductCode()).toUri();
        return ResponseEntity.created(createdUri).body(responseResource);
    }

    private Link getLinkOfProfile(String resourceUri) {
        return new Link("/docs/index.html"+resourceUri).withRel("profile");
    }

    private Link getLinkToList() {
        return controllerLinkBuilder.withRel("product-list");

    }

    private Link getLinkToAdjust(String productCode) {
        return controllerLinkBuilder.slash(productCode).withRel("adjust");
    }

    private Link getLinkToCreate(String productCode) {
        return controllerLinkBuilder.withRel("create");
    }
}
