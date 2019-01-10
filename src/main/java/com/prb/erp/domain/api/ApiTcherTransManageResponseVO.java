package com.prb.erp.domain.api;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiTcherTransManageResponseVO {   
	private String transCd;	
    private String transRequestDt;
    private String transRequestUserCd;
    private String transRequestUserNm;
    
    private String transPrevUserCd;
    private String transPrevUserNm;
    private String transUserCd;
    private String transUserNm; 
    
	private String transHopeDt;	
	private String transReasonCd;	
	private String transReasonCdNm;	
	
	private String moveDt;	
	private String zipcode;	
    private String address1;    
    private String address2;    
    private String transRemark;    
    private String approvalYn;    
    private String approvalYnNm;   
    private String confirmYn;    
    private String confirmYnNm;    

	private String custCd;	
	private String gd1Nm;	
	private String gd1RelationCd;	
	private String gd1RelationCdNm;	
	private String gd1UserCd;	
	private String gd1Birthday;	
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
	private String deliveryZipcode;
	private String deliveryAddress1;
	private String deliveryAddress2;
	private String childCd;
	private String contractDt;	
	private String goodsCd;		
	private String goodsNm;		
	private String subjectNm;	
	private String agreementCd;	
	private String agreementCdNm;	
	private BigDecimal totalPrice;	
	private BigDecimal monthPrice;	
	private BigDecimal contractPrice;
	private String contractPaymentWay;	
	private String contractPaymentWayNm;	
	private String contractPaymentMethod;	
	private String contractPaymentMethodNm;	
	private BigDecimal paymentPrice;	
	private String paymentWay;	
	private String paymentWayNm;	
	private String paymentMethod;	
	private String paymentMethodNm;		
	private String paymentYn;	
	private String paymentYnNm;	
	private String contractType;
	private String contractTypeNm;	
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
	
	private String areaCd;		
	private String areaNm;	
	private String orgCd;	
	private String orgNm;	
	private String areaHpNo;	
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
	private String addContractYn;
	
    private String regUserCd;
    private String regUserNm;	
    private String regDt;
}