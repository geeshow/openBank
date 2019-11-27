package com.ken207.openbank.domain;

import com.ken207.openbank.domain.enums.BranchType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private LocalDateTime newDate;
    private LocalDateTime closeDate;

    @Enumerated(EnumType.STRING)
    private BranchType branchType;

    public Branch(String name, BranchType branchType) {
        this.name = name;
        this.branchType = branchType;
        this.newDate = LocalDateTime.now();
    }
}
