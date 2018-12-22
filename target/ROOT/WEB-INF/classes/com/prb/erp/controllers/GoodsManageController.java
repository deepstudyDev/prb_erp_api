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
import com.prb.erp.domain.item.goods.GoodsManage;
import com.prb.erp.domain.item.goods.GoodsManagePagingVO;
import com.prb.erp.domain.item.goods.GoodsManageService;
import com.prb.erp.domain.item.goods.GoodsManageVO;
import com.prb.erp.domain.item.goods.category.GoodsCategory;
import com.prb.erp.domain.item.goods.category.GoodsCategoryService;
import com.prb.erp.domain.item.goods.category.GoodsCategoryVO;
import com.prb.erp.domain.item.goods.category.product.CategoryProduct;
import com.prb.erp.domain.item.goods.category.product.CategoryProductService;
import com.prb.erp.utils.UserLogUtil;

 /*
  * 상품관리
  */
@Controller
@RequestMapping(value = "/api/v1/goodsManage")
public class GoodsManageController extends BaseController {
  
    @Inject private GoodsManageService goodsManageService;
    @Inject private CategoryProductService categoryProductService;
    @Inject private GoodsCategoryService goodsCategoryService;

    
    //조회 전체
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse getAll(RequestParams<GoodsManageVO> vo) {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리","GET");
    	List<GoodsManageVO> list = goodsManageService.getAll(vo);
        return Responses.ListResponse.of(list); 
    } 

    //조회 페이징
    @RequestMapping(value = "getGoodsManageList",method = RequestMethod.GET, produces = APPLICATION_JSON)
    public GoodsManagePagingVO getGoodsManageList(RequestParams<GoodsManageVO> vo,Pageable pageable) {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리","GET");
    	return goodsManageService.getGoodsManageList(vo,pageable);
    }
    
    //조회 단건
    @RequestMapping(value="getOne" , method = RequestMethod.GET, produces = APPLICATION_JSON)
    public GoodsManage getOne(RequestParams requestParams) {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리","GET");
    	return goodsManageService.get(requestParams); 
    }

    
    //완료
    @RequestMapping(value="success", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public GoodsManage success(@RequestBody GoodsManage item) throws Exception {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리","PUT");
    	return goodsManageService.saveSuccess(item);
    }
    
    //저장
    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public GoodsManage save(@RequestBody GoodsManage item) throws Exception {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리","PUT");
    	return goodsManageService.saveGoods(item);
    }
    
    //삭제
    @RequestMapping(method = {RequestMethod.DELETE}, produces = APPLICATION_JSON)
    public ApiResponse delete(@RequestBody GoodsManage item) {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리","DELETE");
    	goodsManageService.delete(item);
        return ok();
    }

    @RequestMapping(value="category", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse category(RequestParams<GoodsCategoryVO> vo) {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리-카테고리","GET");
        List<GoodsCategoryVO> list = goodsCategoryService.getCategoryProduct(vo);
        return Responses.ListResponse.of(list);
    } 
    
    @RequestMapping(value="category", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody GoodsCategory vo) {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리-카테고리","PUT");
    	goodsCategoryService.saveCategory(vo);
        return ok();
    }
    
    @RequestMapping(value="category", method = {RequestMethod.DELETE}, produces = APPLICATION_JSON)
    public ApiResponse delete(@RequestBody GoodsCategory vo) {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리-카테고리","DELETE");
    	goodsCategoryService.deleteCategory(vo);
        return ok();
    }

    /*
    @RequestMapping(value = "createCategory", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse createCategory(@RequestBody GoodsManage item) throws Exception {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리-카테고리(부가정보)","PUT");
    	goodsCategoryService.createCategory(item);
        return ok();
    }*/
    
    @RequestMapping(value ="product", method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<CategoryProduct> list) throws Exception {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리-카테고리(제품)","PUT");
    	categoryProductService.save(list);
        return ok();
    }
    

    @RequestMapping(value ="product", method = {RequestMethod.DELETE}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody CategoryProduct vo) throws Exception {
    	UserLogUtil.saveUserLog("GoodsManageController","상품관리-카테고리(제품)","DELETE");
    	categoryProductService.delete(vo);
        return ok();
    }
}