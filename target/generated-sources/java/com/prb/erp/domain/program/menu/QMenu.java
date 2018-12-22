package com.prb.erp.domain.program.menu;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMenu is a Querydsl query type for Menu
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMenu extends EntityPathBase<Menu> {

    private static final long serialVersionUID = -667858784L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMenu menu = new QMenu("menu");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Integer> lv = createNumber("lv", Integer.class);

    public final StringPath menuCode = createString("menuCode");

    public final StringPath menuGrpCd = createString("menuGrpCd");

    public final StringPath menuNm = createString("menuNm");

    public final StringPath menuSubCd = createString("menuSubCd");

    public final SimplePath<com.fasterxml.jackson.databind.JsonNode> multiLanguageJson = createSimple("multiLanguageJson", com.fasterxml.jackson.databind.JsonNode.class);

    public final StringPath parentCode = createString("parentCode");

    public final StringPath progCd = createString("progCd");

    public final com.prb.erp.domain.program.QProgram program;

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QMenu(String variable) {
        this(Menu.class, forVariable(variable), INITS);
    }

    public QMenu(Path<? extends Menu> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMenu(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMenu(PathMetadata metadata, PathInits inits) {
        this(Menu.class, metadata, inits);
    }

    public QMenu(Class<? extends Menu> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.program = inits.isInitialized("program") ? new com.prb.erp.domain.program.QProgram(forProperty("program")) : null;
    }

}

