package com.prb.erp.domain.area.org;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface AreaOrgManageMapper extends MyBatisMapper {
    List<AreaOrgManageVO> getAreaOrgList(RequestParams<AreaOrgManageVO> vo);
    List<AreaOrgManageListVO> getAreaOrgAllList10(RequestParams<AreaOrgManageListVO> vo);
    List<AreaOrgManageListVO> getAreaOrgAllList20(RequestParams<AreaOrgManageListVO> vo);
    List<AreaOrgManageListVO> getAreaOrgAllList30(RequestParams<AreaOrgManageListVO> vo);
}  