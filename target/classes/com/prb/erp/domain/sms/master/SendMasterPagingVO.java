package com.prb.erp.domain.sms.master;

import java.util.List;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class SendMasterPagingVO extends BaseVO {
	private List<SendMasterVO> result;
	protected int totalCnt;	// 전체레코드수
	protected int dataSize = 10;	// 페이지당 보여줄 데이타수
	protected int pageSize = 5;	//페이지 그룹범위 1,2,3,4,5
	protected int pageNo;		//현재페이지
}