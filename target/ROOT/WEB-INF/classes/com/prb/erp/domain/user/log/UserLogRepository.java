package com.prb.erp.domain.user.log;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface UserLogRepository extends AXBootJPAQueryDSLRepository<UserLog, Long> {
}
