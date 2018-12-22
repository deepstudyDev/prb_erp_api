package com.prb.erp.domain.code.detail;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
  
@Repository											
public interface BasicCodeDetailRepository extends AXBootJPAQueryDSLRepository<BasicCodeDetail,BasicCodeDetail.BasicCodeDetailId> {
    Page<BasicCodeDetail> findByMainCode(String mainCode, Pageable pageable);
}        