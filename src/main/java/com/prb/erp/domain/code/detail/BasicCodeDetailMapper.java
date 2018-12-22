package com.prb.erp.domain.code.detail;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface BasicCodeDetailMapper extends MyBatisMapper {
    List<BasicCodeDetailVO> getAllByCodeMap(RequestParams<BasicCodeDetailVO> basicCodeDetailVO);

}  