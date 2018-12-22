package com.prb.erp.domain.sms.master;

import java.util.List;

import com.prb.erp.domain.sms.detail.SendDetail;

import lombok.Data;

@Data
public class SendRequestVO {

	private SendMaster master;
	private List<SendDetail> detail;
	
}
