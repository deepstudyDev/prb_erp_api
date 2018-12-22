package com.prb.erp.domain.key.management;


import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface KeyManagementRepository extends AXBootJPAQueryDSLRepository<KeyManagement, KeyManagement.CompanyKeyManagementId> {
}
  