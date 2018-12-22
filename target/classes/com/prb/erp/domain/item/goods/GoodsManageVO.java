package com.prb.erp.domain.item.goods;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class GoodsManageVO  {
	private String goodsCd;	
	private String goodsNm;	
	private String salesType;	
	private String salesTypeNm;
	private String agreementCd;	
	private String agreementCdNm;	
	private String goodsClass;
	private String goodsClassNm;
	private String goodsStep1;
	private String goodsStep2;
	private String goodsStep1Nm;
	private String goodsStep2Nm;
	private String goodsCategoryNm;
	private String goodsSubjectNm;
	private String salesYnNm;	
	private String goodsStatusNm;		

	private String bookServiceYn;		
	private String onlineServiceYn;		
	private String visitServiceYn;		
	private String etcServiceYn;		

	private BigDecimal salesCostPrice;
	private BigDecimal salesPrice;	   
	private BigDecimal bookServiceCostPrice;
	private BigDecimal bookServicePrice;	   
	private BigDecimal onlineServiceCostPrice;
	private BigDecimal onlineServicePrice;	   
	private BigDecimal visitServiceCostPrice;
	private BigDecimal visitServicePrice;	   
	private BigDecimal etcServiceCostPrice;
	private BigDecimal etcServicePrice;	  

	private String visitNumberCd;		
	private String visitNumberCdNm;		
	private String visitTimeCd;		
	private String visitTimeCdNm;		
	
    private String regUserCd;
    private String regUserNm;	
    private String regDt;
    
    private int RowNum;
    private int RowsPerPage;
    private int PageNumber;
}