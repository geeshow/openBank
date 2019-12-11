package com.ken207.openbank.domain.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@DiscriminatorValue("13")
@Getter @Setter
@Builder
public class AccountEntityRg {
    private LocalDateTime lastUseDate; //최종사용일자
    private String loanYn; //대출여부
    private String lastIntPayDt; //최종이자지급일자
}
