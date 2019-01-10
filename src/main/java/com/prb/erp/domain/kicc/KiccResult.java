package com.prb.erp.domain.kicc;

import com.chequer.axboot.core.annotations.ColumnPosition;
import com.chequer.axboot.core.annotations.Comment;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.prb.erp.domain.BaseJpaModel;

import com.prb.erp.domain.SimpleJpaModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
public class KiccResult {


    private String idx;

    private String custCd;

    private String resCd;

    private String resMsg;

    private String cno;

    private String amount;

    private String orderNo;

    private String authNo;

    private String tranDate;

    private String escrowYn;

    private String complexYn;

    private String statCd;

    private String statMsg;

    private String payType;

    private String mallId;

    private String cardNo;

    private String issuerNo;

    private String issuerNm;

    private String acquirerCd;

    private String acquirerNm;

    private String installPeriod;

    private String noint;

    private String partCancelYn;

    private String cardGubun;

    private String cardBizGubun;

    private String cponFlag;

    private String usedCpon;

    private String cancAcqDate;

    private String cancDate;

    private String accountNo;

    public KiccResult() {}

    public KiccResult(String custCd, String resCd, String resMsg, String cno, String amount, String orderNo, String authNo,
                      String tranDate, String escrowYn, String complexYn, String statCd, String statMsg, String payType,
                      String mallId, String cardNo, String issuerNo, String issuerNm, String acquirerCd, String acquirerNm,
                      String installPeriod, String noint, String partCancelYn, String cardGubun, String cardBizGubun,
                      String cponFlag, String usedCpon, String cancAcqDate, String cancDate, String accountNo) {
        this.custCd = custCd;
        this.resCd = resCd;
        this.resMsg = resMsg;
        this.cno = cno;
        this.amount = amount;
        this.orderNo = orderNo;
        this.authNo = authNo;
        this.tranDate = tranDate;
        this.escrowYn = escrowYn;
        this.complexYn = complexYn;
        this.statCd = statCd;
        this.statMsg = statMsg;
        this.payType = payType;
        this.mallId = mallId;
        this.cardNo = cardNo;
        this.issuerNo = issuerNo;
        this.issuerNm = issuerNm;
        this.acquirerCd = acquirerCd;
        this.acquirerNm = acquirerNm;
        this.installPeriod = installPeriod;
        this.noint = noint;
        this.partCancelYn = partCancelYn;
        this.cardGubun = cardGubun;
        this.cardBizGubun = cardBizGubun;
        this.cponFlag = cponFlag;
        this.usedCpon = usedCpon;
        this.cancAcqDate = cancAcqDate;
        this.cancDate = cancDate;
        this.accountNo = accountNo;
    }
}
