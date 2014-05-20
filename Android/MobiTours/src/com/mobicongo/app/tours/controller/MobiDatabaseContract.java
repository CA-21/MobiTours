/**
 * 
 */
package com.mobicongo.app.tours.controller;

import android.provider.BaseColumns;

/**
 * @author Mishka
 *
 */
public class MobiDatabaseContract {

	
	public static final String COLUMN_ID="_id";
	
	public static abstract class GeoZone implements BaseColumns{
		public static final String TABLE_NAME="zonegeo";
		public static final String COLUMN_ZONEID="zoneGeoId";
		public static final String COLUMN_NAME_ZG="nameZoneGeo";
		public static final String COLUMN_DESC_ZG="descZoneGeo";
		public static final String COLUMN_ETENDU="etenduZoneGeo";
		public static final String COLUMN_LAT_ZONE="latzone";
		public static final String COLUMN_LNG_ZONE="lngzone";
		public static final String COLUMN_AREA="areazone";
		
		 //prevent instantiation
        private GeoZone() {}
	}
	
	public static abstract class Image implements BaseColumns{
		public static final String TABLE_NAME="image";
		//public static final String COLUMN_IMAGEID="imageid";
		public static final String COLUMN_POIID="pointofinterestid";
		public static final String COLUMN_URL="url";
		public static final String COLUMN_NAME="name";
		public static final String COLUMN_IMAGE="image";
		public static final String COLUMN_IMAGE_TYPE="imagetype";
		
		private Image(){}
	}
	
	public static abstract class CourtierFull implements BaseColumns{	
		public static final String TABLE_NAME="courtier";
		public static final String COLUMN_EMAIL="email";
		public static final String COLUMN_PASSWORD="password";
		public static final String COLUMN_TITLE="title";
		public static final String COLUMN_LASTNAME="lastname";
		public static final String COLUMN_FIRSTNAME="firstname";
		public static final String COLUMN_WEBSITE="website";
		public static final String COLUMN_CITY="city";
		public static final String COLUMN_COUNTRY="country";
		public static final String COLUMN_COMPANY="company";
		public static final String COLUMN_PHONE="phone";
		public static final String COLUMN_BIOGRAPHY="biography";
		public static final String COLUMN_COURTIERID="courtierid";
		public static final String COLUMN_SPECs="specification";
		private CourtierFull(){}
	}
	
	public static abstract class Courtier implements BaseColumns{	
		public static final String TABLE_NAME="courtier";
		public static final String COLUMN_COURTIERID="courtierid";
		public static final String COLUMN_EMAIL="email";
		public static final String COLUMN_NAME="name";
		public static final String COLUMN_WEBSITE="website";
		public static final String COLUMN_COUNTRY="country";
		public static final String COLUMN_PHONE="phone";
		private Courtier(){}
	}
	
	public static final String DB_CREATE_COURTIER=
			"CREATE TABLE if not exists " + Courtier.TABLE_NAME + "(" +
			COLUMN_ID + " integer PRIMARY KEY autoincrement," +
			Courtier.COLUMN_COURTIERID + " integer not null, " +
			Courtier.COLUMN_EMAIL + " text, " +
			Courtier.COLUMN_NAME + " text, " +
			Courtier.COLUMN_WEBSITE + " text, " +
			Courtier.COLUMN_COUNTRY + " text, "+
			Courtier.COLUMN_PHONE + " text)";
	
	
	public static final String[] PROJECTION_COURTIER=new String[]{
		COLUMN_ID,Courtier.COLUMN_COURTIERID,Courtier.COLUMN_EMAIL,Courtier.COLUMN_NAME,Courtier.COLUMN_WEBSITE,
		Courtier.COLUMN_COUNTRY,Courtier.COLUMN_PHONE
	};
			
	
		
