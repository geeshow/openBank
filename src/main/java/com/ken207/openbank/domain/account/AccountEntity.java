package com.ken207.openbank.domain.account;

import com.ken207.openbank.domain.BaseEntity;
import com.ken207.openbank.domain.enums.AccoStcd;
import com.ken207.openbank.domain.enums.ChnlDvcd;
import com.ken207.openbank.domain.enums.TradeCd;
import com.ken207.openbank.domain.enums.TxtnDvcd;
import lombok.*;
import lombok.Builder.Default;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.util.StringUtils;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
public class AccountEntity extends BaseEntity<AccountEntity> {

    @Id
    @GeneratedValue
    private Long id;
    private String subjcd; //과목코드
    private String acno; //계좌번호
    private String password; //비밀번호
    private String newDt; //신규일자
    private String trmtDt; //해지일자
    private String lastTrnDt; //최종거래일자
    private String lastIntsDt; //최종이자계산일자
    private long accoBlnc;

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

    @Default
    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_log_id")
    private List<TradeLog> tradeLogs = new ArrayList<>();

    /**
     * 신규
     * @return
     */
    public static AccountEntity openAccount() {
        String tradeDate = LocalDate.now().toString();
        AccountEntity account = AccountEntity.builder()
                .subjcd("13") //과목코드
                .acno("") //계좌번호
                .newDt(tradeDate) //신규일자
                .lastTrnDt(tradeDate) //최종거래일자
                .lastIntsDt(tradeDate) //최종이자계산일자
                .accoBlnc(0)
                .build();

        account.addTradeLog(0, 0, TradeCd.OPEN);

        return account;
    }

    public String setAcno() {
        this.acno = this.subjcd + (100000+this.getId());
        return this.acno;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 입금
     */
    public long inAmount(long tradeAmount) {

        long blncBefore = this.accoBlnc;
        this.accoBlnc += tradeAmount;

        addTradeLog(tradeAmount, blncBefore, TradeCd.IN);

        return this.accoBlnc;
    }

    /**
     * 출금
     */
    public long outAmount(long tradeAmount) {

        long blncBefore = this.accoBlnc;
        this.accoBlnc -= tradeAmount;

        addTradeLog(tradeAmount, blncBefore, TradeCd.OUT);

        return this.accoBlnc;
    }

    /**
     * 해지
     */
    public void closeAmount() {

    }


    private void addTradeLog(long tradeAmount, long blncBefore, TradeCd tradeCd) {
        String tradeDate = LocalDate.now().toString();

        TradeLog tradeLog = TradeLog.builder()
                .amount(tradeAmount)
                .blncBefore(blncBefore)
                .blncAfter(this.accoBlnc)
                .tradeCd(tradeCd)
                .tradeDate(tradeDate)
                .accountEntity(this)
                .build();

        this.tradeLogs.add(tradeLog);
        this.lastTrnDt = tradeDate;
    }

}
