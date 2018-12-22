package com.prb.erp.domain.code.master;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository												
public interface BasicCodeMasterRepository extends AXBootJPAQueryDSLRepository<BasicCodeMaster, BasicCodeMaster.BasicCodeMasterId> {

}