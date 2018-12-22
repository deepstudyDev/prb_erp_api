package com.prb.erp.domain.api;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiTcherRestRequestVO {   

    @ApiModelProperty(value="계약코드", required = true)
	private String custCd;	

    @ApiModelProperty(value="자녀계약코드", required = true)
	private String childCd;	

    @ApiModelProperty(value="신청년도 : 2018", required = true)
    private String requestYear;
    
    @ApiModelProperty(value="신청월 : 02,03~", required = true)
    private String requestMonth;
    
    @ApiModelProperty(value="신청자 아이디", required = true)
	private String restRequestUserCd;	
    
    @ApiModelProperty(value="신청사유 : 10(학습상황불안) 20(교사불만) 30(기타)", required = true)
	private String restReasonCd;	
    
    @ApiModelProperty(value="신청내용", required = true)    
	private String restRemark;	
}