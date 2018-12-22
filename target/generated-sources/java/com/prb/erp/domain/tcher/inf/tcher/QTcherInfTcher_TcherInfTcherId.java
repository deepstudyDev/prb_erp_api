package com.prb.erp.domain.tcher.inf.tcher;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherInfTcher_TcherInfTcherId is a Querydsl query type for TcherInfTcherId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTcherInfTcher_TcherInfTcherId extends BeanPath<TcherInfTcher.TcherInfTcherId> {

    private static final long serialVersionUID = 1290427618L;

    public static final QTcherInfTcher_TcherInfTcherId tcherInfTcherId = new QTcherInfTcher_TcherInfTcherId("tcherInfTcherId");

    public final StringPath tcherCd = createString("tcherCd");

    public QTcherInfTcher_TcherInfTcherId(String variable) {
        super(TcherInfTcher.TcherInfTcherId.class, forVariable(variable));
    }

    public QTcherInfTcher_TcherInfTcherId(Path<? extends TcherInfTcher.TcherInfTcherId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherInfTcher_TcherInfTcherId(PathMetadata metadata) {
        super(TcherInfTcher.TcherInfTcherId.class, metadata);
    }

}

