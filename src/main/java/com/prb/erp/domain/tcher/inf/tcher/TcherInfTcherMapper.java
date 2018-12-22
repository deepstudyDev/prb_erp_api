package com.prb.erp.domain.tcher.inf.tcher;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface TcherInfTcherMapper extends MyBatisMapper {
    List<TcherInfTcherVO> getTcherInfoList(RequestParams<TcherInfTcherVO> vo);
}  