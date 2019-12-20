package com.ken207.openbank.domain;

import com.ken207.openbank.domain.enums.SubjectCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Product")
@AttributeOverride(name = "id",column = @Column(name = "product_id"))
public class ProductEntity extends BaseEntity<ProductEntity> {

    private String productCode;

    @Enumerated(EnumType.STRING)
    private SubjectCode subjectCode; //과목코드

    @Builder.Default
    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rate_id")
    private List<RateEntity> rateEntities = new ArrayList<>();
}
