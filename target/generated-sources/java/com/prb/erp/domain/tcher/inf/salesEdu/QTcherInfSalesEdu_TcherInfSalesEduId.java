package com.prb.erp.domain.tcher.inf.salesEdu;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherInfSalesEdu_TcherInfSalesEduId is a Querydsl query type for TcherInfSalesEduId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTcherInfSalesEdu_TcherInfSalesEduId extends BeanPath<TcherInfSalesEdu.TcherInfSalesEduId> {

    private static final long serialVersionUID = -956854818L;

    public static final QTcherInfSalesEdu_TcherInfSalesEduId tcherInfSalesEduId = new QTcherInfSalesEdu_TcherInfSalesEduId("tcherInfSalesEduId");

    public final StringPath salesYyyymm = createString("salesYyyymm");

    public final StringPath tcherCd = createString("tcherCd");

    public QTcherInfSalesEdu_TcherInfSalesEduId(String variable) {
        super(TcherInfSalesEdu.TcherInfSalesEduId.class, forVariable(variable));
    }

    public QTcherInfSalesEdu_TcherInfSalesEduId(Path<? extends TcherInfSalesEdu.TcherInfSalesEduId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherInfSalesEdu_TcherInfSalesEduId(PathMetadata metadata) {
        super(TcherInfSalesEdu.TcherInfSalesEduId.class, metadata);
    }

}

