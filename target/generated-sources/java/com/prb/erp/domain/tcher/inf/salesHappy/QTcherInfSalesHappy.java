package com.prb.erp.domain.tcher.inf.salesHappy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherInfSalesHappy is a Querydsl query type for TcherInfSalesHappy
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTcherInfSalesHappy extends EntityPathBase<TcherInfSalesHappy> {

    private static final long serialVersionUID = 1807917392L;

    public static final QTcherInfSalesHappy tcherInfSalesHappy = new QTcherInfSalesHappy("tcherInfSalesHappy");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final NumberPath<java.math.BigDecimal> contSaleAmt = createNumber("contSaleAmt", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<java.math.BigDecimal> dedSumAmt = createNumber("dedSumAmt", java.math.BigDecimal.class);

    public final StringPath ifYn = createString("ifYn");

    public final NumberPath<java.math.BigDecimal> percentage = createNumber("percentage", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> planAmt = createNumber("planAmt", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> realSumAmt = createNumber("realSumAmt", java.math.BigDecimal.class);

    public final StringPath salesYyyymm = createString("salesYyyymm");

    public final StringPath tcherCd = createString("tcherCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final NumberPath<java.math.BigDecimal> wagesSumAmt = createNumber("wagesSumAmt", java.math.BigDecimal.class);

    public QTcherInfSalesHappy(String variable) {
        super(TcherInfSalesHappy.class, forVariable(variable));
    }

    public QTcherInfSalesHappy(Path<? extends TcherInfSalesHappy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherInfSalesHappy(PathMetadata metadata) {
        super(TcherInfSalesHappy.class, metadata);
    }

}

