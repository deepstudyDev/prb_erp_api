package com.prb.erp.domain.notice;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeManage is a Querydsl query type for NoticeManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QNoticeManage extends EntityPathBase<NoticeManage> {

    private static final long serialVersionUID = 707271087L;

    public static final QNoticeManage noticeManage = new QNoticeManage("noticeManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath areaCd = createString("areaCd");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath noticeCd = createString("noticeCd");

    public final StringPath noticeContents = createString("noticeContents");

    public final StringPath noticeTitle = createString("noticeTitle");

    public final StringPath orgCd = createString("orgCd");

    public final NumberPath<Integer> orgType = createNumber("orgType", Integer.class);

    public final StringPath tempFileCd = createString("tempFileCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QNoticeManage(String variable) {
        super(NoticeManage.class, forVariable(variable));
    }

    public QNoticeManage(Path<? extends NoticeManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeManage(PathMetadata metadata) {
        super(NoticeManage.class, metadata);
    }

}

