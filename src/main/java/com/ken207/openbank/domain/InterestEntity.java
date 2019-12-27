package com.ken207.openbank.domain;

import com.ken207.openbank.common.OBDateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<InterestDetailEntity> calculrate() {

        List<InterestDetailEntity> result = new ArrayList<InterestDetailEntity>();
        return result;
    }

    public List<InterestDetailEntity> makeInterestDetail() {
        Map<String, Long> dailyBalances = tradeEntities.stream().collect(Collectors.toMap(TradeEntity::getBzDate, TradeEntity::getBlncAfter));

        List<InterestDetailEntity> interestDetailList = new ArrayList<>();
        String preBizDate;
        dailyBalances.forEach((tradeDate, balance) -> {
            if ( !interestDetailList.isEmpty() ) {
                InterestDetailEntity interestDetailEntity = interestDetailList.get(interestDetailList.size() - 1);

            }
            InterestDetailEntity build = InterestDetailEntity.builder()
                    .applyRate(this.basicRate)
                    .balance(balance)
                    .fromDate(tradeDate)
                    .months(0)
                    .build();
            interestDetailList.add(build);
        });

        return interestDetailList;
    }

    public long getInterestInPay() {
        return Double.valueOf(Math.ceil(interest)).longValue();
    }
}
