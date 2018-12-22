package com.prb.erp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {
	
	//오늘날짜
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}
	
}
