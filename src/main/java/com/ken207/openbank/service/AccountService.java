package com.ken207.openbank.service;

import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.TradeEntity;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.dto.AccountDto;
import com.ken207.openbank.dto.TradeDto;
import com.ken207.openbank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CodeGeneratorService codeGeneratorService;

    @Transactional
    public String openRegularAccount(AccountDto.RequestOpen accountRequestOpen) {
        String accountNum = codeGeneratorService.createAcno(SubjectCode.REGULAR.getSubjectCode());

        AccountEntity accountEntity = AccountEntity.openAccount(accountNum, SubjectCode.REGULAR, accountRequestOpen.getRegDate(), accountRequestOpen.getTaxationCode());
        AccountEntity saveAccount = accountRepository.save(accountEntity);
        return saveAccount.getAccountNum();
    }

    @Transactional
    public void setPassword(String accountNum, String newPassword) {
        AccountEntity account = accountRepository.findByAccountNum(accountNum);
        account.setPassword(newPassword);
    }

    @Transactional
    public TradeEntity deposit(String accountNum, TradeDto.RequestDeposit requestDeposit) {
        AccountEntity account = getAccountEntity(accountNum);
        account.setReckonDt(requestDeposit.getTradeDate());
        return account.deposit(requestDeposit.getAmount());
    }

    @Transactional
    public TradeEntity outAmount(String accountNum, TradeDto.RequestDeposit requestOut) {
        AccountEntity account = getAccountEntity(accountNum);
        account.setReckonDt(requestOut.getTradeDate());
        return account.outAmount(requestOut.getAmount());
    }

    private AccountEntity getAccountEntity(String acno) {
        AccountEntity account = accountRepository.findByAccountNum(acno);
        if (account == null) {
            throw new EntityNotFoundException("존재하지 않는 계좌번호 입니다.");
        }
        return account;
    }

}
