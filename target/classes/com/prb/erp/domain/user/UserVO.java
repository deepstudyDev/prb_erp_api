package com.prb.erp.domain.user;

import java.time.Instant;
import java.util.List;

import com.chequer.axboot.core.vo.BaseVO;
import com.prb.erp.domain.user.auth.UserAuth;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class UserVO extends BaseVO {
    private String userCd;
    private String userType;
    private String userTypeNm;
    private String decisionYn;
    private String userNm;
    private String userPs;   
    private String userPs2;       

    private String areaCd;
    private String areaNm;
    private String orgClass;
    private String orgCd;
    private String orgNm;
    

    private String rankCd;
    private String rankNm;
    
    private String departmentCdNm;
    private String telNo;    
    private String hpNo;    
    private String email;
    private String birthday;
    private String joinDt;    
    private String outDt;   
    private String marriageYn;
    private String childrenYn;
    private String marriageYnNm;
    private String childrenYnNm;
    private String emergencyNm;
    private String emergencyTel; 
    private String zipcode;	
	private String address1;	
	private String address2;
    private String userStatus;
    private String idStatus;   
    private String userStatusNm;
    private String idStatusNm;    
    //private String lastLoginDate;
    private Instant lastLoginDate;
    private String locale = "ko_KR";
    private String menuGrpCd;    
    private String grpAuthCd;    
    private String grpAuthCdNm;    
    private String useYn;
    private String regUserCd;
    private String regUserNm;	
    private String regDt;     

    private int RowNum;
    private int RowsPerPage;
    private int PageNumber;

    private List<UserAuth> authList; 
}