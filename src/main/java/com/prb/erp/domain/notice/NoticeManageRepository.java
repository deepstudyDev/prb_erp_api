package com.prb.erp.domain.notice;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface NoticeManageRepository extends AXBootJPAQueryDSLRepository<NoticeManage, NoticeManage.NoticeManageId> {
}
