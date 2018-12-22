package com.prb.erp.domain.item.goods;
import java.io.Serializable;
import java.math.BigDecimal;
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
import com.prb.erp.domain.item.goods.type.GoodsTypeManage;

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
@Table(name = "TB_ERP_GOODS000")
@Comment(value = "상품관리")
@IdClass(GoodsManage.GoodsManageId.class)
@Alias("GoodsManage")
public class GoodsManage extends BaseJpaModel<GoodsManage.GoodsManageId> {

	@Id
	@Column(name = "GOODS_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "상품코드")
	private String goodsCd;	
	
	@Column(name = "GOODS_NM", length = 200)
    @ColumnPosition(2)
	@Comment(value = "상품명")
	private String goodsNm;	

	@Column(name = "SALES_TYPE", length = 10)
    @ColumnPosition(3)
	@Comment(value = "상품종류")
	private String salesType;	

	@Column(name = "AGREEMENT_CD", length = 20)
    @ColumnPosition(3)
	@Comment(value = "약정기간")
	private String agreementCd;	
	
	@Column(name = "GOODS_CLASS", length = 20)
    @ColumnPosition(4)
	@Comment(value = "상품분류")
	private String goodsClass;

	@Column(name = "GOODS_STEP1", length = 20)
    @ColumnPosition(5)
	@Comment(value = "상품단계구분1")
	private String goodsStep1;
	
	@Column(name = "GOODS_STEP2", length = 20)
    @ColumnPosition(6)
	@Comment(value = "상품단계구분2")
	private String goodsStep2;

	@Column(name = "SALES_YN", length = 20)
    @ColumnPosition(7)
	@Comment(value = "판매여부")
	private String salesYn;
	
	@Column(name = "SALES_COST_PRICE", precision = 15, scale = 2)
	@Comment(value = "판매원가")
    @ColumnPosition(8)
	private BigDecimal salesCostPrice;	

	@Column(name = "SALES_PRICE", precision = 15, scale = 2)
	@Comment(value = "판매가")
    @ColumnPosition(9)
	private BigDecimal salesPrice;		

	@Column(name = "GOODS_STATUS", length = 20)
    @ColumnPosition(10)
	@Comment(value = "상품상태")
	private String goodsStatus;		

	@Column(name = "BOOK_SERVICE_YN", length = 20)
    @ColumnPosition(11)
	@Comment(value = "전집(도서) 포함여부")
	private String bookServiceYn;			


	@Column(name = "BOOK_SERVICE_COST_PRICE", precision = 15, scale = 2)
	@Comment(value = "전집(도서) 판매원가")
    @ColumnPosition(12)
	private BigDecimal bookServiceCostPrice;	

	@Column(name = "BOOK_SERVICE_PRICE", precision = 15, scale = 2)
	@Comment(value = "전집(도서) 판매가")
    @ColumnPosition(13)
	private BigDecimal bookServicePrice;			

	@Column(name = "ONLINE_SERVICE_YN", length = 20)
    @ColumnPosition(14)
	@Comment(value = "온라인 포함여부")
	private String onlineServiceYn;			


	@Column(name = "ONLINE_SERVICE_COST_PRICE", precision = 15, scale = 2)
	@Comment(value = "온라인 판매원가")
    @ColumnPosition(15)
	private BigDecimal onlineServiceCostPrice;	

	@Column(name = "ONLINE_SERVICE_PRICE", precision = 15, scale = 2)
	@Comment(value = "온라인 판매가")
    @ColumnPosition(16)
	private BigDecimal onlineServicePrice;		

	@Column(name = "VISIT_SERVICE_YN", length = 20)
    @ColumnPosition(17)
	@Comment(value = "방문교사수업 포함여부")
	private String visitServiceYn;			

	@Column(name = "VISIT_NUMBER_CD", length = 20)
    @ColumnPosition(18)
	@Comment(value = "방문교사수업-횟수")
	private String visitNumberCd;		

	@Column(name = "VISIT_TIME_CD", length = 20)
    @ColumnPosition(19)
	@Comment(value = "방문교사수업-수업시간")
	private String visitTimeCd;		

	@Column(name = "VISIT_SERVICE_COST_PRICE", precision = 15, scale = 2)
	@Comment(value = "방문교사수업 판매원가")
    @ColumnPosition(20)
	private BigDecimal visitServiceCostPrice;	

	@Column(name = "VISIT_SERVICE_PRICE", precision = 15, scale = 2)
	@Comment(value = "방문교사수업 판매가")
    @ColumnPosition(21)
	private BigDecimal visitServicePrice;	
	
	@Column(name = "ETC_SERVICE_YN", length = 20)
    @ColumnPosition(22)
	@Comment(value = "부가서비스 포함여부")
	private String etcServiceYn;		

	@Column(name = "ETC_SERVICE_COST_PRICE", precision = 15, scale = 2)
	@Comment(value = "부가상품 판매원가")
    @ColumnPosition(23)
	private BigDecimal etcServiceCostPrice;	

	@Column(name = "ETC_SERVICE_PRICE", precision = 15, scale = 2)
	@Comment(value = "부가상품 판매가")
    @ColumnPosition(24)
	private BigDecimal etcServicePrice;	


    @Transient
    private List<GoodsTypeManage> booksTypeList;

    @Transient
    private List<GoodsTypeManage> onlineTypeList;
    
    @Transient
    private List<GoodsTypeManage> etcTypeList;

@Override
public GoodsManageId getId() {
return GoodsManageId.of(goodsCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class GoodsManageId implements Serializable {
		@NonNull
		private String goodsCd;
	}
}