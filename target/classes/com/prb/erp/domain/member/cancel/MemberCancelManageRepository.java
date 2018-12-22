package com.prb.erp.domain.member.cancel;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface MemberCancelManageRepository extends AXBootJPAQueryDSLRepository<MemberCancelManage, MemberCancelManage.MemberCancelManagId> {
}
