package com.ken207.openbank.repository;

import com.ken207.openbank.domain.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByEmailIgnoreCase(@NonNull String email);
}
