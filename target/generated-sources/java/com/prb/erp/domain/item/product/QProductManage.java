package com.prb.erp.domain.item.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductManage is a Querydsl query type for ProductManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductManage extends EntityPathBase<ProductManage> {

    private static final long serialVersionUID = -677793698L;

    public static final QProductManage productManage = new QProductManage("productManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final NumberPath<java.math.BigDecimal> asPrice = createNumber("asPrice", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> costPrice = createNumber("costPrice", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath productCd = createString("productCd");

    public final StringPath productClass = createString("productClass");

    public final StringPath productNm = createString("productNm");

    public final StringPath productStep1 = createString("productStep1");

    public final StringPath productStep2 = createString("productStep2");

    public final StringPath productType = createString("productType");

    public final StringPath tempFileCd = createString("tempFileCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QProductManage(String variable) {
        super(ProductManage.class, forVariable(variable));
    }

    public QProductManage(Path<? extends ProductManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductManage(PathMetadata metadata) {
        super(ProductManage.class, metadata);
    }

}

