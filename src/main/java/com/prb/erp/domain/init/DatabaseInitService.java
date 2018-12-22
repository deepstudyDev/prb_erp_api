package com.prb.erp.domain.init;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chequer.axboot.core.code.AXBootTypes;
import com.chequer.axboot.core.db.schema.SchemaGenerator;
import com.chequer.axboot.core.model.extract.service.jdbc.JdbcMetadataService;
import com.chequer.axboot.core.utils.ArrayUtils;
import com.chequer.axboot.core.utils.JsonUtils;
import com.prb.erp.SHAPasswordEncoder;
import com.prb.erp.domain.code.detail.BasicCodeDetail;
import com.prb.erp.domain.code.detail.BasicCodeDetailService;
import com.prb.erp.domain.code.master.BasicCodeMaster;
import com.prb.erp.domain.code.master.BasicCodeMasterService;
import com.prb.erp.domain.froebel.HPB100TVVO;
import com.prb.erp.domain.froebel.HPB130TVVO;
import com.prb.erp.domain.froebel.HPB150TVVO;
import com.prb.erp.domain.froebel.SAM100TVVO;
import com.prb.erp.domain.froebel.SAM130TVVO;
import com.prb.erp.domain.member.MemberManageService;
import com.prb.erp.domain.member.child.MemberChildService;
import com.prb.erp.domain.program.Program;
import com.prb.erp.domain.program.ProgramService;
import com.prb.erp.domain.program.menu.Menu;
import com.prb.erp.domain.program.menu.MenuService;
import com.prb.erp.domain.tcher.TcherManageService;
import com.prb.erp.domain.tcher.inf.charge.TcherInfChargeService;
import com.prb.erp.domain.tcher.inf.salesEdu.TcherInfSalesEduService;
import com.prb.erp.domain.tcher.inf.salesHappy.TcherInfSalesHappyService;
import com.prb.erp.domain.tcher.inf.tcher.TcherInfTcherService;
import com.prb.erp.domain.user.User;
import com.prb.erp.domain.user.UserService;
import com.prb.erp.domain.user.auth.UserAuth;
import com.prb.erp.domain.user.auth.UserAuthService;
import com.prb.erp.domain.user.auth.menu.AuthGroupMenu;
import com.prb.erp.domain.user.auth.menu.AuthGroupMenuService;
import com.prb.erp.domain.user.role.UserRole;
import com.prb.erp.domain.user.role.UserRoleService;
import com.prb.erp.utils.FroebelIFUtils;

@Service
public class DatabaseInitService {

    @Inject
    private SchemaGenerator schemaGenerator;

    @Inject
    private UserService userService;

    @Inject
    private UserRoleService userRoleService;

    @Inject
    private MenuService menuService;

    @Inject
    private AuthGroupMenuService authGroupMenuService;

    @Inject
    private ProgramService programService;

    @Inject
    private JdbcMetadataService jdbcMetadataService;

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Inject
    private BasicCodeMasterService masterCodeService;

    @Inject
    private BasicCodeDetailService detailCodeService;

    @Inject
    private MemberManageService memberManageService;

    @Inject
    private MemberChildService memberChildService;

    @Inject
    private TcherManageService tcherManageService;

    @Inject
    private SHAPasswordEncoder bCryptPasswordEncoder;

    @Inject
    private TcherInfChargeService tcherInfChargeService;

    @Inject
    private TcherInfSalesHappyService tcherInfSalesHappyService;

    @Inject
    private TcherInfSalesEduService tcherInfSalesEduService;

    @Inject
    private TcherInfTcherService tcherInfTcherService;

    @Inject
    private UserAuthService userAuthService;

    public boolean initialized() {
        return ArrayUtils.isNotEmpty(jdbcMetadataService.getTables());
    }

    @Transactional
    public void createBaseCode() throws Exception {
        List<String> lines = new ArrayList<>();

        List<Program> programs = programService.findAll();

        for (Program program : programs) {
            String line = String.format("programService.save(Program.of(\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"));",
                    program.getProgCd(),
                    program.getProgNm(),
                    program.getProgPh(),
                    program.getTarget(),
                    program.getAuthCheck(),
                    program.getSchAh(),
                    program.getSavAh(),
                    program.getExlAh(),
                    program.getDelAh(),
                    program.getFn1Ah(),
                    program.getFn2Ah(),
                    program.getFn3Ah(),
                    program.getFn4Ah(),
                    program.getFn5Ah());

