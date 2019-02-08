package com.prb.erp.domain.api;

import lombok.Data;

@Data
public class ApiQaManageVO {

    private Integer qaId;

    private String qaType;

    private String qaTitle;

    private String createUserCd;

    private Integer isView;

    private String createDate;

    private Integer pageNumber;

    private Integer rowNum;

    private String qaRepContents;
}
