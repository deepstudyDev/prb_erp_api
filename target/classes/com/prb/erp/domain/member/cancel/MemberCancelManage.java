package com.prb.erp.domain.member.cancel;


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
@Table(name = "TB_ERP_MEMBER050")
@Comment(value = "회원(계약)취소")
@IdClass(MemberCancelManage.MemberCancelManagId.class)
@Alias("MemberCancelManage")
public class MemberCancelManage extends BaseJpaModel<MemberCancelManage.MemberCancelManagId> {
	
	@Id
	@Column(name = "CANCEL_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "학습휴식번호")
	private String cancelCd;	

	@Column(name = "CUST_CD", length = 20)
    @ColumnPosition(2)
	@Comment(value = "회원코드(계약)")
	private String custCd;	

	@Column(name = "CHILD_CD", length = 20)
    @ColumnPosition(3)
	@Comment(value = "자녀코드")
	private String childCd;	
	
    @Column(name = "CANCEL_REQUEST_DT", length = 10)
    @Comment(value = "요청일")
    @ColumnPosition(4)
    private String cancelRequestDt;
    
	@Column(name = "CANCEL_REQUEST_USER_CD", length = 20)
    @ColumnPosition(5)
	@Comment(value = "요청사번")
	private String cancelRequestUserCd;	

	@Column(name = "CANCEL_REASON_CD", length = 20)
    @ColumnPosition(6)
	@Comment(value = "사유")
	private String cancelReasonCd;	

	@Column(name = "CANCEL_REMARK", length = 500)
    @ColumnPosition(7)
	@Comment(value = "내용")
	private String cancelRemark;	

	@Column(name = "APPROVAL_YN", length = 20)
	@Comment(value = "승인여부")
    @ColumnPosition(8)
	private String approvalYn;

	@Column(name = "APPROVAL_USER_CD", length = 100)
	@Comment(value = "승인자")
    @ColumnPosition(9)
	private String approvalUserCd;

	@Column(name = "APPROVAL_DT", length = 100)
	@Comment(value = "승인일")
    @ColumnPosition(10)
	private String approvalDt;

	@Column(name = "CONFIRM_YN", length = 20)
	@Comment(value = "확정여부")
    @ColumnPosition(11)
	private String confirmYn;
	
@Override
public MemberCancelManagId getId() {
return MemberCancelManagId.of(cancelCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class MemberCancelManagId implements Serializable {
		@NonNull
		private String cancelCd;
}
}