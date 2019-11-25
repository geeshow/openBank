package com.ken207.openbank.domain.account;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Account {
    private String accoIdno; //계좌식별번호
    private String acno; //계좌번호
    private String custNo; //고객번호
    private String passwd; //비밀번호
    private String newDt; //신규일자
    private String trmtDt; //해지일자
    private String lastTrnDt; //최종거래일자
    private String lastIntsDt; //최종이자계산일자
    private Long accoBlnc;

    @Enumerated(EnumType.STRING)
    private AccoStcd accoStcd; //계좌상태코드

    @Enumerated(EnumType.STRING)
    private AccoStcd accoNewStcd; //계좌신규상태코드

    @Enumerated(EnumType.STRING)
    private AccoStcd accoTrmtStcd; //계좌해지상태코드

    @Enumerated(EnumType.STRING)
    private TxtnDvcd txtnDvcd; //과세구분코드

    @Enumerated(EnumType.STRING)
    private ChnlDvcd regChnlDvcd;

    @Enumerated(EnumType.STRING)
    private DataStatus delYn;

    private Product product; //상품코드
    private Branch newBrCd;
    private Branch mngBrCd;

    private DataLog dataLog;

    /**
     * 신규
     * @return
     */
    public void newAccount() {

    }

    /**
     * 입금
     */
    public void inAmount() {

    }

    /**
     * 출금
     */
    public void outAmount() {

    }

    /**
     * 해지
     */
    public void closeAmount() {

    }
}
