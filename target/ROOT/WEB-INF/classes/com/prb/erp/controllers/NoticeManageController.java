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
import com.prb.erp.domain.area.org.AreaOrgManage;
import com.prb.erp.domain.notice.NoticeManage;
import com.prb.erp.domain.notice.NoticeManagePagingVO;
import com.prb.erp.domain.notice.NoticeManageService;
import com.prb.erp.domain.notice.NoticeManageVO;
import com.prb.erp.utils.UserLogUtil;

 /*
  * 공지사항관리
  */
@Controller
@RequestMapping(value = "/api/v1/noticeManage")
public class NoticeManageController extends BaseController {
  
    @Inject private NoticeManageService noticeManageService;

    //조회 - 전체
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse  gets(RequestParams<NoticeManageVO> vo) {
    	UserLogUtil.saveUserLog("NoticeManageController","공지사항관리","GET");
    	List<NoticeManageVO> list = noticeManageService.getNoticeListAll(vo);
        return Responses.ListResponse.of(list); 
    }

    //조회 - 단건
    @RequestMapping(value = "getNotice", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public NoticeManageVO getNotice(RequestParams<NoticeManageVO> vo, Pageable pageable) {
    	UserLogUtil.saveUserLog("NoticeManageController","공지사항관리","GET");    	
    	return noticeManageService.getNotice(vo);
    }
    

    //조회 - 페이징
     @RequestMapping(value = "getNoticeList", method = RequestMethod.GET, produces = APPLICATION_JSON)
     public NoticeManagePagingVO getNoticeList(RequestParams<NoticeManageVO> vo, Pageable pageable) {
         UserLogUtil.saveUserLog("NoticeManageController","공지사항관리","GET");
         return noticeManageService.getNoticeList(vo,pageable);
     }

    //저장
    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public NoticeManage save(@RequestBody NoticeManage notice) throws Exception {
    	UserLogUtil.saveUserLog("NoticeManageController","공지사항관리","PUT");    	
    	return noticeManageService.saveNotice(notice);
    }
    
    //삭제
    @RequestMapping(method = {RequestMethod.DELETE}, produces = APPLICATION_JSON)
    public ApiResponse deleteNotice(@RequestBody NoticeManage notice) throws Exception {
    	UserLogUtil.saveUserLog("NoticeManageController","공지사항관리","DELETE");    	
    	noticeManageService.delete(notice);
    	return ok();
    }
}