package com.prb.erp.domain.item.goods.category;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoodsCategory is a Querydsl query type for GoodsCategory
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoodsCategory extends EntityPathBase<GoodsCategory> {

    private static final long serialVersionUID = 869046995L;

    public static final QGoodsCategory goodsCategory = new QGoodsCategory("goodsCategory");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath categoryCd = createString("categoryCd");

    public final StringPath categoryNm = createString("categoryNm");

    public final StringPath categoryType = createString("categoryType");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath goodsCategoryCd = createString("goodsCategoryCd");

    public final StringPath goodsCd = createString("goodsCd");

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QGoodsCategory(String variable) {
        super(GoodsCategory.class, forVariable(variable));
    }

    public QGoodsCategory(Path<? extends GoodsCategory> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsCategory(PathMetadata metadata) {
        super(GoodsCategory.class, metadata);
    }

}

