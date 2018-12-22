package com.prb.erp.domain.tcher;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.prb.erp.domain.user.User;
import com.prb.erp.domain.user.UserService;
import com.prb.erp.utils.FroebelIFUtils;
 
@Service
public class TcherManageService extends BaseService<TcherManage, TcherManage.TcherManageId> {
    private TcherManageRepository repository;
    
    @Inject private TcherManageMapper tcherManageMapper;
    @Inject private KeyManagementService keyManagementService;
    @Inject private UserService userService;
    
    @Inject
    public TcherManageService(TcherManageRepository repository) {
        super(repository);
        this.repository = repository;
    }

    //전체
    public List<TcherManageVO> getAll(RequestParams<TcherManageVO> vo) {
    	return tcherManageMapper.getTcherListAll(vo);
    }

    //페이징
    public TcherManagePagingVO getTcherList(RequestParams<TcherManageVO> vo, Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	TcherManagePagingVO tc = new TcherManagePagingVO();
    	tc.setResult(tcherManageMapper.getTcherList(vo));   
    	
    	//현재페이지    	
    	tc.setPageNo(pageNumber);    
    	  
    	int totalCnt = tcherManageMapper.getTcherListCount(vo);    	
    	tc.setTotalCnt(totalCnt);
    	return tc;
    }
    
    //단건
    public TcherManageVO getTcher(RequestParams<TcherManageVO> vo) {
    	return tcherManageMapper.getTcher(vo);
    }
    
    //패스워드초기화-유저쪽 갱신
    @Transactional
    public void resetPs(TcherManage tcher) throws Exception {
    	if(null!=tcher){
    		userService.resetPs(tcher.getTcherCd(),tcher.getTcherHpNo());        	
    		userService.setLoginDate(tcher.getTcherCd());        	
    	}
	}

    //교사저장
    @Transactional
    public TcherManage saveTcher(TcherManage tcher) throws Exception {
    	//주문 사원등록구분값 필수..
    	String mode = "NEW";
    	String dmlFlag = "U";
    	
    	if(null!=tcher){
    		User user = new User();
    		//신규
			if(null==tcher.getTcherCd()){
				dmlFlag = "I";
				tcher.setTcherCd(keyManagementService.getThcerCode("TCHER",tcher.getJoinDt(),4));		
				
				//신규일경우, temp file 존재시 키값 변경
		        update(qCommonFile).set(qCommonFile.targetId , tcher.getTcherCd())
		        	.where(qCommonFile.targetId.eq(tcher.getTempFileCd())
		        			.and(qCommonFile.targetType.eq("TCHER"))
		        			.and(qCommonFile.tempYn.eq("Y"))).execute();		        
				
			}else{
				mode = "";
				user = userService.findOne(tcher.getTcherCd());
			}
			
    	    save(tcher);
    	    user.setUserType(tcher.getTcherType());
    	    user.setUserCd(tcher.getTcherCd());
			user.setUserNm(tcher.getTcherNm());
			user.setTelNo(tcher.getTcherTelNo());
			user.setHpNo(tcher.getTcherHpNo());
			user.setBirthday(tcher.getTcherBirthday());
			user.setJoinDt(tcher.getJoinDt()); 
			user.setOutDt(tcher.getOutDt());
			user.setZipcode(tcher.getTcherZipcode());
			user.setAddress1(tcher.getTcherAddress1());
			user.setAddress2(tcher.getTcherAddress2());
			//user.setAreaCd(tcher.getAreaCd());
			
			//12 상담조직 // 14 방문조직
    	    user.setAreaCd("AREA00001");
    	    user.setOrgClass(tcher.getTcherType());
			user.setOrgCd(tcher.getOrgCd());

			user.setRankCd(tcher.getRankCd());

			//근무상태
			if(tcher.getWorkCd().equals("4")){
				user.setUserStatus("20");
			}else{
				user.setUserStatus("10");				
			}
			
			//결정가능자(ex:팀장)
			if(tcher.getRankCd().equals("310")){
				user.setDecisionYn("Y");
			}else{
				user.setDecisionYn("N");				
			}
			userService.saveTcher(user,mode,"Y");			
    	}
    	
    	if(tcher.getTcherType().equals("12")){
        	//상담
    		FroebelIFUtils.insertTcherManage12(tcher, dmlFlag);    		
    	}else{
    		//방문
    		FroebelIFUtils.insertTcherManage14(tcher, dmlFlag);    		
    	}
    	return tcher;
	}
    
    @Transactional
    public void deleteIfData() throws Exception {
        delete(qTcherManage).where(qTcherManage.ifYn.eq("Y")).execute();
        delete(qUser).where(qUser.ifYn.eq("Y")).execute();
    }
    

    //교사저장
    @Transactional
    public void saveTcherEtcRemark(TcherManage tcher) throws Exception {
    	    save(tcher);
    	
    	if(tcher.getTcherType().equals("10")){
        	//상담
    		FroebelIFUtils.insertTcherManage10Etc(tcher);    		
    	}else{
    		//방문
    		FroebelIFUtils.insertTcherManage20Etc(tcher);    		
    	}
	}

}


