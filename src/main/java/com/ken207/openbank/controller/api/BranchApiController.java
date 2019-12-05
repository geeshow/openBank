package com.ken207.openbank.controller.api;

import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.dto.request.BranchCreateRequest;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.dto.response.BranchResponse;
import com.ken207.openbank.common.ResponseResource;
import com.ken207.openbank.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.net.URI;
import java.util.Optional;

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
                branchCreateRequest.getTelNumber(),
                branchCreateRequest.getBranchType()
        );
        Branch newBranch = branchRepository.save(branch);

        //Set response data
        BranchResponse branchResponse = BranchResponse.builder()
                .id(newBranch.getId())
                .name(newBranch.getName())
                .telNumber(newBranch.getTelNumber())
                .businessNumber(newBranch.getBusinessNumber())
                .taxOfficeCode(newBranch.getTaxOfficeCode())
                .branchType(newBranch.getBranchType())
                .regDateTime(newBranch.getRegDateTime())
                .build();

        //HATEOAS REST API
        ResponseResource responseResource = new ResponseResource(branchResponse,
                linkTo(this.getClass()).slash(branchResponse.getId()).withRel("update-branch"),
                linkTo(this.getClass()).withRel(("query-branches")),
                new Link("/docs/index.html#resources-branches-create").withRel("profile")
        );

        //redirect
        ControllerLinkBuilder selfLinkBuilder = linkTo(this.getClass()).slash(branchResponse.getId());
        URI createdUri = selfLinkBuilder.toUri();
        return ResponseEntity.created(createdUri).body(responseResource);
    }

    @GetMapping
    public ResponseEntity queryBranches(Pageable pageable, PagedResourcesAssembler<Branch> assembler) {
        Page<Branch> page = this.branchRepository.findAll(pageable);
        PagedResources<ResponseResource> pagedResources = assembler.toResource(page,
                e -> new ResponseResource(
                        BranchResponse.transform(e)
                ));
        pagedResources.add(new Link("/docs/index.html#resources-branches-list").withRel("profile"));
        return ResponseEntity.ok(pagedResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBranch(@PathVariable Long id) {
        Optional<Branch> byId = this.branchRepository.findById(id);
        if (!byId.isPresent()) {
            return notFoundResponse();
        }

        Branch branch = byId.get();

        //Set response data
        BranchResponse branchResponse = BranchResponse.builder()
                .id(branch.getId())
                .name(branch.getName())
                .telNumber(branch.getTelNumber())
                .businessNumber(branch.getBusinessNumber())
                .taxOfficeCode(branch.getTaxOfficeCode())
                .branchType(branch.getBranchType())
                .regDateTime(branch.getRegDateTime())
                .build();

        //HATEOAS REST API
        ResponseResource responseResource = new ResponseResource(branchResponse,
                linkTo(this.getClass()).slash(branchResponse.getId()).withRel("update-branch"),
                linkTo(this.getClass()).withRel(("query-branches")),
                new Link("/docs/index.html#resources-branch-get").withRel("profile")
        );

        return ResponseEntity.ok().body(responseResource);
    }

    private ResponseEntity<Object> notFoundResponse() {
        return ResponseEntity.notFound().build();
    }
}