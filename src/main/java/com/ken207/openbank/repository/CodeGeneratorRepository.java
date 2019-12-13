package com.ken207.openbank.repository;

import com.ken207.openbank.domain.CodeGenerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeGeneratorRepository extends JpaRepository<CodeGenerator, Long> {
}
