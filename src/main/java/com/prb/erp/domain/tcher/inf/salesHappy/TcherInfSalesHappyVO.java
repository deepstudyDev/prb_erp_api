package com.prb.erp.domain.tcher.inf.salesHappy;

import java.math.BigDecimal;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class TcherInfSalesHappyVO extends BaseVO {
	private String tcherCd;	
	private String salesYyyymm;	
	private BigDecimal planAmt;		
	private BigDecimal contSaleAmt;	
	private BigDecimal percentage;	
	private BigDecimal wagesSumAmt;	
	private BigDecimal dedSumAmt;
	private BigDecimal realSumAmt;
}