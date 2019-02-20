package com.prb.erp.utils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    //구분자로 문자열 자르기
    public static String[] stringSplit(String str, String regx) {
        String[] array = null;
        if (!"".equals(str)) array = str.split(regx);
        return array;
    }

    public static boolean containsStrList(List<String>strList, String checkStr) {
        if (strList.size() == 0 && "".equals(checkStr)) return false;

        for (String str : strList) {
            if (checkStr.contains(str)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        List<String>list = new ArrayList<>();
        list.add("teacher");
        list.add("bb");
        boolean bl = containsStrList(list, "abc");
        System.out.println(bl);
    }
}
