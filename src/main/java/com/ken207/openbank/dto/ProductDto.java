package com.ken207.openbank.dto;

import com.ken207.openbank.domain.RateEntity;
import com.ken207.openbank.domain.enums.SubjectCode;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

public class ProductDto {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    public static class Create {
        private String name;
        private String productCode;
        private SubjectCode subjectCode; //과목코드
        private String startDate;
        private String endDate;
        private double basicRate;
    }


}
