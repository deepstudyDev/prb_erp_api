package com.prb.erp.domain.code.master;

import java.util.ArrayList;
import java.util.List;

import com.chequer.axboot.core.utils.ModelMapperUtils;
import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class BasicCodeMasterVO extends BaseVO {

	private String company;
	private String plant;
	private String mainCode; 
	private String mainName; 
	private String systemCodeYn;
	private String publicYn;
	private String useYn;

    public static BasicCodeMasterVO of(BasicCodeMaster eo) {
        return ModelMapperUtils.map(eo, BasicCodeMasterVO.class);
    }


    public static List<BasicCodeMasterVO> of(List<BasicCodeMaster> eoList) {
        List<BasicCodeMasterVO> vtoList = new ArrayList<>();
        for (BasicCodeMaster object : eoList) {
            vtoList.add(of(object));
        }
        return vtoList;
    }
 
}