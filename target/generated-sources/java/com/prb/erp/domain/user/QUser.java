package com.prb.erp.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1061623030L;

    public static final QUser user = new QUser("user");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath address1 = createString("address1");

    public final StringPath address2 = createString("address2");

    public final StringPath areaCd = createString("areaCd");

    public final StringPath birthday = createString("birthday");

    public final StringPath childrenYn = createString("childrenYn");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath custCd = createString("custCd");

    public final StringPath decisionYn = createString("decisionYn");

    public final StringPath emergencyNm = createString("emergencyNm");

    public final StringPath emergencyTel = createString("emergencyTel");

    public final StringPath grpAuthCd = createString("grpAuthCd");

    public final StringPath hpNo = createString("hpNo");

    public final StringPath idStatus = createString("idStatus");

    public final StringPath ifYn = createString("ifYn");

    public final StringPath joinDt = createString("joinDt");

    public final DateTimePath<java.time.Instant> lastLoginDate = createDateTime("lastLoginDate", java.time.Instant.class);

    public final StringPath locale = createString("locale");

    public final StringPath marriageYn = createString("marriageYn");

    public final StringPath menuGrpCd = createString("menuGrpCd");

    public final StringPath orgCd = createString("orgCd");

    public final StringPath orgClass = createString("orgClass");

    public final StringPath outDt = createString("outDt");

    public final StringPath rankCd = createString("rankCd");

    public final StringPath telNo = createString("telNo");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath userCd = createString("userCd");

    public final StringPath userNm = createString("userNm");

    public final StringPath userPs = createString("userPs");

    public final StringPath userPs2 = createString("userPs2");

    public final StringPath userStatus = createString("userStatus");

    public final StringPath userType = createString("userType");

    public final EnumPath<com.chequer.axboot.core.code.AXBootTypes.Used> useYn = createEnum("useYn", com.chequer.axboot.core.code.AXBootTypes.Used.class);

    public final StringPath zipcode = createString("zipcode");

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

