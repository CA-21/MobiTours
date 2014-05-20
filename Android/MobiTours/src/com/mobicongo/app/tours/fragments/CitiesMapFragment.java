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
import com.mobicongo.app.tours.controller.MobiToursBD;

/**
 * @author Mishka
 *
 */
public class CitiesMapFragment extends SherlockFragment{

	MapView mv;
	GoogleMap gm;
	Marker marker;
	LatLng iPos;
	int idzone1=0;
	MobiToursBD mb_database;
	private final static String LOG_TAG=CitiesMapFragment.class.getName();
	
	//Null constructor
	public CitiesMapFragment(){
		//nothing
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		idzone1=getSherlockActivity().getIntent().getIntExtra("myZoneid", 0);
				
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
		
		mb_database=new MobiToursBD(getSherlockActivity());
		mb_database.open();
		
		loadGeozoneCoordinates(idzone1); //load the coordinate of theGeoZone
		//Load all cities by geozone locations from database
		loadCitiesByGeoZoneFromDatabase(idzone1);
				
		return v;
	}


	private void loadGeozoneCoordinates(int iz){
		
		Double latz=0.0;
		Double lngz=0.0;
		String name="";
		
		Cursor z=mb_database.getOneGeoZoneValues(iz);
		
		latz=z.getDouble(z.getColumnIndex(MobiToursBD.KEY_LAT_ZONE));
		lngz=z.getDouble(z.getColumnIndex(MobiToursBD.KEY_LNG_ZONE));
		name=z.getString(z.getColumnIndex(MobiToursBD.KEY_NAME_ZG));
		
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
		
		gm.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latz,lngz), 15));
		gm.animateCamera(CameraUpdateFactory.zoomTo(15),2000,null);
		
		gm.addMarker(new MarkerOptions()
		.title(name)
		.visible(true)
		.position(new LatLng(latz,lngz))
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.drc_zone))
		);
		
		//Before updating the new animate camera, change properties of CameraUpdate
		CameraPosition cameraPosition=new CameraPosition.Builder()
		.target(new LatLng(latz,lngz))
		.zoom(12)
		.bearing(90)
		.tilt(65)
		.build();
		
		gm.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
	}
	
	private void loadCitiesByGeoZoneFromDatabase(int idz) {
		
		Double latc=0.0;
		Double lngc=0.0;
		String city="";
		LatLng new_iPos;
		
		Cursor c=mb_database.getAllCitiesLocationByZone(idz);
		c.moveToFirst();
		for(int i=0;i<c.getCount();i++){
			latc=c.getDouble(c.getColumnIndex(MobiToursBD.key_latcity));
			lngc=c.getDouble(c.getColumnIndex(MobiToursBD.key_lngcity));
			city=c.getString(c.getColumnIndex(MobiToursBD.key_name_city));
			
			new_iPos=new LatLng(latc,lngc);
			
			gm.addMarker(new MarkerOptions()
				.title(city)
				.visible(true)
				.position(new_iPos)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.drc_town))
				);
			//Move to next
			c.moveToNext();
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
