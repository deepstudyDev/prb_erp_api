package com.prb.erp.domain.api;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface ApiMapper extends MyBatisMapper {
	
	List<ApiCommonCodeVO> getCommonCode(RequestParams<ApiCommonCodeVO> vo);
	
	ApiUserVO getUserInfo(RequestParams<ApiUserVO> vo);	
    List<ApiGoodsManageVO> getGoodsManageList(RequestParams<ApiGoodsManageVO> vo);
    int getGoodsManageListCount(RequestParams<ApiGoodsManageVO> vo);

    int getTodayMemberCount(RequestParams<ApiMemberManageVO> vo);
    int getTotalMemberCount(RequestParams<ApiMemberManageVO> vo);
    
    List<ApiMemberManageVO> getMemberList(RequestParams<ApiMemberManageVO> vo);
    List<ApiMemberDetailVO> getMemberDetail(RequestParams<ApiMemberDetailVO> vo);
    
    List<ApiMemberChildVO> getContractInfoDetail(RequestParams<Object> vo);
    
    int getMemberListCount(RequestParams<ApiMemberManageVO> vo);
    
    //계약헤더정보
    ApiMemberHeaderVO getMemberHeader(RequestParams<Object> vo);
    //계약헤더정보(핀노드 추가요청건 - 안지호)
    ApiMemberHeaderVO getMemberHeaderAnyParam(RequestParams<Object> vo);
    //자녀계약정보 - 특정자녀
    ApiMemberChildVO getMemberChild(RequestParams<Object> vo);
    //자녀계약정보 - 특정자녀(핀노드 추가요청건 - 안지호)
    ApiMemberChildVO getMemberChildAnyParam(RequestParams<Object> vo);
    //자녀계약정보 - 모든자녀
    List<ApiMemberChildVO> getMemberChilds(RequestParams<Object> vo);
    
    //자녀계약정보 - 형제정보
    List<ApiBrotherVO> getMemberBrothers(RequestParams<Object> vo);
    
    
    //인수인계목록
    List<ApiTcherTransManageResponseVO> getTransMemberList(RequestParams vo);
    ApiTcherTransManageResponseVO getTransMemberDetail(RequestParams vo);
    
    //배정목록
    List<ApiTcherAssignManageResponseVO> getAssignList(RequestParams vo);
    //휴식목록
    List<ApiTcherRestResponseVO> getRestList(RequestParams vo);
    //교사목록
    List<ApiTcherManageVO> getTcherList(RequestParams vo);
    //공지목록
    List<ApiNoticeManageVO> getNoticeList(RequestParams vo);
    int getNoticeListCount(RequestParams vo);
    //오늘의알림
    List<ApiTodayArmVO> getTodayArm(RequestParams vo);
    /*
     * 아이디 비밀번호찾기
     */    

	ApiUserVO findUser(RequestParams<ApiUserVO> vo);
	
	
	/*
	 * sms
	 */
	
    List<ApiSendMasterVO> getSendList(RequestParams<ApiSendMasterVO> vo);
    int getSendListCount(RequestParams<ApiSendMasterVO> vo);
}  