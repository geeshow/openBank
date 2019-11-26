package com.ken207.openbank.domain.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("31")
@Getter
@Setter
public class AccountIt extends Account {
    private String contractMonth; //계약기간월수
    private String contractDay; //계약기간일수
    private String nextPaymentDate; //다음납입일자
    private String paymentFrequency; //납입주기
}
