package com.prb.erp.domain.tcher.trans;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.prb.erp.domain.member.child.MemberChildService;
import com.prb.erp.domain.tcher.assign.TcherAssignManage;
import com.prb.erp.domain.tcher.assign.TcherAssignManageService;
import com.prb.erp.utils.SessionUtils;
import com.querydsl.core.BooleanBuilder;
 
@Service
public class TcherTransManageService extends BaseService<TcherTransManage, TcherTransManage.TcherTransManageId> {
    private TcherTransManageRepository repository;
    
    @Inject private KeyManagementService keyManagementService;
    @Inject private TcherTransManageMapper tcherTransManageMapper;
    @Inject private TcherAssignManageService tcherAssignManageService;
    
    @Inject
    public TcherTransManageService(TcherTransManageRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public TcherTransManage getOne(String transCd) {
    	BooleanBuilder builder = new BooleanBuilder();
		if (isNotEmpty(transCd)) {  
			builder.and(qTcherTransManage.transCd.eq(transCd));
		}
		return select().from(qTcherTransManage).where(builder).fetchOne();
    }
 
    public TcherTransManageVO getTcherTrans(RequestParams<TcherTransManageVO> vo) {
    	return tcherTransManageMapper.getTcherTrans(vo);
    }
    
    
    public TcherTransManagePagingVO getTcherTransList(RequestParams<TcherTransManageVO> vo, Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	TcherTransManagePagingVO tc = new TcherTransManagePagingVO();
    	tc.setResult(tcherTransManageMapper.getTcherTransList(vo));   
    	
    	//현재페이지    	
    	tc.setPageNo(pageNumber);    
    	  
    	int totalCnt = tcherTransManageMapper.getTcherTransListCount(vo);    	
    	tc.setTotalCnt(totalCnt);
    	return tc;
    }
    

    //저장-요청
    @Transactional
    public TcherTransManage transRequest(TcherTransManage vo) throws Exception {
    	String userCd;
		if(null==vo.getTransCd()){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        String transRequestDt = sdf.format(cal.getTime());		    

			if(null == vo.getTransRequestUserCd()){
				userCd = SessionUtils.getCurrentLoginUserCd();		    		
			}else{
				userCd = vo.getTransRequestUserCd();
			}
	        
	        
	        vo.setTransRequestDt(transRequestDt);
	        vo.setTransRequestUserCd(userCd);
			vo.setTransCd(keyManagementService.getCommonCode("TCHER_TRANS","T",4));
			
			vo.setApprovalYn("N"); //미승인,요청상태
			vo.setConfirmYn("N"); //인수인계미확정
		}
		
	    save(vo);   
	    
	    //해당 회원 - 인수인계요청으로 상태변경 40	인수인계요청
        update(qMemberChild).set(qMemberChild.visitServiceStatus , "40").where(qMemberChild.custCd.eq(vo.getCustCd()).and(qMemberChild.childCd.eq(vo.getChildCd()))).execute();
    	return vo;
	}
    
    
    //저장-승인
    @Transactional
    public TcherTransManage transSave(TcherTransManage vo) throws Exception {
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
		
	    //해당 회원 - 교육중으로 상태변경 10 교육중
        update(qMemberChild).set(qMemberChild.visitServiceStatus , "10").where(qMemberChild.custCd.eq(vo.getCustCd()).and(qMemberChild.childCd.eq(vo.getChildCd()))).execute();
        
        
        //배정 테이블 insert
        TcherAssignManage assign = new TcherAssignManage();
        assign.setCustCd(vo.getCustCd());
        assign.setChildCd(vo.getChildCd());
        assign.setAssignRequestUserCd(userCd);        
        assign.setAssignRemark(vo.getTransRemark());
        assign.setAssignUserCd(vo.getTransUserCd());        
        assign.setAssignType("20");        
        tcherAssignManageService.saveAssign(assign);
        
	    //회원정보 담당 교사 업데이트	    
	    /*
        if(isNotEmpty(vo.getTransUserCd())){
	    	memberChildService.updateVisitorTcherCd(vo.getCustCd(),vo.getChildCd(), vo.getTransUserCd());
	    }
	    */
    	return vo;
	}
}


