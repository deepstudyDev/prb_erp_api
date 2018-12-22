package com.prb.erp.domain.area;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAreaManage_AreaManageId is a Querydsl query type for AreaManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QAreaManage_AreaManageId extends BeanPath<AreaManage.AreaManageId> {

    private static final long serialVersionUID = -544378260L;

    public static final QAreaManage_AreaManageId areaManageId = new QAreaManage_AreaManageId("areaManageId");

    public final StringPath areaCd = createString("areaCd");

    public QAreaManage_AreaManageId(String variable) {
        super(AreaManage.AreaManageId.class, forVariable(variable));
    }

    public QAreaManage_AreaManageId(Path<? extends AreaManage.AreaManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAreaManage_AreaManageId(PathMetadata metadata) {
        super(AreaManage.AreaManageId.class, metadata);
    }

}

