package com.prb.erp.domain.area.org;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class AreaOrgManageListVO extends BaseVO {
	
    private String areaCd;
    private String areaNm;
    private String org1Cd;
    private String org1Nm;
    private String org2Cd;
    private String org2Nm;
    private String org3Cd;
    private String org3Nm;   
    private String remark;   
    private String orgClass;   

    private String regUserNm;	
    private String regDt;
}