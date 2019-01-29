package com.prb.erp.domain.api;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import com.prb.erp.domain.kicc.KiccResultService;
import com.prb.erp.domain.member.MemberManageVO;
import com.prb.erp.domain.user.User;
import com.prb.erp.utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.SHAPasswordEncoder;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.prb.erp.domain.member.MemberManage;
import com.prb.erp.domain.member.MemberManageService;
import com.prb.erp.domain.member.brother.MemberBrother;
import com.prb.erp.domain.member.brother.MemberBrotherService;
import com.prb.erp.domain.member.child.MemberChild;
import com.prb.erp.domain.member.child.MemberChildService;
import com.prb.erp.domain.member.item.MemberItem;
import com.prb.erp.domain.member.item.MemberItemService;
import com.prb.erp.domain.sms.detail.SendDetail;
import com.prb.erp.domain.sms.detail.SendDetailService;
import com.prb.erp.domain.sms.master.SendMaster;
import com.prb.erp.domain.sms.master.SendMasterService;
import com.prb.erp.domain.tcher.assign.TcherAssignManage;
import com.prb.erp.domain.tcher.assign.TcherAssignManageService;
import com.prb.erp.domain.tcher.rest.TcherRestManage;
import com.prb.erp.domain.tcher.rest.TcherRestManageService;
import com.prb.erp.domain.tcher.trans.TcherTransManage;
import com.prb.erp.domain.tcher.trans.TcherTransManageService;
import com.prb.erp.domain.user.UserService;
import com.prb.erp.utils.SmsSendUtils;


@Service
public class ApiService extends BaseService {

    @Inject private SHAPasswordEncoder bCryptPasswordEncoder;
	@Inject private ApiMapper apiMapper;
    @Inject private KeyManagementService keyManagementService;
    @Inject private MemberManageService memberManageService;
    @Inject private MemberItemService memberItemService;
    @Inject private MemberChildService memberChildService;
	@Inject private TcherTransManageService tcherTransManageService;
	@Inject private TcherRestManageService tcherRestManageService;
	@Inject private TcherAssignManageService tcherAssignManageService;
	@Inject private MemberBrotherService memberBrotherService;

	@Inject private SendMasterService sendMasterService;
	@Inject private SendDetailService sendDetailService;
	@Inject private UserService userService;

	@Inject private KiccResultService kiccResultService;


	public List<ApiCommonCodeVO> getCommonCode(RequestParams<ApiCommonCodeVO> vo){
    	return apiMapper.getCommonCode(vo);
    }

    public ApiUserVO getUserInfo(RequestParams requestParams) {
        String userPs = requestParams.getString("userPs" , "");        
	    String password = bCryptPasswordEncoder.encode(userPs);
	    requestParams.put("userPs" , password);	   
    	return apiMapper.getUserInfo(requestParams);
    }

    //상품정보
    public ApiResultObjectPagingVO getGoodsManageList(RequestParams<ApiGoodsManageVO> vo) {
    	String goodsCd = vo.getString("goodsCd","");
    	int pageNumber = 1;
    	
    	if(goodsCd.equals("")){
    		pageNumber = vo.getInt("pageNumber",1);
    	}    	
    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	ApiResultObjectPagingVO result = new ApiResultObjectPagingVO();
    	List<ApiGoodsManageVO> list = apiMapper.getGoodsManageList(vo);
    	result.setResult(list);    
    	
    	//현재페이지    	
    	result.setPageNumber(pageNumber);         	  
    	int totalCnt = apiMapper.getGoodsManageListCount(vo);    	
    	result.setTotalCnt(totalCnt);    	
    	
    	String resultCode;
    	String resultMsg;
    	
    	if (list.size() > 0){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "결과가 없습니다.";
    	}    	
    	
    	result.setResultCode(resultCode);
    	result.setResultMsg(resultMsg);   	
    	return result;
    }

    
    //계약 상세 (자녀별)
    public List<ApiMemberDetailVO> getMemberDetail(RequestParams<ApiMemberDetailVO> vo) {
    	List<ApiMemberDetailVO> details = apiMapper.getMemberDetail(vo);    	
    	return details;
    }
    
    //계약 상세 (header)
    public ApiMemberHeaderVO getMemberHeader(RequestParams<Object> vo) {
    	ApiMemberHeaderVO header = apiMapper.getMemberHeader(vo);    	
    	return header;
    }

	/**
	 * <PRE>
	 *     1. 작성자 : 안지호
	 *     2. 작성일 : 2018-12-11
	 *     3. 설명 : 계약정보 상세조회 - 보호자이름, 자녀이름, 휴대전화번호, 자녀학년 4가지 필수값 (핀노드 요청으로 신규 작성)
	 * </PRE>
	 * @return
	 */
	//계약 상세 (header)
	public ApiMemberHeaderVO getMemberHeaderAnyParam(RequestParams<Object> vo) {
		ApiMemberHeaderVO header = apiMapper.getMemberHeaderAnyParam(vo);
		return header;
	}

    //계약 상세 (child)
    public List<ApiMemberChildVO> getMemberChilds(RequestParams<Object> vo) {
    	List<ApiMemberChildVO> child = apiMapper.getMemberChilds(vo);    	
    	return child;
    }

    //계약 상세 (child-형제정보)
    public List<ApiBrotherVO> getMemberBrothers(RequestParams<Object> vo) {
    	List<ApiBrotherVO> child = apiMapper.getMemberBrothers(vo);    	
    	return child;
    }
    
    //계약 상세 (child)
    public ApiMemberChildVO getMemberChild(RequestParams<Object> vo) {
    	ApiMemberChildVO child = apiMapper.getMemberChild(vo);    	
    	return child;
    }

	/**
	 * <PRE>
	 *     1. 작성자 : 안지호
	 *     2. 작성일 : 2018-12-11
	 *     3. 설명 : 계약정보 상세조회 - 보호자이름, 자녀이름, 휴대전화번호, 자녀학년 4가지 필수값 (핀노드 요청으로 신규 작성)
	 * </PRE>
	 * @return
	 */
	//계약 상세 (child)
	public ApiMemberChildVO getMemberChildAnyParam(RequestParams<Object> vo) {
		ApiMemberChildVO child = apiMapper.getMemberChildAnyParam(vo);
		return child;
	}
    //계약 상세 (child-요약)
    public List<ApiMemberChildVO> getContractInfoDetail(RequestParams<Object> vo) {
    	List<ApiMemberChildVO> child = apiMapper.getContractInfoDetail(vo);    	
    	return child;
    }    
    
    
    //회원::계약정보
    public ApiResultObjectPagingVO getMemberList(RequestParams<ApiMemberManageVO> vo) {
    	String custCd = vo.getString("custCd","");
    	String orderColumn = vo.getString("orderColumn","");
    	String orderType = vo.getString("orderType","");
    	String orderType2 = "";
    	
    	int pageNumber = 1;
    	
    	if(custCd.equals("")){
    		pageNumber = vo.getInt("pageNumber",1);
    	}    	
    	
    	if(orderColumn.equals("")){
    		orderColumn = "UPDATED_AT";
    	}
    	
    	if(orderType.equals("")){
    		orderType = "asc";
    	}

    	if(orderType.equals("asc")){
    		orderType2 = "desc";
    	}else{
    		orderType2 = "asc";
    	}

    	vo.put("orderColumn" ,orderColumn);  
    	vo.put("orderType" ,orderType);  
    	vo.put("orderType2" ,orderType2);  
    	
    	
    	vo.put("pageNumber" ,pageNumber);  
    	
    	ApiResultObjectPagingVO result = new ApiResultObjectPagingVO();
    	List<ApiMemberManageVO> list = apiMapper.getMemberList(vo);
    	result.setResult(list);   
    	
    	//현재페이지    	
    	result.setPageNumber(pageNumber);         	  
    	int totalCnt = apiMapper.getMemberListCount(vo);    	
    	result.setTotalCnt(totalCnt);    	
    	
    	String resultCode;
    	String resultMsg;
    	
    	if (list.size() > 0){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "결과가 없습니다.";
    	}
    	
    	result.setResultCode(resultCode);
    	result.setResultMsg(resultMsg);   	

    	result.setTodayMemberCount(apiMapper.getTodayMemberCount(vo) + "");
    	result.setTotalMemberCount(apiMapper.getTotalMemberCount(vo) + "");
    	return result;
    }

