package com.playsync.UserService.dto;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.playsync.UserService.model.Users;

public class UserDto {
	
	private String id; 
    private String name;
    private List<String> preferredSports;
    private GeoJsonPoint location;
    
    private Map<String, Integer> gamesPlayed;
    
    private Map<String, Integer> gamesWon;

    private Map<String, Integer> experiencePerSport; 

    public UserDto(Users user) {
    	this.id = user.getId();
        this.name = user.getName();
        this.preferredSports = user.getLikedSports();
        this.gamesPlayed = user.getGamesPlayed();
        this.gamesWon = user.getGamesWon();
        this.experiencePerSport = user.getExperiencePerSport();
    }

	public Map<String, Integer> getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(Map<String, Integer> gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public Map<String, Integer> getGamesWon() {
		return gamesWon;
	}

	public void setGamesWon(Map<String, Integer> gamesWon) {
		this.gamesWon = gamesWon;
	}

	public Map<String, Integer> getExperiencePerSport() {
		return experiencePerSport;
	}

	public void setExperiencePerSport(Map<String, Integer> experiencePerSport) {
		this.experiencePerSport = experiencePerSport;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPreferredSports() {
		return preferredSports;
	}

	public void setPreferredSports(List<String> preferredSports) {
		this.preferredSports = preferredSports;
	}

	public GeoJsonPoint getLocation() {
		return location;
	}

	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}
}
