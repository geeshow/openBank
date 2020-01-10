package com.ken207.openbank.repository;

import com.ken207.openbank.domain.Account;
import com.ken207.openbank.domain.Trade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
    Page<Trade> findByAccount(Account account, Pageable pageable);

    List<Trade> findByBzDateGreaterThan(String lastIntsDt);

    List<Trade> findByAccountIdAndBzDateGreaterThan(Long accountId, String lastIntsDt);

    List<Trade> findByAccountIdAndTradeDateGreaterThanOrderBySrnoDesc(Long accountId, String tradeDate);

}
