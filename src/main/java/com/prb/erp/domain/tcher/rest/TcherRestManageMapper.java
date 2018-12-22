package com.prb.erp.domain.tcher.rest;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface TcherRestManageMapper extends MyBatisMapper {
    TcherRestManageVO getTcherRest(RequestParams<TcherRestManageVO> vo);
    List<TcherRestManageVO> getTcherRestList(RequestParams<TcherRestManageVO> vo);
    int getTcherRestListCount(RequestParams<TcherRestManageVO> vo);
}  