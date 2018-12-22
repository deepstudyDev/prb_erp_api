package com.prb.erp.domain.item.goods.type;

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
@Table(name = "TB_ERP_GOODS050")
@Comment(value = "상품관리-항목관리")
@IdClass(GoodsTypeManage.GoodsTypeManageId.class)
@Alias("GoodsTypeManage")
public class GoodsTypeManage extends BaseJpaModel<GoodsTypeManage.GoodsTypeManageId> {

	@Id
	@Column(name = "GOODS_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "제품코드")
	private String goodsCd;		

	@Id
	@Column(name = "GOODS_CATEGORY_TYPE", length = 20)
    @ColumnPosition(2)
	@Comment(value = "제품-항목")
	private String goodsCategoryType;		

	@Id
	@Column(name = "GOODS_CATEGORY_CD", length = 20)
    @ColumnPosition(3)
	@Comment(value = "제품-항목")
	private String goodsCategoryCd;	
	
	@Column(name = "USE_YN", length = 20)
    @ColumnPosition(4)
	@Comment(value = "제품-항목")
	private String useYn;	

@Override
public GoodsTypeManageId getId() {
return GoodsTypeManageId.of(goodsCd,goodsCategoryType,goodsCategoryCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class GoodsTypeManageId implements Serializable {
	@NonNull
	private String goodsCd;
	@NonNull
	private String goodsCategoryType;
	@NonNull
	private String goodsCategoryCd;
}
	
}