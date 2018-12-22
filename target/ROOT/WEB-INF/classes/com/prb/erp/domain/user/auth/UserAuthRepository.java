package com.prb.erp.domain.user.auth;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface UserAuthRepository extends AXBootJPAQueryDSLRepository<UserAuth, UserAuthId> {

    List<UserAuth> findByUserCd(String userCd);
}
