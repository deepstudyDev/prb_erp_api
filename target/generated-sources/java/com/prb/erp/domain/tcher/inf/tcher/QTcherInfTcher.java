package com.prb.erp.domain.tcher.inf.tcher;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherInfTcher is a Querydsl query type for TcherInfTcher
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTcherInfTcher extends EntityPathBase<TcherInfTcher> {

    private static final long serialVersionUID = -811169494L;

    public static final QTcherInfTcher tcherInfTcher = new QTcherInfTcher("tcherInfTcher");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final NumberPath<java.math.BigDecimal> addRate = createNumber("addRate", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath ifYn = createString("ifYn");

    public final StringPath mgrStartYyyymm = createString("mgrStartYyyymm");

    public final NumberPath<java.math.BigDecimal> nrate1 = createNumber("nrate1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> nrate2 = createNumber("nrate2", java.math.BigDecimal.class);

    public final StringPath payStartYyyymm = createString("payStartYyyymm");

    public final NumberPath<java.math.BigDecimal> rate1 = createNumber("rate1", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> rate2 = createNumber("rate2", java.math.BigDecimal.class);

    public final NumberPath<java.math.BigDecimal> rateMgr = createNumber("rateMgr", java.math.BigDecimal.class);

    public final StringPath rateMonth1 = createString("rateMonth1");

    public final StringPath rateMonth2 = createString("rateMonth2");

    public final StringPath tcherCd = createString("tcherCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath weekday = createString("weekday");

    public QTcherInfTcher(String variable) {
        super(TcherInfTcher.class, forVariable(variable));
    }

    public QTcherInfTcher(Path<? extends TcherInfTcher> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherInfTcher(PathMetadata metadata) {
        super(TcherInfTcher.class, metadata);
    }

}

