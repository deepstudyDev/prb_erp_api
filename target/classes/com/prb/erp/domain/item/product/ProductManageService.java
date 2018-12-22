package com.prb.erp.domain.item.product;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.prb.erp.utils.FroebelIFUtils;
 
@Service
public class ProductManageService extends BaseService<ProductManage, ProductManage.ProductManageId> {
    private ProductManageRepository repository;
    

    @Inject private KeyManagementService keyManagementService;  
    @Inject private ProductManageMapper productManageMapper;
    
    @Inject
    public ProductManageService(ProductManageRepository repository) {
        super(repository);
        this.repository = repository;
    } 

    //조회 - 전체
    public List<ProductManageVO> getAll(RequestParams<ProductManageVO> vo) {
    	return productManageMapper.getProductManageListAll(vo);
    }
    
    //조회 - 단건
    public ProductManageVO getProductManage(RequestParams<ProductManageVO> vo) {
    	return productManageMapper.getProductManage(vo);
    }
    
    //조회 - 페이징
    public ProductManagePagingVO getProductManageList(RequestParams<ProductManageVO> vo,Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber == 0 ? 1 : pageNumber);
    	
    	ProductManagePagingVO result = new ProductManagePagingVO();
    	result.setResult(productManageMapper.getProductManageList(vo));   
    	
    	//현재페이지    	
    	result.setPageNo(pageNumber);    
    	  
    	int totalCnt = productManageMapper.getProductManageListCount(vo);    	
    	result.setTotalCnt(totalCnt);
    	
    	return result;
    }
    
    /* 저장/수정 */
    @Transactional
    public ProductManage saveProduct(ProductManage product) throws Exception {

    	String dmlFlag = "U";
    	
		if(null==product.getProductCd()){
			//제품코드생성
			product.setProductCd(keyManagementService.getItemCode("PRODUCT","P",5));
			
			dmlFlag = "I";
			
			//신규일경우, temp file 존재시 키값 변경
	        update(qCommonFile).set(qCommonFile.targetId , product.getProductCd())
	        	.where(qCommonFile.targetId.eq(product.getTempFileCd())
	        			.and(qCommonFile.targetType.eq("PRODUCT"))
	        			.and(qCommonFile.tempYn.eq("Y"))).execute();		 
		}
		
		
		save(product);		
		FroebelIFUtils.insertProductManage(product,dmlFlag);
		
		return product;
    }
}


