package com.ken207.openbank.domain.enums;

public enum AccountStatusCode {
    ACTIVE("10"),
    STOP("90"),
    STOP_AND_TRANSFER("91"),
    CLOSE("40"),
    CANCEL("60");

    private String accountStatusCode;
    AccountStatusCode(String accountStatusCode) {
        this.accountStatusCode = accountStatusCode;
    }

    public String getAccountStatusCode() {
        return this.accountStatusCode;
    }
}
