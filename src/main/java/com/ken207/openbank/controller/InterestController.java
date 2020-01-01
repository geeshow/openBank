package com.ken207.openbank.controller;

import com.ken207.openbank.annotation.CurrentUser;
import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.InterestEntity;
import com.ken207.openbank.domain.MemberEntity;
import com.ken207.openbank.dto.InterestDto;
import com.ken207.openbank.mapper.AccountMapper;
import com.ken207.openbank.mapper.InterestMapper;
import com.ken207.openbank.mapper.TradeMapper;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.repository.ProductRepository;
import com.ken207.openbank.repository.TradeRepository;
import com.ken207.openbank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/interest", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class InterestController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final ControllerLinkBuilder controllerLinkBuilder = linkTo(AccountRegularController.class);
    private final InterestMapper interestMapper = InterestMapper.INSTANCE;

    @GetMapping("/{accountNum}/{until}")
    public ResponseEntity checkInterest(@PathVariable String accountNum,
                                      @PathVariable String until,
                                      @CurrentUser MemberEntity memberEntity) {

        AccountEntity account = this.accountRepository.findByAccountNum(accountNum);

        //Request Data Validation
        if ( account == null ) {
            return ResponseEntity.notFound().build();
        }

        //Calculate interest
        //이자계산은 지급이나 해지 당일은 해당되지 않기 때문에 한편(until -> -1)으로 계산함.
        String calculateInterestToDate = OBDateUtils.addDays(until, -1);
        InterestEntity interest = accountService.getInterest(accountNum, calculateInterestToDate);

        //Set response data
        InterestDto.Response response = interestMapper.entityToDto(interest);

        //HATEOAS REST API
        Resource resource = new Resource(response,
                controllerLinkBuilder.slash(response.getAccountNum()).withSelfRel(),
                getLinkOfCheck(accountNum, until),
                getLinkOfReceive(accountNum, until),
                getLinkOfList(accountNum),
                getLinkOfProfile("#resources-interest-check")
        );

        return ResponseEntity.ok().body(resource);
    }

    private Link getLinkOfCheck(String accountNum, String untilDate) {
        return controllerLinkBuilder.slash(accountNum).slash(untilDate).withRel("check");
    }

    private Link getLinkOfReceive(String accountNum, String untilDate) {
        return controllerLinkBuilder.slash(accountNum).slash(untilDate).withRel("receive");
    }

    private Link getLinkOfList(String accountNum) {
        return controllerLinkBuilder.slash(accountNum).withRel("received-list");
    }

    private Link getLinkOfProfile(String resourceUri) {
        return new Link("/docs/index.html"+resourceUri).withRel("profile");
    }
}