    //사용자저장
    @Transactional
    public ApiResultCodeVO saveMember(ApiMemberManageSaveVO apiVo) {
		MemberManage member = new MemberManage();
		MemberItem memberItem = new MemberItem();
		MemberChild memberChild = new MemberChild();

		ApiResultCodeVO result = new ApiResultCodeVO();
		
		try{
			if(null!=apiVo){
				if(StringUtils.isEmpty(apiVo.getCustCd())){
					apiVo.setCustCd(keyManagementService.getItemCode("MEMBER","M",5));
					member.setContCd(keyManagementService.getItemCode("CONT","CONT",5));
				}else{
					apiVo.setCustCd(apiVo.getCustCd());
				}
				member.setTmsgSeq(keyManagementService.getItemCode("TMSG","",12));
				
				member.setCustCd(apiVo.getCustCd());
				member.setAreaCd(apiVo.getAreaCd());
				member.setOrgCd(apiVo.getOrgCd());
				//주민번호 관련 추가 2019. 01. 29 안지호
				member.setRepreNum( CommonUtils.getRepreNum(apiVo.getGd1Birthday(), apiVo.getGd1RelationCd()) );
				member.setAreaHpNo(apiVo.getAreaHpNo()); 
				member.setGd1UserCd(apiVo.getCustCd());
				member.setGd1Nm(apiVo.getGd1Nm());
				member.setGd1RelationCd(apiVo.getGd1RelationCd());				
				member.setGd1Birthday(apiVo.getGd1Birthday());
				member.setGd1RelationCd(apiVo.getGd1RelationCd());
				member.setGd2Nm(apiVo.getGd2Nm());
				member.setGd2RelationCd(apiVo.getGd2RelationCd());
				member.setGd2Birthday(apiVo.getGd2Birthday());
				member.setGd1RelationCd(apiVo.getGd1RelationCd());
				member.setTelNo(apiVo.getTelNo());
				member.setHpNo(apiVo.getHpNo());
				member.setEmail(apiVo.getEmail());				
				member.setHomeZipcode(apiVo.getHomeZipcode());
				member.setHomeAddress1(apiVo.getHomeAddress1());
				member.setHomeAddress2(apiVo.getHomeAddress2());
				member.setDeliveryZipcode(apiVo.getDeliveryZipcode());
				member.setDeliveryAddress1(apiVo.getDeliveryAddress1());
				member.setDeliveryAddress2(apiVo.getDeliveryAddress2());
//				member.setBankAccountNm(apiVo.getBankAccountNm());
//				member.setBankAccountNo(apiVo.getBankAccountNo());
//				member.setBankCd(apiVo.getBankCd());
//				member.setWithDrawDay(apiVo.getWithDrawDay());
				memberManageService.save(member); 
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
				
				user.setAreaCd(apiVo.getAreaCd());			
				user.setOrgCd(apiVo.getOrgCd());
				user.setDecisionYn("N");						
				userService.saveMember(user , "NEW");	*/

				memberItem.setCustCd(apiVo.getCustCd());
				memberItem.setSalesType(apiVo.getSalesType());	// 2018-12-11 안지
				memberItem.setContractDt(apiVo.getContractDt());
				memberItem.setGoodsCd(apiVo.getGoodsCd());
				memberItem.setSubjectNm(apiVo.getSubjectNm());
				memberItem.setAgreementCd(apiVo.getAgreementCd());
				memberItem.setTotalPrice(apiVo.getTotalPrice());
				memberItem.setMonthPrice(apiVo.getMonthPrice());
				memberItem.setContractPrice(apiVo.getContractPrice());		
				memberItem.setPaymentPrice(apiVo.getPaymentPrice());
				memberItem.setContractPaymentWay(apiVo.getContractPaymentWay());
				memberItem.setContractPaymentMethod(apiVo.getContractPaymentMethod());
				memberItem.setPaymentWay(apiVo.getPaymentWay());
				memberItem.setPaymentMethod(apiVo.getPaymentMethod());
				memberItem.setBankCd(apiVo.getBankCd());
				memberItem.setBankAccountNm(apiVo.getBankAccountNm());
				memberItem.setBankAccountNo(apiVo.getBankAccountNo());
				memberItem.setWithDrawDay(apiVo.getWithDrawDay());
				memberItemService.saveDetail(memberItem);
				

				memberChild.setCustCd(apiVo.getCustCd());
				
				if(StringUtils.isEmpty(apiVo.getChildCd())){
					apiVo.setChildCd(keyManagementService.getItemCode("CHILDREN","C",5));
					//교육중
		        	if(apiVo.getOnlineServiceYn().equals("Y"))
		        		memberChild.setOnlineServiceStatus("10");
		        	
		        	//최초 교사 미배정
		        	if(apiVo.getVisitServiceYn().equals("Y")){
		        		memberChild.setVisitServiceStatus("50");	        		
		        		//교육시작종료일 설정 필요
		                // 날짜 더하기
			    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			            Calendar cal = Calendar.getInstance();
			            String startDt = sdf.format(cal.getTime());
			            
			            memberChild.setVisitStartDt(startDt);		            
		                cal.add(Calendar.MONTH, 18);//18개월무료	                
			            String endDt = sdf.format(cal.getTime());
			            memberChild.setVisitEndDt(endDt);
		        	}
					memberChild.setMemberStatus("1"); // 계약중
				}else{
					apiVo.setChildCd(apiVo.getChildCd());
				}

				memberChild.setChildCd(apiVo.getChildCd());
				memberChild.setChildrenUserCd(apiVo.getChildCd());
	    						//기본계약
				memberChild.setContractType("1");
				
				
	    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	            Calendar cal = Calendar.getInstance();		            
	            String childrenContractDt = sdf.format(cal.getTime());
	            
	            memberChild.setChildrenContractDt(childrenContractDt);
	           
				memberChild.setChildrenNm(apiVo.getChildrenNm());
				memberChild.setChildrenSex(apiVo.getChildrenSex());
				memberChild.setChildrenBirthday(apiVo.getChildrenBirthday());
				memberChild.setChildrenHpNo(apiVo.getChildrenHpNo());
				memberChild.setChildrenGradeCd(apiVo.getChildrenGradeCd());
				memberChild.setOnlineServiceYn(apiVo.getOnlineServiceYn());
				memberChild.setOnlineServicePrice(apiVo.getOnlineServicePrice());
				memberChild.setOnlinePaymentWay(apiVo.getOnlinePaymentWay());
				memberChild.setOnlinePaymentMethod(apiVo.getOnlinePaymentMethod());

				memberChild.setVisitServiceYn(apiVo.getVisitServiceYn());
				memberChild.setVisitServicePrice(apiVo.getVisitServicePrice());
				memberChild.setVisitPaymentWay(apiVo.getVisitPaymentWay());
				memberChild.setVisitPaymentMethod(apiVo.getVisitPaymentMethod());


				memberChild.setVisitNumberCd(apiVo.getVisitNumberCd());
				memberChild.setVisitTimeCd(apiVo.getVisitTimeCd());			

				//2018-12-19
//				memberChild.setVisitNumberCd(apiVo.getVisitTimeCd());
//				memberChild.setVisitTimeCd(apiVo.getVisitNumberCd());

				memberChild.setCounselor1TcherCd(apiVo.getCounselor1TcherCd());
				memberChild.setCounselor1TcherHpNo(apiVo.getCounselor1TcherHpNo());
				memberChild.setCounselor2TcherCd(apiVo.getCounselor2TcherCd());
				memberChild.setCounselor2TcherHpNo(apiVo.getCounselor2TcherHpNo());

				//memberChildService.saveDetail(memberChild,member);
				memberChildService.save(memberChild);
				
				//queue insert
				String qInsert =
						" Insert into TMSG_QUEUE (TMSG_SEQ, SEND_ORG_CD, RECV_ORG_CD, TMSG_KNCD, TMSG_SECTION, TMSG_TYPE, REQ_YMD, REQ_TIME, STATUS) " +
						" Values ('" + member.getTmsgSeq() + "', 'FC001', 'DK001', 'FC101', '1', '1', CONVERT(VARCHAR, GETDATE(), 112), REPLACE(CONVERT(VARCHAR, GETDATE(), 8),':',''), '0')";
           
				jdbcTemplate.update(qInsert);
				
				
				
	        	result.setKeyCd("custCd");
	        	result.setKeyValue(apiVo.getCustCd());
				result.setResultCode("S");
				result.setResultMsg("SUCCESS");    	
			}				
		}catch(Exception e){	
			result.setResultCode("F1");
			result.setResultCode(e.getMessage());    
    	}  
		return result;
	}

    
    //인수인계요청
    @Transactional
    public ApiResultCodeVO transRequest(ApiTcherTransRequestVO apiVo) {
    	ApiResultCodeVO result = new ApiResultCodeVO();    	
    	try{
        	TcherTransManage trans = new TcherTransManage();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        String transRequestDt = sdf.format(cal.getTime());		    
        	
        	trans.setCustCd(apiVo.getCustCd());
	        trans.setChildCd(apiVo.getChildCd());    	        
	        trans.setTransRequestUserCd(apiVo.getTransRequestUserCd());
	        trans.setTransPrevUserCd(apiVo.getTransPrevUserCd());
	        
	        trans.setTransHopeDt(apiVo.getTransHopeDt());
	        trans.setTransReasonCd(apiVo.getTransReasonCd());
	        trans.setMoveDt(apiVo.getMoveDt());
	        trans.setZipcode(apiVo.getZipcode());
	        trans.setAddress1(apiVo.getAddress1());
	        trans.setAddress2(apiVo.getAddress2());
	        trans.setTransRemark(apiVo.getTransRemark());
	        trans.setRequestStartDt(apiVo.getRequestStartDt());	//첫 수업 요청일 추가 2019. 01. 04 안지호
    		
	        trans.setTransRequestDt(transRequestDt);
	        trans.setTransCd(keyManagementService.getCommonCode("TCHER_TRANS","T",4));
			
	        trans.setApprovalYn("N"); //미승인,요청상태
	        trans.setConfirmYn("N"); //인수인계미확정
			
	        tcherTransManageService.save(trans);   
	        
        	update(qMemberChild).set(qMemberChild.visitServiceStatus , "40")
        	.where(qMemberChild.custCd.eq(trans.getCustCd()).and(qMemberChild.childCd.eq(trans.getChildCd()))).execute();	     
	        
        	result.setKeyCd("transCd");
        	result.setKeyValue(trans.getTransCd());
			result.setResultCode("S");
			result.setResultMsg("SUCCESS");    	
			
    	}catch(Exception e){	
			result.setResultCode("F1");
			result.setResultCode(e.getMessage());    
    	}   	
    	return result;
	}
    

    
    //인수인계확정
    @Transactional
    public ApiResultCodeVO transSave(ApiTcherTransSaveVO apiVo) {    	
    	TcherTransManage trans = tcherTransManageService.getOne(apiVo.getTransCd());
    	ApiResultCodeVO result = new ApiResultCodeVO();   	

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String approvalDt = sdf.format(cal.getTime());		
    	
    	if(null!=trans){
        	try{        	
    	        trans.setRequestStartDt(apiVo.getRequestStartDt());
    	        trans.setRequestStartHour(apiVo.getRequestStartHour());
    	        trans.setRequestStartTime(apiVo.getRequestStartTime());
    	        trans.setTransRemark(apiVo.getTransRemark());
    	        trans.setApprovalUserCd(apiVo.getApprovalUserCd());
    	        
    	        trans.setApprovalDt(approvalDt);
    	        trans.setApprovalYn("Y"); //승인
    	        trans.setConfirmYn("Y"); //인수인계확정
    			
    	        tcherTransManageService.save(trans);   

	        	update(qMemberChild).set(qMemberChild.visitServiceStatus , "10")
	        	.where(qMemberChild.custCd.eq(trans.getCustCd())
	        			.and(qMemberChild.childCd.eq(trans.getChildCd()))).execute();
	        	
	        	//배정 테이블 insert
	            TcherAssignManage assign = new TcherAssignManage();
	            assign.setCustCd(trans.getCustCd());
	            assign.setChildCd(trans.getChildCd());
	            assign.setAssignRequestUserCd(apiVo.getApprovalUserCd());     
	            
	            assign.setAssignRemark(trans.getTransRemark());
	            assign.setAssignUserCd(trans.getTransUserCd());        
	            assign.setAssignType("20");        
	            
	            tcherAssignManageService.saveAssign(assign);
        	 
            	result.setKeyCd("transCd");
            	result.setKeyValue(trans.getTransCd());
    			result.setResultCode("S");
    			result.setResultMsg("SUCCESS");    	
    			
        	}catch(Exception e){	
    			result.setResultCode("F1");
    			result.setResultCode(e.getMessage());    
        	}   	
    	}else{

			result.setResultCode("F2");
			result.setResultCode("존재하지 않는 인수인계코드 입니다.");    
    	}
    	return result;
	}
    

