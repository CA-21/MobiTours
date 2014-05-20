/***********************************************************************
 * Module:  OtherPOI.java
 * Author:  Mishka
 * Purpose: Defines the Class OtherPOI
 ***********************************************************************/
package com.mobicongo.app.tours.model;


public class OtherPOI extends PointOfInterest {
   
   private String specification;
   
   public OtherPOI(){
	   //null constructor
   }
   
   public OtherPOI(String spec,
		   String title, String desc, Type type, String adresse, String pictureAttr, String picture, double latitude, double longitude, int idcity){
		super(title,desc,type,pictureAttr,picture,latitude,longitude,idcity, adresse);
		  
		this.specification=spec;
		
   }

public String getSpecification() {
	return specification;
}

public void setSpecification(String specification) {
	this.specification = specification;
}

}