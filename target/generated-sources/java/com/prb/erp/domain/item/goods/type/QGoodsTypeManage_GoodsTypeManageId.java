package com.prb.erp.domain.item.goods.type;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoodsTypeManage_GoodsTypeManageId is a Querydsl query type for GoodsTypeManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QGoodsTypeManage_GoodsTypeManageId extends BeanPath<GoodsTypeManage.GoodsTypeManageId> {

    private static final long serialVersionUID = 2021899826L;

    public static final QGoodsTypeManage_GoodsTypeManageId goodsTypeManageId = new QGoodsTypeManage_GoodsTypeManageId("goodsTypeManageId");

    public final StringPath goodsCategoryCd = createString("goodsCategoryCd");

    public final StringPath goodsCategoryType = createString("goodsCategoryType");

    public final StringPath goodsCd = createString("goodsCd");

    public QGoodsTypeManage_GoodsTypeManageId(String variable) {
        super(GoodsTypeManage.GoodsTypeManageId.class, forVariable(variable));
    }

    public QGoodsTypeManage_GoodsTypeManageId(Path<? extends GoodsTypeManage.GoodsTypeManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsTypeManage_GoodsTypeManageId(PathMetadata metadata) {
        super(GoodsTypeManage.GoodsTypeManageId.class, metadata);
    }

}

