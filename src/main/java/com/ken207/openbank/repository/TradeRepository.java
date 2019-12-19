package com.ken207.openbank.repository;

import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.TradeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long> {
    Page<TradeEntity> findByAccount(AccountEntity accountEntity, Pageable pageable);
}
