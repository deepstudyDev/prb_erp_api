package com.prb.erp.domain.item.goods;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface GoodsManageRepository extends AXBootJPAQueryDSLRepository<GoodsManage, GoodsManage.GoodsManageId> {
}