	public static abstract class User implements BaseColumns{
		public static final String TABLE_NAME="user";
		//from Person.class
		public static final String COLUMN_EMAIL="email";
		public static final String COLUMN_PASSWORD="password";
		public static final String COLUMN_TITLE="title";
		public static final String COLUMN_LASTNAME="lastname";
		public static final String COLUMN_FIRSTNAME="firstname";
		public static final String COLUMN_WEBSITE="website";
		public static final String COLUMN_CITY="city";
		public static final String COLUMN_COUNTRY="country";
		public static final String COLUMN_COMPANY="company";
		public static final String COLUMN_PHONE="phone";
		public static final String COLUMN_BIOGRAPHY="biography";
		public static final String COLUMN_USERID="userid";
		public static final String COLUMN_UID="uid";
		private User(){}
	}
	
		
	public static abstract class Hotel implements BaseColumns{
		public static final String TABLE_NAME="hotel";
		public static final String COLUMN_POIID="pointinterestid";
		public static final String COLUMN_TYPE="type";
		public static final String COLUMN_TITLE="title";
		public static final String COLUMN_DESC="description";
		public static final String COLUMN_ADRESS="adresse";
		public static final String COLUMN_LATITUDE="latitude";
		public static final String COLUMN_LONGITUDE="longitude";
		public static final String COLUMN_IDCITY="idcity";
		public static final String COLUMN_PICTUREURL="pictureurl";
		public static final String COLUMN_URL="url";
		public static final String COLUMN_MAIL="mail";
		public static final String COLUMN_STAR="star";
		public static final String COLUMN_NBROOM="room";
		public static final String COLUMN_NBROOM_DISPO="nbroomdispo";
		public static final String COLUMN_ROOMPRICEMIN="roompricemin";
		public static final String COLUMN_ROOMPRICEMAX="roompricemax";
		private Hotel(){}
	}
	
	public static final String[] PROJECTION_HOTEL=new String[]{
		COLUMN_ID,Hotel.COLUMN_POIID,Hotel.COLUMN_TYPE, Hotel.COLUMN_TITLE, Hotel.COLUMN_DESC, Hotel.COLUMN_ADRESS, Hotel.COLUMN_LATITUDE,
		Hotel.COLUMN_LONGITUDE,Hotel.COLUMN_IDCITY,Hotel.COLUMN_PICTUREURL,Hotel.COLUMN_URL,Hotel.COLUMN_MAIL,Hotel.COLUMN_STAR,
		Hotel.COLUMN_NBROOM, Hotel.COLUMN_NBROOM_DISPO, Hotel.COLUMN_ROOMPRICEMIN, Hotel.COLUMN_ROOMPRICEMAX
	};
	
	public static final String DB_CREATE_HOTEL=
			"CREATE TABLE if not exists " + Hotel.TABLE_NAME +" (" +
				COLUMN_ID + " integer PRIMARY KEY autoincrement," +
				Hotel.COLUMN_POIID + " integer not null," +
				Hotel.COLUMN_TYPE + " text not null, " +
				Hotel.COLUMN_TITLE + " text not null, " +
				Hotel.COLUMN_DESC + " text, " + 
				Hotel.COLUMN_ADRESS + " text, " + 
				Hotel.COLUMN_LATITUDE + " double," +
				Hotel.COLUMN_LONGITUDE + " double," +
				Hotel.COLUMN_IDCITY + " integer not null," +
				Hotel.COLUMN_PICTUREURL + " text," +
				Hotel.COLUMN_URL + " text," +
				Hotel.COLUMN_MAIL + " text," +
				Hotel.COLUMN_STAR + " integer, " +
				Hotel.COLUMN_NBROOM + " integer," +
				Hotel.COLUMN_NBROOM_DISPO + " integer," +
				Hotel.COLUMN_ROOMPRICEMIN + " double," +
				Hotel.COLUMN_ROOMPRICEMAX + " double )"; 
	
	public static final String DB_CREATE_IMAGE=
			"CREATE TABLE if not exists " + Image.TABLE_NAME + "(" +
		    COLUMN_ID + " integer PRIMARY KEY autoincrement, " +
		    Image.COLUMN_POIID + " integer not null, " +
			Image.COLUMN_NAME + " text, " +
			Image.COLUMN_URL + " text , " +
		    Image.COLUMN_IMAGE + " BLOB, " +
			Image.COLUMN_IMAGE_TYPE + " text )";
	
	public static abstract class OtherPOI implements BaseColumns{
		public static final String TABLE_NAME="OtherPOI";
		public static final String COLUMN_POIID="pointinterestid";
		public static final String COLUMN_TYPE="type";
		public static final String COLUMN_TITLE="title";
		public static final String COLUMN_DESC="description";
		public static final String COLUMN_LATITUDE="latitude";
		public static final String COLUMN_LONGITUDE="longitude";
		public static final String COLUMN_IDCITY="idcity";
		public static final String COLUMN_PICTUREURL="pictureurl";
		public static final String COLUMN_URL="url";
		public static final String COLUMN_POI_SPEC="specification";
		private OtherPOI(){}
	}
		
