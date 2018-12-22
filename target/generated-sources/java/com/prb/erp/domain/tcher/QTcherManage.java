package com.prb.erp.domain.tcher;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTcherManage is a Querydsl query type for TcherManage
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTcherManage extends EntityPathBase<TcherManage> {

    private static final long serialVersionUID = -672476577L;

    public static final QTcherManage tcherManage = new QTcherManage("tcherManage");

    public final com.prb.erp.domain.QBaseJpaModel _super = new com.prb.erp.domain.QBaseJpaModel(this);

    public final StringPath bankAccountNm = createString("bankAccountNm");

    public final StringPath bankAccountNo = createString("bankAccountNo");

    public final StringPath bankCd = createString("bankCd");

    public final StringPath bringTcherCd = createString("bringTcherCd");

    public final StringPath ccOrgCd = createString("ccOrgCd");

    public final StringPath ccRankCd = createString("ccRankCd");

    public final StringPath companyRegNum = createString("companyRegNum");

    public final StringPath companyType = createString("companyType");

    public final StringPath countryCd = createString("countryCd");

    //inherited
    public final DateTimePath<java.time.Instant> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath eduMonth = createString("eduMonth");

    public final StringPath eduYear = createString("eduYear");

    public final StringPath guaranteeDocCd = createString("guaranteeDocCd");

    public final StringPath ifYn = createString("ifYn");

    public final StringPath incomeType = createString("incomeType");

    public final StringPath joinDt = createString("joinDt");

    public final StringPath localCd = createString("localCd");

    public final StringPath orgCd = createString("orgCd");

    public final StringPath outDt = createString("outDt");

    public final StringPath outReasonCd = createString("outReasonCd");

    public final StringPath rankCd = createString("rankCd");

    public final StringPath remark = createString("remark");

    public final StringPath residenceType = createString("residenceType");

    public final StringPath sectCode = createString("sectCode");

    public final StringPath tcherAddress1 = createString("tcherAddress1");

    public final StringPath tcherAddress2 = createString("tcherAddress2");

    public final StringPath tcherBirthday = createString("tcherBirthday");

    public final StringPath tcherCd = createString("tcherCd");

    public final StringPath tcherEmail = createString("tcherEmail");

    public final StringPath tcherEmailType = createString("tcherEmailType");

    public final StringPath tcherHpNo = createString("tcherHpNo");

    public final StringPath tcherNm = createString("tcherNm");

    public final StringPath tcherTelNo = createString("tcherTelNo");

    public final StringPath tcherType = createString("tcherType");

    public final StringPath tcherZipcode = createString("tcherZipcode");

    public final StringPath tempFileCd = createString("tempFileCd");

    //inherited
    public final DateTimePath<java.time.Instant> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath workCd = createString("workCd");

    public final StringPath workReasonCd = createString("workReasonCd");

    public final StringPath workRegCd = createString("workRegCd");

    public QTcherManage(String variable) {
        super(TcherManage.class, forVariable(variable));
    }

    public QTcherManage(Path<? extends TcherManage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTcherManage(PathMetadata metadata) {
        super(TcherManage.class, metadata);
    }

}

