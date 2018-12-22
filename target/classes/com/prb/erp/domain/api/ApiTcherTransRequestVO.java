package com.prb.erp.domain.api;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiTcherTransRequestVO {   

    @ApiModelProperty(value="계약코드", required = true)
	private String custCd;	

    @ApiModelProperty(value="자녀계약코드", required = true)
	private String childCd;	
    
    @ApiModelProperty(value="요청자아이디", required = true)
    private String transRequestUserCd;

    @ApiModelProperty(value="인계교사아이디", required = true)
    private String transPrevUserCd;        

    @ApiModelProperty(value="인계희망일", required = true)
	private String transHopeDt;	

    @ApiModelProperty(value="사유(10	관리지역변경,20	교사요청,30	회원이사,40	기타)", required = true)
	private String transReasonCd;	
	
    @ApiModelProperty(value="이사일", required = false)
	private String moveDt;	

    @ApiModelProperty(value="우편번호", required = false)
	private String zipcode;	

    @ApiModelProperty(value="주소1", required = false)
    private String address1;    

    @ApiModelProperty(value="주소2", required = false)
    private String address2;   
    
    @ApiModelProperty(value="내용", required = false)
    private String transRemark;        
}