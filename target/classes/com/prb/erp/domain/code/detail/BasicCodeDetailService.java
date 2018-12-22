package com.prb.erp.domain.code.detail;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.querydsl.core.BooleanBuilder;
 
@Service
public class BasicCodeDetailService extends BaseService <BasicCodeDetail,BasicCodeDetail.BasicCodeDetailId>{
	
    private BasicCodeDetailRepository basicCodeDetailRepository;

    @Inject private BasicCodeDetailMapper basicCodeDetailMapper;
    
    @Inject
    private KeyManagementService keyManagementService;  
    
    @Inject
    public BasicCodeDetailService(BasicCodeDetailRepository basicCodeDetailRepository) {
        super(basicCodeDetailRepository);
        this.basicCodeDetailRepository = basicCodeDetailRepository;
    }
    

    public List<BasicCodeDetailVO> getAllByCodeMap(RequestParams<BasicCodeDetailVO> basicCodeDetailVO){
    	return basicCodeDetailMapper.getAllByCodeMap(basicCodeDetailVO);
    }
    
    public List<BasicCodeDetail> get(RequestParams requestParams) {
    	
        String mainCode = requestParams.getString("mainCode", "");
        String includeValue = requestParams.getString("includeValue", "");
        String exceptValue = requestParams.getString("exceptValue", "");
        
        String data1 = requestParams.getString("data1", "");
        
        String useYn = requestParams.getString("useYn", ""); 
        String empty = requestParams.getString("empty", ""); 

        BooleanBuilder builder = new BooleanBuilder();
  

        if (isNotEmpty(mainCode)) { 
            builder.and(qBasicCodeDetail.mainCode.eq(mainCode));
        }
        if (isNotEmpty(includeValue)) { 
            builder.and(qBasicCodeDetail.subCode.eq(includeValue));
        }
        if (isNotEmpty(exceptValue)) { 
            builder.and(qBasicCodeDetail.subCode.ne(exceptValue));
        }
        
        if (isNotEmpty(data1)) { 
            if (isNotEmpty(empty) && empty.equals("Y")) { 
                builder.and(qBasicCodeDetail.data1.eq(data1).or(qBasicCodeDetail.data1.eq("")));
            }else{
                builder.and(qBasicCodeDetail.data1.eq(data1));
            }
        }
        
        
        if (isNotEmpty(useYn)) {  
            builder.and(qBasicCodeDetail.useYn.eq(useYn));
        }

        builder.and(qBasicCodeDetail.useYn.eq("Y"));
        List<BasicCodeDetail> commonCodeList = select().from(qBasicCodeDetail).where(builder).orderBy(qBasicCodeDetail.mainCode.asc(), qBasicCodeDetail.sort.asc()).fetch();

     
        return commonCodeList;

    }
    
    
    public Page<BasicCodeDetail> findByParentKeyWithPaging(String mainCode, Pageable pageable) {
        return basicCodeDetailRepository.findByMainCode(mainCode, pageable);
    } 
    
    //저장
    @Transactional
    public void saveCodeDetail(List<BasicCodeDetail> itemInfos) {
    	
    	if (isNotEmpty(itemInfos)) {  
    		for (BasicCodeDetail itemInfo : itemInfos) {
         		save(itemInfo);  
    		}
    	}
    }
}