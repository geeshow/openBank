package com.ken207.openbank.service;

import com.ken207.openbank.domain.account.AccountEntity;
import com.ken207.openbank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public AccountEntity openAccount(String passwd) {
        AccountEntity accountEntity = AccountEntity.openAccount();
        accountEntity.setPassword(passwd);
        AccountEntity saveAccount = accountRepository.save(accountEntity);
        saveAccount.setAcno();
        return saveAccount;
    }
}
