package com.prb.erp.domain.api;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiTcherRestSaveVO {   

    @ApiModelProperty(value="학습휴식번호", required = true)
	private String restCd;	
    
    @ApiModelProperty(value="신청년도 : 2018", required = true)
    private String requestYear;
    
    @ApiModelProperty(value="신청월 : 02,03~", required = true)
    private String requestMonth;
    
    @ApiModelProperty(value="신청사유 : 10(학습상황불안) 20(교사불만) 30(기타)", required = true)
	private String restReasonCd;	
    
    @ApiModelProperty(value="신청내용", required = true)    
	private String restRemark;	
    
    @ApiModelProperty(value="승인/취소자아이디", required = true)
	private String approvalUserCd;
}