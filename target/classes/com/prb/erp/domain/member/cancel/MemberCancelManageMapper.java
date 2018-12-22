package com.prb.erp.domain.member.cancel;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface MemberCancelManageMapper extends MyBatisMapper {
	MemberCancelManageVO getMemberCancel(RequestParams<MemberCancelManageVO> vo);
    List<MemberCancelManageVO> getMemberCancelList(RequestParams<MemberCancelManageVO> vo);
    int getMemberCancelListCount(RequestParams<MemberCancelManageVO> vo);
}  