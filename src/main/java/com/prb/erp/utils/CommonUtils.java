package com.prb.erp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonUtils {
	
	//오늘날짜
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

	public static<T> List<T> mergeTwoList(List<T> list1, List<T> list2) {
		return Stream.of(list1, list2).flatMap(x -> x.stream()).collect(Collectors.toList());
	}
	
}
