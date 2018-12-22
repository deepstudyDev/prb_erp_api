package com.prb.erp.domain.sms.master;

import lombok.Data;

@Data
public class SendSmsUserListVO {
	private String userType;
	private String userTypeNm;
	private String userNm;
	private String userCd;
	private String hpNo;
	
    private int RowNum;
    private int RowsPerPage;
    private int PageNumber;
}
