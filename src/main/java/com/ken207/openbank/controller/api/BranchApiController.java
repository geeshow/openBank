package com.ken207.openbank.controller.api;

import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.dto.request.BranchCreateRequest;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.dto.response.BranchCreateResponse;
import com.ken207.openbank.common.ResponseResource;
import com.ken207.openbank.repository.BranchRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/branch", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class BranchApiController {

    @Autowired
    BranchRepository branchRepository;

    @PostMapping
    public ResponseEntity createBranch(@RequestBody @Valid BranchCreateRequest branchCreateRequest, Errors errors) {

        //Request Data Validation
        if (errors.hasErrors()) {
            return RequestValidator.badRequest(errors);
        }

        //Create Entity and save to database
        Branch branch = new Branch(
                branchCreateRequest.getName(),
                branchCreateRequest.getBusinessNumber(),
                branchCreateRequest.getTaxOfficeCode(),
                branchCreateRequest.getTelNumber()
        );
        Branch newBranch = branchRepository.save(branch);

        //Set response data
        BranchCreateResponse branchCreateResponse = BranchCreateResponse.builder()
                .id(newBranch.getId())
                .name(newBranch.getName())
                .telNumber(newBranch.getTelNumber())
                .regDateTime(newBranch.getRegDateTime())
                .build();

        //HATEOAS REST API
        ResponseResource responseResource = new ResponseResource(branchCreateResponse);
        return responseResource.getResponse(this.getClass());
    }

    @GetMapping
    public ResponseEntity queryBranches(Pageable pageable, PagedResourcesAssembler<Branch> assembler) {
        Page<Branch> page = this.branchRepository.findAll(pageable);
        var pagedResources = assembler.toResource(page);
        return ResponseEntity.ok(pagedResources);
    }
}