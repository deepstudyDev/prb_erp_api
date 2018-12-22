package com.prb.erp.controllers;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.sms.list.SendSaveList;
import com.prb.erp.domain.sms.list.SendSaveListPagingVO;
import com.prb.erp.domain.sms.list.SendSaveListService;
import com.prb.erp.domain.sms.master.SendMasterPagingVO;
import com.prb.erp.domain.sms.master.SendMasterService;
import com.prb.erp.domain.sms.master.SendMasterVO;
import com.prb.erp.domain.sms.master.SendRequestVO;
import com.prb.erp.domain.sms.master.SendSmsUserListVO;
import com.prb.erp.domain.sms.master.SendSmsUserPagingVO;
import com.prb.erp.utils.UserLogUtil;

/*
 * SMS관리
 */
@Controller
@RequestMapping(value = "/api/v1/sms")
public class SendManageController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SendManageController.class);
	
	@Inject private SendMasterService masterService;
	@Inject private SendSaveListService sendSaveListService;

	@RequestMapping(value = "getSendList", method = RequestMethod.GET, produces = APPLICATION_JSON)	
    public SendMasterPagingVO getSendList(RequestParams<SendMasterVO> requestParams, Pageable pageable) {		
    	UserLogUtil.saveUserLog("SendManageController","문자관리","GET");
    	return masterService.getSendList(requestParams,pageable);
    } 
	

	//SMS 대상 유저 조회
	@RequestMapping(value = "getSmsUserList", method = RequestMethod.GET, produces = APPLICATION_JSON)	
    public SendSmsUserPagingVO getSmsUserList(RequestParams<SendSmsUserListVO> requestParams, Pageable pageable) {		
    	UserLogUtil.saveUserLog("SendManageController","문자관리-대상유저조회","GET");
    	return masterService.getSmsUserList(requestParams,pageable);
    } 
	
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse saveMaster(@RequestBody SendRequestVO list) {
    	UserLogUtil.saveUserLog("SendManageController","문자관리","PUT");
        masterService.saveSend(list);
        return ok();
    }

    //내문자함 조회 -페이징
	@RequestMapping(value = "getSaveList", method = RequestMethod.GET, produces = APPLICATION_JSON)	
    public SendSaveListPagingVO getSaveList(RequestParams<SendSaveList> vo, Pageable pageable) {
    	UserLogUtil.saveUserLog("SendManageController","문자관리-내문자함","GET");
		return sendSaveListService.getSendSaveList(vo, pageable);
    }

    @RequestMapping(value ="smsSave" ,method = {RequestMethod.POST, RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse saveSendList(@RequestBody SendSaveList vo) {
    	UserLogUtil.saveUserLog("SendManageController","문자관리-내문자함","PUT");
    	sendSaveListService.saveSendMsg(vo);
        return ok();
    }

    @RequestMapping(value ="deleteMsg" ,method = {RequestMethod.POST, RequestMethod.DELETE}, produces = APPLICATION_JSON)
    public ApiResponse deleteMsg(@RequestBody SendSaveList vo) {
    	UserLogUtil.saveUserLog("SendManageController","문자관리-내문자함","DELETE");
    	sendSaveListService.deleteMsg(vo);
        return ok();
    }
    
}
