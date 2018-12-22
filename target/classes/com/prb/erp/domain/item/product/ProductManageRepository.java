package com.prb.erp.domain.item.product;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface ProductManageRepository extends AXBootJPAQueryDSLRepository<ProductManage, ProductManage.ProductManageId> {
}
