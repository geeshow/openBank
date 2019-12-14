package com.ken207.openbank.dto;

import com.ken207.openbank.domain.enums.AccountStatusCode;
import com.ken207.openbank.domain.enums.SubjectCode;
import com.ken207.openbank.domain.enums.TaxationCode;
import lombok.*;

public class AccountDto {

    @Builder
    @Getter @Setter
    public static class Request extends AccountDto {
        private String regDate; //신규일자
        private TaxationCode taxationCode; //과세구분코드
    }

    @Builder
    @Getter @Setter
    public static class Response extends AccountDto {
        private String regDate; //신규일자
        private TaxationCode taxationCode; //과세구분코드
        private String accoutNum; //계좌번호
        private String closeDate; //해지일자
        private String lastIntsDt; //최종이자계산일자
        private long accoBlnc;

        private SubjectCode subjectCode; //과목코드
        private AccountStatusCode accountStatusCode; //계좌상태코드
        private AccountStatusCode accoNewStcd; //계좌신규상태코드
        private AccountStatusCode accoTrmtStcd; //계좌해지상태코드
    }

}
