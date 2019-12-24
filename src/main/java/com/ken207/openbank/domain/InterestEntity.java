package com.ken207.openbank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Interest")
@AttributeOverride(name = "id",column = @Column(name = "interest_id"))
public class InterestEntity extends BaseEntity<InterestEntity> {

    private String reckonDate;
    private String fromDate;
    private String toDate;
    private double basicRate;
    private double interest;

    @OneToMany
    @JoinColumn(name = "interest_detail_id")
    private List<InterestDetailEntity> interestDetails;

    @OneToOne
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;

    @Transient
    private List<TradeEntity> tradeEntities = new ArrayList<>();


}
