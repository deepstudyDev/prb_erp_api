package com.prb.erp.domain.member.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberItem_MemberItemId is a Querydsl query type for MemberItemId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QMemberItem_MemberItemId extends BeanPath<MemberItem.MemberItemId> {

    private static final long serialVersionUID = -324488418L;

    public static final QMemberItem_MemberItemId memberItemId = new QMemberItem_MemberItemId("memberItemId");

    public final StringPath custCd = createString("custCd");

    public QMemberItem_MemberItemId(String variable) {
        super(MemberItem.MemberItemId.class, forVariable(variable));
    }

    public QMemberItem_MemberItemId(Path<? extends MemberItem.MemberItemId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberItem_MemberItemId(PathMetadata metadata) {
        super(MemberItem.MemberItemId.class, metadata);
    }

}

