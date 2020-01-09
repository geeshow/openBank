package com.ken207.openbank.dto;

import com.ken207.openbank.domain.enums.SubjectCode;
import com.sun.istack.NotNull;
import lombok.*;

public class ProductDto {

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter @Setter
    public static class Create {
        @NotNull
        private String name;
        private String productCode;
        private SubjectCode subjectCode; //과목코드
        private String startDate;
        private String endDate;
        private double basicRate;
    }


}
