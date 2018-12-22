package com.prb.erp.domain.tcher.rest;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherRestManage is a Querydsl query type for TcherRestManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTcherRestManage extends EntityPathBase<TcherRestManage> {

    private static final long serialVersionUID = 107060817L;

    public static final QTcherRestManage tcherRestManage = new QTcherRestManage("tcherRestManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

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

    public final StringPath requestMonth = createString("requestMonth");

    public final StringPath requestYear = createString("requestYear");

    public final StringPath restCd = createString("restCd");

    public final StringPath restReasonCd = createString("restReasonCd");

    public final StringPath restRemark = createString("restRemark");

    public final StringPath restRequestDt = createString("restRequestDt");

    public final StringPath restRequestUserCd = createString("restRequestUserCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QTcherRestManage(String variable) {
        super(TcherRestManage.class, forVariable(variable));
    }

    public QTcherRestManage(Path<? extends TcherRestManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherRestManage(PathMetadata metadata) {
        super(TcherRestManage.class, metadata);
    }

}

