package com.prb.erp.domain.tcher.inf.charge;


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
@Table(name = "TB_ERP_TCHER150")
@Comment(value = "교사관리-인사정보 (인터페이스)")
@IdClass(TcherInfCharge.TcherInfChargeId.class)
@Alias("TcherInfCharge")
public class TcherInfCharge extends BaseJpaModel<TcherInfCharge.TcherInfChargeId> {

	@Id
	@Column(name = "TCHER_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "사번")
	private String tcherCd;	
	
	@Id
	@Column(name = "CHARGE_DT", length = 10)
    @ColumnPosition(2)
	@Comment(value = "발생일")
	private String chargeDt;	
	
	@Id
	@Column(name = "CHARGE_CODE", length = 20)
    @ColumnPosition(3)
	@Comment(value = "사유코드")
	private String chargeCode;	
	
	
	@Column(name = "COMPANY_NAME", length = 20)
    @ColumnPosition(4)
	@Comment(value = "회사명")
	private String companyName;	
	
	@Column(name = "SECT_CODE", length = 20)
    @ColumnPosition(5)
	@Comment(value = "신고사업장")
	private String sectCode;	
	
	@Column(name = "ORG_CD", length = 20)
    @ColumnPosition(6)
	@Comment(value = "조직코드")
	private String orgCd;	

	@Column(name = "RANK_CD", length = 20)
	@Comment(value = "직급")
    @ColumnPosition(7)
	private String rankCd;	
	
	@Column(name = "CC_ORG_CD", length = 200)
	@Comment(value = "겸직 센터")
    @ColumnPosition(9)
	private String ccOrgCd;
	
	@Column(name = "CC_RANK_CD", length = 20)
	@Comment(value = "겸직직급")
    @ColumnPosition(10)
	private String ccRankCd;

	@Column(name = "CHARGE_DTCD", length = 20)
	@Comment(value = "휴직코드")
    @ColumnPosition(12)
	private String chargeDtcd;

	@Column(name = "REMARK",  columnDefinition = "TEXT")
    @ColumnPosition(13)
	@Comment(value = "비고")
	private String remark;	

	@Column(name = "IF_YN", length = 10)
    @ColumnPosition(14)
	@Comment(value = "if데이터유무")
	private String ifYn;	
@Override
public TcherInfChargeId getId() {
return TcherInfChargeId.of(tcherCd,chargeDt,chargeCode);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class TcherInfChargeId implements Serializable {
	@NonNull
	private String tcherCd;
	@NonNull
	private String chargeDt;
	@NonNull
	private String chargeCode;
}
}