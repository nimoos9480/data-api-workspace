package com.kh.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {
	
	public static final String serviceKey = "E2Yxi7LY8WT0So4ug5OHep%2FWznH5ws3FDEEVJAJnmib%2BfEXOT5EOZGeoHfr0CcFQNAb8jdXV%2FeuOW8DJXClF8g%3D%3D"; 
	
	public static void main(String[] args) throws IOException {
		
		String url = "https://apis.data.go.kr/1741000/TsunamiShelter3/getTsunamiShelter1List";
		
		url += "?serviceKey=" + serviceKey;
		
		url += "&numOfRows=2";
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection) requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String line = null;
		
		StringBuffer responseBuffer = new StringBuffer();
		
		while((line= br.readLine())!=null) {
			System.out.println(line);
			responseBuffer.append(line);
		}
		
		br.close();
		urlConnection.disconnect();
	}
}
