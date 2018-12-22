package com.prb.erp.domain.member;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class MemberSchoolVO extends BaseVO {
	private String schoolRegion;		//지역
	private String searchSchulNm;		//학교명
	private String gubun;				//구분/초등/대학등등
	private String perPage;				//한페이지 최대건수
}