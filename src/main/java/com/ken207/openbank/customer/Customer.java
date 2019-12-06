package com.ken207.openbank.customer;

import com.ken207.openbank.domain.Branch;
import com.ken207.openbank.domain.Employee;
import com.ken207.openbank.domain.account.Account;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of="id")
public class Customer {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String name;
    private String email;
    private String nation;
    private LocalDateTime regDateTime;

    @Nullable
    @ManyToOne(fetch = LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "branch_id", name="reg_branch_id")
    private Branch regBranch;

    @Nullable
    @ManyToOne(fetch = LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "branch_id", name="mng_branch_id")
    private Branch mngBranch;

    @Nullable
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee regEmployee;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public Customer(String name, String email, String nation) {
        this.name = name;
        this.email = email;
        this.nation = nation;
        this.regDateTime = LocalDateTime.now();
    }

    public void setRegEmployee(Employee regEmployee) {
        this.regBranch = regEmployee.getBelongBranch();
        this.mngBranch = regEmployee.getBelongBranch();
        this.regEmployee = regEmployee;
    }
}
