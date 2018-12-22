package com.prb.erp.domain.sms.detail;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface SendDetailRepository extends AXBootJPAQueryDSLRepository<SendDetail, SendDetail.SendDetailId> {
}
