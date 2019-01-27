package com.prb.erp.utils;

public class StringUtils {

    //구분자로 문자열 자르기
    public static String[] stringSplit(String str, String regx) {
        String[] array = null;
        if (!"".equals(str)) array = str.split(regx);
        return array;
    }
}
