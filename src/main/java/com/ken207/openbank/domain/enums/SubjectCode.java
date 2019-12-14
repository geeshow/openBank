package com.ken207.openbank.domain.enums;

public enum SubjectCode {
    /**
     * REGULAR : regular savings account, 보통예금
     * FIXED : fixed deposit, 정기예금
     * INTALLMENT : installment savings,정기적금
     */
    REGULAR("13"), FIXED("24"), INTALLMENT("31");

    private String subjectCode;
    SubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectCode() {
        return this.subjectCode;
    }
}
