package com.playsync.UserService.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class GeocodingService {
	
//	@Value("${gomaps.maps.api-key}")
//	private String gomapsApiKey;
	private String openWeatherMapApiKey = "abe3a0f4d0b6cebfbe7393b4b4e3aa28";
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public double[] getCoordinatesFromAddress(String address) {
		try {
			String url = "https://api.openweathermap.org/data/2.5/weather?q=" + address.replace(" ", "+") + "&appid=" + openWeatherMapApiKey + "&units=metric";
			
			String response = restTemplate.getForObject(url, String.class);
			JSONObject json = new JSONObject(response);
			JSONObject location = json.getJSONObject("coord");
			
			return new double[] {location.getDouble("lat"), location.getDouble("lon")};
		}
		catch(Exception e) {
			e.printStackTrace();
			
			return new double[] {0,0};
		}
	}
}
