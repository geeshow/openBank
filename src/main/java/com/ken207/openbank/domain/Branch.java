package com.ken207.openbank.domain;

import com.ken207.openbank.domain.enums.BranchType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Branch {

    @Id @GeneratedValue
    @Column(name = "branch_id")
    private Long id;

    private String name;
    private String businessNumber;
    private String taxOfficeCode;
    private String telNumber;
    private LocalDateTime regDateTime;
    private LocalDateTime closeDate;

    @Enumerated(EnumType.STRING)
    private BranchType branchType;

    public Branch(String name, String businessNumber, String taxOfficeCode, String telNumber) {
        this.name = name;
        this.businessNumber = businessNumber;
        this.taxOfficeCode = taxOfficeCode;
        this.telNumber = telNumber;
        this.regDateTime = LocalDateTime.now();
    }

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
