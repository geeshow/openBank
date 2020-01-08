package com.ken207.openbank.common;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class OBDateUtils {
    public static final String MIN_DATE = "19550101";
    public static final String MAX_DATE = "99991231";


    public static String getToday() {
        return parseString(LocalDate.now());
    }

    public static String getYesterday() {
        return parseString(
                LocalDate.now().plusDays(-1)
        );
    }

    public static String addDays(String date, long days) {
        return parseString(
                parseLocalDate(date).plusDays(days)
        );
    }

    public static String
    addDaysFromNow(long days) {
        return parseString(
                parseLocalDate(getToday()).plusDays(days)
        );
    }

    public static LocalDate parseLocalDate(String date) {
        int year = Integer.parseInt(date.substring(0,4));
        int month = Integer.parseInt(date.substring(4,6));
        int dayOfMonth = Integer.parseInt(date.substring(6,8));

        Date a = new Date();

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

    /**
     *
     * @param sourceDate
     * @param targetDate
     * @return 두 값이 같은 경우 return true
     */
    public static boolean isSameDate(String sourceDate, String targetDate) {
        return sourceDate.compareTo(targetDate) == 0;
    }

    /**
     *
     * @param sourceDate
     * @param targetDate
     * @return 두 값이 다를 경우 return true
     */
    public static boolean isNotSameDate(String sourceDate, String targetDate) {
        return sourceDate.compareTo(targetDate) != 0;
    }

    /**
     * Compare date between sourceDate and baseDate
     * date pattern : yyyymmdd
     * true : sourceDate <= baseDate
     * @param sourceDate
     * @param baseDate
     * @return It is true, if sourceDate is early or same then baseDate, otherwise it is false
     */
    public static boolean isLeftEarlierOrSame(String sourceDate, String baseDate) {
        return OBDateUtils.compareDate(sourceDate, baseDate) <= 0;
    }

    /**
     * Compare date between sourceDate and baseDate
     * date pattern : yyyymmdd
     * true : sourceDate < baseDate
     * @param sourceDate
     * @param baseDate
     * @return It is true, if sourceDate is early then baseDate, otherwise it is false
     */
    public static boolean isLeftEarlier(String sourceDate, String baseDate) {
        return OBDateUtils.compareDate(sourceDate, baseDate) < 0;
    }

    /**
     * Compare date between sourceDate and baseDate
     * date pattern : yyyymmdd
     * true : sourceDate >= baseDate
     * @param sourceDate
     * @param baseDate
     * @return It is true, if sourceDate is later or same then baseDate, otherwise it is false
     */
    public static boolean isLeftLaterOrSame(String sourceDate, String baseDate) {
        return OBDateUtils.compareDate(sourceDate, baseDate) >= 0;
    }

    /**
     * Compare date between sourceDate and baseDate
     * date pattern : yyyymmdd
     * true : sourceDate > baseDate
     * @param sourceDate
     * @param baseDate
     * @return It is true, if sourceDate is later then baseDate, otherwise it is false
     */
    public static boolean isLeftLater(String sourceDate, String baseDate) {
        return OBDateUtils.compareDate(sourceDate, baseDate) > 0;
    }

    public static boolean isBetween(String sourceDate, String fromDate, String toDate) {
        return isLeftLaterOrSame(sourceDate, fromDate) && isLeftEarlierOrSame(sourceDate, toDate);
    }

    /**
     * 두 날짜의 일수 차이를 계산.
     * 한편으로 계산 함. (ex. from 20190202, to 20190203 -> result 1)
     * from/to 날짜가 같을 경우 0을 리턴 함.
     * @param from
     * @param to
     * @return 두 날짜의 일수 차이. 음수 포함.
     */
    public static int getNumberOfDays(String from, String to) {
        int yearOfFrom = Integer.parseInt(from.substring(0,4));
        int monthOfFrom = Integer.parseInt(from.substring(4,6)) - 1;
        int dayOfFrom = Integer.parseInt(from.substring(6,8));

        int yearOfTo = Integer.parseInt(to.substring(0,4));
        int monthOfTo = Integer.parseInt(to.substring(4,6)) - 1;
        int dayOfTo = Integer.parseInt(to.substring(6,8));

        Calendar calendar = Calendar.getInstance();
        calendar.set(yearOfFrom, monthOfFrom, dayOfFrom);
        long timeInMillisFrom = calendar.getTimeInMillis();

        calendar.set(yearOfTo, monthOfTo, dayOfTo);
        long timeInMillisTo = calendar.getTimeInMillis();
        Double result = Math.ceil((timeInMillisTo - timeInMillisFrom) / (24 * 60 * 60 * 1000));
        return result.intValue();
    }

    /**
     * 두 날짜의 일수 차이를 계산.
     * 양편으로 계산 함. (ex. from 20190202, to 20190203 -> result 2)
     * from/to 날짜가 같을 경우 1을 리턴 함.
     * @param from
     * @param to
     * @return 두 날짜의 일수 차이. 결과 > 0. 결과는 절대값으로 음수와 0은 포함 안됨.
     */
    public static int getNumberOfDaysInclude(String from, String to) {
        int numberOfDays = Math.abs(getNumberOfDays(from, to));
        return numberOfDays + 1;
    }
}
