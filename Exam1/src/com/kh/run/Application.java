package com.kh.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	
	// serviceKey 값 필요(제공받은 인코딩 키)
	public static final String serviceKey = "E2Yxi7LY8WT0So4ug5OHep%2FWznH5ws3FDEEVJAJnmib%2BfEXOT5EOZGeoHfr0CcFQNAb8jdXV%2FeuOW8DJXClF8g%3D%3D";

	public static void main(String[] args) throws IOException {
		
		// URL 
		String url = "https://apis.data.go.kr/1543061/animalShelterSrvc/shelterInfo";
		
		 url += "?serviceKey=" + serviceKey;
		 
		 // 다른 파라미터 값 추가하고 싶으면?
		 url += "&numOfRows=5";  // 한페이지 결과 수 (여기서는 total로 가져왔음)
		 url += "&_type=json"; // 기본값 : xml 
		 
		 URL requestUrl = new URL(url);
		 HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		 urlConnection.setRequestMethod("GET");
		 
		 BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		 String line = null;
		 
		 
		 StringBuffer responseBuffer = new StringBuffer(); 
		 
		 while((line = br.readLine())!=null) {
			 // null이 아닐때까지 한 줄씩 읽어오기 
//			 System.out.println(line);
			 responseBuffer.append(line); // 한 줄씩 담기
		 }
		 
		 br.close();
		 urlConnection.disconnect();
		 
		 
		 // JSON 파싱 시작
		 String responseData = responseBuffer.toString();   // 스트링으로 변환
		 JSONObject jsonResponse = new JSONObject(responseData);
		 
		 // 구조 파악
		 // response(객체) -> body(객체) => items(객체) => item(배열)
		 JSONObject response = jsonResponse.getJSONObject("response");
		 JSONObject body = response.getJSONObject("body");
		 JSONObject items = body.getJSONObject("items");
		 JSONArray item = items.getJSONArray("item");
		 
//		 System.out.println(item);
		 
		 for(int i=0; i<item.length(); i++) {
			 JSONObject result = item.getJSONObject(i);
//			 System.out.println(result);
			 
			 // 필요값만 가져오기
			 String careNm = result.getString("careNm");
			 String careAddr = result.getString("careAddr");
			 System.out.println("동물보호센터명 : " + careNm);
			 System.out.println("주소 : " + careAddr);
			 System.out.println();
		 }
	}

}
