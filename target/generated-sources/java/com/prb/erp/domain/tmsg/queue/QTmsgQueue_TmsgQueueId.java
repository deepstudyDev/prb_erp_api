package com.prb.erp.domain.tmsg.queue;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTmsgQueue_TmsgQueueId is a Querydsl query type for TmsgQueueId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTmsgQueue_TmsgQueueId extends BeanPath<TmsgQueue.TmsgQueueId> {

    private static final long serialVersionUID = -197116229L;

    public static final QTmsgQueue_TmsgQueueId tmsgQueueId = new QTmsgQueue_TmsgQueueId("tmsgQueueId");

    public final StringPath recvOrgCd = createString("recvOrgCd");

    public final StringPath sendOrgCd = createString("sendOrgCd");

    public final StringPath tmsgSeq = createString("tmsgSeq");

    public QTmsgQueue_TmsgQueueId(String variable) {
        super(TmsgQueue.TmsgQueueId.class, forVariable(variable));
    }

    public QTmsgQueue_TmsgQueueId(Path<? extends TmsgQueue.TmsgQueueId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTmsgQueue_TmsgQueueId(PathMetadata metadata) {
        super(TmsgQueue.TmsgQueueId.class, metadata);
    }

}

