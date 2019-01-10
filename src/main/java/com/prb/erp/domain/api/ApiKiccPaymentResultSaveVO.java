package com.prb.erp.domain.api;

import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ApiKiccPaymentResultSaveVO {

    @ApiModelProperty(value = "계약코드", required = true)
    private String custCd;

    @ApiModelProperty(value = "kicc 응답코드", required = false)
    private String resCd;

    @ApiModelProperty(value = "kicc 응답메시지", required = false)
    private String resMsg;

    @ApiModelProperty(value = "pg거래번호", required = false)
    private String cno;

    @ApiModelProperty(value = "총 결제금액", required = false)
    private String amount;

    @ApiModelProperty(value = "주문번호", required = false)
    private String orderNo;

    @ApiModelProperty(value = "승인번호", required = false)
    private String authNo;

    @ApiModelProperty(value = "승인일시", required = false)
    private String tranDate;

    @ApiModelProperty(value = "에스크로 사용유무", required = false)
    private String escrowYn;

    @ApiModelProperty(value = "복합결제 유무", required = false)
    private String complexYn;

    @ApiModelProperty(value = "상태코드", required = false)
    private String statCd;

    @ApiModelProperty(value = "상태메시지", required = false)
    private String statMsg;

    @ApiModelProperty(value = "결제수단", required = false)
    private String payType;

    @ApiModelProperty(value = "가맹점 Mall ID", required = false)
    private String mallId;

    @ApiModelProperty(value = "카드번호", required = false)
    private String cardNo;

    @ApiModelProperty(value = "발급사코드", required = false)
    private String issuerNo;

    @ApiModelProperty(value = "발급사명", required = false)
    private String issuerNm;

    @ApiModelProperty(value = "매입사코드", required = false)
    private String acquirerCd;

    @ApiModelProperty(value = "매입사명", required = false)
    private String acquirerNm;

    @ApiModelProperty(value = "할부개월", required = false)
    private String installPeriod;

    @ApiModelProperty(value = "무이자여부", required = false)
    private String noint;

    @ApiModelProperty(value = "부분취소 가능여부", required = false)
    private String partCancelYn;

    @ApiModelProperty(value = "신용카드 종류", required = false)
    private String cardGubun;

    @ApiModelProperty(value = "신용카드 구분", required = false)
    private String cardBizGubun;

    @ApiModelProperty(value = "쿠폰 사용유무", required = false)
    private String cponFlag;

    @ApiModelProperty(value = "쿠폰 사용금액", required = false)
    private String usedCpon;

    @ApiModelProperty(value = "매입취소 일시", required = false)
    private String cancAcqDate;

    @ApiModelProperty(value = "취소일시", required = false)
    private String cancDate;

    @ApiModelProperty(value = "계좌번호", required = false)
    private String accountNo;

}
