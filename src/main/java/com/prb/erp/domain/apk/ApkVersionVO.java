package com.prb.erp.domain.apk;

import lombok.Data;

@Data
public class ApkVersionVO {

    private Integer idx;

    private String packageName = "com.ideepstudy.stu.realease";

    private String apkVersion;

    private String apkFilePath;

    private String createUserCd;

    private Integer isLog;

    private String apkFileName;

    private String createDate;

    private Integer rowNum;
}
