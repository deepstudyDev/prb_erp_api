package com.prb.erp.controllers;
 
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.froebel.HPB201TVVO;
import com.prb.erp.domain.tcher.TcherManage;
import com.prb.erp.domain.tcher.TcherManagePagingVO;
import com.prb.erp.domain.tcher.TcherManageService;
import com.prb.erp.domain.tcher.TcherManageVO;
import com.prb.erp.domain.tcher.assign.TcherAssignManage;
import com.prb.erp.domain.tcher.assign.TcherAssignManagePagingVO;
import com.prb.erp.domain.tcher.assign.TcherAssignManageService;
import com.prb.erp.domain.tcher.assign.TcherAssignManageVO;
import com.prb.erp.domain.tcher.inf.charge.TcherInfChargeService;
import com.prb.erp.domain.tcher.inf.charge.TcherInfChargeVO;
import com.prb.erp.domain.tcher.inf.salesEdu.TcherInfSalesEduService;
import com.prb.erp.domain.tcher.inf.salesEdu.TcherInfSalesEduVO;
import com.prb.erp.domain.tcher.inf.salesHappy.TcherInfSalesHappyService;
import com.prb.erp.domain.tcher.inf.salesHappy.TcherInfSalesHappyVO;
import com.prb.erp.domain.tcher.inf.tcher.TcherInfTcherService;
import com.prb.erp.domain.tcher.inf.tcher.TcherInfTcherVO;
import com.prb.erp.domain.tcher.rest.TcherRestManage;
import com.prb.erp.domain.tcher.rest.TcherRestManagePagingVO;
import com.prb.erp.domain.tcher.rest.TcherRestManageService;
import com.prb.erp.domain.tcher.rest.TcherRestManageVO;
import com.prb.erp.domain.tcher.trans.TcherTransManage;
import com.prb.erp.domain.tcher.trans.TcherTransManagePagingVO;
import com.prb.erp.domain.tcher.trans.TcherTransManageService;
import com.prb.erp.domain.tcher.trans.TcherTransManageVO;
import com.prb.erp.utils.UserLogUtil;

 /*
  * 교사관리
  */
@Controller
@RequestMapping(value = "/api/v1/tcherManage")
public class TcherManageController extends BaseController {

    @Inject
    private TcherManageService tcherManageService;
    @Inject
    private TcherAssignManageService tcherAssignManageService; 
    @Inject
    private TcherTransManageService tcherTransManageService; 
    @Inject
    private TcherRestManageService tcherRestManageService; 
    @Inject
    private TcherInfChargeService tcherInfChargeService;
    @Inject
    private TcherInfSalesHappyService tcherInfSalesHappyService;
    @Inject
    private TcherInfSalesEduService tcherInfSalesEduService;
    @Inject
    private TcherInfTcherService tcherInfTcherService;
    
    //조회 (리스트)
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse gets(RequestParams<TcherManageVO> vo) {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-조회 (리스트)","GET");
    	List<TcherManageVO> list = tcherManageService.getAll(vo); 
        return Responses.ListResponse.of(list); 
    }

