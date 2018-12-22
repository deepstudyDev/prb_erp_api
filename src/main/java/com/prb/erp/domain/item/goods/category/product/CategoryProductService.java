package com.prb.erp.domain.item.goods.category.product;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.prb.erp.domain.BaseService;


@Service
public class CategoryProductService extends BaseService<CategoryProduct, CategoryProduct.CategoryProductId> {
    private CategoryProductRepository repository;    
 
    @Inject
    public CategoryProductService(CategoryProductRepository repository) { 
        super(repository);
        this.repository = repository;
    }
}
 