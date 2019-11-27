package com.ken207.openbank.domain;

import com.ken207.openbank.domain.account.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String name;
    private String nation;
    private LocalDateTime regDt;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id", insertable=false, updatable=false)
    private Branch newBranch;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id", insertable=false, updatable=false)
    private Branch mngBranch;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee regEmployee;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name, String nation, Branch newBranch, Employee regEmployee) {
        this.name = name;
        this.nation = nation;
        this.newBranch = newBranch;
        this.regEmployee = regEmployee;
    }
}
