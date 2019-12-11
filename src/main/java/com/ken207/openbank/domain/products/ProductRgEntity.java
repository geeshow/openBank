package com.ken207.openbank.domain.products;

import com.ken207.openbank.domain.BaseEntity;
import com.ken207.openbank.domain.account.AccountEntityRg;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name="ProductRg")
@AttributeOverride(name = "id",column = @Column(name = "product_id"))
public class ProductRgEntity extends BaseEntity<ProductRgEntity> {

    private String name;
    private String subjCd;
    private Double inrt;



}
