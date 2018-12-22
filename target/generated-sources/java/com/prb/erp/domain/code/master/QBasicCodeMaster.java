package com.prb.erp.domain.code.master;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBasicCodeMaster is a Querydsl query type for BasicCodeMaster
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBasicCodeMaster extends EntityPathBase<BasicCodeMaster> {

    private static final long serialVersionUID = -360381746L;

    public static final QBasicCodeMaster basicCodeMaster = new QBasicCodeMaster("basicCodeMaster");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath mainCode = createString("mainCode");

    public final StringPath mainName = createString("mainName");

    public final StringPath remark = createString("remark");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath useYn = createString("useYn");

    public QBasicCodeMaster(String variable) {
        super(BasicCodeMaster.class, forVariable(variable));
    }

    public QBasicCodeMaster(Path<? extends BasicCodeMaster> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBasicCodeMaster(PathMetadata metadata) {
        super(BasicCodeMaster.class, metadata);
    }

}

