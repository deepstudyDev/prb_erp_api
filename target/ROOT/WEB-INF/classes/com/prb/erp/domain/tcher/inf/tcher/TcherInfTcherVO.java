package com.prb.erp.domain.tcher.inf.tcher;

import java.math.BigDecimal;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class TcherInfTcherVO extends BaseVO {
	private String tcherCd;	
	private BigDecimal rate1;	
	private BigDecimal rate2;		
	private String payStartYyyymm;	
	private String rateMonth1;	
	private String rateMonth2;	
	private BigDecimal rateMgr;
	private String mgrStartYyyymm; 
	private BigDecimal addRate; 
	private BigDecimal nrate1; 
	private BigDecimal nrate2; 
	private String weekday; 
}