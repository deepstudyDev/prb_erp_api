package com.prb.erp.domain.notice;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
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
@Table(name = "TB_ERP_NOTICE000")
@Comment(value = "공지사항관리")
@IdClass(NoticeManage.NoticeManageId.class)
@Alias("NoticeManage")
public class NoticeManage extends BaseJpaModel<NoticeManage.NoticeManageId> {

	@Id
	@Column(name = "NOTICE_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "공지사항코드")
	private String noticeCd;	
	
	@Column(name = "AREA_CD", length = 20)
    @ColumnPosition(2)
	@Comment(value = "지역사")
	private String areaCd;	

    @Column(name = "ORG_CD", length = 20)
    @Comment(value = "부서")
    @ColumnPosition(3)
    private String orgCd;

	@Column(name = "NOTICE_TITLE", length = 400) 
    @ColumnPosition(6)
	@Comment(value = "제목")
	private String noticeTitle;	
	
	@Column(name = "NOTICE_CONTENTS", columnDefinition = "TEXT")
	@Comment(value = "내용")
    @ColumnPosition(7)
	private String noticeContents;

	@Column(name = "TEMP_FILE_CD", length = 40)
    @ColumnPosition(8)
	@Comment(value = "TEMP FILE")
	private String tempFileCd;

	@Column(name = "ORG_TYPE")
	@ColumnPosition(9)
	@Comment(value = "전체/상담사원/방문사원 구분값")
	private int orgType;
	

@Override
public NoticeManageId getId() {
return NoticeManageId.of(noticeCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class NoticeManageId implements Serializable {
		@NonNull
		private String noticeCd;
}
	
}