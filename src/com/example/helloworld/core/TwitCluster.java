package com.example.helloworld.core;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TwitCluster {
	double latitude;
	double longitude;
	int count;
	
	public TwitCluster(double lat,double longi,int count){
		this.latitude=lat;
		this.longitude=longi;
		this.count=count;
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
	
	@JsonProperty
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
}
