package com.prb.erp.domain.tcher.inf.salesHappy;


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
@Table(name = "TB_ERP_TCHER161")
@Comment(value = "교사관리-매출정보 (인터페이스)::상담")
@IdClass(TcherInfSalesHappy.TcherInfSalesHappyId.class)
@Alias("TcherInfSalesHappy")
public class TcherInfSalesHappy extends BaseJpaModel<TcherInfSalesHappy.TcherInfSalesHappyId> {

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
	
	@Column(name = "PLAN_AMT", precision = 16, scale = 4)
    @ColumnPosition(3)
	@Comment(value = "")
	private BigDecimal planAmt;		
	
	@Column(name = "CONT_SALE_AMT", precision = 16, scale = 4)  
    @ColumnPosition(4)
	@Comment(value = "")
	private BigDecimal contSaleAmt;	
	
	@Column(name = "PERCENTAGE", precision = 16, scale = 4)
    @ColumnPosition(5)
	@Comment(value = "")
	private BigDecimal percentage;	
	
	@Column(name = "WAGES_SUM_AMT", precision = 16, scale = 4)
    @ColumnPosition(6)
	@Comment(value = "")
	private BigDecimal wagesSumAmt;	

	@Column(name = "DED_SUM_AMT", precision = 16, scale = 4)
	@Comment(value = "")
    @ColumnPosition(7)
	private BigDecimal dedSumAmt;
	
	@Column(name = "REAL_SUM_AMT", precision = 16, scale = 4)
	@Comment(value = "")
    @ColumnPosition(8)
	private BigDecimal realSumAmt;
	
	@Column(name = "IF_YN", length = 10)
    @ColumnPosition(9)  
	@Comment(value = "if데이터유무")
	private String ifYn;	
@Override
public TcherInfSalesHappyId getId() {
return TcherInfSalesHappyId.of(tcherCd,salesYyyymm);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class TcherInfSalesHappyId implements Serializable {
	@NonNull
	private String tcherCd;
	@NonNull
	private String salesYyyymm;
}
}