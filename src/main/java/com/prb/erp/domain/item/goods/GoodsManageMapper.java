package com.prb.erp.domain.item.goods;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
import org.apache.ibatis.annotations.Param;


public interface GoodsManageMapper extends MyBatisMapper {
    List<GoodsManageVO> getGoodsManageList(RequestParams<GoodsManageVO> vo);
    List<GoodsManageVO> getGoodsManageListAll(RequestParams<GoodsManageVO> vo);
    int getGoodsManageListCount(RequestParams<GoodsManageVO> vo);
    String getGoodsName(@Param("goodsCd") String goodsCd);
}  