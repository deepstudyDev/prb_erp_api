package com.prb.erp.utils;

import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

import com.chequer.axboot.core.code.AXBootTypes;
import com.chequer.axboot.core.context.AppContextManager;
import com.chequer.axboot.core.parameter.RequestParams;
import com.chequer.axboot.core.utils.JsonUtils;
import com.prb.erp.domain.area.AreaManageService;
import com.prb.erp.domain.area.AreaManageVO;
import com.prb.erp.domain.code.detail.BasicCodeDetail;
import com.prb.erp.domain.code.detail.BasicCodeDetailService;
import com.prb.erp.domain.code.detail.BasicCodeDetailVO;
import com.prb.erp.domain.item.goods.GoodsManageService;
import com.prb.erp.domain.item.goods.GoodsManageVO;

public class BasicCodeUtils {

	public static List<BasicCodeDetail> get(String mainCode) {
        RequestParams<BasicCodeDetail> requestParams = new RequestParams<>(BasicCodeDetail.class);
        requestParams.put("mainCode", mainCode);
        requestParams.put("useYn", AXBootTypes.Used.YES.getLabel()); 
        return getService().get(requestParams);
    } 
	 
	public static List<BasicCodeDetail> get(String mainCode,String subCode) {
        RequestParams<BasicCodeDetail> requestParams = new RequestParams<>(BasicCodeDetail.class);
        requestParams.put("mainCode", mainCode);
        requestParams.put("subCode", subCode);
        requestParams.put("useYn", AXBootTypes.Used.YES.getLabel()); 
        return getService().get(requestParams);
    } 

	public static List<BasicCodeDetail> get(String mainCode,String includeValue,String exceptValue) {
        RequestParams<BasicCodeDetail> requestParams = new RequestParams<>(BasicCodeDetail.class);
        requestParams.put("mainCode", mainCode);
        requestParams.put("includeValue", includeValue);
        requestParams.put("exceptValue", exceptValue);
        requestParams.put("useYn", AXBootTypes.Used.YES.getLabel()); 
        return getService().get(requestParams);
    } 
	
	public static List<BasicCodeDetail> get(String company,String mainCode,String includeValue,String exceptValue,String data1) {
        RequestParams<BasicCodeDetail> requestParams = new RequestParams<>(BasicCodeDetail.class);
        requestParams.put("mainCode", mainCode);
        requestParams.put("includeValue", includeValue);
        requestParams.put("exceptValue", exceptValue);        
        requestParams.put("data1", data1);
        requestParams.put("empty", "Y");        
        requestParams.put("useYn", AXBootTypes.Used.YES.getLabel());        

        return getService().get(requestParams);
    } 


	public static List<GoodsManageVO> getGoodsCd() {
        RequestParams<GoodsManageVO> requestParams = new RequestParams<>(GoodsManageVO.class);
        requestParams.put("salesYn", "Y");
        return getGoodsManageService().getAll(requestParams);
    } 


	//지사=지역사
	public static List<AreaManageVO> getAreaCd(String areaCd) {
        RequestParams<AreaManageVO> requestParams = new RequestParams<>(AreaManageVO.class);
        requestParams.put("areaCd" , areaCd);
        
        return getAreaManageService().getAreaListAll(requestParams);
    } 
	
	public static Map<String, List<BasicCodeDetailVO>> getAllByCodeMap() {

        RequestParams<BasicCodeDetailVO> requestParams = new RequestParams<>(BasicCodeDetailVO.class);
        List<BasicCodeDetailVO> commonCodes = getService().getAllByCodeMap(requestParams);

        Map<String, List<BasicCodeDetailVO>> commonCodeMap = commonCodes.stream().collect(groupingBy(BasicCodeDetailVO::getMainCode));        		
        return commonCodeMap;
	}
	
    public static String getAllByCodeMapJson() {
        return JsonUtils.toJson(getAllByCodeMap());
    }    
    
    public static BasicCodeDetailService getService() {
        return AppContextManager.getBean(BasicCodeDetailService.class);
    }
    
    public static GoodsManageService getGoodsManageService() {
        return AppContextManager.getBean(GoodsManageService.class);
    }

    public static AreaManageService getAreaManageService() {
        return AppContextManager.getBean(AreaManageService.class);
    }
}
