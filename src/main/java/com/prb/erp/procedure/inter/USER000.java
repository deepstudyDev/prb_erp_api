package com.prb.erp.procedure.inter;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class USER000 {

    private String userCd;

    private String userType;

    private String decisionYn;

    private String userNm;

    private String userPs;

    private String userPs2;

    private String areaCd;

    private String orgClass;

    private String orgCd;

    private String rankCd;

    private String hpNo;

    private String telNo;

    private String birthDay;

    private String joinDt;

    private String outDt;

    private String marriageYn;

    private String childrenYn;

    private String emergencyNm;

    private String emergencyTel;

    private String zipCode;

    private String address1;

    private String address2;

    //재직/퇴사
    private String userStatus;

    private String idStatus;

    private Instant lastLoginDate;

    private String menuGrpCd;

    private String grpAuthCd;

    private String locale = "ko_KR";

    private String useYn = "N";

    private String custCd;

    private String ifYn;
}
