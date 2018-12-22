package com.prb.erp.domain.tmsg.queue;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTmsgQueue is a Querydsl query type for TmsgQueue
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTmsgQueue extends EntityPathBase<TmsgQueue> {

    private static final long serialVersionUID = -1233006582L;

    public static final QTmsgQueue tmsgQueue = new QTmsgQueue("tmsgQueue");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath linkKey = createString("linkKey");

    public final StringPath recvOrgCd = createString("recvOrgCd");

    public final StringPath regTime = createString("regTime");

    public final StringPath reqYmd = createString("reqYmd");

    public final StringPath resMsg = createString("resMsg");

    public final StringPath respCd = createString("respCd");

    public final StringPath sendOrgCd = createString("sendOrgCd");

    public final StringPath status = createString("status");

    public final StringPath tmsgKncd = createString("tmsgKncd");

    public final StringPath tmsgSection = createString("tmsgSection");

    public final StringPath tmsgSeq = createString("tmsgSeq");

    public final StringPath tmsgType = createString("tmsgType");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath updTime = createString("updTime");

    public final StringPath updYmd = createString("updYmd");

    public final StringPath userEmail = createString("userEmail");

    public final StringPath userHpNo = createString("userHpNo");

    public final StringPath userNm = createString("userNm");

    public QTmsgQueue(String variable) {
        super(TmsgQueue.class, forVariable(variable));
    }

    public QTmsgQueue(Path<? extends TmsgQueue> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTmsgQueue(PathMetadata metadata) {
        super(TmsgQueue.class, metadata);
    }

}

