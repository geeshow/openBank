package com.ken207.openbank.domain;

import com.ken207.openbank.domain.enums.InOutCd;
import com.ken207.openbank.domain.enums.TradeCd;
import com.ken207.openbank.domain.enums.YesNo;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
@Builder

public class Statement extends BaseEntity<Statement> {

    private LocalDateTime tradeDate;
    private String tradeId;
    private int subSrno;
    private String accountSubjectCode;
    private Long tradeAmount;

    @Enumerated(EnumType.STRING)
    private YesNo isCancel;

    @Enumerated(EnumType.STRING)
    private InOutCd inOutCd;
}
