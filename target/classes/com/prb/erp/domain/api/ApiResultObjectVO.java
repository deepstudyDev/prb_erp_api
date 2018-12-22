package com.prb.erp.domain.api;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiResultObjectVO {	
	Object result;
	String resultCode;
	String resultMsg;
}  