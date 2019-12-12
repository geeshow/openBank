package com.ken207.openbank.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ken207.openbank.domain.BaseEntity;
import com.ken207.openbank.domain.EmployeeEntity;
import com.ken207.openbank.domain.enums.TradeCd;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverride(name = "id",column = @Column(name = "trade_id"))
public class TradeLog extends BaseEntity<TradeLog> {

    private long srno;
    private String tradeDate;
    private String bzDate;
    private long amount;
    private long blncBefore;
    private long blncAfter;
    private TradeCd tradeCd;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;
}
