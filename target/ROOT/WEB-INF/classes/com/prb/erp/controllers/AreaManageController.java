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
import com.prb.erp.domain.area.AreaManage;
import com.prb.erp.domain.area.AreaManagePagingVO;
import com.prb.erp.domain.area.AreaManageService;
import com.prb.erp.domain.area.AreaManageVO;
import com.prb.erp.domain.area.org.AreaOrgManage;
import com.prb.erp.domain.area.org.AreaOrgManageListVO;
import com.prb.erp.domain.area.org.AreaOrgManageService;
import com.prb.erp.domain.area.org.AreaOrgManageVO;
import com.prb.erp.utils.UserLogUtil;

 /*
  * 지역사관리
  */
@Controller
@RequestMapping(value = "/api/v1/areaManage")
public class AreaManageController extends BaseController {
  
    @Inject private AreaManageService areaManageService;
    @Inject private AreaOrgManageService areaOrgManageService;

    //조회 - 전체
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse  gets(RequestParams<AreaManageVO> vo) {
    	UserLogUtil.saveUserLog("AreaManageController","지역사관리","GET");
    	List<AreaManageVO> list = areaManageService.getAreaListAll(vo);
        return Responses.ListResponse.of(list); 
    }

    //조회 - 단건
    @RequestMapping(value = "getArea", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public AreaManageVO getArea(RequestParams<AreaManageVO> vo, Pageable pageable) {
    	UserLogUtil.saveUserLog("AreaManageController","지역사관리","GET");    	
    	return areaManageService.getArea(vo);
    }
    

    //조회 - 페이징
    @RequestMapping(value = "getAreaList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public AreaManagePagingVO getAreaList(RequestParams<AreaManageVO> vo, Pageable pageable) {
    	UserLogUtil.saveUserLog("AreaManageController","지역사관리","GET");    	
    	return areaManageService.getAreaList(vo,pageable);
    }
    

    //저장
    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public AreaManage save(@RequestBody AreaManage area) throws Exception {
    	UserLogUtil.saveUserLog("AreaManageController","지역사관리","PUT");    	
    	return areaManageService.saveArea(area);
    }

    //조회 마이바티스
    @RequestMapping(value = "getAreaOrgList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public List<AreaOrgManageVO> getAreaOrgList(RequestParams<AreaOrgManageVO> vo) {
    	return areaOrgManageService.getAreaOrgList(vo);
    }
    
    //상담
    @RequestMapping(value = "getAreaOrgAllList10", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public List<AreaOrgManageListVO> getAreaOrgAllList10(RequestParams<AreaOrgManageListVO> vo) {
    	return areaOrgManageService.getAreaOrgAllList10(vo);
    }
    //방문
    @RequestMapping(value = "getAreaOrgAllList20", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public List<AreaOrgManageListVO> getAreaOrgAllList20(RequestParams<AreaOrgManageListVO> vo) {
    	return areaOrgManageService.getAreaOrgAllList20(vo);
    }
    //본사
    @RequestMapping(value = "getAreaOrgAllList30", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public List<AreaOrgManageListVO> getAreaOrgAllList30(RequestParams<AreaOrgManageListVO> vo) {
    	return areaOrgManageService.getAreaOrgAllList30(vo);
    }
    
    
    //지사선택->총국 가져오기
    @RequestMapping(value = "getHeadQList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getHeadQList(RequestParams<AreaOrgManage> vo) {
    	List<AreaOrgManage> list = areaOrgManageService.getHeadQList(vo);
        return Responses.ListResponse.of(list); 
    }
        

    //저장
    @RequestMapping(value ="org", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse saveOrg(@RequestBody AreaOrgManage area) throws Exception {
    	UserLogUtil.saveUserLog("AreaManageController","조직-등록","PUT");    	
    	areaOrgManageService.saveOrg(area);
    	return ok();
    }
    
    //삭제
    @RequestMapping(value ="org", method = {RequestMethod.DELETE}, produces = APPLICATION_JSON)
    public ApiResponse deleteOrg(@RequestBody AreaOrgManage area) throws Exception {
    	UserLogUtil.saveUserLog("AreaManageController","조직-삭제","DELETE");    	
    	areaOrgManageService.deleteOrg(area);
    	return ok();
    }
}