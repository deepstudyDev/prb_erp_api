package com.prb.erp.domain.item.goods.category;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface GoodsCategoryRepository extends AXBootJPAQueryDSLRepository<GoodsCategory, GoodsCategory.GoodsCategoryId> {
}
