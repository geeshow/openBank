package com.ken207.openbank.domain;

import com.ken207.openbank.domain.account.Account;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of="id")
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String name;
    private String nation;
    private LocalDateTime regDt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "branch_id", name="new_branch_id")
    private Branch newBranch;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(referencedColumnName = "branch_id", name="mng_branch_id")
    private Branch mngBranch;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "employee_id")
    private Employee regEmployee;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name, String nation, Employee regEmployee) {
        this.name = name;
        this.nation = nation;
        this.regDt = LocalDateTime.now();
        this.newBranch = regEmployee.getBelongBranch();
        this.mngBranch = regEmployee.getBelongBranch();
        this.regEmployee = regEmployee;
    }
}
