package com.ken207.openbank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.enums.InOutCd;
import com.ken207.openbank.domain.enums.TradeCd;
import com.ken207.openbank.domain.enums.YesNo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Statement extends BaseEntity<Statement> {

    private LocalDateTime tradeDate;
    private int subSrno;
    private String accountSubjectCode;
    private Long tradeAmount;

    @Enumerated(EnumType.STRING)
    private YesNo isCancel;

    @Enumerated(EnumType.STRING)
    private InOutCd inOutCd;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trade_id")
    private Trade trade;

    public static Statement makeStatement(Trade trade) {
        InOutCd ioCd = InOutCd.PAID;

        // in = OPEN, DEPOSIT, INTEREST
        // out = WITHDRAW, CLOSE
        if ( TradeCd.IO.in.contains(trade.getTradeCd()) ) {
            ioCd = InOutCd.RECEVIED;
        }
        return Statement.builder()
                .trade(trade)
                .tradeDate(LocalDateTime.now())
                .subSrno(0)
                .accountSubjectCode("13")
                .tradeAmount(trade.getAmount())
                .isCancel(YesNo.N)
                .inOutCd(ioCd)
                .build();
    }
}
