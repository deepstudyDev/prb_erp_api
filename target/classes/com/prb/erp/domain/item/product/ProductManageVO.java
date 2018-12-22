package com.prb.erp.domain.item.product;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ProductManageVO {
	private String productCd;	
	private String productNm;	
	private String productClass;
	private String productClassNm;
	private String productType;
	private String productTypeNm;
	private String productStep1;
	private String productStep1Nm;
	private String productStep2;
	private String productStep2Nm;
	
	private BigDecimal costPrice;	
	private BigDecimal asPrice;		
    private String regUserCd;
    private String regUserNm;	
    private String regDt;

    private int RowNum;
    private int RowsPerPage;
    private int PageNumber;
    
}