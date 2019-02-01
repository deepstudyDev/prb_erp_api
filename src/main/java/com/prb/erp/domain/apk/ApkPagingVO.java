package com.prb.erp.domain.apk;

import com.chequer.axboot.core.vo.BaseVO;
import lombok.Data;

import java.util.List;

@Data
public class ApkPagingVO extends BaseVO {
    private List<ApkVersionVO> result;

    private int totalCnt;

    private int dataSize = 10;

    private int pageSize = 5;

    private int pageNo;

    public ApkPagingVO() {}

    public ApkPagingVO(List<ApkVersionVO>apkVersionVOList, int pageNo, int totalCnt) {
        this.result = apkVersionVOList;
        this.pageNo = pageNo;
        this.totalCnt = totalCnt;
    }
}
