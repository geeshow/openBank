package com.ken207.openbank.service;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.InterestEntity;
import com.ken207.openbank.domain.ProductEntity;
import com.ken207.openbank.domain.TradeEntity;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.exception.BizRuntimeException;
import com.ken207.openbank.repository.AccountRepository;
import com.ken207.openbank.repository.ProductRepository;
import com.ken207.openbank.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final TradeRepository tradeRepository;
    private final CodeGeneratorService codeGeneratorService;

    @Transactional
    public Long openRegularAccount(AccountDto.RequestOpen accountRequestOpen) {

        ProductEntity productEntity = productRepository.findByProductCode(accountRequestOpen.getProductCode());

        if ( productEntity == null ) {
            throw new BizRuntimeException("존재하지 않는 상품 코드 입니다. 상품코드:"+accountRequestOpen.getProductCode());
        }

        String accountNum = codeGeneratorService.createAccountNumber(SubjectCode.REGULAR.getSubjectCode());

        AccountEntity accountEntity = AccountEntity.openAccount(productEntity, accountNum, accountRequestOpen.getRegDate(), accountRequestOpen.getTaxationCode());

        AccountEntity saveAccount = accountRepository.save(accountEntity);

        return saveAccount.getId();
    }

    @Transactional
    public void setPassword(String accountNum, String newPassword) {
        AccountEntity account = accountRepository.findByAccountNum(accountNum);
        account.setPassword(newPassword);
    }

    @Transactional
    public TradeEntity deposit(String accountNum, TradeDto.RequestDeposit requestDeposit) {
        AccountEntity account = getAccountEntity(accountNum);

        if ( OBDateUtils.compareDate(account.getLastTradeDate(), requestDeposit.getTradeDate()) > 0 ) {
            throw new BizRuntimeException("지정일 이 후 거래가 존재. 기산일 거래를 요청해야 함.");
        }

        account.setReckonDt(requestDeposit.getTradeDate());
        TradeEntity deposit = account.deposit(requestDeposit.getAmount());
        return tradeRepository.save(deposit);
    }

    @Transactional
    public TradeEntity withdraw(String accountNum, TradeDto.RequestDeposit requestWithdraw) {
        AccountEntity account = getAccountEntity(accountNum);


        account.setReckonDt(requestWithdraw.getTradeDate());
        TradeEntity withdraw = account.withdraw(requestWithdraw.getAmount());
        return tradeRepository.save(withdraw);
    }

    private AccountEntity getAccountEntity(String accountNum) {
        AccountEntity account = accountRepository.findByAccountNum(accountNum);
        if (account == null) {
            throw new EntityNotFoundException("존재하지 않는 계좌번호 입니다.");
        }
        return account;
    }

    public Page<AccountEntity> getAccountList(Pageable pageable) {
        Page<AccountEntity> page = this.accountRepository.findAll(pageable);
        page.stream().forEach(o -> o.getProduct().getName());
        return page;
    }

    @Transactional
    public TradeEntity closeAccount(String accountNum, String reckonDate) {
        AccountEntity account = accountRepository.findByAccountNum(accountNum);
        List<TradeEntity> tradeListForInterest = tradeRepository.findByAccountIdAndTradeDateGreaterThan(account.getId(), account.getLastIntsDt());

        InterestEntity interest = InterestEntity.builder()
                .accountEntity(account)
                .basicRate(account.getBasicRate().getRate())
                .reckonDate(reckonDate)
                .fromDate(account.getLastIntsDt())
                .tradeEntities(tradeListForInterest)
                .build();
        return account.closeAccount(interest);
    }
}
