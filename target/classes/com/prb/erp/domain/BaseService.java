package com.prb.erp.domain;

import java.io.Serializable;

import com.chequer.axboot.core.domain.base.AXBootBaseService;
import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import com.prb.erp.domain.area.QAreaManage;
import com.prb.erp.domain.area.org.QAreaOrgManage;
import com.prb.erp.domain.code.detail.QBasicCodeDetail;
import com.prb.erp.domain.code.master.QBasicCodeMaster;
import com.prb.erp.domain.file.QCommonFile;
import com.prb.erp.domain.item.goods.QGoodsManage;
import com.prb.erp.domain.item.goods.category.QGoodsCategory;
import com.prb.erp.domain.item.goods.category.product.QCategoryProduct;
import com.prb.erp.domain.item.goods.type.QGoodsTypeManage;
import com.prb.erp.domain.item.product.QProductManage;
import com.prb.erp.domain.key.management.QKeyManagement;
import com.prb.erp.domain.key.system.QSystemKeyManagement;
import com.prb.erp.domain.member.QMemberManage;
import com.prb.erp.domain.member.brother.QMemberBrother;
import com.prb.erp.domain.member.child.QMemberChild;
import com.prb.erp.domain.member.item.QMemberItem;
import com.prb.erp.domain.notice.QNoticeManage;
import com.prb.erp.domain.program.QProgram;
import com.prb.erp.domain.program.menu.QMenu;
import com.prb.erp.domain.sms.list.QSendSaveList;
import com.prb.erp.domain.tcher.QTcherManage;
import com.prb.erp.domain.tcher.assign.QTcherAssignManage;
import com.prb.erp.domain.tcher.inf.charge.QTcherInfCharge;
import com.prb.erp.domain.tcher.inf.salesEdu.QTcherInfSalesEdu;
import com.prb.erp.domain.tcher.inf.salesHappy.QTcherInfSalesHappy;
import com.prb.erp.domain.tcher.inf.tcher.QTcherInfTcher;
import com.prb.erp.domain.tcher.rest.QTcherRestManage;
import com.prb.erp.domain.tcher.trans.QTcherTransManage;
import com.prb.erp.domain.user.QUser;
import com.prb.erp.domain.user.auth.QUserAuth;
import com.prb.erp.domain.user.auth.menu.QAuthGroupMenu;
import com.prb.erp.domain.user.log.QUserLog;
import com.prb.erp.domain.user.role.QUserRole;


public class BaseService<T, ID extends Serializable> extends AXBootBaseService<T, ID> { 

    protected QUserRole qUserRole = QUserRole.userRole;
    protected QAuthGroupMenu qAuthGroupMenu = QAuthGroupMenu.authGroupMenu;
    protected QUser qUser = QUser.user;  
    protected QUserAuth qUserAuth = QUserAuth.userAuth;
    
    protected QProgram qProgram = QProgram.program;
    protected QUserLog qUserLog = QUserLog.userLog;
    protected QMenu qMenu = QMenu.menu;    
    protected QCommonFile qCommonFile = QCommonFile.commonFile;  
     
         
    protected QBasicCodeMaster qBasicCodeMaster = QBasicCodeMaster.basicCodeMaster;    
    protected QBasicCodeDetail qBasicCodeDetail = QBasicCodeDetail.basicCodeDetail;
     
    //KEY값관리
    protected QSystemKeyManagement qSystemKeyManagement = QSystemKeyManagement.systemKeyManagement;
    protected QKeyManagement qKeyManagement = QKeyManagement.keyManagement;
     
    //지역사관리
    protected QAreaManage qAreaManage = QAreaManage.areaManage;
    protected QAreaOrgManage qAreaOrgManage = QAreaOrgManage.areaOrgManage;
    
    //회원
    protected QMemberManage qMemberManage = QMemberManage.memberManage;
    protected QMemberItem qMemberItem = QMemberItem.memberItem;
    protected QMemberChild qMemberChild = QMemberChild.memberChild;
    protected QMemberBrother qMemberBrother = QMemberBrother.memberBrother;
    
    //교사
    protected QTcherManage qTcherManage = QTcherManage.tcherManage;
    protected QTcherAssignManage qTcherAssignManage = QTcherAssignManage.tcherAssignManage;
    protected QTcherTransManage qTcherTransManage = QTcherTransManage.tcherTransManage;
    protected QTcherRestManage qTcherRestManage = QTcherRestManage.tcherRestManage;
    
    protected QTcherInfCharge qTcherInfCharge = QTcherInfCharge.tcherInfCharge;
    protected QTcherInfSalesHappy qTcherInfSalesHappy = QTcherInfSalesHappy.tcherInfSalesHappy;
    protected QTcherInfSalesEdu qTcherInfSalesEdu = QTcherInfSalesEdu.tcherInfSalesEdu;
    protected QTcherInfTcher qTcherInfTcher = QTcherInfTcher.tcherInfTcher;
    
    //제품관리
    protected QProductManage qProductManage = QProductManage.productManage;
    //상품관리
    protected QGoodsManage qGoodsManage = QGoodsManage.goodsManage;
    protected QGoodsTypeManage qGoodsTypeManage = QGoodsTypeManage.goodsTypeManage;
    protected QGoodsCategory qGoodsCategory = QGoodsCategory.goodsCategory;
    protected QCategoryProduct qCategoryProduct = QCategoryProduct.categoryProduct;

    //문자관리
    protected QSendSaveList qSendSaveList = QSendSaveList.sendSaveList;
    

    //공지관리
    protected QNoticeManage qNoticeManage = QNoticeManage.noticeManage;
    
    protected AXBootJPAQueryDSLRepository<T, ID> repository;
    
    public BaseService() {
        super();    
    }

    public BaseService(AXBootJPAQueryDSLRepository<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }
}
