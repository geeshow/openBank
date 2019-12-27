package com.ken207.openbank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.enums.TradeCd;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="Trade")
@AttributeOverride(name = "id",column = @Column(name = "trade_id"))
public class TradeEntity extends BaseEntity<TradeEntity> {

    private long srno;
    private String tradeDate; // reckon date, request date
    private String bzDate; // real trade date, system date
    private long amount;
    private long blncBefore;
    private long blncAfter;
    private TradeCd tradeCd;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AccountEntity account;
}
