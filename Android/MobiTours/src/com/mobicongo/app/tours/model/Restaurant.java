package com.mobicongo.app.tours.model;


public class Restaurant extends PointOfInterest{

	private String mealofday;
	private double mealprice;
	private String classification;
	private int idResto;
	
	
	public Restaurant(){
		//Null constructor
	}
	
	//not null constructor
	public Restaurant(int idresto,String mealofday,double mealprice,String classification,
			String title, String desc, Type type, String adresse, String pictureAttr, String picture, double latitude, double longitude, int idcity){
		super(title,desc,type,pictureAttr,picture,latitude,longitude,idcity, adresse);
		
		this.mealofday=mealofday;
		this.idResto=idresto;
		this.classification=classification;
		this.mealprice=mealprice;
		
	}
	
	
	public int getIdResto() {
		return idResto;
	}
	public void setIdResto(int idResto) {
		this.idResto = idResto;
	}
	public String getMealofday() {
		return mealofday;
	}
	public void setMealofday(String mealofday) {
		this.mealofday = mealofday;
	}
	public double getMealprice() {
		return mealprice;
	}
	public void setMealprice(double mealprice) {
		this.mealprice = mealprice;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	
	
	
}
