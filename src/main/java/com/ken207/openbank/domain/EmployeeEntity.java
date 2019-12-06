package com.ken207.openbank.domain;

import com.ken207.openbank.domain.enums.EmployeeType;
import com.ken207.openbank.domain.enums.Position;
import com.ken207.openbank.domain.enums.UserStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name="Employee")
@AttributeOverride(name = "id",column = @Column(name = "employee_id"))
public class EmployeeEntity extends BaseEntity<EmployeeEntity> {

    private String employeeCode;
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
    private BranchEntity belongBranchEntity;

    public EmployeeEntity(String employeeCode, String name, EmployeeType employeeType, BranchEntity belongBranchEntity) {
        this.employeeCode = employeeCode;
        this.name = name;
        this.employeeType = employeeType;
        this.userStat = UserStatus.근무;
        this.position = Position.사원;
        this.regDate = LocalDateTime.now();
        setBelongBranchEntity(belongBranchEntity);
    }

    public void setBelongBranchEntity(BranchEntity belongBranchEntity) {
        this.belongBranchEntity = belongBranchEntity;
    }
}
