package com.prb.erp.controllers;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import com.prb.erp.domain.api.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.user.UserService;
import com.prb.erp.utils.UserLogUtil;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;


//오픈 API
@RestController
public class OpenAPIController extends BaseController {
	@Inject private ApiService apiService;
	@Inject private UserService userService;


    //공통코드
    @RequestMapping(value = "/api/v4/edu/getCommonCode",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("공통코드")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "mainCode", value = "마스터코드", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultObjectVO getCommonCode(RequestParams<ApiCommonCodeVO> vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getCommonCode","GET");

    	String resultCode;
    	String resultMsg;
		resultCode = "S";
		resultMsg = "SUCCESS";

    	List<ApiCommonCodeVO> result =  apiService.getCommonCode(vo);
    	ApiResultObjectVO apiResult = new ApiResultObjectVO();

    	apiResult.setResult(result);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
    }



    //사용자조회 (로그인/권한)
    @RequestMapping(value = "/api/v4/edu/getUserInfo", method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("사용자조회 (로그인/권한)")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "userCd", value = "아이디", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "userPs", value = "패스워드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "userType", value = "10:본사,12:상담교사,14:방문교사,30:계약자,40:학생", dataType = "String", paramType = "query", required = true, allowableValues = "10,12,14,30,40")
	})
    @ResponseBody
    public ApiResultObjectVO getUserInfo(RequestParams<ApiUserVO> vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getUserInfo","GET");

    	String resultCode;
    	String resultMsg;

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	ApiUserVO result = apiService.getUserInfo(vo);

    	if (null != result){
    		resultCode = "S";
    		resultMsg = "SUCCESS";

    		//교사일경우 / 최초로그인 / 퇴사유무
    		if ((result.getUserType().equals("12") || result.getUserType().equals("14"))){

    			if(StringUtils.isEmpty(result.getLastLoginDate())){
            		resultCode = "F3";
            		resultMsg = "최초 로그인 하는 교사입니다. 패스워드 변경 후 진행 하세요.";
    			}
        		if (result.getUserStatus().equals("20")){
            		resultCode = "F2";
            		resultMsg = "접근할수 없는 사용자입니다.("+result.getUserStatusNm()+")";
        		}
    		}
    	}else{
    		resultCode = "F1";
    		resultMsg = "사용자 정보가 없습니다.";
    	}

    	if(resultCode.equals("S")){
    		userService.setLoginDate(result.getUserCd());
    	}

    	apiResult.setResult(result);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
    }

