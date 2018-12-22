package com.prb.erp.domain.api;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor

public class ApiUserVO {
	
    @NotEmpty(message = "id를 입력하세요.")
    private String userCd;

    @NotEmpty(message = "유저유형을 입력하세요.")
    private String userType;    
    private String userTypeNm;
    private String userNm;

    private String birthday;
    private String zipcode;
    private String address1;
    private String address2;
    
    private String areaCd;
    private String areaNm;
    private String orgCd;
    private String orgNm;
    private String hpNo;    
    private String telNo;    
    private String email;    
    private String joinDt;
    private String outDt;
    private String workCd;
    private String workCdNm;    
    private String userStatus;  
    private String userStatusNm;
    private String decisionYn;
    private String lastLoginDate;
}