package com.kh.practice.contorller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class APIController {
	public static final String serviceKey = "UNmnLkcNtzgMKivBzvyb3TdrsqmthwquJWOHYpkKXK6aXtSdhG1gbTQ6EOqATL5t3ApCJ2hySkej4pCTXuaAZg%3D%3D";
	
	@ResponseBody
	@RequestMapping(value="api.do", produces="application/json; charset=utf-8")
	public String apiInfo(String stnId) throws IOException {
		String url = "https://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst";
		
		url += "?serviceKey=" + serviceKey;
		url += "&tmFc=202310190600";
		url += "&dataType=JSON";
		url += "&stnId=" +stnId;
		
//		String stnName="서울";
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
