package com.prb.erp.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberManage_MemberManageId is a Querydsl query type for MemberManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QMemberManage_MemberManageId extends BeanPath<MemberManage.MemberManageId> {

    private static final long serialVersionUID = -586030439L;

    public static final QMemberManage_MemberManageId memberManageId = new QMemberManage_MemberManageId("memberManageId");

    public final StringPath custCd = createString("custCd");

    public QMemberManage_MemberManageId(String variable) {
        super(MemberManage.MemberManageId.class, forVariable(variable));
    }

    public QMemberManage_MemberManageId(Path<? extends MemberManage.MemberManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberManage_MemberManageId(PathMetadata metadata) {
        super(MemberManage.MemberManageId.class, metadata);
    }

}

