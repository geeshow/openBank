package com.ken207.openbank.controller;

import com.ken207.openbank.annotation.CurrentUser;
import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.Account;
import com.ken207.openbank.domain.Interest;
import com.ken207.openbank.domain.Member;
import com.ken207.openbank.domain.Trade;
import com.ken207.openbank.dto.InterestDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.mapper.InterestMapper;
import com.ken207.openbank.mapper.TradeMapper;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.repository.InterestRepository;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/interest", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class InterestController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final InterestRepository interestRepository;
    private final ControllerLinkBuilder controllerLinkBuilder = linkTo(InterestController.class);
    private final InterestMapper interestMapper = InterestMapper.INSTANCE;
    private final TradeMapper tradeMapper = TradeMapper.INSTANCE;

    @GetMapping("/{accountNum}/log")
    public ResponseEntity getInterestList(@PathVariable String accountNum, Pageable pageable, PagedResourcesAssembler<Interest> assembler,
                                       @CurrentUser Member currentMember) {

        Account account = this.accountRepository.findByAccountNum(accountNum);

        //Request Data Validation
        if ( account == null ) {
            return ResponseEntity.notFound().build();
        }

        Page<Interest> page = this.interestRepository.findByAccount(account, pageable);

        PagedResources<Resource> pagedResources = assembler.toResource(page,
                e -> new Resource(
                        interestMapper.entityToDtoForList(e),
                        getLinkOfDetail(accountNum, e.getId())
                ));

        pagedResources.add(new Link("/docs/index.html#resources-interest-list").withRel("profile"));
        pagedResources.add(getLinkOfList(accountNum));
        pagedResources.add(getLinkOfIndex(accountNum));

        return ResponseEntity.ok(pagedResources);
    }

    @GetMapping("/{accountNum}/log/{detailId}")
    public ResponseEntity getInterestDetail(@PathVariable String accountNum,
                                            @PathVariable Long detailId,
                                          @CurrentUser Member currentMember) {

        Account account = this.accountRepository.findByAccountNum(accountNum);

        //Request Data Validation
        if ( account == null ) {
            return ResponseEntity.notFound().build();
        }

        Interest interest = this.interestRepository.findById(detailId).get();

        InterestDto.ResponseDetail responseDetail = interestMapper.entityToDetail(interest);

        //HATEOAS REST API
        Resource resource = new Resource(responseDetail,
                controllerLinkBuilder.slash(accountNum).slash("log").slash(interest.getId()).withSelfRel(),
                getLinkOfIndex(accountNum),
                getLinkOfList(accountNum),
                getLinkOfProfile("#resources-interest-detail")
        );

        return ResponseEntity.ok(resource);
    }

    @PostMapping("/{accountNum}")
    public ResponseEntity payInterest(@PathVariable String accountNum,
                                          @CurrentUser Member member) {

        Account account = this.accountRepository.findByAccountNum(accountNum);

        //Request Data Validation
        if ( account == null ) {
            return ResponseEntity.notFound().build();
        }

        Trade resultTrade = accountService.payInterest(accountNum, OBDateUtils.getYesterday(), OBDateUtils.getToday());

        //set response data
        TradeDto.Response response = tradeMapper.entityToResponse(resultTrade);

        //HATEOAS REST API
        Resource resource = new Resource(response,
                controllerLinkBuilder.slash(accountNum).withSelfRel(),
                getLinkOfIndex(accountNum),
                getLinkOfList(accountNum),
                getLinkOfDetail(accountNum, resultTrade.getInterest().getId()),
                getLinkOfProfile("#resources-interest-pay")
        );

        return ResponseEntity.ok().body(resource);
    }

    @GetMapping("/{accountNum}")
    public ResponseEntity indexInterest(@PathVariable String accountNum,
                                      @CurrentUser Member member) {

        Account account = this.accountRepository.findByAccountNum(accountNum);

        //Request Data Validation
        if ( account == null ) {
            return ResponseEntity.notFound().build();
        }

        //Calculate interest
        //이자계산은 지급이나 해지 당일은 해당되지 않기 때문에 한편(until -> -1)으로 계산함.
        String calculateInterestToDate = OBDateUtils.addDays(OBDateUtils.getToday(), -1);
        Interest interest = accountService.getInterest(accountNum, calculateInterestToDate);

        //Set response data
        InterestDto.Response response = interestMapper.entityToDto(interest);

        //HATEOAS REST API
        Resource resource = new Resource(response,
                controllerLinkBuilder.slash(accountNum).withSelfRel(),
                getLinkOfCheck(accountNum, OBDateUtils.getToday()),
                getLinkOfReceive(accountNum, OBDateUtils.getToday()),
                getLinkOfList(accountNum),
                getLinkOfProfile("#resources-interest-check")
        );

        return ResponseEntity.ok().body(resource);
    }


    @GetMapping("/{accountNum}/{until}")
    public ResponseEntity checkInterest(@PathVariable String accountNum,
                                        @PathVariable String until,
                                        @CurrentUser Member member) {

        Account account = this.accountRepository.findByAccountNum(accountNum);

        //Request Data Validation
        if ( account == null ) {
            return ResponseEntity.notFound().build();
        }

        //Calculate interest
        //이자계산은 지급이나 해지 당일은 해당되지 않기 때문에 한편(until -> -1)으로 계산함.
        String calculateInterestToDate = OBDateUtils.addDays(until, -1);
        Interest interest = accountService.getInterest(accountNum, calculateInterestToDate);

        //Set response data
        InterestDto.Response response = interestMapper.entityToDto(interest);

        //HATEOAS REST API
        Resource resource = new Resource(response,
                controllerLinkBuilder.slash(accountNum).slash(until).withSelfRel(),
                getLinkOfIndex(accountNum),
                getLinkOfCheck(accountNum, until),
                getLinkOfReceive(accountNum, until),
                getLinkOfList(accountNum),
                getLinkOfProfile("#resources-interest-calculate")
        );

        return ResponseEntity.ok().body(resource);
    }

    private Link getLinkOfIndex(String accountNum) {
        return controllerLinkBuilder.slash(accountNum).withRel("interest-index");
    }

    private Link getLinkOfCheck(String accountNum, String untilDate) {
        return controllerLinkBuilder.slash(accountNum).slash(untilDate).withRel("interest-calculate");
    }

    private Link getLinkOfReceive(String accountNum, String untilDate) {
        return controllerLinkBuilder.slash(accountNum).slash(untilDate).withRel("receive");
    }

    private Link getLinkOfList(String accountNum) {
        return controllerLinkBuilder.slash(accountNum).slash("log").withRel("interest-list");
    }

    private Link getLinkOfDetail(String accountNum, Long interestId) {
        return controllerLinkBuilder.slash(accountNum).slash("log").slash(interestId).withRel("interest-detail");
    }

    private Link getLinkOfProfile(String resourceUri) {
        return new Link("/docs/index.html"+resourceUri).withRel("profile");
    }
}
