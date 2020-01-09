package com.ken207.openbank.controller;

import com.ken207.openbank.annotation.CurrentUser;
import com.ken207.openbank.common.ErrorsResource;
import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.Member;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.dto.request.BranchRequest;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.dto.response.BranchResponse;
import com.ken207.openbank.common.ResponseResource;
import com.ken207.openbank.mapper.BranchMapper;
import com.ken207.openbank.repository.BranchRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/branch", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class BranchController {

    BranchRepository branchRepository;

    BranchMapper branchMapper;

    private final ControllerLinkBuilder controllerLinkBuilder = linkTo(BranchController.class);

    public BranchController(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
        this.branchMapper = BranchMapper.INSTANCE;
    }

    @PostMapping
    public ResponseEntity createBranch(@RequestBody @Valid BranchRequest branchRequest, Errors errors,
                                        @CurrentUser Member member) {

        //Request Data Validation
        HttpStatus httpStatus = RequestValidator.createBranch(branchRequest, errors, member);
        if (errors.hasErrors()) {
            return new ResponseEntity(new ErrorsResource(errors), httpStatus);
        }

        //Create Entity and save to database
        Branch branch = new Branch(
                branchRequest.getName(),
                branchRequest.getBusinessNumber(),
                branchRequest.getTaxOfficeCode(),
                branchRequest.getTelNumber(),
                BranchType.지점
        );
        Branch newBranch = branchRepository.save(branch);

        //Set response data
        BranchResponse branchResponse = branchMapper.entityToResponse(newBranch);

        //HATEOAS REST API
        ResponseResource responseResource = new ResponseResource(branchResponse,
                controllerLinkBuilder.slash(branchResponse.getId()).withSelfRel(),
                controllerLinkBuilder.slash(branchResponse.getId()).withRel("update-branch"),
                controllerLinkBuilder.withRel(("query-branches")),
                new Link("/docs/index.html#resources-branches-create").withRel("profile")
        );

        //redirect
        ControllerLinkBuilder selfLinkBuilder = controllerLinkBuilder.slash(branchResponse.getId());
        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(responseResource);
    }

    @GetMapping
    public ResponseEntity queryBranches(Pageable pageable, PagedResourcesAssembler<Branch> assembler,
                                        @CurrentUser Member member) {
        Page<Branch> page = this.branchRepository.findAll(pageable);
        PagedResources<ResponseResource> pagedResources = assembler.toResource(page,
                e -> new ResponseResource(
                        branchMapper.entityToResponse(e),
                        controllerLinkBuilder.slash(e.getId()).withSelfRel()
                ));

        pagedResources.add(new Link("/docs/index.html#resources-branches-list").withRel("profile"));
        if ( member != null ) {
            pagedResources.add(controllerLinkBuilder.withRel("create-branch"));
        }
        return ResponseEntity.ok(pagedResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBranch(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<Branch> byId = this.branchRepository.findById(id);
        if (!byId.isPresent()) {
            return notFoundResponse();
        }

        Branch branch = byId.get();

        //Set response data
        BranchResponse branchResponse = branchMapper.entityToResponse(branch);

        //HATEOAS REST API
        ResponseResource responseResource = new ResponseResource(branchResponse,
                controllerLinkBuilder.slash(branchResponse.getId()).withSelfRel(),
                controllerLinkBuilder.slash(branchResponse.getId()).withRel("update-branch"),
                controllerLinkBuilder.withRel(("query-branches")),
                new Link("/docs/index.html#resources-branch-get").withRel("profile")
        );

        return ResponseEntity.ok().body(responseResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateBranch(@PathVariable Long id,
                                       @RequestBody @Valid BranchRequest branchRequest,
                                       Errors errors,
                                       @CurrentUser Member currentMember) {

        Optional<Branch> optionalBranch = this.branchRepository.findById(id);
        if ( !optionalBranch.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        //Request Data Validation
        HttpStatus httpStatus = RequestValidator.createBranch(branchRequest, errors, currentMember);
        if ( errors.hasErrors()) {
            return new ResponseEntity(new ErrorsResource(errors), httpStatus);
        }

        //Data mapping from Dto to Entity
        Branch existingBranch = optionalBranch.get();
        branchMapper.updateBranchFromDto(branchRequest, existingBranch);

        //Data mapping for response data
        Branch savedBranch = this.branchRepository.save(existingBranch);
        BranchResponse branchResponse = branchMapper.entityToResponse(savedBranch);

        //Hateoas
        ResponseResource responseResource = new ResponseResource(branchResponse);
        responseResource.add(controllerLinkBuilder.slash(branchResponse.getId()).withSelfRel());
        responseResource.add(new Link("/docs/index.html#resources-branch-update").withRel("profile"));

        return ResponseEntity.ok(responseResource);
    }

    private ResponseEntity<Object> notFoundResponse() {
        return ResponseEntity.notFound().build();
    }
}