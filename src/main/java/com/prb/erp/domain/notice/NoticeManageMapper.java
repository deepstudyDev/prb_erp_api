package com.prb.erp.domain.notice;

import java.util.List;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;
 
 
public interface NoticeManageMapper extends MyBatisMapper {
    NoticeManageVO getNotice(RequestParams<NoticeManageVO> vo);
    List<NoticeManageVO> getNoticeList(RequestParams<NoticeManageVO> vo);
    List<NoticeManageVO> getNoticeListAll(RequestParams<NoticeManageVO> vo);
    int getNoticeListCount(RequestParams<NoticeManageVO> vo);
}  