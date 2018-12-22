package com.prb.erp.domain.code.detail;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QBasicCodeDetail_BasicCodeDetailId is a Querydsl query type for BasicCodeDetailId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QBasicCodeDetail_BasicCodeDetailId extends BeanPath<BasicCodeDetail.BasicCodeDetailId> {

    private static final long serialVersionUID = -1270929211L;

    public static final QBasicCodeDetail_BasicCodeDetailId basicCodeDetailId = new QBasicCodeDetail_BasicCodeDetailId("basicCodeDetailId");

    public final StringPath mainCode = createString("mainCode");

    public final StringPath subCode = createString("subCode");

    public QBasicCodeDetail_BasicCodeDetailId(String variable) {
        super(BasicCodeDetail.BasicCodeDetailId.class, forVariable(variable));
    }

    public QBasicCodeDetail_BasicCodeDetailId(Path<? extends BasicCodeDetail.BasicCodeDetailId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBasicCodeDetail_BasicCodeDetailId(PathMetadata metadata) {
        super(BasicCodeDetail.BasicCodeDetailId.class, metadata);
    }

}

