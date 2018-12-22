package com.prb.erp.domain.key.management;


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
@Table(name = "TB_ERP_KEY100")
@Comment(value = "유형별 키관리 테이블")
@IdClass(KeyManagement.CompanyKeyManagementId.class)
@Alias("CompanyKeyManagement")
public class KeyManagement extends BaseJpaModel<KeyManagement.CompanyKeyManagementId> {
	@Id
	@Column(name = "CODE_TYPE", length = 30, nullable = false)
	@Comment(value = "KEY유형")   
    @ColumnPosition(1)
	private String codeType;

	@Id
	@Column(name = "SEQ", length = 20, nullable = false)
	@Comment(value = "SEQ")
    @ColumnPosition(2)
	private Long seq;

	@Id
	@Column(name = "RESULT_CODE", length = 50, nullable = false)
	@Comment(value = "결과값")   
    @ColumnPosition(3)
	private String resultCode;

@Override
public CompanyKeyManagementId getId() {
return CompanyKeyManagementId.of(codeType,seq);
}


  
@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class CompanyKeyManagementId implements Serializable {

	@NonNull
	private String codeType;
	@NonNull
	private Long seq;
}
}