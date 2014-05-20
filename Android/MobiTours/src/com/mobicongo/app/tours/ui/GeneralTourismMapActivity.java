/**
 * 
 */
package com.mobicongo.app.tours.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiDatabaseContract;
import com.mobicongo.app.tours.controller.MobiToursBD;

/**
 * @author Mishka
 *
 */

public class GeneralTourismMapActivity extends SherlockFragmentActivity{

	GoogleMap mGoogleMap;
	Marker mMarker;
	LatLng mLatLng;
	LatLng mLatLng2;
	LatLng mCenterRdc=new LatLng(-2.24064,23.818359);
	String mSiteTitle;
	String mHotelTitle;
	String mCityTitle;
	String mGeozonetitle;
	double Latitude,Longitude;
	MobiToursBD db;
	private static final float CAMERA_ZOOM=6.75f;
	
	private final static String LOG_TAG=GeneralTourismMapActivity.class.getName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maptourismrdc);
		
		ActionBar actionBar=getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(R.string.app_name);
		
		db=new MobiToursBD(this);
		db.open();
		
		SupportMapFragment fm=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.tourismglobalmap);
		mGoogleMap=fm.getMap();
		
		loadLatLngDetailsFromDatabase();
		
		mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mGoogleMap.getUiSettings().setCompassEnabled(true);
		mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
		mGoogleMap.getUiSettings().setAllGesturesEnabled(true);
		
		try{
			MapsInitializer.initialize(this);
		}catch(GooglePlayServicesNotAvailableException e){
			Log.v("Could not show the map", "Error -- GooglePlayService not installed");
			e.printStackTrace();
		}
		
		mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mCenterRdc, CAMERA_ZOOM));
		mGoogleMap.animateCamera(CameraUpdateFactory.zoomIn());
		mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(CAMERA_ZOOM),2000,null);
		
		mMarker=mGoogleMap
				.addMarker(new MarkerOptions()
				.visible(true)
				.title("Dem. Rep. of Congo")
				.snippet("Tourism data overview")
				.position(mCenterRdc)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.drc_boundaries))
				);
		
		CameraPosition cameraPosition=new CameraPosition.Builder()
		.target(mCenterRdc)
		.zoom(7.75f)
		.bearing(90)
		.tilt(20)
		.build();
		mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
		loadLatLngDetailsFromDatabase();
		
	}

	private void loadLatLngDetailsFromDatabase() {
		
		Cursor c_geo=db.getAllZoneLocations();
		Cursor c_cite=db.getAllCities();
		Cursor c_hotel=db.getAllHotels();
		Cursor c_site=db.getAllSites();
		
		//Begin by loading geo zone values
		c_geo.moveToFirst();
		for(int i=0;i<c_geo.getCount();i++){
			Latitude=c_geo.getDouble(c_geo.getColumnIndexOrThrow(MobiToursBD.KEY_LAT_ZONE));
			Longitude=c_geo.getDouble(c_geo.getColumnIndexOrThrow(MobiToursBD.KEY_LNG_ZONE));
			mGeozonetitle=c_geo.getString(c_geo.getColumnIndexOrThrow(MobiToursBD.KEY_NAME_ZG));
			
			mLatLng=new LatLng(Latitude,Longitude);
			
			mGoogleMap.addMarker(new MarkerOptions()
				.title(mGeozonetitle)
				.visible(true)
				.position(mLatLng)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.drc_zone))
			);
			
			c_geo.moveToNext();
			
		}
		//Begin by loading city values
		c_cite.moveToFirst();
		for(int j=0;j<c_cite.getCount();j++){
			Latitude=c_cite.getDouble(c_cite.getColumnIndexOrThrow(MobiToursBD.key_latcity));
			Longitude=c_cite.getDouble(c_cite.getColumnIndexOrThrow(MobiToursBD.key_lngcity));
			mCityTitle=c_cite.getString(c_cite.getColumnIndexOrThrow(MobiToursBD.key_name_city));
			
			mLatLng=new LatLng(Latitude,Longitude);
			
			mGoogleMap.addMarker(new MarkerOptions()
				.title(mCityTitle)
				.visible(true)
				.position(mLatLng)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.drc_town))
			);
			
			c_cite.moveToNext();
		}
		//Begin by loading hotel values
		c_hotel.moveToFirst();
		for(int k=0;k<c_hotel.getCount();k++){
			Latitude=c_hotel.getDouble(c_hotel.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LATITUDE));
			Longitude=c_hotel.getDouble(c_hotel.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LONGITUDE));
			mHotelTitle=c_hotel.getString(c_hotel.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_TITLE));
			
			mLatLng=new LatLng(Latitude,Longitude);
			
			mGoogleMap.addMarker(new MarkerOptions()
				.title(mHotelTitle)
				.visible(true)
				.position(mLatLng)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.drc_hotel))
			);
			
			c_hotel.moveToNext();
		}	
		//Begin by loading naturals sites values
		c_site.moveToFirst();
		for(int i=0;i<c_site.getCount();i++){
			Latitude=c_site.getDouble(c_site.getColumnIndexOrThrow(MobiDatabaseContract.SiteNaturel.COLUMN_LATITUDE));
			Longitude=c_site.getDouble(c_site.getColumnIndexOrThrow(MobiDatabaseContract.SiteNaturel.COLUMN_LONGITUDE));
			mSiteTitle=c_site.getString(c_site.getColumnIndexOrThrow(MobiDatabaseContract.SiteNaturel.COLUMN_TITLE));
			
			mLatLng=new LatLng(Latitude,Longitude);
			
			mGoogleMap.addMarker(new MarkerOptions()
				.title(mSiteTitle)
				.visible(true)
				.position(mLatLng)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.drc_site))
			);
			
			c_site.moveToNext();
		}	
		
		
		
		
	}
	

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
		
	
	
}
