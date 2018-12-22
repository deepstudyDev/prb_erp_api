package com.prb.erp.domain.api;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiSendMasterVO {
	private String sendCode;
	private String sendSeq;
	private String sendType;
	private String sendTypeNm;
	private String smsType;
	private String smsTypeNm;
	private String deleteYn;
	private String sendDt;
	private String sendTitle;
	private String sendMessage;
	private String orgCd;
	private String orgNm;
	private String sendUserCd;
	private String sendUserNm;
	private String senderNo;
    private Integer totalByte;
	private String userType;
	private String userTypeNm;
	private String userCd;
	private String userNm;
	private String hpNo;
	private String successYn;
	private String successYnNm;
	
    private String regUserCd;
    private String regUserNm;	
    private String regDt;

    private int rowNum;

    private String sendStartDate;
	private String sendEndDate;

	private String orderColumn;
	private String orderType;
}