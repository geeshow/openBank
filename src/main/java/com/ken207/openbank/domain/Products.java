package com.ken207.openbank.domain;

public interface Products {
    AccountEntity openAccount(AccountEntity accountEntity);
    AccountEntity closeAccount(AccountEntity accountEntity);
    AccountEntity inAmount(AccountEntity accountEntity);
    AccountEntity outAmount(AccountEntity accountEntity);
    AccountEntity payInterest(AccountEntity accountEntity);
}
