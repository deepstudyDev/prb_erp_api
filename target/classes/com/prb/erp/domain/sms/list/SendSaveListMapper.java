package com.prb.erp.domain.sms.list;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface SendSaveListMapper extends MyBatisMapper {
    List<SendSaveList> getSendSaveList(RequestParams<SendSaveList> vo);
    int getSendSaveListCount(RequestParams<SendSaveList> vo);
}  