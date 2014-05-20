package com.mobicongo.app.tours.ui;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.ImageDB;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.fragments.HomeListFragment;
import com.mobicongo.app.tours.json.JSONParser;
import com.mobicongo.app.tours.utils.MyConstants;


public class HomeActivity extends SherlockFragmentActivity{
	

	ViewFlipper vf;
	JSONParser jParser;
	
	MobiToursBD dbHelper;
	ImageDB imaHelper;
	
	JSONArray gzones;
	JSONArray cities;
	JSONArray images;
	JSONArray hotels;
	JSONArray ratings;
	JSONArray courtiers;
	JSONArray sitenaturels;
	
	MobiToursBD db;
	
	int mobigallery[]={
			R.drawable.foufou,R.drawable.kinkfleuve,R.drawable.bana_fleuve,R.drawable.riviera_display_a,
			R.drawable.mere_ya_nzamba, R.drawable.nyiragongo,R.drawable.survol_fleuve};
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		// Define the contentView here
			setContentView(R.layout.activity_home);
	
		ActionBar actionBar=getSupportActionBar();
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle("MobiTours");
		actionBar.setDisplayHomeAsUpEnabled(true);
				
		vf=(ViewFlipper)findViewById(R.id.vflipper);
		for(int i=0;i<mobigallery.length; i++){
			setFlipperImage(mobigallery[i]);
		}
		
		db=new MobiToursBD(this);
		db.open();
		
		HomeListFragment liste=new HomeListFragment();		//Create new instance of the HomeListFragment
		FragmentTransaction transact=getSupportFragmentManager().beginTransaction(); 
		transact.add(R.id.fragList, liste); //Add the HomeListFragment in the transaction
		transact.commit(); //Commit the fragment in the FragmentTransaction named transact
		
		vf.startFlipping();
		
		dbHelper=new MobiToursBD(getBaseContext());
		dbHelper.open();
		imaHelper=new ImageDB(getBaseContext());
		imaHelper.open();
		//loadDefaultvalueWhenWebNotWork();

		// load data into database if required
		if(isOnline()){
	       // if (!JSONParser.hasDataLoaded(getApplicationContext())) {
	           //synchronizeDataWithServer();
	        	new DataSynchronization().execute();
	       // }
		}else{
			Toast.makeText(getApplicationContext(), "No Internet Connexion. Sync failed.", Toast.LENGTH_SHORT)
			.show();
			db.onInsertAllValuesForNoInternet();
		}
		
		//Close the database to improve the rendering
		dbHelper.close();
		imaHelper.close();
		
	}

	//Method for flip image 
	private void setFlipperImage(int res) {
		Log.i("setting up images","Vflipper");
		ImageView Img=	new ImageView(getApplicationContext());
		Img.setAdjustViewBounds(true);
		Img.setBackgroundResource(res);
		vf.addView(Img);
	}
		
 
	/*Method for configuring the animation*/
	public static Animation inFromRightAnimation(int duration) {
        Animation inFromRight = new TranslateAnimation(
		        Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
		        Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
        );
        inFromRight.setDuration(duration);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }
	
	public static Animation outToLeftAnimation(int duration) {
        Animation outtoLeft = new TranslateAnimation(
	          Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  -1.0f,
	          Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
        );      
        outtoLeft.setDuration(duration);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }
	

	
	/*
	 * Allow to synchronize data with the mysql server through PHP Web Services 
	 */
	//Thread to Load Data from MySql Server and add them to the local database 
		class DataSynchronization extends AsyncTask<String,String,String>{

			@Override
			protected void onPreExecute() {	
				super.onPreExecute();
			}
			
			@Override
			protected String doInBackground(String... args) {
		
				try{
					List<NameValuePair> params=new ArrayList<NameValuePair>();
					//Parse the JSON file from php
					JSONObject data_gzone=JSONParser.makeHttpRequest(MyConstants.url_get_all_geozone, "GET", params);
					JSONObject data_city=JSONParser.makeHttpRequest(MyConstants.url_get_all_cities, "GET", params);
				//	JSONObject data_image=JSONParser.makeHttpRequest(MyConstants.url_get_all_image, "GET", params);
					JSONObject data_hotel=JSONParser.makeHttpRequest(MyConstants.url_get_all_hotel, "GET", params);
					JSONObject data_rating=JSONParser.makeHttpRequest(MyConstants.url_get_all_rating, "GET", params);
					JSONObject data_courtier=JSONParser.makeHttpRequest(MyConstants.url_get_all_courtier, "GET", params);
					JSONObject data_sitenaturel=JSONParser.makeHttpRequest(MyConstants.url_get_sitenaturel, "GET", params);
					//Just to see if it work
					Log.d("All geozones :",data_gzone.toString());
					Log.d("All cities :",data_city.toString());
					Log.d("All hotels :",data_hotel.toString());
					Log.d("All rating :",data_rating.toString());
					Log.d("All broker :",data_courtier.toString());
					Log.d("All natural site :",data_sitenaturel.toString());
					//Initialize the database

					//Insert data
					/*
					 * Table GeoZone
					 */
						int success=data_gzone.getInt(MyConstants.TAG_SUCCESS);
						if(success==1){
							gzones=data_gzone.getJSONArray("geozonelist");
							//Execute the json and add data in the database
			      			dbHelper.loadGeoZone(gzones);
						}
					/*
					 * Table City
					 */
						int success2=data_city.getInt(MyConstants.TAG_SUCCESS);
						if(success2==1){
							cities=data_city.getJSONArray("thecity");
							//Execute the json and add data in the database
			      			dbHelper.loadCities(cities);
						}
					/*
					 * Table Hotel
					 */
						int success1=data_hotel.getInt(MyConstants.TAG_SUCCESS);
						if(success1==1){
							hotels=data_hotel.getJSONArray("hotels");
							dbHelper.loadHotels(hotels);
						}
					/*
					 * Table Rating
					 */
					int success3=data_rating.getInt(MyConstants.TAG_SUCCESS);
					if(success3==1){
						ratings=data_rating.getJSONArray("votes");
						dbHelper.loadRate(ratings);
					}
					
					/*
					 * Table Courtier
					 */
					int success4=data_courtier.getInt(MyConstants.TAG_SUCCESS);
					if(success4==1){
						courtiers=data_courtier.getJSONArray("courtier");
						dbHelper.loadCourtiers(courtiers);
					}
					/*
					 * Table SiteNaturel
					 */
					int success5=data_sitenaturel.getInt(MyConstants.TAG_SUCCESS);
					if(success5==1){
						sitenaturels=data_sitenaturel.getJSONArray("naturalsite");
						dbHelper.loadNaturalSites(sitenaturels);
					}
						
					/*
					 * Table Image
					 */
						/*
						 * int success0=data_image.getInt(MyConstants.TAG_SUCCESS);
							if(success0==1){
							images=data_image.getJSONArray("image");
							imaHelper.loadImageData(images);
						}*/
					
					
					//Mark data as loaded
					JSONParser.setDataLoaded(getApplicationContext(), true);
					
				}catch (JSONException e) {
			        //throw new IllegalStateException("Could not parse to the server.",e);
					e.printStackTrace();
			    }
			
				
				return null;
		}

			@Override
			protected void onPostExecute(String file_url) {
				
				/*Toast.makeText(getApplicationContext(), "Data synchronization finished", Toast.LENGTH_LONG)
				.show();*/
			}
		}
	
		
		private boolean isOnline(){
			ConnectivityManager cm=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni=cm.getActiveNetworkInfo();
			if(ni !=null && ni.isConnected()){
				return true;
			}
			return false;
		}
		
}
