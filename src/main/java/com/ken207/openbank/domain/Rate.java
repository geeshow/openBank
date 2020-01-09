package com.ken207.openbank.domain;

import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Rate")
@AttributeOverride(name = "id",column = @Column(name = "rate_id"))
public class Rate extends BaseEntity<Product> {

    private String name;
    private String startDate;
    private String endDate;
    private double rate;

}
