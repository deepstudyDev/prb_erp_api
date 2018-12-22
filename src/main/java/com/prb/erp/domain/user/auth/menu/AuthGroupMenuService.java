package com.prb.erp.domain.user.auth.menu;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chequer.axboot.core.code.AXBootTypes;
import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.program.ProgramService;
import com.prb.erp.domain.program.menu.Menu;
import com.prb.erp.domain.program.menu.MenuService;
import com.prb.erp.domain.user.SessionUser;
import com.prb.erp.domain.user.UserService;
import com.querydsl.core.BooleanBuilder;

@Service
public class AuthGroupMenuService extends BaseService<AuthGroupMenu, AuthGroupMenu.AuthGroupMenuId> {
    private AuthGroupMenuRepository authGroupMenuRepository;

    @Inject
    private ProgramService programService;

    @Inject
    private MenuService menuService;

    @Inject
    private UserService userService;

    @Inject
    public AuthGroupMenuService(AuthGroupMenuRepository authGroupMenuRepository) {
        super(authGroupMenuRepository);
        this.authGroupMenuRepository = authGroupMenuRepository;
    }

    public AuthGroupMenuVO get(RequestParams requestParams) {
      //  Long menuId = requestParams.getLong("menuId");
        String menuCode = requestParams.getString("menuCode");
        Menu menu = menuService.findOne(menuCode);
        AuthGroupMenuVO authGroupMenuV2VO = new AuthGroupMenuVO();

        List<AuthGroupMenu> list = select().from(qAuthGroupMenu).where(qAuthGroupMenu.menuCode.eq(menuCode)).orderBy(qAuthGroupMenu.createdAt.asc()).fetch();
        authGroupMenuV2VO.setList(list);
        authGroupMenuV2VO.setProgram(menu.getProgram());

        return authGroupMenuV2VO;
    } 
    
    public List<AuthGroupMenu> gets(String menuCode) {

        List<AuthGroupMenu> list = select().from(qAuthGroupMenu).where(qAuthGroupMenu.menuCode.eq(menuCode)).fetch();

        return list;
    }

    public AuthGroupMenu getCurrentAuthGroupMenu(String menuCode, SessionUser sessionUser) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(qAuthGroupMenu.grpAuthCd.in(sessionUser.getAuthGroupList()));
        builder.and(qAuthGroupMenu.menuCode.eq(menuCode));

        List<AuthGroupMenu> authorizationList = select().from(qAuthGroupMenu).where(builder).fetch();

        AuthGroupMenu authorization = null;

        for (AuthGroupMenu authGroupMenu : authorizationList) {
            if (authorization == null) {
                authorization = authGroupMenu;
            } else {
                authorization.updateAuthorization(authGroupMenu);
            }
        }

        return authorization;
    }
    
    public List<AuthGroupMenu> getAuthGroupMenuAll(String menuCode,String grpAuthCd) {
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(qAuthGroupMenu.menuCode.eq(menuCode));
        builder.and(qAuthGroupMenu.grpAuthCd.eq(grpAuthCd));

        List<AuthGroupMenu> authorizationList = select().from(qAuthGroupMenu).where(builder).fetch();

        
        return authorizationList;
    }
    
    /*
     * 메뉴 삭제시 권한동시 삭제
     */
    @Transactional
    public void deleteAuth(String menuCode){
    	delete(qAuthGroupMenu).where(qAuthGroupMenu.menuCode.eq(menuCode)).execute();
    }
    
    @Transactional
    public void saveAuth(List<AuthGroupMenu> authGroupMenuList) {
        for (AuthGroupMenu authGroupMenu : authGroupMenuList) {
            if (authGroupMenu.getUseYn() == AXBootTypes.Used.NO) {                               
                delete(authGroupMenu);                
            } else {
                save(authGroupMenu);
            }
        }      
    }
}
