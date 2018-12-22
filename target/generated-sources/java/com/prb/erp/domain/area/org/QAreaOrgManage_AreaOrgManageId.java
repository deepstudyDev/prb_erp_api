package com.prb.erp.domain.area.org;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAreaOrgManage_AreaOrgManageId is a Querydsl query type for AreaOrgManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QAreaOrgManage_AreaOrgManageId extends BeanPath<AreaOrgManage.AreaOrgManageId> {

    private static final long serialVersionUID = -1095739714L;

    public static final QAreaOrgManage_AreaOrgManageId areaOrgManageId = new QAreaOrgManage_AreaOrgManageId("areaOrgManageId");

    public final StringPath areaCd = createString("areaCd");

    public final StringPath orgCd = createString("orgCd");

    public final StringPath orgClass = createString("orgClass");

    public final StringPath orgDepth = createString("orgDepth");

    public QAreaOrgManage_AreaOrgManageId(String variable) {
        super(AreaOrgManage.AreaOrgManageId.class, forVariable(variable));
    }

    public QAreaOrgManage_AreaOrgManageId(Path<? extends AreaOrgManage.AreaOrgManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAreaOrgManage_AreaOrgManageId(PathMetadata metadata) {
        super(AreaOrgManage.AreaOrgManageId.class, metadata);
    }

}

