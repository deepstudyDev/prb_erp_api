package com.prb.erp.domain.api;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiBrotherInsertVO {
	private String brotherNm;
	private String brotherSex;
	private String brotherBirthday;
	private String brotherGradeCd;
}