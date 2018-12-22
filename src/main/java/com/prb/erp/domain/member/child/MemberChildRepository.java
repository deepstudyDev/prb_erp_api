package com.prb.erp.domain.member.child;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface MemberChildRepository extends AXBootJPAQueryDSLRepository<MemberChild, MemberChild.MemberChildId> {
}
