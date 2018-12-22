package com.prb.erp.domain.api;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiMemberModifyCustVO {
	//방문용
    @ApiModelProperty(value="부모아이디", required = true)
	private String gd1UserCd;	
    @ApiModelProperty(value="자녀아이디", required = true)    
	private String childrenUserCd;        
    @ApiModelProperty(value="계약자명", required = true)
	private String gd1Nm;	
    @ApiModelProperty(value="계약자관계", required = true)
	private String gd1RelationCd;	
    @ApiModelProperty(value="계약자생년월일", required = false)
	private String gd1Birthday;	
    @ApiModelProperty(value="보호자2명", required = false)
	private String gd2Nm;	
    @ApiModelProperty(value="보호자2관계", required = true)
	private String gd2RelationCd;	
    @ApiModelProperty(value="보호자2생년월일", required = true)
	private String gd2Birthday;	
    @ApiModelProperty(value="휴대전화", required = true)
	private String hpNo;		
    @ApiModelProperty(value="이메일", required = true)
    private String email;        
    @ApiModelProperty(value="집우편번호", required = true)
	private String homeZipcode;
    @ApiModelProperty(value="집주소1", required = true)
	private String homeAddress1;
    @ApiModelProperty(value="집주소2", required = true)
	private String homeAddress2;    
    @ApiModelProperty(value="배송우편번호", required = true)
	private String deliveryZipcode;
    @ApiModelProperty(value="배송주소1", required = true)
	private String deliveryAddress1;
    @ApiModelProperty(value="배송주소2", required = true)
	private String deliveryAddress2;	

    @ApiModelProperty(value="자녀명", required = true)
	private String childrenNm;	
    @ApiModelProperty(value="자녀휴대전화", required =false)
	private String childrenHpNo;	
    @ApiModelProperty(value="학교명", required = false)
	private String childrenSchoolNm;	
    @ApiModelProperty(value="자녀생일", required = false)
	private String childrenBirthday;	
    @ApiModelProperty(value="자녀학년", required = false)
	private String childrenGradeCd;
    @ApiModelProperty(value="집 전화번호", required = false)
    private String telNo;
}