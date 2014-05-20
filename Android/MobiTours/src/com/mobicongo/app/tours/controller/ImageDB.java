package com.mobicongo.app.tours.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mobicongo.app.tours.utils.Utilities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;


public class ImageDB {

	private static final int DB_VERSION=1;
	private static final String IMAGE_DATABASE="imagedb.db";
	private static final String TAG="ImageDB";
	
	private imageSQLiteHelper imaHelper;
	private SQLiteDatabase imagedb;
	private final Context ctx;
	
		
	public ImageDB(Context context){
		this.ctx=context;
	}
	
	public ImageDB open() throws SQLException{
		imaHelper=new imageSQLiteHelper(ctx);
		imagedb=imaHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		if(imaHelper != null){
			imaHelper.close();
		}
	}
	
	public class imageSQLiteHelper extends SQLiteOpenHelper {
		
		public imageSQLiteHelper(Context context) {
			super(context, IMAGE_DATABASE, null,DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(MobiDatabaseContract.DB_CREATE_IMAGE);
			Log.w(TAG,"Database created successfuly !");//Log
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG,"Update database from version "+ oldVersion +
					" To the version "+ newVersion + ". So please backup data");
			db.execSQL("DROP TABLE IF EXISTS " +  MobiDatabaseContract.Image.TABLE_NAME);
			onCreate(db);
		}		
	}
	
/********************************************************************                          
	********************** CRUD QUERY *******************************/

	public long insertImage(int poiid, String name, String url, byte[] img, String imgtype){
		
		ContentValues cv=new ContentValues();
		cv.put(MobiDatabaseContract.Image.COLUMN_POIID, poiid);
		cv.put(MobiDatabaseContract.Image.COLUMN_NAME, name);
		cv.put(MobiDatabaseContract.Image.COLUMN_URL, url);
		cv.put(MobiDatabaseContract.Image.COLUMN_IMAGE, img);
		cv.put(MobiDatabaseContract.Image.COLUMN_IMAGE_TYPE, imgtype);
		
		return imagedb.insert(MobiDatabaseContract.Image.TABLE_NAME, null, cv);
	}
	
	public byte[] fetchingSingleImage(int poiid){
		Cursor req=imagedb.query(MobiDatabaseContract.Image.TABLE_NAME,
				MobiDatabaseContract.PROJECTION_GET_ONE_IMAGE, 
				MobiDatabaseContract.Image.COLUMN_IMAGE + "=" + poiid, 
				null, null, null, null);
		if(req==null){
			return null;
		}
		byte[] image=null;
		if(req.moveToFirst()){
			image=req.getBlob(req.getColumnIndexOrThrow(MobiDatabaseContract.Image.COLUMN_IMAGE));
		}
		req.close();
		
		return image;
	}
	
	/**
     * Extract Image data from a {@link JSONArray} of Image from MySql and add it to the Image table.
     * in SQLite
     * @param data
     */
    public void loadImageData(JSONArray data) throws JSONException{
    	
    	SQLiteDatabase db = imaHelper.getWritableDatabase();

        // empty the GeoZone table to remove all existing data
        db.delete(MobiDatabaseContract.Image.TABLE_NAME, null, null);
        // need to complete transaction first to clear data
        db.close();
        // begin the insert transaction
        db = imaHelper.getWritableDatabase();
        db.beginTransaction();
        // Loop over each point of interest in array
        for (int i = 0; i < data.length(); i++) {
            JSONObject imaObj = data.getJSONObject(i);
            //Extract Images properties
            int poiid=imaObj.getInt("pointofinterestid");
			String name=imaObj.getString("name");
			String url=imaObj.getString("url");
			String limage=imaObj.getString("image");
			String imatype=imaObj.getString("imagetype");
			//Decode the image first and get a Bitmap object
			byte[] rawImage = Base64.decode(limage, Base64.DEFAULT);
            Bitmap bmp = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length); 
            //Convert the Bitmap object to a Byte[] object
            byte[] image_in_byte = Utilities.getBytes(bmp);
            // Create content values object for insert
            ContentValues cv = new ContentValues();
            cv.put(MobiDatabaseContract.Image.COLUMN_POIID, poiid);
    		cv.put(MobiDatabaseContract.Image.COLUMN_NAME, name);
    		cv.put(MobiDatabaseContract.Image.COLUMN_URL, url);
    		cv.put(MobiDatabaseContract.Image.COLUMN_IMAGE, image_in_byte);
    		cv.put(MobiDatabaseContract.Image.COLUMN_IMAGE_TYPE, imatype);
            // Insert data and image
            db.insert(MobiDatabaseContract.Image.TABLE_NAME, null, cv);
        }
        // All insert statement have been submitted, mark transaction as successful
        db.setTransactionSuccessful();

        if (db != null) {
            db.endTransaction();
        }
    }

    // Put this in the activity where you want to view the image from database
    
    /*private void queryFromDB(int id) {
    	ImageDB imadb;
    	imadb=new ImageDB(context);
    	imadb.open();
        byte[] image = imadb.fetchingSingleImage(id);
        imageView.setImageBitmap(Utilities.getImage(image));
        imadb.close();
    }*/
	
//THE END
	}


