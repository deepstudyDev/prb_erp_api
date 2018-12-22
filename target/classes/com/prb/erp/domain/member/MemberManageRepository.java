package com.prb.erp.domain.member;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface MemberManageRepository extends AXBootJPAQueryDSLRepository<MemberManage, MemberManage.MemberManageId> {
}
