package com.prb.erp.domain.froebel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FroebelConstractVO {

    @JsonProperty(value = "TREE_CODE")
    private String treeCode = "";    //지국 A1 필수
    @JsonProperty(value = "DEPT_CODE")
    private String deptCode = "";    //부서코드
    @JsonProperty(value = "PERSON_NUMB")
    private String personNum = "";   //상담사원코드1 1000000001 필수
    @JsonProperty(value = "PERSON_NUMB2")
    private String personNum2 = "";   //상담사원코드2 1000000001
    @JsonProperty(value = "CONT_DATE")
    private String contDate = ""; // 계약일 (yyyymmdd) 공휴일 안됨
    @JsonProperty(value = "DEL_REQ_DATE")
    private String delReqDate = "";  //배본 희망일 (yyyymmdd) 공휴일암됨, 계약일보다 이전일자 허용 안됨
    @JsonProperty(value = "CONT_STATE")
    private String contState = "";   //계약상태
    @JsonProperty(value = "ACC_YN")
    private String accYn;   //계산서 발급 유무 (1:현금영수증, 2:세금계산서, 3: 무)
    @JsonProperty(value = "CUST_CODE")
    private String custCode = "";    //계약고객코드 필수
    @JsonProperty(value = "CUST_NAME")
    private String custName = "";    //계약고객명
    @JsonProperty(value = "REPRE_NUM")
    private String repreNum = "";    //계약고객 주민등록번호
    @JsonProperty(value = "SUB_CUST_NAME")
    private String subCustName = ""; //부고객명
    @JsonProperty(value = "SUB_REPRE_NUM")
    private String subRepreNum = ""; //부고객 주민등록번호
    @JsonProperty(value = "ZIP_NO")
    private String zipNo = "";   //우편번호 필수
    @JsonProperty(value = "ADDR")
    private String addr = "";    //주소 필수
    @JsonProperty(value = "ADDR2")
    private String addr2 = "";    //상세주소 필수
    @JsonProperty(value = "DEL_ZIP_NO")
    private String delZipNo = "";    //배송지 우편번호
    @JsonProperty(value = "DEL_ADDR")
    private String delAddr = "";    //배송지 주소 필수
    @JsonProperty(value = "DEL_ADDR2")
    private String delAddr2 = "";    //배송지 상세주소 필수
    @JsonProperty(value = "AMOUNT")
    private String amount = "";  //상품금액
    @JsonProperty(value = "PAY_WAY")
    private String payWay = "";  //결제방법 필수
    @JsonProperty(value = "ALLOT_MON")
    private String allotMon = "";    //할부개월
    @JsonProperty(value = "CREDIT_CMP")
    private String creditCmp = "";   //카드발급사
    @JsonProperty(value = "CARDPRINT_NAME")
    private String cardPrintName = "";   //카드 명의자 이름
    @JsonProperty(value = "CREDIT_NUM")
    private String creditNum = "";   //카드번호
    @JsonProperty(value = "CREDIT_ANUM")
    private String creditAnum = "";  //카드승인번호
    @JsonProperty(value = "APPROVAL_DATE")
    private String approvalDate = "";    //승인일자
    @JsonProperty(value = "VALID_DATE")
    private String validDate = "";   //카드유효기간
    @JsonProperty(value = "CREDIT_DEMAND")
    private String creditDemand = "";    //키드청구상태
    @JsonProperty(value = "CREDIT_MONEY")
    private String creditMoney = "0"; //카드결제금액
    @JsonProperty(value = "CARD_BILL")
    private String cardBill = "N";    //엑셀표에 없음 디폴트 N
    @JsonProperty(value = "IS_PHONE")
    private String isPhone = ""; //전화등록여부
    @JsonProperty(value = "CMS_BANK_CODE")
    private String cmsBankCode = ""; //은행코드
    @JsonProperty(value = "CMS_ACC_NUMB")
    private String cmsAccNumb = "";  //계좌번호
    @JsonProperty(value = "CMS_PAY_DATE")
    private String cmsPayDate = "";  //cms결제일
    @JsonProperty(value = "CMS_REGNUMB")
    private String cmsRegNum = "";   //계좌주인의 주민번호
    @JsonProperty(value = "CMS_MONEY")
    private String cmsMoney = "";    //cms 결제금액
    @JsonProperty(value = "CMS_STATUS")
    private String cmsStatus = "";   //처리상(00)
    @JsonProperty(value = "CONT_TYPE")
    private String contType = "";    //cms계약금약정(1.현금, 2.현금아님)
    @JsonProperty(value = "CONT_AMT")
    private String contAmt = ""; //계약금액
    @JsonProperty(value = "IS_TRANSFER")
    private String isTansfer = "";   //이체여 필수 (00)
    @JsonProperty(value = "IS_COMPLETE")
    private String isComplete = "";  //완료여 (0)
    @JsonProperty(value = "PERSON_TYPE")
    private String personType = "";  //사원구분 (12)
    @JsonProperty(value = "CONT_REC_YN")
    private String contRecYn = "";   //계약서 접수유무
    @JsonProperty(value = "TRANSFER_YN")
    private String transferYn = "";  //이체배본여부
    @JsonProperty(value = "TRANSFER_STATE")
    private String transferState = "";
    @JsonProperty(value = "WEATHER_CODE")
    private String weatherCode = ""; //상담경로 (1)
    @JsonProperty(value = "PAY_YN")
    private String payYn = "";   //상담역본인구입분 (N)
    @JsonProperty(value = "ETC")
    private String etc = ""; //비고
    @JsonProperty(value = "CREATE_USERID")
    private String createUserId = "";    //입력(상담사원 사원번호)
    @JsonProperty(value = "ITEM_CODE")
    private String itemCode = "";    //상품코드
    @JsonProperty(value = "ITEM_NAME")
    private String itemName = "";    //상품이름
    @JsonProperty(value = "QTY")
    private String qty = ""; //수량
    @JsonProperty(value = "PRICE")
    private String price = "";   //상품의 금액
    @JsonProperty(value = "EDU_HOPE_DATE")
    private String eduHopeDate = "";
    @JsonProperty(value = "EDU_HOPE_YN")
    private String eduHopeYn = "";
    @JsonProperty(value = "CHILD_CODE1")
    private String childCode1 = "";
    @JsonProperty(value = "CHILD_CODE2")
    private String childCode2 = "";
    @JsonProperty(value = "CHILD_CODE3")
    private String childCode3 = "";
    @JsonProperty(value = "CHILD_CODE4")
    private String childCode4 = "";
    @JsonProperty(value = "BIL_STATUS")
    private String bilStatus = "";
    @JsonProperty(value = "DEL_STATUS")
    private String delStatus = "";

    public FroebelConstractVO(){}

    public FroebelConstractVO (String deptCode, String personNum, String contDate, String delReqDate,
                              String custCode, String custName, String repreNum, String zipNo, String addr, String addr2,
                              String delZipNo, String delAddr, String delAddr2, String amount, String allotMon,
                              String cmsBankCode, String cmsAccNumb, String cmsPayDate, String cmsRegNum, String cmsMoney,
                              String contType, String createUserId, String itemCode, String itemName, String price) {
        this.treeCode = "A1";
        this.deptCode = deptCode;
        this.personNum = personNum;
        this.contDate = contDate;
        this.delReqDate = delReqDate;
        this.contState = "1";
        this.accYn = "3";
        this.custCode = custCode;
        this.custName = custName;
        this.repreNum = repreNum;
        this.zipNo = zipNo;
        this.addr = addr;
        this.addr2 = addr2;
        this.delZipNo = delZipNo;
        this.delAddr = delAddr;
        this.delAddr2 = delAddr2;
        this.amount = amount;
        this.payWay = "3";
        this.allotMon = allotMon;
        this.isPhone = "0";
        this.cmsBankCode = cmsBankCode;
        this.cmsAccNumb = cmsAccNumb;
        this.cmsPayDate = cmsPayDate;
        this.cmsRegNum = cmsRegNum;
        this.cmsMoney = cmsMoney;
        this.cmsStatus = "00";
        this.contType = contType.equals("30") ? "1" : "2";
        this.contAmt = "100000";
        this.isTansfer = "00";
        this.isComplete = "0";
        this.weatherCode = "1";
        this.createUserId = createUserId;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.qty = "1";
        this.price = price;
        this.eduHopeYn = "N";
        this.bilStatus = "2";
        this.delStatus = "40";
        this.personType = "2";
    }

}

