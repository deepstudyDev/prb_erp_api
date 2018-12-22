package com.prb.erp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.prb.erp.domain.sms.master.SendResultVO;

@Service
public class SmsSendUtils{
	
	//알리고 팔피엠
	public static String testApi(){			
			String apiUrl = "http://www.career.go.kr/cnet/openapi/getOpenApi?apiKey=182bdd0c7e5b66a12b67bcac32e23e17&svcType=api&svcCode=SCHOOL&contentType=json&gubun=elem_list";
	 		RestTemplate rest = new RestTemplate();
			return rest.getForObject(apiUrl,  String.class);
	}

	//알리고 팔피엠
	public static SendResultVO sendMsg2(String senderNo,String hpNo, String sendMessage){
		
		String apiUrl = "https://apis.aligo.in/send/";
		String sendApiKey = "dbnd2m1l1dsxex7nlpw0s18cwcfgtbbw";
		String sendUserId = "didhdzz";
		String msg = sendMessage; 
		String receiver = hpNo;
				
		MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();

		parameters.add("sender", senderNo);
		parameters.add("msg",msg);
		parameters.add("receiver", receiver);
		parameters.add("userid",sendUserId);
		parameters.add("key", sendApiKey);

		HttpHeaders headers = new HttpHeaders();
 
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameters, headers);

		RestTemplate rest = new RestTemplate();
		String result = rest.postForObject(apiUrl, request, String.class);			
		
		SendResultVO vo = JsonUtils.stringToObject(result, SendResultVO.class);
		return vo;
	}

	//프뢰벨-팔피엠 (문자관리)
	public static SendResultVO sendMsg(String senderNo,String hpNo, String sendMessage , String reserveTime){
		SendResultVO vo = new SendResultVO();
		String msg = sendMessage; 
		String receiver = hpNo;
		
		Map<String ,Object> paramMap = new HashMap();
		paramMap.put("send_number", senderNo.replace("-",""));
		paramMap.put("receive_number", receiver.replace("-",""));
		paramMap.put("message",msg);
		paramMap.put("reserve_time",reserveTime);
		
		List<Map<String ,Object>> list = new ArrayList<>();
		list.add(paramMap);
		
		try{
			String callUrl = "http://211.62.104.179:8080/mindedu-api/message/send_sms";
			String jsonBody = JsonUtils.convertToJsonStr(list);
			String cookie = "JSESSIONID=9kfla4mo73iq561r0u6oqba98n";			

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(callUrl);
			StringEntity stringEntity = new StringEntity(jsonBody, "UTF-8");
			post.setHeader("content-type", "application/json");
			post.setHeader("Cookie", cookie);
			post.setEntity(stringEntity);

			HttpResponse response = client.execute(post);
			String json = EntityUtils.toString(response.getEntity(), "UTF-8");					
			vo = JsonUtils.stringToObject(json, SendResultVO.class);						
		}catch(Exception e){
			
		}
		return vo;
	}
	

	//프뢰벨-팔피엠 (사용자등록시 패스워드 전송)
	public static SendResultVO sendPwMsg(String hpNo ,String id, String pw){
		SendResultVO vo = new SendResultVO();
		String msg = "비밀번호가 초기화 되었습니다. 아이디[ "+id+" ] 비밀번호[ "+pw+" ]"; 
		String receiver = hpNo;
		
		Map<String ,Object> paramMap = new HashMap();
		paramMap.put("send_number", "15660800");
		paramMap.put("receive_number", receiver.replace("-",""));
		paramMap.put("message",msg);
		paramMap.put("reserve_time","");
		
		List<Map<String ,Object>> list = new ArrayList<>();
		list.add(paramMap);
		
		try{
			String callUrl = "http://211.62.104.179:8080/mindedu-api/message/send_sms";
			String jsonBody = JsonUtils.convertToJsonStr(list);
			String cookie = "JSESSIONID=9kfla4mo73iq561r0u6oqba98n";			

			HttpClient client = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(callUrl);
			StringEntity stringEntity = new StringEntity(jsonBody, "UTF-8");
			post.setHeader("content-type", "application/json");
			post.setHeader("Cookie", cookie);
			post.setEntity(stringEntity);

			HttpResponse response = client.execute(post);
			String json = EntityUtils.toString(response.getEntity(), "UTF-8");			

			vo = JsonUtils.stringToObject(json, SendResultVO.class);						
		}catch(Exception e){
			
		}
		return vo;
	}
} 