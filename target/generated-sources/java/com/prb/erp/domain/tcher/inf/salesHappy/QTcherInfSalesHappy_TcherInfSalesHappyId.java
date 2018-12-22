package com.prb.erp.domain.tcher.inf.salesHappy;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherInfSalesHappy_TcherInfSalesHappyId is a Querydsl query type for TcherInfSalesHappyId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTcherInfSalesHappy_TcherInfSalesHappyId extends BeanPath<TcherInfSalesHappy.TcherInfSalesHappyId> {

    private static final long serialVersionUID = 1467555528L;

    public static final QTcherInfSalesHappy_TcherInfSalesHappyId tcherInfSalesHappyId = new QTcherInfSalesHappy_TcherInfSalesHappyId("tcherInfSalesHappyId");

    public final StringPath salesYyyymm = createString("salesYyyymm");

    public final StringPath tcherCd = createString("tcherCd");

    public QTcherInfSalesHappy_TcherInfSalesHappyId(String variable) {
        super(TcherInfSalesHappy.TcherInfSalesHappyId.class, forVariable(variable));
    }

    public QTcherInfSalesHappy_TcherInfSalesHappyId(Path<? extends TcherInfSalesHappy.TcherInfSalesHappyId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherInfSalesHappy_TcherInfSalesHappyId(PathMetadata metadata) {
        super(TcherInfSalesHappy.TcherInfSalesHappyId.class, metadata);
    }

}

