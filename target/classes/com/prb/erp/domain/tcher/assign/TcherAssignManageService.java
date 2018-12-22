package com.prb.erp.domain.tcher.assign;

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
import com.prb.erp.utils.SessionUtils;
 
@Service
public class TcherAssignManageService extends BaseService<TcherAssignManage, TcherAssignManage.TcherAssignManageId> {
    private TcherAssignManageRepository repository;
    
    @Inject private KeyManagementService keyManagementService;
    @Inject private TcherAssignManageMapper tcherAssignManageMapper;
    @Inject private MemberChildService memberChildService;
    
    
    @Inject
    public TcherAssignManageService(TcherAssignManageRepository repository) {
        super(repository);
        this.repository = repository;
    }
 
    public TcherAssignManageVO gets(RequestParams<TcherAssignManageVO> vo) {
    	return tcherAssignManageMapper.getTcherAssign(vo);
    }
    
    
    public TcherAssignManagePagingVO getTcherAssignList(RequestParams<TcherAssignManageVO> vo, Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	TcherAssignManagePagingVO tc = new TcherAssignManagePagingVO();
    	tc.setResult(tcherAssignManageMapper.getTcherAssignList(vo));   
    	
    	//현재페이지    	
    	tc.setPageNo(pageNumber);    
    	  
    	int totalCnt = tcherAssignManageMapper.getTcherAssignListCount(vo);    	
    	tc.setTotalCnt(totalCnt);
    	return tc;
    }
    

    
    //저장
    @Transactional
    public TcherAssignManage saveAssign(TcherAssignManage vo) throws Exception {
		if(null==vo.getAssignCd()){
			
			//기존 배정목록  N 처리
			updatePreAssign(vo.getCustCd(), vo.getChildCd());
			
			vo.setAssignCd(keyManagementService.getCommonCode("TCHER_ASSIGN","TS",4));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        
	        String approvalDt = sdf.format(cal.getTime());		
	    	String userCd = SessionUtils.getCurrentLoginUserCd();		        

			if(null != vo.getAssignRequestUserCd()){
				userCd = SessionUtils.getCurrentLoginUserCd();		    		
			}
			
	    	vo.setAssignRequestUserCd(userCd);
	    	vo.setAssignRequestDt(approvalDt);
	    	vo.setApprovalDt(approvalDt);
	    	vo.setApprovalUserCd(userCd);
			vo.setApprovalYn("Y"); //승인
			vo.setConfirmYn("Y"); //인수인계확정
			vo.setLastYn("Y"); //승인
			vo.setAssignType("10"); //배정구분 10 일반
		}		
		
	    save(vo);   	    
	    //회원정보 담당 교사 업데이트	    
	    if(isNotEmpty(vo.getAssignUserCd())){ 
	    	memberChildService.updateVisitorTcherCd(vo.getCustCd(),vo.getChildCd(),vo.getAssignUserCd());
	    }
    	return vo;
	}

    
    //이전 배정 건들 LAST_YN = N 처리
    @Transactional
    public void updatePreAssign(String custCd, String childCd) throws Exception {
    	 update(qTcherAssignManage)
    	 .set(qTcherAssignManage.lastYn , "N")
    	 .where(qTcherAssignManage.custCd.eq(custCd)
    			 .and(qTcherAssignManage.childCd.eq(childCd)))
    	 .execute();
    }
    
}


