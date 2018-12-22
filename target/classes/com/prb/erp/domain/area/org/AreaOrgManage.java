package com.prb.erp.domain.area.org;

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
@Table(name = "TB_ERP_AREA100")
@Comment(value = "지역별 조직코드")
@ToString
@JsonInclude(Include.NON_NULL)
@IdClass(AreaOrgManage.AreaOrgManageId.class)
@Alias("AreaOrgManage")
public class AreaOrgManage extends BaseJpaModel<AreaOrgManage.AreaOrgManageId> {  

	@Id
    @Column(name = "AREA_CD", length = 20)
    @Comment(value = "지역사코드")
    @ColumnPosition(1)
    private String areaCd;
	
	@Id
    @Column(name = "ORG_CLASS", length = 20)
    @Comment(value = "조직 분류")
    @ColumnPosition(2)
    private String orgClass;
	
	@Id
    @Column(name = "ORG_DEPTH", length = 20)
    @Comment(value = "조직깊이")
    @ColumnPosition(3)
    private String orgDepth;
    
	@Id
    @Column(name = "ORG_CD", length = 20)
    @Comment(value = "조직코드")
    @ColumnPosition(4)
    private String orgCd;

    @Column(name = "ORG_NM", length = 100)
    @Comment(value = "조직명")
    @ColumnPosition(5)
    private String orgNm;

    @Column(name = "PARENT_ORG_CD", length = 20)
    @Comment(value = "상위조직")
    @ColumnPosition(6)
    private String parentOrgCd;    

    @Column(name = "REMARK", length = 2000)
    @Comment(value = "비고")
    @ColumnPosition(7)
    private String remark;        
    
    @Column(name = "SORT", precision = 10)
    @Comment(value = "정렬")
    @ColumnPosition(8)
    private int sort;

	@Column(name = "USE_YN", length = 20)
    @ColumnPosition(9)
	@Comment(value = "사용유무")
	private String useYn;	

@Override
public AreaOrgManageId getId() {
return AreaOrgManageId.of(areaCd,orgClass,orgDepth,orgCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class AreaOrgManageId implements Serializable {

	@NonNull
	private String areaCd;
	@NonNull
	private String orgClass;
	@NonNull
	private String orgDepth;
	@NonNull
	private String orgCd;
}
	
}
