package com.prb.erp.domain.tcher.inf.salesEdu;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface TcherInfSalesEduMapper extends MyBatisMapper {
    List<TcherInfSalesEduVO> getTcherSalesEduList(RequestParams<TcherInfSalesEduVO> vo);
}  