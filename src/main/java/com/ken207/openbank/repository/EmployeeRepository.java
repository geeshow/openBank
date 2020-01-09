package com.ken207.openbank.repository;

import com.ken207.openbank.domain.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findByEmployeeCode(String internet);
}
