package com.ken207.openbank.dto;

import com.ken207.openbank.domain.enums.SubjectCode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ProductDto {

    @Builder
    @Getter @Setter
    public static class Create {
        private String productCode;
        private SubjectCode subjectCode; //과목코드
        private String startDate;
        private String endDate;
        private double basicRate;
    }
}
