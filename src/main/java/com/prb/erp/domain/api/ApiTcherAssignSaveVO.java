package com.prb.erp.domain.api;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiTcherAssignSaveVO {   
    @ApiModelProperty(value="계약코드", required = true)
	private String custCd;	
    
    @ApiModelProperty(value="자녀계약코드", required = true)
	private String childCd;	
    
    @ApiModelProperty(value="요청자", required = true)
	private String assignRequestUserCd;	
    
    @ApiModelProperty(value="내용", required = true)
	private String assignRemark;	
    
    @ApiModelProperty(value="배정교사 사번", required = true)    
	private String assignUserCd;	
    
    @ApiModelProperty(value="배정구분: 10 일반 20 인수인계 30 학습휴식신청", required = true)
	private String assignType;
}