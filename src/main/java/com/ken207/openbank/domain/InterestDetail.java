package com.ken207.openbank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ken207.openbank.common.OBDateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="InterestDetail")
@AttributeOverride(name = "id",column = @Column(name = "interest_detail_id"))
public class InterestDetail extends BaseEntity<InterestDetail> {
    private String fromDate;
    private String toDate;
    private double interestRate;
    private double taxRate;
    private long balance;
    private int months;
    private int days;
    private double interestAmount;
    private double tax;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "interest_id")
    private Interest interest;

    /**
     * 일수이자계산
     * @return 이자 = 잔액 * 이율 * 일수 / 100 / 365
     */
    public double calculateByDays() {
        setDays();

        BigDecimal balanceBig = new BigDecimal(balance);
        BigDecimal interestRateBig = new BigDecimal(interestRate);
        BigDecimal daysBig = new BigDecimal(days);
        BigDecimal number100ForRate = new BigDecimal(100);
        BigDecimal daysOfYear = new BigDecimal(365);
        BigDecimal interestBig = balanceBig.multiply(interestRateBig).divide(number100ForRate, MathContext.DECIMAL64).multiply(daysBig).divide(daysOfYear, MathContext.DECIMAL64);
        this.interestAmount = interestBig.setScale(3, RoundingMode.DOWN).doubleValue();
        return this.interestAmount;
    }

    public void setDays() {
        this.days = OBDateUtils.getNumberOfDaysInclude(fromDate, toDate);
    }

    /**
     * 월수이자계산
     * @return 이자 = 잔액 * 이율 * 일수 / 100 / 365
     */
    public double calculateByMonths() {
        BigDecimal balanceBig = new BigDecimal(balance);
        BigDecimal interestRateBig = new BigDecimal(interestRate);
        BigDecimal monthsBig = new BigDecimal(months);
        BigDecimal number100ForRate = new BigDecimal(100);
        BigDecimal monthsOfYear = new BigDecimal(12);
        BigDecimal interestBig = balanceBig.multiply(interestRateBig).divide(number100ForRate, MathContext.DECIMAL64).multiply(monthsBig).divide(monthsOfYear, MathContext.DECIMAL64);
        this.interestAmount = interestBig.doubleValue();
        return this.interestAmount;
    }
}
