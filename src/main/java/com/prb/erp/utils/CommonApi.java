package com.prb.erp.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.member.MemberSchoolVO;

import java.net.URLEncoder;

@Service
public class CommonApi{

	protected static final Logger logger = LoggerFactory.getLogger(CommonApi.class);
	
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

	/**
	 * 프뢰벨 API POST 전송 메서드
	 * @param url
	 * @param postProperty (property=params)
	 * @param jsonBodyParam (json string)
	 * @return
	 * ex> http://test.com?postProperty=jsonBodyParam
	 */
	public static String froebelPostWayApi(String url, String postProperty, String jsonBodyParam) {
		if ("".equals(url) && "".equals(jsonBodyParam)) return null;

		String reultJson = null;
		HttpClient httpClient = HttpClientBuilder.create().build();
		logger.info("Froebel Api Call Start >>>> " + url);
		try {
			HttpPost httpPost = new HttpPost(url);
			StringEntity params = new StringEntity(postProperty + "=" + URLEncoder.encode(jsonBodyParam, "UTF-8"));
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.setEntity(params);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			reultJson = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			logger.info("result ::::: " + reultJson);
			return reultJson;
		} catch (Exception e) {
			logger.error("Froebel Api Error");
			e.printStackTrace();
		}
		return reultJson;
	}
} 