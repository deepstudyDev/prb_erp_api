package com.prb.erp.domain.tcher;

import javax.persistence.Lob;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class TcherManageVO extends BaseVO {
	private String tcherCd;		
	private String tcherNm;	
    private String tcherType;        
    private String tcherTypeNm;        
	private String tcherTelNo;	    
	private String tcherHpNo;		
	private String tcherEmail;	
    private String tcherEmailType;    
	private String localCd;	
	private String localCdNm;	
	private String tcherBirthday;	
	private String countryCd;		
	private String countryCdNm;		
	private String residenceType;	
	private String residenceTypeNm;	
	private String tcherZipcode;	
	private String tcherAddress1;		
	private String tcherAddress2;		
	private String joinDt;	
	private String outDt;	
	private String outReasonCd;		
	private String outReasonCdNm;		
	private String workCd;	
	private String workCdNm;	
	private String workReasonCd;		
	private String workReasonCdNm;		
	private String workRegCd;	
	private String workRegCdNm;	
	private String orgCd;	
	private String orgNm;	
	private String ccOrgCd;	
	private String ccOrgNm;	
	private String rankCd;	
	private String rankCdNm;	
	private String ccRankCd;	
	private String ccRankCdNm;	

	private String eduDt;	
	private String eduYear;	
	private String eduMonth;	
	private String guaranteeDocCd;	
	private String guaranteeDocCdNm;	
	private String incomeType;	
	private String incomeTypeNm;	
	private String companyType;	
	private String companyTypeNm;	
	private String sectCode;	
	private String sectCodeNm;	
	private String companyRegNum;	
	private String bankCd;	
	private String bankCdNm;	
	private String bankAccountNm;	
	private String bankAccountNo;	    

	@Lob
	private String remark;	

    private int memberCount;
	
    private String regUserCd;
    private String regUserNm;	
    private String regDt;
    private int RowNum;
    private int RowsPerPage;
    private int PageNumber;
}