package com.ken207.openbank.domain;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.enums.PeriodType;
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

    public List<InterestDetailEntity> calculate() {

        List<InterestDetailEntity> result = new ArrayList<>();
        return result;
    }

    public List<InterestDetailEntity> makeInterestDetail(List<TradeEntity> tradeListForInterest) {

        String flagBzDate = this.toDate;
        for (TradeEntity trade: tradeListForInterest) {
            if ( OBDateUtils.isLeftEarlier(trade.getBzDate(), flagBzDate) ) {
                InterestDetailEntity interestDetail = InterestDetailEntity.builder()
                        .interestRate(this.basicRate)
                        .balance(trade.getBlncAfter())
                        .fromDate(trade.getBzDate())
                        .toDate(flagBzDate)
                        .build();

                interestDetail.setPeriodType(PeriodType.DAILY);
                interestDetail.calculate();
                interestDetails.add(interestDetail);

                flagBzDate = OBDateUtils.addDays(trade.getBzDate(), -1);
            }
        }

        return interestDetails;
    }

    public long getInterestInPay() {
        return Double.valueOf(Math.ceil(interest)).longValue();
    }
}
