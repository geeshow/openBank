package com.ken207.openbank.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class OBDateUtilsTest {

    @Test
    @TestDescription("날짜 비교 테스트")
    public void dateCompare() throws Exception {
        //given
        String date1 = "20191010";
        String date2 = "20201211";

        //when
        int result1 = OBDateUtils.compareDate(date1, date1);
        int result2 = OBDateUtils.compareDate(date1, date2);
        int result3 = OBDateUtils.compareDate(date2, date1);
        int result4 = OBDateUtils.compareDate(date2, date2);

        //then
        assertTrue(result1 == 0);
        assertTrue(result2 < 0);
        assertTrue(result3 > 0);
        assertTrue(result4 == 0);
    }
}