package com.example.helloworld.core;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tweets {
	long userid;
	String username;
	String status;
	double latitude;
	double longitude;
	String category;
	public Tweets(long id,String uname,String status,double latitude,double longitude, String category){
		this.userid=id;
		this.username=uname;
		this.status=status;
		this.latitude=latitude;
		this.longitude=longitude;
		this.category=category;
					
	}
	
	@JsonProperty
	public long getUserId(){
		return userid;
	}
	public void getUserId(long id){
		this.userid = id;
	}
    @JsonProperty
	public String getUserName() {
		return username;
	}

	public void setUserName(String uname) {
		this.username = uname;
	}
    @JsonProperty
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    @JsonProperty
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
    @JsonProperty
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
  
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}

