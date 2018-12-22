package com.prb.erp.domain.tcher;


import java.io.Serializable;

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
@Table(name = "TB_ERP_TCHER000")
@Comment(value = "교사관리")
@IdClass(TcherManage.TcherManageId.class)
@Alias("TcherManage")
public class TcherManage extends BaseJpaModel<TcherManage.TcherManageId> {

	@Id
	@Column(name = "TCHER_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "사번")
	private String tcherCd;	
	
	@Column(name = "TCHER_NM", length = 100)
    @ColumnPosition(2)
	@Comment(value = "교사명")
	private String tcherNm;	

    @Column(name = "TCHER_TYPE", length = 20)
    @Comment(value = "교사유형")
    @ColumnPosition(3)
    private String tcherType;    
    
	@Column(name = "TCHER_TEL_NO", length = 15)
    @ColumnPosition(4)
	@Comment(value = "교사전화")
	private String tcherTelNo;	
    
	@Column(name = "TCHER_HP_NO", length = 15)
    @ColumnPosition(5)
	@Comment(value = "교사휴대전화")
	private String tcherHpNo;	
	
	@Column(name = "TCHER_EMAIL", length = 200)
    @ColumnPosition(6)
	@Comment(value = "이메일")
	private String tcherEmail;	

    @Column(name = "TCHER_EMAIL_TYPE", length = 100)
    @Comment(value = "이메일")
    @ColumnPosition(7)
    private String tcherEmailType;    

	@Column(name = "LOCAL_CD", length = 20)
    @ColumnPosition(8)
	@Comment(value = "내외국인구분")
	private String localCd;	
	
	@Column(name = "TCHER_BIRTHDAY", length = 15)
    @ColumnPosition(9)
	@Comment(value = "생년월일")
	private String tcherBirthday;	

	@Column(name = "COUNTRY_CD", length = 20)
    @ColumnPosition(10)
	@Comment(value = "국가코드")
	private String countryCd;	
	
	@Column(name = "RESIDENCE_TYPE", length = 20)
    @ColumnPosition(11)
	@Comment(value = "거주구분")
	private String residenceType;	

	@Column(name = "TCHER_ZIPCODE", length = 20)
    @ColumnPosition(12)
	@Comment(value = "우편번호")
	private String tcherZipcode;		

	@Column(name = "TCHER_ADDRESS1", length = 400)
    @ColumnPosition(13)
	@Comment(value = "주소1")
	private String tcherAddress1;	
	
	@Column(name = "TCHER_ADDRESS2", length = 400)
    @ColumnPosition(14)
	@Comment(value = "주소2")
	private String tcherAddress2;

	@Column(name = "JOIN_DT", length = 15)
    @ColumnPosition(15)
	@Comment(value = "입사일")
	private String joinDt;	

	@Column(name = "OUT_DT", length = 15)
    @ColumnPosition(15)
	@Comment(value = "퇴사일")
	private String outDt;	

	@Column(name = "OUT_REASON_CD", length = 20)
    @ColumnPosition(16)
	@Comment(value = "퇴사사유코드")
	private String outReasonCd;		

	@Column(name = "WORK_CD", length = 20)
    @ColumnPosition(17)
	@Comment(value = "근무상태")
	private String workCd;	

	@Column(name = "WORK_REASON_CD", length = 20)
    @ColumnPosition(18)
	@Comment(value = "사유코드")
	private String workReasonCd;	

	@Column(name = "WORK_REG_CD", length = 20)
    @ColumnPosition(18)
	@Comment(value = "사원등록구분")
	private String workRegCd;	
	
	@Column(name = "ORG_CD", length = 20)
	@Comment(value = "조직")
    @ColumnPosition(20)
	private String orgCd;

	@Column(name = "RANK_CD", length = 20)
	@Comment(value = "직급")
    @ColumnPosition(21)
	private String rankCd;	
	
	@Column(name = "CC_ORG_CD", length = 200)
	@Comment(value = "겸직 센터")
    @ColumnPosition(23)
	private String ccOrgCd;
	
	@Column(name = "CC_RANK_CD", length = 20)
	@Comment(value = "겸직직급")
    @ColumnPosition(24)
	private String ccRankCd;

	@Column(name = "EDU_YEAR", length = 4)
    @ColumnPosition(26)
	@Comment(value = "교육월일")
	private String eduYear;
	
	@Column(name = "EDU_MONTH", length = 4)
    @ColumnPosition(27)
	@Comment(value = "교육월일")
	private String eduMonth;

	@Column(name = "GUARANTEE_DOC_CD", length = 20)
    @ColumnPosition(28)
	@Comment(value = "보증서류상태")
	private String guaranteeDocCd;	

	@Column(name = "INCOME_TYPE", length = 20)
    @ColumnPosition(29)
	@Comment(value = "소득자구분")
	private String incomeType;	

	@Column(name = "COMPANY_TYPE", length = 20)
    @ColumnPosition(30)
	@Comment(value = "법인/개인구분")
	private String companyType;	

	@Column(name = "SECT_CODE", length = 20)
    @ColumnPosition(31)
	@Comment(value = "신고사업장")
	private String sectCode;	
	
	@Column(name = "COMPANY_REG_NUM", length = 20)
    @ColumnPosition(32)
	@Comment(value = "사업자번호")
	private String companyRegNum;	

	@Column(name = "BANK_CD", length = 20)
    @ColumnPosition(33)
	@Comment(value = "은행코드")
	private String bankCd;	
	
	@Column(name = "BANK_ACCOUNT_NM", length=200)
	@Comment(value = "예금주")
    @ColumnPosition(34)
	private String bankAccountNm;	

	@Column(name = "BANK_ACCOUNT_NO", length = 50)
    @ColumnPosition(35)
	@Comment(value = "계좌번호")
	private String bankAccountNo;	

	@Column(name = "REMARK",  columnDefinition = "TEXT")
    @ColumnPosition(36)
	@Comment(value = "기타사항")
	private String remark;	

	@Column(name = "TEMP_FILE_CD", length = 40)
    @ColumnPosition(37)
	@Comment(value = "TEMP FILE")
	private String tempFileCd;		

	@Column(name = "BRING_TCHER_CD", length = 20)
    @ColumnPosition(38)
	@Comment(value = "사번")
	private String bringTcherCd;	
	
	@Column(name = "IF_YN", length = 10)
    @ColumnPosition(39)
	@Comment(value = "if데이터유무")
	private String ifYn;	
    
	@Override
	public TcherManageId getId() {
		return TcherManageId.of(tcherCd);
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@RequiredArgsConstructor(staticName = "of")
	public static class TcherManageId implements Serializable {
			@NonNull
			private String tcherCd;
	}
	
}