package com.prb.erp.domain.code.master;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBasicCodeMaster_BasicCodeMasterId is a Querydsl query type for BasicCodeMasterId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QBasicCodeMaster_BasicCodeMasterId extends BeanPath<BasicCodeMaster.BasicCodeMasterId> {

    private static final long serialVersionUID = 1857837112L;

    public static final QBasicCodeMaster_BasicCodeMasterId basicCodeMasterId = new QBasicCodeMaster_BasicCodeMasterId("basicCodeMasterId");

    public final StringPath mainCode = createString("mainCode");

    public QBasicCodeMaster_BasicCodeMasterId(String variable) {
        super(BasicCodeMaster.BasicCodeMasterId.class, forVariable(variable));
    }

    public QBasicCodeMaster_BasicCodeMasterId(Path<? extends BasicCodeMaster.BasicCodeMasterId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBasicCodeMaster_BasicCodeMasterId(PathMetadata metadata) {
        super(BasicCodeMaster.BasicCodeMasterId.class, metadata);
    }

}

