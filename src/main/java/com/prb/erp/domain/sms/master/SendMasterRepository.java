package com.prb.erp.domain.sms.master;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface SendMasterRepository extends AXBootJPAQueryDSLRepository<SendMaster, SendMaster.SendMasterId> {
}
