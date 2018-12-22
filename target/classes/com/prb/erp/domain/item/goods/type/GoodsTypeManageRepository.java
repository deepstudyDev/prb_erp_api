package com.prb.erp.domain.item.goods.type;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface GoodsTypeManageRepository extends AXBootJPAQueryDSLRepository<GoodsTypeManage, GoodsTypeManage.GoodsTypeManageId> {
}
