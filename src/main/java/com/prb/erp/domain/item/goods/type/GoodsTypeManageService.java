package com.prb.erp.domain.item.goods.type;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.querydsl.core.BooleanBuilder;
 
@Service
public class GoodsTypeManageService extends BaseService<GoodsTypeManage, GoodsTypeManage.GoodsTypeManageId> {
    private GoodsTypeManageRepository repository;
    
    
    @Inject
    public GoodsTypeManageService(GoodsTypeManageRepository repository) {
        super(repository);
        this.repository = repository;
    } 
    

    //조회
    public List<GoodsTypeManage> get(RequestParams requestParams) {
    	String goodsCd = requestParams.getString("goodsCd", "");
    	String goodsCategoryType = requestParams.getString("goodsCategoryType", "");
        BooleanBuilder builder = new BooleanBuilder();
        
        if (isNotEmpty(goodsCd)) {
            builder.and(qGoodsTypeManage.goodsCd.eq(goodsCd));
        }             
        if (isNotEmpty(goodsCategoryType)) {
            builder.and(qGoodsTypeManage.goodsCategoryType.eq(goodsCategoryType));
        }     
        
        return findAll(builder);
    }
}


