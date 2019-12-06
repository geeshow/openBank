package com.ken207.openbank.domain;

import com.ken207.openbank.domain.account.AccountEntity;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name="Customer")
@AttributeOverride(name = "id",column = @Column(name = "customer_id"))
public class CustomerEntity extends BaseEntity<CustomerEntity> {

    private String name;
    private String email;
    private String nation;
    private LocalDateTime regDateTime;

    @Nullable
    @ManyToOne(fetch = LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "branch_id", name="reg_branch_id")
    private BranchEntity regBranchEntity;

    @Nullable
    @ManyToOne(fetch = LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "branch_id", name="mng_branch_id")
    private BranchEntity mngBranchEntity;

    @Nullable
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity regEmployeeEntity;

    @OneToMany(mappedBy = "customerEntity", cascade = CascadeType.ALL)
    private List<AccountEntity> accountEntities = new ArrayList<>();

    public CustomerEntity(String name, String email, String nation) {
        this.name = name;
        this.email = email;
        this.nation = nation;
        this.regDateTime = LocalDateTime.now();
    }

    public void setRegEmployeeEntity(EmployeeEntity regEmployeeEntity) {
        this.regBranchEntity = regEmployeeEntity.getBelongBranchEntity();
        this.mngBranchEntity = regEmployeeEntity.getBelongBranchEntity();
        this.regEmployeeEntity = regEmployeeEntity;
    }
}
