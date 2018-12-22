package com.prb.erp.domain.tmsg;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTmsgKncd_TmsgKncdId is a Querydsl query type for TmsgKncdId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTmsgKncd_TmsgKncdId extends BeanPath<TmsgKncd.TmsgKncdId> {

    private static final long serialVersionUID = -148684852L;

    public static final QTmsgKncd_TmsgKncdId tmsgKncdId = new QTmsgKncd_TmsgKncdId("tmsgKncdId");

    public final StringPath orgCd = createString("orgCd");

    public final StringPath tmsgKncd = createString("tmsgKncd");

    public QTmsgKncd_TmsgKncdId(String variable) {
        super(TmsgKncd.TmsgKncdId.class, forVariable(variable));
    }

    public QTmsgKncd_TmsgKncdId(Path<? extends TmsgKncd.TmsgKncdId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTmsgKncd_TmsgKncdId(PathMetadata metadata) {
        super(TmsgKncd.TmsgKncdId.class, metadata);
    }

}

