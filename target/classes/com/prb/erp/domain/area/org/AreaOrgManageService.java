package com.prb.erp.domain.area.org;

import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Transactional;

import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.querydsl.core.BooleanBuilder;


@Service
public class AreaOrgManageService extends BaseService<AreaOrgManage, AreaOrgManage.AreaOrgManageId> {
    private AreaOrgManageRepository repository;
    

    @Inject private KeyManagementService keyManagementService;
    @Inject private AreaOrgManageMapper areaOrgManageMapper;
    @Inject
    public AreaOrgManageService(AreaOrgManageRepository repository) { 
        super(repository);
        this.repository = repository;
    }    
    
    @Transactional
    public void saveOrg(AreaOrgManage m) {
        if(isEmpty(m.getOrgCd())){
        	if(m.getOrgDepth().equals("DEPTH1")){
    			m.setOrgCd(keyManagementService.getOrgCd(m.getOrgDepth(),m.getOrgClass(),m.getParentOrgCd(), 2));        		
        	}else if(m.getOrgDepth().equals("DEPTH2")){
    			m.setOrgCd(keyManagementService.getOrgCd(m.getOrgDepth(),m.getOrgClass(),m.getParentOrgCd(), 4));        		
        	}else{
    			m.setOrgCd(keyManagementService.getOrgCd(m.getOrgDepth(),m.getOrgClass(),m.getParentOrgCd(), 2));        		
        	}
        }
        save(m);
    }

    @Transactional
    public void deleteOrg(AreaOrgManage m) {
        delete(m);
    }
    public List<AreaOrgManage> getHeadQList(RequestParams<AreaOrgManage> requestParams) {
    	String areaCd = requestParams.getString("areaCd", "");
    	String orgClass = requestParams.getString("orgClass", "");
    	String orgDepth = requestParams.getString("orgDepth", "");
    	String orgCd = requestParams.getString("orgCd", "");
    	String parentOrgCd = requestParams.getString("parentOrgCd", "");
    	
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(areaCd)) {
            builder.and(qAreaOrgManage.areaCd.eq(areaCd));
        }     

        if (isNotEmpty(orgCd)) {
            builder.and(qAreaOrgManage.orgCd.eq(orgCd));
        }     
        
        if (isNotEmpty(orgClass)) {
            builder.and(qAreaOrgManage.orgClass.eq(orgClass));
        }     
        
        if (isNotEmpty(orgDepth)) {
            builder.and(qAreaOrgManage.orgDepth.eq(orgDepth));
        }     
        
        if (isNotEmpty(parentOrgCd)) {
            builder.and(qAreaOrgManage.parentOrgCd.eq(parentOrgCd));
        }     
        
        return findAll(builder);   
    }

    public List<AreaOrgManageVO> getAreaOrgList(RequestParams<AreaOrgManageVO> requestParams) {
    	return areaOrgManageMapper.getAreaOrgList(requestParams);
    }
    
    //상담조직
    public List<AreaOrgManageListVO> getAreaOrgAllList10(RequestParams<AreaOrgManageListVO> requestParams) {
    	return areaOrgManageMapper.getAreaOrgAllList10(requestParams);
    }
    
    
    //방문조직
    public List<AreaOrgManageListVO> getAreaOrgAllList20(RequestParams<AreaOrgManageListVO> requestParams) {
    	return areaOrgManageMapper.getAreaOrgAllList20(requestParams);
    }
    
    //본사조직
    public List<AreaOrgManageListVO> getAreaOrgAllList30(RequestParams<AreaOrgManageListVO> requestParams) {
    	return areaOrgManageMapper.getAreaOrgAllList30(requestParams);
    }

    
    /*
    @Transactional
    public void saveCategory(AreaOrgManage m) {
        if(isEmpty(m.getCategoryCd())){
        	m.setCategoryCd(systemKeyManagementService.setCommonCode("CATEGORY","CAT",5));
        }
        save(m);
    }
    

    public List<GoodsCategoryVO> getCategoryProduct(RequestParams<GoodsCategoryVO> vo) {
    	return goodsCategoryMapper.getCategoryProduct(vo);
    }
    
    @Transactional
    public void createCategory(GoodsManage goods){
    	

        delete(qGoodsCategory).where(qGoodsCategory.goodsCd.eq(goods.getGoodsCd()).and(qGoodsCategory.categoryType.eq("20"))).execute(); 
        
		AreaOrgManage category = new AreaOrgManage();
		category.setGoodsCd(goods.getGoodsCd());
		
		RequestParams requestParams = new RequestParams();
		requestParams.put("goodsCd",goods.getGoodsCd());
    	requestParams.put("goodsCategoryType", "ETC");
		
    	List<GoodsTypeManage> goodsTypes = goodsTypeManageService.get(requestParams);
    	
    	int sort = 1; 

        for (GoodsTypeManage goodsType : goodsTypes) {
    		category.setCategoryCd(systemKeyManagementService.setCommonCode("CATEGORY","CAT",5));
    		category.setCategoryType("20");	//부가서비스
        	if(goodsType.getGoodsCategoryCd().equals("60")){
        		category.setCategoryNm("교구");    		
        	}
        	if(goodsType.getGoodsCategoryCd().equals("70")){
        		category.setCategoryNm("테블릿");    		
        	}
        	if(goodsType.getGoodsCategoryCd().equals("80")){
        		category.setCategoryNm("DVD");    		
        	}
        	if(goodsType.getGoodsCategoryCd().equals("90")){
        		category.setCategoryNm("CD");    		
        	}
        	if(goodsType.getGoodsCategoryCd().equals("100")){
        		category.setCategoryNm("사은품");    		
        	}
        	if(goodsType.getGoodsCategoryCd().equals("110")){
        		category.setCategoryNm("판촉물");    		
        	}
        	if(goodsType.getGoodsCategoryCd().equals("120")){
        		category.setCategoryNm("AS부품");    		
        	}
        	if(goodsType.getGoodsCategoryCd().equals("130")){
        		category.setCategoryNm("기타");    		
        	}
    		category.setSort(sort);
    		save(category);
    		sort ++;
        }
    }*/
}