    //조회 (페이징) 
    @RequestMapping(value ="getTcherList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public TcherManagePagingVO getTcherList(RequestParams<TcherManageVO> vo, Pageable pageable) {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-조회 (페이징)","GET");
    	return tcherManageService.getTcherList(vo,pageable);
    }
    

    //조회 (단건) 
    @RequestMapping(value ="getTcher", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public TcherManageVO getTcher(RequestParams<TcherManageVO> vo) {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-조회 (단건)","GET");
    	return tcherManageService.getTcher(vo);
    }

    //교사저장
    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public TcherManage save(@RequestBody TcherManage tcher) throws Exception {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-교사저장","PUT");
    	return tcherManageService.saveTcher(tcher);
    }

    //교사저장 - 기타사항
    @RequestMapping(value = "saveTcherEtcRemark", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse saveTcherEtcRemark(@RequestBody TcherManage tcher) throws Exception {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-교사저장-기타사항","PUT");
    	tcherManageService.saveTcherEtcRemark(tcher);
        return ok();
    }
    
    //패스워드초기화
    @RequestMapping(value = "resetPs", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse resetPs(@RequestBody TcherManage tcher) throws Exception {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-패스워드초기화","PUT");
    	tcherManageService.resetPs(tcher);
        return ok();
    }
    
    
    //배정대상조회(단건)
    @RequestMapping(value ="getTcherAssign", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public TcherAssignManageVO getTcherAssign(RequestParams<TcherAssignManageVO> vo) {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-배정대상조회(단건)","GET");    	
    	return tcherAssignManageService.gets(vo);
    }
    

    //배정대상조회(페이징)
    @RequestMapping(value ="getTcherAssignList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public TcherAssignManagePagingVO getTcherAssignList(RequestParams<TcherAssignManageVO> vo, Pageable pageable) {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-배정대상조회(페이징)","GET");    	
    	return tcherAssignManageService.getTcherAssignList(vo,pageable);
    }

    //배정대상조회(저장)
    @RequestMapping(value="assign", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse assignSave(@RequestBody TcherAssignManage vo) throws Exception {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-배정","PUT");
    	tcherAssignManageService.saveAssign(vo);
    	return ok();
    }
    

    //인수인계조회(단건)
    @RequestMapping(value ="getTcherTrans", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public TcherTransManageVO getTcherTrans(RequestParams<TcherTransManageVO> vo) {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-인수인계조회(단건)","GET");    	
    	return tcherTransManageService.getTcherTrans(vo);
    }

    //인수인계조회(페이징)
    @RequestMapping(value ="getTcherTransList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public TcherTransManagePagingVO getTcherTransList(RequestParams<TcherTransManageVO> vo, Pageable pageable) {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-인수인계조회(페이징)","GET");    	
    	return tcherTransManageService.getTcherTransList(vo,pageable);
    }

    //인수인계요청
    @RequestMapping(value="transRequest", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public TcherTransManage transRequest(@RequestBody TcherTransManage vo) throws Exception {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-인수인계요청","PUT");
    	return tcherTransManageService.transRequest(vo);
    }

    //인수인계완료
    @RequestMapping(value="transSave", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse transSave(@RequestBody TcherTransManage vo) throws Exception {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-인수인계완료","PUT");
    	tcherTransManageService.transSave(vo);
    	return ok();
    }
    

    //학습휴식조회(단건)
    @RequestMapping(value ="getTcherRest", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public TcherRestManageVO getTcherRest(RequestParams<TcherRestManageVO> vo) {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-학습휴식(단건)","GET");    	
    	return tcherRestManageService.getTcherRest(vo);
    }

    //학습휴식조회(페이징)
    @RequestMapping(value ="getTcherRestList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public TcherRestManagePagingVO getTcherRestList(RequestParams<TcherRestManageVO> vo, Pageable pageable) {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-학습휴식(페이징)","GET");    	
    	return tcherRestManageService.getTcherRestList(vo,pageable);
    }
 
    //학습휴식요청
    @RequestMapping(value="restRequest", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public TcherRestManage restRequest(@RequestBody TcherRestManage vo) throws Exception {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-학습휴식","PUT");
    	return tcherRestManageService.restRequest(vo);
    }

    //학습휴식완료
    @RequestMapping(value="restSave", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse restSave(@RequestBody TcherRestManage vo) throws Exception {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-학습휴식","PUT");
    	tcherRestManageService.restSave(vo);
    	return ok();
    } 
    
    
    //프뢰벨 :: 교사 :: 영입현황 :: 상담
    @RequestMapping(value ="getTcherBringList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getTcherBringList(RequestParams<TcherManageVO> vo) {
    	UserLogUtil.saveUserLog("TcherManageController","교사관리-조회 (리스트)","GET");
    	List<TcherManageVO> list = tcherManageService.getAll(vo);
        return Responses.ListResponse.of(list); 
    }
    
    
    //프뢰벨 :: 교사 :: 사원이력(인사정보) :: 상담/방문
    @RequestMapping(value ="getTcherChargeList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public  Responses.ListResponse getTcherChargeList(RequestParams<TcherInfChargeVO> vo) {
    	List<TcherInfChargeVO> list = tcherInfChargeService.getTcherInfChargeList(vo);
        return Responses.ListResponse.of(list); 
    }
    
    //프뢰벨 :: 교사 :: 교사정보 :: 방문
    @RequestMapping(value ="getTcherInfoList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getTcherInfoList(RequestParams<TcherInfTcherVO> vo) {
    	List<TcherInfTcherVO> list = tcherInfTcherService.getTcherInfoList(vo);
        return Responses.ListResponse.of(list);  
    }    

    /*실시간*/
    //프뢰벨 :: 교사 :: 매출정보 :: 상담
    @RequestMapping(value ="getTcherSalesHappyList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getTcherSalesHappyList(RequestParams<TcherInfSalesHappyVO> vo) {
    	List<HPB201TVVO> list = tcherInfSalesHappyService.getTcherSalesHappyList(vo);
        return Responses.ListResponse.of(list);  
    }

    //프뢰벨 :: 교사 :: 매출정보 :: 방문
    @RequestMapping(value ="getTcherSalesEduList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getTcherSalesEduList(RequestParams<TcherInfSalesEduVO> vo) {
    	List<HPB201TVVO> list = tcherInfSalesEduService.getTcherSalesEduList(vo);
        return Responses.ListResponse.of(list);  
    }
    
    
    /*1회성
    //프뢰벨 :: 교사 :: 매출정보 :: 상담
    @RequestMapping(value ="getTcherSalesHappyList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getTcherSalesHappyList(RequestParams<TcherInfSalesHappyVO> vo) {
    	List<TcherInfSalesHappyVO> list = tcherInfSalesHappyService.getTcherSalesHappyList(vo);
        return Responses.ListResponse.of(list);  
    }

    //프뢰벨 :: 교사 :: 매출정보 :: 방문
    @RequestMapping(value ="getTcherSalesEduList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getTcherSalesEduList(RequestParams<TcherInfSalesEduVO> vo) {
    	List<TcherInfSalesEduVO> list = tcherInfSalesEduService.getTcherSalesEduList(vo);
        return Responses.ListResponse.of(list);  
    }*/

    
}