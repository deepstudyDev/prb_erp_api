package com.prb.erp.domain.sms.detail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSendDetail_SendDetailId is a Querydsl query type for SendDetailId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QSendDetail_SendDetailId extends BeanPath<SendDetail.SendDetailId> {

    private static final long serialVersionUID = 1673302021L;

    public static final QSendDetail_SendDetailId sendDetailId = new QSendDetail_SendDetailId("sendDetailId");

    public final StringPath sendCode = createString("sendCode");

    public final NumberPath<Long> sendSeq = createNumber("sendSeq", Long.class);

    public QSendDetail_SendDetailId(String variable) {
        super(SendDetail.SendDetailId.class, forVariable(variable));
    }

    public QSendDetail_SendDetailId(Path<? extends SendDetail.SendDetailId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSendDetail_SendDetailId(PathMetadata metadata) {
        super(SendDetail.SendDetailId.class, metadata);
    }

}

