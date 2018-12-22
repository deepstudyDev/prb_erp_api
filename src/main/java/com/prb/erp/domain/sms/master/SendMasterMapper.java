package com.prb.erp.domain.sms.master;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface SendMasterMapper extends MyBatisMapper {
    List<SendMasterVO> getSendList(RequestParams<SendMasterVO> vo);
    int getSendListCount(RequestParams<SendMasterVO> vo);
    

    List<SendSmsUserListVO> getSmsUserList(RequestParams<SendSmsUserListVO> vo);
    int getSmsUserListCount(RequestParams<SendSmsUserListVO> vo);
    
    
}  