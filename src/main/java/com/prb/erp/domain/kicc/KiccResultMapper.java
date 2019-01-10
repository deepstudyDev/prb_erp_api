package com.prb.erp.domain.kicc;

import com.chequer.axboot.core.mybatis.MyBatisMapper;

public interface KiccResultMapper extends MyBatisMapper {

    void saveKiccPaymentResultLog(KiccResult kiccResult);
}
