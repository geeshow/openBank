package com.ken207.openbank.dto.request.dto;

import com.ken207.openbank.domain.InterestDetail;
import com.ken207.openbank.domain.enums.PeriodType;
import com.ken207.openbank.dto.InterestDetailDto;
import lombok.*;

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
        private long expectedInterestAmount;
        private PeriodType periodType;
        private java.util.List<InterestDetailDto> details;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ResponseDetail {
        private String accountNum; //계좌번호
        private String reckonDate;
        private String fromDate;
        private String toDate;
        private double basicRate;
        private long interestAmount;
        private PeriodType periodType;
        private java.util.List<InterestDetail> details;
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
        private long interestAmount;
        private PeriodType periodType;
    }

}
