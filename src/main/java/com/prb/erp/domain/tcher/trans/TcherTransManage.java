package com.prb.erp.domain.tcher.trans;


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
@Table(name = "TB_ERP_TCHER200")
@Comment(value = "교사관리-인수인계")
@IdClass(TcherTransManage.TcherTransManageId.class)
@Alias("TcherTransManage")
public class TcherTransManage extends BaseJpaModel<TcherTransManage.TcherTransManageId> {
	
	@Id
	@Column(name = "TRANS_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "인수인계번호")
	private String transCd;	

	@Column(name = "CUST_CD", length = 20)
    @ColumnPosition(2)
	@Comment(value = "회원코드(계약)")
	private String custCd;	
	
	@Column(name = "CHILD_CD", length = 20)
    @ColumnPosition(3)
	@Comment(value = "자녀코드")
	private String childCd;		
	
    @Column(name = "TRANS_REQUEST_DT", length = 10)
    @Comment(value = "요청일")
    @ColumnPosition(4)
    private String transRequestDt;
    
	@Column(name = "TRANS_REQUEST_USER_CD", length = 20)
    @ColumnPosition(5)
	@Comment(value = "요청자")
	private String transRequestUserCd;	
    
	@Column(name = "TRANS_PREV_USER_CD", length = 20)
    @ColumnPosition(5)
	@Comment(value = "인계자")
	private String transPrevUserCd;	

	@Column(name = "TRANS_USER_CD", length = 20)
    @ColumnPosition(6)
	@Comment(value = "인수자")
	private String transUserCd;	
	
    @Column(name = "TRANS_HOPE_DT", length = 10)
    @Comment(value = "인계희망일")
    @ColumnPosition(7)
    private String transHopeDt;

	@Column(name = "TRANS_REASON_CD", length = 20)
    @ColumnPosition(8)
	@Comment(value = "사유")
	private String transReasonCd;	

    @Column(name = "REQUEST_START_DT", length = 10)
    @Comment(value = "첫수업시작일")
    @ColumnPosition(9)
    private String requestStartDt;

    @Column(name = "REQUEST_START_HOUR", length = 2)
    @Comment(value = "첫수업 시간")
    @ColumnPosition(10)
    private String requestStartHour;

    @Column(name = "REQUEST_START_TIME", length = 2)
    @Comment(value = "첫수업 분")
    @ColumnPosition(11)
    private String requestStartTime;
    
    @Column(name = "MOVE_DT", length = 10)
    @Comment(value = "이사예정일")
    @ColumnPosition(12)
    private String moveDt;

	@Column(name = "ZIPCODE", length = 20)
	@Comment(value = "우편번호")
    @ColumnPosition(13)
	private String zipcode;
	
	@Column(name = "ADDRESS1", length = 400)
	@Comment(value = "주소1")
    @ColumnPosition(14)
	private String address1;
	
	@Column(name = "ADDRESS2", length = 400)
	@Comment(value = "주소2")
    @ColumnPosition(15)
	private String address2;    

	@Column(name = "TRANS_REMARK", length = 500)
    @ColumnPosition(16)
	@Comment(value = "내용")
	private String transRemark;	

	@Column(name = "APPROVAL_DT", length = 10)
	@Comment(value = "승인여부")
    @ColumnPosition(17)
	private String approvalDt;
	
	@Column(name = "APPROVAL_YN", length = 20)
	@Comment(value = "승인여부")
    @ColumnPosition(18)
	private String approvalYn;

	@Column(name = "APPROVAL_USER_CD", length = 100)
	@Comment(value = "승인자")
    @ColumnPosition(19)
	private String approvalUserCd;

	@Column(name = "CONFIRM_YN", length = 20)
	@Comment(value = "확정여부")
    @ColumnPosition(20)
	private String confirmYn;
	
@Override
public TcherTransManageId getId() {
return TcherTransManageId.of(transCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class TcherTransManageId implements Serializable {
		@NonNull
		private String transCd;
}
}