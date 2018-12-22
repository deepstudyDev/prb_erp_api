package com.prb.erp.domain.key.system;


import java.util.List;

import javax.inject.Inject;
import javax.jdo.annotations.Transactional;

import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.querydsl.core.BooleanBuilder;

@Service
public class SystemKeyManagementService extends BaseService<SystemKeyManagement, SystemKeyManagement.SystemKeyManagementId> {
    private SystemKeyManagementRepository CompanyKeyManagementRepository;

    @Inject
    public SystemKeyManagementService(SystemKeyManagementRepository CompanyKeyManagementRepository) {
        super(CompanyKeyManagementRepository);
        this.CompanyKeyManagementRepository = CompanyKeyManagementRepository;
    } 
  
    

    @Transactional
    public String setCommonCode(String typeName,String keyword,int digits){
		BooleanBuilder builder = new BooleanBuilder();
     	builder.and(qSystemKeyManagement.typeName.eq(typeName));  	 
     	
		Long nowSeq = select().select(qSystemKeyManagement.seq).distinct().from(qSystemKeyManagement).where(builder).fetchOne();    				
		Long setWorkSeq = new Long(1);
		 
		if(null!=nowSeq){
			setWorkSeq = nowSeq + 1;
			update(qSystemKeyManagement).set(qSystemKeyManagement.seq, setWorkSeq).where(builder).execute();
          
		}else{
			
			SystemKeyManagement key = new SystemKeyManagement();    	
			key.setTypeName(typeName);
			key.setSeq(setWorkSeq); 
			save(key);
		}
		
		return keyword+String.format("%0"+digits+"d", setWorkSeq);
	}
    
    
    public List<SystemKeyManagement> gets(RequestParams<SystemKeyManagement> requestParams) {
        return findAll();
    }
}