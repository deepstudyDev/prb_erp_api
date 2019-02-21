package com.prb.erp.domain.user;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
import org.apache.ibatis.annotations.Param;


public interface UserMapper extends MyBatisMapper {
    UserVO getLoginUser(RequestParams<UserVO> vo);
    List<UserVO> getUserList(RequestParams<UserVO> vo);
    UserVO getUser(RequestParams<UserVO> vo);
    int getUserListCount(RequestParams<UserVO> vo);
    void updateUserChildCd(@Param("custCd") String custCd, @Param("childCd") String childCd);
}  