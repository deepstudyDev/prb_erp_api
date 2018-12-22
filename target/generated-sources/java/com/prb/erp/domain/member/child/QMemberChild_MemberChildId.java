package com.prb.erp.domain.member.child;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberChild_MemberChildId is a Querydsl query type for MemberChildId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QMemberChild_MemberChildId extends BeanPath<MemberChild.MemberChildId> {

    private static final long serialVersionUID = -66076209L;

    public static final QMemberChild_MemberChildId memberChildId = new QMemberChild_MemberChildId("memberChildId");

    public final StringPath childCd = createString("childCd");

    public final StringPath custCd = createString("custCd");

    public QMemberChild_MemberChildId(String variable) {
        super(MemberChild.MemberChildId.class, forVariable(variable));
    }

    public QMemberChild_MemberChildId(Path<? extends MemberChild.MemberChildId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberChild_MemberChildId(PathMetadata metadata) {
        super(MemberChild.MemberChildId.class, metadata);
    }

}