    //상품조회
    @RequestMapping(value = "/api/v4/edu/getGoodsList",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("상품리스트조회")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "goodsCd", value = "상품코드", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "salesYn", value = "판매여부 Y:판매중 , N:판매중지", dataType = "String", paramType = "query", required = false,allowableValues = "Y,N"),
	    @ApiImplicitParam(name = "goodsClass", value = "상품분류 0: 단품상품, 1:패키지상품", dataType = "String", paramType = "query", required = false,allowableValues = "0,1"),
	    @ApiImplicitParam(name = "searchType", value = "검색유형", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "searchText", value = "검색값", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "rowsPerPage", value = "한페이지당 검색건수", dataType = "int", paramType = "query", required = false),
	    @ApiImplicitParam(name = "pageNumber", value = "현재페이지 번호", dataType = "int", paramType = "query", required = false)
	})
    public ApiResultObjectPagingVO getGoodsList(RequestParams<ApiGoodsManageVO> vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getGoodsList","GET");
    	ApiResultObjectPagingVO apiResult = apiService.getGoodsManageList(vo);
    	return apiResult;
    }

    //계약정보저장
    @RequestMapping(value = "/api/v4/edu/saveMember" , method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("계약정보저장")
    public ApiResultCodeVO saveMember(@Valid @ModelAttribute ApiMemberManageSaveVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","saveMember","GET");
    	return apiService.saveMember(vo);
    }

    //계약정보조회
    @RequestMapping(value = "/api/v4/edu/getMemberList",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("계약정보목록 조회")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "fromDate", value = "등록시작일", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "toDate", value = "등록종료일", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "gd1Nm", value = "계약자명", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "tcherCd", value = "교사아이디", dataType = "String", paramType = "query", required = false),

	    @ApiImplicitParam(name = "goodsCd", value = "상품코드", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "freeYn", value = "무료Y,유료N (현재는 무료)", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "visitNumberCd", value = "방문 횟수(월) 1~4회 (1,2,3,4)", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "contractType", value = "추가계약구분 1:기본 , 2:형제추가 3:시간.횟수추가", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "childrenGradeCd", value = "학년,0:미취학 1:1학년 2:2학년 3:3학년 4:4학년 5:5학년 6:6학년", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "childrenSex", value = "성별 : 0남 1여", dataType = "String", paramType = "query", required = false),

	    @ApiImplicitParam(name = "searchType", value = "검색유형 10:계약자명 , 20:계약자휴대전화", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "searchText", value = "검색값", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "rowsPerPage", value = "한페이지당 검색건수", dataType = "int", paramType = "query", required = false),
	    @ApiImplicitParam(name = "pageNumber", value = "현재페이지 번호", dataType = "int", paramType = "query", required = false),

	    @ApiImplicitParam(name = "orderColumn", value = "미입력시 최종수정일로", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "orderType", value = "미입력시 asc ", dataType = "String", paramType = "query", required = false)
	})
    public ApiResultObjectPagingVO getMemberList(RequestParams<ApiMemberManageVO> vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getMemberList","GET");
    	ApiResultObjectPagingVO apiResult = apiService.getMemberList(vo);
    	return apiResult;
    }

    //계약상세
    @RequestMapping(value = "/api/v4/edu/getMemberDetails",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("계약정보 상세조회")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "custCd", value = "계약코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childCd", value = "자녀코드", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "childUserCd", value = "자녀 ID", dataType = "String", paramType = "query", required = false)
	})
    public ApiMemberDetailsResultVO getMemberDetail(RequestParams<Object> vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getMemberDetails","GET");

    	String resultCode;
    	String resultMsg;

    	ApiMemberDetailsResultVO apiResult = new ApiMemberDetailsResultVO();
    	ApiMemberHeaderVO header =  apiService.getMemberHeader(vo);
    	List<ApiMemberChildVO> child =  apiService.getMemberChilds(vo);

		// 2018-12-20 안지호 (핀노드 요청으로 수정)
    	if (child != null) {
    		apiResult = ApiMemberDetailsResultVO.of(vo);
    		List<ApiBrotherVO>brothers = apiService.getMemberBrothers(vo);

    		if (brothers != null) apiResult.setBrothers(brothers);
		}

		if (null != header){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "결과 정보가 없습니다.";
    	}
    	apiResult.setHeader(header);
    	apiResult.setChild(child);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);

    	return apiResult;
    }


    //계약상세
    @RequestMapping(value = "/api/v4/edu/getMemberDetail",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("계약정보 상세조회-자녀필수")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "custCd", value = "계약코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childCd", value = "자녀코드", dataType = "String", paramType = "query", required = true)
	})
    public ApiMemberDetailResultVO getMemberDetails(RequestParams<Object> vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getMemberDetail","GET");

    	String resultCode;
    	String resultMsg;

    	ApiMemberDetailResultVO apiResult = new ApiMemberDetailResultVO();
    	ApiMemberHeaderVO header =  apiService.getMemberHeader(vo);
    	ApiMemberChildVO child =  apiService.getMemberChild(vo);

    	if (null != header){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "결과 정보가 없습니다.";
    	}
    	apiResult.setHeader(header);
    	apiResult.setChild(child);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);

    	return apiResult;
    }

	/**
	 * <PRE>
	 *     1. 작성자 : 안지호
	 *     2. 작성일 : 2018-12-11
	 *     3. 설명 : 계약정보 상세조회 - 보호자이름, 자녀이름, 휴대전화번호, 자녀학년 4가지 필수값 (핀노드 요청으로 신규 작성)
	 * </PRE>
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "/api/v4/edu/getMemberDetailAnyParam",method = RequestMethod.POST, produces = APPLICATION_JSON)
	@ApiOperation("계약정보 상세조회 - 보호자이름, 자녀이름, 휴대전화번호, 자녀학년 4가지 필수값")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "gd1Nm", value = "보호자이름", dataType = "String", paramType = "query", required = true),
			@ApiImplicitParam(name = "childrenNm", value = "자녀이름", dataType = "String", paramType = "query", required = true),
			@ApiImplicitParam(name = "hpNo", value = "휴대전화번호", dataType = "String", paramType = "query", required = true),
			@ApiImplicitParam(name = "childrenGradeCd", value = "자녀학년", dataType = "String", paramType = "query", required = true)
	})
	public ApiMemberDetailResultVO getMemberDetailsAnyParam(RequestParams<Object> vo) {
		UserLogUtil.saveUserLog("OpenAPIController","getMemberDetailAnyParam","GET");

		String resultCode;
		String resultMsg;

		ApiMemberDetailResultVO apiResult = new ApiMemberDetailResultVO();
		ApiMemberHeaderVO header =  apiService.getMemberHeaderAnyParam(vo);
		ApiMemberChildVO child =  apiService.getMemberChildAnyParam(vo);

		if (null != header){
			resultCode = "S";
			resultMsg = "SUCCESS";
		}else{
			resultCode = "F1";
			resultMsg = "결과 정보가 없습니다.";
		}
		apiResult.setHeader(header);
		apiResult.setChild(child);
		apiResult.setResultCode(resultCode);
		apiResult.setResultMsg(resultMsg);

		return apiResult;
	}

	/*10.19*/
    //마이메뉴 SP080
    @RequestMapping(value = "/api/v4/edu/getSP080",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("마이메뉴 SP080 정보")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "custCd", value = "계약코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childCd", value = "자녀코드", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultObjectVO getSP080(RequestParams<Object> vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getSP080","GET");

    	String resultCode;
    	String resultMsg;
    	Result080VO result = new Result080VO();

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();

    	ApiMemberChildVO child =  apiService.getMemberChild(vo);

    	if(null!=child){
    		result = Result080VO.of(child);
    	}

    	if (null != result){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "결과 정보가 없습니다.";
    	}

    	apiResult.setResult(result);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);

    	return apiResult;
    }

    //결제정보(기본결제정보) SP081
    @RequestMapping(value = "/api/v4/edu/getSP081",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("결제정보(기본결제정보) SP081")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "custCd", value = "계약코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childCd", value = "자녀코드", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultObjectVO getSP081(RequestParams<Object> vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getSP081","GET");

    	String resultCode;
    	String resultMsg;
    	Result081VO result = new Result081VO();

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	ApiMemberChildVO child =  apiService.getMemberChild(vo);

    	if(null!=child){
    		result = Result081VO.of(child);
    	}

    	if (null != result){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "결과 정보가 없습니다.";
    	}
    	apiResult.setResult(result);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
    }

    //부모별 계약정보
    @RequestMapping(value = "/api/v4/edu/getContractInfo",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("부모 로그인 ID별 계약정보")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "gd1UserCd", value = "부모 로그인 아이디", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "contractType", value = "자녀계약구분 1:기본 , 2:형제추가 3:시간.횟수추가", dataType = "String", paramType = "query", required = false, allowableValues = "1,2,3")
	})
    public ApiContractInfoResultVO getContractInfo(RequestParams<Object> vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getContractInfo","GET");

    	String resultCode;
    	String resultMsg;
    	ApiContractInfoResultVO apiResult = new ApiContractInfoResultVO();
    	ApiMemberHeaderVO header =  apiService.getMemberHeader(vo);
    	List<ApiMemberChildVO> child =  apiService.getContractInfoDetail(vo);

    	if (null != header){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
        	apiResult.setCustCd(header.getCustCd());
    	}else{
    		resultCode = "F1";
    		resultMsg = "결과 정보가 없습니다.";
    	}
    	apiResult.setChild(child);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);

    	return apiResult;
    }

    //인수인계요청
    @RequestMapping(value="/api/v4/edu/transRequest", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("인수인계요청")
    public ApiResultCodeVO transRequest(@ModelAttribute ApiTcherTransRequestVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","transRequest","POST");
    	return apiService.transRequest(vo);
    }

    //인수인계확정
    @RequestMapping(value="/api/v4/edu/transSave", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("인수인계요청(승인)")
    public ApiResultCodeVO transSave(@ModelAttribute ApiTcherTransSaveVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","transSave","POST");
    	return apiService.transSave(vo);
    }

    //학습휴식신청
    @RequestMapping(value="/api/v4/edu/restRequest", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("학습휴식신청")
    public ApiResultCodeVO restRequest(@ModelAttribute ApiTcherRestRequestVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","restRequest","POST");
    	return apiService.restRequest(vo);
    }

    //학습휴식신청
    @RequestMapping(value="/api/v4/edu/restSave", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("학습휴식신청(승인)")
    public ApiResultCodeVO restSave(@ModelAttribute ApiTcherRestSaveVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","restSave","POST");
    	return apiService.restSave(vo);
    }

    //학습휴식신청
    @RequestMapping(value="/api/v4/edu/restCancel", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("학습휴식신청(미승인/취소)")
    public ApiResultCodeVO restCancel(@ModelAttribute ApiTcherRestSaveVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","restCancel","POST");
    	return apiService.restCancel(vo);
    }

    //교사배정
    @RequestMapping(value="/api/v4/edu/assignSave", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("교사배정/학습재시작")
    public ApiResultCodeVO assignSave(@ModelAttribute ApiTcherAssignSaveVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","assignSave","POST");
    	return apiService.assignSave(vo);
    }

    //10:인계 20:인수
    //인계목록
    @RequestMapping(value = "/api/v4/edu/getTransPrevList",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("인계목록")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "tcherCd", value = "교사아이디", dataType = "String", paramType = "query", required = true)
	})
    public List<ApiTcherTransManageResponseVO> getTransPrevList(RequestParams vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getTransPrevList","GET");
    	vo.put("searchType" ,"10");
    	return apiService.getTransMemberList(vo);
    }

    //인수목록
    @RequestMapping(value = "/api/v4/edu/getTransList",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("인수목록")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "tcherCd", value = "교사아이디", dataType = "String", paramType = "query", required = true)
	})
    public List<ApiTcherTransManageResponseVO> getTransList(RequestParams vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getTransList","GET");
    	vo.put("searchType" ,"20");
    	return apiService.getTransMemberList(vo);
    }

    //인수인계상세
    @RequestMapping(value = "/api/v4/edu/getTransDetail",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("인수인계상세")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "transCd", value = "인수인계코드", dataType = "String", paramType = "query", required = true)
	})
    public ApiTcherTransManageResponseVO getTransDetail(RequestParams vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getTransDetail","GET");
    	return apiService.getTransMemberDetail(vo);
    }

    //배정목록
    @RequestMapping(value = "/api/v4/edu/getAssignList",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("배정목록")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "tcherCd", value = "교사아이디", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "orderColumn", value = "미입력시 최종수정일로", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "orderType", value = "미입력시 asc ", dataType = "String", paramType = "query", required = false)
	})
    public List<ApiTcherAssignManageResponseVO> getAssignList(RequestParams vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getAssignList","GET");
    	return apiService.getAssignList(vo);
    }

    //휴식목록
    @RequestMapping(value = "/api/v4/edu/getRestList",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("휴식목록")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "tcherCd", value = "교사아이디", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "approvalYn", value = "승인:Y 취소:N", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "requestYear", value = "신청년", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "requestMonth", value = "신청월", dataType = "String", paramType = "query", required = false)
	})
    public List<ApiTcherRestResponseVO> getRestList(RequestParams vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getRestList","GET");
    	return apiService.getRestList(vo);
    }

    //교사목록
    @RequestMapping(value = "/api/v4/edu/getTcherList",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("교사목록")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "orgCd", value = "조직코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "tcherCd", value = "교사아이디(특정교사 조회시)", dataType = "String", paramType = "query", required = false)
	})
    public List<ApiTcherManageVO> getTcherList(RequestParams vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getTcherList","GET");
    	return apiService.getTcherList(vo);
    }

   //공지목록
    @RequestMapping(value = "/api/v4/edu/getNoticeList",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("공지목록")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "orgCd", value = "조직코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "rowsPerPage", value = "한페이지당 검색건수", dataType = "int", paramType = "query", required = false),
	    @ApiImplicitParam(name = "pageNumber", value = "현재페이지 번호", dataType = "int", paramType = "query", required = false)
	})
    public ApiResultObjectPagingVO getNoticeList(RequestParams vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getNoticeList","GET");
    	return apiService.getNoticeList(vo);
    }

	//공지목록(전체/상담/방문 구분)
	@RequestMapping(value = "/api/v4/edu/getNoticeListByType",method = RequestMethod.POST, produces = APPLICATION_JSON)
	@ApiOperation("공지목록(전체/상담/방문 구분)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "orgType", value = "조직구분(1:전체, 12:상담사원, 14:방문사원)", dataType = "int", paramType = "query", required = true),
			@ApiImplicitParam(name = "orgCd", value = "조직코드(전체는 orgCd 값이 없습니다)", dataType = "String", paramType = "query", required = true),
			@ApiImplicitParam(name = "rowsPerPage", value = "한페이지당 검색건수", dataType = "int", paramType = "query", required = false),
			@ApiImplicitParam(name = "pageNumber", value = "현재페이지 번호", dataType = "int", paramType = "query", required = false)
	})
	public ApiResultObjectPagingVO getNoticeListByType(RequestParams vo) {
		UserLogUtil.saveUserLog("OpenAPIController","getNoticeListByType","GET");
		return apiService.getNewNoticeList(vo);
	}

    //오늘의알림
    @RequestMapping(value = "/api/v4/edu/getTodayArm",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("오늘의알림")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "tcherCd", value = "교사아이디", dataType = "String", paramType = "query", required = true)
	})
    public List<ApiTodayArmVO> getTodayArm(RequestParams vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","getTodayArm","GET");
    	return apiService.getTodayArm(vo);
    }



    //[자녀사진정보저장 ]
    @RequestMapping(value="/api/v4/edu/childImageSave", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("자녀사진정보저장")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "custCd", value = "계약코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childCd", value = "자녀계약코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childImgUrl", value = "이미지전체경로url", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childImgRegDt", value = "이미지등록일시", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childImgBy", value = "이미지등록자ID", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultObjectVO childImageSave(RequestParams<Object> vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","childImageSave","GET");

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	apiResult = apiService.childImageSave(vo);
    	return apiResult;
    }


    //[상담교사 - 회원정보변경 수정 ]
    @RequestMapping(value="/api/v4/edu/elctrnCtrtcUpdate", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("상담교사 - 회원정보변경 수정")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "custCd", value = "계약코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childCd", value = "자녀계약코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childImgUrl", value = "사진경로", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "childrenNm", value = "자녀이름", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childrenSex", value = "자녀성별", dataType = "String", paramType = "query", required = true),


	    @ApiImplicitParam(name = "childrenBirthday", value = "자녀생일", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childrenHpNo", value = "자녀휴대전화", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "childrenSchoolNm", value = "자녀학교명", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "childrenGradeCd", value = "자녀학년", dataType = "String", paramType = "query", required = true),

	    @ApiImplicitParam(name = "gd1Nm", value = "보호자1", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "gd1RelationCd", value = "보호자1관계", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "hpNo", value = "보호자1전화", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "gd2Nm", value = "보호자2", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "gd2RelationCd", value = "보호자2관계", dataType = "String", paramType = "query", required = false),
	  //  @ApiImplicitParam(name = "hpNo2", value = "보호자2휴대전화", dataType = "String", paramType = "query", required = false),

	    @ApiImplicitParam(name = "telNo", value = "집전화", dataType = "String", paramType = "query", required = false),
	    @ApiImplicitParam(name = "email", value = "이메일", dataType = "String", paramType = "query", required = false),

	    @ApiImplicitParam(name = "homeZipcode", value = "우편번호", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "homeAddress1", value = "주소1", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "homeAddress2", value = "주소2", dataType = "String", paramType = "query", required = true),
	})
    public ApiResultObjectVO elctrnCtrtcUpdate(RequestParams<Object> vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","elctrnCtrtcUpdate","GET");

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	apiResult = apiService.elctrnCtrtcUpdate(vo);
    	return apiResult;
    }


    //[형제추가정보 추가 ]
    @RequestMapping(value="/api/v4/edu/elctrnCtrtcBrotherInsert", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("형제추가정보 추가")
    public ApiResultObjectVO elctrnCtrtcBrotherInsert(@ModelAttribute ApiBrotherRequestVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","elctrnCtrtcBrotherInsert","GET");

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	apiResult = apiService.elctrnCtrtcBrotherInsert(vo);
    	return apiResult;
    }



    //회원정보변경 조회
    @RequestMapping(value = "/api/v4/edu/elctrnCtrtcView",method = RequestMethod.POST, produces = APPLICATION_JSON)
    @ApiOperation("회원정보변경 조회 ")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "custCd", value = "계약코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "childCd", value = "자녀코드", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultObjectVO elctrnCtrtcView(RequestParams<Object> vo) {
    	UserLogUtil.saveUserLog("OpenAPIController","elctrnCtrtcView","GET");

    	String resultCode;
    	String resultMsg;
    	Result082VO result = new Result082VO();

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	ApiMemberChildVO child =  apiService.getMemberChild(vo);

    	if(null!=child){
    		result = Result082VO.of(child);
    		List<ApiBrotherVO> brothers = apiService.getMemberBrothers(vo);

    		if(null != brothers){
    			result.setBrothers(brothers);
    		}
    	}

    	if (null != result){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "결과 정보가 없습니다.";
    	}

    	apiResult.setResult(result);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
    }

    //아이디찾기
    @RequestMapping(value="/api/v4/edu/saveVisitDt", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("방문교육일 등록")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "childCd", value = "자녀계약코드", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "startDt (YYYY-MM-DD)", value = "교육시작일", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "endDt (YYYY-MM-DD)", value = "교육종료일", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultObjectVO saveVisitDt(RequestParams vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","saveVisitDt","GET");

    	String resultCode;
    	String resultMsg;

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	apiService.saveVisitDt(vo);

    	resultCode = "S";
		resultMsg = "SUCCESS";
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
    }


    //개인정보(계약::부모::자녀정보) 수정
    /*
     * 아이디찾기. 비밀번호찾기
     */
    //아이디찾기
    @RequestMapping(value="/api/v4/edu/findId", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("아이디찾기")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "userNm", value = "사용자명", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "hpNo", value = "휴대폰번호", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultObjectVO findId(RequestParams<ApiUserVO> vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","findId","GET");

    	String resultCode;
    	String resultMsg;

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	ApiUserVO result = apiService.findUser(vo);

    	if (null != result){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "사용자 정보가 없습니다.";
    	}
    	apiResult.setResult(result);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
    }


    //비밀번호찾기
    @RequestMapping(value="/api/v4/edu/findPs", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("비밀번호찾기")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "userCd", value = "사용자아이디", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "userNm", value = "사용자명", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "hpNo", value = "휴대폰번호", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultObjectVO findPs(RequestParams<ApiUserVO> vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","findPs","GET");

    	String resultCode;
    	String resultMsg;

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	ApiUserVO result = apiService.findUser(vo);

    	if (null != result){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "사용자 정보가 없습니다.";
    	}
    	apiResult.setResult(result);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
    }

    //패스워드확인
    @RequestMapping(value="/api/v4/edu/checkPs", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("패스워드확인")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "userCd", value = "사용자아이디", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "userNm", value = "사용자명", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "userPs", value = "현재비밀번호", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultObjectVO checkPs(RequestParams<ApiUserVO> vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","checkPs","GET");
    	String resultCode;
    	String resultMsg;

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	ApiUserVO result = apiService.findUser(vo);

    	if (null != result){
    		resultCode = "S";
    		resultMsg = "SUCCESS";
    	}else{
    		resultCode = "F1";
    		resultMsg = "사용자 정보가 없습니다.";
    	}
    	apiResult.setResult(result);
    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
    }

    //비밀번호수정
    @RequestMapping(value="/api/v4/edu/changePs", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("비밀번호수정")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "userCd", value = "사용자아이디", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "userNm", value = "사용자명", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "userPs", value = "비밀번호", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultCodeVO changePs(RequestParams<ApiUserVO> vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","changePs","GET");
    	ApiResultCodeVO apiResult = apiService.changePs(vo);
    	return apiResult;
    }

    /*
     * sms
     */
    //sms 발송 :: 여러건
    @RequestMapping(value="/api/v4/edu/sendSms", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("문자발송")
    public ApiResultCodeVO sendSms(@Valid @ModelAttribute ApiSmsMasterVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","sendSms","GET");
    	return apiService.saveSend(vo);
    }

    //전송내역
    @RequestMapping(value="/api/v4/edu/getSendList", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("문자전송내역")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "senderUserCd", value = "보낸사람아이디", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "rowsPerPage", value = "한페이지당 검색건수", dataType = "int", paramType = "query", required = false),
	    @ApiImplicitParam(name = "pageNumber", value = "현재페이지 번호", dataType = "int", paramType = "query", required = false),
		@ApiImplicitParam(name = "orderColumn", value = "미입력시 최종수정일로", dataType = "String", paramType = "query", required = false),
		@ApiImplicitParam(name = "orderType", value = "미입력시 asc ", dataType = "String", paramType = "query", required = false),
		@ApiImplicitParam(name = "sendStartDate", value = "발송일검색 시작일", dataType = "String", paramType = "query", required = false),
		@ApiImplicitParam(name = "sendEndDate", value = "발송일검색 종료일", dataType = "String", paramType = "query", required = false),
		@ApiImplicitParam(name = "successYn", value = "발송 결과", dataType = "String", paramType = "query", required = false),
	})
    public ApiResultObjectPagingVO getSendList(RequestParams<ApiSendMasterVO> requestParams) {
    	UserLogUtil.saveUserLog("OpenAPIController","getSendList","GET");
    	return apiService.getSendList(requestParams);
    }

    //개인정보수정 (방문)
    @RequestMapping(value="/api/v4/edu/modifyCust", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("개인정보수정 (방문앱)")
    public ApiResultObjectVO modifyCust(@ModelAttribute ApiMemberModifyCustVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","modifyCust","GET");

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	String resultCode;
    	String resultMsg;

    	apiService.modifyCust(vo);

		resultCode = "S";
		resultMsg = "SUCCESS";

    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
    }

    //개인정보수정(학습자-에듀)
    @RequestMapping(value="/api/v4/edu/modCust", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("개인정보수정 (학부모앱)")
    public ApiResultObjectVO modifyCust(@ModelAttribute ApiMemberModCustVO vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","modifyCust","GET");

    	ApiResultObjectVO apiResult = new ApiResultObjectVO();
    	String resultCode;
    	String resultMsg;

    	apiService.modCust(vo);

		resultCode = "S";
		resultMsg = "SUCCESS";

    	apiResult.setResultCode(resultCode);
    	apiResult.setResultMsg(resultMsg);
    	return apiResult;
    }

    //교사정보수정
    @RequestMapping(value="/api/v4/edu/tcherInfoChange", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    @ApiOperation("교사정보수정")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "tcherCd", value = "교사사번", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "tcherBirthday", value = "생년월일 (yyyy-mm-dd)", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "tcherZipcode", value = "주소(우편번호)", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "tcherAddress1", value = "주소1", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "tcherAddress2", value = "주소2", dataType = "String", paramType = "query", required = true),
	    @ApiImplicitParam(name = "userPs", value = "비밀번호", dataType = "String", paramType = "query", required = true)
	})
    public ApiResultCodeVO tcherInfoChange(RequestParams<Object> vo) throws Exception {
    	UserLogUtil.saveUserLog("OpenAPIController","tcherInfoChange","GET");
    	ApiResultCodeVO apiResult = apiService.tcherInfoChange(vo);
    	return apiResult;
    }

	//학무보, 자녀 로그인정보 저장
	@RequestMapping(value="/api/ㅎv4/edu/saveUserLoginInfo", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
	@ApiOperation("로그인 정보 저장(학부모, 학생)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "custCd", value = "학부모 코드", dataType = "String", paramType = "query", required = true),
			@ApiImplicitParam(name = "parentLoginId", value = "학부모 로그인 아이디", dataType = "String", paramType = "query", required = true),
			@ApiImplicitParam(name = "parentPassword", value = "학부모 로그인 패스워드", dataType = "String", paramType = "query", required = true),
			@ApiImplicitParam(name = "childCd", value = "학생 코드", dataType = "String", paramType = "query", required = true),
			@ApiImplicitParam(name = "childLoginId", value = "학생 로그인 아이디", dataType = "String", paramType = "query", required = true),
			@ApiImplicitParam(name = "childPassword", value = "학생 로그인 패스워드", dataType = "String", paramType = "query", required = true)
	})
	public ApiResultCodeVO saveUserLoginInfo(RequestParams<Object> vo) throws Exception {
		ApiResultCodeVO apiResult = apiService.saveUserLoginInfo(vo);
		return apiResult;
	}

	//kicc결제결과 저장
	@RequestMapping(value = "/api/v4/edu/saveKiccPaymentResult", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
	@ApiOperation("kicc결제결과 저장")
	public ApiResultCodeVO saveKiccPaymentResult(@ModelAttribute ApiKiccPaymentResultSaveVO vo) {
		return apiService.saveKiccPaymentResult(vo);
	}
}