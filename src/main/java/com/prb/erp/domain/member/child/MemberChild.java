package com.prb.erp.domain.member.child;


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
@Table(name = "TB_ERP_MEMBER200")
@Comment(value = "회원(계약) 상세 - 자녀")
@IdClass(MemberChild.MemberChildId.class)
@Alias("MemberChild")
public class MemberChild extends BaseJpaModel<MemberChild.MemberChildId> {

	@Id
	@Column(name = "CUST_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "회원코드(계약)")
	private String custCd;	

	@Id
	@Column(name = "CHILD_CD", length = 20)
    @ColumnPosition(2)
	@Comment(value = "자녀코드")
	private String childCd;		

	@Column(name = "PARENT_CHILD_CD", length = 20)
    @ColumnPosition(2)
	@Comment(value = "상위 자녀계약코드")
	private String parentChildCd;	
	
	@Column(name = "CHILD_REPRE_NUM", length = 30)
    @ColumnPosition(3)
	@Comment(value = "자녀주민번호")
	private String childRefreNum;	
	
	@Column(name = "CHILDREN_CONTRACT_DT", length = 10)
    @ColumnPosition(3)
	@Comment(value = "계약일")
	private String childrenContractDt;	

	@Column(name = "CONTRACT_TYPE", length = 20)
    @ColumnPosition(3)
	@Comment(value = "계약종류")
	private String contractType;	
	
	@Column(name = "CHILDREN_USER_CD", length = 20)
    @ColumnPosition(4)
	@Comment(value = "학습자 아이디")
	private String childrenUserCd;	

	@Column(name = "CHILDREN_NM", length = 100)
    @ColumnPosition(5)
	@Comment(value = "자녀명")
	private String childrenNm;	

	@Column(name = "CHILDREN_SEX", length = 20)
    @ColumnPosition(6)
	@Comment(value = "자녀명")
	private String childrenSex;	

	@Column(name = "CHILDREN_BIRTHDAY", length = 10)
    @ColumnPosition(7)
	@Comment(value = "생년월일")
	private String childrenBirthday;	

	@Column(name = "CHILDREN_HP_NO", length = 15)
    @ColumnPosition(8)
	@Comment(value = "휴대전화번호")
	private String childrenHpNo;	

	@Column(name = "CHILDREN_SCHOOL_NM", length = 100)
    @ColumnPosition(9)
	@Comment(value = "학교명")
	private String childrenSchoolNm;	

	@Column(name = "CHILDREN_GRADE_CD", length = 20)
    @ColumnPosition(10)
	@Comment(value = "학년")
	private String childrenGradeCd;			

	@Column(name = "ONLINE_SERVICE_STATUS", length = 20)
    @ColumnPosition(11)
	@Comment(value = "온라인서비스")
	private String onlineServiceStatus;	
	
	@Column(name = "ONLINE_SERVICE_YN", length=20)
	@Comment(value = "온라인서비스 신청유무")
    @ColumnPosition(12)
	private String onlineServiceYn;	

	@Column(name = "ONLINE_SERVICE_PRICE", precision = 15, scale = 2)
	@Comment(value = "온라인서비스교육금액")
    @ColumnPosition(13)
	private BigDecimal onlineServicePrice;		

	@Column(name = "ONLINE_PAYMENT_WAY", length = 20)
	@Comment(value = "온라인서비스결제방법")
    @ColumnPosition(14)
	private String onlinePaymentWay;	

	@Column(name = "ONLINE_PAYMENT_METHOD", length = 20)
	@Comment(value = "온라인결제수단")
    @ColumnPosition(15)
	private String onlinePaymentMethod;	

	@Column(name = "VISIT_SERVICE_STATUS", length = 20)
    @ColumnPosition(16)
	@Comment(value = "방문서비스")
	private String visitServiceStatus;	

	@Column(name = "VISIT_SERVICE_YN", length=20)
	@Comment(value = "방문서비스 교육 신청유무")
    @ColumnPosition(17)
	private String visitServiceYn;	

	@Column(name = "VISIT_NUMBER_CD", length = 20)
    @ColumnPosition(18)
	@Comment(value = "방문교사수업-횟수")
	private String visitNumberCd;		

	@Column(name = "VISIT_TIME_CD", length = 20)
    @ColumnPosition(19)
	@Comment(value = "방문교사수업-수업시간")
	private String visitTimeCd;		
	
	@Column(name = "VISIT_SERVICE_PRICE", precision = 15, scale = 2)
	@Comment(value = "방문서비스교육금액")
    @ColumnPosition(20)
	private BigDecimal visitServicePrice;		

	@Column(name = "VISIT_START_DT", length = 10)
    @ColumnPosition(21)
	@Comment(value = "방문교사무료수업-시작일")
	private String visitStartDt;		

	@Column(name = "VISIT_END_DT", length = 10)
    @ColumnPosition(22)
	@Comment(value = "방문교사무료수업-종료일")
	private String visitEndDt;		

	@Column(name = "VISIT_PAYMENT_WAY", length = 20)
	@Comment(value = "방문서비스결제방법")
    @ColumnPosition(23)
	private String visitPaymentWay;	
	
	@Column(name = "VISIT_PAYMENT_METHOD", length = 20)
	@Comment(value = "방문서비스결제수단")
    @ColumnPosition(24)
	private String visitPaymentMethod;	


	@Column(name = "COUNSELOR1_TCHER_CD", length = 100)
    @ColumnPosition(28)
	@Comment(value = "상담사원1 이름")
	private String counselor1TcherCd;	
	
	@Column(name = "COUNSELOR1_TCHER_HP_NO", length = 15)
    @ColumnPosition(29)
	@Comment(value = "상담사원1 전화")
	private String counselor1TcherHpNo;	

	@Column(name = "COUNSELOR2_TCHER_CD", length = 100)
    @ColumnPosition(30)
	@Comment(value = "상담사원2 이름")
	private String counselor2TcherCd;	

	@Column(name = "COUNSELOR2_TCHER_HP_NO", length = 15)
    @ColumnPosition(31)
	@Comment(value = "상담사원2 전화")
	private String counselor2TcherHpNo;	

	@Column(name = "VISITOR_TCHER_CD", length = 100)
    @ColumnPosition(32)
	@Comment(value = "방문교사")
	private String visitorTcherCd;	
	
	@Column(name = "VISITOR_TCHER_HP_NO", length = 15)
    @ColumnPosition(33)
	@Comment(value = "방문교사 전화")
	private String visitorTcherHpNo;	

	@Column(name = "REMARK", length = 1000)
    @ColumnPosition(34)
	@Comment(value = "비고")
	private String remark;	
	
	@Column(name = "MEMBER_STATUS", length = 20)
	@Comment(value = "계약상태")
	@ColumnPosition(35)
	private String memberStatus;

	@Column(name = "CHILD_IMG_URL", length = 400)
    @ColumnPosition(36)
	@Comment(value = "자녀이미지경로")
	private String childImgUrl;	

	@Column(name = "CHILD_IMG_REG_DT", length = 10)
    @ColumnPosition(37)
	@Comment(value = "자녀이미지등록일")
	private String childImgRegDt;	
	
	@Column(name = "CHILD_IMG_BY", length = 255)
    @ColumnPosition(38)
	@Comment(value = "자녀 이미지 등록자")
	private String childImgBy;	
	
	@Column(name = "IF_YN", length = 10)
    @ColumnPosition(39)
	@Comment(value = "if데이터유무")
	private String ifYn;	
    
@Override
public MemberChildId getId() {
return MemberChildId.of(custCd,childCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class MemberChildId implements Serializable {

	@NonNull
	private String custCd;
	@NonNull
	private String childCd;
}
	
}