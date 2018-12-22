package com.prb.erp.domain.sms.list;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSendSaveList is a Querydsl query type for SendSaveList
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QSendSaveList extends EntityPathBase<SendSaveList> {

    private static final long serialVersionUID = 931631706L;

    public static final QSendSaveList sendSaveList = new QSendSaveList("sendSaveList");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> saveSeq = createNumber("saveSeq", Long.class);

    public final StringPath sendMessage = createString("sendMessage");

    public final StringPath sendTitle = createString("sendTitle");

    public final StringPath smsType = createString("smsType");

    public final NumberPath<Integer> totalByte = createNumber("totalByte", Integer.class);

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath userCd = createString("userCd");

    public QSendSaveList(String variable) {
        super(SendSaveList.class, forVariable(variable));
    }

    public QSendSaveList(Path<? extends SendSaveList> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSendSaveList(PathMetadata metadata) {
        super(SendSaveList.class, metadata);
    }

}

