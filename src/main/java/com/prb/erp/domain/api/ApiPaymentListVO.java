package com.prb.erp.domain.api;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApiPaymentListVO {

    private String custCd;

    private String childCd;

    private String gd1Nm;

    private String childrenNm;

    private String childrenGradeCd;

    private String goodsNm;

    private String visitTimeCdNm;

    private String visitNumberCdNm;

    private String visitStartDt;

    private String visitEndDt;

    private String visitorTcherNm;

    private String agreementCdNm;

    private BigDecimal totalPrice;

    private String custLoginId;

    private String childLoginId;
}
