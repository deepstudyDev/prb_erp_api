package com.prb.erp.domain.api;

import java.math.BigDecimal;

import com.wordnik.swagger.annotations.ApiImplicitParam;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiMemberChildVO {

	private String custCd;	
	private String childCd;

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

	private String homeZipcode;
	private String homeAddress1;
	private String homeAddress2;

	private String childImgUrl;
	private String childImgRegDt;
	private String childImgBy;
	
	//자녀정보
	private String childrenContractDt;	
	private String contractType;
	private String contractTypeNm;		
	private String childrenUserCd;	
	private String childrenNm;	
	private String childrenSex;	
	private String childrenSexNm;	
	private String childrenBirthday;	
	private String childrenHpNo;	
	private String childrenSchoolNm;	
	private String childrenGradeCd;	
	private String childrenGradeCdNm;	
	private String onlineServiceStatus;	
	private String onlineServiceStatusNm;	
	private String onlineServiceYn;		
	private String onlineServiceYnNm;	
	
	private BigDecimal onlineServicePrice;		
	private String visitServiceStatus;	
	private String visitServiceStatusNm;	
	private String visitServiceYn;	
	private String visitServiceYnNm;	
	private String visitTimeCd;		
	private String visitTimeCdNm;		
	private String visitNumberCd;		
	private String visitNumberCdNm;			
	private BigDecimal visitServicePrice;		

	private String onlinePaymentWay;	
	private String onlinePaymentWayNm;	
	private String visitPaymentWay;			
	private String visitPaymentWayNm;			
	private String onlinePaymentMethod;			
	private String onlinePaymentMethodNm;
	private String visitPaymentMethod;			
	private String visitPaymentMethodNm;	
	
	private String visitStartDt;	
	private String visitEndDt;
	
	//교사정보
	private String counselor1TcherCd;	
	private String counselor1TcherNm;	
	private String counselor1TcherHpNo;	
	
	private String counselor2TcherCd;	
	private String counselor2TcherNm;	
	private String counselor2TcherHpNo;	
	
	private String visitorTcherCd;	
	private String visitorTcherNm;	
	private String visitorTcherHpNo;	
	private String remark;	
	

	//option
	private String totalVisitTimeCdNm;	
	private String totalVisitNumberCdNm;	
	private String agreementCdNm;
	private String agreementEndDt;	
	private String goodsNm;		
	private String subjectNm;	
	private String addPrice;	
	
	private String totalPrice;	
	private String monthPrice;
	private String splitPayment;	
	private String paymentWayNm;	
	private String paymentMethodNm;		
	
	private String addTime;	
	private String addNumber;	
	private String addContractYn;	
	private String paymentTypeNm;
	private String paymentDt;
	private String payInfo;	
	private String billDt;	
	private String paymentMonth;	
	private String paymentRemainMonth;	
	private String remainPrice;	
	private String paymentInfo;
	private String contractDt;
	private int agreementCd;
}