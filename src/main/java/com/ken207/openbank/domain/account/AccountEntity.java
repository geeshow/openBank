package com.ken207.openbank.domain.account;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.BaseEntity;
import com.ken207.openbank.domain.enums.*;
import com.ken207.openbank.exception.BizRuntimeException;
import lombok.*;
import lombok.Builder.Default;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
@AttributeOverride(name = "id",column = @Column(name = "account_id"))
public class AccountEntity extends BaseEntity<AccountEntity> {

    private String accountNum; //계좌번호
    private String password; //비밀번호
    private String regDate; //신규일자
    private String closeDate; //해지일자
    private String lastIntsDt; //최종이자계산일자
    private long accoBlnc;
    private long loanLimitAmount; //대출한도금액
    private long lastTrnSrno; //최종거래일련번호

    @Enumerated(EnumType.STRING)
    private SubjectCode subjectCode; //과목코드

    @Enumerated(EnumType.STRING)
    @Default
    private YesNo loanYn = YesNo.N; //대출계좌여부

    @Enumerated(EnumType.STRING)
    private AccountStatusCode accountStatusCode; //계좌상태코드

    @Enumerated(EnumType.STRING)
    private TaxationCode taxationCode; //과세구분코드

    @Enumerated(EnumType.STRING)
    private ChnlDvcd regChnlDvcd;

    @Default
    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_log_id")
    private List<TradeLog> tradeLogs = new ArrayList<>();

    @Transient
    private String reckonDt; //기산일자

    @Transient
    private long blncBefore; //거래전잔액

    @Transient
    private long tradeAmount; //거래금액
    /**
     * 신규
     * @return
     */
    public static AccountEntity openAccount(String accountNum, SubjectCode subjectCode, String regDate, TaxationCode taxationCode) {
        AccountEntity account = AccountEntity.builder()
                .subjectCode(subjectCode) //과목코드
                .accountNum(accountNum) //계좌번호
                .regDate(regDate) //신규일자
                .reckonDt(regDate) //최종거래일자
                .lastIntsDt(regDate) //최종이자계산일자
                .taxationCode(taxationCode) //과세구분코드
                .accountStatusCode(AccountStatusCode.ACTIVE)
                .accoBlnc(0)
                .blncBefore(0)
                .tradeAmount(0)
                .build();

        account.addTradeLog(TradeCd.OPEN);

        return account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 입금
     */
    public long inAmount(long tradeAmount) {

        this.tradeAmount = tradeAmount;
        this.blncBefore = this.accoBlnc;
        this.accoBlnc += this.tradeAmount;

        addTradeLog(TradeCd.IN);

        return this.accoBlnc;
    }

    /**
     * 출금
     */
    public long outAmount(long tradeAmount) {

        if ( getPosibleOutAmt() < tradeAmount ) {
            throw new BizRuntimeException("출금가능금액 부족");
        }
        this.tradeAmount = tradeAmount;
        this.blncBefore = this.accoBlnc;
        this.accoBlnc -= this.tradeAmount;

        addTradeLog(TradeCd.OUT);

        return this.accoBlnc;
    }

    public long getPosibleOutAmt() {
        if ( this.loanYn == YesNo.Y ) {
            return this.loanLimitAmount + this.accoBlnc;
        }
        else {
            return this.accoBlnc;
        }
    }

    /**
     * 해지
     */
    public void closeAmount() {

    }


    private void addTradeLog(TradeCd tradeCd) {

        TradeLog tradeLog = TradeLog.builder()
                .amount(this.tradeAmount)
                .blncBefore(this.blncBefore)
                .blncAfter(this.accoBlnc)
                .tradeCd(tradeCd)
                .tradeDate(this.getReckonDt())
                .accountEntity(this)
                .build();

        this.tradeLogs.add(tradeLog);
    }

    public String getReckonDt() {
        if ( reckonDt == null ) {
            return OBDateUtils.getToday();
        }

        return reckonDt;
    }
}
