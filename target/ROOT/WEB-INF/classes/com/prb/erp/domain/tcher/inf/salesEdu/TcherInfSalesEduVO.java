package com.prb.erp.domain.tcher.inf.salesEdu;

import java.math.BigDecimal;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class TcherInfSalesEduVO extends BaseVO {
	private String tcherCd;	
	private String salesYyyymm;	
	private BigDecimal planSu;		
	private BigDecimal resultSu;	
	private BigDecimal effectRiz;	
	private BigDecimal gnlAmt;	
	private BigDecimal preAmt;	
	private BigDecimal transAmt;
}