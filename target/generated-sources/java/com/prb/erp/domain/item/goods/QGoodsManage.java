package com.prb.erp.domain.item.goods;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QGoodsManage is a Querydsl query type for GoodsManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QGoodsManage extends EntityPathBase<GoodsManage> {

    private static final long serialVersionUID = -2060617172L;

    public static final QGoodsManage goodsManage = new QGoodsManage("goodsManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath agreementCd = createString("agreementCd");

    public final NumberPath<java.math.BigDecimal> bookServiceCostPrice = createNumber("bookServiceCostPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> bookServicePrice = createNumber("bookServicePrice", java.math.BigDecimal.class);

    public final StringPath bookServiceYn = createString("bookServiceYn");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<java.math.BigDecimal> etcServiceCostPrice = createNumber("etcServiceCostPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> etcServicePrice = createNumber("etcServicePrice", java.math.BigDecimal.class);

    public final StringPath etcServiceYn = createString("etcServiceYn");

    public final StringPath goodsCd = createString("goodsCd");

    public final StringPath goodsClass = createString("goodsClass");

    public final StringPath goodsNm = createString("goodsNm");

    public final StringPath goodsStatus = createString("goodsStatus");

    public final StringPath goodsStep1 = createString("goodsStep1");

    public final StringPath goodsStep2 = createString("goodsStep2");

    public final NumberPath<java.math.BigDecimal> onlineServiceCostPrice = createNumber("onlineServiceCostPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> onlineServicePrice = createNumber("onlineServicePrice", java.math.BigDecimal.class);

    public final StringPath onlineServiceYn = createString("onlineServiceYn");

    public final NumberPath<java.math.BigDecimal> salesCostPrice = createNumber("salesCostPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> salesPrice = createNumber("salesPrice", java.math.BigDecimal.class);

    public final StringPath salesType = createString("salesType");

    public final StringPath salesYn = createString("salesYn");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath visitNumberCd = createString("visitNumberCd");

    public final NumberPath<java.math.BigDecimal> visitServiceCostPrice = createNumber("visitServiceCostPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> visitServicePrice = createNumber("visitServicePrice", java.math.BigDecimal.class);

    public final StringPath visitServiceYn = createString("visitServiceYn");

    public final StringPath visitTimeCd = createString("visitTimeCd");

    public QGoodsManage(String variable) {
        super(GoodsManage.class, forVariable(variable));
    }

    public QGoodsManage(Path<? extends GoodsManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGoodsManage(PathMetadata metadata) {
        super(GoodsManage.class, metadata);
    }

}

