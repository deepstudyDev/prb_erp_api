package com.prb.erp.domain.member;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.prb.erp.domain.member.child.MemberChild;
import com.prb.erp.domain.member.child.MemberChildService;
import com.prb.erp.domain.member.item.MemberItem;
import com.prb.erp.domain.member.item.MemberItemService;
import com.prb.erp.domain.tmsg.queue.TmsgQueueService;
import com.prb.erp.domain.user.UserService;
 
@Service
public class MemberManageService extends BaseService<MemberManage, MemberManage.MemberManageId> {
    private MemberManageRepository repository;
    
    @Inject private KeyManagementService keyManagementService;
    @Inject private MemberItemService memberItemService;
    @Inject private MemberChildService memberChildService;
    @Inject private MemberManageMapper memberManageMapper;
    @Inject private UserService userService;
    @Inject private TmsgQueueService tmsgQueueService;
    
    @Inject
    public MemberManageService(MemberManageRepository repository) {
        super(repository);
        this.repository = repository;
    }

    //조회 (단건)
    public MemberManageVO getMember(RequestParams<MemberManageVO> requestParams) {
    	return memberManageMapper.getMember(requestParams);
    }
    
    //조회 (단건::자녀)
    public MemberManageVO getMemberChildren(RequestParams<MemberManageVO> requestParams) {
    	return memberManageMapper.getMemberChildren(requestParams);
    }

    //조회 (리스트)
    public List<MemberManageVO> gets(RequestParams<MemberManageVO> vo) {
    	return memberManageMapper.getMemberListAll(vo);
    }


    //조회  (페이징)
    public MemberManagePagingVO getMemberList(RequestParams<MemberManageVO> vo,  Pageable pageable) {    
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	MemberManagePagingVO result = new MemberManagePagingVO();
    	result.setResult(memberManageMapper.getMemberList(vo));   
    	
    	//현재페이지    	
    	result.setPageNo(pageNumber);    
    	  
    	int totalCnt = memberManageMapper.getMemberListCount(vo);    	
    	result.setTotalCnt(totalCnt);
    	return result;
    }

    //계약 상세 (자녀별)
    public List<MemberDetailVO> getMemberDetail(RequestParams<MemberDetailVO> vo) {
    	List<MemberDetailVO> details = memberManageMapper.getMemberDetail(vo);    	
    	return details;
    }

    //사용자저장
    @Transactional
    public MemberManage saveMember(MemberManage member) throws Exception {
    	String dmlFlag = "U";
    	if(null!=member)
    	{
			MemberItem memberItem = member.getItemList();
			//MemberTcher tcherItem = member.getTcherList();			

			if(null==member.getTmsgSeq()){
				member.setTmsgSeq(keyManagementService.getItemCode("TMSG","",12));
			}
			
			if(null==member.getCustCd()){
				dmlFlag = "I";
				member.setContCd(keyManagementService.getItemCode("CONT","CONT",5));
				member.setCustCd(keyManagementService.getItemCode("MEMBER","M",5));
				
				memberItem.setCustCd(member.getCustCd());
				//tcherItem.setCustCd(member.getCustCd());
		        
		        for (MemberChild item : member.getChildList()) {
		        	item.setCustCd(member.getCustCd());		   
		        }

				//신규일경우, temp file 존재시 키값 변경
		        update(qCommonFile).set(qCommonFile.targetId , member.getCustCd())
		        	.where(qCommonFile.targetId.eq(member.getTempFileCd())
		        			.and(qCommonFile.targetType.eq("CONTRACT"))
		        			.and(qCommonFile.tempYn.eq("Y"))).execute();		 
			}
			//2018-12-21 안지호. 상세화면에서 지국 셀렉트 박스에서 오류로 전체로 선택되었을때 예외처리
			if ("".equals(member.getOrgCd())) {
				member.setOrgCd(memberManageMapper.getMemberOrgCd(member.getCustCd()));
			}
		    save(member); 
		    
			//FroebelIFUtils.insertMemberManage(member, dmlFlag);    		
		    
		    /*
		    User user = new User();
    		//자녀권한 셋팅
    	    user.setUserCd(member.getCustCd());
			user.setUserNm(member.getGd1Nm());
			user.setHpNo(member.getHpNo());
			user.setBirthday(member.getGd1Birthday());
			user.setZipcode(member.getHomeZipcode());
			user.setAddress1(member.getHomeAddress1());
			user.setAddress2(member.getHomeAddress2());
			
			user.setAreaCd(member.getAreaCd());			
			user.setOrgCd(member.getOrgCd());			
			userService.saveMember(user , "NEW");	
			 */

	        memberItemService.saveDetail(memberItem);       
	        memberChildService.saveDetail(member.getChildList().get(0),member);
		    
    	}

    	//전문 등록 TMSG
    	tmsgQueueService.insertQueue(member.getTmsgSeq());
	    return member;
	}
    
    //사용자저장 (초기 데이터 프뢰벨 인터페이스)
    @Transactional
    public void saveInitMember(MemberManage member) throws Exception {
    	if(null!=member){
    		save(member);
    	}
	}
    
    //계약추가 (형제추가)
    @Transactional
    public MemberManage addChild(MemberManage member) throws Exception {
    	if(null!=member){
    		
			if(isNotEmpty(member.getCustCd())){
				
		        for (MemberChild item : member.getChildList()) {
		        	item.setCustCd(member.getCustCd());
		        	item.setChildCd(null);
		        	
		        	//교육중
		        	if(item.getOnlineServiceYn().equals("Y"))
		        		item.setOnlineServiceStatus("10");
		        	
		        	if(item.getVisitServiceYn().equals("Y"))
		        		item.setVisitServiceStatus("10");
		        }
			}
    	}
        memberChildService.saveDetail(member.getChildList().get(0),member);	    
	    return member;
	}
    

    //if 데이터 삭제 일괄
    @Transactional
    public void deleteIfData() throws Exception {
        delete(qMemberManage).where(qMemberManage.ifYn.eq("Y")).execute();
    }
    
}


