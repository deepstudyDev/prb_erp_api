package com.prb.erp.domain.user;


import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface UserRepository extends AXBootJPAQueryDSLRepository<User, String> {
}
