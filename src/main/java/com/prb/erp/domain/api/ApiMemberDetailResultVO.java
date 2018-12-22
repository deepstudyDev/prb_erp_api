package com.prb.erp.domain.api;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiMemberDetailResultVO {

	private ApiMemberHeaderVO header;
	private ApiMemberChildVO child;
	private String resultCode;
	private String resultMsg;
}