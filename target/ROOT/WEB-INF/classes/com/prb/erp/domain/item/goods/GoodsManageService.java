package com.prb.erp.domain.item.goods;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.item.goods.category.GoodsCategoryService;
import com.prb.erp.domain.item.goods.type.GoodsTypeManage;
import com.prb.erp.domain.item.goods.type.GoodsTypeManageService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.prb.erp.utils.FroebelIFUtils;
import com.querydsl.core.BooleanBuilder;
 
@Service
public class GoodsManageService extends BaseService<GoodsManage, GoodsManage.GoodsManageId> {
    private GoodsManageRepository repository;
    
    @Inject private KeyManagementService keyManagementService;  
    @Inject private GoodsManageMapper goodsManageMapper;
    @Inject private GoodsTypeManageService goodsTypeManageService;
    @Inject private GoodsCategoryService goodsCategoryService;
    
    @Inject
    public GoodsManageService(GoodsManageRepository repository) {
        super(repository);
        this.repository = repository; 
    }

    //조회
    public GoodsManage get(RequestParams requestParams) {
    	String goodsCd = requestParams.getString("goodsCd", "");
    	
        BooleanBuilder builder = new BooleanBuilder();
        if (isNotEmpty(goodsCd)) {
            builder.and(qGoodsManage.goodsCd.eq(goodsCd));
        }     
        GoodsManage goods = findOne(builder);           
        
        if (goods != null) {
        	requestParams.put("goodsCategoryType", "BOOK");
        	goods.setBooksTypeList(goodsTypeManageService.get(requestParams));


        	requestParams.put("goodsCategoryType", "ONLINE");
        	goods.setOnlineTypeList(goodsTypeManageService.get(requestParams));
        	

        	requestParams.put("goodsCategoryType", "ETC");
        	goods.setEtcTypeList(goodsTypeManageService.get(requestParams));
        }
        
        return goods;
    }
      
    
    //조회
    public List<GoodsManageVO> getAll(RequestParams<GoodsManageVO> vo) {
    	return goodsManageMapper.getGoodsManageListAll(vo);
    }
      

    //조회 - 페이징 
    public GoodsManagePagingVO getGoodsManageList(RequestParams<GoodsManageVO> vo,Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	GoodsManagePagingVO result = new GoodsManagePagingVO();
    	result.setResult(goodsManageMapper.getGoodsManageList(vo));   
    	
    	//현재페이지    	
    	result.setPageNo(pageNumber);     
    	  
    	int totalCnt = goodsManageMapper.getGoodsManageListCount(vo);    	
    	result.setTotalCnt(totalCnt);
    	
    	return result;
    }
    

    @Transactional
    public GoodsManage saveGoods(GoodsManage goods) throws Exception {
    	String dmlFlag = "U";
		if(null==goods.getGoodsCd()){
			//상품코드생성
			goods.setGoodsCd(keyManagementService.getItemCode("GOODS","G",5));
			dmlFlag = "I";
		}
		
		save(goods);
		

        delete(qGoodsTypeManage).where(qGoodsTypeManage.goodsCd.eq(goods.getGoodsCd())).execute();

        for (GoodsTypeManage type : goods.getBooksTypeList()) {
        	type.setGoodsCd(goods.getGoodsCd());
        }

        for (GoodsTypeManage type : goods.getOnlineTypeList()) {
        	type.setGoodsCd(goods.getGoodsCd());
        }
        
        for (GoodsTypeManage type : goods.getEtcTypeList()) {
        	type.setGoodsCd(goods.getGoodsCd());
        }
        goodsTypeManageService.save(goods.getBooksTypeList());
        goodsTypeManageService.save(goods.getOnlineTypeList());
        goodsTypeManageService.save(goods.getEtcTypeList());
    	goodsCategoryService.createEtcCategory(goods);
    	

		FroebelIFUtils.insertGoodsManage(goods, dmlFlag);
		return goods;
    }
    
    @Transactional
    public GoodsManage saveSuccess(GoodsManage goods) throws Exception {
    	String dmlFlag = "U";
    	
		save(goods);		
		FroebelIFUtils.insertGoodsManage(goods, dmlFlag);
		
		return goods;
    }
    
    
}


