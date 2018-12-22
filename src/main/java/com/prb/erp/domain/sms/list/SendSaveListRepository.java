package com.prb.erp.domain.sms.list;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface SendSaveListRepository extends AXBootJPAQueryDSLRepository<SendSaveList, SendSaveList.SendSaveListId> {
}
  