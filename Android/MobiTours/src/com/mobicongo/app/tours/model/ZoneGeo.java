package com.mobicongo.app.tours.model;

public class ZoneGeo {
	
	private int zoneGeoId;
	private String nameZoneGeo;
	private String etenduZoneGeo;
	private String descZoneGeo;
	private float latzone;
	private float lngzone;
	private String areazone;

	
	
	public ZoneGeo(){
		//Null constructor
	}
	
	public ZoneGeo(int zoneGeoId, String nameZoneGeo, String descZoneGeo){
		this.zoneGeoId=zoneGeoId;
		this.nameZoneGeo=nameZoneGeo;
		this.descZoneGeo=descZoneGeo;
	}
	
	public int getZoneGeoId(){
		return zoneGeoId;
	}
	public void setZoneGeoId(int zoneGeoId){
		this.zoneGeoId=zoneGeoId;
	}
	
	public String getNameZoneGeo(){
		return nameZoneGeo;
	}
	public void setNameZoneGeo(String nameZoneGeo){
		this.nameZoneGeo=nameZoneGeo;
	}
	
	public String getDescZoneGeo(){
		return descZoneGeo;
	}
	public void setDescZoneGeo(String descZoneGeo){
		this.descZoneGeo=descZoneGeo;
	}

	public String getEtenduZoneGeo() {
		return etenduZoneGeo;
	}

	public void setEtenduZoneGeo(String etenduZoneGeo) {
		this.etenduZoneGeo = etenduZoneGeo;
	}
}
