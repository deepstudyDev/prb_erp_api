package com.prb.erp.domain.notice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeManage_NoticeManageId is a Querydsl query type for NoticeManageId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QNoticeManage_NoticeManageId extends BeanPath<NoticeManage.NoticeManageId> {

    private static final long serialVersionUID = -1527784809L;

    public static final QNoticeManage_NoticeManageId noticeManageId = new QNoticeManage_NoticeManageId("noticeManageId");

    public final StringPath noticeCd = createString("noticeCd");

    public QNoticeManage_NoticeManageId(String variable) {
        super(NoticeManage.NoticeManageId.class, forVariable(variable));
    }

    public QNoticeManage_NoticeManageId(Path<? extends NoticeManage.NoticeManageId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeManage_NoticeManageId(PathMetadata metadata) {
        super(NoticeManage.NoticeManageId.class, metadata);
    }

}

