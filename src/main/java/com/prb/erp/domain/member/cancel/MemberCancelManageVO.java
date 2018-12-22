package com.prb.erp.domain.member.cancel;

import java.math.BigDecimal;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class MemberCancelManageVO extends BaseVO {
	private String cancelCd;	
    private String cancelRequestDt;
    private String cancelRequestUserCd;
    private String cancelRequestUserCdNm;
    private String cancelReasonCd;   
    private String cancelReasonCdNm;   
    private String cancelRemark;   
    private String approvalYn;    
    private String approvalYnNm;   
    private String confirmYn;    
    private String confirmYnNm;    
    private String approvalDt;    
    private String approvalUserCd;    
    private String approvalUserNm;    

	private String custCd;	
	private String childCd;	
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
	private String childrenContractDt;
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
	
    private String regUserCd;
    private String regUserNm;	
    private String regDt;
    
    private int RowNum;
    private int RowsPerPage;
    private int PageNumber;
}