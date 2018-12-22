package com.prb.erp.domain.key.management;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QKeyManagement_CompanyKeyManagementId is a Querydsl query type for CompanyKeyManagementId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QKeyManagement_CompanyKeyManagementId extends BeanPath<KeyManagement.CompanyKeyManagementId> {

    private static final long serialVersionUID = -931180732L;

    public static final QKeyManagement_CompanyKeyManagementId companyKeyManagementId = new QKeyManagement_CompanyKeyManagementId("companyKeyManagementId");

    public final StringPath codeType = createString("codeType");

    public final NumberPath<Long> seq = createNumber("seq", Long.class);

    public QKeyManagement_CompanyKeyManagementId(String variable) {
        super(KeyManagement.CompanyKeyManagementId.class, forVariable(variable));
    }

    public QKeyManagement_CompanyKeyManagementId(Path<? extends KeyManagement.CompanyKeyManagementId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKeyManagement_CompanyKeyManagementId(PathMetadata metadata) {
        super(KeyManagement.CompanyKeyManagementId.class, metadata);
    }

}

