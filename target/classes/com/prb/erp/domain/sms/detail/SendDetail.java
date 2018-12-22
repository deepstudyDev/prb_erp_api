package com.prb.erp.domain.sms.detail;


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
@Table(name = "TB_ERP_SMS100")
@Comment(value = "알림상세")
@IdClass(SendDetail.SendDetailId.class)
@Alias("sendDetail")
public class SendDetail extends BaseJpaModel<SendDetail.SendDetailId> {

	@Id
	@Column(name = "SEND_CODE", length = 50)
	@Comment(value = "발송코드")
    @ColumnPosition(1)
	private String sendCode;

	@Id
	@Column(name = "SEND_SEQ", length = 20)
	@Comment(value = "발송코드")
    @ColumnPosition(2)
	private Long sendSeq;
	
	@Column(name = "USER_CD", length = 20)
	@Comment(value = "USER_CD")
    @ColumnPosition(3)
	private String userCd;
    
    @Column(name = "HP_NO", length = 15)
    @Comment(value = "휴대폰")
    @ColumnPosition(4)
    private String hpNo;
    
	@Column(name = "SUCCESS_YN", length = 20)
	@Comment(value = "발송성공여부")
    @ColumnPosition(5)
	private String successYn;
	
@Override
public SendDetailId getId() {
return SendDetailId.of(sendCode,sendSeq);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class SendDetailId implements Serializable {
	@NonNull
	private String sendCode;
	@NonNull
	private Long sendSeq;
}
}