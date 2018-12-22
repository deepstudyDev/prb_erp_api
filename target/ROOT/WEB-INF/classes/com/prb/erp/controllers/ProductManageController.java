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
import com.prb.erp.domain.item.product.ProductManage;
import com.prb.erp.domain.item.product.ProductManagePagingVO;
import com.prb.erp.domain.item.product.ProductManageService;
import com.prb.erp.domain.item.product.ProductManageVO;
import com.prb.erp.utils.UserLogUtil;
 
 /*
  * 제품관리
  */
@Controller
@RequestMapping(value = "/api/v1/productManage")
public class ProductManageController extends BaseController {
  
    @Inject
    private ProductManageService productManageService;
 

    
    //조회 전체
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getAll(RequestParams<ProductManageVO> vo) {
    	UserLogUtil.saveUserLog("ProductManageController","제품관리","GET");
    	List<ProductManageVO> list = productManageService.getAll(vo);
        return Responses.ListResponse.of(list); 
    }

    //조회 단건
    @RequestMapping(value = "getProductManage",method = RequestMethod.GET, produces = APPLICATION_JSON)
    public ProductManageVO getProductManage(RequestParams<ProductManageVO> vo) {
    	UserLogUtil.saveUserLog("ProductManageController","제품관리","GET");
    	return productManageService.getProductManage(vo);
    }

    //조회 페이징
    @RequestMapping(value = "getProductManageList",method = RequestMethod.GET, produces = APPLICATION_JSON)
    public ProductManagePagingVO getProductManageList(RequestParams<ProductManageVO> vo,Pageable pageable) {
    	//UserLogUtil.saveUserLog("ProductManageController","제품관리","GET");
    	return productManageService.getProductManageList(vo,pageable);
    }
    
    //저장
    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ProductManage save(@RequestBody ProductManage item)  throws Exception {
    	UserLogUtil.saveUserLog("ProductManageController","제품관리","PUT");
    	return productManageService.saveProduct(item);
        
    }
    
    //삭제
    @RequestMapping(method = {RequestMethod.DELETE}, produces = APPLICATION_JSON)
    public ApiResponse delete(@RequestBody ProductManage item) {
    	UserLogUtil.saveUserLog("ProductManageController","제품관리","DELETE");
    	productManageService.delete(item);
        return ok();
    }
}