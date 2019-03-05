package com.prb.erp.domain.api;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ApiPaymentInfoVO {

    private String custCd;

    private String childCd;

    private String childrenNm;

    private String childrenGradeCd;

    private String goodsNm;

    private String subjectNm;

    private String visitEndDt;

    private String homeAddress1;

    private String homeAddress2;

    private String gd1Nm;

    private String gd1RelationCdNm;

    private String hpNo;

    private String childrenSexNm;

    private String childrenBirthday;

    private String childrenSchoolNm;

    private String agreementCdNm;

    private String visitNumberCdNm;

    private String visitTimeCdNm;

    private String consultTcherNm;

    private String consultTcherOrgNm;

    private String visitTcherNm;

    private String visitTcherOrgNm;

    private String contractPrice;

    private String contractPaymentMethodNm;

    private String cardCompanyName;

    private String cardNo;

    private String installPeriod;

    private String bankNm;

    private String bankAccountNm;

    private String totalPrice;

    private String paymentPrice;

    private String monthPrice;

    private String paymentMethod;

    private String paymentType;

    private String withDrawDay;

}
