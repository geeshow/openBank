package com.ken207.openbank.repository;

import com.ken207.openbank.domain.Account;
import com.ken207.openbank.domain.Interest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestRepository extends JpaRepository<Interest, Long> {

    Page<Interest> findByAccount(Account account, Pageable pageable);

}
