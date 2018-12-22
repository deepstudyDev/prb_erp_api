package com.prb.erp.domain.tmsg;


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
@Table(name = "TMSG_KNCD")
@Comment(value = "TMSG_KNCD")
@IdClass(TmsgKncd.TmsgKncdId.class)
@Alias("TmsgKncd")
public class TmsgKncd extends BaseJpaModel<TmsgKncd.TmsgKncdId> {

	@Id
	@Column(name = "ORG_CD", length = 5)
    @ColumnPosition(1)
	@Comment(value = "기관코드")
	private String orgCd;	

	@Id
	@Column(name = "TMSG_KNCD", length = 10)
    @ColumnPosition(2)
	@Comment(value = "문서코드")
	private String tmsgKncd;	
	
	@Column(name = "TMSG_NM", length = 100)
    @ColumnPosition(3)
	@Comment(value = "전문명")
	private String tmsgNm;	
	
	@Column(name = "BIZ_NO", length = 10)
    @ColumnPosition(4)
	@Comment(value = "사업자번호")
	private String bizNo;	
	
	
	@Column(name = "DOC_NO", length = 20)
    @ColumnPosition(5)
	@Comment(value = "문서번호")
	private String docNo;	
	
	@Column(name = "PROC_TYPE", length = 1)
    @ColumnPosition(6)
	@Comment(value = "처리구분")
	private String procType;	
	
	@Column(name = "TMSG_TABLE_NM", length = 100)
    @ColumnPosition(7)
	@Comment(value = "처리테이블명")
	private String tmsgTableNm;	
	
	@Column(name = "TMSG_LAYOUT", length = 2000)
    @ColumnPosition(8)
	@Comment(value = "문서레이아웃")
	private String tmsgLayout;	
	
	@Column(name = "DOC_LINK_KEY", length = 100)
    @ColumnPosition(9)
	@Comment(value = "연계키컬럼")
	private String docLinkKey;	
	
	@Column(name = "DOC_USER_NM", length = 100)
    @ColumnPosition(10)
	@Comment(value = "사용자명(문서명)컬럼")
	private String docUserNm;	
	
	@Column(name = "DOC_USER_HP_NO", length = 100)
    @ColumnPosition(11)
	@Comment(value = "사용자핸드폰번호 컬럼")
	private String docUserHpNo;	
	
	@Column(name = "DOC_USER_EMAIL", length = 100)
    @ColumnPosition(12)
	@Comment(value = "사용자이메일 컬럼")
	private String docUserEmail;	
    
	@Override
	public TmsgKncdId getId() {
		return TmsgKncdId.of(orgCd,tmsgKncd);
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@RequiredArgsConstructor(staticName = "of")
	public static class TmsgKncdId implements Serializable {
		@NonNull
		private String orgCd;
		@NonNull
		private String tmsgKncd;
	}
	
}