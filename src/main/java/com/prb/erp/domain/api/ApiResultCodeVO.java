package com.prb.erp.domain.api;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiResultCodeVO {	
	String keyCd;
	String keyValue;	
	String resultCode;
	String resultMsg;
	String froebelCustCd;
}