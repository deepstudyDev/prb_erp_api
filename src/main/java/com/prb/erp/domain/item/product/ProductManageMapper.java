package com.prb.erp.domain.item.product;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface ProductManageMapper extends MyBatisMapper {
    ProductManageVO getProductManage(RequestParams<ProductManageVO> vo);
    List<ProductManageVO> getProductManageList(RequestParams<ProductManageVO> vo);
    List<ProductManageVO> getProductManageListAll(RequestParams<ProductManageVO> vo);
    int getProductManageListCount(RequestParams<ProductManageVO> vo);

}  