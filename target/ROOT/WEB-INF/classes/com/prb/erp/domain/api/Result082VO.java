package com.prb.erp.domain.api;

import java.util.ArrayList;
import java.util.List;

import com.chequer.axboot.core.utils.ModelMapperUtils;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class Result082VO {
	
	private String custCd;	
	private String childCd;

	private String childImgUrl;
	private String childrenNm;	
	private String childrenSex;	
	private String childrenSexNm;
	private String childrenBirthday;	
	private String childrenHpNo;	
	private String childrenSchoolNm;	
	private String childrenGradeCd;	
	private String childrenGradeCdNm;	

	private String gd1Nm;
	private String gd1RelationCd;
	private String gd1RelationCdNm;
	private String gd1Birthday;
	private String gd1UserCd;
	private String gd2Nm;
	private String gd2RelationCd;
	private String gd2RelationCdNm;
	private String gd2Birthday;
	private String telNo;
	private String hpNo;
	private String email;

	private String homeZipcode;
	private String homeAddress1;
	private String homeAddress2;
	
	private List<ApiBrotherVO> brothers;


    public static Result082VO of(Object object) {
        return ModelMapperUtils.map(object, Result082VO.class);
    }
    public static List<Result082VO> of(List<Object> eoList) {
        List<Result082VO> vtoList = new ArrayList<>();

        for (Object object : eoList) {
            vtoList.add(of(object));
        }
        return vtoList;
    }
}