package com.prb.erp.utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

	//주민번호만들가(yymmdd1,2)
	public static String getRepreNum(String birthDay, String gd1RelationCd) {
		if ("".endsWith(birthDay) || "".equals(gd1RelationCd)) return null;
		//주민번호 앞자리 생성 ex) 880101
		String[] firstRepreNum = StringUtils.stringSplit(birthDay, "-");
		String firstRepreNum1 = firstRepreNum[0].substring(2, 4);
		//남성인지 여성인지 확인 후 1 또는 2 생성
		if ("0".equals(gd1RelationCd) || "2".equals(gd1RelationCd)) gd1RelationCd = "1";
		else if ("1".equals(gd1RelationCd) || "3".equals(gd1RelationCd)) gd1RelationCd = "2";
		else gd1RelationCd = "0";

		String repreNnm = firstRepreNum1 + firstRepreNum[1] + firstRepreNum[2] + gd1RelationCd;
		return repreNnm;
	}

	public static void main(String[] args) {
		Clock baseClock = Clock.systemDefaultZone();
		Clock clock = Clock.offset(baseClock, Duration.ofHours(72));
		clock = Clock.offset(baseClock, Duration.ofDays(-90));
		Instant instant = Instant.now(clock);
		System.out.println(instant);
	}

}
