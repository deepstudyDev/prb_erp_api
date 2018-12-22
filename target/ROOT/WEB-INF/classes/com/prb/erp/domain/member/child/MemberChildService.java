package com.prb.erp.domain.member.child;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.prb.erp.domain.member.MemberManage;
import com.prb.erp.domain.user.User;
import com.prb.erp.domain.user.UserService;
import com.prb.erp.utils.FroebelIFUtils;
 
@Service
public class MemberChildService extends BaseService<MemberChild, MemberChild.MemberChildId> {
    private MemberChildRepository repository;

    @Inject private KeyManagementService keyManagementService;
    @Inject private UserService userService;
    
    @Inject
    public MemberChildService(MemberChildRepository repository) {
        super(repository);
        this.repository = repository;
    }
    
    //저장
    @Transactional
    public void saveDetail(MemberChild detail,MemberManage member) throws Exception {
    	if(null!=detail){
        	String dmlFlag = "U";	    	    
    		if(isEmpty(detail.getChildCd())){	    			
				dmlFlag = "I";
				detail.setChildCd(keyManagementService.getItemCode("CHILDREN","C",5));
				
				//교육중
	        	if(detail.getOnlineServiceYn().equals("Y"))
	        		detail.setOnlineServiceStatus("10");
	        	
	        	//최초 교사 미배정
	        	if(detail.getVisitServiceYn().equals("Y")){
	        		detail.setVisitServiceStatus("50");	        		
	        		//교육시작종료일 설정 필요
	                // 날짜 더하기
		    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            Calendar cal = Calendar.getInstance();
		            String startDt = sdf.format(cal.getTime());
		            
		            detail.setVisitStartDt(startDt);		            
	                cal.add(Calendar.MONTH, 18);//18개월무료	                
		            String endDt = sdf.format(cal.getTime());
		            detail.setVisitEndDt(endDt);
	        	}
	        	detail.setMemberStatus("1"); // 계약중
    		}
    		if(isEmpty(detail.getContractType())){	 
	    		detail.setContractType("1");	    
    		}		

    		if(isEmpty(detail.getChildrenContractDt())){	 
                // 날짜 더하기
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            Calendar cal = Calendar.getInstance();		            
	            String childrenContractDt = sdf.format(cal.getTime());
	            
	            detail.setChildrenContractDt(childrenContractDt);
    		}
        	
    	    save(detail); 	    	    
        	//FroebelIFUtils.insertChildManage(detail, dmlFlag);    
        	 
    	    /*
        	if(!detail.getContractType().equals("3")){
        		User user = new User();
        		//자녀권한 셋팅
        	    user.setUserCd(detail.getChildCd());
    			user.setUserNm(detail.getChildrenNm());
    			user.setHpNo(detail.getChildrenHpNo());
    			user.setBirthday(detail.getChildrenBirthday());
    			user.setZipcode(member.getHomeZipcode());
    			user.setAddress1(member.getHomeAddress1());
    			user.setAddress2(member.getHomeAddress2());
    			
    			user.setAreaCd(member.getAreaCd());
    			user.setOrgCd(member.getOrgCd());
    			userService.saveChildren(user , "NEW");		
        	}*/
        }
    	
	}
    

    //사용자저장 (초기 데이터 프뢰벨 인터페이스)
    @Transactional
    public void saveInitChild(MemberChild child) throws Exception {
    	if(null!=child){
    		save(child);
    	}
	}
    
    

    //if 데이터 삭제 일괄
    @Transactional
    public void deleteIfData() throws Exception {
        delete(qMemberChild).where(qMemberChild.ifYn.eq("Y")).execute();
    }

    //담당교사 업데이트
    @Transactional
    public void updateVisitorTcherCd(String custCd,String childCd, String visitorTcherCd) throws Exception {
        //교사업데이트/교육중으로 변경
        update(qMemberChild).set(qMemberChild.visitorTcherCd , visitorTcherCd).set(qMemberChild.visitServiceStatus , "10").where(qMemberChild.custCd.eq(custCd).and(qMemberChild.childCd.eq(childCd))).execute();
	}
}