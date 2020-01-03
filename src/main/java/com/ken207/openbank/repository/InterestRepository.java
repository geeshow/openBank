package com.ken207.openbank.repository;

import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.InterestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<InterestEntity, Long> {

    Page<InterestEntity> findByAccount(AccountEntity accountEntity, Pageable pageable);

}
