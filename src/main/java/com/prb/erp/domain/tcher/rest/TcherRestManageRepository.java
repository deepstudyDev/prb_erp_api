package com.prb.erp.domain.tcher.rest;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface TcherRestManageRepository extends AXBootJPAQueryDSLRepository<TcherRestManage, TcherRestManage.TcherRestManageId> {
}
