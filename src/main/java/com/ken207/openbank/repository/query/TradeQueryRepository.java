package com.ken207.openbank.repository.query;

import com.ken207.openbank.domain.TradeEntity;
import com.ken207.openbank.repository.TradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TradeQueryRepository {

    private final EntityManager em;
    private final TradeRepository tradeRepository;

    public Map<String, Long> getDailyBalanceFrom(Long accountId, String lastInterestPayDate) {
        List<TradeEntity> tradeListForInterest = tradeRepository.findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(accountId, lastInterestPayDate);

        return tradeListForInterest.stream().collect(Collectors.toMap(TradeEntity::getBzDate, TradeEntity::getBlncAfter));
    }
}
