package com.prb.erp.domain.member.brother;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberBrother_MemberBrotherId is a Querydsl query type for MemberBrotherId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QMemberBrother_MemberBrotherId extends BeanPath<MemberBrother.MemberBrotherId> {

    private static final long serialVersionUID = 304299451L;

    public static final QMemberBrother_MemberBrotherId memberBrotherId = new QMemberBrother_MemberBrotherId("memberBrotherId");

    public final StringPath brotherCd = createString("brotherCd");

    public final StringPath childCd = createString("childCd");

    public final StringPath custCd = createString("custCd");

    public QMemberBrother_MemberBrotherId(String variable) {
        super(MemberBrother.MemberBrotherId.class, forVariable(variable));
    }

    public QMemberBrother_MemberBrotherId(Path<? extends MemberBrother.MemberBrotherId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberBrother_MemberBrotherId(PathMetadata metadata) {
        super(MemberBrother.MemberBrotherId.class, metadata);
    }

}

