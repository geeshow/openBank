package com.ken207.openbank.common;

import org.junit.Assert;
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

    @Test
    @TestDescription("해당 날짜가 특정 기간에 포함여부를 확인하는 테스트")
    public void dateBetween() throws Exception {
        //given

        //when
        boolean result1 = OBDateUtils.isBetween("20190101", "20190101", "20190101");
        boolean result2 = OBDateUtils.isBetween("20190101", "20181231", "20190101");
        boolean result3 = OBDateUtils.isBetween("20190101", "20190101", "20191231");
        boolean result4 = OBDateUtils.isBetween("20190101", "20201231", "20101231");

        //then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
        Assert.assertTrue(result3);
        Assert.assertFalse(result4);
    }


    @Test
    public void isLeftEarlierOrSame() throws Exception {
        //given

        //when
        boolean result1 = OBDateUtils.isLeftEarlierOrSame("20190101", "20190101");
        boolean result2 = OBDateUtils.isLeftEarlierOrSame("20190101", "20190102");
        boolean result3 = OBDateUtils.isLeftEarlierOrSame("20190331", "20190401");
        boolean result4 = OBDateUtils.isLeftEarlierOrSame("20190102", "20190101");
        boolean result5 = OBDateUtils.isLeftEarlierOrSame("20190401", "20190331");

        //then
        Assert.assertTrue(result1);
        Assert.assertTrue(result2);
        Assert.assertTrue(result3);
        Assert.assertFalse(result4);
        Assert.assertFalse(result5);
    }

    @Test
    public void isLeftEarlier() throws Exception {
        //given

        //when
        boolean result1 = OBDateUtils.isLeftEarlier("20190101", "20190101");
        boolean result2 = OBDateUtils.isLeftEarlier("20190101", "20190102");
        boolean result3 = OBDateUtils.isLeftEarlier("20190331", "20190401");
        boolean result4 = OBDateUtils.isLeftEarlier("20190102", "20190101");
        boolean result5 = OBDateUtils.isLeftEarlier("20190401", "20190331");

        //then
        Assert.assertFalse(result1);
        Assert.assertTrue(result2);
        Assert.assertTrue(result3);
        Assert.assertFalse(result4);
        Assert.assertFalse(result5);
    }

    @Test
    public void isLeftLaterOrSame() throws Exception {
        //given

        //when
        boolean result1 = OBDateUtils.isLeftLaterOrSame("20190101", "20190101");
        boolean result2 = OBDateUtils.isLeftLaterOrSame("20190101", "20190102");
        boolean result3 = OBDateUtils.isLeftLaterOrSame("20190331", "20190401");
        boolean result4 = OBDateUtils.isLeftLaterOrSame("20190102", "20190101");
        boolean result5 = OBDateUtils.isLeftLaterOrSame("20190401", "20190331");

        //then
        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
        Assert.assertFalse(result3);
        Assert.assertTrue(result4);
        Assert.assertTrue(result5);
    }


    @Test
    public void isLeftLater() throws Exception {
        //given

        //when
        boolean result1 = OBDateUtils.isLeftLater("20190101", "20190101");
        boolean result2 = OBDateUtils.isLeftLater("20190101", "20190102");
        boolean result3 = OBDateUtils.isLeftLater("20190331", "20190401");
        boolean result4 = OBDateUtils.isLeftLater("20190102", "20190101");
        boolean result5 = OBDateUtils.isLeftLater("20190401", "20190331");

        //then
        Assert.assertFalse(result1);
        Assert.assertFalse(result2);
        Assert.assertFalse(result3);
        Assert.assertTrue(result4);
        Assert.assertTrue(result5);
    }
}