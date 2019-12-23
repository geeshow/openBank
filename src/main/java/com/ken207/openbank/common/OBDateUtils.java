package com.ken207.openbank.common;

import java.time.LocalDate;

public class OBDateUtils {
    public static String MAX_DATE = "99991231";
    public static String getToday() {
        return parseString(LocalDate.now());
    }

    public static String addDays(String date, long days) {
        return parseString(
                parseLocalDate(date).plusDays(days)
        );
    }

    public static LocalDate parseLocalDate(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int dayOfMonth = Integer.parseInt(date.substring(6,8));
        return LocalDate.of(year, month, dayOfMonth);
    }

    public static String parseString(LocalDate localDate) {
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

    /**
     *
     * @param sourceDate
     * @param targetDate
     * @return 두 값이 같은 경우 return 0
     * sourceDate > targetDate 일 경우 return 0 보다 큰 값
     * sourceDate < targetDate 일 경우 return 0 보다 작은 값
     */
    public static int compareDate(String sourceDate, String targetDate) {
        return sourceDate.compareTo(targetDate);
    }
}
