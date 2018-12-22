package com.prb.erp.domain.tmsg;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTmsgKncd is a Querydsl query type for TmsgKncd
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTmsgKncd extends EntityPathBase<TmsgKncd> {

    private static final long serialVersionUID = -1884594066L;

    public static final QTmsgKncd tmsgKncd1 = new QTmsgKncd("tmsgKncd1");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath bizNo = createString("bizNo");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath docLinkKey = createString("docLinkKey");

    public final StringPath docNo = createString("docNo");

    public final StringPath docUserEmail = createString("docUserEmail");

    public final StringPath docUserHpNo = createString("docUserHpNo");

    public final StringPath docUserNm = createString("docUserNm");

    public final StringPath orgCd = createString("orgCd");

    public final StringPath procType = createString("procType");

    public final StringPath tmsgKncd = createString("tmsgKncd");

    public final StringPath tmsgLayout = createString("tmsgLayout");

    public final StringPath tmsgNm = createString("tmsgNm");

    public final StringPath tmsgTableNm = createString("tmsgTableNm");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QTmsgKncd(String variable) {
        super(TmsgKncd.class, forVariable(variable));
    }

    public QTmsgKncd(Path<? extends TmsgKncd> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTmsgKncd(PathMetadata metadata) {
        super(TmsgKncd.class, metadata);
    }

}

