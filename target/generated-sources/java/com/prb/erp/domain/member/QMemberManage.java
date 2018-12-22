package com.prb.erp.domain.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberManage is a Querydsl query type for MemberManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberManage extends EntityPathBase<MemberManage> {

    private static final long serialVersionUID = 1712614255L;

    public static final QMemberManage memberManage = new QMemberManage("memberManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath areaCd = createString("areaCd");

    public final StringPath areaHpNo = createString("areaHpNo");

    public final StringPath contCd = createString("contCd");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath custCd = createString("custCd");

    public final StringPath deliveryAddress1 = createString("deliveryAddress1");

    public final StringPath deliveryAddress2 = createString("deliveryAddress2");

    public final StringPath deliveryZipcode = createString("deliveryZipcode");

    public final StringPath email = createString("email");

    public final StringPath emailType = createString("emailType");

    public final StringPath gd1Birthday = createString("gd1Birthday");

    public final StringPath gd1Nm = createString("gd1Nm");

    public final StringPath gd1RelationCd = createString("gd1RelationCd");

    public final StringPath gd1UserCd = createString("gd1UserCd");

    public final StringPath gd2Birthday = createString("gd2Birthday");

    public final StringPath gd2Nm = createString("gd2Nm");

    public final StringPath gd2RelationCd = createString("gd2RelationCd");

    public final StringPath homeAddress1 = createString("homeAddress1");

    public final StringPath homeAddress2 = createString("homeAddress2");

    public final StringPath homeZipcode = createString("homeZipcode");

    public final StringPath hpNo = createString("hpNo");

    public final StringPath ifYn = createString("ifYn");

    public final StringPath orgCd = createString("orgCd");

    public final StringPath repreNum = createString("repreNum");

    public final StringPath telNo = createString("telNo");

    public final StringPath tempFileCd = createString("tempFileCd");

    public final StringPath tmsgSeq = createString("tmsgSeq");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QMemberManage(String variable) {
        super(MemberManage.class, forVariable(variable));
    }

    public QMemberManage(Path<? extends MemberManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberManage(PathMetadata metadata) {
        super(MemberManage.class, metadata);
    }

}