	public static final String DB_CREATE_OTHERPOI=
			"CREATE TABLE if not exists " + OtherPOI.TABLE_NAME + "(" +
			COLUMN_ID + " integer PRIMARY KEY autoincrement, " +
			OtherPOI.COLUMN_POIID + " integer not null, " +
			OtherPOI.COLUMN_TYPE + " text not null, " +
			OtherPOI.COLUMN_TITLE + " text not null, " +
			OtherPOI.COLUMN_DESC + " text, " +
			OtherPOI.COLUMN_LATITUDE + " double, " +
			OtherPOI.COLUMN_LONGITUDE + " double, " +
			OtherPOI.COLUMN_IDCITY + " integer not null, " +
			OtherPOI.COLUMN_URL + " text, " +
			OtherPOI.COLUMN_PICTUREURL + " text, " +
			OtherPOI.COLUMN_POI_SPEC + " text )";
			
	public static abstract class SiteNaturel implements BaseColumns{
		public static final String TABLE_NAME="SiteNaturel";
		public static final String COLUMN_IDSITE="idsite";
		public static final String COLUMN_TYPE="type";
		public static final String COLUMN_AREA="area";
		public static final String COLUMN_SITEDESC="sitedesc";
		public static final String COLUMN_ATTRACT_TOUR="attractTourist";
		public static final String COLUMN_LARGEUR="largeur";
		public static final String COLUMN_LONGUEUR="longueur";
		public static final String COLUMN_VISITOR="visitorperan";
		public static final String COLUMN_SECURITY="security";
		public static final String COLUMN_LATITUDE="latitude";
		public static final String COLUMN_LONGITUDE="longitude";
		public static final String COLUMN_IDCITY="idcity";
		public static final String COLUMN_TITLE="title";
		public static final String COLUMN_SITE_IMG_URL="site_url_image";
		private SiteNaturel(){}
	}
	
		
	public static final String DB_CREATE_SITENATURE=
			"CREATE TABLE if not exists " + SiteNaturel.TABLE_NAME + "(" +
			COLUMN_ID + " integer PRIMARY KEY autoincrement, " +
			SiteNaturel.COLUMN_IDSITE + " integer not null, " +
			SiteNaturel.COLUMN_TYPE + " text not null, " +
			SiteNaturel.COLUMN_TITLE + " text not null, " +
			SiteNaturel.COLUMN_AREA + " double, " + 
			SiteNaturel.COLUMN_SITEDESC + " text, " +
			SiteNaturel.COLUMN_ATTRACT_TOUR + " text, " +
			SiteNaturel.COLUMN_LARGEUR + " double, " +
			SiteNaturel.COLUMN_LONGUEUR + " double, " +
			SiteNaturel.COLUMN_VISITOR + " integer, " +
			SiteNaturel.COLUMN_IDCITY + " integer not null, " +
			SiteNaturel.COLUMN_SECURITY + " text, " +
			SiteNaturel.COLUMN_SITE_IMG_URL + " text, " +
			SiteNaturel.COLUMN_LATITUDE + " double, " +
			SiteNaturel.COLUMN_LONGITUDE + " double )";
	
	public static final String[] PROJECTION_SITENATUREL=new String[]{
		COLUMN_ID, SiteNaturel.COLUMN_IDSITE, SiteNaturel.COLUMN_TYPE, SiteNaturel.COLUMN_AREA, SiteNaturel.COLUMN_SITEDESC,
		SiteNaturel.COLUMN_ATTRACT_TOUR, SiteNaturel.COLUMN_LARGEUR, SiteNaturel.COLUMN_LONGUEUR, SiteNaturel.COLUMN_VISITOR,
		SiteNaturel.COLUMN_SECURITY, SiteNaturel.COLUMN_LATITUDE, SiteNaturel.COLUMN_LONGITUDE, SiteNaturel.COLUMN_IDCITY,
		SiteNaturel.COLUMN_TITLE, SiteNaturel.COLUMN_SITE_IMG_URL
	};
	
	public static abstract class Booking implements BaseColumns{
		public static final String TABLE_NAME="Booking";
		public static final String COLUMN_BOOKINGID="bookingid";
		public static final String COLUMN_BOOKINGDESC="bookingdesc";
		public static final String COLUMN_DATETRIP="datetrip";
		public static final String COLUMN_DURATION="duration";
		public static final String COLUMN_PAYMENTMODE="paymentmode";
		public static final String COLUMN_USERID="userid";
		public static final String COLUMN_COURTIERID="courtierid";
		public static final String COLUMN_POINTOFINTERESTID="pointofinterestid";
		public static final String COLUMN_IDSITE="idsite";
		private Booking(){}
	}
	
		
	public static final String DB_CREATE_BOOKING=
			"CREATE TABLE if not exists " + Booking.TABLE_NAME + "(" +
	        COLUMN_ID + " integer PRIMARY KEY autoincrement, " +
	        Booking.COLUMN_BOOKINGID + " integer not null, " +
	        Booking.COLUMN_BOOKINGDESC + " text, " +
	        Booking.COLUMN_DATETRIP + " text, " +
	        Booking.COLUMN_DURATION + " integer, " +
	        Booking.COLUMN_PAYMENTMODE + " text, " +
	        Booking.COLUMN_USERID + " integer, " +
	        Booking.COLUMN_COURTIERID + " integer, " +
	        Booking.COLUMN_POINTOFINTERESTID + " integer, " +
	        Booking.COLUMN_IDSITE + " integer )";
	
