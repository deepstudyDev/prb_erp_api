package com.prb.erp.domain.member.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberItem is a Querydsl query type for MemberItem
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMemberItem extends EntityPathBase<MemberItem> {

    private static final long serialVersionUID = 412870136L;

    public static final QMemberItem memberItem = new QMemberItem("memberItem");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath agreementCd = createString("agreementCd");

    public final StringPath contractDt = createString("contractDt");

    public final StringPath contractPaymentMethod = createString("contractPaymentMethod");

    public final StringPath contractPaymentWay = createString("contractPaymentWay");

    public final NumberPath<java.math.BigDecimal> contractPrice = createNumber("contractPrice", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath custCd = createString("custCd");

    public final StringPath goodsCd = createString("goodsCd");

    public final NumberPath<java.math.BigDecimal> monthPrice = createNumber("monthPrice", java.math.BigDecimal.class);

    public final StringPath paymentMethod = createString("paymentMethod");

    public final NumberPath<java.math.BigDecimal> paymentPrice = createNumber("paymentPrice", java.math.BigDecimal.class);

    public final StringPath paymentWay = createString("paymentWay");

    public final StringPath paymentYn = createString("paymentYn");

    public final StringPath salesType = createString("salesType");

    public final StringPath subjectNm = createString("subjectNm");

    public final NumberPath<java.math.BigDecimal> totalPrice = createNumber("totalPrice", java.math.BigDecimal.class);

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QMemberItem(String variable) {
        super(MemberItem.class, forVariable(variable));
    }

    public QMemberItem(Path<? extends MemberItem> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberItem(PathMetadata metadata) {
        super(MemberItem.class, metadata);
    }

}

