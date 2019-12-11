package com.ken207.openbank.domain.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("24")
@Getter
@Setter
public class AccountEntityFx {
    private String contractMonth; //계약기간월수
    private String contractDay; //계약기간일수
    private String lastIntPayDt; //최종이자지급일자
}
