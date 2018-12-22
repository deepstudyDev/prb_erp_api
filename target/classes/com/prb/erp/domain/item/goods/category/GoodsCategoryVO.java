package com.prb.erp.domain.item.goods.category;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class GoodsCategoryVO extends BaseVO {
    private String goodsCd;
    private String categoryType;
    private String categoryCd;
    private String categoryNm;
    private int sort;
    private int categoryCount;
    private String productCd;
    private String productNm;
}