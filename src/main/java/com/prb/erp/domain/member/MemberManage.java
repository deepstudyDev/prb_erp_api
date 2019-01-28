package com.prb.erp.domain.member;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.chequer.axboot.core.annotations.ColumnPosition;
import com.chequer.axboot.core.annotations.Comment;
import com.prb.erp.domain.BaseJpaModel;
import com.prb.erp.domain.member.child.MemberChild;
import com.prb.erp.domain.member.item.MemberItem;

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
@Table(name = "TB_ERP_MEMBER000")
@Comment(value = "회원")
@IdClass(MemberManage.MemberManageId.class)
@Alias("MemberManage")
public class MemberManage extends BaseJpaModel<MemberManage.MemberManageId> {

	@Id
	@Column(name = "CONT_CD", length = 20)
	@ColumnPosition(1)
	@Comment(value = "계약(계약코드)")
	private String contCd;

	@Id
	@Column(name = "CUST_CD", length = 20)
	@ColumnPosition(2)
	@Comment(value = "회원코드(계약코드)")
	private String custCd;

	@Column(name = "AREA_CD", length = 20)
	@ColumnPosition(3)
	@Comment(value = "지역사")
	private String areaCd;

	@Column(name = "ORG_CD", length = 20)
	@ColumnPosition(4)
	@Comment(value = "센터")
	private String orgCd;

	@Column(name = "AREA_HP_NO", length = 20)
	@ColumnPosition(4)
	@Comment(value = "지국 전화번호")
	private String areaHpNo;

	@Column(name = "REPRE_NUM", length = 20)
	@ColumnPosition(5)
	@Comment(value = "회원주민번호")
	private String repreNum;

	@Column(name = "GD1_NM", length = 100)
	@ColumnPosition(6)
	@Comment(value = "보호자1이름")
	private String gd1Nm;

	@Column(name = "GD1_RELATION_CD", length = 20)
	@ColumnPosition(7)
	@Comment(value = "보호자1관계")
	private String gd1RelationCd;

	@Column(name = "GD1_BIRTHDAY", length = 10)
	@ColumnPosition(8)
	@Comment(value = "보호자 생년월일")
	private String gd1Birthday;

	@Column(name = "GD1_USER_CD", length = 20)
	@ColumnPosition(9)
	@Comment(value = "계약자 아이디")
	private String gd1UserCd;

	@Column(name = "GD2_NM", length = 100)
	@ColumnPosition(10)
	@Comment(value = "보호자2이름")
	private String gd2Nm;

	@Column(name = "GD2_RELATION_CD", length = 20)
	@ColumnPosition(11)
	@Comment(value = "보호자2관계")
	private String gd2RelationCd;

	@Column(name = "GD2_BIRTHDAY", length = 10)
	@ColumnPosition(12)
	@Comment(value = "보호자2관계")
	private String gd2Birthday;

	@Column(name = "TEL_NO", length = 20)
	@ColumnPosition(13)
	@Comment(value = "보호자1휴대전화")
	private String telNo;

	@Column(name = "HP_NO", length = 20)
	@ColumnPosition(14)
	@Comment(value = "보호자2휴대전화")
	private String hpNo;

	@Column(name = "EMAIL", length = 400)
	@Comment(value = "이메일")
	@ColumnPosition(15)
	private String email;

	@Column(name = "EMAIL_TYPE", length = 100)
	@Comment(value = "이메일")
	@ColumnPosition(16)
	private String emailType;

	@Column(name = "HOME_ZIPCODE", length = 20)
	@Comment(value = "집우편번호")
	@ColumnPosition(17)
	private String homeZipcode;

	@Column(name = "HOME_ADDRESS1", length = 400)
	@Comment(value = "집주소1")
	@ColumnPosition(18)
	private String homeAddress1;

	@Column(name = "HOME_ADDRESS2", length = 400)
	@Comment(value = "집주소2")
	@ColumnPosition(19)
	private String homeAddress2;

	@Column(name = "DELIVERY_ZIPCODE", length = 20)
	@Comment(value = "배송우편번호")
	@ColumnPosition(20)
	private String deliveryZipcode;

	@Column(name = "DELIVERY_ADDRESS1", length = 400)
	@Comment(value = "배송주소1")
	@ColumnPosition(21)
	private String deliveryAddress1;

	@Column(name = "DELIVERY_ADDRESS2", length = 400)
	@Comment(value = "배송주소2")
	@ColumnPosition(22)
	private String deliveryAddress2;

	@Column(name = "TMSG_SEQ", length = 12)
	@Comment(value = "전문일련번호")
	@ColumnPosition(23)
	private String tmsgSeq;

	@Column(name = "TEMP_FILE_CD", length = 40)
	@ColumnPosition(24)
	@Comment(value = "TEMP FILE")
	private String tempFileCd;

	@Column(name = "IF_YN", length = 10)
	@ColumnPosition(25)
	@Comment(value = "if데이터유무")
	private String ifYn;

	//결제기능 추가 2019. 01. 11
//	@Column(name = "BANK_CD", length = 20)
//	@ColumnPosition(26)
//	@Comment(value = "은행코드")
//	private String bankCd;
//
//	@Column(name = "BANK_ACCOUNT_NM", length=200)
//	@Comment(value = "예금주")
//	@ColumnPosition(27)
//	private String bankAccountNm;
//
//	@Column(name = "BANK_ACCOUNT_NO", length = 50)
//	@ColumnPosition(28)
//	@Comment(value = "계좌번호")
//	private String bankAccountNo;
//
//	@Column(name = "WITH_DRAW_DAY", length = 2)
//	@ColumnPosition(29)
//	@Comment(value = "출금요청일")
//	private String withDrawDay;

	@Transient
	private MemberItem itemList;

	@Transient
	private List<MemberChild> childList;

	@Override
	public MemberManageId getId() {
		return MemberManageId.of(custCd);
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@RequiredArgsConstructor(staticName = "of")
	public static class MemberManageId implements Serializable {

		@NonNull
		private String custCd;
	}
}