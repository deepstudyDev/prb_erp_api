package com.prb.erp.domain.tcher.assign;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherAssignManage_TcherAssignManageId is a Querydsl query type for TcherAssignManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QTcherAssignManage_TcherAssignManageId extends BeanPath<TcherAssignManage.TcherAssignManageId> {

    private static final long serialVersionUID = -1079298738L;

    public static final QTcherAssignManage_TcherAssignManageId tcherAssignManageId = new QTcherAssignManage_TcherAssignManageId("tcherAssignManageId");

    public final StringPath assignCd = createString("assignCd");

    public QTcherAssignManage_TcherAssignManageId(String variable) {
        super(TcherAssignManage.TcherAssignManageId.class, forVariable(variable));
    }

    public QTcherAssignManage_TcherAssignManageId(Path<? extends TcherAssignManage.TcherAssignManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherAssignManage_TcherAssignManageId(PathMetadata metadata) {
        super(TcherAssignManage.TcherAssignManageId.class, metadata);
    }

}

