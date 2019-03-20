package com.prb.erp.domain.user;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import com.chequer.axboot.core.annotations.ColumnPosition;
import com.chequer.axboot.core.annotations.Comment;
import com.chequer.axboot.core.code.AXBootTypes;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.prb.erp.domain.BaseJpaModel;
import com.prb.erp.domain.user.auth.UserAuth;
import com.prb.erp.domain.user.role.UserRole;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "TB_ERP_USER000")
@Alias("user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userCd")
public class User extends BaseJpaModel<String> {

    @Id
    @Column(name = "USER_CD", length = 100)
    @Comment(value = "사용자코드")
    @ColumnPosition(1)
    private String userCd;

    @Column(name = "USER_TYPE", length = 20)
    @Comment(value = "사원구분")
    @ColumnPosition(2)
    private String userType;

    @Column(name = "DECISION_YN", length = 20)
    @Comment(value = "결정권자구분")
    @ColumnPosition(3)
    private String decisionYn;

    @Column(name = "USER_NM" , length = 200)
    @Comment(value = "사용자명")
    @ColumnPosition(4)
    private String userNm;

    @Column(name = "USER_PS", length = 128)
    @Comment(value = "비밀번호")
    @ColumnPosition(5)
    private String userPs;

    @Column(name = "USER_PS2", length = 128)
    @Comment(value = "비밀번호-초기화용")
    @ColumnPosition(6)
    private String userPs2;

	@Column(name = "AREA_CD", length = 20)
    @ColumnPosition(7)
	@Comment(value = "지역사 코드")
	private String areaCd;

    @Column(name = "ORG_CLASS", length = 20)
    @Comment(value = "조직 분류")
    @ColumnPosition(8)
    private String orgClass;

    @Column(name = "ORG_CD", length = 20)
    @Comment(value = "부서")
    @ColumnPosition(9)
    private String orgCd;

    @Column(name = "RANK_CD", length = 20)
    @Comment(value = "직급1")
    @ColumnPosition(13)
    private String rankCd;

    @Column(name = "HP_NO", length = 15)
    @Comment(value = "휴대폰")
    @ColumnPosition(15)
    private String hpNo;

    @Column(name = "TEL_NO", length = 15)
    @Comment(value = "전화")
    @ColumnPosition(16)
    private String telNo;

    @Column(name = "BIRTHDAY", length = 10)
    @Comment(value = "생일")
    @ColumnPosition(17)
    private String birthday;

    @Column(name = "JOIN_DT", length = 10)
    @Comment(value = "입사일")
    @ColumnPosition(18)
    private String joinDt;

    @Column(name = "OUT_DT", length = 10)
    @Comment(value = "퇴사일")
    @ColumnPosition(19)
    private String outDt;

    @Column(name = "MARRIAGE_YN", length = 20)
    @Comment(value = "결혼유무")
    @ColumnPosition(20)
    private String marriageYn;

    @Column(name = "CHILDREN_YN", length = 20)
    @Comment(value = "자식유무")
    @ColumnPosition(21)
    private String childrenYn;

    @Column(name = "EMERGENCY_NM", length = 200)
    @Comment(value = "비상망-이름")
    @ColumnPosition(22)
    private String emergencyNm;

    @Column(name = "EMERGENCY_TEL", length = 15)
    @Comment(value = "비상망-전화")
    @ColumnPosition(23)
    private String emergencyTel;

	@Column(name = "ZIPCODE", length = 20)
	@Comment(value = "우편번호")
    @ColumnPosition(24)
	private String zipcode;

	@Column(name = "ADDRESS1", length = 400)
	@Comment(value = "주소1")
    @ColumnPosition(25)
	private String address1;

	@Column(name = "ADDRESS2", length = 400)
	@Comment(value = "주소2")
    @ColumnPosition(26)
	private String address2;

    //재직/퇴사
    @Column(name = "USER_STATUS", length = 20)
    @Comment(value = "사용자 상태")
    @ColumnPosition(27)
    private String userStatus;

    @Column(name = "ID_STATUS", length = 20)
    @Comment(value = "계정 상태")
    @ColumnPosition(28)
    private String idStatus;

    @Column(name = "LAST_LOGIN_DATE")
    @Comment(value = "마지막로그인일시")
    @ColumnPosition(29)
    private Instant lastLoginDate;

    @Column(name = "MENU_GRP_CD", length = 20)
    @Comment(value = "메뉴그룹코드")
    @ColumnPosition(30)
    private String menuGrpCd;

    @Column(name = "GRP_AUTH_CD", length = 20)
    @Comment(value = "권한그룹코드")
    @ColumnPosition(31)
    private String grpAuthCd;

    @Column(name = "LOCALE", length = 10)
    @Comment(value = "Locale")
    @ColumnPosition(32)
    private String locale = "ko_KR";

    @Column(name = "USE_YN", length = 1)
    @Comment(value = "사용여부")
    @Type(type = "labelEnum")
    @ColumnPosition(33)
    private AXBootTypes.Used useYn = AXBootTypes.Used.YES;

    @Column(name = "CUST_CD", length = 20)
    @Comment(value = "계약코드")
    @ColumnPosition(34)
    private String custCd;

	@Column(name = "IF_YN", length = 10)
    @ColumnPosition(35)
	@Comment(value = "if데이터유무")
	private String ifYn;

    @Transient
    private List<UserRole> roleList;

    @Transient
    private List<UserAuth> authList;

    @Override
    public String getId() {
        return userCd;
    }
}
