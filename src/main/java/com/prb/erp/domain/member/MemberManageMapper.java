package com.prb.erp.domain.member;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
import org.apache.ibatis.annotations.Param;


public interface MemberManageMapper extends MyBatisMapper {
    MemberManageVO getMember(RequestParams<MemberManageVO> vo);
    MemberManageVO getMemberByCustCd(@Param("custCd") String custCd);
    List<MemberManageVO> getMemberList(RequestParams<MemberManageVO> vo);
    List<MemberManageVO> getMemberListAll(RequestParams<MemberManageVO> vo);
    List<MemberDetailVO> getMemberDetail(RequestParams<MemberDetailVO> vo);
    int getMemberListCount(RequestParams<MemberManageVO> vo);
    MemberManageVO getMemberChildren(RequestParams<MemberManageVO> vo);
    MemberManageVO getMemberChildrenChildCd(@Param("childCd") String childCd);
    String getMemberOrgCd(@Param("custCd") String custCd);
    void updateFroebelCustCd(@Param("froebelCustCd") String froebelCustCd, @Param("custCd") String custCd);
    int getUserCdCountByLoginId(@Param("loginId") String loginId);
    List<String>getForbiddenWordList();
}  
