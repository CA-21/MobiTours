package com.mobicongo.app.tours.controller;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobicongo.app.tours.model.City;
import com.mobicongo.app.tours.model.Restaurant;
import com.mobicongo.app.tours.model.ZoneGeo;

public class MobiToursBD {

	private static final int DB_VERSION=1;
	private static final String BD_MOBITOURS="mobitoursDB.db";
	private static final String TAG="MobiToursDB";
	
	//List of database's tables
	private static final String ZONEGEO_TABLE="zonegeo";
	private static final String CITY_TABLE="city";
	private static final String RESTO_TABLE="restaurant";
	
	//List of table's fields
	public static final String KEY_ROWID="_id";
	public static final String key_type="type";
	//ZoneGeo table
	public static final String KEY_ZONEID="zoneGeoId";
	public static final String KEY_NAME_ZG="nameZoneGeo";
	public static final String KEY_DESC_ZG="descZoneGeo";
	public static final String KEY_ETENDU="etenduZoneGeo";
	public static final String KEY_LAT_ZONE="latzone";
	public static final String KEY_LNG_ZONE="lngzone";
	public static final String KEY_AREA="areazone";
	//City table
	public static final String key_idcity="idcity";
	public static final String key_name_city="nameCity";
	public static final String key_desc_city="descCity";
	public static final String key_idzone="idzone";
	public static final String key_latcity="latcity";
	public static final String key_lngcity="lngcity";
	public static final String key_altcity="altcity";
	public static final String cityurlimage="cityurlimage";
	//POI public key (for Restaurants, Hotels, Cultures ... and all other POI)
	public static final String key_poi_name="name";
	public static final String key_poi_link="link";
	public static final String key_poi_classif="classification";
	public static final String key_poi_lat="latitude";
	public static final String key_poi_long="longitude";
	public static final String key_poi_alt="altitude";
	public static final String key_poi_descr="description";
	public static final String key_poi_idcity="idcity";
	//Restaurant table
	public static final String key_idresto="idresto";
	public static final String key_mealofday="mealofday";
	public static final String key_mealprice="mealprice";
	public static final String key_restoimg="restoimg";
	
	private mobiSQLiteHelper mobiloHelper;
	private SQLiteDatabase mobidb;
	private final Context ctx;
	
	//Database Queries
	//Table ZoneGeo
	private static final String DB_CREATE_ZONEGEO=
		"CREATE TABLE if not exists "+ ZONEGEO_TABLE +" (" +
			KEY_ROWID + " integer PRIMARY KEY autoincrement, " +
			KEY_ZONEID + " integer not null, " +
			KEY_NAME_ZG + " text not null, " + 
			KEY_ETENDU + " text, " + 
			KEY_DESC_ZG + " text, " +
			KEY_LAT_ZONE + " double, " + 
			KEY_LNG_ZONE + " double, " + 
			KEY_AREA + " double )";
	//Table City
	private static final String DB_CREATE_CITY=
		"CREATE TABLE if not exists "+ CITY_TABLE +" (" +
			KEY_ROWID + " integer PRIMARY KEY autoincrement," +
			key_idcity + " integer not null, " +
			key_name_city + " text not null, " + 
			key_idzone + " int not null, " + 
			key_desc_city + " text, " + 
			key_latcity + " double, " +
			key_lngcity + " double, " + 
			key_altcity + " double, " + 
			cityurlimage + " text )"; 
		
	//Table Resto
	private static final String DB_CREATE_RESTO=
			"CREATE TABLE if not exists "+ CITY_TABLE +" (" +
				KEY_ROWID + " integer PRIMARY KEY autoincrement," +
				key_idcity + " integer not null, " +
				key_idresto + " integer not null, " +
				key_poi_name + " text not null, " + 
				key_mealofday + " text," +
				key_mealprice + " text," +
				key_type + " text, " +
				key_poi_classif + " text," +
				key_poi_lat + " double, " +
				key_poi_long + " double, " + 
				key_poi_descr + " text )"; 
			
	
	public MobiToursBD(Context context){
		this.ctx=context;
	}
	
	public MobiToursBD open() throws SQLException{
		mobiloHelper=new mobiSQLiteHelper(ctx);
		mobidb=mobiloHelper.getWritableDatabase();
		return this;
	}
		
	public void close(){
		if(mobiloHelper !=null){
			mobiloHelper.close();
		}
	}
	
	public class mobiSQLiteHelper extends SQLiteOpenHelper {
		
