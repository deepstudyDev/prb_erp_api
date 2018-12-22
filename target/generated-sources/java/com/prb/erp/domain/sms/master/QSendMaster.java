package com.prb.erp.domain.sms.master;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSendMaster is a Querydsl query type for SendMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSendMaster extends EntityPathBase<SendMaster> {

    private static final long serialVersionUID = -922466627L;

    public static final QSendMaster sendMaster = new QSendMaster("sendMaster");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath sendCode = createString("sendCode");

    public final StringPath sendDt = createString("sendDt");

    public final StringPath senderNo = createString("senderNo");

    public final StringPath senderUserCd = createString("senderUserCd");

    public final StringPath sendMessage = createString("sendMessage");

    public final StringPath sendType = createString("sendType");

    public final StringPath smsType = createString("smsType");

    public final NumberPath<Integer> totalByte = createNumber("totalByte", Integer.class);

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QSendMaster(String variable) {
        super(SendMaster.class, forVariable(variable));
    }

    public QSendMaster(Path<? extends SendMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSendMaster(PathMetadata metadata) {
        super(SendMaster.class, metadata);
    }

}

