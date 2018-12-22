package com.prb.erp.domain.tmsg.queue;


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
@Table(name = "TMSG_QUEUE")
@Comment(value = "TMSG_QUEUE")
@IdClass(TmsgQueue.TmsgQueueId.class)
@Alias("TmsgQueue")
public class TmsgQueue extends BaseJpaModel<TmsgQueue.TmsgQueueId> {

	@Id
	@Column(name = "TMSG_SEQ", length = 12)
    @ColumnPosition(1)
	@Comment(value = "전문일련번호")
	private String tmsgSeq;	

	@Id
	@Column(name = "SEND_ORG_CD", length = 5)
    @ColumnPosition(2)
	@Comment(value = "송신기관코드")
	private String sendOrgCd;	

	@Id
	@Column(name = "RECV_ORG_CD", length = 5)
    @ColumnPosition(3)
	@Comment(value = "수신기관코드")
	private String recvOrgCd;	
	
	@Column(name = "TMSG_KNCD", length = 10)
    @ColumnPosition(4)
	@Comment(value = "문서코드")
	private String tmsgKncd;	
	
	@Column(name = "TMSG_SECTION", length = 1)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String tmsgSection;	

	
	@Column(name = "TMSG_TYPE", length = 1)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String tmsgType;	
	
	@Column(name = "LINK_KEY", length = 100)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String linkKey;	
	
	@Column(name = "USER_NM", length = 60)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String userNm;	
	
	@Column(name = "USER_HP_NO", length = 20)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String userHpNo;	
	
	
	@Column(name = "USER_EMAIL", length = 100)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String userEmail;	
	
	
	@Column(name = "CONTENT", length = 4000)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String content;	
	
	@Column(name = "RESP_CD", length = 6)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String respCd;	
	
	@Column(name = "RES_MSG", length = 100)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String resMsg;	
	
	@Column(name = "REQ_YMD", length = 8)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String reqYmd;	
	
	@Column(name = "REQ_TIME", length = 6)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String regTime;	
	
	@Column(name = "UPD_YMD", length = 8)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String updYmd;	
	
	@Column(name = "UPD_TIME", length = 6)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String updTime;	
	
	@Column(name = "STATUS", length = 1)
    @ColumnPosition(5)
	@Comment(value = "송수신구분")
	private String status;	
	
	@Override
	public TmsgQueueId getId() {
		return TmsgQueueId.of(tmsgSeq,sendOrgCd,recvOrgCd);
	}

	@Embeddable
	@Data
	@NoArgsConstructor
	@RequiredArgsConstructor(staticName = "of")
	public static class TmsgQueueId implements Serializable {
		@NonNull
		private String tmsgSeq;
		@NonNull
		private String sendOrgCd;
		@NonNull
		private String recvOrgCd;
	}
	
}