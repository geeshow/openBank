package com.ken207.openbank.service;

import com.ken207.openbank.domain.account.AccountEntity;
import com.ken207.openbank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public String openAccount(String passwd) {
        AccountEntity accountEntity = AccountEntity.openAccount();
        accountEntity.setPassword(passwd);
        AccountEntity saveAccount = accountRepository.save(accountEntity);
        saveAccount.setAcno();
        return saveAccount.getAcno();
    }

    @Transactional
    public String openAccount() {
        return openAccount("");
    }

    @Transactional
    public Long inAmount(String acno, long amount) {
        AccountEntity account = getAccountEntity(acno);
        account.inAmount(amount);
        return account.getAccoBlnc();
    }

    @Transactional
    public Long outAmount(String acno, long amount) {
        AccountEntity account = getAccountEntity(acno);
        account.outAmount(amount);
        return account.getAccoBlnc();
    }

    private AccountEntity getAccountEntity(String acno) {
        AccountEntity account = accountRepository.findByAcno(acno);
        if (account == null) {
            throw new EntityNotFoundException("존재하지 않는 계좌번호 입니다.");
        }
        return account;
    }

}
