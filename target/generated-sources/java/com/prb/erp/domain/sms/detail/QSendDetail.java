package com.prb.erp.domain.sms.detail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSendDetail is a Querydsl query type for SendDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSendDetail extends EntityPathBase<SendDetail> {

    private static final long serialVersionUID = -1938322595L;

    public static final QSendDetail sendDetail = new QSendDetail("sendDetail");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath hpNo = createString("hpNo");

    public final StringPath sendCode = createString("sendCode");

    public final NumberPath<Long> sendSeq = createNumber("sendSeq", Long.class);

    public final StringPath successYn = createString("successYn");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath userCd = createString("userCd");

    public QSendDetail(String variable) {
        super(SendDetail.class, forVariable(variable));
    }

    public QSendDetail(Path<? extends SendDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSendDetail(PathMetadata metadata) {
        super(SendDetail.class, metadata);
    }

}

