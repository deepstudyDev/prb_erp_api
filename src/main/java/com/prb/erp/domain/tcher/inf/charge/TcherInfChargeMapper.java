package com.prb.erp.domain.tcher.inf.charge;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface TcherInfChargeMapper extends MyBatisMapper {
    List<TcherInfChargeVO> getTcherInfChargeList(RequestParams<TcherInfChargeVO> vo);
}  