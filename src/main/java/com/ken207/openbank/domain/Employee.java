package com.ken207.openbank.domain;

import com.ken207.openbank.domain.enums.EmployeeType;
import com.ken207.openbank.domain.enums.Position;
import com.ken207.openbank.domain.enums.UserStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {
    @Id @GeneratedValue
    @Column(name = "employee_id")
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @Enumerated(EnumType.STRING)
    private UserStatus userStat;

    @Enumerated(EnumType.STRING)
    private Position position;
    private LocalDateTime regDate;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Branch belongBranch;

    public Employee(String name, EmployeeType employeeType, Branch belongBranch) {
        this.name = name;
        this.employeeType = employeeType;
        this.userStat = UserStatus.근무;
        this.position = Position.사원;
        this.belongBranch = belongBranch;
        this.regDate = LocalDateTime.now();
    }
}
