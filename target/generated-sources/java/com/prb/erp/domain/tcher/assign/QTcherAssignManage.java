package com.prb.erp.domain.tcher.assign;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherAssignManage is a Querydsl query type for TcherAssignManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTcherAssignManage extends EntityPathBase<TcherAssignManage> {

    private static final long serialVersionUID = -104892793L;

    public static final QTcherAssignManage tcherAssignManage = new QTcherAssignManage("tcherAssignManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath approvalDt = createString("approvalDt");

    public final StringPath approvalUserCd = createString("approvalUserCd");

    public final StringPath approvalYn = createString("approvalYn");

    public final StringPath assignCd = createString("assignCd");

    public final StringPath assignRemark = createString("assignRemark");

    public final StringPath assignRequestDt = createString("assignRequestDt");

    public final StringPath assignRequestUserCd = createString("assignRequestUserCd");

    public final StringPath assignType = createString("assignType");

    public final StringPath assignUserCd = createString("assignUserCd");

    public final StringPath childCd = createString("childCd");

    public final StringPath confirmYn = createString("confirmYn");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath custCd = createString("custCd");

    public final StringPath lastYn = createString("lastYn");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QTcherAssignManage(String variable) {
        super(TcherAssignManage.class, forVariable(variable));
    }

    public QTcherAssignManage(Path<? extends TcherAssignManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherAssignManage(PathMetadata metadata) {
        super(TcherAssignManage.class, metadata);
    }

}

