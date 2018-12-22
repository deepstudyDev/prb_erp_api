package com.prb.erp.domain.api;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiGoodsManageVO  {
	private String salesType;	
	private String salesTypeNm;	
	private String agreementCd;	
	private String agreementCdNm;	
	private String goodsCd;	
	private String goodsNm;	
	private String goodsSubjectNm;
	private BigDecimal salesPrice;	   
	private BigDecimal visitServicePrice;	   
	private BigDecimal onlineServicePrice;	   

	private String bookServiceYn;		
	private String onlineServiceYn;		
	private String visitServiceYn;		
	private String etcServiceYn;

	/**
	 * 2018-12-20 기획수정으로 추가(안지호)
	 *
 	 */
	private String visitTimeCd;
	private String visitNumberCd;
	
    private int rowNum;
}