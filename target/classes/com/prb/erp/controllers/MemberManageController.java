package com.prb.erp.controllers;
 
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.member.MemberDetailVO;
import com.prb.erp.domain.member.MemberManage;
import com.prb.erp.domain.member.MemberManagePagingVO;
import com.prb.erp.domain.member.MemberManageService;
import com.prb.erp.domain.member.MemberManageVO;
import com.prb.erp.domain.member.MemberSchoolVO;
import com.prb.erp.domain.member.cancel.MemberCancelManage;
import com.prb.erp.domain.member.cancel.MemberCancelManagePagingVO;
import com.prb.erp.domain.member.cancel.MemberCancelManageService;
import com.prb.erp.domain.member.cancel.MemberCancelManageVO;
import com.prb.erp.utils.CommonApi;
import com.prb.erp.utils.UserLogUtil;

 /*
  * 회원관리
  */
@Controller
@RequestMapping(value = "/api/v1/memberManage")
public class MemberManageController extends BaseController {
   
    @Inject private MemberManageService memberManageService;
    @Inject private MemberCancelManageService memberCancelManageService;
    

    //학교검색 api
    @RequestMapping(value ="getSchoolListApi" , method = RequestMethod.GET, produces = APPLICATION_JSON)
    public @ResponseBody String getSchoolListApi(RequestParams<MemberSchoolVO> vo) {
    	String result =  CommonApi.getSchoolListApi(vo);
		return result;
    }  
    
    
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<MemberManageVO> vo) {
    	UserLogUtil.saveUserLog("MemberManageController","회원관리","GET");
    	List<MemberManageVO> list = memberManageService.gets(vo);
        return Responses.ListResponse.of(list); 
    }  

    //단건 조회
    @RequestMapping(value ="getMember" , method = RequestMethod.GET, produces = APPLICATION_JSON)
    public MemberManageVO getMember(RequestParams<MemberManageVO> vo) {
    	UserLogUtil.saveUserLog("MemberManageController","회원관리-단건조회","GET");
    	return memberManageService.getMember(vo);
    }  
    //단건 조회 :: 자녀
    @RequestMapping(value ="getMemberChildren" , method = RequestMethod.GET, produces = APPLICATION_JSON)
    public MemberManageVO getMemberChildren(RequestParams<MemberManageVO> vo) {
    	UserLogUtil.saveUserLog("MemberManageController","회원관리-단건조회::자녀","GET");
    	return memberManageService.getMemberChildren(vo);
    }  

    //조회 (페이징 포함)
    @RequestMapping(value="getMemberList" , method = RequestMethod.GET, produces = APPLICATION_JSON)
    public MemberManagePagingVO getMemberList(RequestParams<MemberManageVO> vo, Pageable pageable) {    	
    	UserLogUtil.saveUserLog("MemberManageController","회원관리-조회 (페이징 포함)","GET");
    	return memberManageService.getMemberList(vo,pageable);
    }  
    
    //계약상세 조회 (자녀별)
    @RequestMapping(value="getMemberDetail" , method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getMemberDetail(RequestParams<MemberDetailVO> vo) {    	
    	UserLogUtil.saveUserLog("MemberManageController","회원관리-계약상세 조회 (자녀별)","GET");
    	List<MemberDetailVO> list = memberManageService.getMemberDetail(vo);
        return Responses.ListResponse.of(list); 
    }  
    
    //계약저장
    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public MemberManage save(@RequestBody MemberManage request) throws Exception {
    	UserLogUtil.saveUserLog("MemberManageController","회원관리-계약저장","PUT");    	
    	return memberManageService.saveMember(request);
    }

    //계약추가(형제/시간)
    @RequestMapping(value="addChild" , method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse addChild(@RequestBody MemberManage request) throws Exception {
    	UserLogUtil.saveUserLog("MemberManageController","회원관리-계약추가(형제/시간)","PUT");    	
    	memberManageService.addChild(request);
    	return ok();
    }
    
    //계약취소 단건 조회
    @RequestMapping(value ="getMemberCancel" , method = RequestMethod.GET, produces = APPLICATION_JSON)
    public MemberCancelManageVO getMemberCancel(RequestParams<MemberCancelManageVO> vo) {
    	UserLogUtil.saveUserLog("MemberManageController","계약취소-단건조회","GET");
    	return memberCancelManageService.getMemberCancel(vo);
    }  

    //계약취소 조회 (페이징 포함)
    @RequestMapping(value="getMemberCancelList" , method = RequestMethod.GET, produces = APPLICATION_JSON)
    public MemberCancelManagePagingVO getMemberCancelList(RequestParams<MemberCancelManageVO> vo, Pageable pageable) {    	
    	UserLogUtil.saveUserLog("MemberManageController","회원관리-조회 (페이징 포함)","GET");
    	return memberCancelManageService.getMemberCancelList(vo,pageable);
    }  

    //계약취소요청
    @RequestMapping(value="requestCancel" , method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public MemberCancelManage requestCancel(@RequestBody MemberCancelManage request) throws Exception {
    	UserLogUtil.saveUserLog("MemberManageController","회원관리-계약취소요청","PUT");    	
    	return memberCancelManageService.requestCancel(request);
    }
    //계약취소승인
    @RequestMapping(value="approvalCancel" , method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse approvalCancel(@RequestBody MemberCancelManage request) throws Exception {
    	UserLogUtil.saveUserLog("MemberManageController","회원관리-계약취소승인","PUT");    	
    	memberCancelManageService.approvalCancel(request);
    	return ok();
    }
    
    //삭제 (사용하지 않음)
    @RequestMapping(method = {RequestMethod.DELETE}, produces = APPLICATION_JSON)
    public ApiResponse delete(@RequestBody MemberManage item) {
    	UserLogUtil.saveUserLog("MemberManageController","회원관리-삭제","DELETE");
    	memberManageService.delete(item);
        return ok();
    }
}