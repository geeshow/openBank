package com.ken207.openbank.dto.request.dto;

import com.ken207.openbank.domain.enums.TradeCd;
import lombok.*;

public class TradeDto {

    @Builder
    @Getter @Setter
    public static class RequestDeposit {
        private String tradeDate;
        private long amount;
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
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
