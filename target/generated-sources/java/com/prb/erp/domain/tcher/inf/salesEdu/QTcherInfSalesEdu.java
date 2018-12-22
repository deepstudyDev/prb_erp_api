package com.prb.erp.domain.tcher.inf.salesEdu;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherInfSalesEdu is a Querydsl query type for TcherInfSalesEdu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTcherInfSalesEdu extends EntityPathBase<TcherInfSalesEdu> {

    private static final long serialVersionUID = -1064788784L;

    public static final QTcherInfSalesEdu tcherInfSalesEdu = new QTcherInfSalesEdu("tcherInfSalesEdu");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<java.math.BigDecimal> effectRiz = createNumber("effectRiz", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> gnlAmt = createNumber("gnlAmt", java.math.BigDecimal.class);

    public final StringPath ifYn = createString("ifYn");

    public final NumberPath<java.math.BigDecimal> planSu = createNumber("planSu", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> preAmt = createNumber("preAmt", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> resultSu = createNumber("resultSu", java.math.BigDecimal.class);

    public final StringPath salesYyyymm = createString("salesYyyymm");

    public final StringPath tcherCd = createString("tcherCd");

    public final NumberPath<java.math.BigDecimal> transAmt = createNumber("transAmt", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QTcherInfSalesEdu(String variable) {
        super(TcherInfSalesEdu.class, forVariable(variable));
    }

    public QTcherInfSalesEdu(Path<? extends TcherInfSalesEdu> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherInfSalesEdu(PathMetadata metadata) {
        super(TcherInfSalesEdu.class, metadata);
    }

}

