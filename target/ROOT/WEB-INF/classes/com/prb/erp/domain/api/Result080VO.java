package com.prb.erp.domain.api;

import java.util.ArrayList;
import java.util.List;

import com.chequer.axboot.core.utils.ModelMapperUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class Result080VO {
	//마이메뉴 SP080	
	private String visitorTcherNm;	
	private String classTime;
	private String visitTimeCdNm;	
	private String addTime;	
	private String visitNumberCdNm;	
	private String addNumber;	
	private String addContractYn;	
	private String goodsNm;	
	private String subjectNm;	
	private String paymentInfo;	

	private String totalVisitTimeCdNm;	
	private String totalVisitNumberCdNm;

	private String childImgUrl;
	private String childImgRegDt;
	private String childImgBy;
	
	

    public static Result080VO of(Object object) {
        return ModelMapperUtils.map(object, Result080VO.class);
    }
    public static List<Result080VO> of(List<Object> eoList) {
        List<Result080VO> vtoList = new ArrayList<>();

        for (Object object : eoList) {
            vtoList.add(of(object));
        }
        return vtoList;
    }
}