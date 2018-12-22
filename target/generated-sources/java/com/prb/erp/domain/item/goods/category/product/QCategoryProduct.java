package com.prb.erp.domain.item.goods.category.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryProduct is a Querydsl query type for CategoryProduct
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCategoryProduct extends EntityPathBase<CategoryProduct> {

    private static final long serialVersionUID = 289695921L;

    public static final QCategoryProduct categoryProduct = new QCategoryProduct("categoryProduct");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath categoryCd = createString("categoryCd");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath goodsCd = createString("goodsCd");

    public final StringPath productCd = createString("productCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QCategoryProduct(String variable) {
        super(CategoryProduct.class, forVariable(variable));
    }

    public QCategoryProduct(Path<? extends CategoryProduct> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryProduct(PathMetadata metadata) {
        super(CategoryProduct.class, metadata);
    }

}

