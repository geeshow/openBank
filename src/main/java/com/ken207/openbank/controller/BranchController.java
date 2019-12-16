package com.ken207.openbank.controller;

import com.ken207.openbank.annotation.CurrentUser;
import com.ken207.openbank.domain.BranchEntity;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.domain.enums.BranchType;
import com.ken207.openbank.dto.request.BranchRequest;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.dto.response.BranchResponse;
import com.ken207.openbank.common.ResponseResource;
import com.ken207.openbank.mapper.BranchMapper;
import com.ken207.openbank.repository.BranchRepository;
import com.ken207.openbank.user.MemberAdapter;
import com.ken207.openbank.user.MemberRole;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    public ResponseEntity createBranch(@RequestBody @Valid BranchRequest branchRequest, Errors errors) {

        //Request Data Validation
        if (errors.hasErrors()) {
            return RequestValidator.badRequest(errors);
        }

        //Create Entity and save to database
        BranchEntity branchEntity = new BranchEntity(
                branchRequest.getName(),
                branchRequest.getBusinessNumber(),
                branchRequest.getTaxOfficeCode(),
                branchRequest.getTelNumber(),
                BranchType.지점
        );
        BranchEntity newBranchEntity = branchRepository.save(branchEntity);

        //Set response data
        BranchResponse branchResponse = branchMapper.entityToResponse(newBranchEntity);

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
    public ResponseEntity queryBranches(Pageable pageable, PagedResourcesAssembler<BranchEntity> assembler,
                                        @CurrentUser MemberEntity memberEntity) {
        Page<BranchEntity> page = this.branchRepository.findAll(pageable);
        PagedResources<ResponseResource> pagedResources = assembler.toResource(page,
                e -> new ResponseResource(
                        branchMapper.entityToResponse(e),
                        controllerLinkBuilder.slash(e.getId()).withSelfRel()
                ));

        pagedResources.add(new Link("/docs/index.html#resources-branches-list").withRel("profile"));
        if ( memberEntity != null ) {
            pagedResources.add(controllerLinkBuilder.withRel("create-branch"));
        }
        return ResponseEntity.ok(pagedResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBranch(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Optional<BranchEntity> byId = this.branchRepository.findById(id);
        if (!byId.isPresent()) {
            return notFoundResponse();
        }

        BranchEntity branchEntity = byId.get();

        //Set response data
        BranchResponse branchResponse = branchMapper.entityToResponse(branchEntity);

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
                                       @CurrentUser MemberEntity currentMember) {

        if ( !currentMember.getRoles().contains(MemberRole.ADMIN) ) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        Optional<BranchEntity> optionalBranch = this.branchRepository.findById(id);
        if ( !optionalBranch.isPresent() ) {
            return ResponseEntity.notFound().build();
        }

        //Request Data Validation
        ResponseEntity validate = RequestValidator.validate(branchRequest, errors);
        if ( errors.hasErrors()) {
            return validate;
        }

        //Data mapping from Dto to Entity
        BranchEntity existingBranchEntity = optionalBranch.get();
        branchMapper.updateBranchFromDto(branchRequest, existingBranchEntity);

        //Data mapping for response data
        BranchEntity savedBranchEntity = this.branchRepository.save(existingBranchEntity);
        BranchResponse branchResponse = branchMapper.entityToResponse(savedBranchEntity);

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