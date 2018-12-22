package com.prb.erp.domain.tcher.inf.tcher;


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
@Table(name = "TB_ERP_TCHER170")
@Comment(value = "교사관리-교사정보 (인터페이스)")
@IdClass(TcherInfTcher.TcherInfTcherId.class)
@Alias("TcherInfTcher")
public class TcherInfTcher extends BaseJpaModel<TcherInfTcher.TcherInfTcherId> {

	@Id
	@Column(name = "TCHER_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "사번")
	private String tcherCd;	
	
	@Column(name = "RATE1", precision = 16, scale = 4)
    @ColumnPosition(2)
	@Comment(value = "발생일")
	private BigDecimal rate1;	
	
	@Column(name = "RATE2", precision = 16, scale = 4)
    @ColumnPosition(3)
	@Comment(value = "사유코드")
	private BigDecimal rate2;		
	
	@Column(name = "PAY_START_YYYYMM", length = 8)
    @ColumnPosition(4)
	@Comment(value = "")
	private String payStartYyyymm;	
	
	@Column(name = "RATE_MONTH1", length = 2)
    @ColumnPosition(5)
	@Comment(value = "")
	private String rateMonth1;	
	
	@Column(name = "RATE_MONTH2", length = 2)
    @ColumnPosition(6)
	@Comment(value = "")
	private String rateMonth2;	

	@Column(name = "RATE_MGR", precision = 16, scale = 4)
	@Comment(value = "")
    @ColumnPosition(7)
	private BigDecimal rateMgr;

	@Column(name = "MGR_START_YYYYMM", length = 8)
	@Comment(value = "")
    @ColumnPosition(8)
	private String mgrStartYyyymm; 
	
	@Column(name = "ADD_RATE", precision = 16, scale = 4)
	@Comment(value = "")
    @ColumnPosition(9)
	private BigDecimal addRate; 
	
	@Column(name = "NRATE1", precision = 16, scale = 4)
	@Comment(value = "")
    @ColumnPosition(10)
	private BigDecimal nrate1; 
	
	@Column(name = "NRATE2", precision = 16, scale = 4)
	@Comment(value = "")
    @ColumnPosition(11)
	private BigDecimal nrate2; 
	
	@Column(name = "WEEKDAY", length = 20)
	@Comment(value = "")
    @ColumnPosition(12)
	private String weekday; 
	
	@Column(name = "IF_YN", length = 10)
    @ColumnPosition(9)
	@Comment(value = "if데이터유무")
	private String ifYn;	
@Override
public TcherInfTcherId getId() {
return TcherInfTcherId.of(tcherCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class TcherInfTcherId implements Serializable {
	@NonNull
	private String tcherCd;
}
}