		public mobiSQLiteHelper(Context context) {
			super(context, BD_MOBITOURS, null,DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {

			db.execSQL(DB_CREATE_ZONEGEO);
			db.execSQL(DB_CREATE_CITY);
			db.execSQL(DB_CREATE_RESTO);
			db.execSQL(MobiDatabaseContract.DB_CREATE_HOTEL);
			db.execSQL(MobiDatabaseContract.DB_CREATE_OTHERPOI);
			db.execSQL(MobiDatabaseContract.DB_CREATE_SITENATURE);
			db.execSQL(MobiDatabaseContract.DB_CREATE_BOOKING);
			db.execSQL(MobiDatabaseContract.DB_CREATE_COMMENTS);
			db.execSQL(MobiDatabaseContract.DB_CREATE_RATING);
			db.execSQL(MobiDatabaseContract.DB_CREATE_COURTIER);
			
			Log.w(TAG,"Database created successfuly !");//Log
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG,"Update database from version "+ oldVersion +
					" To the version "+ newVersion + ". So please backup data");
			db.execSQL("DROP TABLE IF EXISTS " + ZONEGEO_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + CITY_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + DB_CREATE_RESTO);
			db.execSQL("DROP TABLE IF EXISTS " + MobiDatabaseContract.DB_CREATE_HOTEL);
			db.execSQL("DROP TABLE IF EXISTS " + MobiDatabaseContract.DB_CREATE_OTHERPOI);
			db.execSQL("DROP TABLE IF EXISTS " + MobiDatabaseContract.DB_CREATE_SITENATURE);
			db.execSQL("DROP TABLE IF EXISTS " + MobiDatabaseContract.DB_CREATE_BOOKING);
			db.execSQL("DROP TABLE IF EXISTS " + MobiDatabaseContract.DB_CREATE_COMMENTS);
			db.execSQL("DROP TABLE IF EXISTS " + MobiDatabaseContract.DB_CREATE_RATING);
			db.execSQL("DROP TABLE IF EXISTS " + MobiDatabaseContract.DB_CREATE_COURTIER);
			onCreate(db);
		}			
	}
	
/********************************************************************                          
	********************** CRUD QUERY *******************************
*/
// ZONEGEO TABLE ***********************************************************************************************
	
				
		public long insertZonegeo(int idz, String zname, String zdesc, String zetendu,
				Double lat,Double lng){
			ContentValues c=new ContentValues();
			c.put(KEY_ZONEID, idz);
			c.put(KEY_NAME_ZG, zname);
			c.put(KEY_ETENDU, zetendu);
			c.put(KEY_DESC_ZG, zdesc);
			c.put(KEY_LAT_ZONE, lat);
			c.put(KEY_LNG_ZONE, lng);
			
			return mobidb.insert(ZONEGEO_TABLE, null, c);
		}
		
		public boolean deleteAllZoneGeo(){
			int done=0;
			done=mobidb.delete(ZONEGEO_TABLE, null, null);
			Log.w(TAG,Integer.toString(done));
			return  done>0;
		};
		
		public boolean deleteZoneGeoById(ZoneGeo zg){
			return(
				mobidb.delete(ZONEGEO_TABLE, 
						KEY_ROWID + "=" + zg.getZoneGeoId(), null) > 0
			);
		}
	//Retrieve all zone
		public Cursor getAllZoneLocations() {
			Cursor mobiCursor=mobidb.query(ZONEGEO_TABLE, new String[]{KEY_ROWID, KEY_ZONEID, KEY_NAME_ZG, KEY_DESC_ZG, KEY_LAT_ZONE, KEY_LNG_ZONE},
					null, null,null,null,null);
			
			if(mobiCursor != null){
				mobiCursor.moveToFirst();
			}
			return mobiCursor;
		}
				
		
		public Cursor getOneGeoZoneValues(int idz) throws SQLException{
			Log.w(TAG,Integer.toString(idz));
			Cursor c=null;
			if(Integer.toString(idz)!=null){
				c=mobidb.query(ZONEGEO_TABLE, new String[]{
						KEY_ROWID, KEY_ZONEID, KEY_NAME_ZG, KEY_LAT_ZONE, KEY_LNG_ZONE
				}, KEY_ZONEID + "=" + idz, null, null, null, null);
			}
			if(c != null){
				c.moveToFirst();
			}
			return c;
		}
		
		/**
	     * Extract GeoZone data from a {@link JSONArray} of GeoZone and add it to the GeoZone table.
	     * in SQLite
	     * @param data
	     */
	    public void loadGeoZone(JSONArray data) throws JSONException{
	    	
	    	SQLiteDatabase db = mobiloHelper.getWritableDatabase();

	        // empty the GeoZone table to remove all existing data
	        db.delete(MobiDatabaseContract.GeoZone.TABLE_NAME, null, null);
	        // need to complete transaction first to clear data
	        db.close();
	        // begin the insert transaction
	        db = mobiloHelper.getWritableDatabase();
	        db.beginTransaction();
	        // Loop over each point of interest in array
	        for (int i = 0; i < data.length(); i++) {
	            JSONObject geoz = data.getJSONObject(i);
	            //Extract POI properties
	            final int zoneid = geoz.getInt("zoneGeoId");
	            final String zonename = geoz.getString("nameZoneGeo");
	            final String desczone = geoz.getString("descZoneGeo");
	            final String etenduzone = geoz.getString("etenduZoneGeo");
	            final Double latitude = geoz.getDouble("latzone");
	            final Double longitude = geoz.getDouble("lngzone");
	            final Double areazone = geoz.getDouble("areazone");
	            // Create content values object for insert
	            ContentValues cv = new ContentValues();
	            cv.put(MobiDatabaseContract.GeoZone.COLUMN_ZONEID, zoneid);
	            cv.put(MobiDatabaseContract.GeoZone.COLUMN_NAME_ZG, zonename);
	            cv.put(MobiDatabaseContract.GeoZone.COLUMN_DESC_ZG, desczone);
	            cv.put(MobiDatabaseContract.GeoZone.COLUMN_ETENDU, etenduzone);
	            cv.put(MobiDatabaseContract.GeoZone.COLUMN_LAT_ZONE, latitude);
	            cv.put(MobiDatabaseContract.GeoZone.COLUMN_LNG_ZONE, longitude);
	            cv.put(MobiDatabaseContract.GeoZone.COLUMN_AREA, areazone);
	            // Insert data
	            db.insert(MobiDatabaseContract.GeoZone.TABLE_NAME, null, cv);
	        }
	        // All insert statement have been submitted, mark transaction as successful
	        db.setTransactionSuccessful();

	        if (db != null) {
	            db.endTransaction();
	        }

	    }

		
		
// CITY TABLE	 ************************************************************************************************
		
