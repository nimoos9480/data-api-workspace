package com.kh.opendata.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class APIController {
// api 불러와서 그냥 화면에 띄우는 역할을 할 것
	
	// 메소드 호출을 받는 부분이 있어야 함
	
	public static final String serviceKey = "UNmnLkcNtzgMKivBzvyb3TdrsqmthwquJWOHYpkKXK6aXtSdhG1gbTQ6EOqATL5t3ApCJ2hySkej4pCTXuaAZg%3D%3D";
	
	// api 정보 보내는 부분
	@ResponseBody
	@RequestMapping(value="api.do", produces="application/json; charset=utf-8")  // api.do를 호출할 때 여기로 올 수 있게 함
	public String apiInfo(String stnId) throws IOException {
		// URL 
		String url = "https://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst";
		
		// 시험 => 파라미터값 어떻게 전달하는지 파악하는 것이 중요 (사이트에서 요구하는대로 작성할 것)
		url += "?serviceKey=" + serviceKey;
		url += "&tmFc=202310190600";
		url += "&dataType=JSON";
		url += "&stnId=" + stnId; 	
		// 파라미터값이 숫자일때는 그냥 가져오지만 만약에 파라미터값이 한글이면 utf-8 인코딩을 해줘야 함
//		String stnName = "서울";
//		url += "&stnName=" + URLEncoder.encode(stnName, "UTF-8");
					 
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
					 
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		
		String responseText = "";
		
		while((line = br.readLine())!=null) {
			responseText += line;
		}
		br.close();
		urlConnection.disconnect();
		
		System.out.println(responseText);
		
		return responseText;
	}
	
}