    //학습휴식신청
    @Transactional
    public ApiResultCodeVO restRequest(ApiTcherRestRequestVO apiVo) {
    	ApiResultCodeVO result = new ApiResultCodeVO();    	
    	try{
        	TcherRestManage rest = new TcherRestManage();
        	
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        String restRequestDt = sdf.format(cal.getTime());	

	        rest.setCustCd(apiVo.getCustCd());
        	rest.setChildCd(apiVo.getChildCd());    	   
	        rest.setRestRequestUserCd(apiVo.getRestRequestUserCd());
	        rest.setRequestYear(apiVo.getRequestYear());
	        rest.setRequestMonth(apiVo.getRequestMonth());
        	rest.setRestReasonCd(apiVo.getRestReasonCd());
        	rest.setRestRemark(apiVo.getRestRemark());
	    	rest.setRestRequestDt(restRequestDt);
	    	rest.setRestCd(keyManagementService.getCommonCode("TCHER_REST","T",4));
	    	rest.setApprovalYn("N"); //미승인,요청상태
	    	rest.setConfirmYn("N"); //인수인계미확정
			
        	tcherRestManageService.save(rest);   
        	
    		update(qMemberChild).set(qMemberChild.visitServiceStatus , "80")
    		.where(qMemberChild.custCd.eq(rest.getCustCd()).and(qMemberChild.childCd.eq(rest.getChildCd()))).execute();
        	
        	
        	result.setKeyCd("restCd");
        	result.setKeyValue(rest.getRestCd());
			result.setResultCode("S");
			result.setResultMsg("SUCCESS");    	
			
    	}catch(Exception e){	
			result.setResultCode("F1");
			result.setResultCode(e.getMessage());    
    	}   	
    	return result;
	}

