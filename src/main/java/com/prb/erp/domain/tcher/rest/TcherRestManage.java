package com.prb.erp.domain.tcher.rest;


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
@Table(name = "TB_ERP_TCHER300")
@Comment(value = "교사관리-학습휴식")
@IdClass(TcherRestManage.TcherRestManageId.class)
@Alias("TcherRestManage")
public class TcherRestManage extends BaseJpaModel<TcherRestManage.TcherRestManageId> {

	
	@Id
	@Column(name = "REST_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "학습휴식번호")
	private String restCd;	

	@Column(name = "CUST_CD", length = 20)
    @ColumnPosition(2)
	@Comment(value = "회원코드(계약)")
	private String custCd;	
	
	@Column(name = "CHILD_CD", length = 20)
    @ColumnPosition(3)
	@Comment(value = "자녀코드")
	private String childCd;		
	
    @Column(name = "REST_REQUEST_DT", length = 10)
    @Comment(value = "요청일")
    @ColumnPosition(4)
    private String restRequestDt;
	
    @Column(name = "REQUEST_YEAR", length = 10)
    @Comment(value = "신청년")
    @ColumnPosition(5)
    private String requestYear;
	
    @Column(name = "REQUEST_MONTH", length = 10)
    @Comment(value = "신청월")
    @ColumnPosition(6)
    private String requestMonth;
    
	@Column(name = "REST_REQUEST_USER_CD", length = 20)
    @ColumnPosition(7)
	@Comment(value = "요청자")
	private String restRequestUserCd;	

	@Column(name = "REST_REASON_CD", length = 20)
    @ColumnPosition(8)
	@Comment(value = "사유")
	private String restReasonCd;	

	@Column(name = "REST_REMARK", length = 500)
    @ColumnPosition(9)
	@Comment(value = "내용")
	private String restRemark;	

	@Column(name = "APPROVAL_DT", length = 10)
	@Comment(value = "승인여부")
    @ColumnPosition(10)
	private String approvalDt;

	@Column(name = "APPROVAL_YN", length = 20)
	@Comment(value = "승인여부")
    @ColumnPosition(11)
	private String approvalYn;

	@Column(name = "APPROVAL_USER_CD", length = 100)
	@Comment(value = "승인자")
    @ColumnPosition(12)
	private String approvalUserCd;

	@Column(name = "CONFIRM_YN", length = 20)
	@Comment(value = "확정여부")
    @ColumnPosition(13)
	private String confirmYn;
	
@Override
public TcherRestManageId getId() {
return TcherRestManageId.of(restCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class TcherRestManageId implements Serializable {
		@NonNull
		private String restCd;
}
}