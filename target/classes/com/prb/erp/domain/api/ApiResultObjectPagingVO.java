package com.prb.erp.domain.api;

import java.util.List;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiResultObjectPagingVO {
	private List<?> result;
	private int totalCnt;			// 전체레코드수
	private int pageNumber;		//현재페이지
	private String resultCode;
	private String resultMsg; 
	
	//회원
    private String todayMemberCount;
    private String totalMemberCount;
}