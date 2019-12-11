package com.ken207.openbank.domain;

import com.ken207.openbank.domain.account.AccountEntity;

public interface Products {
    AccountEntity openAccount(AccountEntity accountEntity);
    AccountEntity closeAccount(AccountEntity accountEntity);
    AccountEntity inAmount(AccountEntity accountEntity);
    AccountEntity outAmount(AccountEntity accountEntity);
    AccountEntity payInterest(AccountEntity accountEntity);
}
