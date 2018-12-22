package com.prb.erp.domain.area.org;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAreaOrgManage is a Querydsl query type for AreaOrgManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAreaOrgManage extends EntityPathBase<AreaOrgManage> {

    private static final long serialVersionUID = -2121593547L;

    public static final QAreaOrgManage areaOrgManage = new QAreaOrgManage("areaOrgManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath areaCd = createString("areaCd");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath orgCd = createString("orgCd");

    public final StringPath orgClass = createString("orgClass");

    public final StringPath orgDepth = createString("orgDepth");

    public final StringPath orgNm = createString("orgNm");

    public final StringPath parentOrgCd = createString("parentOrgCd");

    public final StringPath remark = createString("remark");

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath useYn = createString("useYn");

    public QAreaOrgManage(String variable) {
        super(AreaOrgManage.class, forVariable(variable));
    }

    public QAreaOrgManage(Path<? extends AreaOrgManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAreaOrgManage(PathMetadata metadata) {
        super(AreaOrgManage.class, metadata);
    }

}

