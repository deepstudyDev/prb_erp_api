package com.prb.erp.domain.item.goods.category;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.item.goods.GoodsManage;
import com.prb.erp.domain.item.goods.category.product.CategoryProduct;
import com.prb.erp.domain.item.goods.type.GoodsTypeManage;
import com.prb.erp.domain.item.goods.type.GoodsTypeManageService;
import com.prb.erp.domain.key.system.SystemKeyManagementService;
import com.querydsl.core.BooleanBuilder;


@Service
public class GoodsCategoryService extends BaseService<GoodsCategory, GoodsCategory.GoodsCategoryId> {
    private GoodsCategoryRepository repository;
    
 
    @Inject private SystemKeyManagementService systemKeyManagementService;  
    @Inject private GoodsCategoryMapper goodsCategoryMapper;
    @Inject private GoodsTypeManageService goodsTypeManageService;
    
    
    @Inject
    public GoodsCategoryService(GoodsCategoryRepository repository) { 
        super(repository);
        this.repository = repository;
    }    

    
    
    public List<GoodsCategory> get(RequestParams requestParams) {
    	String goodsCd = requestParams.getString("goodsCd", "");
    	String categoryType = requestParams.getString("categoryType", "");
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(goodsCd)) {
            builder.and(qGoodsCategory.goodsCd.eq(goodsCd));
        }
        
        if (isNotEmpty(categoryType)) {
            builder.and(qGoodsCategory.categoryType.eq(categoryType));
        }
        
        return findAll(builder);        
    }


    @Transactional
    public void saveCategory(GoodsCategory m) {
        if(isEmpty(m.getCategoryCd())){
        	m.setCategoryCd(systemKeyManagementService.setCommonCode("CATEGORY","CAT",5));
        }
        save(m);
    }
    

    public List<GoodsCategoryVO> getCategoryProduct(RequestParams<GoodsCategoryVO> vo) {
    	return goodsCategoryMapper.getCategoryProduct(vo);
    }
    
    @Transactional
    public void createEtcCategory(GoodsManage goods){
    	
    	if(goods.getEtcServiceYn().equals("N")){    		
    		//미포함이면 전체삭제
    		delete(qCategoryProduct).where(qCategoryProduct.categoryCd.in(select().select(qGoodsCategory.categoryCd).distinct().from(qGoodsCategory)
            		.where(qGoodsCategory.goodsCd.eq(goods.getGoodsCd())
            				.and(qGoodsCategory.categoryType.eq("20"))))).execute();
    		
            delete(qGoodsCategory).where(qGoodsCategory.goodsCd.eq(goods.getGoodsCd()).and(qGoodsCategory.categoryType.eq("20"))).execute(); 
        	
    	}else{
    		delete(qCategoryProduct).where(qCategoryProduct.categoryCd.in(select().select(qGoodsCategory.categoryCd).distinct().from(qGoodsCategory)
    	    		.where(qGoodsCategory.goodsCategoryCd.notIn(select().select(qGoodsTypeManage.goodsCategoryCd).distinct().from(qGoodsTypeManage)
    	            		.where(qGoodsTypeManage.goodsCd.eq(goods.getGoodsCd())
    	            				.and(qGoodsTypeManage.goodsCategoryType.eq("ETC"))))))).execute();
    		
    		
            delete(qGoodsCategory).where(qGoodsCategory.goodsCategoryCd.notIn(select().select(qGoodsTypeManage.goodsCategoryCd).distinct().from(qGoodsTypeManage)
            		.where(qGoodsTypeManage.goodsCd.eq(goods.getGoodsCd())
            				.and(qGoodsTypeManage.goodsCategoryType.eq("ETC"))))).execute(); 
    		
    		GoodsCategory category = new GoodsCategory();
    		category.setGoodsCd(goods.getGoodsCd());
    		
    		RequestParams requestParams = new RequestParams();
    		requestParams.put("goodsCd",goods.getGoodsCd());
        	requestParams.put("goodsCategoryType", "ETC");
    		
        	List<GoodsTypeManage> goodsTypes = goodsTypeManageService.get(requestParams);
        	
        	int sort = 1; 
            for (GoodsTypeManage goodsType : goodsTypes) {
            	
            	GoodsCategory c = select().from(qGoodsCategory)
        		.where(qGoodsCategory.goodsCd.eq(goodsType.getGoodsCd())
        				.and(qGoodsCategory.goodsCategoryCd.eq(goodsType.getGoodsCategoryCd()))).fetchOne();
            	
            	if(null == c){
            		category.setCategoryCd(systemKeyManagementService.setCommonCode("CATEGORY","CAT",5));
            		category.setCategoryType("20");	//부가서비스

            		category.setGoodsCategoryCd(goodsType.getGoodsCategoryCd());
            		
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
            }
    	}
        
		
    }
    

    @Transactional
    public void deleteCategory(GoodsCategory category){    	
    	delete(category);
    	delete(qCategoryProduct).where(qCategoryProduct.categoryCd.eq(category.getCategoryCd())).execute();
    }
}
