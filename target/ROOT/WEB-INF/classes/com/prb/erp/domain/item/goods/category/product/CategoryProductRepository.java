package com.prb.erp.domain.item.goods.category.product;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface CategoryProductRepository extends AXBootJPAQueryDSLRepository<CategoryProduct, CategoryProduct.CategoryProductId> {
}
