package com.prb.erp.domain.api;

import java.util.ArrayList;
import java.util.List;

import com.chequer.axboot.core.utils.ModelMapperUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class Result081VO {
	//결제정보(기본결제정보) SP081_1
	private String childrenContractDt;	
	private String agreementCdNm;
	private String agreementEndDt;	

	private String goodsNm;	
	private String subjectNm;	
	private String visitNumberCdNm;	
	private String visitTimeCdNm;	
	private String addContractYn;	
	private String addNumber;	
	private String addTime;	
	private String addPrice;	
	
	private String totalPrice;	
	private String monthPrice;
	private String paymentDt;
	private String paymentTypeNm;
	private String splitPayment;	
	private String paymentWayNm;	
	private String paymentMethodNm;	
	private String payInfo;	
	private String billDt;	
	private String paymentMonth;	
	private String paymentRemainMonth;	
	private String remainPrice;	
	private String paymentInfo;

	private String contractDt;
	private int agreementCd;
	
	


    public static Result081VO of(Object object) {
        return ModelMapperUtils.map(object, Result081VO.class);
    }
    public static List<Result081VO> of(List<Object> eoList) {
        List<Result081VO> vtoList = new ArrayList<>();

        for (Object object : eoList) {
            vtoList.add(of(object));
        }
        return vtoList;
    }
}