    //학습휴식신청(승인)
    @Transactional
    public ApiResultCodeVO restSave(ApiTcherRestSaveVO apiVo) {
    	TcherRestManage rest = tcherRestManageService.getOne(apiVo.getRestCd());
    	ApiResultCodeVO result = new ApiResultCodeVO();    	

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String approvalDt = sdf.format(cal.getTime());		
        
    	if(null!=rest){
    		try{
    			
            	rest.setRequestYear(apiVo.getRequestYear());
            	rest.setRequestMonth(apiVo.getRequestMonth());
            	rest.setRestReasonCd(apiVo.getRestReasonCd());
            	rest.setRestRemark(apiVo.getRestRemark());
            	rest.setApprovalUserCd(apiVo.getApprovalUserCd());
            	
    			
            	rest.setApprovalDt(approvalDt);
            	rest.setApprovalYn("Y"); //승인
            	rest.setConfirmYn("Y"); //인수인계확정
    			
            	tcherRestManageService.save(rest);   
        	 
        		update(qMemberChild).set(qMemberChild.visitServiceStatus , "70")
        		.where(qMemberChild.custCd.eq(rest.getCustCd()).and(qMemberChild.childCd.eq(rest.getChildCd()))).execute();
            
            	
            	result.setKeyCd("restCd");
            	result.setKeyValue(rest.getRestCd());
    			result.setResultCode("S");
    			result.setResultMsg("SUCCESS");    	
    			
        	}catch(Exception e){	
    			result.setResultCode("F1");
    			result.setResultCode(e.getMessage());    
        	}   	
    		
    	}else{
			result.setResultCode("F2");
			result.setResultCode("존재하지 않는 학습휴식코드 입니다.");  
    	}
    	return result;
	}
    

    //학습휴식신청(취소)
    @Transactional
    public ApiResultCodeVO restCancel(ApiTcherRestSaveVO apiVo) {
    	TcherRestManage rest = tcherRestManageService.getOne(apiVo.getRestCd());
    	ApiResultCodeVO result = new ApiResultCodeVO();    	

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String approvalDt = sdf.format(cal.getTime());		
        
    	if(null!=rest){
    		try{
    			
            	rest.setRequestYear(apiVo.getRequestYear());
            	rest.setRequestMonth(apiVo.getRequestMonth());
            	rest.setRestReasonCd(apiVo.getRestReasonCd());
            	rest.setRestRemark(apiVo.getRestRemark());
            	rest.setApprovalUserCd(apiVo.getApprovalUserCd());
            	
    			
            	rest.setApprovalDt(approvalDt);
            	rest.setApprovalYn("N"); //승인
            	rest.setConfirmYn("N"); //인수인계확정
            	tcherRestManageService.save(rest);   
        	 
        		update(qMemberChild).set(qMemberChild.visitServiceStatus , "10")
        		.where(qMemberChild.custCd.eq(rest.getCustCd()).and(qMemberChild.childCd.eq(rest.getChildCd()))).execute();
            
            	
            	result.setKeyCd("restCd");
            	result.setKeyValue(rest.getRestCd());
    			result.setResultCode("S");
    			result.setResultMsg("SUCCESS");    	
    			
        	}catch(Exception e){	
    			result.setResultCode("F1");
    			result.setResultCode(e.getMessage());    
        	}   	
    		
    	}else{
			result.setResultCode("F2");
			result.setResultCode("존재하지 않는 학습휴식코드 입니다.");  
    	}
    	return result;
	}
    
    //교사배정
    @Transactional
    public ApiResultCodeVO assignSave(ApiTcherAssignSaveVO apiVo) {
    	ApiResultCodeVO result = new ApiResultCodeVO();   
		try{
			//기존 배정목록  N 처리
			tcherAssignManageService.updatePreAssign(apiVo.getCustCd(), apiVo.getChildCd());
			
        	TcherAssignManage assign = new TcherAssignManage();
        	assign.setAssignCd(keyManagementService.getCommonCode("TCHER_ASSIGN","TS",4));
        	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        
	        String approvalDt = sdf.format(cal.getTime());		
	        
        	assign.setCustCd(apiVo.getCustCd());
        	assign.setChildCd(apiVo.getChildCd());    	     
        	assign.setAssignRequestUserCd(apiVo.getAssignRequestUserCd());
        	assign.setAssignRemark(apiVo.getAssignRemark());
        	assign.setAssignUserCd(apiVo.getAssignUserCd());
        	assign.setAssignType(apiVo.getAssignType());
        	assign.setAssignRequestDt(approvalDt);
        	assign.setApprovalDt(approvalDt);
        	assign.setApprovalUserCd(apiVo.getAssignRequestUserCd());
        	assign.setApprovalYn("Y"); //승인
        	assign.setConfirmYn("Y"); //인수인계확정
        	assign.setLastYn("Y"); //승인
        	assign.setAssignType("10"); //배정구분 10 일반
        	
        	tcherAssignManageService.save(assign);   
    	 
        	if(isNotEmpty(assign.getAssignUserCd())){ 
    	    	memberChildService.updateVisitorTcherCd(assign.getCustCd(),assign.getChildCd(),assign.getAssignUserCd());
    	    }
        	
        	result.setKeyCd("assignCd");
        	result.setKeyValue(assign.getAssignCd());
			result.setResultCode("S");
			result.setResultMsg("SUCCESS");    	
			
    	}catch(Exception e){	
			result.setResultCode("F1");
			result.setResultCode(e.getMessage());    
    	}  
    	
    	return result;
	}
    
    /*
     * 아이디/비밀번호찾기
     */

    public ApiUserVO findUser(RequestParams requestParams) {
        String userPs = requestParams.getString("userPs" , "");      
        if(isNotEmpty(userPs)){
    	    String password = bCryptPasswordEncoder.encode(userPs);
    	    requestParams.put("userPs" , password);
        } 
    	return apiMapper.findUser(requestParams);
    }
    

    //비밀번호수정
    @Transactional
    public ApiResultCodeVO changePs(RequestParams requestParams) throws Exception {
        String userCd = requestParams.getString("userCd" , "");      
        String userNm = requestParams.getString("userNm" , "");     
        String userPs = requestParams.getString("userPs" , "");     

        requestParams.put("userPs" , "");    
        ApiUserVO user =  apiMapper.findUser(requestParams);
        String resultCode="";
    	String resultMsg=""; 
    	
    	
    	if (null != user){
    	    String password = bCryptPasswordEncoder.encode(userPs);  
    	    
            update(qUser)
            .set(qUser.userPs , password).set(qUser.userPs2 , userPs)
            .set(qUser.lastLoginDate, Instant.now(Clock.systemUTC()))
            .where(qUser.userCd.eq(userCd).and(qUser.userNm.eq(userNm))).execute();   
            
        	resultCode="S";
        	resultMsg="SUCCESS"; 	
    	}else{
        	resultCode="F1";
        	resultMsg="사용자가 없습니다.";
    	}
    	
    	
    	ApiResultCodeVO apiResult = new ApiResultCodeVO();  
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);    	
    	
