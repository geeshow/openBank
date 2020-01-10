package com.ken207.openbank.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name="Customer")
@AttributeOverride(name = "id",column = @Column(name = "customer_id"))
public class Customer extends BaseEntity<Customer> {

    private String name;
    private String email;
    private String nation;
    private LocalDateTime regDateTime;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "branch_id", name="reg_branch_id")
    private Branch regBranch;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "branch_id", name="mng_branch_id")
    private Branch mngBranch;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee regEmployee;

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
