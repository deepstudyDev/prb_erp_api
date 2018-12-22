package com.prb.erp.domain.tcher;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface TcherManageRepository extends AXBootJPAQueryDSLRepository<TcherManage, TcherManage.TcherManageId> {
}
