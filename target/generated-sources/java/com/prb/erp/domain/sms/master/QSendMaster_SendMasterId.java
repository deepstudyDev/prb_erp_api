package com.prb.erp.domain.sms.master;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QSendMaster_SendMasterId is a Querydsl query type for SendMasterId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QSendMaster_SendMasterId extends BeanPath<SendMaster.SendMasterId> {

    private static final long serialVersionUID = 1925849718L;

    public static final QSendMaster_SendMasterId sendMasterId = new QSendMaster_SendMasterId("sendMasterId");

    public final StringPath sendCode = createString("sendCode");

    public QSendMaster_SendMasterId(String variable) {
        super(SendMaster.SendMasterId.class, forVariable(variable));
    }

    public QSendMaster_SendMasterId(Path<? extends SendMaster.SendMasterId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSendMaster_SendMasterId(PathMetadata metadata) {
        super(SendMaster.SendMasterId.class, metadata);
    }

}

