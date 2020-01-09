package com.ken207.openbank.dto.request.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InterestDetailDto {
    private String fromDate;
    private String toDate;
    private double interestRate;
    private double taxRate;
    private long balance;
    private int months;
    private int days;
    private double interestAmount;
    private double tax;
}
