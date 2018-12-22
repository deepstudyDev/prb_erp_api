package com.prb.erp.domain.item.goods.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoodsCategory_GoodsCategoryId is a Querydsl query type for GoodsCategoryId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QGoodsCategory_GoodsCategoryId extends BeanPath<GoodsCategory.GoodsCategoryId> {

    private static final long serialVersionUID = 1033857396L;

    public static final QGoodsCategory_GoodsCategoryId goodsCategoryId = new QGoodsCategory_GoodsCategoryId("goodsCategoryId");

    public final StringPath categoryCd = createString("categoryCd");

    public final StringPath goodsCd = createString("goodsCd");

    public QGoodsCategory_GoodsCategoryId(String variable) {
        super(GoodsCategory.GoodsCategoryId.class, forVariable(variable));
    }

    public QGoodsCategory_GoodsCategoryId(Path<? extends GoodsCategory.GoodsCategoryId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsCategory_GoodsCategoryId(PathMetadata metadata) {
        super(GoodsCategory.GoodsCategoryId.class, metadata);
    }

}

