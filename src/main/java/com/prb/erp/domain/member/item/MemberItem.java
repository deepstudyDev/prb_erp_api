package com.prb.erp.domain.member.item;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.chequer.axboot.core.annotations.ColumnPosition;
import com.chequer.axboot.core.annotations.Comment;
import com.prb.erp.domain.BaseJpaModel;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
 
 
@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "TB_ERP_MEMBER100")
@Comment(value = "회원(계약) 상품-금액")
@IdClass(MemberItem.MemberItemId.class)
@Alias("MemberItem")
public class MemberItem extends BaseJpaModel<MemberItem.MemberItemId> {

	@Id
	@Column(name = "CUST_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "회원코드(계약)")
	private String custCd;	
	
	@Column(name = "SALES_TYPE", length = 10)
    @ColumnPosition(2)
	@Comment(value = "상품종류")
	private String salesType;	
	
	@Column(name = "CONTRACT_DT", length = 10)
    @ColumnPosition(3)
	@Comment(value = "계약일")
	private String contractDt;	

	@Column(name = "GOODS_CD", length = 100)
    @ColumnPosition(4)
	@Comment(value = "계약상품")
	private String goodsCd;			

	@Column(name = "SUBJECT_NM", length = 200)
    @ColumnPosition(5)
	@Comment(value = "과목명")
	private String subjectNm;		

	@Column(name = "AGREEMENT_CD", length = 20)
    @ColumnPosition(6)
	@Comment(value = "약정기간")
	private String agreementCd;	

	@Column(name = "TOTAL_PRICE", precision = 15, scale = 2)
	@Comment(value = "총금액")
    @ColumnPosition(7)
	private BigDecimal totalPrice;	

	@Column(name = "MONTH_PRICE", precision = 15, scale = 2)
	@Comment(value = "월지불금액")
    @ColumnPosition(8)
	private BigDecimal monthPrice;	

	@Column(name = "CONTRACT_PRICE", precision = 15, scale = 2)
	@Comment(value = "계약금")
    @ColumnPosition(9)
	private BigDecimal contractPrice;

	@Column(name = "CONTRACT_PAYMENT_WAY", length = 20)
	@Comment(value = "계약금결제방법")
    @ColumnPosition(10)
	private String contractPaymentWay;	

	@Column(name = "CONTRACT_PAYMENT_METHOD", length = 20)
	@Comment(value = "계약금결제수단")
    @ColumnPosition(11)
	private String contractPaymentMethod;	

	@Column(name = "PAYMENT_PRICE", precision = 15, scale = 2)
	@Comment(value = "상품결제금액")
    @ColumnPosition(12)
	private BigDecimal paymentPrice;	

	@Column(name = "PAYMENT_WAY", length = 20)
	@Comment(value = "상품결제방법")
    @ColumnPosition(13)
	private String paymentWay;	

	@Column(name = "PAYMENT_METHOD", length = 20)
	@Comment(value = "상품결제수단")
    @ColumnPosition(14)
	private String paymentMethod;	

	@Column(name = "PAYMENT_YN", length = 20)
	@Comment(value = "납부현황")
    @ColumnPosition(15)
	private String paymentYn;

	//결제기능 추가 2019. 01. 11
	@Column(name = "BANK_CD", length = 20)
	@ColumnPosition(16)
	@Comment(value = "은행코드")
	private String bankCd;

	@Column(name = "BANK_ACCOUNT_NM", length=200)
	@Comment(value = "예금주")
	@ColumnPosition(17)
	private String bankAccountNm;

	@Column(name = "BANK_ACCOUNT_NO", length = 50)
	@ColumnPosition(18)
	@Comment(value = "계좌번호")
	private String bankAccountNo;

	@Column(name = "WITH_DRAW_DAY", length = 2)
	@ColumnPosition(19)
	@Comment(value = "출금요청일")
	private Integer withDrawDay;
    
@Override
public MemberItemId getId() {
return MemberItemId.of(custCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class MemberItemId implements Serializable {

	@NonNull
	private String custCd;
}
	
}