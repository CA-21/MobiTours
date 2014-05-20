package com.mobicongo.app.tours.model;


public class Hotel extends PointOfInterest{

	private int star;
	private int nbroom;
	private int nbroomdispo;
	private double roompricemin;
	private double roompricemax;
	private int idhotel;
	private String email;
	private String url;
	
	
	public Hotel(){
		//Null constructor
	}
	
	//not null constructor
	public Hotel(int idhotel,int star, int nbroom, int nbroomdispo, double roompricemin, double roompricemax, String email, String url,
			String title, String desc, String adresse, Type type, String pictureAttr, String picture, double latitude, double longitude, int idcity){
		super(title,desc,type,pictureAttr,picture,latitude,longitude,idcity,adresse);
		
		this.idhotel=idhotel;
		this.nbroom=nbroom;
		this.nbroomdispo=nbroomdispo;
		this.roompricemin=roompricemin;
		this.roompricemax=roompricemax;
		this.star=star;
		this.email=email;
		this.url=url;
		
	}
		
	
	public int getIdhotel(){
		return idhotel;
	}
	
	public void setIdHotel(int idhotel){
		this.idhotel=idhotel;
	}
	
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public int getNbroom() {
		return nbroom;
	}
	public void setNbroom(int nbroom) {
		this.nbroom = nbroom;
	}
	public double getRoompricemin() {
		return roompricemin;
	}
	public void setRoompricemin(double roompricemin) {
		this.roompricemin = roompricemin;
	}
	
	public double getRoompricemax() {
		return roompricemax;
	}
	public void setRoompricemax(double roompricemax) {
		this.roompricemax = roompricemax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setIdhotel(int idhotel) {
		this.idhotel = idhotel;
	}

	public int getNbroomdispo() {
		return nbroomdispo;
	}

	public void setNbroomdispo(int nbroomdispo) {
		this.nbroomdispo = nbroomdispo;
	}

	
	
}
