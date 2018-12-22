package com.prb.erp.domain.item.product;

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
@Table(name = "TB_ERP_PROD000")
@Comment(value = "제품관리")
@IdClass(ProductManage.ProductManageId.class)
@Alias("ProductManage")
public class ProductManage extends BaseJpaModel<ProductManage.ProductManageId> {

	@Id
	@Column(name = "PRODUCT_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "제품코드")
	private String productCd;	
	
	@Column(name = "PRODUCT_NM", length = 200)
    @ColumnPosition(3)
	@Comment(value = "제품명")
	private String productNm;	
	
	@Column(name = "PRODUCT_CLASS", length = 20)
    @ColumnPosition(4)
	@Comment(value = "제품분류")
	private String productClass;

	@Column(name = "PRODUCT_TYPE", length = 20)
	@Comment(value = "제품유형")
    @ColumnPosition(5)
	private String productType;

	@Column(name = "PRODUCT_STEP1", length = 20)
    @ColumnPosition(6)
	@Comment(value = "제품단계구분1")
	private String productStep1;
	
	@Column(name = "PRODUCT_STEP2", length = 20)
    @ColumnPosition(7)
	@Comment(value = "제품단계구분2")
	private String productStep2;

	@Column(name = "COST_PRICE", precision = 15, scale = 2)
	@Comment(value = "제작원가")
    @ColumnPosition(8)
	private BigDecimal costPrice;	

	@Column(name = "AS_PRICE", precision = 15, scale = 2)
	@Comment(value = "AS가")
    @ColumnPosition(9)
	private BigDecimal asPrice;

	@Column(name = "TEMP_FILE_CD", length = 40)
    @ColumnPosition(10)
	@Comment(value = "TEMP FILE")
	private String tempFileCd;		
    
	
@Override
public ProductManageId getId() {
return ProductManageId.of(productCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class ProductManageId implements Serializable {
		@NonNull
		private String productCd;
} 
	
}