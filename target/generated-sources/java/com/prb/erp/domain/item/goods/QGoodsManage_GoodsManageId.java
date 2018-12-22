package com.prb.erp.domain.item.goods;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoodsManage_GoodsManageId is a Querydsl query type for GoodsManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QGoodsManage_GoodsManageId extends BeanPath<GoodsManage.GoodsManageId> {

    private static final long serialVersionUID = 684248692L;

    public static final QGoodsManage_GoodsManageId goodsManageId = new QGoodsManage_GoodsManageId("goodsManageId");

    public final StringPath goodsCd = createString("goodsCd");

    public QGoodsManage_GoodsManageId(String variable) {
        super(GoodsManage.GoodsManageId.class, forVariable(variable));
    }

    public QGoodsManage_GoodsManageId(Path<? extends GoodsManage.GoodsManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsManage_GoodsManageId(PathMetadata metadata) {
        super(GoodsManage.GoodsManageId.class, metadata);
    }

}

