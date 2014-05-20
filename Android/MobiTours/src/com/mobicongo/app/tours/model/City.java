package com.mobicongo.app.tours.model;

import java.sql.Blob;

public class City {

	private String cityname; 
	private String cityDesc;
	private Integer idcity;
	private int idzone;
	private double latitude;
	private double longitude;
	private int altitude;
	private Blob cityImg;
	
	/*public String getCityImgURL() {
		return cityImgURL;
	}

	public void setCityImgURL(String cityImgURL) {
		this.cityImgURL = cityImgURL;
	}*/

	//Constructor
	public City(String cityname, Integer idcity, int idzone,
			double latitude, double longitude, int altitude, Blob cityimg) {
		super();
		this.cityname = cityname;
		this.idcity = idcity;
		this.idzone = idzone;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.cityImg=cityimg;
		
	}
	
	public City(){
		//Null constructor
	}
	
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public String getCityDesc() {
		return cityDesc;
	}

	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}

	public Integer getIdcity() {
		return idcity;
	}
	public void setIdcity(Integer idcity) {
		this.idcity = idcity;
	}
	
	public int getIdzone() {
		return idzone;
	}

	public void setIdzone(int idzone) {
		this.idzone = idzone;
	}

	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}

	public Blob getCityImg() {
		return cityImg;
	}

	public void setCityImg(Blob cityImg) {
		this.cityImg = cityImg;
	}
	

//	`category_city_id` int(11) not null,
//	`zonegeo_id` int(11) not null UNIQUE,
//	`kml_file` object,


	
}

