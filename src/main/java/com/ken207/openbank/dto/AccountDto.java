package com.ken207.openbank.dto;

import com.ken207.openbank.domain.AccountEntity;
import com.ken207.openbank.domain.enums.AccountStatusCode;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.enums.TaxationCode;
import lombok.*;

public class AccountDto {

    @Builder
    @Getter @Setter
    public static class RequestOpen {
        @NonNull
        private String productCode; //상품코드
        @NonNull
        private String regDate; //신규일자
        @NonNull
        private TaxationCode taxationCode; //과세구분코드
    }

    @Builder
    @NoArgsConstructor @AllArgsConstructor
    @Getter @Setter
    public static class Response {
        private String productCode;
        private String productName;
        private SubjectCode subjectCode;
        private double basicRate;

        private String regDate; //신규일자
        private TaxationCode taxationCode; //과세구분코드
        private String accountNum; //계좌번호
        private String closeDate; //해지일자
        private String lastIntsDt; //최종이자계산일자
        private long balance;

        private AccountStatusCode accountStatusCode; //계좌상태코드
    }

    public static Response transform(AccountEntity accountEntity) {

        return Response.builder()
                .regDate(accountEntity.getRegDate())
                .taxationCode(accountEntity.getTaxationCode())
                .accountNum(accountEntity.getAccountNum())
                .closeDate(accountEntity.getCloseDate())
                .lastIntsDt(accountEntity.getLastIntsDt())
                .balance(accountEntity.getBalance())
                .accountStatusCode(accountEntity.getAccountStatusCode())
                .build();

    }
}
