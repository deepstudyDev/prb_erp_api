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

    public static void main(String[] args) {
        System.out.println(getYYYYMMDDAtNow());
    }
}
