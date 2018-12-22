package com.prb.erp.domain.code.master;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.controllers.BasicCodeController;
import com.prb.erp.domain.BaseService;
import com.querydsl.core.BooleanBuilder;
 
@Service
public class BasicCodeMasterService extends BaseService <BasicCodeMaster,BasicCodeMaster.BasicCodeMasterId>{

    private BasicCodeMasterRepository basicCodeMasterRepository;
	private static final Logger logger = LoggerFactory.getLogger(BasicCodeController.class);
    
    @Inject
    public BasicCodeMasterService(BasicCodeMasterRepository basicCodeMasterRepository) {
        super(basicCodeMasterRepository);
        this.basicCodeMasterRepository = basicCodeMasterRepository;
    }
 
    public List<BasicCodeMaster> getList(RequestParams requestParams) {
    	
        String mainCode = requestParams.getString("mainCode", "");
        String useYn = requestParams.getString("useYn", "");
        
        
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(mainCode)) {
            builder.and(qBasicCodeMaster.mainCode.like("%" + mainCode +"%").or(qBasicCodeMaster.mainName.like("%" + mainCode +"%")));
        }

        if (isNotEmpty(useYn)) {
            builder.and(qBasicCodeMaster.useYn.eq(useYn));
        }

        return findAll(builder);
    }
  
  /*  
	@Transactional
	public void deleteByKeys(List<String> keys) {
		keys.forEach(this::delete);
	}*/
}