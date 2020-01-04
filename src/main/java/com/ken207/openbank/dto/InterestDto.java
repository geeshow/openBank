package com.ken207.openbank.dto;

import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.InterestDetailEntity;
import com.ken207.openbank.domain.enums.AccountStatusCode;
import com.ken207.openbank.domain.enums.PeriodType;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.enums.TaxationCode;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class InterestDto {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class Response {
        private String accountNum; //계좌번호
        private String lastIntsDt; //최종이자계산일자. 이자계산기간의 마지막날(from ~ to에서 to에 해당)
        private long balance;
        private String fromDate;
        private String toDate;
        private double basicRate;
        private long interest;
        private PeriodType periodType;
        private java.util.List<InterestDetailEntity> details;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    public static class Dto {
        private Long id;
        private String accountNum; //계좌번호
        private String fromDate;
        private String toDate;
        private double basicRate;
        private long interest;
        private PeriodType periodType;
    }

}