	public static abstract class Comments implements BaseColumns{
		public static final String TABLE_NAME="Comments";
		//public static final String COLUMN_COMMENTID="commentid";
		public static final String COLUMN_TEXT="text";
		public static final String COLUMN_DATECOMMENT="date";
		public static final String COLUMN_USERID="userid";
		public static final String COLUMN_POIID="pointofinterestid";
		public static final String COLUMN_IDSITE="idsite";
		private Comments(){}
	}
	
	public static final String DB_CREATE_COMMENTS=
			"CREATE TABLE if not exists " + Comments.TABLE_NAME + "(" +
	        COLUMN_ID + " integer PRIMARY KEY autoincrement," +
	        Comments.COLUMN_TEXT + " text, " +
	        Comments.COLUMN_DATECOMMENT + " text, " +
	        Comments.COLUMN_USERID + " integer, " +
	        Comments.COLUMN_POIID + " integer, " +
	        Comments.COLUMN_IDSITE + " integer )";
	
	
	public static abstract class Rating implements BaseColumns{
		public static final String TABLE_NAME="Rating";
		//public static final String COLUMN_RATINGID="ratingid";
		public static final String COLUMN_RATING="rating";
		public static final String COLUMN_USERID="userid";
		public static final String COLUMN_POIID="pointofinterestid";
		public static final String COLUMN_IDSITE="idsite";
		private Rating(){}
	}
	
	public static final String DB_CREATE_RATING=
			"CREATE TABLE if not exists " + Rating.TABLE_NAME + "(" +
	        COLUMN_ID + " integer PRIMARY KEY autoincrement, " +
	       // Rating.COLUMN_RATINGID + " integer not null, " +
	        Rating.COLUMN_RATING + " integer not null, " +
	        Rating.COLUMN_USERID + " integer, " +
	        Rating.COLUMN_POIID + " integer, " +
	        Rating.COLUMN_IDSITE + " integer )";
	
	/*
	 *  All string for get data from table
	 * 
	 */
	
	public static final String[] PROJECTION_OTHERPOI=new String[]{
		COLUMN_ID, OtherPOI.COLUMN_POIID, OtherPOI.COLUMN_TYPE, OtherPOI.COLUMN_TITLE, OtherPOI.COLUMN_DESC, OtherPOI.COLUMN_LATITUDE,
		OtherPOI.COLUMN_LONGITUDE, OtherPOI.COLUMN_IDCITY, OtherPOI.COLUMN_PICTUREURL, OtherPOI.COLUMN_URL, OtherPOI.COLUMN_POI_SPEC
	};
		
	public static final String[] PROJECTION_BOOKING=new String[]{
		COLUMN_ID, Booking.COLUMN_BOOKINGID, Booking.COLUMN_BOOKINGDESC, Booking.COLUMN_DATETRIP, Booking.COLUMN_DURATION,
		Booking.COLUMN_PAYMENTMODE, Booking.COLUMN_USERID, Booking.COLUMN_COURTIERID, Booking.COLUMN_POINTOFINTERESTID, Booking.COLUMN_IDSITE
	};
	
	public final static String[] PROJECTION_COMMENTS=new String[]{
		COLUMN_ID, Comments.COLUMN_TEXT, Comments.COLUMN_DATECOMMENT,
		Comments.COLUMN_USERID, Comments.COLUMN_POIID, Comments.COLUMN_IDSITE
	};
	
	public static final String[] PROJECTION_RATING=new String[]{
		COLUMN_ID, Rating.COLUMN_RATING, Rating.COLUMN_USERID, Rating.COLUMN_POIID, Rating.COLUMN_IDSITE 
	};
	
	public static final String[] PROJECTION_DATA_IMAGE=new String[]{
		COLUMN_ID, Image.COLUMN_POIID, Image.COLUMN_NAME, Image.COLUMN_URL, Image.COLUMN_IMAGE, Image.COLUMN_IMAGE_TYPE
	};
	//To get only one image
	public static final String[] PROJECTION_GET_ONE_IMAGE=new String[]{
		COLUMN_ID,
		Image.COLUMN_POIID, 
		Image.COLUMN_IMAGE
	};
	
	
	public static final String[] PROJECTION_RESTAURANT=new String[]{
		
	};

	
	
}
