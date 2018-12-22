package com.prb.erp.domain.tmsg.queue;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface TmsgQueueRepository extends AXBootJPAQueryDSLRepository<TmsgQueue, TmsgQueue.TmsgQueueId> {
}
