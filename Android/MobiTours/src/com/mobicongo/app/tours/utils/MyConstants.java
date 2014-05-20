/**
 * 
 */
package com.mobicongo.app.tours.utils;


/**
 * @author Mishka
 *
 * this class will help to store basic URL, Basics methods and all basics values
 */

public class MyConstants {
		
	
		//Here the 10.0.2.2 is used because it localhost
		public static String BASE_URL="http://192.168.56.1:8080/mobitours/ws/";
		public static final String REST_RESULT = "com.mobitours.android.REST_RESULT";
		public static String myLocalURL/*="file:///android_asset/webapp.html"*/;
		
		public static final String url_get_all_geozone=MyConstants.BASE_URL+"get_all_geozone_in_db.php";
		public static final String url_get_all_cities=MyConstants.BASE_URL+"get_all_cities_in_db.php";
		public static final String url_get_all_hotel=MyConstants.BASE_URL+"get_all_hotel_in_db.php";
		public static final String url_get_all_rating=MyConstants.BASE_URL+"get_all_rate_in_db.php";
		public static final String url_get_all_courtier=MyConstants.BASE_URL+"get_all_courtier_in_db.php";
		public static final String url_get_restaurant="";
		public static final String url_get_sitenaturel=MyConstants.BASE_URL+"get_all_naturalsite_in_db.php";
		public static final String url_get_pointofinterest=MyConstants.BASE_URL+"";
		public static final String url_get_all_image=MyConstants.BASE_URL + "get_all_images_in_db.php";
		public static final String url_onInsert_new_comment=MyConstants.BASE_URL+"newcomments.php";
		public static final String url_onInsert_new_rate=MyConstants.BASE_URL+"newrating.php";
		//Variable to store the User ID of the current user
		public static int USER_ID;
	
		//JSON Nodes Name
		//City Table
		public static final String TAG_SUCCESS="success";
		public static final String TAG_CITY="thecity";
		public static final String tag_idcity="idcity";
		public static final String tag_idzone="idzone";
		public static final String tag_namecity="namecity";
		public static final String tag_desc="desccity";
		public static final String tag_latitude="latcity";
		public static final String tag_longitude="lngcity";
		public static final String tag_altitude="altcity";
		
		
}
