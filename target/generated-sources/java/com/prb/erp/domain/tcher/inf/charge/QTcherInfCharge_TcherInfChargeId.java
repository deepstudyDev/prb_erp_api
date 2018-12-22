package com.prb.erp.domain.tcher.inf.charge;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherInfCharge_TcherInfChargeId is a Querydsl query type for TcherInfChargeId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTcherInfCharge_TcherInfChargeId extends BeanPath<TcherInfCharge.TcherInfChargeId> {

    private static final long serialVersionUID = -1737836728L;

    public static final QTcherInfCharge_TcherInfChargeId tcherInfChargeId = new QTcherInfCharge_TcherInfChargeId("tcherInfChargeId");

    public final StringPath chargeCode = createString("chargeCode");

    public final StringPath chargeDt = createString("chargeDt");

    public final StringPath tcherCd = createString("tcherCd");

    public QTcherInfCharge_TcherInfChargeId(String variable) {
        super(TcherInfCharge.TcherInfChargeId.class, forVariable(variable));
    }

    public QTcherInfCharge_TcherInfChargeId(Path<? extends TcherInfCharge.TcherInfChargeId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherInfCharge_TcherInfChargeId(PathMetadata metadata) {
        super(TcherInfCharge.TcherInfChargeId.class, metadata);
    }

}

