package com.prb.erp.domain.api;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiBrotherVO {
	private String custCd;	
	private String childCd;	
	
	private String brotherCd;	
	private String brotherNm;
	private String brotherSex;
	private String brotherSexNm;
	private String brotherBirthday;
	private String brotherGradeCd;
	private String brotherGradeCdNm;
}