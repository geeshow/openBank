package com.ken207.openbank.domain.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ken207.openbank.domain.BaseEntity;
import com.ken207.openbank.domain.EmployeeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountLog extends BaseEntity<AccountLog> {

    private LocalDateTime changeTime;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity changeEmp;
    private String guid;

    @JsonIgnore
    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AccountEntity accountEntity;
}
