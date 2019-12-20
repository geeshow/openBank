package com.ken207.openbank.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Rate")
@AttributeOverride(name = "id",column = @Column(name = "rate_id"))
public class RateEntity extends BaseEntity<ProductEntity> {

    private String name;
    private String startDate;
    private String endDate;
    private double rate;
}
