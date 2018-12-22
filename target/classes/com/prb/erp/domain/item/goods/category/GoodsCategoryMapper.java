package com.prb.erp.domain.item.goods.category;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface GoodsCategoryMapper extends MyBatisMapper {
    List<GoodsCategoryVO> getCategoryProduct(RequestParams<GoodsCategoryVO> vo);
}  