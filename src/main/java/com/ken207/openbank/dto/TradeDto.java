package com.ken207.openbank.dto;

import com.ken207.openbank.domain.enums.TaxationCode;
import com.ken207.openbank.domain.enums.TradeCd;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public class TradeDto {

    @Builder
    @Getter @Setter
    public static class RequestDeposit {
        private String tradeDate;
        private long amount;
    }

    @Builder
    @Getter @Setter
    public static class Response {
        private long srno;
        private String tradeDate;
        private String bzDate;
        private long amount;
        private long blncBefore;
        private long blncAfter;
        private TradeCd tradeCd;
    }
}
