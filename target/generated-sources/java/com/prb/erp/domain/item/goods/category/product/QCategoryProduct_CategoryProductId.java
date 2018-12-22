package com.prb.erp.domain.item.goods.category.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryProduct_CategoryProductId is a Querydsl query type for CategoryProductId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QCategoryProduct_CategoryProductId extends BeanPath<CategoryProduct.CategoryProductId> {

    private static final long serialVersionUID = 934434543L;

    public static final QCategoryProduct_CategoryProductId categoryProductId = new QCategoryProduct_CategoryProductId("categoryProductId");

    public final StringPath categoryCd = createString("categoryCd");

    public final StringPath goodsCd = createString("goodsCd");

    public final StringPath productCd = createString("productCd");

    public QCategoryProduct_CategoryProductId(String variable) {
        super(CategoryProduct.CategoryProductId.class, forVariable(variable));
    }

    public QCategoryProduct_CategoryProductId(Path<? extends CategoryProduct.CategoryProductId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryProduct_CategoryProductId(PathMetadata metadata) {
        super(CategoryProduct.CategoryProductId.class, metadata);
    }

}