	public long createCity(int id,String name, int idz, String desc,
			double lat, double lng, int alt,String img_link){
		
		ContentValues initcity=new ContentValues();
		initcity.put(key_idcity, id);
		initcity.put(key_name_city, name);
		initcity.put(key_idzone, idz);
		initcity.put(key_desc_city, desc);
		initcity.put(key_latcity, lat);
		initcity.put(key_lngcity, lng);
		initcity.put(key_altcity, alt);
		initcity.put(cityurlimage, img_link);
		
		return mobidb.insert(CITY_TABLE, null, initcity);
	}
	
	//delete all city entry
	public boolean deleteAllCities(){
		int okdel=0;
		okdel=mobidb.delete(CITY_TABLE, null, null);
		Log.w(TAG,Integer.toString(okdel));
		return okdel>0;
	}
	
	
	//Return all cities by zone
	public Cursor getAllCitiesLocationByZone(int idz) throws SQLException{
		Log.w(TAG,Integer.toString(idz));
		//Cursor c=null;
		/*if(Integer.toString(idz)!=null){*/
			Cursor c=mobidb.query(CITY_TABLE, new String[]{
					KEY_ROWID,key_idcity,key_name_city,key_idzone,key_desc_city,key_latcity,key_lngcity,key_altcity,cityurlimage
			}, key_idzone + "=" + idz, null, null, null, null);
		//{
		if(c != null){
			c.moveToFirst();
		}
		return c;
	}
	
	/** return one city by his ID */
	public Cursor returnOneCity(int id) throws SQLException{
		Log.w(TAG,Integer.toString(id));
		Cursor c=null;
		if((Integer.toString(id)==null)){
			c=mobidb.query(CITY_TABLE, new String[]{
					KEY_ROWID,key_idcity,key_name_city,key_idzone,key_desc_city,key_latcity,key_lngcity,key_altcity,cityurlimage
			}, null, null, null, null, null);
		}
		else {
			
			c=mobidb.query(CITY_TABLE, new String[]{
					KEY_ROWID,key_idcity,key_name_city,key_idzone,key_desc_city,key_latcity,key_lngcity,key_altcity,cityurlimage
			}, key_idzone + "=" + id, null, null, null, null);
			
		}
		if(c != null){
			c.moveToFirst();
		}
		return c;
	}
	
		
	//fecth all cities values without definition of the geo zone
	public Cursor getAllCitiesLocations(){
		Cursor c=mobidb.query(CITY_TABLE, new String[]{
				KEY_ROWID,key_idcity,key_name_city,key_idzone,key_desc_city,key_latcity,key_lngcity,key_altcity,cityurlimage
		}, null, null, null, null, null);
		if(c!= null){
			c.moveToFirst();
		}
		return c;
	}
		
		
	public ArrayList<City> GetCityValues() //(String aTable,String[] aColumn)
		{
		    ArrayList<City> clist = new ArrayList<City>();
		    City c=new City();
		    Cursor cursor = mobidb.query(CITY_TABLE, new String[]{key_name_city,key_desc_city,key_altcity,key_latcity,key_lngcity,cityurlimage}, null, null, null, null, null);
		    if (cursor.moveToFirst())
		    {
		        do
		        {
		           // clist.add(cursor.getString(0));
		        	c=new City();
		        	c.setCityname(cursor.getString(0));
		            c.setAltitude(Integer.parseInt(cursor.getString(1)));
		            c.setLatitude(Double.parseDouble(cursor.getString(2)));
		            c.setLongitude(Double.parseDouble(cursor.getString(3)));
		            
		            clist.add(c);
		        }
		        while (cursor.moveToNext());
		    }
		    if (cursor != null && !cursor.isClosed()) 
		    {
		        cursor.close();
		    }
		    return clist;
		}
		

		/**
	     * Extract cities's data from a {@link JSONArray} of City and add it to the City table.
	     * in SQLite
	     * @param data
	     */
	    public void loadCities(JSONArray data) throws JSONException{
	    	
	    	SQLiteDatabase db = mobiloHelper.getWritableDatabase();
	        // empty the GeoZone table to remove all existing data
	        db.delete(CITY_TABLE, null, null);
	        // need to complete transaction first to clear data
	        db.close();
	        // begin the insert transaction
	        db = mobiloHelper.getWritableDatabase();
	        db.beginTransaction();
	        // Loop over each city in array
	        for (int i = 0; i < data.length(); i++) {
	            JSONObject cityObj = data.getJSONObject(i);
	            //Extract POI properties
	            final int id = cityObj.getInt("idcity");
	            final String namecity = cityObj.getString("namecity");
	            final String idzone = cityObj.getString("idzone");
	            final String desccity = cityObj.getString("desccity");
	            final Double latitude = cityObj.getDouble("latcity");
	            final Double longitude = cityObj.getDouble("lngcity");
	            final int altitude = cityObj.getInt("altcity");	 
	            final String curlimage=cityObj.getString("cityurlimage");
	            // Create content values object for insert
	            ContentValues cv = new ContentValues();
	            cv.put(key_idcity, id);
	            cv.put(key_name_city, namecity);
	            cv.put(key_idzone, idzone);
	            cv.put(key_desc_city, desccity);
	            cv.put(key_latcity, latitude);
	            cv.put(key_lngcity, longitude);
	            cv.put(key_altcity, altitude);
	            cv.put(cityurlimage, curlimage);
	            // Insert data
	            db.insert(CITY_TABLE, null, cv);
	        }
	        // All insert statement have been submitted, mark transaction as successful
	        db.setTransactionSuccessful();
	        if (db != null) {
	            db.endTransaction();
	        }
	    }

