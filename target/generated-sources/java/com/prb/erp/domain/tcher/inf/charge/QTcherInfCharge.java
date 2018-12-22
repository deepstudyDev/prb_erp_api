package com.prb.erp.domain.tcher.inf.charge;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherInfCharge is a Querydsl query type for TcherInfCharge
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTcherInfCharge extends EntityPathBase<TcherInfCharge> {

    private static final long serialVersionUID = 1237822160L;

    public static final QTcherInfCharge tcherInfCharge = new QTcherInfCharge("tcherInfCharge");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath ccOrgCd = createString("ccOrgCd");

    public final StringPath ccRankCd = createString("ccRankCd");

    public final StringPath chargeCode = createString("chargeCode");

    public final StringPath chargeDt = createString("chargeDt");

    public final StringPath chargeDtcd = createString("chargeDtcd");

    public final StringPath companyName = createString("companyName");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath ifYn = createString("ifYn");

    public final StringPath orgCd = createString("orgCd");

    public final StringPath rankCd = createString("rankCd");

    public final StringPath remark = createString("remark");

    public final StringPath sectCode = createString("sectCode");

    public final StringPath tcherCd = createString("tcherCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QTcherInfCharge(String variable) {
        super(TcherInfCharge.class, forVariable(variable));
    }

    public QTcherInfCharge(Path<? extends TcherInfCharge> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherInfCharge(PathMetadata metadata) {
        super(TcherInfCharge.class, metadata);
    }

}

