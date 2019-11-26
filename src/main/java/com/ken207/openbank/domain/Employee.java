package com.ken207.openbank.domain;

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
    private String id;
    private String name;
    private String empDvcd;
    private String userStat;
    private String position;
    private LocalDateTime regDate;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private Branch belongBranch;
}
