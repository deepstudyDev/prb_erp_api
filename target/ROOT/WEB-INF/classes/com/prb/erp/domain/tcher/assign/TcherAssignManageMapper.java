package com.prb.erp.domain.tcher.assign;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface TcherAssignManageMapper extends MyBatisMapper {
    TcherAssignManageVO getTcherAssign(RequestParams<TcherAssignManageVO> vo);
    List<TcherAssignManageVO> getTcherAssignList(RequestParams<TcherAssignManageVO> vo);
    int getTcherAssignListCount(RequestParams<TcherAssignManageVO> vo);
}  