package com.prb.erp.domain.user;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface UserMapper extends MyBatisMapper {
    UserVO getLoginUser(RequestParams<UserVO> vo);
    List<UserVO> getUserList(RequestParams<UserVO> vo);
    UserVO getUser(RequestParams<UserVO> vo);
    int getUserListCount(RequestParams<UserVO> vo);
}  