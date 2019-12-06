package com.ken207.openbank.domain.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("13")
@Getter
@Setter
public class AccountEntityRg extends AccountEntity<AccountEntityRg> {
    private LocalDateTime lastUseDate; //최종사용일자
    private String loanYn; //대출여부
    private String lastIntPayDt; //최종이자지급일자
}
