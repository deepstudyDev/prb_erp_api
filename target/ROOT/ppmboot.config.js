(function () {
    if (ppmboot && ppmboot.def) {

        ppmboot.def["DEFAULT_TAB_LIST"] = [
            {menuCode: "00-dashboard", id: "dashboard1", progNm: '홈', menuNm: '홈', progPh: '/jsp/dashboard.jsp', url: '/jsp/dashboard.jsp?progCd=dashboard', status: "on", fixed: true}
        ];

        ppmboot.def["API"] = {
            //알림
            "users": "/api/v1/users",
            "commonCodes": "/api/v1/basicCode/detail",
            "basic": "/api/v1/basicCode",
            "programs": "/api/v1/programs",
            "programHelp": "/api/v1/programHelp",
            "menu": "/api/v2/menu",     
            "manual": "/api/v1/manual",
            "errorLogs": "/api/v1/errorLogs",
            "userLogs": "/api/v1/userLogs",
            "files": "/api/v1/files",
            //사용자별권한관리
            "userAuth": "/api/v1/userAuth",
            "formDetail" : "/api/v1/formitemdetail",            
            //PLANT 관리
            "plantManagement": "/api/v1/plantManagement" ,
            "approval" : "/api/v1/approval",          
            
            //apps
            "apps" : "/api/v1/appsManagement"    ,
            
            //지역사관리
            "areaManage" : "/api/v1/areaManage",
            //회원관리
            "memberManage" : "/api/v1/memberManage",
            //회원관리
            "memManage" : "/api/v1/memManage",
            //교사관리
            "tcherManage" : "/api/v1/tcherManage",
            //제품관리
            "productManage" : "/api/v1/productManage",
            //상품관리
            "goodsManage" : "/api/v1/goodsManage",
            //상품관리
            "goodsCategory" : "/api/v1/goodsCategory", 
            //문자관리
            "sms" : "/api/v1/sms", 
            //공지사항
            "noticeManage" : "/api/v1/noticeManage",
        };

        ppmboot.def["MODAL"] = {    
            "USER-LOG-MODAL": {
                width:1000,
                height: 800,
                iframe: {
                    url: "/jsp/erp/modal/user-log-modal.jsp"
                },
                header: false
            },  
            "AREA-MODAL": {
                width:800,
                height: 770,
                iframe: {
                    url: "/jsp/erp/modal/area-modal.jsp"
                },
                header: false
            },  
            
            //상담조직
            "AREA-CONSULT-MODAL": {
                width:800,
                height: 770,
                iframe: {
                    url: "/jsp/erp/modal/area-consult-modal.jsp"
                },
                header: false
            },  
            //방문조직
            "AREA-VISIT-MODAL": {
                width:800,
                height: 770,
                iframe: {
                    url: "/jsp/erp/modal/area-visit-modal.jsp"
                },
                header: false
            },  
            "PAY-WAY-MODAL": {
                width:600,    
                height: 290,
                iframe: {
                    url: "/jsp/erp/modal/pay-way-modal.jsp"
                },
                header: false
            },  
            "SCHOOL-MODAL": {
                width:700,
                height: 800,
                iframe: {
                    url: "/jsp/erp/modal/school-modal.jsp"
                },
                header: false
            },  
            "TCHER-MODAL": {
                width:900,
                height: 800,
                iframe: {
                    url: "/jsp/erp/modal/tcher-modal.jsp"
                },
                header: false
            },  
            //배정가능교사조회
            "ASSIGN-TCHER-MODAL": {
                width:900,
                height: 730,
                iframe: {
                    url: "/jsp/erp/tcher/modal/assign-tcher-modal.jsp"
                },
                header: false
            },  
            "PRE-SMS-MODAL": {
                width:340,
                height: 650,
                iframe: {
                    url: "/jsp/erp/sms/modal/pre-sms-modal.jsp"
                },
                header: false
            },  
            //문자 대상 유저 조회
            "SMS-USER-MODAL": {
                width: 900,
                height: 820,  
                iframe: {
                    url: "/jsp/erp/sms/modal/sms-user-modal.jsp"
                },
                header: false
            },  
            //AS
            "AS-LIST-MODAL": {
                width: 1000,
                height: 800,  
                iframe: {
                    url: "/jsp/erp/modal/as-list-modal.jsp"
                },
                header: false
            }, 
            //계약서보기
            "CONTRACT-LIST-MODAL": {
                width: 1000,
                height: 800,  
                iframe: {
                    url: "/jsp/erp/modal/contract-modal.jsp"
                },
                header: false
            }, 
            //납부현황
            "PAYMENT-LIST-MODAL": {
                width: 1000,
                height: 800,  
                iframe: {
                    url: "/jsp/erp/modal/payment-list-modal.jsp"
                },
                header: false
            }, 
            //계약추가
            "ADD-MEMBER-MODAL": {
                width: 1200,
                height: 1000,  
                iframe: {
                    url: "/jsp/erp/member/modal/add-member-modal.jsp"
                },
                header: false
            }, 
            //계약취소
            "REQUEST-CANCEL-MEMBER-MODAL": {
                width: 700,
                height: 600,  
                iframe: {
                    url: "/jsp/erp/member/modal/request-cancel-member-modal.jsp"
                },
                header: false
            }, 
            //인수인계요청
            "REQUEST-TRANS-MEMBER-MODAL": {
                width: 900,
                height: 800,  
                iframe: {
                    url: "/jsp/erp/member/modal/request-trans-member-modal.jsp"
                },
                header: false
            }, 
            //휴식신청
            "REQUEST-REST-MEMBER-MODAL": {
                width: 800,
                height: 650,  
                iframe: {
                    url: "/jsp/erp/member/modal/request-rest-member-modal.jsp"
                },
                header: false
            }, 
            //교새배정
            "REQUEST-ASSIGN-MEMBER-MODAL": {
                width: 1000,
                height: 800,  
                iframe: {
                    url: "/jsp/erp/member/modal/tcher-assign-manage-modal.jsp"
                },
                header: false
            }, 
            //학습중단관리
            "ASSIGN-MODAL": {
                width: 700,
                height: 450,  
                iframe: {
                    url: "/jsp/erp/modal/assign-modal.jsp"
                },
                header: false
            }, 
    		"COMPANY-MODAL": {
                width:700,  
                height: 650,
                iframe: {
                    url: "/jsp/erp/modal/company-modal.jsp"
                },
                header: false
            },      
            "ZIPCODE": {
                width:700,
                height: 650,
                iframe: {
                    url: "/jsp/common/zipcode.jsp"
                },
                header: false
            },
            "COMMON_CODE_MODAL": {
                width: 600,
                height: 650,
                iframe: {
                    url: "/jsp/system/system-config-common-code-modal.jsp"
                },
                header: false
            },  
            "FILE-MODAL": { 
                width: 800,
                height: 700,
                iframe: {
                    url: "/jsp/erp/modal/file-modal.jsp"
                },
                header: false
            },
            "FILE-MODAL3": { 
                width: 800,
                height: 700,
                iframe: {
                    url: "/jsp/mes/test/file-modal3.jsp"
                },
                header: false
            }
        };
    }


    var preDefineUrls = {
        "manual_downloadForm": "/api/v1/manual/excel/downloadForm",
        "manual_viewer": "/jsp/system/system-help-manual-view.jsp"
    };
    ppmboot.getURL = function (url) {
        if (ax5.util.isArray(url)) {
            if (url[0] in preDefineUrls) {
                url[0] = preDefineUrls[url[0]];
            }
            return url.join('/');

        } else {
            return url;
        }
    }
})();