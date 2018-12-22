package com.prb.erp.domain.item.goods.type;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoodsTypeManage is a Querydsl query type for GoodsTypeManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoodsTypeManage extends EntityPathBase<GoodsTypeManage> {

    private static final long serialVersionUID = -133297264L;

    public static final QGoodsTypeManage goodsTypeManage = new QGoodsTypeManage("goodsTypeManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath goodsCategoryCd = createString("goodsCategoryCd");

    public final StringPath goodsCategoryType = createString("goodsCategoryType");

    public final StringPath goodsCd = createString("goodsCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath useYn = createString("useYn");

    public QGoodsTypeManage(String variable) {
        super(GoodsTypeManage.class, forVariable(variable));
    }

    public QGoodsTypeManage(Path<? extends GoodsTypeManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsTypeManage(PathMetadata metadata) {
        super(GoodsTypeManage.class, metadata);
    }

}