    	return apiResult;
    	
    }
    

    @Transactional
    public ApiResultCodeVO saveSend(ApiSmsMasterVO master){      	
		ApiResultCodeVO result = new ApiResultCodeVO(); 
    	try{ 
        	if (null!=master) {  
        		String reserveTime = "";
        		//즉시발송 - 현재시간
        		if(master.getSendType().equals("10")){
    	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	            Calendar c1 = Calendar.getInstance();
    	            String strToday = sdf.format(c1.getTime());
    	            master.setSendDt(strToday);
        		}else{
        			
        			
        			try{
            			String oldstring = master.getSendDt();
            			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);
            			
            			Calendar cal = Calendar.getInstance();
            			cal.setTime(date);
            			cal.add(Calendar.HOUR_OF_DAY, -9);

            			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            			reserveTime = format.format(cal.getTime());
            			
        			}catch(Exception e){
        				
        			}
        		}
    	            
        		String successYn = "Y";
        		String sendCode = "";    		        		
    			sendCode = keyManagementService.getItemCode("SMS","SMS",5);
    			
    			SmsSendUtils.sendMsg(master.getSenderNo(), master.getHpNo(), master.getSendMessage(),reserveTime);

        		Long seq = new Long(1);
    			SendDetail d = new SendDetail();
    			d.setSendCode(sendCode);
    			d.setSendSeq(seq);
    			d.setUserCd(master.getUserCd());
    			d.setHpNo(master.getHpNo());
    			d.setSuccessYn(successYn);
    			sendDetailService.save(d);

    			SendMaster m = new SendMaster();
    			m.setSendCode(sendCode);
    			m.setSendType(master.getSendType());
    			m.setSmsType(master.getSmsType());
    			m.setSendDt(master.getSendDt());
    			m.setSendMessage(master.getSendMessage());
    			m.setSenderUserCd(master.getSenderUserCd());
    			m.setSenderNo(master.getSenderNo());
    			m.setTotalByte(master.getTotalByte());	
            	sendMasterService.save(m);

    			result.setResultCode("S");
    			result.setResultMsg("SUCCESS");    		
        	}        	
    	}catch(Exception e){
			result.setResultCode("F1");
			result.setResultMsg(e.getMessage());
		}
    	return result;
    }

    //sms 전송 조회
    public ApiResultObjectPagingVO getSendList(RequestParams<ApiSendMasterVO> vo) {
    	int pageNumber = vo.getInt("pageNumber",1);    	


    	String orderColumn = vo.getString("orderColumn","");
    	String orderType = vo.getString("orderType","");
    	String orderType2 = "";
    	
    	if(orderColumn.equals("")){
    		orderColumn = "A.UPDATED_AT";
    	} else if (orderColumn.equals("hpNo")) {
			orderColumn = "B.HP_NO";
		} else if (orderColumn.equals("sendUserNm")) {
			orderColumn = "D.USER_NM";
		} else if (orderColumn.equals("sendDt")) {
			orderColumn = "A.SEND_DT";
		} else if (orderColumn.equals("sendType")) {
			orderColumn = "A.SEND_TYPE";
		} else if (orderColumn.equals("sendMessage")) {
			orderColumn = "A.SEND_MESSAGE";
		} else if (orderColumn.equals("successYn")) {
			orderColumn = "B.SUCCESS_YN";
		}
    	
    	if(orderType.equals("")){
    		orderType = "asc";
    	}

    	if(orderType.equals("asc")){
    		orderType2 = "desc";
    	}else{
    		orderType2 = "asc";
    	}

    	vo.put("orderColumn" ,orderColumn);  
    	vo.put("orderType" ,orderType);  
    	vo.put("orderType2" ,orderType2);      	
    	vo.put("pageNumber" ,pageNumber);
    	
    	ApiResultObjectPagingVO result = new ApiResultObjectPagingVO();
    	List<ApiSendMasterVO> list = apiMapper.getSendList(vo);
    	result.setResult(list);   
    	
    	//현재페이지    	
    	result.setPageNumber(pageNumber);         	  
    	int totalCnt = apiMapper.getSendListCount(vo);    	
    	result.setTotalCnt(totalCnt);    	
    	
    	String resultCode;
    	String resultMsg;
    	
    	if (list.size() > 0){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "결과가 없습니다.";
    	}    	
    	
    	result.setResultCode(resultCode);
    	result.setResultMsg(resultMsg);   	
    	return result;
    }
    
    /*
     * 교사별 학생정보
     */


    //인수인계목록
    public List<ApiTcherTransManageResponseVO> getTransMemberList(RequestParams vo) {
    	List<ApiTcherTransManageResponseVO> details = apiMapper.getTransMemberList(vo);    	
    	return details;
    }

    //인수인계상세
    public ApiTcherTransManageResponseVO getTransMemberDetail(RequestParams vo) {
    	ApiTcherTransManageResponseVO detail = apiMapper.getTransMemberDetail(vo);    	
    	return detail;
    }

    public List<ApiTcherAssignManageResponseVO> getAssignList(RequestParams vo) {

    	String orderColumn = vo.getString("orderColumn","");
    	String orderType = vo.getString("orderType","");
    	String orderType2 = "";
    	
    	if(orderColumn.equals("")){
    		orderColumn = "D.UPDATED_AT";
    	}
    	
    	if(orderType.equals("")){
    		orderType = "asc";
    	}

    	if(orderType.equals("asc")){
    		orderType2 = "desc";
    	}else{
    		orderType2 = "asc";
    	}

    	vo.put("orderColumn" ,orderColumn);  
    	vo.put("orderType" ,orderType);  
    	vo.put("orderType2" ,orderType2);  
    	
    	List<ApiTcherAssignManageResponseVO> details = apiMapper.getAssignList(vo);    	
    	return details;
    }
 
    
    //휴식목록
    public List<ApiTcherRestResponseVO> getRestList(RequestParams vo) {
    	List<ApiTcherRestResponseVO> details = apiMapper.getRestList(vo);    	
    	return details;
    }
    
    //교사목록
    public List<ApiTcherManageVO> getTcherList(RequestParams vo) {
    	List<ApiTcherManageVO> details = apiMapper.getTcherList(vo);    	
    	return details;
    }
    
    //공지조회
    public ApiResultObjectPagingVO getNoticeList(RequestParams vo) {

    	String goodsCd = vo.getString("goodsCd","");
    	int pageNumber = 1;
    	
    	if(goodsCd.equals("")){
    		pageNumber = vo.getInt("pageNumber",1);
    	}    	
    	
    	vo.put("pageNumber" ,pageNumber);

    	ApiResultObjectPagingVO result = new ApiResultObjectPagingVO();
    	List<ApiNoticeManageVO> list = apiMapper.getNoticeList(vo);

    	result.setResult(list);
    	
    	//현재페이지
    	result.setPageNumber(pageNumber);         	  
    	int totalCnt = apiMapper.getNoticeListCount(vo);    	
    	result.setTotalCnt(totalCnt);    	
    	
    	String resultCode;
    	String resultMsg;
    	
    	if (list.size() > 0){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "결과가 없습니다.";
    	}    	
    	
    	result.setResultCode(resultCode);
    	result.setResultMsg(resultMsg);   	
    	return result;
    	
    }

	//공지조회(전체 + 방문,상담)
	public ApiResultObjectPagingVO getNewNoticeList(RequestParams vo) {

		String goodsCd = vo.getString("goodsCd","");
		int pageNumber = 1;

		if(goodsCd.equals("")){
			pageNumber = vo.getInt("pageNumber",1);
		}

		vo.put("pageNumber" ,pageNumber);

		ApiResultObjectPagingVO result = new ApiResultObjectPagingVO();
		List<ApiNoticeManageVO> list = apiMapper.getNoticeList(vo);	//상담, 방문 공지사항
		List<ApiNoticeManageVO> list2 = apiMapper.getNoticeListTypeAll(vo);	//전체 공지사항

		List<ApiNoticeManageVO> list3 = CommonUtils.mergeTwoList(list2, list);	//두개 리스트 Merge

		result.setResult(list3);

		//현재페이지
		result.setPageNumber(pageNumber);

		int totalCntTypeAll = apiMapper.getNoticeListCountTypeAll();	//전체공지사항 개수
		int totalCnt = apiMapper.getNoticeListCount(vo);
		result.setTotalCnt(totalCntTypeAll + totalCnt);

		String resultCode;
		String resultMsg;

		if (list.size() > 0){
			resultCode = "S";
			resultMsg = "SUCCESS";
		}else{
			resultCode = "F1";
			resultMsg = "결과가 없습니다.";
		}

		result.setResultCode(resultCode);
		result.setResultMsg(resultMsg);
		return result;

	}

    //오늘의알림
    public List<ApiTodayArmVO> getTodayArm(RequestParams vo) {
    	List<ApiTodayArmVO> details = apiMapper.getTodayArm(vo);    	
    	return details;
    }
    
    //자녀사진정보저장
    @Transactional
    public ApiResultObjectVO childImageSave(RequestParams<Object> requestParams) throws Exception {
        String custCd = requestParams.getString("custCd" , "");      
        String childCd = requestParams.getString("childCd" , "");     
        String childImgUrl = requestParams.getString("childImgUrl" , "");     
        String childImgRegDt = requestParams.getString("childImgRegDt" , "");     
        String childImgBy = requestParams.getString("childImgBy" , "");     

        
        RequestParams<Object> params = new RequestParams<>(Object.class);
        params.put("custCd" , custCd);            
        params.put("childCd" , childCd);            
        ApiMemberChildVO child =  apiMapper.getMemberChild(params);
        
        String resultCode="";
    	String resultMsg="";     	
    	
    	if (null != child){
    		
    		update(qMemberChild)
    		.set(qMemberChild.childImgUrl , childImgUrl)
    		.set(qMemberChild.childImgRegDt , childImgRegDt)
    		.set(qMemberChild.childImgBy , childImgBy)
            .where(qMemberChild.custCd.eq(custCd).and(qMemberChild.childCd.eq(childCd))).execute();   
    		
        	resultCode="S";
        	resultMsg="SUCCESS"; 	
    	}else{
        	resultCode="F1";
        	resultMsg="사용자가 없습니다.";
    	}
    	
    	ApiResultObjectVO apiResult = new ApiResultObjectVO();  
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);    	    	
    	return apiResult;    	
    }

  //형제추가
    @Transactional
    public ApiResultObjectVO elctrnCtrtcBrotherInsert(ApiBrotherRequestVO vo) throws Exception {

        String resultCode="";
    	String resultMsg="";     	
    	
    	String custCd = vo.getCustCd();
    	String childCd = vo.getChildCd(); 
    	String brothers = vo.getBrothers();     	
    	
    	RequestParams<Object> params = new RequestParams<>(Object.class);        
        params.put("custCd" , custCd);            
        params.put("childCd" , childCd);            
        ApiMemberChildVO child =  apiMapper.getMemberChild(params);
    	
        if (null != child){
        	
        	delete(qMemberBrother)
            .where(qMemberBrother.custCd.eq(custCd)
           		 .and(qMemberBrother.childCd.eq(childCd))).execute();
        	
        	String[] bArray = null;
        	
        	if(isNotEmpty(brothers)){
        		bArray = brothers.split("[;]");

            	String[] aArray = null;

            	if(null != bArray){
	           	 	for (String b : bArray) {
	           	 		aArray = b.split("[,]");
	                	if(null != aArray && aArray.length == 4){
	                		MemberBrother d = new MemberBrother();
			         		d.setCustCd(custCd);
			         		d.setChildCd(childCd);
			         		d.setBrotherCd(keyManagementService.getItemCode("BROTHER","B",5));
			         		d.setBrotherNm(aArray[0]);
			         		d.setBrotherSex(aArray[1]);
			     			d.setBrotherBirthday(aArray[2]);
			     			d.setBrotherGradeCd(aArray[3]);
			         		memberBrotherService.save(d);

			            	resultCode="S";
			            	resultMsg="SUCCESS"; 	
	                	}else{
	                    	resultCode="F2";
	                    	resultMsg="값이 불충분합니다.";
	                	}
	           	 	}
            	}else{
                	resultCode="F3";
                	resultMsg="값이 불충분합니다.";
            	}
        	}
        }else{
        	resultCode="F1";
        	resultMsg="사용자가 없습니다.";
        }
        
    	ApiResultObjectVO apiResult = new ApiResultObjectVO();  
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);    	    	
    	return apiResult;    	
    }
    
    //형제수정
    @Transactional
    public ApiResultObjectVO elctrnCtrtcBrotherUpdate(RequestParams<Object> requestParams) throws Exception {
        String custCd = requestParams.getString("custCd" , "");      
        String childCd = requestParams.getString("childCd" , "");     

        String brotherCd = requestParams.getString("brotherCd" , "");    
        String brotherNm = requestParams.getString("brotherNm" , "");     
        String brotherSex = requestParams.getString("brotherSex" , "");     
        String brotherBirthday = requestParams.getString("brotherBirthday" , "");     
        String brotherGradeCd = requestParams.getString("brotherGradeCd" , "");     
        
        RequestParams<Object> params = new RequestParams<>(Object.class);
        params.put("custCd" , custCd);            
        params.put("childCd" , childCd);            
        ApiMemberChildVO child =  apiMapper.getMemberChild(params);
        
        String resultCode="";
    	String resultMsg="";     	
    	
    	if (null != child){
    		 update(qMemberBrother)
    		 .set(qMemberBrother.brotherNm , brotherNm)    		 
    		 .set(qMemberBrother.brotherSex , brotherSex)    		 
    		 .set(qMemberBrother.brotherBirthday , brotherBirthday)    		 
    		 .set(qMemberBrother.brotherGradeCd , brotherGradeCd)    

             .where(qMemberBrother.custCd.eq(custCd)
            		 .and(qMemberBrother.childCd.eq(childCd))
            		 .and(qMemberBrother.brotherCd.eq(brotherCd))).execute();   
    		 
    		
        	resultCode="S";
        	resultMsg="SUCCESS"; 	
    	}else{
        	resultCode="F1";
        	resultMsg="사용자가 없습니다.";
    	}
    	
    	ApiResultObjectVO apiResult = new ApiResultObjectVO();  
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);    	    	
    	return apiResult;    	
    }
    
    //상담교사 - 회원정보변경 수정
    @Transactional
    public ApiResultObjectVO elctrnCtrtcUpdate(RequestParams<Object> requestParams) throws Exception {
	    
        String custCd = requestParams.getString("custCd" , "");      
        String childCd = requestParams.getString("childCd" , "");     
        String childImgUrl = requestParams.getString("childImgUrl" , "");     
        String childrenNm = requestParams.getString("childrenNm" , "");     
        String childrenSex = requestParams.getString("childrenSex" , "");     

        String childrenBirthday = requestParams.getString("childrenBirthday" , "");     
        String childrenHpNo = requestParams.getString("childrenHpNo" , "");     
        String childrenSchoolNm = requestParams.getString("childrenSchoolNm" , "");     
        String childrenGradeCd = requestParams.getString("childrenGradeCd" , "");    

        String gd1Nm = requestParams.getString("gd1Nm" , "");     
        String gd1RelationCd = requestParams.getString("gd1RelationCd" , "");     
        String hpNo = requestParams.getString("hpNo" , "");     
        String gd2Nm = requestParams.getString("gd2Nm" , "");     
        String gd2RelationCd = requestParams.getString("gd2RelationCd" , "");     
        

        String telNo = requestParams.getString("telNo" , "");     
        String email = requestParams.getString("email" , "");     
        String homeZipcode = requestParams.getString("homeZipcode" , "");     
        String homeAddress1 = requestParams.getString("homeAddress1" , "");     
        String homeAddress2 = requestParams.getString("homeAddress2" , "");     

        
        RequestParams<Object> params = new RequestParams<>(Object.class);
        params.put("custCd" , custCd);            
        params.put("childCd" , childCd);            
        ApiMemberChildVO child =  apiMapper.getMemberChild(params);
        
        String resultCode="";
    	String resultMsg="";     	
    	
    	if (null != child){
    		
    		 update(qMemberManage)
    		 .set(qMemberManage.gd1Nm , gd1Nm)    		 
    		 .set(qMemberManage.gd1RelationCd , gd1RelationCd)    		 
    		 .set(qMemberManage.hpNo , hpNo)    		 
    		 .set(qMemberManage.gd2Nm , gd2Nm)    		 
    		 .set(qMemberManage.gd2RelationCd , gd2RelationCd)   

    		 .set(qMemberManage.telNo , telNo)   
    		 .set(qMemberManage.email , email)   
    		 .set(qMemberManage.homeZipcode , homeZipcode)   
    		 .set(qMemberManage.homeAddress1 , homeAddress1)   
    		 .set(qMemberManage.homeAddress2 , homeAddress2)   
    		 .where(qMemberManage.custCd.eq(custCd)).execute();

    		 update(qMemberChild)
     		.set(qMemberChild.childImgUrl , childImgUrl)
     		.set(qMemberChild.childrenNm , childrenNm)
     		.set(qMemberChild.childrenSex , childrenSex)

     		.set(qMemberChild.childrenBirthday , childrenBirthday)
     		.set(qMemberChild.childrenHpNo , childrenHpNo)
     		.set(qMemberChild.childrenSchoolNm , childrenSchoolNm)
     		.set(qMemberChild.childrenGradeCd , childrenGradeCd)
             .where(qMemberChild.custCd.eq(custCd).and(qMemberChild.childCd.eq(childCd))).execute();   
    		 
    		 
    		 update(qUser)
    		 .set(qUser.userNm , childrenNm)
    		 .set(qUser.hpNo , childrenHpNo)    		 
    		 .where(qUser.userCd.eq(child.getChildrenUserCd())).execute();
    		 
    		
        	resultCode="S";
        	resultMsg="SUCCESS"; 	
    	}else{
        	resultCode="F1";
        	resultMsg="사용자가 없습니다.";
    	}
    	
    	ApiResultObjectVO apiResult = new ApiResultObjectVO();  
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);    	    	
    	return apiResult;    	
    }
    
    //방문교육일 등록
    public void saveVisitDt(RequestParams vo){    	
    	String childCd = vo.getString("childCd" ,"");
    	String startDt = vo.getString("startDt" ,"");
    	String endDt = vo.getString("endDt" ,"");
    	
    	 update(qMemberChild)
		 .set(qMemberChild.visitStartDt , startDt)
		 .set(qMemberChild.visitEndDt , endDt)
		 .where(qMemberChild.childCd.eq(childCd)).execute();

    }
    
    //개인정보수정 (학습자)
    @Transactional
    public ApiMemberModCustVO modCust(ApiMemberModCustVO requestVO){    	
    	if(isNotEmpty(requestVO.getGd1UserCd())){    		
    		 update(qMemberManage)
    		 .set(qMemberManage.gd1Nm , requestVO.getGd1Nm())    		 
    		 .set(qMemberManage.gd1RelationCd , requestVO.getGd1RelationCd())
    		 .set(qMemberManage.hpNo , requestVO.getHpNo())
    		 .set(qMemberManage.homeZipcode , requestVO.getHomeZipcode())
    		 .set(qMemberManage.homeAddress1 , requestVO.getHomeAddress1())
    		 .set(qMemberManage.homeAddress2 , requestVO.getHomeAddress2())
    		 .set(qMemberManage.deliveryZipcode , requestVO.getDeliveryZipcode())
    		 .set(qMemberManage.deliveryAddress1 , requestVO.getDeliveryAddress1())
    		 .set(qMemberManage.deliveryAddress2 , requestVO.getDeliveryAddress2())
    		 .where(qMemberManage.gd1UserCd.eq(requestVO.getGd1UserCd())).execute();

    		 update(qUser)
    		 .set(qUser.zipcode , requestVO.getHomeZipcode())
    		 .set(qUser.address1 , requestVO.getHomeAddress1())
    		 .set(qUser.address2 , requestVO.getHomeAddress2())
    		 .set(qUser.userNm , requestVO.getGd1Nm())
    		 .set(qUser.hpNo , requestVO.getHpNo())    		 
    		 .where(qUser.userCd.eq(requestVO.getGd1UserCd())).execute();
    		 
    	}    	
    	if(isNotEmpty(requestVO.getChildrenUserCd())){
    		
    		 update(qMemberChild)
    		 .set(qMemberChild.childrenNm , requestVO.getChildrenNm())
    		 .set(qMemberChild.childrenHpNo , requestVO.getChildrenHpNo())
    		 .set(qMemberChild.childrenSchoolNm , requestVO.getChildrenSchoolNm())
    		 .where(qMemberChild.childrenUserCd.eq(requestVO.getChildrenUserCd())).execute();

    		 update(qUser)
    		 .set(qUser.userNm , requestVO.getChildrenNm())
    		 .set(qUser.hpNo , requestVO.getChildrenHpNo())
    		 .where(qUser.userCd.eq(requestVO.getChildrenUserCd())).execute();
    		 
    	}        
    	return requestVO;
    }
    
    //개인정보수정 (방문앱)
    @Transactional
    public ApiMemberModifyCustVO modifyCust(ApiMemberModifyCustVO requestVO){    	
    	if(isNotEmpty(requestVO.getGd1UserCd())){
    		
    	    
    	    
    		 update(qMemberManage)
    		 .set(qMemberManage.gd1Nm , requestVO.getGd1Nm())    		 
    		 .set(qMemberManage.gd1RelationCd , requestVO.getGd1RelationCd())
    		 .set(qMemberManage.gd1Birthday , requestVO.getGd1Birthday())    		 
    		 .set(qMemberManage.gd2Nm , requestVO.getGd2Nm())    		 
    		 .set(qMemberManage.gd2RelationCd , requestVO.getGd2RelationCd())
    		 .set(qMemberManage.gd2Birthday , requestVO.getGd2Birthday())    		    
    		 .set(qMemberManage.hpNo , requestVO.getHpNo())	    
    		 .set(qMemberManage.telNo , requestVO.getTelNo())	    
    		 .set(qMemberManage.email , requestVO.getEmail())
    		 .set(qMemberManage.homeZipcode , requestVO.getHomeZipcode())
    		 .set(qMemberManage.homeAddress1 , requestVO.getHomeAddress1())
    		 .set(qMemberManage.homeAddress2 , requestVO.getHomeAddress2())
    		 .set(qMemberManage.deliveryZipcode , requestVO.getDeliveryZipcode())
    		 .set(qMemberManage.deliveryAddress1 , requestVO.getDeliveryAddress1())
    		 .set(qMemberManage.deliveryAddress2 , requestVO.getDeliveryAddress2())
    		 .where(qMemberManage.gd1UserCd.eq(requestVO.getGd1UserCd())).execute();

    		 update(qUser)
    		 .set(qUser.zipcode , requestVO.getHomeZipcode())
    		 .set(qUser.address1 , requestVO.getHomeAddress1())
    		 .set(qUser.address2 , requestVO.getHomeAddress2())
    		 .set(qUser.userNm , requestVO.getGd1Nm())
    		 .set(qUser.hpNo , requestVO.getHpNo())
			 .set(qUser.telNo, requestVO.getTelNo())
    		 .where(qUser.userCd.eq(requestVO.getGd1UserCd())).execute();
    		 
    		 
    	}    	
    	if(isNotEmpty(requestVO.getChildrenUserCd())){
    	    
    		 update(qMemberChild)
    		 .set(qMemberChild.childrenNm , requestVO.getChildrenNm())
    		 .set(qMemberChild.childrenHpNo , requestVO.getChildrenHpNo())
    		 .set(qMemberChild.childrenBirthday , requestVO.getChildrenBirthday())
    		 .set(qMemberChild.childrenGradeCd , requestVO.getChildrenGradeCd())
    		 .set(qMemberChild.childrenSchoolNm , requestVO.getChildrenSchoolNm())
    		 .where(qMemberChild.childrenUserCd.eq(requestVO.getChildrenUserCd())).execute();
    		 

    		 update(qUser)
    		 .set(qUser.userNm , requestVO.getChildrenNm())
    		 .set(qUser.hpNo , requestVO.getChildrenHpNo())
			 .set(qUser.telNo, requestVO.getTelNo())
    		 .where(qUser.userCd.eq(requestVO.getChildrenUserCd())).execute();
    		 
    	}        
    	return requestVO;
    }
    

    //비밀번호수정
    @Transactional
    public ApiResultCodeVO tcherInfoChange(RequestParams<Object> requestParams) throws Exception {
    	
        String tcherCd = requestParams.getString("tcherCd" , "");      
        String tcherBirthday = requestParams.getString("tcherBirthday" , "");     
        String tcherZipcode = requestParams.getString("tcherZipcode" , "");     
        String tcherAddress1 = requestParams.getString("tcherAddress1" , "");     
        String tcherAddress2 = requestParams.getString("tcherAddress2" , "");     
        String userPs = requestParams.getString("userPs" , "");             

        RequestParams<ApiUserVO> params = new RequestParams<>(ApiUserVO.class);
        params.put("userCd" , tcherCd);            
        ApiUserVO user =  apiMapper.findUser(params);
        
        String resultCode="";
    	String resultMsg="";     	
    	
    	if (null != user){
    		
    		update(qTcherManage)
    		.set(qTcherManage.tcherBirthday , tcherBirthday)
    		.set(qTcherManage.tcherZipcode , tcherZipcode)
    		.set(qTcherManage.tcherAddress1 , tcherAddress1)
    		.set(qTcherManage.tcherAddress2 , tcherAddress2)
            .where(qTcherManage.tcherCd.eq(tcherCd)).execute();   
    		
    	    String password = bCryptPasswordEncoder.encode(userPs);     
    	    
            update(qUser)
            .set(qUser.birthday , tcherBirthday)
            .set(qUser.zipcode , tcherZipcode)
            .set(qUser.address1 , tcherAddress1)
            .set(qUser.address2 , tcherAddress2)            
            .set(qUser.userPs , password)
            .set(qUser.userPs2 , userPs)
            .set(qUser.lastLoginDate, Instant.now(Clock.systemUTC()))
            .where(qUser.userCd.eq(tcherCd)).execute();   
            
        	resultCode="S";
        	resultMsg="SUCCESS"; 	
    	}else{
        	resultCode="F1";
        	resultMsg="사용자가 없습니다.";
    	}
    	
    	ApiResultCodeVO apiResult = new ApiResultCodeVO();  
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);    	    	
    	return apiResult;    	
    }

    /**
     * 학부모, 자녀 로그인 정보 저장하기
     * 작성자 : 안지호
     * 작성일 : 2019. 01. 04
     * @param requestParams
     * @return
     * @throws Exception
     */
    @Transactional
	public ApiResultCodeVO saveUserLoginInfo(RequestParams<Object> requestParams) throws Exception {
		String resultCode="";
		String resultMsg="";
        //학부모 정보 조회
		String custCd = requestParams.getString("custCd" , "");
		String parentLoginId = requestParams.getString("parentLoginId" , "");
		String parentPassword = requestParams.getString("parentPassword" , "");
		MemberManageVO memberManageVO = memberManageService.getMemberByCustCd(custCd);

		//자녀 정보 조회
		String childCd = requestParams.getString("childCd" , "");
		String childLoginId = requestParams.getString("childLoginId" , "");
		String childPassword = requestParams.getString("childPassword" , "");
		MemberManageVO childManageVO = memberManageService.getMemberChildrenChildCd(childCd);

		if (memberManageVO != null) {
			User user = new User();
			user.setUserCd(parentLoginId);
			user.setUserPs(bCryptPasswordEncoder.encode(parentPassword));
			user.setUserPs2(parentPassword);
			user.setDecisionYn(N);
			user.setUserNm(memberManageVO.getGd1Nm());
			user.setHpNo(memberManageVO.getHpNo());
			user.setTelNo(memberManageVO.getTelNo());
			user.setCustCd(custCd);
            //학부모 로그인 정보 저장
			userService.saveMember(user, NEW);
		}

		if (childManageVO != null) {
			User childUser = new User();
			childUser.setUserCd(childLoginId);
			childUser.setUserPs(bCryptPasswordEncoder.encode(childPassword));
			childUser.setUserPs2(childPassword);
			childUser.setDecisionYn(N);
			childUser.setUserNm(childManageVO.getChildrenNm());
			childUser.setHpNo(childManageVO.getChildrenHpNo());
			childUser.setCustCd(custCd);
            //자녀 로그인 정보 저장
			userService.saveChildren(childUser, NEW);
		}

		resultCode = S;
		resultMsg = SUCCESS;

		ApiResultCodeVO apiResult = new ApiResultCodeVO();
		apiResult.setResultCode(resultCode);
		apiResult.setResultMsg(resultMsg);
		return  apiResult;
	}

	/**
	 * kicc 결제 결과 정보 저장하기
	 * 작성자 : 안지호
	 * 작성일 : 2019. 01. 10
	 * @param vo
	 * @return
	 */
	@Transactional
	public ApiResultCodeVO saveKiccPaymentResult(ApiKiccPaymentResultSaveVO vo) {
		String resultCode = "";
		String resultMsg = "";
    	if (vo.getCustCd() != null) {
			kiccResultService.saveKiccPaymentResultLog(vo);
			resultCode = S;
			resultMsg = SUCCESS;
		} else {
			resultCode = F1;
			resultMsg = "결제코드(custCd)가 없습니다";
		}
		ApiResultCodeVO apiResult = new ApiResultCodeVO();
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
	}

}
