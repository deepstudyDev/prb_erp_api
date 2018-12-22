package com.prb.erp.domain.program.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.chequer.axboot.core.annotations.ColumnPosition;
import com.chequer.axboot.core.annotations.Comment;
import com.chequer.axboot.core.jpa.JsonNodeConverter;
import com.chequer.axboot.core.utils.RequestUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.prb.erp.domain.BaseJpaModel;
import com.prb.erp.domain.program.Program;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TB_ERP_MENU000")
@Comment(value = "메뉴")
@ToString
@JsonInclude(Include.NON_NULL)
public class Menu extends BaseJpaModel<String> implements Cloneable {
	
	@Id
    @Column(name = "MENU_CODE", length = 50)
    @Comment(value = "메뉴 코드")
    @ColumnPosition(2)
    private String menuCode;

    @Column(name = "PARENT_CODE", length = 50)
    @Comment(value = "상위메뉴 코드")
    @ColumnPosition(2)
    private String parentCode;

    @Column(name = "MENU_SUB_CD", length = 100)
    @Comment(value = "메뉴 서브코드")
    @ColumnPosition(3)
    private String menuSubCd;
    
    @Column(name = "MENU_GRP_CD", length = 100)
    @Comment(value = "메뉴 그룹코드")
    @ColumnPosition(4)
    private String menuGrpCd;

    @Column(name = "MENU_NM", length = 100)
    @Comment(value = "메뉴명")
    @ColumnPosition(5)
    private String menuNm;

    @Column(name = "MULTI_LANGUAGE", length = 100)
    @Comment(value = "메뉴 다국어 필드")
    @ColumnPosition(6)
    @Convert(converter = JsonNodeConverter.class)
    private JsonNode multiLanguageJson;
    
    @Column(name = "LV", precision = 10)
    @Comment(value = "레벨")
    @ColumnPosition(7)
    private Integer lv;

    @Column(name = "SORT", precision = 10)
    @Comment(value = "정렬")
    @ColumnPosition(8)
    private Integer sort;

    @Column(name = "PROG_CD", length = 50)
    @Comment(value = "프로그램 코드")
    @ColumnPosition(9)
    private String progCd;

    @Transient
    private boolean open = false;

    @Transient
    private List<Menu> children = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "PROG_CD", referencedColumnName = "PROG_CD", insertable = false, updatable = false)
    private Program program;
    
     @Override
    public String getId() {
        return menuCode;
    }

    @JsonProperty("name")
    public String label() {
        return menuNm;
    }

    @JsonProperty("code")
    public String code() {
        return menuCode;
    }

    @JsonProperty("open")
    public boolean isOpen() {
        return open;
    }

    public void addChildren(Menu menu) {
        children.add(menu);
    }

    public Menu clone() {
        try {
            Menu menu = (Menu) super.clone();
            menu.setChildren(new ArrayList<>());
            return menu;
        } catch (Exception e) {
            // ignore
        }
        return null;
    }

    public static Menu of(String code, String menuGrpCd, String menuNm, JsonNode languageJson,String parentCode, int lv, int sort, String progCd) {
        Menu menu = new Menu();
        menu.setMenuCode(code);
        menu.setMenuGrpCd(menuGrpCd);
        menu.setMenuNm(menuNm);
        menu.setMultiLanguageJson(languageJson);
        menu.setParentCode(parentCode);
        menu.setLv(lv);
        menu.setSort(sort);
        menu.setProgCd(progCd);
        return menu;
    }

    @JsonIgnore
    public String getLocalizedMenuName(HttpServletRequest request) {
        Locale locale = RequestUtils.getLocale(request);

        if (getMultiLanguageJson() != null) {
            JsonNode jsonNode = getMultiLanguageJson().findPath(locale.getLanguage());

            if (jsonNode != null) {
                return jsonNode.asText();
            }
        }
        return menuNm;
    }
}
