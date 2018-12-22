package com.prb.erp.domain.froebel;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class SAM100TVVO {
	//회원::기본정보					
    private String CUST_CODE;
    private String CUST_GUBUN;	
    private String CUST_GUBUN_NAME;    
    private String AREA_CODE;    
    private String TREE_CODE;
    private String TREE_NAME;
    private String DEPT_CODE;
    private String DEPT_NAME;
    private String REPRE_NUM;
    private String CUST_NAME;
    private String SUB_CUST_NAME;
    
    private String SUB_HP_NO;
    private String HP_NO;
    private String TEL_NO;
    private String EMAIL;    
    private String ZIP_NO;
    private String ADDR;
    private String ADDR2;
    
    private String DEL_ZIP_NO;
    private String DEL_ADDR;
    private String DEL_ADDR2;   
    private String USE_YN;
    private String CREATE_DATE;
    private String CREATE_USERID;
    private String UPDATE_DATE;
    private String UPDATE_USERID;
}