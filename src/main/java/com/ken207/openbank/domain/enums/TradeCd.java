package com.ken207.openbank.domain.enums;

import java.util.EnumSet;

public enum TradeCd {
    OPEN, DEPOSIT, WITHDRAW, CLOSE,
    INTEREST;

    public static class IO {
        public static EnumSet<TradeCd> in = EnumSet.of(OPEN, DEPOSIT, INTEREST);
        public static EnumSet<TradeCd> out = EnumSet.of(WITHDRAW, CLOSE);
    }
}
