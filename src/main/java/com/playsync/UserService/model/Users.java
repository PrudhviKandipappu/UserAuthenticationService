package com.playsync.UserService.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.playsync.UserService.dto.Role;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@Document(collection = "users")
public class Users {

	@Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String mobileNumber;
    private int age;
    private String address;
    private String whatsappNumber;
    private int experience;
    private List<String> likedSports;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;
    
    @CreatedDate
    private Date createdAt;
    
    @LastModifiedDate
    private Date updatedAt;

    private List<Role> roles = new ArrayList<Role>();

    private Map<String, Integer> gamesPlayed = new HashMap<>();
    private Map<String, Integer> gamesWon = new HashMap<>();
    
    private Map<String, Integer> experiencePerSport = new HashMap<>();

    public void updateExperience(String sport, boolean won) {
        gamesPlayed.put(sport, gamesPlayed.getOrDefault(sport, 0) + 1);
        if (won) {
            gamesWon.put(sport, gamesWon.getOrDefault(sport, 0) + 1);
        }
        int played = gamesPlayed.get(sport);
        int wonGames = gamesWon.get(sport);
        experiencePerSport.put(sport, (wonGames * 100) / played);
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

	public int getExperience(String sport) {
        int played = gamesPlayed.getOrDefault(sport, 0);
        int won = gamesWon.getOrDefault(sport, 0);
        if (played == 0) return 0;
        return (won * 100) / played; 
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWhatsappNumber() {
		return whatsappNumber;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void setWhatsappNumber(String whatsappNumber) {
		this.whatsappNumber = whatsappNumber;
	}

	public List<String> getLikedSports() {
		return likedSports;
	}

	public void setLikedSports(List<String> likedSports) {
		this.likedSports = likedSports;
	}

	public GeoJsonPoint getLocation() {
		return location;
	}

	public void setLocation(GeoJsonPoint location) {
		this.location = location;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
