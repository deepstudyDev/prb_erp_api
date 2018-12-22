package com.prb.erp.domain.key.system;


import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface SystemKeyManagementRepository extends AXBootJPAQueryDSLRepository<SystemKeyManagement, SystemKeyManagement.SystemKeyManagementId> {
}
   