package com.prb.erp.domain.apk;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApkMapper extends MyBatisMapper {

   ApkVersionVO getCurrentApkVersion();

}
