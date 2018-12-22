package com.prb.erp.domain.code.detail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.chequer.axboot.core.code.AXBootTypes;
import com.chequer.axboot.core.utils.ModelMapperUtils;
import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class BasicCodeDetailVO extends BaseVO {

	private String company;
	
	private String plant;
	
	private String mainCode;
	 
	private String subCode;
 
	private String subName;
 
	private BigDecimal sort;
 
	private String data1;
 
	private String data2;
 
	private String data3;
 
	private BigDecimal data4;
 
	private BigDecimal data5;
 
	private String remark;
 
	//private String useYn;
    private AXBootTypes.Used useYn = AXBootTypes.Used.YES;
    

	private String systemCodeYn;
	

	private String delYn;
 
 
	public static BasicCodeDetailVO of(BasicCodeDetail eo) {
		BasicCodeDetailVO vo = ModelMapperUtils.map(eo, BasicCodeDetailVO.class);
        return vo;
    }

    public static List<BasicCodeDetailVO> of(List<BasicCodeDetail> eoList) {
        List<BasicCodeDetailVO> vtoList = new ArrayList<>();

        for (BasicCodeDetail object : eoList) {
            vtoList.add(of(object));
        }

        return vtoList;
    }
}