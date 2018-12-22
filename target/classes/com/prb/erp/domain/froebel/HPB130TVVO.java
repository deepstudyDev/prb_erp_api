package com.prb.erp.domain.froebel;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class HPB130TVVO {
	//인사정보																					
    private String PERSON_NUMB;
    private String CHARGE_DATE;	
    private String CHARGE_CODE;    
    private String CHARGE_NAME;    
    private String COMPANY_NAME;
    private String SECT_CODE;
    private String SECT_NAME;
    private String HR_DEPT_CODE;
    private String HR_DEPT_NAME;
    
    private String HR_TREE_CODE;
    private String HR_TREE_NAME;
    private String CLASS_CODE;
    private String CLASS_NAME;
    private String WITH_TREE_CODE;
    private String WITH_TREE_NAME;   

    private String WITH_CLASS_CODE;
    private String WITH_CLASS_NAME;
    private String REMARK;
    private String TREE_CODE;
    private String DEPT_CODE;
    private String CREATE_DATE;    
    			
    private String CREATE_USERID;
    private String UPDATE_DATE;
    private String UPDATE_USERID;
    private String CHARGE_DTCD;
    private String CHARGE_DTCD_NAME;

}