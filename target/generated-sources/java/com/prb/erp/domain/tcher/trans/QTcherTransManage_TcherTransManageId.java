package com.prb.erp.domain.tcher.trans;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherTransManage_TcherTransManageId is a Querydsl query type for TcherTransManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTcherTransManage_TcherTransManageId extends BeanPath<TcherTransManage.TcherTransManageId> {

    private static final long serialVersionUID = 1981404343L;

    public static final QTcherTransManage_TcherTransManageId tcherTransManageId = new QTcherTransManage_TcherTransManageId("tcherTransManageId");

    public final StringPath transCd = createString("transCd");

    public QTcherTransManage_TcherTransManageId(String variable) {
        super(TcherTransManage.TcherTransManageId.class, forVariable(variable));
    }

    public QTcherTransManage_TcherTransManageId(Path<? extends TcherTransManage.TcherTransManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherTransManage_TcherTransManageId(PathMetadata metadata) {
        super(TcherTransManage.TcherTransManageId.class, metadata);
    }

}

