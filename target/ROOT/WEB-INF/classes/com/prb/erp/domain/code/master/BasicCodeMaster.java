package com.prb.erp.domain.code.master;

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
@Table(name = "TB_ERP_CODE000")
@Comment(value = "")
@IdClass(BasicCodeMaster.BasicCodeMasterId.class)
@Alias("basicCodeMaster")   
public class BasicCodeMaster extends  BaseJpaModel<BasicCodeMaster.BasicCodeMasterId> {
		
	@Id
	@Column(name = "MAIN_CODE", length = 50, nullable = false)
    @ColumnPosition(1)
	@Comment(value = "대분류")
	private String mainCode;
	
	@Column(name = "MAIN_NAME", length = 200, nullable = false)
    @ColumnPosition(2)
	@Comment(value = "대분류명")
	private String mainName;	

	@Column(name = "REMARK", length = 4000)
    @ColumnPosition(3)
	@Comment(value = "비고")
	private String remark;	
	
	@Column(name = "USE_YN", length = 20)
    @ColumnPosition(4)
	@Comment(value = "사용유무")
	private String useYn;
	  
	public static BasicCodeMaster of(String mainCode, String mainName) {
		BasicCodeMaster basicCode = new BasicCodeMaster();
		basicCode.setMainCode(mainCode);
		basicCode.setMainName(mainName);
		basicCode.setUseYn("Y");
        return basicCode;
    }

	
	
	@Override
	public BasicCodeMasterId getId() {
	return BasicCodeMasterId.of(mainCode);
	}
	@Embeddable
	@Data
	@NoArgsConstructor
	@RequiredArgsConstructor(staticName = "of")
	public static class BasicCodeMasterId implements Serializable {
			@NonNull
			private String mainCode;
	}
}