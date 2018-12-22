package com.prb.erp.domain.member.brother;


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
@Table(name = "TB_ERP_MEMBER300")
@Comment(value = "자녀-형제정보")
@IdClass(MemberBrother.MemberBrotherId.class)
@Alias("MemberBrother")
public class MemberBrother extends BaseJpaModel<MemberBrother.MemberBrotherId> {

	@Id
	@Column(name = "CUST_CD", length = 20)
    @ColumnPosition(1)
	@Comment(value = "회원코드(계약)")
	private String custCd;	

	@Id
	@Column(name = "CHILD_CD", length = 20)
    @ColumnPosition(2)
	@Comment(value = "자녀코드")
	private String childCd;		

	@Id
	@Column(name = "BROTHER_CD", length = 20)
    @ColumnPosition(2)
	@Comment(value = "형제코드")
	private String brotherCd;	
	
	@Column(name = "BROTHER_NM", length = 200)
    @ColumnPosition(3)
	@Comment(value = "형제명")
	private String brotherNm;	

	@Column(name = "BROTHER_SEX", length = 20)
    @ColumnPosition(4)
	@Comment(value = "형제성별")
	private String brotherSex;			

	@Column(name = "BROTHER_BIRTHDAY", length = 10)
    @ColumnPosition(5)
	@Comment(value = "형제생일")
	private String brotherBirthday;		

	@Column(name = "BROTHER_GRADE_CD", length = 20)
    @ColumnPosition(6)
	@Comment(value = "형제학년")
	private String brotherGradeCd;	
    
@Override
public MemberBrotherId getId() {
return MemberBrotherId.of(custCd,childCd,brotherCd);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class MemberBrotherId implements Serializable {

	@NonNull
	private String custCd;
	@NonNull
	private String childCd;
	@NonNull
	private String brotherCd;
	
	
}
	
}