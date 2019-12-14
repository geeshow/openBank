package com.ken207.openbank.controller;

import com.ken207.openbank.domain.account.AccountEntity;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/account/regular", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class AccountRegularController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final ControllerLinkBuilder controllerLinkBuilder = linkTo(AccountRegularController.class);

    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.Request accountRequest, Errors errors) {

        //Request Data Validation
        if (errors.hasErrors()) {
            return RequestValidator.badRequest(errors);
        }

        //Create Entity and save to database
        String accountNum = accountService.openRegularAccount(accountRequest);
        AccountEntity account = accountRepository.findByAccountNum(accountNum);

        //Set response data
        AccountDto.Response newAccount = AccountDto.Response.builder()
                .regDate(account.getRegDate())
                .taxationCode(account.getTaxationCode())
                .accoBlnc(account.getAccoBlnc())
                .accountStatusCode(account.getAccountStatusCode())
                .accoutNum(account.getAccountNum())
                .lastIntsDt(account.getLastIntsDt())
                .subjectCode(account.getSubjectCode())
                .closeDate(account.getCloseDate())
                .build();

        //HATEOAS REST API
        Resource responseResource = new Resource(newAccount,
                controllerLinkBuilder.slash(newAccount.getAccoutNum()).withSelfRel(),

                controllerLinkBuilder.slash(newAccount.getAccoutNum()).withRel("update-account"),
                controllerLinkBuilder.withRel(("query-accounts")),
                new Link("/docs/index.html#resources-accounts-create").withRel("profile")
        );

        //redirect
        URI createdUri = controllerLinkBuilder.slash(newAccount.getAccoutNum()).toUri();
        return ResponseEntity.created(createdUri).body(responseResource);
    }
}
