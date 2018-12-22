package com.prb.erp.domain.tcher.inf.salesHappy;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface TcherInfSalesHappyMapper extends MyBatisMapper {
    List<TcherInfSalesHappyVO> getTcherSalesHappyList(RequestParams<TcherInfSalesHappyVO> vo);
}  