package com.prb.erp.domain.api;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiTcherTransSaveVO {   

    @ApiModelProperty(value="인수인계번호", required = true)
	private String transCd;	
    
    @ApiModelProperty(value="인계교사사번", required = true)
    private String transUserCd;   

    @ApiModelProperty(value="첫수업시작일", required = true)
    private String requestStartDt;   

    @ApiModelProperty(value="첫수업 시간", required = false)
    private String requestStartHour;   

    @ApiModelProperty(value="첫수업 분", required = false)
    private String requestStartTime;   
        
    @ApiModelProperty(value="내용", required = false)
    private String transRemark;        
    
    @ApiModelProperty(value="승인자아이디", required = true)
	private String approvalUserCd;
}