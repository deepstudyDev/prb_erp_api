package com.prb.erp.domain.tcher.rest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.prb.erp.domain.tcher.trans.TcherTransManage;
import com.prb.erp.utils.SessionUtils;
import com.querydsl.core.BooleanBuilder;
 
@Service
public class TcherRestManageService extends BaseService<TcherRestManage, TcherRestManage.TcherRestManageId> {
    private TcherRestManageRepository repository;
    
    @Inject private KeyManagementService keyManagementService;
    @Inject private TcherRestManageMapper tcherRestManageMapper;
    
    
    @Inject
    public TcherRestManageService(TcherRestManageRepository repository) {
        super(repository);
        this.repository = repository;
    }


    public TcherRestManage getOne(String restCd) {
    	BooleanBuilder builder = new BooleanBuilder();
		if (isNotEmpty(restCd)) {  
			builder.and(qTcherRestManage.restCd.eq(restCd));
		}
		return select().from(qTcherRestManage).where(builder).fetchOne();
    }
 
    public TcherRestManageVO getTcherRest(RequestParams<TcherRestManageVO> vo) {
    	return tcherRestManageMapper.getTcherRest(vo); 
    }
    
    
    public TcherRestManagePagingVO getTcherRestList(RequestParams<TcherRestManageVO> vo, Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	TcherRestManagePagingVO tc = new TcherRestManagePagingVO();
    	tc.setResult(tcherRestManageMapper.getTcherRestList(vo));   
    	
    	//현재페이지    	
    	tc.setPageNo(pageNumber);    
    	  
    	int totalCnt = tcherRestManageMapper.getTcherRestListCount(vo);    	
    	tc.setTotalCnt(totalCnt);
    	return tc;
    }
    


    //저장-요청
    @Transactional
    public TcherRestManage restRequest(TcherRestManage vo) throws Exception {
		if(null==vo.getRestCd()){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        String restRequestDt = sdf.format(cal.getTime());		    
	    	String userCd = SessionUtils.getCurrentLoginUserCd();		    		
	        
	        vo.setRestRequestDt(restRequestDt);
	        vo.setRestRequestUserCd(userCd); 
			vo.setRestCd(keyManagementService.getCommonCode("TCHER_REST","T",4));
			vo.setApprovalYn("N"); //미승인,요청상태
			vo.setConfirmYn("N"); //인수인계미확정
		}
		
	    save(vo);   
	    
	    //해당 회원 - 학습휴식신청으로 상태변경 80	학습휴식신청
        update(qMemberChild).set(qMemberChild.visitServiceStatus , "80").where(qMemberChild.custCd.eq(vo.getCustCd()).and(qMemberChild.childCd.eq(vo.getChildCd()))).execute();
    	return vo;
	}
    
    
    //저장-승인
    @Transactional
    public TcherRestManage restSave(TcherRestManage vo) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String approvalDt = sdf.format(cal.getTime());		
    	String userCd = SessionUtils.getCurrentLoginUserCd();		    
    	
		if(null != vo){    

			
			if(null != vo.getApprovalUserCd()){
				userCd = SessionUtils.getCurrentLoginUserCd();		    		
			}
			
	    	vo.setApprovalDt(approvalDt);
	    	vo.setApprovalUserCd(userCd);
			vo.setApprovalYn("Y"); //승인
			vo.setConfirmYn("Y"); //인수인계확정
		    save(vo);   
		}
		
	    //해당 회원 - 학습휴식으로 상태변경 70 학습휴식
        update(qMemberChild).set(qMemberChild.visitServiceStatus , "70").where(qMemberChild.custCd.eq(vo.getCustCd()).and(qMemberChild.childCd.eq(vo.getChildCd()))).execute();
        return vo;
	}
}


