package com.prb.erp.domain.tcher.inf.salesEdu;


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
@Table(name = "TB_ERP_TCHER162")
@Comment(value = "교사관리-매출정보 (인터페이스)::방문")
@IdClass(TcherInfSalesEdu.TcherInfSalesEduId.class)
@Alias("TcherInfSalesEdu")
public class TcherInfSalesEdu extends BaseJpaModel<TcherInfSalesEdu.TcherInfSalesEduId> {

	@Id
	@Column(name = "TCHER_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "사번")
	private String tcherCd;	
	
	@Id
	@Column(name = "SALES_YYYYMM", length = 10)
    @ColumnPosition(2)
	@Comment(value = "발생일")
	private String salesYyyymm;	
	
	@Column(name = "PLAN_SU", precision = 16, scale = 4)
    @ColumnPosition(3)
	@Comment(value = "")
	private BigDecimal planSu;		
	
	@Column(name = "RESULT_SU", precision = 16, scale = 4)
    @ColumnPosition(4)
	@Comment(value = "")
	private BigDecimal resultSu;	
	
	@Column(name = "EFFECT_RIZ", precision = 16, scale = 4)
    @ColumnPosition(5)
	@Comment(value = "")
	private BigDecimal effectRiz;	
	
	@Column(name = "GNL_AMT", precision = 16, scale = 4)
    @ColumnPosition(6)
	@Comment(value = "")
	private BigDecimal gnlAmt;	

	@Column(name = "PRE_AMT", precision = 16, scale = 4)
	@Comment(value = "")
    @ColumnPosition(7)
	private BigDecimal preAmt;
	
	@Column(name = "TRANS_AMT", precision = 16, scale = 4)
	@Comment(value = "")
    @ColumnPosition(8)
	private BigDecimal transAmt;
	
	@Column(name = "IF_YN", length = 10)
    @ColumnPosition(9)  
	@Comment(value = "if데이터유무")
	private String ifYn;	
@Override
public TcherInfSalesEduId getId() {
return TcherInfSalesEduId.of(tcherCd,salesYyyymm);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class TcherInfSalesEduId implements Serializable {
	@NonNull
	private String tcherCd;
	@NonNull
	private String salesYyyymm;
}
}