package com.prb.erp.domain.api;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiBrotherRequestVO {
	private String custCd;	
	private String childCd;	
	private String brothers;	
}