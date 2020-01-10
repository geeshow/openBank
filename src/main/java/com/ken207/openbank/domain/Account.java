package com.ken207.openbank.domain;

import com.ken207.openbank.common.OBDateUtils;
import com.ken207.openbank.domain.enums.*;
import com.ken207.openbank.exception.BizRuntimeException;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name="Account")
@AttributeOverride(name = "id",column = @Column(name = "account_id"))
public class Account extends BaseEntity<Account> {

    private String accountNum; //계좌번호
    private String password; //비밀번호
    private String regDate; //신규일자
    private String closeDate; //해지일자
    private String lastIntsDt; //최종이자계산일자. 이자계산기간의 마지막날(from ~ to에서 to에 해당)
    private long balance;
    private long loanLimitAmount; //대출한도금액
    private long lastTrnSrno; //최종거래일련번호
    private String lastTradeDate; //최종거래일자

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private YesNo loanYn = YesNo.N; //대출계좌여부

    @Enumerated(EnumType.STRING)
    private AccountStatusCode accountStatusCode; //계좌상태코드

    @Enumerated(EnumType.STRING)
    private TaxationCode taxationCode; //과세구분코드

    @Enumerated(EnumType.STRING)
    private ChnlDvcd regChnlDvcd;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "rate_id")
    private Rate basicRate;

    @Builder.Default
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Trade> tradeEntities = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Interest> interestEntities = new ArrayList<>();

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
    public static Account openAccount(Product product, String accountNum, String regDate, TaxationCode taxationCode) {
        Account account = Account.builder()
                .accountNum(accountNum) //계좌번호
                .regDate(regDate) //신규일자
                .reckonDt(regDate) //최종거래일자
                .lastIntsDt(OBDateUtils.addDays(regDate, -1)) //최종이자계산일자
                .taxationCode(taxationCode) //과세구분코드
                .accountStatusCode(AccountStatusCode.ACTIVE)
                .lastTrnSrno(0)
                .lastTradeDate(regDate)
                .balance(0)
                .blncBefore(0)
                .tradeAmount(0)
                .product(product)
                .basicRate(product.getBasicRate())
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
    public Trade deposit(long tradeAmount) {

        if ( OBDateUtils.compareDate(lastTradeDate, getReckonDt()) > 0 ) {
            throw new BizRuntimeException("지정일 이 후 거래가 존재. 기산일 거래를 요청해야 함.");
        }

        this.tradeAmount = tradeAmount;
        this.blncBefore = this.balance;
        this.balance += this.tradeAmount;

        return addTradeLog(TradeCd.DEPOSIT);
    }

    /**
     * 출금
     */
    public Trade withdraw(long tradeAmount) {

        if ( OBDateUtils.compareDate(lastTradeDate, getReckonDt()) > 0 ) {
            throw new BizRuntimeException("지정일 이 후 거래가 존재. 기산일 거래를 요청해야 함.");
        }

        if ( getPosibleOutAmt() < tradeAmount ) {
            throw new BizRuntimeException("출금가능금액 부족");
        }
        this.tradeAmount = tradeAmount;
        this.blncBefore = this.balance;
        this.balance -= this.tradeAmount;

        return addTradeLog(TradeCd.WITHDRAW);
    }

    public long getPosibleOutAmt() {
        if ( this.loanYn == YesNo.Y ) {
            return this.loanLimitAmount + this.balance;
        }
        else {
            return this.balance;
        }
    }

    /**
     * 이자지급
     */
    public Trade payInterest(Interest interest) {

        this.lastIntsDt = interest.getToDate();
        this.tradeAmount = interest.getInterestAmount();
        this.blncBefore = this.balance;
        this.balance += this.tradeAmount;

        return addTradeLog(TradeCd.INTEREST);
    }

    /**
     * 해지
     */
    public Trade closeAccount(Interest interest) {
        if ( this.balance != 0 ) {
            throw new BizRuntimeException("계좌의 잔액이 존재 함.");
        }
        else if ( OBDateUtils.isNotSameDate(this.getLastIntsDt(), this.reckonDt) ) {
            throw new BizRuntimeException("이자 지급 후 해지 해야 함.");
        }
        else if ( YesNo.Y.equals(this.loanYn) ) {
            throw new BizRuntimeException("대출 설정된 계좌.");
        }

        this.closeDate = this.reckonDt;
        this.accountStatusCode = AccountStatusCode.CLOSE;

        return addTradeLog(TradeCd.CLOSE);
    }

    private Trade addTradeLog(TradeCd tradeCd) {

        Trade trade = Trade.builder()
                .srno(++this.lastTrnSrno)
                .amount(this.tradeAmount)
                .blncBefore(this.blncBefore)
                .blncAfter(this.balance)
                .tradeCd(tradeCd)
                .tradeDate(this.getReckonDt())
                .bzDate(OBDateUtils.getToday())
                .account(this)
                .build();

        this.lastTradeDate = this.getReckonDt();
        this.tradeEntities.add(trade);
        return trade;
    }

    public void setReckonDt(String reckonDt) {
        this.reckonDt = reckonDt;
    }

    public String getReckonDt() {
        if ( reckonDt == null ) {
            return OBDateUtils.getToday();
        }

        return reckonDt;
    }

}
