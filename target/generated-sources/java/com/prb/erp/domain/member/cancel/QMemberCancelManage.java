package com.prb.erp.domain.member.cancel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberCancelManage is a Querydsl query type for MemberCancelManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberCancelManage extends EntityPathBase<MemberCancelManage> {

    private static final long serialVersionUID = 216089501L;

    public static final QMemberCancelManage memberCancelManage = new QMemberCancelManage("memberCancelManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath approvalDt = createString("approvalDt");

    public final StringPath approvalUserCd = createString("approvalUserCd");

    public final StringPath approvalYn = createString("approvalYn");

    public final StringPath cancelCd = createString("cancelCd");

    public final StringPath cancelReasonCd = createString("cancelReasonCd");

    public final StringPath cancelRemark = createString("cancelRemark");

    public final StringPath cancelRequestDt = createString("cancelRequestDt");

    public final StringPath cancelRequestUserCd = createString("cancelRequestUserCd");

    public final StringPath childCd = createString("childCd");

    public final StringPath confirmYn = createString("confirmYn");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath custCd = createString("custCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QMemberCancelManage(String variable) {
        super(MemberCancelManage.class, forVariable(variable));
    }

    public QMemberCancelManage(Path<? extends MemberCancelManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberCancelManage(PathMetadata metadata) {
        super(MemberCancelManage.class, metadata);
    }

}

