package com.prb.erp.domain.tcher.rest;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherRestManage_TcherRestManageId is a Querydsl query type for TcherRestManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTcherRestManage_TcherRestManageId extends BeanPath<TcherRestManage.TcherRestManageId> {

    private static final long serialVersionUID = -787689411L;

    public static final QTcherRestManage_TcherRestManageId tcherRestManageId = new QTcherRestManage_TcherRestManageId("tcherRestManageId");

    public final StringPath restCd = createString("restCd");

    public QTcherRestManage_TcherRestManageId(String variable) {
        super(TcherRestManage.TcherRestManageId.class, forVariable(variable));
    }

    public QTcherRestManage_TcherRestManageId(Path<? extends TcherRestManage.TcherRestManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherRestManage_TcherRestManageId(PathMetadata metadata) {
        super(TcherRestManage.TcherRestManageId.class, metadata);
    }

}