	    /**
	     * Extract Hotels data from a {@link JSONArray} of Hotel and add it to the Hotel table.
	     * in SQLite
	     * @param data
	     */
	    public void loadHotels(JSONArray data) throws JSONException{
	    	
	    	SQLiteDatabase db = mobiloHelper.getWritableDatabase();
	        // empty the GeoZone table to remove all existing data
	        db.delete(MobiDatabaseContract.Hotel.TABLE_NAME, null, null);
	        // need to complete transaction first to clear data
	        db.close();
	        // begin the insert transaction
	        db = mobiloHelper.getWritableDatabase();
	        db.beginTransaction();
	        // Loop over each city in array
	        for (int i = 0; i < data.length(); i++) {
	            JSONObject HotelObj = data.getJSONObject(i);
	            //Extract POI properties
	            final int pointofinterestid = HotelObj.getInt("pointofinterestid");
	            final int idcity = HotelObj.getInt("idcity");
	            final String title = HotelObj.getString("title");
	            final String deschotel = HotelObj.getString("deschotel");
	            final Double latitude = HotelObj.getDouble("latitude");
	            final Double longitude = HotelObj.getDouble("longitude");
	            final String type = HotelObj.getString("type");
	            final String adresse = HotelObj.getString("adresse");
	            final int star = HotelObj.getInt("star");
	            final int nbroom = HotelObj.getInt("nbroom");
	            final int nbroomdispo=HotelObj.getInt("nbroomdispo");
	            final Double roompricemin = HotelObj.getDouble("roompricemin");
	            final Double roompricemax = HotelObj.getDouble("roompricemax");
	            final String mail = HotelObj.getString("mail");
	            final String url=HotelObj.getString("url");
	            final String pictureurl = HotelObj.getString("pictureurl");            
	            // Create content values object for insert
	            ContentValues cv = new ContentValues();
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_POIID, pointofinterestid);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_IDCITY, idcity);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_TITLE, title);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_DESC, deschotel);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_LATITUDE, latitude);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_LONGITUDE, longitude);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_TYPE, type);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_TITLE, title);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_ADRESS, adresse);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_STAR, star);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_NBROOM, nbroom);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_NBROOM_DISPO, nbroomdispo);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_ROOMPRICEMIN, roompricemin);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_ROOMPRICEMAX, roompricemax);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_MAIL, mail);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_URL, url);
	            cv.put(MobiDatabaseContract.Hotel.COLUMN_PICTUREURL, pictureurl);
	            // Insert data
	            db.insert(MobiDatabaseContract.Hotel.TABLE_NAME, null, cv);
	        }
	        // All insert statement have been submitted, mark transaction as successful
	        db.setTransactionSuccessful();
	        if (db != null) {
	            db.endTransaction();
	        }
	    }
		
	    /**
	     * Extract Comments data from a {@link JSONArray} of comments and add it to the comments table.
	     * in SQLite
	     * @param data
	     */
	    public void loadComments(JSONArray data) throws JSONException{
	    	
	    	SQLiteDatabase db = mobiloHelper.getWritableDatabase();
	        // empty the GeoZone table to remove all existing data
	        db.delete(MobiDatabaseContract.Comments.TABLE_NAME, null, null);
	        // need to complete transaction first to clear data
	        db.close();
	        // begin the insert transaction
	        db = mobiloHelper.getWritableDatabase();
	        db.beginTransaction();
	        // Loop over each city in array
	        for (int i = 0; i < data.length(); i++) {
	            JSONObject cObj = data.getJSONObject(i);
	            //Extract POI properties
	            final int pointofinterestid = cObj.getInt("pointofinterestid");
	            final int siteid = cObj.getInt("siteid");
	            /*final int commentid = cObj.getInt("commentid");*/
	            final int userid = cObj.getInt("userid");
	            final String text = cObj.getString("text");
	            final String date = cObj.getString("date");          
	            // Create content values object for insert
	            ContentValues cv = new ContentValues();
	            /*cv.put(MobiDatabaseContract.Comments.COLUMN_COMMENTID, commentid);*/
	            cv.put(MobiDatabaseContract.Comments.COLUMN_TEXT, text);
	            cv.put(MobiDatabaseContract.Comments.COLUMN_DATECOMMENT, date);
	            cv.put(MobiDatabaseContract.Comments.COLUMN_USERID, userid);
	            cv.put(MobiDatabaseContract.Comments.COLUMN_POIID, pointofinterestid);
	            cv.put(MobiDatabaseContract.Comments.COLUMN_IDSITE, siteid);
	            // Insert data
	            db.insert(MobiDatabaseContract.Comments.TABLE_NAME, null, cv);
	        }
	        // All insert statement have been submitted, mark transaction as successful
	        db.setTransactionSuccessful();
	        if (db != null) {
	            db.endTransaction();
	        }
	    }
	    
	    /**
	     * Extract Comments data from a {@link JSONArray} of comments and add it to the comments table.
	     * in SQLite
	     * @param data
	     */
	    public void loadRate(JSONArray data) throws JSONException{
	    	
	    	SQLiteDatabase db = mobiloHelper.getWritableDatabase();
	        // empty the GeoZone table to remove all existing data
	        db.delete(MobiDatabaseContract.Rating.TABLE_NAME, null, null);
	        // need to complete transaction first to clear data
	        db.close();
	        // begin the insert transaction
	        db = mobiloHelper.getWritableDatabase();
	        db.beginTransaction();
	        // Loop over each city in array
	        for (int i = 0; i < data.length(); i++) {
	            JSONObject rObj = data.getJSONObject(i);
	            //Extract POI properties
	            final int pointofinterestid = rObj.getInt("pointofinterestid");
	            final int siteid = rObj.getInt("idsite");
	          //  final int ratingid = rObj.getInt("ratingid");
	            final int userid = rObj.getInt("userid");
	            final int rate=rObj.getInt("rating");
	            // Create content values object for insert
	            ContentValues cv = new ContentValues();
	          //  cv.put(MobiDatabaseContract.Rating.COLUMN_RATINGID, ratingid);
	            cv.put(MobiDatabaseContract.Rating.COLUMN_RATING, rate);
	            cv.put(MobiDatabaseContract.Rating.COLUMN_USERID, userid);
	            cv.put(MobiDatabaseContract.Rating.COLUMN_POIID, pointofinterestid);
	            cv.put(MobiDatabaseContract.Rating.COLUMN_IDSITE, siteid);
	            // Insert data
	            db.insert(MobiDatabaseContract.Rating.TABLE_NAME, null, cv);
	        }
	        // All insert statement have been submitted, mark transaction as successful
	        db.setTransactionSuccessful();
	        if (db != null) {
	            db.endTransaction();
	        }
	    }
		
	    /**
	     * Extract Courtier data from a {@link JSONArray} of courtier and add it to the courtier table.
	     * in SQLite
	     * @param data
	     */
	    public void loadCourtiers(JSONArray data) throws JSONException{
	    	
	    	SQLiteDatabase db = mobiloHelper.getWritableDatabase();
	        // empty the GeoZone table to remove all existing data
	        db.delete(MobiDatabaseContract.Courtier.TABLE_NAME, null, null);
	        // need to complete transaction first to clear data
	        db.close();
	        // begin the insert transaction
	        db = mobiloHelper.getWritableDatabase();
	        db.beginTransaction();
	        // Loop over each city in array
	        for (int i = 0; i < data.length(); i++) {
	            JSONObject coObj = data.getJSONObject(i);
	            //Extract POI properties
	            final int courtierid = coObj.getInt("courtierid");
	            final String email = coObj.getString("email");
	            final String name = coObj.getString("name");
	            final String website = coObj.getString("website");
	            final String phone=coObj.getString("phone");
	            final String country=coObj.getString("country");
	            // Create content values object for insert
	            ContentValues cv = new ContentValues();
	            cv.put(MobiDatabaseContract.Courtier.COLUMN_COURTIERID, courtierid);
	            cv.put(MobiDatabaseContract.Courtier.COLUMN_EMAIL, email);
	            cv.put(MobiDatabaseContract.Courtier.COLUMN_NAME, name);
	            cv.put(MobiDatabaseContract.Courtier.COLUMN_WEBSITE, website);
	            cv.put(MobiDatabaseContract.Courtier.COLUMN_PHONE, phone);
	            cv.put(MobiDatabaseContract.Courtier.COLUMN_COUNTRY, country);
	            // Insert data
	            db.insert(MobiDatabaseContract.Courtier.TABLE_NAME, null, cv);
	        }
	        // All insert statement have been submitted, mark transaction as successful
	        db.setTransactionSuccessful();
	        if (db != null) {
	            db.endTransaction();
	        }
	    }
		
	    
	//Inserting Image in the database
		
		
		// RESTO TABLE	 ************************************************************************************************
		
		public void insertNewResto(Restaurant myresto){
			ContentValues resto=new ContentValues();
			resto.put(key_poi_idcity, myresto.getmIdCity());
			resto.put(key_idresto, myresto.getIdResto());
			resto.put(key_poi_name, myresto.getmTitle());
			resto.put(key_mealofday, myresto.getMealofday());
			resto.put(key_mealprice, myresto.getMealprice());
			resto.put(key_poi_classif, myresto.getClassification());
			resto.put(key_poi_lat, myresto.getmLatitude());
			resto.put(key_poi_long, myresto.getmLongitude());
			resto.put(key_type, myresto.getmType().toString());
			resto.put(key_poi_descr, myresto.getmDescription());
			
			mobidb.insert(RESTO_TABLE, null, resto);
			
		}
		
	/*
	 * Retrieve all Hotels
	 */
		//Fetch all hotel per city
		public Cursor fetchingAllHotels(int idcity){
			Cursor req=mobidb.query(MobiDatabaseContract.Hotel.TABLE_NAME,
					MobiDatabaseContract.PROJECTION_HOTEL, 
					MobiDatabaseContract.Hotel.COLUMN_IDCITY + "=" + idcity, 
					null, null, null, null);
			if(req==null){
				return null;
			}
			if(req!=null){
				req.moveToFirst();
			}
			//req.close();

			return req;
		} 
		
		//Fetch one hotel 
		public Cursor fetchingOneHotel(int idhotel){
			Cursor q=mobidb.query(MobiDatabaseContract.Hotel.TABLE_NAME,
					MobiDatabaseContract.PROJECTION_HOTEL, 
					MobiDatabaseContract.Hotel.COLUMN_POIID + "=" + idhotel, 
					null, null, null, null);
			if(q==null){
				return null;
			}
			if(q!=null){
				q.moveToFirst();
			}
			//q.close();

			return q;
		} 
		
		
		/*
		 * Retrieve all Brokers (courtier)
		 */
			//Fetch all Broker 
			public Cursor fetchingAllBrokers(){
				Cursor req=mobidb.query(MobiDatabaseContract.Courtier.TABLE_NAME,
						MobiDatabaseContract.PROJECTION_COURTIER, 
						null, null, null, null, null);
				if(req==null){
					return null;
				}
				if(req!=null){
					req.moveToFirst();
				}
				//req.close();

				return req;
			} 
			
			//Fetch one broker(courtier) 
			public Cursor fetchingOneBroker(int courtierid){
				Cursor q=mobidb.query(MobiDatabaseContract.Courtier.TABLE_NAME,
						MobiDatabaseContract.PROJECTION_COURTIER, 
						MobiDatabaseContract.Courtier.COLUMN_COURTIERID + "=" + courtierid, 
						null, null, null, null);
				if(q==null){
					return null;
				}
				if(q!=null){
					q.moveToFirst();
				}
				//q.close();

				return q;
			} 
			
		
		/*
		 * Retrieve all Natural site
		 */
			//Fetch all hotel per city
			public Cursor fetchingAllSiteNaturel(int idcity){
				Cursor req=mobidb.query(MobiDatabaseContract.SiteNaturel.TABLE_NAME,
						MobiDatabaseContract.PROJECTION_SITENATUREL, 
						MobiDatabaseContract.SiteNaturel.COLUMN_IDCITY + "=" + idcity, 
						null, null, null, null);
				if(req==null){
					return null;
				}
				if(req!=null){
					req.moveToFirst();
				}
				

				return req;
			} 
			
			//Fetch one Natural Site 
			public Cursor fetchingOneSiteNatuel(int idsite){
				Cursor q=mobidb.query(MobiDatabaseContract.SiteNaturel.TABLE_NAME,
						MobiDatabaseContract.PROJECTION_SITENATUREL, 
						MobiDatabaseContract.SiteNaturel.COLUMN_IDSITE + "=" + idsite, 
						null, null, null, null);
				if(q==null){
					return null;
				}
				if(q!=null){
					q.moveToFirst();
				}
			
				return q;
			} 
		
			/*
			 * Retrieve Comments
			 */
				//Fetch all comments for a POI
				public Cursor fetchingCommentsByPoi(int poi_id, String data_type){
					
					Cursor req = null;
					if(data_type=="hotel"){
						req=mobidb.query(MobiDatabaseContract.Hotel.TABLE_NAME,
								MobiDatabaseContract.PROJECTION_HOTEL, 
								MobiDatabaseContract.Hotel.COLUMN_POIID + "=" + poi_id, 
								null, null, null, null);
						if(req==null){
							return null;
						}
						if(req!=null){
							req.moveToFirst();
						}
						//req.close();
					}
					else if(data_type=="restaurant"){
						 req=mobidb.query(RESTO_TABLE,
								MobiDatabaseContract.PROJECTION_RESTAURANT, 
								key_idresto + "=" + poi_id, 
								null, null, null, null);
						if(req==null){
							return null;
						}
						if(req!=null){
							req.moveToFirst();
						}
						//req.close();
					}
					
					return req;
				} 
			/*
			 * Insert Comments
			 */
			
			    /**
			     * Extract Natural site data from a {@link JSONArray} of Natural site and add it to the SiteNaturel table.
			     * in SQLite
			     * @param data
			     */
			    public void loadNaturalSites(JSONArray data) throws JSONException{
			    	
			    	SQLiteDatabase db = mobiloHelper.getWritableDatabase();
			        // empty the GeoZone table to remove all existing data
			        db.delete(MobiDatabaseContract.SiteNaturel.TABLE_NAME, null, null);
			        // need to complete transaction first to clear data
			        db.close();
			        // begin the insert transaction
			        db = mobiloHelper.getWritableDatabase();
			        db.beginTransaction();
			        // Loop over each city in array
			        for (int i = 0; i < data.length(); i++) {
			            JSONObject siteObj = data.getJSONObject(i);
			            //Extract POI properties
			            final int idsite = siteObj.getInt("idsite");
			            final int idcity = siteObj.getInt("idcity");
			            final String title = siteObj.getString("title");
			            final String type = siteObj.getString("type");
			            final double latitude = siteObj.getDouble("latitude");
			            final double longitude = siteObj.getDouble("longitude");
			            final double area = siteObj.getDouble("area");
			            final String attractourist = siteObj.getString("attractourist");
			            final String sitedesc = siteObj.getString("sitedesc");
			            final double largeur = siteObj.getDouble("largeur");
			            final double longueur = siteObj.getDouble("longueur");
			            final String security=siteObj.getString("security");
			            final int visitorperan = siteObj.getInt("visitorperan");
			            final String site_url_image = siteObj.getString("site_url_image");            
			            // Create content values object for insert
			            ContentValues cv = new ContentValues();
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_IDSITE, idsite);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_IDCITY, idcity);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_TITLE, title);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_TYPE, type);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_LATITUDE, latitude);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_LONGITUDE, longitude);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_AREA, area);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_ATTRACT_TOUR, attractourist);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_SITEDESC, sitedesc);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_LARGEUR, largeur);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_LONGUEUR, longueur);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_SECURITY, security);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_VISITOR, visitorperan);
			            cv.put(MobiDatabaseContract.SiteNaturel.COLUMN_SITE_IMG_URL, site_url_image);
			            // Insert data
			            db.insert(MobiDatabaseContract.SiteNaturel.TABLE_NAME, null, cv);
			        }
			        // All insert statement have been submitted, mark transaction as successful
			        db.setTransactionSuccessful();
			        if (db != null) {
			            db.endTransaction();
			        }
			    }
		
		
