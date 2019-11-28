package com.ken207.openbank.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    private String name;
    private String subjCd;
    private Double inrt;

    public Product(String name, String subjCd, Double inrt) {
        this.name = name;
        this.subjCd = subjCd;
        this.inrt = inrt;
    }
}
