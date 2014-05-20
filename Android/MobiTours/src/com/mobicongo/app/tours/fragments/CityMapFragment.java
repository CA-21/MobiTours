/***********************************************************************
 * Module:  CitiesMapFragment.java
 * Author:  Mishka
 * Purpose: Defines the Class Courtier
 ***********************************************************************/
package com.mobicongo.app.tours.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiDatabaseContract;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.ui.CityParentActivity;

/**
 * @author Mishka
 */

public class CityMapFragment extends SherlockFragment{

	MapView mv;
	GoogleMap gm;
	Marker marker;
	LatLng mPos;
	LatLng mCityLocation;
	String mCityTitle;
	String mHotelTitle;
	String mSiteTitle;
	double Latitude,Longitude;
	int idc=0;
	MobiToursBD mb_database;
	private final static String LOG_TAG=CityMapFragment.class.getName();
	
	//Null constructor
	public CityMapFragment(){
		//nothing
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		idc=((CityParentActivity)getSherlockActivity()).getResultFromIntent();	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		View v=inflater.inflate(R.layout.fragment_map, container,false);
		
		getSherlockActivity().setTitle(R.string.app_name);
		//setHasOptionsMenu(true);
		
		mv=(MapView)v.findViewById(R.id.mapview);
		mv.onCreate(savedInstanceState);
		
		Cursor v_city=((CityParentActivity)getSherlockActivity()).db.returnOneCity(idc);
		
		mCityLocation=new LatLng(v_city.getDouble(v_city.getColumnIndex(MobiToursBD.key_latcity)),
				v_city.getDouble(v_city.getColumnIndex(MobiToursBD.key_lngcity))
				);
		mCityTitle=v_city.getString(v_city.getColumnIndex(MobiToursBD.key_name_city));
				
		//Zooming to the city
		gm=mv.getMap();
		gm.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		gm.getUiSettings().setCompassEnabled(true);
		gm.getUiSettings().setMyLocationButtonEnabled(true);
		
		try{
			MapsInitializer.initialize(this.getSherlockActivity());
		}catch(GooglePlayServicesNotAvailableException e){
			Log.v(LOG_TAG, "Error -- GooglePlayService not installed");
			e.printStackTrace();
		}
			
		gm.moveCamera(CameraUpdateFactory.newLatLngZoom(mCityLocation, 13));
		gm.animateCamera(CameraUpdateFactory.zoomTo(15),2000,null);
		gm.addMarker(new MarkerOptions()
		.title(mCityTitle)
		.visible(true)
		.position(mCityLocation)
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.trips_pin_transit))
		);
		
		//Before updating the new animate camera, change properties of CameraUpdate
		CameraPosition cameraPosition=new CameraPosition.Builder()
		.target(mCityLocation)
		.zoom(15f)
		.bearing(90)
		.tilt(70)
		.build();
		
		gm.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
		LoadPoiAndSiteInTheMap();
		
		return v;
	}


	
	private void LoadPoiAndSiteInTheMap() {
		//Create cursors
		Cursor v_h=((CityParentActivity)getSherlockActivity()).db.fetchingAllHotels(idc);
		Cursor v_s=((CityParentActivity)getSherlockActivity()).db.fetchingAllSiteNaturel(idc);
		
		//hotels
		v_h.moveToFirst();
		for(int i=0;i<v_h.getCount();i++){
			Latitude=v_h.getDouble(v_h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LATITUDE));
			Longitude=v_h.getDouble(v_h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LONGITUDE));
			mHotelTitle=v_h.getString(v_h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_TITLE));
			
			mPos=new LatLng(Latitude,Longitude);
			
			gm.addMarker(new MarkerOptions()
				.title(mHotelTitle)
				.visible(true)
				.position(mPos)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.drc_hotel))
					);
			v_h.moveToNext();//Go to the next hotel
		}
		//Naturals Sites
		v_s.moveToFirst();
		for(int j=0;j<v_s.getCount();j++){
			Latitude=v_s.getDouble(v_s.getColumnIndexOrThrow(MobiDatabaseContract.SiteNaturel.COLUMN_LATITUDE));
			Longitude=v_s.getDouble(v_s.getColumnIndexOrThrow(MobiDatabaseContract.SiteNaturel.COLUMN_LONGITUDE));
			mSiteTitle=v_s.getString(v_s.getColumnIndexOrThrow(MobiDatabaseContract.SiteNaturel.COLUMN_TITLE));
			
			mPos=new LatLng(Latitude,Longitude);
			
			gm.addMarker(new MarkerOptions()
				.title(mSiteTitle)
				.visible(true)
				.position(mPos)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.drc_site))
					);
			v_s.moveToNext();//Go to the next Natural Site
		}
		
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getSherlockActivity().getSupportActionBar().setNavigationMode(
				ActionBar.NAVIGATION_MODE_STANDARD);
	}
	
	
	@Override
	public void onDestroy() {
		mv.onDestroy();
		super.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mv.onLowMemory();
	}

	@Override
	public void onResume() {
		super.onResume();
		mv.onResume();
	}
	
	
}
