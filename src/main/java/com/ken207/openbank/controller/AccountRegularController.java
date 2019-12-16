package com.ken207.openbank.controller;

import com.ken207.openbank.annotation.CurrentUser;
import com.ken207.openbank.common.ErrorsResource;
import com.ken207.openbank.common.ResponseResource;
import com.ken207.openbank.domain.BranchEntity;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.domain.account.AccountEntity;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.dto.response.BranchResponse;
import com.ken207.openbank.mapper.AccountMapper;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.service.AccountService;
import com.ken207.openbank.user.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.DateUtils;

import javax.validation.Valid;
import java.net.URI;
import java.text.DateFormat;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/account/regular", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class AccountRegularController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final ControllerLinkBuilder controllerLinkBuilder = linkTo(AccountRegularController.class);

    @PostMapping
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Request accountRequest, Errors errors,
                                        @CurrentUser MemberEntity currentMember) {

        //Request Data Validation
        HttpStatus httpStatus = RequestValidator.createAccount(accountRequest, errors, currentMember);
        if (errors.hasErrors()) {
            return new ResponseEntity(new ErrorsResource(errors), httpStatus);
        }

        //Create Entity and save to database
        String accountNum = accountService.openRegularAccount(accountRequest);
        AccountEntity account = accountRepository.findByAccountNum(accountNum);

        //Set response data
        AccountDto.Response newAccount = AccountMapper.INSTANCE.accountForResponse(account);

        //HATEOAS REST API
        Resource responseResource = new Resource(newAccount,
                controllerLinkBuilder.slash(newAccount.getAccountNum()).withSelfRel(),
                controllerLinkBuilder.slash(newAccount.getAccountNum()).slash("deposit").withRel("deposit"),
                controllerLinkBuilder.slash(newAccount.getAccountNum()).slash("withdraw").withRel("withdraw"),
                controllerLinkBuilder.slash(newAccount.getAccountNum()).slash("close").withRel("close"),
                controllerLinkBuilder.withRel(("query-accounts")),
                new Link("/docs/index.html#resources-accounts-create").withRel("profile")
        );

        //redirect
        URI createdUri = controllerLinkBuilder.slash(newAccount.getAccountNum()).toUri();
        return ResponseEntity.created(createdUri).body(responseResource);
    }

    @GetMapping
    public ResponseEntity queryAccounts(Pageable pageable, PagedResourcesAssembler<AccountEntity> assembler,
                                        @CurrentUser MemberEntity memberEntity) {
        Page<AccountEntity> page = this.accountRepository.findAll(pageable);
        PagedResources<Resource> pagedResources = assembler.toResource(page,
                e -> new Resource(AccountMapper.INSTANCE.accountForResponse(e),
                        controllerLinkBuilder.slash(e.getAccountNum()).withSelfRel()
                ));

        pagedResources.add(new Link("/docs/index.html#resources-accounts-list").withRel("profile"));
        if ( memberEntity != null ) {
            pagedResources.add(controllerLinkBuilder.withRel("create-account"));
        }
        return ResponseEntity.ok(pagedResources);
    }
}
