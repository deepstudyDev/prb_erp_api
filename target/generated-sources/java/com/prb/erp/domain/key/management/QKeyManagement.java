package com.prb.erp.domain.key.management;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QKeyManagement is a Querydsl query type for KeyManagement
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QKeyManagement extends EntityPathBase<KeyManagement> {

    private static final long serialVersionUID = 1578708426L;

    public static final QKeyManagement keyManagement = new QKeyManagement("keyManagement");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath codeType = createString("codeType");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath resultCode = createString("resultCode");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QKeyManagement(String variable) {
        super(KeyManagement.class, forVariable(variable));
    }

    public QKeyManagement(Path<? extends KeyManagement> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKeyManagement(PathMetadata metadata) {
        super(KeyManagement.class, metadata);
    }

}

