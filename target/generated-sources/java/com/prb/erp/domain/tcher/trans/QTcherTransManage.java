package com.prb.erp.domain.tcher.trans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherTransManage is a Querydsl query type for TcherTransManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTcherTransManage extends EntityPathBase<TcherTransManage> {

    private static final long serialVersionUID = 497885945L;

    public static final QTcherTransManage tcherTransManage = new QTcherTransManage("tcherTransManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final StringPath approvalDt = createString("approvalDt");

    public final StringPath approvalUserCd = createString("approvalUserCd");

    public final StringPath approvalYn = createString("approvalYn");

    public final StringPath childCd = createString("childCd");

    public final StringPath confirmYn = createString("confirmYn");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath custCd = createString("custCd");

    public final StringPath moveDt = createString("moveDt");

    public final StringPath requestStartDt = createString("requestStartDt");

    public final StringPath requestStartHour = createString("requestStartHour");

    public final StringPath requestStartTime = createString("requestStartTime");

    public final StringPath transCd = createString("transCd");

    public final StringPath transHopeDt = createString("transHopeDt");

    public final StringPath transPrevUserCd = createString("transPrevUserCd");

    public final StringPath transReasonCd = createString("transReasonCd");

    public final StringPath transRemark = createString("transRemark");

    public final StringPath transRequestDt = createString("transRequestDt");

    public final StringPath transRequestUserCd = createString("transRequestUserCd");

    public final StringPath transUserCd = createString("transUserCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath zipcode = createString("zipcode");

    public QTcherTransManage(String variable) {
        super(TcherTransManage.class, forVariable(variable));
    }

    public QTcherTransManage(Path<? extends TcherTransManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherTransManage(PathMetadata metadata) {
        super(TcherTransManage.class, metadata);
    }

}

