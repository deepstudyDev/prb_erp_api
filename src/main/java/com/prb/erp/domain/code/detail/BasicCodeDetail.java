package com.prb.erp.domain.code.detail;

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
import org.hibernate.annotations.Type;

import com.chequer.axboot.core.annotations.ColumnPosition;
import com.chequer.axboot.core.annotations.Comment;
import com.chequer.axboot.core.code.AXBootTypes;
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
@Table(name = "TB_ERP_CODE100")
@Comment(value = "")
@IdClass(BasicCodeDetail.BasicCodeDetailId.class)
@Alias("basicCodeDetail")
@EqualsAndHashCode
public class BasicCodeDetail extends BaseJpaModel<BasicCodeDetail.BasicCodeDetailId> {
	
	
	@Id
	@Column(name = "MAIN_CODE", length = 50, nullable = false)
    @ColumnPosition(1)
	@Comment(value = "대분류") 
	private String mainCode;
	
	@Id
	@Column(name = "SUB_CODE", length = 50, nullable = false)
    @ColumnPosition(2)
	@Comment(value = "소분류")
	private String subCode;
	
	@Column(name = "SUB_NAME", length = 200, nullable = false)
    @ColumnPosition(3)
	@Comment(value = "소분류명")
	private String subName;
	
	@Column(name = "SORT", precision = 10, scale = 0)
    @ColumnPosition(4)
	@Comment(value = "정렬순서")
	private Integer sort;
	
	@Column(name = "DATA1", length = 100)
    @ColumnPosition(5)
	@Comment(value = "")
	private String data1;
	
	@Column(name = "DATA2", length = 100)
    @ColumnPosition(6)
	@Comment(value = "")
	private String data2;
	
	@Column(name = "DATA3", length = 100)
    @ColumnPosition(7)
	@Comment(value = "")
	private String data3;
	
	@Column(name = "DATA4", precision = 10, scale = 0)
    @ColumnPosition(8)
	@Comment(value = "")
	private BigDecimal data4;
	
	@Column(name = "DATA5", precision = 10, scale = 0)
    @ColumnPosition(9)
	@Comment(value = "")
	private BigDecimal data5;
	
	@Column(name = "REMARK", length = 4000)
    @ColumnPosition(10)
	@Comment(value = "비고")
	private String remark;	
	
	@Column(name = "USE_YN", length = 1)
    @ColumnPosition(11)
	@Comment(value = "사용유무")
    private String useYn = "Y";
	
	public static BasicCodeDetail of(String mainCode,String subCode, String subName,Integer sort) {
		BasicCodeDetail basicCode = new BasicCodeDetail();
		basicCode.setMainCode(mainCode);
		basicCode.setSubCode(subCode);
		basicCode.setSubName(subName);
		basicCode.setSort(sort);
		basicCode.setUseYn("Y");
        return basicCode;
    }
	public static BasicCodeDetail of(String mainCode,String subCode, String subName,Integer sort,String data1) {
		BasicCodeDetail basicCode = new BasicCodeDetail();
		basicCode.setMainCode(mainCode);
		basicCode.setSubCode(subCode);
		basicCode.setSubName(subName);
		basicCode.setData1(data1);
		basicCode.setSort(sort);
		basicCode.setUseYn("Y");
        return basicCode;
    }
	
	//사용유무
	public static BasicCodeDetail of(String mainCode,String subCode, String subName,Integer sort,String data1,String useYn) {
		BasicCodeDetail basicCode = new BasicCodeDetail();
		basicCode.setMainCode(mainCode);
		basicCode.setSubCode(subCode);
		basicCode.setSubName(subName);
		basicCode.setData1(data1);
		basicCode.setSort(sort);
		basicCode.setUseYn(useYn);
        return basicCode;
    }

	public static BasicCodeDetail of(String mainCode,String subCode, String subName,String remark,Integer sort) {
		BasicCodeDetail basicCode = new BasicCodeDetail();
		basicCode.setMainCode(mainCode);
		basicCode.setSubCode(subCode);
		basicCode.setSubName(subName);
		basicCode.setRemark(remark);
		basicCode.setSort(sort);
		basicCode.setUseYn("Y");
        return basicCode;
    }

	
	public BasicCodeDetailId getId() {
		return BasicCodeDetailId.of(mainCode, subCode);
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@RequiredArgsConstructor(staticName = "of")
	public static class BasicCodeDetailId implements Serializable {
			@NonNull
			private String mainCode;
			@NonNull
			private String subCode;
	}
}