package com.prb.erp.domain.member.cancel;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.prb.erp.utils.SessionUtils;
 
@Service
public class MemberCancelManageService extends BaseService<MemberCancelManage, MemberCancelManage.MemberCancelManagId> {
    private MemberCancelManageRepository repository;
    
    @Inject private KeyManagementService keyManagementService;
    @Inject private MemberCancelManageMapper memberCancelManageMapper;
    
    @Inject
    public MemberCancelManageService(MemberCancelManageRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public MemberCancelManageVO getMemberCancel(RequestParams<MemberCancelManageVO> vo) {
    	return memberCancelManageMapper.getMemberCancel(vo);
    }
     
    
    public MemberCancelManagePagingVO getMemberCancelList(RequestParams<MemberCancelManageVO> vo, Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	MemberCancelManagePagingVO tc = new MemberCancelManagePagingVO();
    	tc.setResult(memberCancelManageMapper.getMemberCancelList(vo));   
    	
    	//현재페이지    	
    	tc.setPageNo(pageNumber);    
    	  
    	int totalCnt = memberCancelManageMapper.getMemberCancelListCount(vo);    	
    	tc.setTotalCnt(totalCnt);
    	return tc;
    }
    
    
    //저장-요청
    @Transactional
    public MemberCancelManage requestCancel(MemberCancelManage vo) throws Exception {
		if(null==vo.getCancelCd()){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        String transRequestDt = sdf.format(cal.getTime());		    
	    	String userCd = SessionUtils.getCurrentLoginUserCd();		    		
	        
	        vo.setCancelRequestDt(transRequestDt);
	        vo.setCancelRequestUserCd(userCd);
			vo.setCancelCd(keyManagementService.getCommonCode("MEMBER_CANCEL","MC",4));
			vo.setApprovalYn("N"); //미승인,요청상태
			vo.setConfirmYn("N"); //인수인계미확정
		}		
		
	    save(vo);   	    
	    //해당 회원 - 계약취소요청
        update(qMemberChild).set(qMemberChild.memberStatus , "2").where(qMemberChild.custCd.eq(vo.getCustCd()).and(qMemberChild.childCd.eq(vo.getChildCd()))).execute();
    	return vo;
	}     
    
    //저장-승인
    @Transactional
    public MemberCancelManage approvalCancel(MemberCancelManage vo) throws Exception {
		if(null != vo){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        String approvalDt = sdf.format(cal.getTime());		    
	    	String userCd = SessionUtils.getCurrentLoginUserCd();		    	
			vo.setApprovalYn("Y"); //승인
			vo.setConfirmYn("Y"); //인수인계확정
			vo.setApprovalUserCd(userCd);
			vo.setApprovalDt(approvalDt);
		    save(vo);   
		}
	    //해당 회원 - 계약취소
        update(qMemberChild).set(qMemberChild.memberStatus , "3").where(qMemberChild.custCd.eq(vo.getCustCd()).and(qMemberChild.childCd.eq(vo.getChildCd()))).execute();
    	return vo;
	}
}


