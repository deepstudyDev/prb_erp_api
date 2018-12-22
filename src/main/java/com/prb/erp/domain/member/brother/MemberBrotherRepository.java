package com.prb.erp.domain.member.brother;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface MemberBrotherRepository extends AXBootJPAQueryDSLRepository<MemberBrother, MemberBrother.MemberBrotherId> {
}
