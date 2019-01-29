package com.prb.erp.domain.api;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiMemberManageSaveVO {

    @ApiModelProperty(value="계약번호", required = false)
	private String custCd;	    
    @ApiModelProperty(value="판매유형(1:초등패키지/2:영유아패키지)", required = true,allowableValues = "1,2")
    @NotEmpty(message = "판매유형 입력하세요.")
	private String salesType;    
    @ApiModelProperty(value="보호자1이름 (length = 100)" , required = true)
    @NotEmpty(message = "보호자 입력하세요.")
	private String gd1Nm;	    
    @ApiModelProperty(value="보호자1_관계(YYYY-MM-DD), 0:부 1:모 2:조부 3:조모 4 기타", required = true,allowableValues = "0,1,2,3,4")
    @NotEmpty(message = "보호자1_관계 입력하세요.")
	private String gd1RelationCd;	    
    @ApiModelProperty(value="보호자1_생년월일", required = true)
    private String gd1Birthday;	    
    
    
    @ApiModelProperty(value="보호자2이름 (length = 100)", required = false)
	private String gd2Nm;	        
    @ApiModelProperty(value="보호자2_관계, 0:부 1:모 2:조부 3:조모 4 기타", required = false, allowableValues = "0,1,2,3,4")
	private String gd2RelationCd;	    
    @ApiModelProperty(value="보호자2_생년월일(YYYY-MM-DD)", required = false)
	private String gd2Birthday;	    
    @ApiModelProperty(value="전화번호(000-0000-0000)", required = false)
    private String telNo;	    
    @ApiModelProperty(value="휴대전화(000-0000-0000)", required = true)
    @NotEmpty(message = "휴대전화 입력하세요.")
	private String hpNo;		    
    @ApiModelProperty(value="이메일주소(XXX@XXX.COM) (length = 400)", required = true)
    @NotEmpty(message = "이메일주소 입력하세요.")
    private String email;        
    @ApiModelProperty(value="집주소_우편번호", required = true)
    @NotEmpty(message = "집주소_우편번호 입력하세요.")
	private String homeZipcode;    
    @ApiModelProperty(value="집주소_주소1 (length = 400)", required = true)
    @NotEmpty(message = "집주소_주소1 입력하세요.")
	private String homeAddress1;    
    @ApiModelProperty(value="집주소_주소2 (length = 400)", required = true)
    @NotEmpty(message = "집주소_주소2 입력하세요.")
    private String homeAddress2;        
    @ApiModelProperty(value="배송주소_우편번호", required = true)
    @NotEmpty(message = "배송주소_우편번호 입력하세요.")
    private String deliveryZipcode;
    @ApiModelProperty(value="배송주소_주소1 (length = 400)", required = true)
    @NotEmpty(message = "배송주소_주소1 입력하세요.")
    private String deliveryAddress1;
    @ApiModelProperty(value="배송주소_주소2 (length = 400)", required = true)
    @NotEmpty(message = "배송주소_주소2 입력하세요.")
    private String deliveryAddress2;        
    @ApiModelProperty(value="계약상품코드", required = true)
    @NotEmpty(message = "계약상품코드 입력하세요.")
    private String goodsCd;			
	@ApiModelProperty(value="계약일(YYYY-MM-DD)", required = true)
    @NotEmpty(message = "계약일 입력하세요.")
	private String contractDt;		
	@ApiModelProperty(value="과목명  (length = 200)", required = false)
	private String subjectNm;	
	@ApiModelProperty(value="약정기간, 24:24개월 36:36개월", required = true,allowableValues = "24, 36")
	private String agreementCd;	
	@ApiModelProperty(value="총 금액", required = true)
	private BigDecimal totalPrice;	
	@ApiModelProperty(value="월 납부금액", required = true)
	private BigDecimal monthPrice;	
	@ApiModelProperty(value="계약금", required = true)
	private BigDecimal contractPrice;
	@ApiModelProperty(value="결제금액", required = true)
	private BigDecimal paymentPrice;	
	
    @ApiModelProperty(value="계약금결제방법, 10:분납 20:일시납 30:무료", required = false ,allowableValues = "10,20,30")
    private String contractPaymentWay;			
    @ApiModelProperty(value="계약금결제수단, 10:신용카드정기출금 20:CMS정기출금 30:현금(계좌이체) 40:신용카드 50:100%무료 60: 18개월무료", required = false, allowableValues = "10,20,30,40,50,60")
    private String contractPaymentMethod;			
    @ApiModelProperty(value="상품결제방법, 10:분납 20:일시납 30:무료", required = false ,allowableValues = "10,20,30")
    private String paymentWay;			
    @ApiModelProperty(value="상품결제수단, 10:신용카드정기출금 20:CMS정기출금 30:현금(계좌이체) 40:신용카드 50:100%무료 60: 18개월무료", required = false, allowableValues = "10,20,30,40,50,60")
    private String paymentMethod;			

	//자녀정보
	@ApiModelProperty(value="자녀계약코드", required = false)
	private String childCd;	
	@ApiModelProperty(value="자녀명 (length = 100)", required = true)
    @NotEmpty(message = "자녀명 입력하세요")
	private String childrenNm;	
	@ApiModelProperty(value="성별, 0:남자 1:여자", required = true, allowableValues = "0,1")
    @NotEmpty(message = "성별 입력하세요")
	private String childrenSex;	
	@ApiModelProperty(value="생년월일(YYYY-MM-DD)", required = true)
    @NotEmpty(message = "생년월일 입력하세요")
	private String childrenBirthday;	
	@ApiModelProperty(value="휴대전화번호(000-0000-0000)", required = false)
	private String childrenHpNo;	
	@ApiModelProperty(value="학교명 (length = 100)", required = false)
	private String childrenSchoolNm;	
	@ApiModelProperty(value="학년,0:미취학 1:1학년 2:2학년 3:3학년 4:4학년 5:5학년 6:6학년", required = false, allowableValues = "0,1,2,3,4,5,6")
	private String childrenGradeCd;	
	@ApiModelProperty(value="온라인 컨텐츠 신청유무, Y:신청 N:미신청", required = true, allowableValues = "Y,N")
    @NotEmpty(message = "온라인 컨텐츠 신청유무 입력하세요")
	private String onlineServiceYn;		
	@ApiModelProperty(value="온라인 컨텐츠 교육비", required = false)
	private BigDecimal onlineServicePrice;
	@ApiModelProperty(value="온라인서비스 결제방법, 10:분납 20:일시납 30:무료", required = true, allowableValues = "10,20,30")
    @NotEmpty(message = "온라인서비스 결제방법 입력하세요")
	private String onlinePaymentWay;	
	@ApiModelProperty(value="온라인서비스 결제수단, 10:신용카드정기출금 20:CMS정기출금 30:현금(계좌이체) 40:신용카드 50:100%무료 60: 18개월무료", required = true, allowableValues = "10,20,30,40,50,60")
	@NotEmpty(message = "온라인서비스 결제수단 입력하세요")
	private String onlinePaymentMethod;		
	@ApiModelProperty(value="방문교사 서비스 신청유무", required = true ,allowableValues = "Y,N")
	@NotEmpty(message = "방문교사 서비스 신청유무 입력하세요")
	private String visitServiceYn;	
	@ApiModelProperty(value="방문교육 시간(분) 10~60분 (10,20,30,40,50,60)", required = false, allowableValues = "10,20,30,40,50,60")
	private String visitTimeCd;		
	@ApiModelProperty(value="방문 횟수(월) 1~4회 (1,2,3,4)", required = false, allowableValues = "1,2,3,4")
	private String visitNumberCd;		
	@ApiModelProperty(value="방문교사 서비스 교육비", required = false)
	private BigDecimal visitServicePrice;		
	@ApiModelProperty(value="방문서비스 결제방법, 10:분납 20:일시납 30:무료", required = true, allowableValues = "10,20,30")
	@NotEmpty(message = "방문서비스 결제방법 입력하세요")
	private String visitPaymentWay;			
	@ApiModelProperty(value="방문서비스 결제수단, 10:신용카드정기출금 20:CMS정기출금 30:현금(계좌이체) 40:신용카드 50:100%무료 60: 18개월무료", required = true, allowableValues = "10,20,30,40,50,60")
	@NotEmpty(message = "방문서비스 결제수단 입력하세요")
	private String visitPaymentMethod;	
	//교사정보
	@ApiModelProperty(value="지역사(교사 로그인 지역사)", required = true)
	@NotEmpty(message = "지역사 입력하세요")
	private String areaCd;		
	@ApiModelProperty(value="지국(교사 로그인 지국)", required = true)
	@NotEmpty(message = "지국 입력하세요")
	private String orgCd;		
	@ApiModelProperty(value="전화번호(000-0000-0000)", required = false)
	private String areaHpNo;	
	@ApiModelProperty(value="상담사원1(교사사번)", required = true)
	@NotEmpty(message = "상담사원1(교사사번) 입력하세요")
	private String counselor1TcherCd;	
	@ApiModelProperty(value="휴대전화번호1(000-0000-0000)", required = true)
	@NotEmpty(message = "상담사원1(휴대전화번호) 입력하세요")
	private String counselor1TcherHpNo;		
	@ApiModelProperty(value="상담사원2(교사사번)", required = false)
	private String counselor2TcherCd;	
	@ApiModelProperty(value="휴대전화번호2(000-0000-0000)", required = false)
	private String counselor2TcherHpNo;

	@ApiModelProperty(value = "은행코드(은행코드 API 참조)", required = false)
	private String bankCd;

	@ApiModelProperty(value = "예금주", required = false)
	private String bankAccountNm;

	@ApiModelProperty(value = "계좌번호", required = false)
	private String bankAccountNo;

	@ApiModelProperty(value = "출금요청일 5,10,15,20,25,30일", required = false, allowableValues = "5,10,15,20,25,30")
	private Integer withDrawDay;
}