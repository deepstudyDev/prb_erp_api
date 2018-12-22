package com.prb.erp.domain.user.auth.menu;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.chequer.axboot.core.annotations.ColumnPosition;
import com.chequer.axboot.core.annotations.Comment;
import com.chequer.axboot.core.code.AXBootTypes;
import com.prb.erp.domain.BaseJpaModel;
import com.prb.erp.domain.program.Program;

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
@Table(name = "TB_ERP_AUTH000")
@Comment(value = "권한그룹 맵")
@IdClass(AuthGroupMenu.AuthGroupMenuId.class)
public class AuthGroupMenu extends BaseJpaModel<AuthGroupMenu.AuthGroupMenuId> {

    @Id
	@Column(name = "GRP_AUTH_CD", length = 100, nullable = false)
    @Comment(value = "권한그룹코드")
    @ColumnPosition(1)
    private String grpAuthCd;

    @Id
    @Column(name = "MENU_CODE", length = 50)
    @Comment(value = "메뉴 코드")
    @ColumnPosition(2)
    private String menuCode;
    

    @Column(name = "SCH_AH", length = 1)
    @Comment(value = "조회권한")
    @ColumnPosition(3)
    private String schAh = AXBootTypes.Used.NO.getLabel();

    @Column(name = "SAV_AH", length = 1)
    @Comment(value = "저장권한")
    @ColumnPosition(4)
    private String savAh = AXBootTypes.Used.NO.getLabel();

    @Column(name = "EXL_AH", length = 1)
    @Comment(value = "엑셀권한")
    @ColumnPosition(5)
    private String exlAh = AXBootTypes.Used.NO.getLabel();

    @Column(name = "DEL_AH", length = 1)
    @Comment(value = "삭제권한")
    @ColumnPosition(6)
    private String delAh = AXBootTypes.Used.NO.getLabel();

    @Column(name = "FN1_AH", length = 1)
    @Comment(value = "기능키1권한")
    @ColumnPosition(7)
    private String fn1Ah = AXBootTypes.Used.NO.getLabel();

    @Column(name = "FN2_AH", length = 1)
    @Comment(value = "기능키2권한")
    @ColumnPosition(8)
    private String fn2Ah = AXBootTypes.Used.NO.getLabel();

    @Column(name = "FN3_AH", length = 1)
    @Comment(value = "기능키3권한")
    @ColumnPosition(9)
    private String fn3Ah = AXBootTypes.Used.NO.getLabel();

    @Column(name = "FN4_AH", length = 1)
    @Comment(value = "기능키4권한")
    @ColumnPosition(10)
    private String fn4Ah = AXBootTypes.Used.NO.getLabel();

    @Column(name = "FN5_AH", length = 1)
    @Comment(value = "기능키5권한")
    @ColumnPosition(11)
    private String fn5Ah = AXBootTypes.Used.NO.getLabel();

    @Transient
    private AXBootTypes.Used useYn;

    public void updateAuthorization(AuthGroupMenu authGroupMenu) {
        this.schAh = positive(this.schAh, authGroupMenu.getSchAh());
        this.savAh = positive(this.savAh, authGroupMenu.getSavAh());
        this.exlAh = positive(this.exlAh, authGroupMenu.getExlAh());
        this.delAh = positive(this.delAh, authGroupMenu.getDelAh());
        this.fn1Ah = positive(this.fn1Ah, authGroupMenu.getFn1Ah());
        this.fn2Ah = positive(this.fn2Ah, authGroupMenu.getFn2Ah());
        this.fn3Ah = positive(this.fn3Ah, authGroupMenu.getFn3Ah());
        this.fn4Ah = positive(this.fn4Ah, authGroupMenu.getFn4Ah());
        this.fn5Ah = positive(this.fn5Ah, authGroupMenu.getFn5Ah());
    }

    public void updateAuthorization(Program program) {
        this.schAh = negative(this.schAh, program.getSchAh());
        this.savAh = negative(this.savAh, program.getSavAh());
        this.exlAh = negative(this.exlAh, program.getExlAh());
        this.delAh = negative(this.delAh, program.getDelAh());
        this.fn1Ah = negative(this.fn1Ah, program.getFn1Ah());
        this.fn2Ah = negative(this.fn2Ah, program.getFn2Ah());
        this.fn3Ah = negative(this.fn3Ah, program.getFn3Ah());
        this.fn4Ah = negative(this.fn4Ah, program.getFn4Ah());
        this.fn5Ah = negative(this.fn5Ah, program.getFn5Ah());
    }

    public String positive(String originValue, String newValue) {
        if (originValue != null && originValue.equals("Y"))
            return originValue;

        if (newValue != null && newValue.equals("Y"))
            return newValue;

        return "N";
    }

    public String negative(String originValue, String newValue) {
        if (originValue != null && originValue.equals("Y"))
            return newValue;

        return "N";
    }

    @Override
    public AuthGroupMenuId getId() {
        return AuthGroupMenuId.of(grpAuthCd, menuCode);
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    @RequiredArgsConstructor(staticName = "of")
    public static class AuthGroupMenuId implements Serializable {
        @NonNull
        private String grpAuthCd;
        @NonNull
        private String menuCode;
    }

    public static AuthGroupMenu of(String grpAuthCd, String menuCode, String schAh, String savAh, String exlAh, String delAh, String fn1Ah, String fn2Ah, String fn3Ah, String fn4Ah, String fn5Ah) {
        AuthGroupMenu authGroupMenu = new AuthGroupMenu();
       
        authGroupMenu.setGrpAuthCd(grpAuthCd);
        authGroupMenu.setMenuCode(menuCode);
        authGroupMenu.setSchAh(schAh);
        authGroupMenu.setSavAh(savAh);
        authGroupMenu.setExlAh(exlAh);
        authGroupMenu.setDelAh(delAh);
        authGroupMenu.setFn1Ah(fn1Ah);
        authGroupMenu.setFn2Ah(fn2Ah);
        authGroupMenu.setFn3Ah(fn3Ah);
        authGroupMenu.setFn4Ah(fn4Ah);
        authGroupMenu.setFn5Ah(fn5Ah);
        return authGroupMenu;
    }
}
