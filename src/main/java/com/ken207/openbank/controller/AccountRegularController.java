package com.ken207.openbank.controller;

import com.ken207.openbank.annotation.CurrentUser;
import com.ken207.openbank.common.ErrorsResource;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.TradeEntity;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.dto.request.RequestValidator;
import com.ken207.openbank.mapper.AccountMapper;
import com.ken207.openbank.mapper.TradeMapper;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.repository.TradeRepository;
import com.ken207.openbank.service.AccountService;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/account/regular", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class AccountRegularController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final TradeRepository tradeRepository;
    private final ControllerLinkBuilder controllerLinkBuilder = linkTo(AccountRegularController.class);
    private final AccountMapper accountMapper = AccountMapper.INSTANCE;
    private final TradeMapper tradeMapper = TradeMapper.INSTANCE;

    @PostMapping
    public ResponseEntity createAccount(@RequestBody @Valid AccountDto.RequestOpen accountRequestOpen, Errors errors,
                                        @CurrentUser MemberEntity currentMember) {

        //Request Data Validation
        HttpStatus httpStatus = RequestValidator.createAccount(accountRequestOpen, errors, currentMember);
        if (errors.hasErrors()) {
            return new ResponseEntity(new ErrorsResource(errors), httpStatus);
        }

        //Create Entity and save to database
        String accountNum = accountService.openRegularAccount(accountRequestOpen);
        AccountEntity account = accountRepository.findByAccountNum(accountNum);

        //Set response data
        AccountDto.Response newAccount = accountMapper.accountForResponse(account);

        //HATEOAS REST API
        Resource responseResource = new Resource(newAccount,
                controllerLinkBuilder.slash(newAccount.getAccountNum()).withSelfRel(),
                getLinkOfDeposit(accountNum),
                getLinkOfWithdraw(accountNum),
                getLinkOfClose(accountNum),
                getLinkOfList(),
                getLinkOfProfile("#resources-accounts-create")
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
                e -> new Resource(accountMapper.accountForResponse(e),
                        controllerLinkBuilder.slash(e.getAccountNum()).withSelfRel()
                ));

        pagedResources.add(getLinkOfProfile("#resources-accounts-list"));
        if ( memberEntity != null ) {
            pagedResources.add(controllerLinkBuilder.withRel("create-account"));
        }
        return ResponseEntity.ok(pagedResources);
    }

    @GetMapping("/{accountNum}")
    public ResponseEntity getAccount(@PathVariable String accountNum) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        AccountEntity account = this.accountRepository.findByAccountNum(accountNum);

        //Request Data Validation
        if ( account == null ) {
            return ResponseEntity.notFound().build();
        }

        //Set response data
        AccountDto.Response response = accountMapper.accountForResponse(account);

        //HATEOAS REST API
        Resource resource = new Resource(response,
                controllerLinkBuilder.slash(response.getAccountNum()).withSelfRel(),
                getLinkOfDeposit(accountNum),
                getLinkOfWithdraw(accountNum),
                getLinkOfClose(accountNum),
                getLinkOfList(),
                getLinkOfProfile("#resources-accounts-get")
        );

        return ResponseEntity.ok().body(resource);
    }

    @PutMapping("/{accountNum}/deposit")
    public ResponseEntity accountDeposit(@PathVariable String accountNum,
                                  @RequestBody @Valid TradeDto.RequestDeposit requestDeposit,
                                  Errors errors,
                                  @CurrentUser MemberEntity currentMember) {

        //update for deposit
        TradeEntity resultTrade = this.accountService.deposit(accountNum, requestDeposit);

        //set response data
        TradeDto.Response response = tradeMapper.entityToResponse(resultTrade);

        //HATEOAS REST API
        Resource resource = new Resource(response,
                controllerLinkBuilder.slash(accountNum).slash("deposit").withSelfRel(),
                getLinkOfDeposit(accountNum),
                getLinkOfWithdraw(accountNum),
                getLinkOfClose(accountNum),
                getLinkOfList(),
                getLinkOfProfile("#resources-accounts-deposit")
        );

        return ResponseEntity.ok().body(resource);
    }


    @PutMapping("/{accountNum}/withdraw")
    public ResponseEntity accountWithdraw(@PathVariable String accountNum,
                                         @RequestBody @Valid TradeDto.RequestDeposit requestDeposit,
                                         Errors errors,
                                         @CurrentUser MemberEntity currentMember) {

        //update for deposit
        TradeEntity resultTrade = this.accountService.withdraw(accountNum, requestDeposit);

        //set response data
        TradeDto.Response response = tradeMapper.entityToResponse(resultTrade);

        //HATEOAS REST API
        Resource resource = new Resource(response,
                controllerLinkBuilder.slash(accountNum).slash("withdraw").withSelfRel(),
                getLinkOfDeposit(accountNum),
                getLinkOfWithdraw(accountNum),
                getLinkOfClose(accountNum),
                getLinkOfList(),
                getLinkOfProfile("#resources-accounts-withdraw")
        );

        return ResponseEntity.ok().body(resource);
    }


    @GetMapping("/{accountNum}/trade")
    public ResponseEntity getTradeList(@PathVariable String accountNum, Pageable pageable, PagedResourcesAssembler<TradeEntity> assembler,
                                       @CurrentUser MemberEntity memberEntity) {

        AccountEntity account = this.accountRepository.findByAccountNum(accountNum);

        //Request Data Validation
        if ( account == null ) {
            return ResponseEntity.notFound().build();
        }

        Page<TradeEntity> page = this.tradeRepository.findByAccount(account, pageable);
        PagedResources<Resource> pagedResources = assembler.toResource(page,
                e -> new Resource(
                        tradeMapper.entityToResponse(e)
                ));

        pagedResources.add(new Link("/docs/index.html#resources-trade-list").withRel("profile"));
        //pagedResources.add(linkTo(methodOn(AccountRegularController.class).getTradeList(accountNum, null,null,null)).withSelfRel());

        if ( memberEntity != null ) {
            pagedResources.add(getLinkOfDeposit(accountNum));
            pagedResources.add(getLinkOfWithdraw(accountNum));
            pagedResources.add(getLinkOfClose(accountNum));
            pagedResources.add(getLinkOfList());
        }

        return ResponseEntity.ok(pagedResources);
    }

    private Link getLinkOfDeposit(String accountNum) {
        return controllerLinkBuilder.slash(accountNum).slash("deposit").withRel("deposit");
    }

    private Link getLinkOfWithdraw(String accountNum) {
        return controllerLinkBuilder.slash(accountNum).slash("withdraw").withRel("withdraw");
    }

    private Link getLinkOfClose(String accountNum) {
        return controllerLinkBuilder.slash(accountNum).slash("close").withRel("close");
    }

    private Link getLinkOfList() {
        return controllerLinkBuilder.withRel(("query-accounts"));
    }

    private Link getLinkOfProfile(String resourceUri) {
        return new Link("/docs/index.html"+resourceUri).withRel("profile");
    }
}
