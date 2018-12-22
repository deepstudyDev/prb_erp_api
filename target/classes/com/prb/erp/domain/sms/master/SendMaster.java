package com.prb.erp.domain.sms.master;


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
@Table(name = "TB_ERP_SMS000")
@Comment(value = "SMS마스터")
@IdClass(SendMaster.SendMasterId.class)
@Alias("SendMaster")
public class SendMaster extends BaseJpaModel<SendMaster.SendMasterId> {
	
	@Id
	@Column(name = "SEND_CODE", length = 50)
	@Comment(value = "발송코드")
    @ColumnPosition(1)
	private String sendCode;

	//10즉시 20예약
	@Column(name = "SEND_TYPE", length = 20)
	@Comment(value = "발송구분")
    @ColumnPosition(2)
	private String sendType;

	@Column(name = "SMS_TYPE", length = 20)
	@Comment(value = "발송구분")
    @ColumnPosition(3)
	private String smsType;
	
	@Column(name = "SEND_DT", length = 30)
	@Comment(value = "발송일")
    @ColumnPosition(4)
	private String sendDt;	

	@Column(name = "SEND_MESSAGE", length = 4000)
	@Comment(value = "SMS 내용")
    @ColumnPosition(6)
	private String sendMessage;

	@Column(name = "SENDER_USER_CD", length = 50)
	@Comment(value = "보낸사람")
    @ColumnPosition(7)
	private String senderUserCd;

	@Column(name = "SENDER_NO", length = 50)
	@Comment(value = "발신번호")
    @ColumnPosition(9)
	private String senderNo;

    @Column(name = "TOTAL_BYTE", precision = 10)
    @Comment(value = "총바이트")
    @ColumnPosition(10)
    private Integer totalByte;

@Override
public SendMasterId getId() {
	return SendMasterId.of(sendCode);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class SendMasterId implements Serializable {

	@NonNull
	private String sendCode;

}
}