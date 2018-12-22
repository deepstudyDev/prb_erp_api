package com.prb.erp.domain.area;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class AreaManageVO extends BaseVO {
	private String areaCd;	
	private String areaNm;	
	private String locationCd;	
	private String locationCdNm;	
	private String areaZipcode;
	private String areaAddress1;
	private String areaAddress2;    
	private String areaTelNo;		
	private String areaAsTelNo;		
	private String areaFaxNo;		
	private String companyRegNum;	
	private String remark;
	private String useYn;
    private String regUserCd;
    private String regUserNm;	
    private String regDt;
    
    private int RowNum;
    private int RowsPerPage;
    private int PageNumber;
}