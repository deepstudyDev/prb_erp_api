package com.prb.erp.domain.item.goods.category;

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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.prb.erp.domain.BaseJpaModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_ERP_GOODS100")
@Comment(value = "상품 카테고리")
@ToString
@JsonInclude(Include.NON_NULL)
@IdClass(GoodsCategory.GoodsCategoryId.class)
@Alias("GoodsCategory")
public class GoodsCategory extends BaseJpaModel<GoodsCategory.GoodsCategoryId> { 

	@Id
    @Column(name = "GOODS_CD", length = 50)
    @Comment(value = "상품코드")
    @ColumnPosition(1)
    private String goodsCd;
    
	@Id
    @Column(name = "CATEGORY_CD", length = 50)
    @Comment(value = "메뉴 코드")
    @ColumnPosition(2)
    private String categoryCd;

	@Column(name = "GOODS_CATEGORY_CD", length = 20)
    @ColumnPosition(3)
	@Comment(value = "제품-항목")
	private String goodsCategoryCd;	
	

    @Column(name = "CATEGORY_NM", length = 100)
    @Comment(value = "메뉴명")
    @ColumnPosition(3)
    private String categoryNm;

    @Column(name = "CATEGORY_TYPE", length = 100)
    @Comment(value = "카테고리유형")
    @ColumnPosition(4)
    private String categoryType;

    @Column(name = "SORT", precision = 10)
    @Comment(value = "정렬")
    @ColumnPosition(5)
    private int sort;

@Override
public GoodsCategoryId getId() {
return GoodsCategoryId.of(goodsCd,categoryCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class GoodsCategoryId implements Serializable {

	@NonNull
	private String goodsCd;
	@NonNull
	private String categoryCd;
}
	
}
