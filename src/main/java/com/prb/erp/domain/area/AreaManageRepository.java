package com.prb.erp.domain.area;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface AreaManageRepository extends AXBootJPAQueryDSLRepository<AreaManage, AreaManage.AreaManageId> {
}
