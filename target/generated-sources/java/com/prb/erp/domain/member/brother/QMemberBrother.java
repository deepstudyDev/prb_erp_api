package com.prb.erp.domain.member.brother;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberBrother is a Querydsl query type for MemberBrother
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberBrother extends EntityPathBase<MemberBrother> {

    private static final long serialVersionUID = -1327376568L;

    public static final QMemberBrother memberBrother = new QMemberBrother("memberBrother");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath brotherBirthday = createString("brotherBirthday");

    public final StringPath brotherCd = createString("brotherCd");

    public final StringPath brotherGradeCd = createString("brotherGradeCd");

    public final StringPath brotherNm = createString("brotherNm");

    public final StringPath brotherSex = createString("brotherSex");

    public final StringPath childCd = createString("childCd");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath custCd = createString("custCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QMemberBrother(String variable) {
        super(MemberBrother.class, forVariable(variable));
    }

    public QMemberBrother(Path<? extends MemberBrother> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberBrother(PathMetadata metadata) {
        super(MemberBrother.class, metadata);
    }

}

