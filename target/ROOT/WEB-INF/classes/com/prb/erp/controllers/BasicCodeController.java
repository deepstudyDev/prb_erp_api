package com.prb.erp.controllers;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.chequer.axboot.core.utils.ModelMapperUtils;
import com.prb.erp.domain.code.detail.BasicCodeDetail;
import com.prb.erp.domain.code.detail.BasicCodeDetailService;
import com.prb.erp.domain.code.detail.BasicCodeDetailVO;
import com.prb.erp.domain.code.master.BasicCodeMaster;
import com.prb.erp.domain.code.master.BasicCodeMasterService;
import com.prb.erp.domain.code.master.BasicCodeMasterVO;
import com.prb.erp.utils.BasicCodeUtils;
import com.prb.erp.utils.UserLogUtil;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
/*
 * 기초데이터 관리
 */
@Controller
@RequestMapping(value = "/api/v1/basicCode") 
public class BasicCodeController extends BaseController {
	@Inject
    private BasicCodeMasterService masterService;
	@Inject    
    private BasicCodeDetailService detailService;   
	 
	@RequestMapping(value = "/master", method = RequestMethod.GET, produces = APPLICATION_JSON)	
    public Responses.ListResponse getMasterList(RequestParams<BasicCodeMaster> requestParams) {
		List<BasicCodeMaster> list = masterService.getList(requestParams);
        return Responses.ListResponse.of(list); 
    }
	

    @RequestMapping(value = "/master", method = {RequestMethod.POST, RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse saveMaster(@RequestBody List<BasicCodeMasterVO> list) {
        List<BasicCodeMaster> masterList = ModelMapperUtils.mapList(list, BasicCodeMaster.class);
        masterService.save(masterList);
        return ok();
    }
    
    
    @RequestMapping(value = "/detail", method = RequestMethod.GET, produces = APPLICATION_JSON)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "mainCode", value = "분류 코드", dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "useYn", value = "사용여부 (Y/N)", dataType = "String", paramType = "query")
	})
    public Responses.ListResponse getDetailListsY(RequestParams<BasicCodeDetail> requestParams) {
    	UserLogUtil.saveUserLog("BasicCodeController","공통코드관리","GET");
		List<BasicCodeDetail> codes = detailService.get(requestParams);
        return Responses.ListResponse.of(codes); 
    }
    
    @RequestMapping(value = "/detail", method = {RequestMethod.POST, RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse childSave(@RequestBody List<BasicCodeDetailVO> list) {
    	UserLogUtil.saveUserLog("BasicCodeController","공통코드관리","SAVE");
        List<BasicCodeDetail> detailList = ModelMapperUtils.mapList(list, BasicCodeDetail.class);
        detailService.saveCodeDetail(detailList);
        return ok(); 
    }    
    

    @RequestMapping(value = "/getAllByCodeMap", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Map<String, List<BasicCodeDetailVO>> getAllByCodeMap() {
        return BasicCodeUtils.getAllByCodeMap();
    }
}
