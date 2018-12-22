package com.prb.erp.domain.item.product;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QProductManage_ProductManageId is a Querydsl query type for ProductManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QProductManage_ProductManageId extends BeanPath<ProductManage.ProductManageId> {

    private static final long serialVersionUID = 1232764351L;

    public static final QProductManage_ProductManageId productManageId = new QProductManage_ProductManageId("productManageId");

    public final StringPath productCd = createString("productCd");

    public QProductManage_ProductManageId(String variable) {
        super(ProductManage.ProductManageId.class, forVariable(variable));
    }

    public QProductManage_ProductManageId(Path<? extends ProductManage.ProductManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProductManage_ProductManageId(PathMetadata metadata) {
        super(ProductManage.ProductManageId.class, metadata);
    }

}

