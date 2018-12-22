package com.prb.erp.domain.froebel;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class HPB201TVVO {
	//매출정보 :: 상담					
    private String PERSON_NUMB;
    private String SALES_YYYYMM;	
    private BigDecimal PLAN_SU;    
    private BigDecimal RESULT_SU;
    private BigDecimal EFFECT_RIZ;
    private BigDecimal GNL_AMT;
    private BigDecimal PRE_AMT;
    private BigDecimal TRANS_AMT;

    private BigDecimal PLAN_AMT;
    private BigDecimal CONT_SALE_AMT;
    private BigDecimal PERCENTAGE;
    private BigDecimal WAGES_SUM_AMT;
    private BigDecimal DED_SUM_AMT;
    private BigDecimal REAL_SUM_AMT;
    
    
}