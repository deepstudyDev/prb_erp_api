package com.prb.erp.domain.member.cancel;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberCancelManage_MemberCancelManagId is a Querydsl query type for MemberCancelManagId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QMemberCancelManage_MemberCancelManagId extends BeanPath<MemberCancelManage.MemberCancelManagId> {

    private static final long serialVersionUID = 135784438L;

    public static final QMemberCancelManage_MemberCancelManagId memberCancelManagId = new QMemberCancelManage_MemberCancelManagId("memberCancelManagId");

    public final StringPath cancelCd = createString("cancelCd");

    public QMemberCancelManage_MemberCancelManagId(String variable) {
        super(MemberCancelManage.MemberCancelManagId.class, forVariable(variable));
    }

    public QMemberCancelManage_MemberCancelManagId(Path<? extends MemberCancelManage.MemberCancelManagId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberCancelManage_MemberCancelManagId(PathMetadata metadata) {
        super(MemberCancelManage.MemberCancelManagId.class, metadata);
    }

}

