package com.prb.erp.domain.tcher.trans;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface TcherTransManageMapper extends MyBatisMapper {
    TcherTransManageVO getTcherTrans(RequestParams<TcherTransManageVO> vo);
    List<TcherTransManageVO> getTcherTransList(RequestParams<TcherTransManageVO> vo);
    int getTcherTransListCount(RequestParams<TcherTransManageVO> vo);
}  