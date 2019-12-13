package com.ken207.openbank.common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;

public class OBDateUtils {
    public static String getToday() {
        LocalDate localDate = LocalDate.now();
        int yearValue = localDate.getYear();
        int monthValue = localDate.getMonthValue();
        int dayValue = localDate.getDayOfMonth();

        StringBuilder buf = new StringBuilder(8);
        buf.append(yearValue);
        if ( monthValue < 10 ) {
            buf.append("0");
        }
        buf.append(monthValue);
        if ( dayValue < 10 ) {
            buf.append("0");
        }
        buf.append(dayValue);

        return buf.toString();
    }
}
