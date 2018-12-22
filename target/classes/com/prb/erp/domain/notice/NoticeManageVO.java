package com.prb.erp.domain.notice;

import javax.persistence.Lob;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class NoticeManageVO extends BaseVO {
	private String noticeCd;	
	private String areaCd;	
	private String areaNm;	
    private String orgCd;
    private String orgNm;
	private String noticeTitle;
	private String orgType;

	@Lob
	private String noticeContents;
    private String regUserCd;
    private String regUserNm;	
    private String regDt;
    
    private int RowNum;
    private int RowNum2;
    private int RowsPerPage;
    private int PageNumber;
}