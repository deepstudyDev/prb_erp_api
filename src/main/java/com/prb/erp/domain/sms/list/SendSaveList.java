package com.prb.erp.domain.sms.list;


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
@Table(name = "TB_ERP_SMS200")
@Comment(value = "SMS 내문자함")
@IdClass(SendSaveList.SendSaveListId.class)
@Alias("SendSaveList")
public class SendSaveList extends BaseJpaModel<SendSaveList.SendSaveListId> {

	@Id
	@Column(name = "USER_CD", length = 50)
	@Comment(value = "유저아이디")
    @ColumnPosition(1)
	private String userCd;

	@Id
	@Column(name = "SAVE_SEQ", length = 20)
	@Comment(value = "저장SEQ")
    @ColumnPosition(2)
	private Long saveSeq;

	@Column(name = "SMS_TYPE", length = 20)
	@Comment(value = "발송구분")
    @ColumnPosition(3)
	private String smsType;
	
	@Column(name = "SEND_TITLE", length = 100)
	@Comment(value = "SMS 내용")
    @ColumnPosition(4)
	private String sendTitle;

	@Column(name = "SEND_MESSAGE", length = 4000)
	@Comment(value = "SMS 내용")
    @ColumnPosition(5)
	private String sendMessage;
	
    @Column(name = "TOTAL_BYTE", precision = 10)
    @Comment(value = "총바이트")
    @ColumnPosition(6)
    private Integer totalByte;  
    

@Override
public SendSaveListId getId() {
return SendSaveListId.of(userCd,saveSeq);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class SendSaveListId implements Serializable {
	@NonNull
	private String userCd;
	@NonNull
	private Long saveSeq;
}
}