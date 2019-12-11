package com.ken207.openbank.domain.trade;

import com.ken207.openbank.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public abstract class TradeEntity extends BaseEntity<TradeEntity> {
    private LocalDateTime tradeDate;
    private Long amount;
}
