package com.prb.erp.domain.tcher;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface TcherManageMapper extends MyBatisMapper {
    TcherManageVO getTcher(RequestParams<TcherManageVO> vo);
    List<TcherManageVO> getTcherList(RequestParams<TcherManageVO> vo);
    List<TcherManageVO> getTcherListAll(RequestParams<TcherManageVO> vo);
    int getTcherListCount(RequestParams<TcherManageVO> vo);
}  