package com.prb.erp.domain.tcher.inf.charge;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class TcherInfChargeVO extends BaseVO {
	private String tcherCd;	
	private String chargeDt;	
	private String chargeCode;	
	private String chargeCodeNm;	
	private String companyName;	
	private String sectCode;	
	private String sectCodeNm;	
	private String orgCd;	
	private String orgNm;	
	private String rankCd;
	private String rankCdNm;
	private String ccOrgCd;
	private String ccOrgNm;
	private String ccRankCd;
	private String ccRankCdNm;
	private String chargeDtcd;
	private String chargeDtcdNm;
	private String remark;	
}