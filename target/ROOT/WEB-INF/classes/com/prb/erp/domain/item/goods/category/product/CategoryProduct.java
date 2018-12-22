package com.prb.erp.domain.item.goods.category.product;

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
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_ERP_GOODS150")
@Comment(value = "카테고리별 제품")
@IdClass(CategoryProduct.CategoryProductId.class)
@Alias("CategoryProduct")
public class CategoryProduct extends BaseJpaModel<CategoryProduct.CategoryProductId> { 

	@Id
    @Column(name = "GOODS_CD", length = 50)
    @Comment(value = "상품코드")
    @ColumnPosition(1)
    private String goodsCd;
    
	@Id
    @Column(name = "CATEGORY_CD", length = 50)
    @Comment(value = "메뉴 코드")
    @ColumnPosition(1)
    private String categoryCd;	

	@Id
	@Column(name = "PRODUCT_CD", length = 20)
    @ColumnPosition(3)
	@Comment(value = "제품코드")
	private String productCd;	

@Override
public CategoryProductId getId() {
return CategoryProductId.of(goodsCd,categoryCd,productCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class CategoryProductId implements Serializable {

	@NonNull
	private String goodsCd;
	@NonNull
	private String categoryCd;
	@NonNull
	private String productCd;
}
	
}
