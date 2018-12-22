package com.prb.erp.domain.program.menu;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

@Repository
public interface MenuRepository extends AXBootJPAQueryDSLRepository<Menu, String> {
}
