package com.prb.erp.domain.tcher.assign;


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
@Table(name = "TB_ERP_TCHER100")
@Comment(value = "교사관리-배정")
@IdClass(TcherAssignManage.TcherAssignManageId.class)
@Alias("TcherAssignManage")
public class TcherAssignManage extends BaseJpaModel<TcherAssignManage.TcherAssignManageId> {
	
	@Id
	@Column(name = "ASSIGN_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "배정번호")
	private String assignCd;	

	@Column(name = "CUST_CD", length = 20)
    @ColumnPosition(2)
	@Comment(value = "회원코드(계약)")
	private String custCd;	

	@Column(name = "CHILD_CD", length = 20)
    @ColumnPosition(3)
	@Comment(value = "자녀코드")
	private String childCd;	
	
    @Column(name = "ASSIGN_REQUEST_DT", length = 10)
    @Comment(value = "요청일")
    @ColumnPosition(4)
    private String assignRequestDt;
	
	@Column(name = "ASSIGN_REQUEST_USER_CD", length = 20)
    @ColumnPosition(5)
	@Comment(value = "요청자")
	private String assignRequestUserCd;	
    
	@Column(name = "ASSIGN_REMARK", length = 500)
    @ColumnPosition(6)
	@Comment(value = "내용")
	private String assignRemark;	

	@Column(name = "ASSIGN_USER_CD", length = 20)
    @ColumnPosition(7)
	@Comment(value = "배정자")
	private String assignUserCd;	
	
	@Column(name = "APPROVAL_DT", length = 10)
	@Comment(value = "승인여부")
    @ColumnPosition(8)
	private String approvalDt;

	@Column(name = "APPROVAL_YN", length = 20)
	@Comment(value = "승인여부")
    @ColumnPosition(9)
	private String approvalYn;

	@Column(name = "APPROVAL_USER_CD", length = 100)
	@Comment(value = "승인자")
    @ColumnPosition(10)
	private String approvalUserCd;

	@Column(name = "CONFIRM_YN", length = 20)
	@Comment(value = "확정여부")
    @ColumnPosition(11)
	private String confirmYn;

	@Column(name = "ASSIGN_TYPE", length = 20)
	@Comment(value = "배정구분: 10 일반 20 인수인계 30 학습휴식신청 ")
    @ColumnPosition(11)
	private String assignType;
	
	@Column(name = "LAST_YN", length = 20)
	@Comment(value = "최종DATA")
    @ColumnPosition(12)
	private String lastYn;
    
@Override
public TcherAssignManageId getId() {
return TcherAssignManageId.of(assignCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class TcherAssignManageId implements Serializable {
		@NonNull
		private String assignCd;
}
}