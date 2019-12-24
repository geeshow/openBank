package com.ken207.openbank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="InterestDetail")
@AttributeOverride(name = "id",column = @Column(name = "interest_detail_id"))
public class InterestDetailEntity extends BaseEntity<InterestDetailEntity> {
    private String fromDate;
    private String toDate;
    private double applyRate;
    private long balance;
    private int months;
    private int days;

    @ManyToOne
    @JoinColumn(name = "interest_id")
    private InterestEntity interest;

    @OneToOne
    @JoinColumn(name = "account_log_id")
    private TradeEntity relevantTrade;

}