/*
 * fetching all data for different tables for the general map without criteria
 */
			    //get all HOTEL
			    public Cursor getAllHotels(){
					Cursor curs=mobidb.query(MobiDatabaseContract.Hotel.TABLE_NAME,
							MobiDatabaseContract.PROJECTION_HOTEL, 
							null,null, null, null, null);
					if(curs==null){
						return null;
					}
					if(curs!=null){
						curs.moveToFirst();
					}
					//req.close();
					return curs;
				} 
			  //get all Sites
			    public Cursor getAllSites(){
					Cursor curs=mobidb.query(MobiDatabaseContract.SiteNaturel.TABLE_NAME,
							MobiDatabaseContract.PROJECTION_SITENATUREL, 
							null,null, null, null, null);
					if(curs==null){
						return null;
					}
					if(curs!=null){
						curs.moveToFirst();
					}
					//req.close();
					return curs;
				} 
			    
			   //City projection
	public static final String[] PROJECTION_CITY= new String[]{
		MobiToursBD.KEY_ROWID,MobiToursBD.key_idcity,MobiToursBD.key_name_city,MobiToursBD.key_idzone,
		MobiToursBD.key_desc_city,MobiToursBD.key_latcity,MobiToursBD.key_lngcity,MobiToursBD.key_altcity,
		MobiToursBD.cityurlimage};

			  //get all Cities
			    public Cursor getAllCities(){
					Cursor c=mobidb.query(MobiToursBD.CITY_TABLE, MobiToursBD.PROJECTION_CITY
					, null, null, null, null, null);
					if(c!= null){
						c.moveToFirst();
					}
					return c;
				}
			  
			    
	public long insertHotels(int poi,int idc, String title, String type, String url, double latitude,double longitude,
			String address, String desc, String star, int room, double minp, double maxp, String mail, String picturl,int dispo ){
		
		ContentValues cr=new ContentValues();
		cr.put(MobiDatabaseContract.Hotel.COLUMN_POIID, poi);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_IDCITY, idc);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_TITLE, title);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_TYPE, type);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_URL, url);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_LATITUDE, latitude);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_LONGITUDE, longitude);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_ADRESS, address);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_DESC, desc);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_STAR, star);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_NBROOM, room);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_STAR, star);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_NBROOM_DISPO, dispo);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_ROOMPRICEMIN, minp);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_ROOMPRICEMAX, maxp);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_MAIL, mail);
		cr.put(MobiDatabaseContract.Hotel.COLUMN_PICTUREURL, picturl);
		
		return mobidb.insert(MobiDatabaseContract.Hotel.TABLE_NAME, null, cr);
	}
	
	public long insertBrokers(int id,String mail, String name,String website, 
			String country, String phone){
		ContentValues cb=new ContentValues();
		cb.put(MobiDatabaseContract.Courtier.COLUMN_COURTIERID, id);
		cb.put(MobiDatabaseContract.Courtier.COLUMN_EMAIL, mail);
		cb.put(MobiDatabaseContract.Courtier.COLUMN_NAME, name);
		cb.put(MobiDatabaseContract.Courtier.COLUMN_WEBSITE, website);
		cb.put(MobiDatabaseContract.Courtier.COLUMN_COUNTRY, country);
		cb.put(MobiDatabaseContract.Courtier.COLUMN_PHONE, phone);
		
		return mobidb.insert(MobiDatabaseContract.Courtier.TABLE_NAME, null, cb);
	}
	
	public long insertNaturalSites(int ids,int idc,String title, String type, double area, String desc,
			double lat, double lng, String attract, double larg, double longu, String sec, int visitor,String urlimg){
		ContentValues cb=new ContentValues();
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_IDSITE, ids);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_IDCITY, idc);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_TITLE, title);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_TYPE, type);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_AREA, area);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_SITEDESC, desc);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_LATITUDE, lat);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_LONGITUDE, lng);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_ATTRACT_TOUR, attract);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_LARGEUR, larg);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_LONGUEUR, longu);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_SECURITY, sec);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_VISITOR, visitor);
		cb.put(MobiDatabaseContract.SiteNaturel.COLUMN_SITE_IMG_URL, urlimg);
		
		return mobidb.insert(MobiDatabaseContract.SiteNaturel.TABLE_NAME, null, cb);
	}
		
			    
    public void onInsertAllValuesForNoInternet(){
    	//Insert GeoZones
    	insertZonegeo(1,"Nord-Kivu","Au bord du lac kivu, a cote du volcan nyiragongo","1245Km2",-1.654586,29.220371);
    	insertZonegeo (2,"Sud-Kivu","Au bord du lac kivu, zone montagneuse","1845Km2",-2.510259,28.844948);
    	insertZonegeo(3,"Maniema","Ville enclaver entre le Sud-Kivu, Nord-kivu et Katanga et riche en minerais","1845Km2",-2.948698,25.950222);
    	insertZonegeo(4,"Kasai Oriental","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-2.751018,23.389893);
    	insertZonegeo(5,"Kasai Occidental","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-5.123772,21.851807);
    	insertZonegeo(6,"Bandundu","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-3.312077,17.385521);
    	insertZonegeo(7,"Bas-Congo","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-5.848107,13.056049);
    	insertZonegeo(8,"Equateur","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",0.049782,18.255844);
    	insertZonegeo(9,"Kinshasa","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-4.318339,15.314255);
    	insertZonegeo(10,"Province Orientale","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",0.520642,25.196114);
    	insertZonegeo(11,"Katanga","au bord du lac kivu, a cote du volcan nyiragongo","1845Km2",-11.649546,27.479553);
    	Log.v(TAG, "Ajout des zones geo OK ####### !");
    	//Insert Cities		
		createCity(1,"Goma",1,"Ville au bord du lac kivu et sur le flanc du Nyiragongo",-1.654586,29.220371, 1678,"http://192.168.56.1:8080/mobitours/images/test0.jpg");
	    createCity(2,"Beni",1,"Une des grande ville du Grand-nord au flanc du Mt Ruwenzori",0.490946,29.458981, 1678,"http://192.168.56.1:8080/mobitours/images/cbeni.jpg");
	    createCity(3,"Butembo",1,"desc desc desc desc",-0.775839,28.913802, 1678,"http://192.168.56.1:8080/mobitours/images/test1.jpg");
	    createCity(4,"Masisi",1,"desc desc desc desc",-1.398324,28.818303, 1678,"http://192.168.56.1:8080/mobitours/images/test2.jpg");
		createCity(5,"Sake",1,"desc desc desc desc",-0.56122,28.705357, 1678,"http://192.168.56.1:8080/mobitours/images/test3.jpg");
		createCity(6,"Kitshanga",1,"desc desc desc desc",-0.97284,28.698214, 1678,null);
		createCity(7,"Rutshuru",1,"desc desc desc desc",-1.160556,29.3825, 1678,null);
		createCity(8,"Bunagana",1,"desc desc desc desc",-1.313035,29.595378, 1678,null);
		createCity(9,"Rumangabo",1,"desc desc desc desc",-1.357663,29.369063, 1678,null);
		Log.v(TAG, "Ajout des villes OK ####### !");
		//Insert courtiers
		insertBrokers(1,"misamuna@gmail.com","MOBILEX TOURS","www.mobilex.com/tourism","Rep. Dem. of Congo","00243894141469");
		insertBrokers(2,"mmariska7@gmail.com","MISHAPI TOURS","www.mishapitours.com","Rep. Dem. of Congo","00243857110007");
		insertBrokers(3,"candysifa@gmail.com","NOVA TRAVEL AGENCY","www.novastonekivu.com","Rep. Dem. of Congo","00243998665133");
		insertBrokers(4,"sergekikobya@gmail.com","KYKOKIVU TRAVEL","www.kykobya.com","Rep. Dem. of Congo","00243993404585");
		insertBrokers(5,"joamani@yahoo.fr","GO TO GOMA","www.gotogoma-tourism.com","Rep. Dem. of Congo","00243990105560");
		Log.v(TAG, "Ajout des Courtiers OK ####### !");
		//Insert Hotels
		insertHotels(1,1,"Grands Lacs Hotel","hotel","www.grandslacshotel.com",-1.690314,29.238444,"Bld Kanyamunga ville","One of old goma\'s hotels","2",50,65,200,"","http://192.168.56.1:8080/mobitours/images/goma.jpg",12);
		insertHotels(2,1,"New look Hotel","hotel","www.newlookhotel-goma.com",-1.69303,29.238688,"Bld Kanyamunga, ville","Hotel with all you want","2",20,50,125,"","http://192.168.56.1:8080/mobitours/images/chalet_a.jpg",2);
		insertHotels(3,1,"Ihusi Hotel","hotel","www.ihusi.com",-1.698727,29.242473,"Bld Kanyamunga, ville","one of the best of goma","4",180,65,500,"","http://192.168.56.1:8080/mobitours/images/chalet_b.jpg",3);
		insertHotels(4,1,"Test Hotel","hotel","www.cosyscd.com",-1.69681,29.241811,"Bld Kanyamunga, ville","just for testing 1","4",18,25,200,"","http://192.168.56.1:8080/mobitours/images/hotel_ihusi.jpg",11);
		insertHotels(5,1,"Test Big Hotel","hotel","www.mobilex.com",-1.68378,29.234918,"Bld Kanyamunga, ville","just another for testing","4",200,25,700,"","http://192.168.56.1:8080/mobitours/images/chalet_c.jpg",46);
		insertHotels(6,1,"Hotel Bassin du Congo","hotel","www.bassincongo.com",-1.694169,29.242376,"Bld Kanyamunga, ville","just another for testing","4",140,30,200,"","http://192.168.56.1:8080/mobitours/images/chalet_d.jpg",100);
		insertHotels(7,1,"Centre d\'acceuil Caritas","hotel","www.caritas.cd",-1.700056,29.242473,"Bld Kanyamunga, ville","just another for testing","4",40,25,125,"","",13);
		Log.v(TAG, "Ajout des hotels OK ####### !");
		//Insert Natural Site
		insertNaturalSites(1,1,"Parc National des Virunga","Parc",0.0,"",-0.383333,29.5,"Un des plus vieux parcs",1.23,1.76,"N",255,"");
		insertNaturalSites(2,1,"Montagnes du Virunga","Site",0.0,"",-1.416667,29.5,"Parmis les plus hautes",0,0,"N",35,null);
    	insertNaturalSites(3,1,"Nyamurariga","Site",0.0,"",-1.408,29.2,"Un des volcans actifs",0,0,"Y",1245,null);
    	insertNaturalSites(4,1,"Sabyinyo","Site",0.0,"",-1.388,29.592,"Un des volcans actifs",0,0,"Y",1545,null);
    	Log.v(TAG, "Ajout des cites OK ####### !");
    	
    	
    }
    
			    
//THE END
	}

