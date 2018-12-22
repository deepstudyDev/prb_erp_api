package com.prb.erp.domain.froebel;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class SAM130TVVO {
	//회원::자녀정보
    private String CUST_CODE;
    private String CUST_NAME;	
    private String CHILD_CODE;    
    private String CHILD_NAME;    
    private String CHILD_REPRE_NUM;
    private String CREATE_DATE;
    private String CREATE_USERID;
    private String UPDATE_DATE;
    private String UPDATE_USERID;
}