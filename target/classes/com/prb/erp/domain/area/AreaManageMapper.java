package com.prb.erp.domain.area;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface AreaManageMapper extends MyBatisMapper {
    AreaManageVO getArea(RequestParams<AreaManageVO> vo);
    List<AreaManageVO> getAreaList(RequestParams<AreaManageVO> vo);
    List<AreaManageVO> getAreaListAll(RequestParams<AreaManageVO> vo);
    int getAreaListCount(RequestParams<AreaManageVO> vo);
}  