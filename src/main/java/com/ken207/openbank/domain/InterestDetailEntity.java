package com.ken207.openbank.domain;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.enums.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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
    private double interestRate;
    private double taxRate;
    private long balance;
    private int months;
    private int days;
    private double interest;
    private double tax;

    @Enumerated(EnumType.STRING)
    private PeriodType periodType;

    @ManyToOne
    @JoinColumn(name = "interest_id")
    private InterestEntity interestEntity;

    public void setPeriodType(PeriodType periodType) {
        this.periodType = periodType;

        if ( periodType == PeriodType.DAILY ) {
            months = 0;
            days = OBDateUtils.getNumberOfDaysInclude(this.fromDate, this.toDate);
        }
        else if ( periodType == PeriodType.MONTHLY ) {

        }
        else if ( periodType == PeriodType.MON_DAILY ) {

        }
    }

    public void calculate() {
        BigDecimal balanceBig = new BigDecimal(balance);
        BigDecimal interestRateBig = new BigDecimal(interestRate);
        BigDecimal daysBig = new BigDecimal(days);
        BigDecimal number100ForRate = new BigDecimal(100);
        BigDecimal daysOfYear = new BigDecimal(365);
        BigDecimal interestBig = balanceBig.multiply(interestRateBig).multiply(daysBig).divide(number100ForRate).divide(daysOfYear);
        this.interest = interestBig.doubleValue();
    }
}
