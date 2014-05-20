/***********************************************************************
 * Module:  SiteTouristique.java
 * Author:  Mishka
 * Purpose: Defines the Class SiteTouristique
 ***********************************************************************/
package com.mobicongo.app.tours.model;

public class SiteTouristique {

   public enum Type{
        PARCNATIONAL(30),
        RESERVENATUREL(210),
        DOMAINEDECHASSE(270);

        /** Hue of the marker to use for this type of POI. */
        final float mHue;

        private Type(float hue) {
            this.mHue = hue;
        }
   }

   private int idsite;
   private Type type;
   private Double area;
   private String sitedesc;
   private String attracTourist;
   private Double largeur;
   private Double longueur;
   private int visitorperan;
   private Boolean security;
   private Double latitude;
   private Double longitude;
   
	public int getIdsite() {
		return idsite;
	}
	public void setIdsite(int idsite) {
		this.idsite = idsite;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public String getSitedesc() {
		return sitedesc;
	}
	public void setSitedesc(String sitedesc) {
		this.sitedesc = sitedesc;
	}
	public String getAttracTourist() {
		return attracTourist;
	}
	public void setAttracTourist(String attracTourist) {
		this.attracTourist = attracTourist;
	}
	public Double getLargeur() {
		return largeur;
	}
	public void setLargeur(Double largeur) {
		this.largeur = largeur;
	}
	public Double getLongueur() {
		return longueur;
	}
	public void setLongueur(Double longueur) {
		this.longueur = longueur;
	}
	public int getVisitorperan() {
		return visitorperan;
	}
	public void setVisitorperan(int visitorperan) {
		this.visitorperan = visitorperan;
	}
	public Boolean getSecurity() {
		return security;
	}
	public void setSecurity(Boolean security) {
		this.security = security;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
   

}