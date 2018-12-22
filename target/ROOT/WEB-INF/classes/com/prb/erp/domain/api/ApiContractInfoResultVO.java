package com.prb.erp.domain.api;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiContractInfoResultVO {
	String custCd;
	private List<ApiMemberChildVO> child;
	private String resultCode;
	private String resultMsg;
	
}