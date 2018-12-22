package com.prb.erp.domain.key.management;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.querydsl.core.BooleanBuilder;

@Service
public class KeyManagementService extends BaseService<KeyManagement, KeyManagement.CompanyKeyManagementId> {
    private KeyManagementRepository repository;

    @Inject
    public KeyManagementService(KeyManagementRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Transactional
    public String getItemCode(String codeType,String code,int digit){
    	
    	String resultCode = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        String workDt = sdf.format(cal.getTime());
        
        
		BooleanBuilder builder = new BooleanBuilder();
     	builder.and(qKeyManagement.codeType.eq(codeType));  	
     	
		Long nowSeq = select().select(qKeyManagement.seq).distinct().from(qKeyManagement).where(builder).fetchOne();    				
		Long setWorkSeq = new Long(1);
		 
		if(null!=nowSeq){
			setWorkSeq = nowSeq + 1;
			resultCode = code + String.format("%0"+digit+"d", setWorkSeq);
			update(qKeyManagement).set(qKeyManagement.seq, setWorkSeq).set(qKeyManagement.resultCode, resultCode).where(builder).execute();
          
		}else{

			resultCode = code + String.format("%0"+digit+"d", setWorkSeq);
			
			KeyManagement key = new KeyManagement();   
			key.setCodeType(codeType);
			key.setSeq(setWorkSeq);   
			key.setResultCode(resultCode);
			save(key);
		}
		return resultCode;
	}
    
    //교사사번
    @Transactional
    public String getThcerCode(String codeType,String joinDt, int digit){
    	String resultCode = "";
		SimpleDateFormat orignalSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		
		try{
			Date originalDate = orignalSdf.parse(joinDt);
			joinDt = sdf.format(originalDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		BooleanBuilder builder = new BooleanBuilder();
     	builder.and(qKeyManagement.codeType.eq(codeType));  	      	
		Long nowSeq = select().select(qKeyManagement.seq).distinct().from(qKeyManagement).where(builder).fetchOne();    				
		Long setWorkSeq = new Long(1);
		 
		if(null!=nowSeq){
			setWorkSeq = nowSeq + 1;
			resultCode = joinDt + String.format("%0"+digit+"d", setWorkSeq);
			update(qKeyManagement).set(qKeyManagement.seq, setWorkSeq).set(qKeyManagement.resultCode, resultCode).where(builder).execute();
          
		}else{

			resultCode = joinDt + String.format("%0"+digit+"d", setWorkSeq);
			
			KeyManagement key = new KeyManagement();   
			key.setCodeType(codeType);
			key.setSeq(setWorkSeq);   
			key.setResultCode(resultCode);
			save(key);
		}
		return resultCode;
	}
    

    @Transactional
    public String getCommonCode(String codeType,String code,int digit){
    	String resultCode = "";
        
		BooleanBuilder builder = new BooleanBuilder();
     	builder.and(qKeyManagement.codeType.eq(codeType));  	
     	
		Long nowSeq = select().select(qKeyManagement.seq).distinct().from(qKeyManagement).where(builder).fetchOne();    				
		Long setWorkSeq = new Long(1);
		 
		if(null!=nowSeq){
			setWorkSeq = nowSeq + 1;
			resultCode = code+String.format("%0"+digit+"d", setWorkSeq);
			update(qKeyManagement).set(qKeyManagement.seq, setWorkSeq).set(qKeyManagement.resultCode, resultCode).where(builder).execute();
          
		}else{

			resultCode = code+String.format("%0"+digit+"d", setWorkSeq);
			
			KeyManagement key = new KeyManagement();   
			key.setCodeType(codeType);
			key.setSeq(setWorkSeq);   
			key.setResultCode(resultCode);
			save(key);
		}
		return resultCode;
	}
    

    //교사사번
    @Transactional
    public String getOrgCd(String codeType,String orgClass,String parentOrgCd, int digit){
    	
    	String resultCode = "";
    	
    	if(isEmpty(parentOrgCd)){
    		parentOrgCd = "";
    	}
				
		BooleanBuilder builder = new BooleanBuilder();
     	builder.and(qKeyManagement.codeType.eq(codeType));  	      	
		Long nowSeq = select().select(qKeyManagement.seq).distinct().from(qKeyManagement).where(builder).fetchOne();    				
		Long setWorkSeq = new Long(1);
		 
		if(null!=nowSeq){
			setWorkSeq = nowSeq + 1;
			resultCode = orgClass + parentOrgCd + String.format("%0"+digit+"d", setWorkSeq);
			update(qKeyManagement).set(qKeyManagement.seq, setWorkSeq).set(qKeyManagement.resultCode, resultCode).where(builder).execute();
          
		}else{

			resultCode = orgClass + parentOrgCd + String.format("%0"+digit+"d", setWorkSeq);
			
			KeyManagement key = new KeyManagement();   
			key.setCodeType(codeType);
			key.setSeq(setWorkSeq);   
			key.setResultCode(resultCode);
			save(key);
		}
		return resultCode;
	}
    
    public List<KeyManagement> gets(RequestParams<KeyManagement> requestParams) {
        return findAll();
    }
}