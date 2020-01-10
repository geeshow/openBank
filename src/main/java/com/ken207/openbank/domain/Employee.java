package com.ken207.openbank.domain;

import com.ken207.openbank.domain.enums.EmployeeType;
import com.ken207.openbank.domain.enums.Position;
import com.ken207.openbank.domain.enums.EmployeeStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name="Employee")
@AttributeOverride(name = "id",column = @Column(name = "employee_id"))
public class Employee extends BaseEntity<Employee> {

    private String employeeCode;
    private String name;

    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus userStat;

    @Enumerated(EnumType.STRING)
    private Position position;
    private LocalDateTime regDate;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Branch belongBranch;

    public Employee(String employeeCode, String name, EmployeeType employeeType, Branch belongBranch) {
        this.employeeCode = employeeCode;
        this.name = name;
        this.employeeType = employeeType;
        this.userStat = EmployeeStatus.근무;
        this.position = Position.사원;
        this.regDate = LocalDateTime.now();
        setBelongBranch(belongBranch);
    }

    public void setBelongBranch(Branch belongBranch) {
        this.belongBranch = belongBranch;
    }
}
