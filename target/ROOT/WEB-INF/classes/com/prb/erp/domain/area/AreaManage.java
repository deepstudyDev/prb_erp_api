package com.prb.erp.domain.area;


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
@Table(name = "TB_ERP_AREA000")
@Comment(value = "지역사관리")
@IdClass(AreaManage.AreaManageId.class)
@Alias("AreaManage")
public class AreaManage extends BaseJpaModel<AreaManage.AreaManageId> {

	@Id
	@Column(name = "AREA_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "지역사 코드")
	private String areaCd;	
	
	@Column(name = "AREA_NM", length = 20)
    @ColumnPosition(2)
	@Comment(value = "지역사 명")
	private String areaNm;	

	@Column(name = "LOCATION_CD", length = 20)
    @ColumnPosition(3)
	@Comment(value = "지역코드")
	private String locationCd;	
	
	@Column(name = "AREA_ZIPCODE", length = 20)
	@Comment(value = "집우편번호")
    @ColumnPosition(4)
	private String areaZipcode;
	
	@Column(name = "AREA_ADDRESS1", length = 400)
	@Comment(value = "집주소1")
    @ColumnPosition(5)
	private String areaAddress1;
	
	@Column(name = "AREA_ADDRESS2", length = 400)
	@Comment(value = "집주소2")
    @ColumnPosition(6)
	private String areaAddress2;    


	@Column(name = "AREA_TEL_NO", length = 15)
    @ColumnPosition(7)
	@Comment(value = "대표번호")
	private String areaTelNo;		

	@Column(name = "AREA_AS_TEL_NO", length = 15)
    @ColumnPosition(8)
	@Comment(value = "AS 번호")
	private String areaAsTelNo;		
	

	@Column(name = "AREA_FAX_NO", length = 15)
    @ColumnPosition(9)
	@Comment(value = "팩스번호")
	private String areaFaxNo;		

	@Column(name = "COMPANY_REG_NUM", length = 20)
    @ColumnPosition(10)
	@Comment(value = "사업자번호")
	private String companyRegNum;	
	
	@Column(name = "REMARK", length = 1000)
    @ColumnPosition(11)
	@Comment(value = "메모")
	private String remark;

	@Column(name = "USE_YN", length = 20)
    @ColumnPosition(12)
	@Comment(value = "사용유무")
	private String useYn;	
	

@Override
public AreaManageId getId() {
return AreaManageId.of(areaCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class AreaManageId implements Serializable {

		@NonNull
		private String areaCd;
}
	
}