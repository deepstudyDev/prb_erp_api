package com.prb.erp.domain.member.child;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberChild is a Querydsl query type for MemberChild
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberChild extends EntityPathBase<MemberChild> {

    private static final long serialVersionUID = -197237760L;

    public static final QMemberChild memberChild = new QMemberChild("memberChild");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath childCd = createString("childCd");

    public final StringPath childImgBy = createString("childImgBy");

    public final StringPath childImgRegDt = createString("childImgRegDt");

    public final StringPath childImgUrl = createString("childImgUrl");

    public final StringPath childRefreNum = createString("childRefreNum");

    public final StringPath childrenBirthday = createString("childrenBirthday");

    public final StringPath childrenContractDt = createString("childrenContractDt");

    public final StringPath childrenGradeCd = createString("childrenGradeCd");

    public final StringPath childrenHpNo = createString("childrenHpNo");

    public final StringPath childrenNm = createString("childrenNm");

    public final StringPath childrenSchoolNm = createString("childrenSchoolNm");

    public final StringPath childrenSex = createString("childrenSex");

    public final StringPath childrenUserCd = createString("childrenUserCd");

    public final StringPath contractType = createString("contractType");

    public final StringPath counselor1TcherCd = createString("counselor1TcherCd");

    public final StringPath counselor1TcherHpNo = createString("counselor1TcherHpNo");

    public final StringPath counselor2TcherCd = createString("counselor2TcherCd");

    public final StringPath counselor2TcherHpNo = createString("counselor2TcherHpNo");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath custCd = createString("custCd");

    public final StringPath ifYn = createString("ifYn");

    public final StringPath memberStatus = createString("memberStatus");

    public final StringPath onlinePaymentMethod = createString("onlinePaymentMethod");

    public final StringPath onlinePaymentWay = createString("onlinePaymentWay");

    public final NumberPath<java.math.BigDecimal> onlineServicePrice = createNumber("onlineServicePrice", java.math.BigDecimal.class);

    public final StringPath onlineServiceStatus = createString("onlineServiceStatus");

    public final StringPath onlineServiceYn = createString("onlineServiceYn");

    public final StringPath parentChildCd = createString("parentChildCd");

    public final StringPath remark = createString("remark");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath visitEndDt = createString("visitEndDt");

    public final StringPath visitNumberCd = createString("visitNumberCd");

    public final StringPath visitorTcherCd = createString("visitorTcherCd");

    public final StringPath visitorTcherHpNo = createString("visitorTcherHpNo");

    public final StringPath visitPaymentMethod = createString("visitPaymentMethod");

    public final StringPath visitPaymentWay = createString("visitPaymentWay");

    public final NumberPath<java.math.BigDecimal> visitServicePrice = createNumber("visitServicePrice", java.math.BigDecimal.class);

    public final StringPath visitServiceStatus = createString("visitServiceStatus");

    public final StringPath visitServiceYn = createString("visitServiceYn");

    public final StringPath visitStartDt = createString("visitStartDt");

    public final StringPath visitTimeCd = createString("visitTimeCd");

    public QMemberChild(String variable) {
        super(MemberChild.class, forVariable(variable));
    }

    public QMemberChild(Path<? extends MemberChild> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberChild(PathMetadata metadata) {
        super(MemberChild.class, metadata);
    }

}

