package com.prb.erp.domain.tcher.assign;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface TcherAssignManageRepository extends AXBootJPAQueryDSLRepository<TcherAssignManage, TcherAssignManage.TcherAssignManageId> {
}
