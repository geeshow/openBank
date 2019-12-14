package com.ken207.openbank.domain.enums;

public enum TaxationCode {
    REGULAR("01"), PREFER("08"), EXEMPTION("02");

    private String taxationCode;
    TaxationCode(String taxationCode) {
        this.taxationCode = taxationCode;
    }

    public String getTaxationCode() {
        return this.taxationCode;
    }
}
