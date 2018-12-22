package com.prb.erp.domain.tcher;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherManage_TcherManageId is a Querydsl query type for TcherManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTcherManage_TcherManageId extends BeanPath<TcherManage.TcherManageId> {

    private static final long serialVersionUID = -837155753L;

    public static final QTcherManage_TcherManageId tcherManageId = new QTcherManage_TcherManageId("tcherManageId");

    public final StringPath tcherCd = createString("tcherCd");

    public QTcherManage_TcherManageId(String variable) {
        super(TcherManage.TcherManageId.class, forVariable(variable));
    }

    public QTcherManage_TcherManageId(Path<? extends TcherManage.TcherManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherManage_TcherManageId(PathMetadata metadata) {
        super(TcherManage.TcherManageId.class, metadata);
    }

}

