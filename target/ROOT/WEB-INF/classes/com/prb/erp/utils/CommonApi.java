package com.prb.erp.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.member.MemberSchoolVO;

@Service
public class CommonApi{
	
	//학교검색
	public static String getSchoolListApi(RequestParams<MemberSchoolVO> vo){	
		
		String schoolRegion = vo.getString("schoolRegion","");
		String searchSchulNm = vo.getString("searchSchulNm","");
		String gubun = vo.getString("gubun","");
		String perPage = vo.getString("perPage","");

		//String apiKey = "182bdd0c7e5b66a12b67bcac32e23e17";
		String apiKey = "39f0044d85cb9097de8be667f87029f7";
		
		
		String apiUrl = "http://www.career.go.kr/cnet/openapi/getOpenApi?svcType=api&svcCode=SCHOOL&contentType=json&apiKey=" + apiKey;
		
		if (StringUtils.isNotEmpty(schoolRegion)) {
        	apiUrl = apiUrl + "&schoolRegion=" + schoolRegion;
        }
        if (StringUtils.isNotEmpty(searchSchulNm)) {
        	apiUrl = apiUrl + "&searchSchulNm=" + searchSchulNm;        	
        }
        if (StringUtils.isNotEmpty(gubun)) {
        	apiUrl = apiUrl + "&gubun=" + gubun;        	
        }
        if (StringUtils.isNotEmpty(perPage)) {
        	apiUrl = apiUrl + "&perPage=" + perPage;        	
        }
        
        
    	RestTemplate rest = new RestTemplate();
		return rest.getForObject(apiUrl,  String.class);
	}
} 