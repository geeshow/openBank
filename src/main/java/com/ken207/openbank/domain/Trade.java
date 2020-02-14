package com.ken207.openbank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ken207.openbank.domain.enums.TradeCd;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="Trade")
@AttributeOverride(name = "id",column = @Column(name = "trade_id"))
public class Trade extends BaseEntity<Trade> {

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
    private Account account;

    @JsonIgnore
    @OneToOne(mappedBy = "trade")
    @JoinColumn(name = "interest_id")
    private Interest interest;

    @Builder.Default
    @OneToMany(mappedBy = "trade", cascade = CascadeType.ALL)
    private List<Statement> statements = new ArrayList();

    @Override
    public int compareTo(Trade trade) {
        return Long.compare(this.getSrno(), trade.getSrno());
    }
}
