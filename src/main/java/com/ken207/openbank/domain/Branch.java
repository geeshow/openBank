package com.ken207.openbank.domain;

import com.ken207.openbank.domain.enums.BranchType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name="Branch")
@AttributeOverride(name = "id",column = @Column(name = "branch_id"))
public class Branch extends BaseEntity<Branch> {

    private String name;
    private String businessNumber;
    private String taxOfficeCode;
    private String telNumber;
    private LocalDateTime regDateTime;
    private LocalDateTime closeDate;

    @Enumerated(EnumType.STRING)
    private BranchType branchType;

    @ManyToOne
    private Member manager;

    public Branch(String name, String businessNumber, String taxOfficeCode, String telNumber, BranchType branchType) {
        this.name = name;
        this.businessNumber = businessNumber;
        this.taxOfficeCode = taxOfficeCode;
        this.telNumber = telNumber;
        this.regDateTime = LocalDateTime.now();
        this.branchType = branchType;
    }

    public void setBranchType(BranchType branchType) {
        this.branchType = branchType;
    }
}
