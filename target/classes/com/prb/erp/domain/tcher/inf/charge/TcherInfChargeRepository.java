package com.prb.erp.domain.tcher.inf.charge;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface TcherInfChargeRepository extends AXBootJPAQueryDSLRepository<TcherInfCharge, TcherInfCharge.TcherInfChargeId> {
}
