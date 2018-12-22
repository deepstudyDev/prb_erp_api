package com.prb.erp.domain.api;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiSmsMasterVO {   
    
    @ApiModelProperty(value="발송유형 10:즉시발송", required = true,allowableValues = "10")
    @NotEmpty(message = "발송유형 입력하세요.")
	private String sendType;
    
    @ApiModelProperty(value="SMS/LMS", required = true,allowableValues = "SMS,LMS")
    @NotEmpty(message = "SMS/LMS")
	private String smsType;	
    
    @ApiModelProperty(value="발송일(YYYY-MM-DD)", required = true)
	private String sendDt;	
    
    @ApiModelProperty(value="내용 (length=4000)", required = true)
	private String sendMessage;
    
    @ApiModelProperty(value="내용바이트수", required = true)
    private Integer totalByte;
    
    @ApiModelProperty(value="발송자아이디", required = true)
	private String senderUserCd;
    
    @ApiModelProperty(value="보낸 전화번호(000-0000-0000)", required = true)
	private String senderNo;
    
    @ApiModelProperty(value="받는사람 아이디(로그인아이디)", required = true)
	private String userCd;
    
    @ApiModelProperty(value="받는사람 전화번호(YYYY-MM-DD)", required = true)
    private String hpNo;
}