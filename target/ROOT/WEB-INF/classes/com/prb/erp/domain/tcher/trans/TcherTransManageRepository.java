package com.prb.erp.domain.tcher.trans;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface TcherTransManageRepository extends AXBootJPAQueryDSLRepository<TcherTransManage, TcherTransManage.TcherTransManageId> {
}
