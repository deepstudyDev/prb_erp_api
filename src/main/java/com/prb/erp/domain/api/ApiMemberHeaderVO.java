package com.prb.erp.domain.api;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiMemberHeaderVO {

	private String custCd;		
	
	private String areaCd;		
	private String areaNm;	
	private String orgCd;	
	private String orgNm;	
	private String areaHpNo;	
	
	private String gd1Nm;	
	private String gd1RelationCd;	
	private String gd1RelationCdNm;	
	private String gd1Birthday;	
	private String gd1UserCd;	
	private String gd2Nm;	
	private String gd2RelationCd;	
	private String gd2RelationCdNm;	
	private String gd2Birthday;	
	private String telNo;	
	private String hpNo;		
    private String email;    
    private String emailType;   
	private String homeZipcode;
	private String homeAddress1;
	private String homeAddress2;    
	private String deliveryZipcode;
	private String deliveryAddress1;
	private String deliveryAddress2;
	
	//판매유형
	private String salesType;
	private String salesTypeNm;	
	
	private String goodsCd;		
	private String goodsNm;		
	private String contractDt;	
	private String subjectNm;	
	private String agreementCd;	
	private String agreementCdNm;		
	private BigDecimal totalPrice;	
	private BigDecimal monthPrice;	
	private BigDecimal contractPrice;
	private BigDecimal paymentPrice;	
	
	//계약금 납부방법
	private String contractPaymentWay;	
	private String contractPaymentWayNm;	
	private String contractPaymentMethod;	
	private String contractPaymentMethodNm;		
	
	//납부방법
	private String paymentWay;	
	private String paymentWayNm;	
	private String paymentMethod;	
	private String paymentMethodNm;		
	private String paymentYn;	
	private String paymentYnNm;

	private String restCd;
	private String transCd;

	private String lastBillDt;

	private String bankAccountNm;
	private String bankAccountNo;
	private String bankCd;
	private String withDrawDay;
	
}