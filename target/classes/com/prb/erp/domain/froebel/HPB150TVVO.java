package com.prb.erp.domain.froebel;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class HPB150TVVO {		
	//교사정보						
    private String PERSON_NUMB;
    private String NAME;	
    private BigDecimal RATE_1;    
    private BigDecimal RATE_2;
    private String PAY_START_YYYYMM;
    private String RATE_MONTH_1;
    private String RATE_MONTH_2;
    private BigDecimal RATE_MGR;    

    private String MGR_START_YYYYMM;
    private BigDecimal ADD_RATE;
    private BigDecimal NRATE1;
    private BigDecimal NRATE2;
    private String WEEKDAY;
}