            lines.add(line);
        }

        lines.add("\n");

        for (Menu menu : menuService.findAll()) {
            if (menu.getParentCode() == null) {
                String line = String.format("menuService.save(Menu.of(%dL,\"%s\",\"%s\",JsonUtils.fromJson(%s), null, %d, %d, null));",
                        menu.getId(),
                        menu.getMenuGrpCd(),
                        menu.getMenuNm(),
                        JsonUtils.toJson(JsonUtils.toJson(menu.getMultiLanguageJson())),
                        menu.getLv(),
                        menu.getSort());

                lines.add(line);
            } else {
                String line = String.format("menuService.save(Menu.of(%dL,\"%s\",\"%s\",JsonUtils.fromJson(%s),%dL, %d, %d, \"%s\"));",
                        menu.getId(),
                        menu.getMenuGrpCd(),
                        menu.getMenuNm(),
                        JsonUtils.toJson(JsonUtils.toJson(menu.getMultiLanguageJson())),
                        menu.getParentCode(),
                        menu.getLv(),
                        menu.getSort(),
                        menu.getProgCd());

                lines.add(line);
            }
        }

        lines.add("\n");

        for (AuthGroupMenu authGroupMenu : authGroupMenuService.findAll()) {
            String line = String.format("authGroupMenuService.save(AuthGroupMenu.of(\"%s\",%dL,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"));",
                    authGroupMenu.getGrpAuthCd(),
                    //       authGroupMenu.getMenuId(),
                    authGroupMenu.getSchAh(),
                    authGroupMenu.getSavAh(),
                    authGroupMenu.getExlAh(),
                    authGroupMenu.getDelAh(),
                    authGroupMenu.getFn1Ah(),
                    authGroupMenu.getFn2Ah(),
                    authGroupMenu.getFn3Ah(),
                    authGroupMenu.getFn4Ah(),
                    authGroupMenu.getFn5Ah());
            lines.add(line);
        }

        String code = System.getProperty("user.home") + "/Desktop/code.txt";

        IOUtils.writeLines(lines, null, new FileOutputStream(new File(code)), "UTF-8");
    }


    public void init() throws Exception {
        createSchema();
    }

    public void initMenu() throws Exception {
        createMenuData();
    }

    public void initData() throws Exception {
        createBasicData();
    }


    public void initMember() throws Exception {
        createMemberData();
        createChildData();
    }

    public void initTcher() throws Exception {
        createTcherData();
    }

    public void initTcher2() throws Exception {
        createTcherData2();
    }

    public void createSchema() throws Exception {
        dropSchema();
        schemaGenerator.createSchema();
        createDefaultData();
        createMenuData();
        createBasicData();
    }

    public void createDefaultData() throws IOException {
        User user = new User();
        user.setUserCd("system");
        user.setUserNm("시스템 관리자");
        user.setUserPs(bCryptPasswordEncoder.encode("1!"));
        user.setUserStatus("10");
        user.setLocale("ko_KR");
        user.setUserType("10");// 일반사용자
        user.setMenuGrpCd("SYSTEM_ADMIN_GROUP");
        user.setUseYn(AXBootTypes.Used.YES);
        //user.setDelYn(AXBootTypes.Deleted.NO);
        user.setGrpAuthCd("S0000");
        userService.save(user);

        UserAuth auth = new UserAuth();
        auth.setUserCd(user.getUserCd());
        auth.setGrpAuthCd("S0000");
        userAuthService.save(auth);

        UserRole aspAccess = new UserRole();
        aspAccess.setUserCd("system");
        aspAccess.setRoleCd("ASP_ACCESS");

        UserRole systemManager = new UserRole();
        systemManager.setUserCd("system");
        systemManager.setRoleCd("SYSTEM_ADMIN_GROUP");
        userRoleService.save(Arrays.asList(aspAccess, systemManager));


        User user2 = new User();
        user2.setUserCd("master");
        user2.setUserNm("시스템 관리자");
        user2.setUserPs(bCryptPasswordEncoder.encode("1!"));
        user2.setUserStatus("10");
        user2.setLocale("ko_KR");
        user2.setUserType("10");// 일반사용자
        user2.setMenuGrpCd("SYSTEM_ADMIN_GROUP");
        user2.setUseYn(AXBootTypes.Used.YES);
        user2.setGrpAuthCd("S0000");
        userService.save(user2);

        UserAuth auth2 = new UserAuth();
        auth2.setUserCd("master");
        auth2.setGrpAuthCd("S0000");
        userAuthService.save(auth2);

        UserRole aspAccess2 = new UserRole();
        aspAccess2.setUserCd("master");
        aspAccess2.setRoleCd("ASP_ACCESS");

        UserRole systemManager2 = new UserRole();
        systemManager2.setUserCd("master");
        systemManager2.setRoleCd("SYSTEM_ADMIN_GROUP");
        userRoleService.save(Arrays.asList(aspAccess2, systemManager2));
    }

    public void createMenuData() {
        authGroupMenuService.deleteAll();
        menuService.deleteAll();
        programService.deleteAll();


        /***************** 프로그램 목록 *****************/
        programService.save(Program.of("api", "API", "/swagger/", "_self", "N", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("login", "로그인", "/jsp/login.jsp", "_self", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("main", "메인", "/jsp/main.jsp", "_self", "N", "N", "N", "N", "N", "N", "N", "N", "N", "N"));

        //시스템관리
        programService.save(Program.of("basic-data", "공통코드 관리", "/jsp/erp/basic/basic-data.jsp", "_self", "Y", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("system-config-program", "프로그램 관리", "/jsp/system/system-config-program.jsp", "_self", "Y", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("system-operation-log", "에러로그 관리", "/jsp/system/system-operation-log.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("system-user-log", "사용자 로그", "/jsp/system/system-user-log.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));

        //권한관리
        programService.save(Program.of("system-config-menu", "메뉴그룹관리", "/jsp/erp/auth/system-config-menu.jsp", "_self", "Y", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("system-auth-user", "사용자관리", "/jsp/erp/auth/system-auth-user.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("system-auth-tcher", "교사권한관리", "/jsp/erp/auth/system-auth-tcher.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));

        //지역사관리
        programService.save(Program.of("area-manage", "지역사관리", "/jsp/erp/area/area-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));

        //회원관리
        programService.save(Program.of("member-manage", "회원관리", "/jsp/erp/member/member-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("member-cancel-manage", "취소/환불 회원관리", "/jsp/erp/member/member-cancel-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));

        //교사조회
        programService.save(Program.of("tcher-manage", "교사조회", "/jsp/erp/tcher/tcher-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("tcher10-manage-detail", "상담사원 등록", "/jsp/erp/tcher/tcher10-manage-detail.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("tcher20-manage-detail", "방문교사등록", "/jsp/erp/tcher/tcher20-manage-detail.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("tcher-assign-manage", "교사배정관리", "/jsp/erp/tcher/tcher-assign-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));

        //제품관리
        programService.save(Program.of("product-manage", "제품관리", "/jsp/erp/item/product-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));

        //상품관리
        programService.save(Program.of("goods-manage", "상품관리", "/jsp/erp/item/goods-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));

        //as관리
        programService.save(Program.of("as-manage", "AS관리", "/jsp/erp/as/as-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));

        //주문배송관리
        programService.save(Program.of("order-manage", "주문배송관리", "/jsp/erp/order/order-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));

        //문자관리
        programService.save(Program.of("sms-manage", "문자발송", "/jsp/erp/sms/sms-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));
        programService.save(Program.of("sms-list", "문자발송내역", "/jsp/erp/sms/sms-list.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));

        //공지사항
        programService.save(Program.of("notice-manage", "공지사항", "/jsp/erp/notice/notice-manage.jsp", "_self", "Y", "Y", "N", "N", "N", "N", "N", "N", "N", "N"));


        /***************** 메뉴 목록 *****************/
        //회원
        menuService.save(Menu.of("MEMBER_MANAGE", "SYSTEM_ADMIN_GROUP", "회원관리", JsonUtils.fromJson("{\"ko\":\"회원관리\",\"en\":\"회원관리\"}"), null, 0, 10, null));
        menuService.save(Menu.of("MEMBER_MANAGE_01", "SYSTEM_ADMIN_GROUP", "회원관리", JsonUtils.fromJson("{\"ko\":\"회원관리\",\"en\":\"회원관리\"}"), "MEMBER_MANAGE", 1, 3, "member-manage"));
        menuService.save(Menu.of("MEMBER_MANAGE_02", "SYSTEM_ADMIN_GROUP", "취소/환불 회원관리", JsonUtils.fromJson("{\"ko\":\"취소/환불 회원관리\",\"en\":\"취소/환불 회원관리\"}"), "MEMBER_MANAGE", 1, 3, "member-cancel-manage"));

        //교사
        menuService.save(Menu.of("TCHER_MANAGE", "SYSTEM_ADMIN_GROUP", "교사관리", JsonUtils.fromJson("{\"ko\":\"교사관리\",\"en\":\"교사관리\"}"), null, 0, 20, null));
        menuService.save(Menu.of("TCHER_MANAGE_01", "SYSTEM_ADMIN_GROUP", "교사조회", JsonUtils.fromJson("{\"ko\":\"교사조회\",\"en\":\"교사조회\"}"), "TCHER_MANAGE", 1, 1, "tcher-manage"));
        menuService.save(Menu.of("TCHER_MANAGE_02", "SYSTEM_ADMIN_GROUP", "상담사원등록", JsonUtils.fromJson("{\"ko\":\"상담사원등록\",\"en\":\"상담사원등록\"}"), "TCHER_MANAGE", 1, 2, "tcher10-manage-detail"));
        menuService.save(Menu.of("TCHER_MANAGE_03", "SYSTEM_ADMIN_GROUP", "방문교사등록", JsonUtils.fromJson("{\"ko\":\"방문교사등록\",\"en\":\"방문교사등록\"}"), "TCHER_MANAGE", 1, 3, "tcher20-manage-detail"));
        menuService.save(Menu.of("TCHER_MANAGE_04", "SYSTEM_ADMIN_GROUP", "교사배정관리", JsonUtils.fromJson("{\"ko\":\"교사배정관리\",\"en\":\"교사배정관리\"}"), "TCHER_MANAGE", 1, 4, "tcher-assign-manage"));

        //권한
        menuService.save(Menu.of("AUTH_MANAGE", "SYSTEM_ADMIN_GROUP", "권한 관리", JsonUtils.fromJson("{\"ko\":\"사용자관리\",\"en\":\"사용자관리\"}"), null, 0, 30, null));
        menuService.save(Menu.of("AUTH_MANAGE_01", "SYSTEM_ADMIN_GROUP", "메뉴그룹관리", JsonUtils.fromJson("{\"ko\":\"메뉴그룹관리\",\"en\":\"메뉴그룹관리\"}"), "AUTH_MANAGE", 1, 1, "system-config-menu"));
        menuService.save(Menu.of("AUTH_MANAGE_02", "SYSTEM_ADMIN_GROUP", "사용자관리", JsonUtils.fromJson("{\"ko\":\"사용자관리\",\"en\":\"사용자관리\"}"), "AUTH_MANAGE", 1, 2, "system-auth-user"));
        menuService.save(Menu.of("AUTH_MANAGE_03", "SYSTEM_ADMIN_GROUP", "교사권한관리", JsonUtils.fromJson("{\"ko\":\"교사권한관리\",\"en\":\"교사권한관리\"}"), "AUTH_MANAGE", 1, 3, "system-auth-tcher"));

        //지역사
        menuService.save(Menu.of("AREA_MANAGE", "SYSTEM_ADMIN_GROUP", "지역사관리", JsonUtils.fromJson("{\"ko\":\"지역사관리\",\"en\":\"지역사관리\"}"), null, 0, 40, null));
        menuService.save(Menu.of("AREA_MANAGE_01", "SYSTEM_ADMIN_GROUP", "지역사관리", JsonUtils.fromJson("{\"ko\":\"지역사관리\",\"en\":\"지역사관리\"}"), "AREA_MANAGE", 1, 3, "area-manage"));


        //학습상품관리
        menuService.save(Menu.of("ITEM_MANAGE", "SYSTEM_ADMIN_GROUP", "학습상품관리", JsonUtils.fromJson("{\"ko\":\"학습상품관리\",\"en\":\"학습상품관리\"}"), null, 0, 50, null));
        menuService.save(Menu.of("ITEM_MANAGE_01", "SYSTEM_ADMIN_GROUP", "제품관리", JsonUtils.fromJson("{\"ko\":\"제품관리\",\"en\":\"제품관리\"}"), "ITEM_MANAGE", 1, 1, "product-manage"));
        menuService.save(Menu.of("ITEM_MANAGE_02", "SYSTEM_ADMIN_GROUP", "상품관리", JsonUtils.fromJson("{\"ko\":\"상품관리\",\"en\":\"상품관리\"}"), "ITEM_MANAGE", 1, 2, "goods-manage"));


        //AS관리
        menuService.save(Menu.of("AS_MANAGE", "SYSTEM_ADMIN_GROUP", "AS관리", JsonUtils.fromJson("{\"ko\":\"AS관리\",\"en\":\"AS관리\"}"), null, 0, 60, null));
        menuService.save(Menu.of("AS_MANAGE_01", "SYSTEM_ADMIN_GROUP", "AS관리", JsonUtils.fromJson("{\"ko\":\"AS관리\",\"en\":\"AS관리\"}"), "AS_MANAGE", 1, 3, "as-manage"));


        //주문배송관리
        menuService.save(Menu.of("ORDER_MANAGE", "SYSTEM_ADMIN_GROUP", "주문배송관리", JsonUtils.fromJson("{\"ko\":\"주문배송관리\",\"en\":\"주문배송관리\"}"), null, 0, 70, null));
        menuService.save(Menu.of("ORDER_MANAGE_01", "SYSTEM_ADMIN_GROUP", "주문배송관리", JsonUtils.fromJson("{\"ko\":\"주문배송관리\",\"en\":\"주문배송관리\"}"), "ORDER_MANAGE", 1, 3, "order-manage"));


        //문자관리
        menuService.save(Menu.of("SMS_MANAGE", "SYSTEM_ADMIN_GROUP", "문자관리", JsonUtils.fromJson("{\"ko\":\"문자관리\",\"en\":\"문자관리\"}"), null, 0, 80, null));
        menuService.save(Menu.of("SMS_MANAGE_01", "SYSTEM_ADMIN_GROUP", "문자발송", JsonUtils.fromJson("{\"ko\":\"문자발송\",\"en\":\"문자발송\"}"), "SMS_MANAGE", 1, 1, "sms-manage"));
        menuService.save(Menu.of("SMS_MANAGE_02", "SYSTEM_ADMIN_GROUP", "문자발송내역", JsonUtils.fromJson("{\"ko\":\"문자발송내역\",\"en\":\"문자발송내역\"}"), "SMS_MANAGE", 1, 2, "sms-list"));

        //게시판관리
        menuService.save(Menu.of("NOTICE_MANAGE", "SYSTEM_ADMIN_GROUP", "공지사항관리", JsonUtils.fromJson("{\"ko\":\"공지사항관리\",\"en\":\"공지사항관리\"}"), null, 0, 90, null));
        menuService.save(Menu.of("NOTICE_MANAGE_01", "SYSTEM_ADMIN_GROUP", "공지사항관리", JsonUtils.fromJson("{\"ko\":\"공지사항관리\",\"en\":\"공지사항관리\"}"), "NOTICE_MANAGE", 1, 1, "notice-manage"));

        //시스템관리
        menuService.save(Menu.of("SYSTEM_MANAGE", "SYSTEM_ADMIN_GROUP", "시스템 관리", JsonUtils.fromJson("{\"ko\":\"시스템 관리\",\"en\":\"System Management\"}"), null, 0, 100, null));
        menuService.save(Menu.of("SYSTEM_MANAGE_01", "SYSTEM_ADMIN_GROUP", "공통코드", JsonUtils.fromJson("{\"ko\":\"공통코드\",\"en\":\"공통코드\"}"), "SYSTEM_MANAGE", 1, 1, "basic-data"));
        menuService.save(Menu.of("SYSTEM_MANAGE_02", "SYSTEM_ADMIN_GROUP", "프로그램 관리", JsonUtils.fromJson("{\"ko\":\"프로그램 관리\",\"en\":\"Program Mgmt\"}"), "SYSTEM_MANAGE", 1, 2, "system-config-program"));
        menuService.save(Menu.of("SYSTEM_MANAGE_03", "SYSTEM_ADMIN_GROUP", "사용자 로그관리", JsonUtils.fromJson("{\"ko\":\"사용자 로그관리\",\"en\":\"사용자 로그관리\"}"), "SYSTEM_MANAGE", 1, 7, "system-user-log"));
        menuService.save(Menu.of("SYSTEM_MANAGE_04", "SYSTEM_ADMIN_GROUP", "에러로그 관리", JsonUtils.fromJson("{\"ko\":\"에러로그관리\",\"en\":\"에러로그관리\"}"), "SYSTEM_MANAGE", 1, 8, "system-operation-log"));


        //시스템관리
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "SYSTEM_MANAGE_01", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "SYSTEM_MANAGE_02", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "SYSTEM_MANAGE_03", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "SYSTEM_MANAGE_04", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));

        //권한관리
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "AUTH_MANAGE_01", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "AUTH_MANAGE_02", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "AUTH_MANAGE_03", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));

        //지역사관리
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "AREA_MANAGE_01", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));

        //회원관리
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "MEMBER_MANAGE_01", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "MEMBER_MANAGE_02", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));

        //교사관리
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "TCHER_MANAGE_01", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "TCHER_MANAGE_02", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "TCHER_MANAGE_03", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "TCHER_MANAGE_04", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));

        //학습상품관리
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "ITEM_MANAGE_01", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "ITEM_MANAGE_02", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));

        //AS관리
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "AS_MANAGE_01", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));


        //주문배송관리
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "ORDER_MANAGE_01", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));

        //문자관리
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "SMS_MANAGE_01", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "SMS_MANAGE_02", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));

        //공지관리
        authGroupMenuService.save(AuthGroupMenu.of("S0000", "NOTICE_MANAGE_01", "Y", "Y", "N", "N", "N", "N", "N", "N", "N"));
    }

    public void createBasicData() {
        masterCodeService.deleteAll();
        detailCodeService.deleteAll();

        //공통코드등록
        masterCodeService.save(BasicCodeMaster.of("AUTH_GROUP", "권한그룹"));
        masterCodeService.save(BasicCodeMaster.of("DEL_YN", "삭제여부"));
        masterCodeService.save(BasicCodeMaster.of("LOCALE", "로케일"));
        masterCodeService.save(BasicCodeMaster.of("MENU_GROUP", "메뉴그룹"));
        masterCodeService.save(BasicCodeMaster.of("USE_YN", "사용여부"));
        masterCodeService.save(BasicCodeMaster.of("USER_ROLE", "사용자 롤"));

        detailCodeService.save(BasicCodeDetail.of("AUTH_GROUP", "S0000", "시스템관리자권한", 0));
        detailCodeService.save(BasicCodeDetail.of("AUTH_GROUP", "S0001", "운영자권한", 1));
        detailCodeService.save(BasicCodeDetail.of("AUTH_GROUP", "S0002", "방문교사권한", 2));
        detailCodeService.save(BasicCodeDetail.of("AUTH_GROUP", "S0003", "상담교사권한", 3));
        detailCodeService.save(BasicCodeDetail.of("AUTH_GROUP", "S0004", "일반사원권한", 4));
        detailCodeService.save(BasicCodeDetail.of("AUTH_GROUP", "S0005", "TEST권한", 5));

        detailCodeService.save(BasicCodeDetail.of("DEL_YN", "N", "미삭제", 2));
        detailCodeService.save(BasicCodeDetail.of("DEL_YN", "Y", "삭제", 1));

        detailCodeService.save(BasicCodeDetail.of("LOCALE", "en_US", "미국", 2));
        detailCodeService.save(BasicCodeDetail.of("LOCALE", "ko_KR", "대한민국", 1));

        detailCodeService.save(BasicCodeDetail.of("MENU_GROUP", "SYSTEM_ADMIN_GROUP", "전체메뉴", 1));
		/*
		detailCodeService.save(BasicCodeDetail.of("MENU_GROUP","VISIT_TCHER_GROUP","방문교사그룹",2));
		detailCodeService.save(BasicCodeDetail.of("MENU_GROUP","CUNSULTING_TCHER_GROUP","상담사원그룹",3));
		detailCodeService.save(BasicCodeDetail.of("MENU_GROUP","NORMAL_GROUP","일반사원그룹",3));
		detailCodeService.save(BasicCodeDetail.of("MENU_GROUP","TEST_GROUP","테스트그룹",3));*/

        detailCodeService.save(BasicCodeDetail.of("USER_ROLE", "API", "API 접근 롤", 1));
        detailCodeService.save(BasicCodeDetail.of("USER_ROLE", "ASP_ACCESS", "시스템 접근 롤", 2));
        detailCodeService.save(BasicCodeDetail.of("USER_ROLE", "SYSTEM_ADMIN_GROUP", "시스템 관리자 롤", 3));


        detailCodeService.save(BasicCodeDetail.of("USE_YN", "N", "미사용", 2));
        detailCodeService.save(BasicCodeDetail.of("USE_YN", "Y", "사용", 1));


        masterCodeService.save(BasicCodeMaster.of("PRODUCT_CLASS", "제품분류"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_CLASS", "10", "전집(도서)", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_CLASS", "20", "부가상품", 2, ""));


        masterCodeService.save(BasicCodeMaster.of("PRODUCT_TYPE", "제품유형(세부)"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "10", "리딩", 1, "10"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "20", "수학", 2, "10"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "30", "국어", 3, "10"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "40", "영어", 4, "10"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "50", "한자", 5, "10"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "60", "교구", 1, "20"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "70", "태블릿", 2, "20"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "80", "DVD", 3, "20"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "90", "CD", 4, "20"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "100", "사은품", 5, "20"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "110", "판촉물", 6, "20"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "120", "AS부품", 7, "20"));
        detailCodeService.save(BasicCodeDetail.of("PRODUCT_TYPE", "130", "기타", 8, "20"));


        masterCodeService.save(BasicCodeMaster.of("ITEM_STEP1", "단계구분1"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP1", "10", "학년", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP1", "20", "레벨", 2, ""));

        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "10", "6", 1, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "20", "7", 2, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "30", "8(초1)", 3, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "40", "9(초2)", 4, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "50", "10(초3)", 5, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "60", "11(초4)", 6, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "70", "12(초5)", 7, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "80", "13(초6)", 8, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "85", "전체", 9, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "90", "레벨1", 10, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "100", "레벨2", 11, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "110", "레벨3", 12, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "120", "레벨4", 13, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "130", "레벨5", 14, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "140", "레벨6", 15, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "150", "레벨7", 16, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "160", "레벨8", 17, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ITEM_STEP2", "165", "전체", 18, "20", "Y"));

        masterCodeService.save(BasicCodeMaster.of("GOODS_CLASS", "상품분류"));
        detailCodeService.save(BasicCodeDetail.of("GOODS_CLASS", "0", "단품상품", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("GOODS_CLASS", "1", "패키지상품", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("VISIT_NUMBER_CD", "방문교사수업(월 횟수)"));
        detailCodeService.save(BasicCodeDetail.of("VISIT_NUMBER_CD", "1", "1회", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("VISIT_NUMBER_CD", "2", "2회", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("VISIT_NUMBER_CD", "3", "3회", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("VISIT_NUMBER_CD", "4", "4회", 4, ""));

        masterCodeService.save(BasicCodeMaster.of("VISIT_TIME_CD", "방문교사수업(수업시간)"));
        detailCodeService.save(BasicCodeDetail.of("VISIT_TIME_CD", "10", "10분", 1, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("VISIT_TIME_CD", "20", "20분", 2, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("VISIT_TIME_CD", "30", "30분", 3, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("VISIT_TIME_CD", "40", "40분", 4, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("VISIT_TIME_CD", "50", "50분", 5, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("VISIT_TIME_CD", "60", "60분", 6, "", "Y"));


        masterCodeService.save(BasicCodeMaster.of("INCLUDE_YN", "포함여부"));
        detailCodeService.save(BasicCodeDetail.of("INCLUDE_YN", "Y", "포함", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("INCLUDE_YN", "N", "불포함", 2, ""));


        masterCodeService.save(BasicCodeMaster.of("ORDER_YN", "주문가부"));
        detailCodeService.save(BasicCodeDetail.of("ORDER_YN", "Y", "가능", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("ORDER_YN", "N", "불가능", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("USE_YN", "사용유무"));
        detailCodeService.save(BasicCodeDetail.of("USE_YN", "Y", "사용", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("USE_YN", "N", "미사용", 2, ""));


        masterCodeService.save(BasicCodeMaster.of("POSITION1_CD", "직위"));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "10", "교사", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "20", "사원", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "30", "주임", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "40", "대리", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "50", "과장", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "60", "차장", 6, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "70", "부장", 7, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "80", "팀장", 8, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "90", "국장", 9, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "100", "센터장", 10, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "110", "이사", 11, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "120", "부사장", 12, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION1_CD", "130", "사장", 13, ""));

        masterCodeService.save(BasicCodeMaster.of("POSITION2_CD", "직책"));
        detailCodeService.save(BasicCodeDetail.of("POSITION2_CD", "10", "팀원", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION2_CD", "20", "팀장", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION2_CD", "30", "실장", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION2_CD", "40", "본부장", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION2_CD", "50", "국장", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("POSITION2_CD", "60", "센터장", 6, ""));


        masterCodeService.save(BasicCodeMaster.of("DEPARTMENT_CD", "부서"));
        detailCodeService.save(BasicCodeDetail.of("DEPARTMENT_CD", "10", "부서1", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("DEPARTMENT_CD", "20", "부서2", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("DEPARTMENT_CD", "30", "부서3", 3, ""));


        masterCodeService.save(BasicCodeMaster.of("USER_STATUS", "유저상태"));
        detailCodeService.save(BasicCodeDetail.of("USER_STATUS", "10", "재직", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("USER_STATUS", "20", "퇴사", 2, ""));


        masterCodeService.save(BasicCodeMaster.of("ID_STATUS", "계정상태"));
        detailCodeService.save(BasicCodeDetail.of("ID_STATUS", "Y", "정상", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("ID_STATUS", "N", "잠김", 2, ""));


        masterCodeService.save(BasicCodeMaster.of("MARRIAGE_YN", "결혼유무"));
        detailCodeService.save(BasicCodeDetail.of("MARRIAGE_YN", "Y", "기혼", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("MARRIAGE_YN", "N", "미혼", 2, ""));


        masterCodeService.save(BasicCodeMaster.of("CHILDREN_YN", "자녀유무"));
        detailCodeService.save(BasicCodeDetail.of("CHILDREN_YN", "1", "1", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("CHILDREN_YN", "2", "2", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("CHILDREN_YN", "3", "3", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("CHILDREN_YN", "4", "4", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("CHILDREN_YN", "5", "5", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("CHILDREN_YN", "6", "6", 6, ""));


        masterCodeService.save(BasicCodeMaster.of("SALES_YN", "판매여부"));
        detailCodeService.save(BasicCodeDetail.of("SALES_YN", "Y", "판매중", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("SALES_YN", "N", "판매중지", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("GOODS_STATUS", "상품등록상태"));
        detailCodeService.save(BasicCodeDetail.of("GOODS_STATUS", "Y", "완료", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("GOODS_STATUS", "N", "임시저장", 2, ""));


        masterCodeService.save(BasicCodeMaster.of("TCHER_TYPE", "교사유형"));
        detailCodeService.save(BasicCodeDetail.of("TCHER_TYPE", "12", "상담사원", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("TCHER_TYPE", "14", "방문교사", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("RANK_CD", "직급"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "100", "전무이사", 1, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "110", "상무이사", 2, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "120", "이사", 3, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "130", "처장", 4, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "140", "국장(구)", 5, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "141", "수석센터장", 6, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "142", "센터장", 7, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "143", "예비센터장", 8, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "144", "국장1급", 9, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "145", "국장2급", 10, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "146", "국장3급", 11, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "147", "국장4급", 12, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "148", "국장", 13, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "150", "소장", 14, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "160", "수석본부장", 15, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "170", "본부장", 16, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "171", "조직본부장", 17, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "172", "조직부장", 18, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "173", "국장5급", 19, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "174", "예비국장", 20, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "180", "팀장", 21, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "185", "파트장", 22, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "190", "부장", 23, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "200", "과장", 24, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "210", "대리", 25, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "211", "NT1", 26, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "212", "NT2", 27, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "213", "NT3", 28, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "214", "NT3(본인)", 29, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "215", "ST1", 30, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "220", "A/G", 31, "12", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "310", "팀장", 1, "14", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "320", "부주임", 2, "14", "Y"));
        detailCodeService.save(BasicCodeDetail.of("RANK_CD", "330", "교사", 3, "14", "Y"));


        masterCodeService.save(BasicCodeMaster.of("GUARANTEE_DOC_CD", "보증서류"));
        detailCodeService.save(BasicCodeDetail.of("GUARANTEE_DOC_CD", "0", "필요없음", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("GUARANTEE_DOC_CD", "1", "제출", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("GUARANTEE_DOC_CD", "2", "미제출", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("COMPANY_TYPE", "법인개인구분"));
        detailCodeService.save(BasicCodeDetail.of("COMPANY_TYPE", "1", "법인", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("COMPANY_TYPE", "2", "개인", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("BANK_CD", "은행명"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "002", "산업은행", 1, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "003", "기업은행", 2, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "004", "국민은행", 3, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "005", "외환은행", 4, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "006", "주택", 5, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "007", "수협중앙회", 6, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "011", "농협중앙회", 7, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "012", "농협중앙회  단위조합", 8, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "020", "우리은행", 9, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "021", "조흥은행", 10, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "023", "SC제일은행", 11, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "027", "한국씨티은행", 12, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "031", "대구은행", 13, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "032", "부산은행", 14, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "034", "광주은행", 15, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "035", "제주은행", 16, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "037", "전북은행", 17, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "039", "경남은행", 18, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "045", "새마을", 19, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "048", "신용협동조합", 20, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "050", "상호저축은행중앙회", 21, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "054", "HSBC", 22, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "071", "우체국", 23, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "081", "KEB하나은행", 24, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "088", "신한은행", 25, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "089", "K뱅크", 26, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "090", "카카오뱅크", 27, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "209", "동양유안타증권", 28, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "218", "현대증권", 29, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "230", "미래에셋증권", 30, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "238", "미래에셋대우", 31, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "240", "삼성투자증권", 32, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "262", "하이투자증권", 33, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "267", "대신증권", 34, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "269", "한화투자증권", 35, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "270", "하나금융투자", 36, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "278", "신한금융투자", 37, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "03", "기업은행", 38, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "04", "국민은행", 39, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "05", "외환은행", 40, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "11", "농협", 41, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "12", "농협중앙회", 42, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "20", "우리은행", 43, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "23", "SC제일은행", 44, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "27", "한국씨티은행", 45, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "278", "신한투자증권", 46, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "45", "새마을금고", 47, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "48", "신협", 48, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "50", "상호저축은행중앙회", 49, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "71", "우체국", 50, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "81", "하나은행", 51, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "88", "신한은행", 52, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "90", "카카오뱅크", 53, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "A", "국민MMF", 54, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "B", "대신증권", 55, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "C", "삼성증권", 56, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "D", "CJ투자증권", 57, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "E", "SK투자증권", 58, "20", "Y"));
        detailCodeService.save(BasicCodeDetail.of("BANK_CD", "F", "한국투자증권", 59, "20", "Y"));

        masterCodeService.save(BasicCodeMaster.of("BANK_CD", "은행명"));
        detailCodeService.save(BasicCodeDetail.of("LOCAL_CD", "1", "내국인", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCAL_CD", "9", "외국인", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("WORK_CD", "근무상태"));
        detailCodeService.save(BasicCodeDetail.of("WORK_CD", "1", "임시등록", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("WORK_CD", "2", "정상근무", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("WORK_CD", "3", "휴직", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("WORK_CD", "4", "퇴직", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("WORK_CD", "5", "퇴직신청", 5, ""));

        masterCodeService.save(BasicCodeMaster.of("WORK_REASON_CD", "사유코드"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "1", "채용", 1, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "2", "부서이동", 2, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "3", "겸직", 3, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "4", "조직개편", 4, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "5", "승진", 5, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "6", "휴직", 6, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "7", "복직", 7, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "8", "배출", 8, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "9", "자동퇴직", 9, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "10", "퇴직", 10, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "11", "재입사", 11, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "12", "강등", 12, "10", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REASON_CD", "13", "3회이상입사", 13, "10", "Y"));


        masterCodeService.save(BasicCodeMaster.of("WORK_REG_CD", "사원등록구분"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REG_CD", "1", "기존사원등록", 1, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REG_CD", "2", "재입사", 2, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("WORK_REG_CD", "3", "신규입사", 3, "", "Y"));


        masterCodeService.save(BasicCodeMaster.of("INCOME_TYPE", "소득자구분"));
        detailCodeService.save(BasicCodeDetail.of("INCOME_TYPE", "940908", "방판/외판", 1, ""));

        masterCodeService.save(BasicCodeMaster.of("OUT_REASON_CD", "퇴사사유"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "11", "건강", 1, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "12", "집안 반대", 2, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "13", "이직", 3, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "14", "이사", 4, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "15", "육아", 5, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "16", "임신", 6, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "17", "출산", 7, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "18", "가족병환", 8, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "19", "개인사유", 9, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "20", "해외이주", 10, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "21", "부적응", 11, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "22", "급여불만족", 12, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "23", "고객항의", 13, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "24", "권고해촉", 14, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("OUT_REASON_CD", "25", "기타", 15, "", "Y"));


        masterCodeService.save(BasicCodeMaster.of("SECT_CODE", "신고사업장"));
        detailCodeService.save(BasicCodeDetail.of("SECT_CODE", "1", "프뢰벨교육원", 1, ""));


        masterCodeService.save(BasicCodeMaster.of("COUNTRY_CD", "국가코드"));
        detailCodeService.save(BasicCodeDetail.of("COUNTRY_CD", "KR", "대한민국", 1, ""));


        masterCodeService.save(BasicCodeMaster.of("RESIDENCE_TYPE", "거주구분"));
        detailCodeService.save(BasicCodeDetail.of("RESIDENCE_TYPE", "1", "거주자", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("RESIDENCE_TYPE", "2", "거주자", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("GD_RELATION_CD", "보호자관계"));
        detailCodeService.save(BasicCodeDetail.of("GD_RELATION_CD", "0", "부", 1, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("GD_RELATION_CD", "1", "모", 2, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("GD_RELATION_CD", "2", "조부", 3, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("GD_RELATION_CD", "3", "조모", 4, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("GD_RELATION_CD", "4", "기타", 5, "", "Y"));


        masterCodeService.save(BasicCodeMaster.of("SEX", "성별"));
        detailCodeService.save(BasicCodeDetail.of("SEX", "0", "남자", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("SEX", "1", "여자", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("GRADE_CD", "학년"));
        detailCodeService.save(BasicCodeDetail.of("GRADE_CD", "0", "미취학", 0, ""));
        detailCodeService.save(BasicCodeDetail.of("GRADE_CD", "1", "1학년", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("GRADE_CD", "2", "2학년", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("GRADE_CD", "3", "3학년", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("GRADE_CD", "4", "4학년", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("GRADE_CD", "5", "5학년", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("GRADE_CD", "6", "6학년", 6, ""));

        masterCodeService.save(BasicCodeMaster.of("AGREEMENT_CD", "약정기간"));
        detailCodeService.save(BasicCodeDetail.of("AGREEMENT_CD", "24", "24개월", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("AGREEMENT_CD", "36", "36개월", 3, ""));


        masterCodeService.save(BasicCodeMaster.of("ONLINE_SERVICE_STATUS", "온라인교육상태"));
        detailCodeService.save(BasicCodeDetail.of("ONLINE_SERVICE_STATUS", "10", "교육중", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("ONLINE_SERVICE_STATUS", "20", "교육종료", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("ONLINE_SERVICE_STATUS", "30", "미납중지", 3, ""));


        masterCodeService.save(BasicCodeMaster.of("VISIT_SERVICE_STATUS", "방문교육상태"));
        detailCodeService.save(BasicCodeDetail.of("VISIT_SERVICE_STATUS", "10", "교육중", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("VISIT_SERVICE_STATUS", "20", "학습중단", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("VISIT_SERVICE_STATUS", "30", "학습중단요청", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("VISIT_SERVICE_STATUS", "40", "인수인계요청", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("VISIT_SERVICE_STATUS", "50", "교사미배정", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("VISIT_SERVICE_STATUS", "60", "미납중지", 6, ""));
        detailCodeService.save(BasicCodeDetail.of("VISIT_SERVICE_STATUS", "70", "학습휴식", 7, "", "N"));
        detailCodeService.save(BasicCodeDetail.of("VISIT_SERVICE_STATUS", "80", "학습휴식신청", 8, "", "N"));


        masterCodeService.save(BasicCodeMaster.of("SALES_YN", "판매여부"));
        detailCodeService.save(BasicCodeDetail.of("SALES_YN", "Y", "판매중", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("SALES_YN", "N", "판매중지", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("GOODS_STATUS", "상품상태"));
        detailCodeService.save(BasicCodeDetail.of("GOODS_STATUS", "10", "임시저장", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("GOODS_STATUS", "20", "완료", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("SERVICE_USE_YN", "서비스 신청유무"));
        detailCodeService.save(BasicCodeDetail.of("SERVICE_USE_YN", "Y", "신청", 1, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("SERVICE_USE_YN", "N", "미신청", 2, "", "Y"));


        masterCodeService.save(BasicCodeMaster.of("PAYMENT_YN", "납부여부"));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_YN", "Y", "납부", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_YN", "N", "미납중지", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("SEND_TYPE", "발송구분"));
        detailCodeService.save(BasicCodeDetail.of("SEND_TYPE", "10", "즉시발송", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("SEND_TYPE", "20", "예약발송", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("SMS_TYPE", "문자종류"));
        detailCodeService.save(BasicCodeDetail.of("SMS_TYPE", "SMS", "SMS", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("SMS_TYPE", "LMS", "LMS", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("SMS_RESULT", "문자전송결과"));
        detailCodeService.save(BasicCodeDetail.of("SMS_RESULT", "Y", "성공", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("SMS_RESULT", "N", "실패", 2, ""));


        masterCodeService.save(BasicCodeMaster.of("USER_TYPE", "회원구분"));
        detailCodeService.save(BasicCodeDetail.of("USER_TYPE", "10", "일반", 1, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("USER_TYPE", "12", "상담교사", 2, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("USER_TYPE", "14", "방문교사", 3, "", ""));
        detailCodeService.save(BasicCodeDetail.of("USER_TYPE", "30", "계약자", 4, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("USER_TYPE", "40", "자녀", 5, "", "Y"));

        masterCodeService.save(BasicCodeMaster.of("TRANS_REASON_CD", "인수인계사유"));
        detailCodeService.save(BasicCodeDetail.of("TRANS_REASON_CD", "10", "관리지역변경", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("TRANS_REASON_CD", "20", "교사요청", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("TRANS_REASON_CD", "30", "회원이사", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("TRANS_REASON_CD", "40", "기타", 4, ""));

        masterCodeService.save(BasicCodeMaster.of("ASSIGN_CLASS", "배정구분1"));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_CLASS", "10", "배정예정자", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_CLASS", "20", "인수인계", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_CLASS", "30", "학습휴식신청", 3, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_CLASS", "40", "팀반납", 4, "", "N"));

        masterCodeService.save(BasicCodeMaster.of("ASSIGN_TYPE", "배정구분2"));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_TYPE", "10", "학습중단신청", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_TYPE", "20", "팀반납", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("ASSIGN_REASON_CD", "학습중단사유"));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_REASON_CD", "10", "약정만료", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_REASON_CD", "20", "타 학습지,학원,과외", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_REASON_CD", "30", "학습상황분만", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_REASON_CD", "40", "교사불만", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_REASON_CD", "50", "과목변경", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_REASON_CD", "60", "인수인계요청", 6, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_REASON_CD", "70", "개인사정", 7, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_REASON_CD", "80", "이사", 8, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_REASON_CD", "90", "출산휴회", 9, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_REASON_CD", "100", "기타", 10, ""));

        masterCodeService.save(BasicCodeMaster.of("ASSIGN_KIND_CD", "학습중단종류"));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_KIND_CD", "10", "휴회", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_KIND_CD", "20", "탈퇴", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("ASSIGN_KIND_CD", "30", "시간추가중단", 3, ""));

        masterCodeService.save(BasicCodeMaster.of("PAYMENT_WAY", "결제방식"));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_WAY", "10", "분납", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_WAY", "20", "일시납", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_WAY", "30", "무료", 3, ""));

        masterCodeService.save(BasicCodeMaster.of("PAYMENT_METHOD", "결제수단"));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_METHOD", "10", "신용카드정기출금", 1, "10"));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_METHOD", "20", "CMS정기출금", 2, "10"));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_METHOD", "30", "현금(계좌이체)", 3, "20"));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_METHOD", "40", "신용카드", 4, "20"));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_METHOD", "50", "100% 무료", 5, "30"));
        detailCodeService.save(BasicCodeDetail.of("PAYMENT_METHOD", "60", "18개월 무료", 6, "30"));

        masterCodeService.save(BasicCodeMaster.of("LOCATION_CD", "지역"));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "10", "서울", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "20", "부산", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "30", "대구", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "40", "인천", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "50", "광주", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "60", "대전", 6, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "70", "울산", 7, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "80", "세종", 8, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "90", "경기", 9, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "100", "강원", 10, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "110", "충북", 11, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "120", "충남", 12, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "130", "전북", 13, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "140", "전남", 14, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "150", "경북", 15, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "160", "경남", 16, ""));
        detailCodeService.save(BasicCodeDetail.of("LOCATION_CD", "170", "제주", 17, ""));

        masterCodeService.save(BasicCodeMaster.of("ORG_CLASS", "조직분류"));
        detailCodeService.save(BasicCodeDetail.of("ORG_CLASS", "12", "상담사조직", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("ORG_CLASS", "14", "방문교사조직", 2, ""));

        masterCodeService.save(BasicCodeMaster.of("ORG_TYPE", "조직유형"));
        detailCodeService.save(BasicCodeDetail.of("ORG_TYPE", "HEADQ", "총국", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("ORG_TYPE", "AREAQ", "지국", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("ORG_TYPE", "DEPTQ", "본부", 3, ""));

        masterCodeService.save(BasicCodeMaster.of("APPROVAL_YN", "승인여부"));
        detailCodeService.save(BasicCodeDetail.of("APPROVAL_YN", "Y", "승인", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("APPROVAL_YN", "N", "미승인", 2, ""));


        masterCodeService.save(BasicCodeMaster.of("CONTRACT_TYPE", "계약종류"));
        detailCodeService.save(BasicCodeDetail.of("CONTRACT_TYPE", "1", "기본", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("CONTRACT_TYPE", "2", "형제추가", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("CONTRACT_TYPE", "3", "시간.횟수추가", 3, ""));

        masterCodeService.save(BasicCodeMaster.of("CANCEL_REASON_CD", "계약취소사유"));
        detailCodeService.save(BasicCodeDetail.of("CANCEL_REASON_CD", "1", "일반", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("CANCEL_REASON_CD", "2", "계약취소", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("CANCEL_REASON_CD", "3", "일부반품", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("CANCEL_REASON_CD", "4", "전체반품", 4, ""));


        masterCodeService.save(BasicCodeMaster.of("MEMBER_STATUS", "계약상태"));
        detailCodeService.save(BasicCodeDetail.of("MEMBER_STATUS", "1", "계약중", 1, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("MEMBER_STATUS", "2", "계약취소요청", 2, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("MEMBER_STATUS", "3", "계약취소", 3, "", "Y"));

        masterCodeService.save(BasicCodeMaster.of("YEAR", "년"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2000", "2000년", 20, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2001", "2001년", 19, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2002", "2002년", 18, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2003", "2003년", 17, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2004", "2004년", 16, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2005", "2005년", 15, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2006", "2006년", 14, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2007", "2007년", 13, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2008", "2008년", 12, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2009", "2009년", 11, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2010", "2010년", 10, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2011", "2011년", 9, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2012", "2012년", 8, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2013", "2013년", 7, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2014", "2014년", 6, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2015", "2015년", 5, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2016", "2016년", 4, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2017", "2017년", 3, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2018", "2018년", 2, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("YEAR", "2019", "2019년", 1, "", "Y"));


        masterCodeService.save(BasicCodeMaster.of("MONTH", "월"));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "01", "1월", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "02", "2월", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "03", "3월", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "04", "4월", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "05", "5월", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "06", "6월", 6, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "07", "7월", 7, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "08", "8월", 8, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "09", "9월", 9, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "10", "10월", 10, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "11", "11월", 11, ""));
        detailCodeService.save(BasicCodeDetail.of("MONTH", "12", "12월", 12, ""));

        masterCodeService.save(BasicCodeMaster.of("REST_REASON_CD", "학습휴식신청사유"));
        detailCodeService.save(BasicCodeDetail.of("REST_REASON_CD", "10", "학습상황불안", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("REST_REASON_CD", "20", "교사불만", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("REST_REASON_CD", "30", "기타", 3, ""));

        masterCodeService.save(BasicCodeMaster.of("SCHOOL_REGION", "학교검색-지역"));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100260", "서울특별시", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100267", "부산광역시", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100269", "인천광역시", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100271", "대전광역시", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100272", "대구광역시", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100273", "울산광역시", 6, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100275", "광주광역시", 7, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100276", "경기도", 8, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100278", "강원도", 9, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100280", "충청북도", 10, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100281", "충청남도", 11, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100282", "전라북도", 12, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100283", "전라남도", 13, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100285", "경상북도", 14, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100291", "경상남도", 15, ""));
        detailCodeService.save(BasicCodeDetail.of("SCHOOL_REGION", "100292", "제주도", 16, ""));

        masterCodeService.save(BasicCodeMaster.of("NATIONAL_NUMBER", "지역번호"));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "02", "02", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "051", "051", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "053", "053", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "032", "032", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "062", "062", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "042", "042", 6, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "052", "052", 7, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "044", "044", 8, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "031", "031", 9, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "033", "033", 10, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "043", "043", 11, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "041", "041", 12, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "063", "063", 13, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "061", "061", 14, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "054", "054", 15, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "055", "055", 16, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "064", "064", 17, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "0130", "0130", 18, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "080", "080", 19, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "070", "070", 20, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "0503", "0503", 21, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "0502", "0502", 22, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "0507", "0507", 23, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "0506", "0506", 24, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "0504", "0504", 25, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "0505", "0505", 26, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "0303", "0303", 27, ""));
        detailCodeService.save(BasicCodeDetail.of("NATIONAL_NUMBER", "050", "050", 28, ""));

        masterCodeService.save(BasicCodeMaster.of("HP_NUMBER", "휴대폰번호"));
        detailCodeService.save(BasicCodeDetail.of("HP_NUMBER", "010", "010", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("HP_NUMBER", "011", "011", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("HP_NUMBER", "016", "016", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("HP_NUMBER", "017", "017", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("HP_NUMBER", "018", "018", 5, ""));
        detailCodeService.save(BasicCodeDetail.of("HP_NUMBER", "070", "070", 6, ""));

        masterCodeService.save(BasicCodeMaster.of("NOTICE_TYPE", "공지구분"));
        detailCodeService.save(BasicCodeDetail.of("NOTICE_TYPE", "10", "전체", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("NOTICE_TYPE", "20", "팀 공지", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("NOTICE_TYPE", "30", "조 공지", 3, ""));

        masterCodeService.save(BasicCodeMaster.of("EMAIL_TYPE", "이메일타입"));
        detailCodeService.save(BasicCodeDetail.of("EMAIL_TYPE", "naver.com", "네이버", 1, ""));
        detailCodeService.save(BasicCodeDetail.of("EMAIL_TYPE", "daum.net", "다음", 2, ""));
        detailCodeService.save(BasicCodeDetail.of("EMAIL_TYPE", "gmail.com", "지메일", 3, ""));
        detailCodeService.save(BasicCodeDetail.of("EMAIL_TYPE", "hanmail.net", "한메일", 4, ""));
        detailCodeService.save(BasicCodeDetail.of("EMAIL_TYPE", "nate.com", "네이트", 5, ""));

        masterCodeService.save(BasicCodeMaster.of("SALES_TYPE", "판매유형"));
        detailCodeService.save(BasicCodeDetail.of("SALES_TYPE", "1", "초등패키지", 1, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("SALES_TYPE", "2", "영유아패키지", 2, "", "Y"));

        masterCodeService.save(BasicCodeMaster.of("CHARGE_CODE", "인사_사유코드"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "110", "채용", 1, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "120", "부서이동", 2, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "130", "겸직", 3, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "140", "조직개편", 4, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "150", "승진", 5, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "160", "휴직", 6, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "170", "복직", 7, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "180", "배출", 8, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "190", "강등", 9, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "200", "퇴직", 10, "", "Y"));
        detailCodeService.save(BasicCodeDetail.of("CHARGE_CODE", "210", "재입사", 11, "", "Y"));
    }


    public void createMemberData() throws Exception {
        String sql = "";
        List<SAM100TVVO> members = FroebelIFUtils.getSAM100TV_HAPPY();
        if (null != members) {
            memberManageService.deleteIfData();
            sql = "INSERT INTO TB_ERP_MEMBER000 " +
                    "(  CONT_CD,"
                    + " CUST_CD,"
                    + " AREA_CD, "
                    + " ORG_CD, "
                    + " REPRE_NUM, "
                    + "	GD1_NM, "
                    + "	GD1_RELATION_CD, "
                    + "	GD1_BIRTHDAY, "
                    + "	GD1_USER_CD, "
                    + "	GD2_NM, "
                    + "	TEL_NO, "
                    + "	HP_NO, "
                    + "	EMAIL, "
                    + "	HOME_ZIPCODE, "
                    + "	HOME_ADDRESS1, "
                    + "	HOME_ADDRESS2, "
                    + "	DELIVERY_ZIPCODE, "
                    + "	DELIVERY_ADDRESS1, "
                    + "	DELIVERY_ADDRESS2, "
                    + "	IF_YN, "
                    + " CREATED_AT,"
                    + " CREATED_BY,"
                    + " UPDATED_AT,"
                    + " UPDATED_BY)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y',GETDATE(),'system',GETDATE(),'system') ";

            jdbcTemplate.batchUpdate(
                    sql,
                    members,
                    100,
                    new ParameterizedPreparedStatementSetter<SAM100TVVO>() {
                        public void setValues(PreparedStatement ps, SAM100TVVO argument) throws SQLException {
                            ps.setString(1, argument.getCUST_CODE());
                            ps.setString(2, argument.getCUST_CODE());
                            //ps.setString(3,argument.getAREA_CODE());
                            ps.setString(3, "AREA00001");
                            ps.setString(4, argument.getTREE_CODE());
                            ps.setString(5, argument.getREPRE_NUM());
                            ps.setString(6, argument.getCUST_NAME());
                            ps.setString(7, "1");
                            ps.setString(8, "0000-00-00");
                            ps.setString(9, argument.getCUST_CODE());
                            ps.setString(10, argument.getSUB_CUST_NAME());
                            ps.setString(11, argument.getTEL_NO());
                            ps.setString(12, argument.getHP_NO());
                            ps.setString(13, argument.getEMAIL());
                            ps.setString(14, argument.getZIP_NO());
                            ps.setString(15, argument.getADDR());
                            ps.setString(16, argument.getADDR2());
                            ps.setString(17, argument.getDEL_ZIP_NO());
                            ps.setString(18, argument.getDEL_ADDR());
                            ps.setString(19, argument.getDEL_ADDR2());
                        }
                    });
        }
    }

    public void createChildData() throws Exception {
        String sql = "";
        List<SAM130TVVO> members = FroebelIFUtils.getSAM130TV_HAPPY();
        if (null != members) {
            memberChildService.deleteIfData();
            sql = "INSERT INTO TB_ERP_MEMBER200 " +
                    "( CUST_CD,"
                    + "CHILD_CD, "
                    + "	CONTRACT_TYPE, "
                    + "	CHILDREN_USER_CD, "
                    + "	CHILDREN_NM,"
                    + " CHILDREN_CONTRACT_DT,"
                    + " CHILD_REPRE_NUM, "
                    + "	IF_YN, "
                    + " CREATED_AT,"
                    + " CREATED_BY,"
                    + " UPDATED_AT,"
                    + " UPDATED_BY)" +
                    " VALUES (?,?,?,?,?,?,?,'Y',GETDATE(),'system',GETDATE(),'system') ";

            jdbcTemplate.batchUpdate(
                    sql,
                    members,
                    100,
                    new ParameterizedPreparedStatementSetter<SAM130TVVO>() {
                        public void setValues(PreparedStatement ps, SAM130TVVO argument) throws SQLException {
                            ps.setString(1, argument.getCUST_CODE());
                            ps.setString(2, argument.getCHILD_CODE());
                            ps.setString(3, "1");
                            ps.setString(4, argument.getCHILD_CODE());
                            ps.setString(5, argument.getCHILD_NAME());
                            ps.setString(6, argument.getCREATE_DATE());
                            ps.setString(7, argument.getCHILD_REPRE_NUM());
                        }
                    });
        }
    }

    public void createTcherData() throws Exception {
        String sql = "";
        List<HPB100TVVO> tchers = FroebelIFUtils.getHPB100TV();
        if (null != tchers) {
            tcherManageService.deleteIfData();
            sql = "INSERT INTO TB_ERP_TCHER000 " +
                    "(TCHER_CD, "
                    + "	TCHER_NM, "
                    + "	TCHER_TYPE, "
                    + "	TCHER_TEL_NO, "
                    + "	TCHER_HP_NO, "
                    + "	TCHER_EMAIL, "
                    + "	LOCAL_CD, "
                    + "	TCHER_BIRTHDAY, "
                    + "	COUNTRY_CD, "
                    + "	RESIDENCE_TYPE, "
                    + "	TCHER_ZIPCODE, "
                    + "	TCHER_ADDRESS1, "
                    + "	TCHER_ADDRESS2, "
                    + "	JOIN_DT, "
                    + "	OUT_DT, "
                    + "	OUT_REASON_CD, "
                    + "	WORK_CD, "
                    + "	WORK_REASON_CD, "
                    + "	WORK_REG_CD, "
                    + "	ORG_CD, "
                    + "	RANK_CD, "
                    + "	CC_ORG_CD, "
                    + "	CC_RANK_CD, "
                    + "	EDU_YEAR, "
                    + "	EDU_MONTH, "
                    + "	GUARANTEE_DOC_CD, "
                    + "	INCOME_TYPE, "
                    + "	COMPANY_TYPE, "
                    + "	SECT_CODE, "
                    + "	COMPANY_REG_NUM, "
                    + "	BANK_CD, "
                    + "	BANK_ACCOUNT_NM, "
                    + "	BANK_ACCOUNT_NO, "
                    + "	REMARK, "
                    + "	BRING_TCHER_CD, "
                    + "	IF_YN, "
                    + " CREATED_AT,"
                    + " CREATED_BY,"
                    + " UPDATED_AT,"
                    + " UPDATED_BY)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'Y',GETDATE(),'system',GETDATE(),'system') ";
            jdbcTemplate.batchUpdate(
                    sql,
                    tchers,
                    100,
                    new ParameterizedPreparedStatementSetter<HPB100TVVO>() {
                        public void setValues(PreparedStatement ps, HPB100TVVO argument) throws SQLException {
                            ps.setString(1, argument.getPERSON_NUMB());
                            ps.setString(2, argument.getNAME());
                            ps.setString(3, argument.getPERSON_TYPE());
                            ps.setString(4, argument.getTELEPHON());
                            ps.setString(5, argument.getPHONE_NO());

                            ps.setString(6, argument.getEMAIL_ADDR());
                            ps.setString(7, argument.getFOREIGN_YN());
                            ps.setString(8, argument.getBIRTH_DATE());
                            ps.setString(9, argument.getNATION_CODE());
                            ps.setString(10, argument.getDWELLING_YN());

                            ps.setString(11, argument.getZIP_CODE());
                            ps.setString(12, argument.getADDR1());
                            ps.setString(13, argument.getADDR2());
                            ps.setString(14, argument.getJOIN_DATE());
                            ps.setString(15, argument.getRETR_DATE());

                            ps.setString(16, argument.getRETR_RESN());
                            ps.setString(17, argument.getWORK_STATE());
                            //WORK_REASON_CD,
                            ps.setString(18, null);
                            ps.setString(19, argument.getREG_TYPE());
                            ps.setString(20, argument.getHR_TREE_CODE());
                            ps.setString(21, argument.getCLASS_CODE());

                            ps.setString(22, argument.getWITH_TREE_CODE());
                            ps.setString(23, argument.getWITH_CLASS_CODE());


                            ps.setString(24, argument.getEDU_YYYY());
                            ps.setString(25, argument.getEDU_MM());

                            ps.setString(26, argument.getDOCUMENT_YN());
                            ps.setString(27, argument.getDED_CODE());
                            ps.setString(28, argument.getBUSINESS_TYPE());
                            ps.setString(29, argument.getSECT_CODE());
                            ps.setString(30, argument.getCOMP_NUM());

                            ps.setString(31, argument.getBANK_CODE());
                            ps.setString(32, argument.getDEPOSITOR());
                            ps.setString(33, argument.getBANK_ACCOUNT());
                            ps.setString(34, argument.getREMARK());
                            ps.setString(35, argument.getBRING_PERSON_NUMB());
                        }
                    });

            for (HPB100TVVO tcher : tchers) {
                String mode = "NEW";
                if (null != tcher) {
                    User user = new User();
                    user.setUserType(tcher.getPERSON_TYPE());
                    user.setUserCd(tcher.getPERSON_NUMB());
                    user.setUserNm(tcher.getNAME());
                    user.setTelNo(tcher.getTELEPHON());
                    user.setHpNo(tcher.getPHONE_NO());
                    user.setBirthday(tcher.getBIRTH_DATE());
                    user.setJoinDt(tcher.getJOIN_DATE());
                    user.setOutDt(tcher.getRETR_DATE());
                    user.setZipcode(tcher.getZIP_CODE());
                    user.setAddress1(tcher.getADDR1());
                    user.setAddress2(tcher.getADDR2());

                    //12 상담조직 // 14 방문조직
                    user.setAreaCd("AREA00001");
                    user.setOrgClass(tcher.getPERSON_TYPE());
                    user.setOrgCd(tcher.getHR_TREE_CODE());

                    user.setRankCd(tcher.getCLASS_CODE());
                    user.setIfYn("Y");

                    //근무상태 (퇴사)
                    if (tcher.getWORK_STATE().equals("4")) {
                        user.setUserStatus("20");
                    } else {
                        user.setUserStatus("10");
                    }

                    //결정가능자(ex:팀장)
                    if (tcher.getCLASS_CODE().equals("310")) {
                        user.setDecisionYn("Y");
                    } else {
                        user.setDecisionYn("N");
                    }

                    userService.saveTcher(user, mode, "N");
                }
            }
        }
    }


    public void createTcherData2() throws Exception {
        String sql = "";
        //인사정보
        List<HPB130TVVO> charges1 = FroebelIFUtils.getHPB130TV();
        if (null != charges1) {
            tcherInfChargeService.deleteIfData();

            sql = "INSERT INTO TB_ERP_TCHER150 " +
                    "(TCHER_CD,"
                    + "CHARGE_DT,"
                    + "CHARGE_CODE,"
                    + "COMPANY_NAME,"
                    + "SECT_CODE,"
                    + "ORG_CD,"
                    + "RANK_CD,"
                    + "CC_ORG_CD,"
                    + "CC_RANK_CD,"
                    + "CHARGE_DTCD,"
                    + "REMARK,"
                    + "IF_YN,"
                    + "CREATED_AT,"
                    + "CREATED_BY,"
                    + "UPDATED_AT,"
                    + "UPDATED_BY)" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,'Y',GETDATE(),'system',GETDATE(),'system') ";


            jdbcTemplate.batchUpdate(
                    sql,
                    charges1,
                    100,
                    new ParameterizedPreparedStatementSetter<HPB130TVVO>() {
                        public void setValues(PreparedStatement ps, HPB130TVVO argument) throws SQLException {
                            ps.setString(1, argument.getPERSON_NUMB());
                            ps.setString(2, argument.getCHARGE_DATE());
                            ps.setString(3, argument.getCHARGE_CODE());
                            ps.setString(4, argument.getCOMPANY_NAME());
                            ps.setString(5, argument.getSECT_CODE());

                            ps.setString(6, argument.getTREE_CODE());
                            ps.setString(7, argument.getCLASS_CODE());

                            ps.setString(8, argument.getWITH_TREE_CODE());
                            ps.setString(9, argument.getWITH_CLASS_CODE());

                            ps.setString(10, argument.getCHARGE_DTCD());
                            ps.setString(11, argument.getREMARK());
                        }
                    });
        }


        //상담 매출
    	/*
    	List<HPB201TVVO> sales1 = FroebelIFUtils.getHPB201TV_HAPPY();
    	if(null!=sales1){
    		tcherInfSalesHappyService.deleteIfData();

    		sql = "INSERT INTO TB_ERP_TCHER161 " +
        	    	"(TCHER_CD, "+
					"	SALES_YYYYMM, "+
					"	PLAN_AMT, "+
					"	CONT_SALE_AMT, "+
					"	PERCENTAGE, "+
					"	WAGES_SUM_AMT, "+
					"	DED_SUM_AMT, "+
					"	REAL_SUM_AMT, "+
					"	IF_YN, "+
					"	CREATED_AT, "+
					"	CREATED_BY, "+
					"	UPDATED_AT, "+
					"	UPDATED_BY "+
					" )" +
        	    	" VALUES (?,?,?,?,?,?,?,?,'Y',GETDATE(),'system',GETDATE(),'system') "	;


    		jdbcTemplate.batchUpdate(
          		  sql,
          		  sales1,
          	      100,
          	      new ParameterizedPreparedStatementSetter<HPB201TVVO>() {
          	        public void setValues(PreparedStatement ps, HPB201TVVO argument) throws SQLException {
          				ps.setString(1,argument.getPERSON_NUMB());
          				ps.setString(2,argument.getSALES_YYYYMM());
          				ps.setBigDecimal(3,argument.getPLAN_AMT());
          				ps.setBigDecimal(4,argument.getCONT_SALE_AMT());
          				ps.setBigDecimal(5,argument.getPERCENTAGE());

          				ps.setBigDecimal(6,argument.getWAGES_SUM_AMT());
          				ps.setBigDecimal(7,argument.getDED_SUM_AMT());
          				ps.setBigDecimal(8,argument.getREAL_SUM_AMT());
          	         }
             });
    	}


    	//방문 매출 -속도 문제 불러오기안됨
    	List<HPB201TVVO> sales2 = FroebelIFUtils.getHPB201TV_EDU();
    	if(null!=sales2){
    		tcherInfSalesEduService.deleteIfData();

    		sql = "INSERT INTO TB_ERP_TCHER162 " +
        	    	"(TCHER_CD, "+
					"	SALES_YYYYMM, "+
					"	PLAN_SU, "+
					"	RESULT_SU, "+
					"	EFFECT_RIZ, "+
					"	GNL_AMT, "+
					"	PRE_AMT, "+
					"	TRANS_AMT, "+
					"	IF_YN, "+
					"	CREATED_AT, "+
					"	CREATED_BY, "+
					"	UPDATED_AT, "+
					"	UPDATED_BY "+
					" )" +
        	    	" VALUES (?,?,?,?,?,?,?,?,'Y',GETDATE(),'system',GETDATE(),'system') "	;


    		jdbcTemplate.batchUpdate(
          		  sql,
          		  sales2,
          	      100,
          	      new ParameterizedPreparedStatementSetter<HPB201TVVO>() {
          	        public void setValues(PreparedStatement ps, HPB201TVVO argument) throws SQLException {
          				ps.setString(1,argument.getPERSON_NUMB());
          				ps.setString(2,argument.getSALES_YYYYMM());
          				ps.setBigDecimal(3,argument.getPLAN_SU());
          				ps.setBigDecimal(4,argument.getRESULT_SU());
          				ps.setBigDecimal(5,argument.getEFFECT_RIZ());

          				ps.setBigDecimal(6,argument.getGNL_AMT());
          				ps.setBigDecimal(7,argument.getPRE_AMT());
          				ps.setBigDecimal(8,argument.getTRANS_AMT());
          	         }
             });
    	}*/


        //교사정보
        List<HPB150TVVO> tcherInfos = FroebelIFUtils.getHPB150TV();
        if (null != tcherInfos) {
            tcherInfTcherService.deleteIfData();

            sql = "INSERT INTO TB_ERP_TCHER170 " +
                    "(  TCHER_CD, " +
                    "	RATE1, " +
                    "	RATE2, " +
                    "	PAY_START_YYYYMM, " +
                    "	RATE_MONTH1, " +
                    "	RATE_MONTH2, " +
                    "	RATE_MGR, " +
                    "	MGR_START_YYYYMM, " +
                    "	ADD_RATE, " +
                    "	NRATE1, " +
                    "	NRATE2, " +
                    "	WEEKDAY, " +
                    "	IF_YN, " +
                    "	CREATED_AT, " +
                    "	CREATED_BY, " +
                    "	UPDATED_AT, " +
                    "	UPDATED_BY " +
                    " )" +
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,'Y',GETDATE(),'system',GETDATE(),'system') ";


            jdbcTemplate.batchUpdate(
                    sql,
                    tcherInfos,
                    100,
                    new ParameterizedPreparedStatementSetter<HPB150TVVO>() {
                        public void setValues(PreparedStatement ps, HPB150TVVO argument) throws SQLException {
                            ps.setString(1, argument.getPERSON_NUMB());
                            ps.setBigDecimal(2, argument.getRATE_1());
                            ps.setBigDecimal(3, argument.getRATE_2());
                            ps.setString(4, argument.getPAY_START_YYYYMM());
                            ps.setString(5, argument.getRATE_MONTH_1());
                            ps.setString(6, argument.getRATE_MONTH_2());
                            ps.setBigDecimal(7, argument.getRATE_MGR());
                            ps.setString(8, argument.getMGR_START_YYYYMM());
                            ps.setBigDecimal(9, argument.getNRATE1());
                            ps.setBigDecimal(10, argument.getNRATE2());
                            ps.setBigDecimal(11, argument.getADD_RATE());
                            ps.setString(12, argument.getWEEKDAY());
                        }
                    });
        }
    }

    public void dropSchema() {
        try {
            List<String> tableList = schemaGenerator.getTableList();

            tableList.forEach(table -> {
                jdbcTemplate.update("DROP TABLE " + table);
            });
        } catch (Exception e) {
            // ignore
        }
    }
}
