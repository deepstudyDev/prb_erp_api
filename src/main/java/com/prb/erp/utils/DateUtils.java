package com.prb.erp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String returnNowDateByYyyymmddhhmmss() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(today);
    }

    public static String getYYYYMMDDAtNow() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(today);
    }

    public static String splitDate(String YYYY_MM_DD) {
        if ("".equals(YYYY_MM_DD)) return null;
        String[] dateStr = StringUtils.stringSplit(YYYY_MM_DD, "-");
        String yyyymmdd = dateStr[0] + dateStr[1] + dateStr[2];
        return yyyymmdd;
    }

    public static void main(String[] args) {
        System.out.println(splitDate("2011-01-01"));
    }
}
