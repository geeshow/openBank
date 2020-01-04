package com.ken207.openbank.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.enums.PeriodType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder @NoArgsConstructor
@AllArgsConstructor
@Table(name="Interest")
@AttributeOverride(name = "id",column = @Column(name = "interest_id"))
public class InterestEntity extends BaseEntity<InterestEntity> {

    private String reckonDate;
    private String fromDate;
    private String toDate;
    private double basicRate;
    private long interest;

    @Enumerated(EnumType.STRING)
    private PeriodType periodType;

    @Builder.Default
    @OneToMany(mappedBy = "interestEntity", cascade = CascadeType.ALL)
    private List<InterestDetailEntity> interestDetails = new ArrayList<>();

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @Transient
    private List<TradeEntity> tradeListForInterest; //이자계산용 거래내역

    @Transient
    private boolean isSorted = false;

    //==연관관계 메서드==//
    public void setAccount(AccountEntity account) {
        this.account = account;
        this.account.getInterestEntities().add(this);
    }

    public static InterestEntity createInterest(AccountEntity account) {
        InterestEntity interest = InterestEntity.builder()
                .basicRate(account.getBasicRate().getRate())
                .reckonDate(account.getReckonDt())
                .build();

        interest.setAccount(account);

        return interest;
    }

    public void setPeriod(String fromDate, String toDate, PeriodType periodType) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.periodType = periodType;
    }

    public void setTradeListForInterest(List<TradeEntity> tradeListForInterest) {
        this.tradeListForInterest = tradeListForInterest;
    }

    /**
     * 거래내역 최근거래 -> 오래된 거래 순서로 정렬
     */
    public void sortedTradeList() {
        //거래내역은 거래일시의 역순이어야 함.
        this.tradeListForInterest = this.tradeListForInterest.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        isSorted = true;
    }

    /**
     * 거래내역을 이용해 이자계산 상세 내역을 생성
     */
    public void makeInterestDetail() {

        if ( !isSorted ) {
            sortedTradeList();
        }

        String flagTradeDate = this.toDate;
        for (TradeEntity trade: tradeListForInterest) {

            InterestDetailEntity interestDetail = InterestDetailEntity.builder()
                    .interestRate(this.basicRate)
                    .balance(trade.getBlncAfter())
                    .fromDate(trade.getTradeDate())
                    .toDate(flagTradeDate)
                    .build();

            interestDetails.add(interestDetail);

            flagTradeDate = OBDateUtils.addDays(trade.getTradeDate(), -1);
        }
    }


    public void calculate() {
        if ( this.periodType == PeriodType.DAILY) {
            interestDetails.stream().forEach(o -> {
                o.calculateByDays();
            });
        }
        else {
            interestDetails.stream().forEach(o -> {
                o.calculateByMonths();
            });
        }
        Double interestSum = interestDetails.stream().collect(Collectors.summingDouble(InterestDetailEntity::getInterest));
        this.interest = Double.valueOf(Math.ceil(interestSum)).longValue();
    }

    /**
     * 일별로 마감 잔액 구하기.
     * 이자계산시 하루 중 변동된 잔액은 필요가 없으므로 일별로 마지막 거래만 남겨서 이자계산을 한다.
     */
    public void remainLastTradeOfDays() {

        if ( !isSorted ) {
            sortedTradeList();
        }

        var ref = new Object() {
            String finalFlagTradeDate = toDate;
        };

        this.tradeListForInterest = tradeListForInterest.stream()
                .filter(trade -> {
                    if (OBDateUtils.isLeftLater(trade.getTradeDate(), ref.finalFlagTradeDate)) {
                        return false;
                    } else if (OBDateUtils.isLeftEarlier(trade.getTradeDate(), this.fromDate)) {
                        return false;
                    }

                    ref.finalFlagTradeDate = OBDateUtils.addDays(trade.getTradeDate(), -1);
                    return true;
                })
                .collect(Collectors.toList());
    }

    public TradeEntity payInterest(String reckonDate) {
        this.account.setReckonDt(reckonDate);
        return this.account.payInterest(this);
    }
}
