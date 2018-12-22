package com.prb.erp.domain.tcher.inf.tcher;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface TcherInfTcherRepository extends AXBootJPAQueryDSLRepository<TcherInfTcher, TcherInfTcher.TcherInfTcherId> {
}
