package com.prb.erp.domain.program.menu;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.system.SystemKeyManagementService;
import com.prb.erp.domain.user.auth.menu.AuthGroupMenuService;
import com.querydsl.core.BooleanBuilder;


@Service
public class MenuService extends BaseService<Menu, String> {
    private MenuRepository repository;
    
    @Inject
    private AuthGroupMenuService authGroupMenuService;

 
    @Inject
    private SystemKeyManagementService systemKeyManagementService;  
    
    @Inject
    public MenuService(MenuRepository repository) { 
        super(repository);
        this.repository = repository;
    }
    
    public List<Menu> get(RequestParams requestParams) {
    	String menuGrpCd = requestParams.getString("menuGrpCd", "");
        String progCd = requestParams.getString("progCd", "");
        String returnType = requestParams.getString("returnType", "hierarchy");
        boolean menuOpen = requestParams.getBoolean("menuOpen", true);
        List<String> menuCodes = (List<String>) requestParams.getObject("menuCodes");

        BooleanBuilder builder = new BooleanBuilder();

        
        if (isNotEmpty(menuGrpCd)) {
            builder.and(qMenu.menuGrpCd.eq(menuGrpCd));
        }

        if (isNotEmpty(progCd)) {
            builder.and(qMenu.progCd.eq(progCd));
        }

        List<Menu> menuList = select()
                .from(qMenu)
                .leftJoin(qMenu.program, qProgram)
                .fetchJoin()
                .where(builder)
                .orderBy(qMenu.lv.asc(), qMenu.sort.asc())
                .fetch();

        if (returnType.equals("hierarchy")) {
            List<Menu> hierarchyList = new ArrayList<>();
            List<Menu> filterList = new ArrayList<>();

            for (Menu menu : menuList) {
                menu.setOpen(menuOpen);

                if (menuCodes != null) {
                    if (isNotEmpty(menu.getProgCd()) && !menuCodes.contains(menu.getMenuCode())) {
                        continue;
                    }
                }

                Menu parent = getParent(hierarchyList, menu);

                if (parent == null) {
                    hierarchyList.add(menu);
                } else {
                    parent.addChildren(menu);
                }
            }

            if (menuCodes != null) {
                filterNoChildMenu(filterList, hierarchyList);
            } else {
                filterList.addAll(hierarchyList);
            }

            return filterList;
        }

        return menuList;
    }

    public boolean hasTerminalMenu(Menu menu) {
        boolean hasTerminalMenu = false;

        if (isNotEmpty(menu.getChildren())) {
            for (Menu _menu : menu.getChildren()) {
                hasTerminalMenu = hasTerminalMenu(_menu);

                if (hasTerminalMenu) {
                    return true;
                }
            }
        }

        if (isNotEmpty(menu.getProgCd())) {
            hasTerminalMenu = true;
        }

        return hasTerminalMenu;
    }

    public void filterNoChildMenu(List<Menu> filterList, List<Menu> startList) {
        if (isNotEmpty(startList)) {
            for (Menu menu : startList) {
                if (hasTerminalMenu(menu)) {
                    Menu parent = getParent(filterList, menu);

                    if (parent == null) {
                        filterList.add(menu.clone());
                    } else {
                        parent.addChildren(menu.clone());
                    }
                }

                if (isNotEmpty(menu.getChildren())) {
                    filterNoChildMenu(filterList, menu.getChildren());
                }
            }
        }
    }
    
    public List<Menu> getAuthorizedMenuList(String menuGrpCd, List<String> authGroupList) {
        List<String> menuCodes = select().select(qAuthGroupMenu.menuCode).distinct().from(qAuthGroupMenu).where(qAuthGroupMenu.grpAuthCd.in(authGroupList)).fetch();
        RequestParams<Menu> requestParams = new RequestParams<>(Menu.class);
        requestParams.put("menuGrpCd", menuGrpCd);
        requestParams.put("menuCodes", menuCodes);
        requestParams.put("menuOpen", "false");

        return get(requestParams);
    }
    

    public Menu getParent(List<Menu> menus, Menu menu) {
        Menu parent = menus.stream().filter(m -> m.getId().equals(menu.getParentCode())).findAny().orElse(null);

        if (parent == null) {
            for (Menu _menu : menus) {
                parent = getParent(_menu.getChildren(), menu);

                if (parent != null)
                    break;
            }
        }

        return parent;
    }


    @Transactional
    public void processMenu(MenuRequestVO menuVO) {
        saveMenu(menuVO.getMenuList());
        deleteMenu(menuVO.getDeletedList());
    }

    
    @Transactional
    public void saveMenu(List<Menu> menus) {
    	
    	menus.forEach(m -> {            
            if (isEmpty(m.getProgCd())) {
                m.setProgCd(null);
            }

            if (m.getLv() == 0) {
                m.setParentCode(null);
            }
            
            if(isEmpty(m.getMenuCode())){
            	m.setMenuCode(systemKeyManagementService.setCommonCode("MENU","MENU",5));
            }
        });
    	
        save(menus);
        
        
        menus.stream().filter(menu -> isNotEmpty(menu.getChildren())).forEach(menu -> {
            menu.getChildren().forEach(m ->
            		{
	           			 //m.setParentId(menu.getId());
	        			 m.setParentCode(menu.getMenuCode());
            		});
            saveMenu(menu.getChildren());
        });
    }
    
    
    @Transactional
    public void deleteMenu(List<Menu> menus) {
        menus.forEach(m -> {
            delete(m);
            authGroupMenuService.deleteAuth(m.getMenuCode());
        });
        
        
        menus.stream().filter(menu -> isNotEmpty(menu.getChildren())).forEach(menu -> {
            deleteMenu(menu.getChildren());
            authGroupMenuService.deleteAuth(menu.getMenuCode());
        });
    }
  
    @Transactional
    public void updateMenu(String code, Menu request) {
        Menu exist = findOne(code);
        exist.setMenuSubCd(request.getMenuSubCd());
        exist.setMultiLanguageJson(request.getMultiLanguageJson());
        save(exist);
    }
    

}
