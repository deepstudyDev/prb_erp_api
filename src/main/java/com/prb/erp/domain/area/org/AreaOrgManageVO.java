package com.prb.erp.domain.area.org;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class AreaOrgManageVO extends BaseVO {
    private String areaCd;
    private String orgClass;
    private String orgDepth;
    private String parentOrgCd;
    private String orgCd;
    private String orgNm;
    private String orgLv;
    private String remark;
	private int childCount;
	private String useYn;
    private String regUserNm;	
    private String regDt;
}