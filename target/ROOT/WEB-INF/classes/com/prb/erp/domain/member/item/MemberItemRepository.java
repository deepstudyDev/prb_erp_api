package com.prb.erp.domain.member.item;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
 
@Repository
public interface MemberItemRepository extends AXBootJPAQueryDSLRepository<MemberItem, MemberItem.MemberItemId> {
}
