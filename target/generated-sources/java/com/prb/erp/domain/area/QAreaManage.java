package com.prb.erp.domain.area;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAreaManage is a Querydsl query type for AreaManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAreaManage extends EntityPathBase<AreaManage> {

    private static final long serialVersionUID = -1309589937L;

    public static final QAreaManage areaManage = new QAreaManage("areaManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath areaAddress1 = createString("areaAddress1");

    public final StringPath areaAddress2 = createString("areaAddress2");

    public final StringPath areaAsTelNo = createString("areaAsTelNo");

    public final StringPath areaCd = createString("areaCd");

    public final StringPath areaFaxNo = createString("areaFaxNo");

    public final StringPath areaNm = createString("areaNm");

    public final StringPath areaTelNo = createString("areaTelNo");

    public final StringPath areaZipcode = createString("areaZipcode");

    public final StringPath companyRegNum = createString("companyRegNum");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath locationCd = createString("locationCd");

    public final StringPath remark = createString("remark");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath useYn = createString("useYn");

    public QAreaManage(String variable) {
        super(AreaManage.class, forVariable(variable));
    }

    public QAreaManage(Path<? extends AreaManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAreaManage(PathMetadata metadata) {
        super(AreaManage.class, metadata);
    }

}

