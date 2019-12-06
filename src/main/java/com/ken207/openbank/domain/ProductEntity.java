package com.ken207.openbank.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name="Product")
@AttributeOverride(name = "id",column = @Column(name = "product_id"))
public class ProductEntity extends BaseEntity<ProductEntity> {

    private String name;
    private String subjCd;
    private Double inrt;

}
