package com.prb.erp.domain.sms.list;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSendSaveList_SendSaveListId is a Querydsl query type for SendSaveListId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QSendSaveList_SendSaveListId extends BeanPath<SendSaveList.SendSaveListId> {

    private static final long serialVersionUID = 52943378L;

    public static final QSendSaveList_SendSaveListId sendSaveListId = new QSendSaveList_SendSaveListId("sendSaveListId");

    public final NumberPath<Long> saveSeq = createNumber("saveSeq", Long.class);

    public final StringPath userCd = createString("userCd");

    public QSendSaveList_SendSaveListId(String variable) {
        super(SendSaveList.SendSaveListId.class, forVariable(variable));
    }

    public QSendSaveList_SendSaveListId(Path<? extends SendSaveList.SendSaveListId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSendSaveList_SendSaveListId(PathMetadata metadata) {
        super(SendSaveList.SendSaveListId.class, metadata);
    }

}

