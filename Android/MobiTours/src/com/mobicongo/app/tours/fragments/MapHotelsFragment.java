/**
 * 
 */
package com.mobicongo.app.tours.fragments;

import android.database.Cursor;
import android.os.Bundle;
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
import com.mobicongo.app.tours.ui.HotelListOrMapActivity;


/**
 * @author Mishka
 *
 */


public class MapHotelsFragment extends SherlockFragment{

	MapView mV;
	GoogleMap mMap;
	Marker mMarker;
	LatLng mPos;
	public int townid=0;
	
	MobiToursBD db;
	
	/*public MapHotelsFragment(){
		//null constructor
	}*/

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		townid=((HotelListOrMapActivity)getSherlockActivity()).getResultFromIntent();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		View rv=inflater.inflate(R.layout.fragment_map, container, false);
		
		getActivity().setTitle(R.string.app_name);
		
		mV=(MapView)rv.findViewById(R.id.mapview);
		mV.onCreate(savedInstanceState);
		
		db=new MobiToursBD(getSherlockActivity());
		db.open();
		
		Cursor citylatlng=db.returnOneCity(townid);/*((HotelListOrMapActivity)getSherlockActivity()).bd.returnOneCity(townid);*/
		
		mPos=new LatLng(
				citylatlng.getDouble(citylatlng.getColumnIndexOrThrow(MobiToursBD.key_latcity)),
				citylatlng.getDouble(citylatlng.getColumnIndexOrThrow(MobiToursBD.key_lngcity))
				);
		//citylatlng.close();
		
		setUpMapIfNeeded();
		
		ShowAllHotelInTheMap();
		
		return rv;
	}

	private void setUpMapIfNeeded() {
		
		mMap=mV.getMap();
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.getUiSettings().setCompassEnabled(true);
		mMap.getUiSettings().setMyLocationButtonEnabled(true);
		
		try{
			MapsInitializer.initialize(this.getSherlockActivity());
		}catch(GooglePlayServicesNotAvailableException e){
			e.printStackTrace();
		}
		
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mPos,15));
		mMap.animateCamera(CameraUpdateFactory.zoomIn());
		mMap.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);
		
		mMarker=mMap
				.addMarker(new MarkerOptions()
				.visible(true)
				.position(mPos)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_hotel))
						);
		CameraPosition cameraPosition=new CameraPosition.Builder()
			.target(mPos)
			.zoom(18f)
			.bearing(90)
			.tilt(30)
			//.target(arg0)
			.build();
		
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
		
	}

	//Here we will load data from database
	private void ShowAllHotelInTheMap() {
		
		//Create cursor
		Cursor data=db.fetchingAllHotels(townid);/*((HotelListOrMapActivity)getSherlockActivity()).bd.fetchingAllHotels(townid);*/
		data.moveToFirst();
		for(int i=0;i<data.getCount();i++){
			Double lat=data.getDouble(data.getColumnIndex(MobiDatabaseContract.Hotel.COLUMN_LATITUDE));
			Double lng=data.getDouble(data.getColumnIndex(MobiDatabaseContract.Hotel.COLUMN_LONGITUDE));
			String title=data.getString(data.getColumnIndex(MobiDatabaseContract.Hotel.COLUMN_TITLE));
					
			mMap.addMarker(new MarkerOptions()
				.title(title)
				.position(new LatLng(lat,lng))
				.visible(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_hotel))
			);
			
			data.moveToNext();
		}
		
	}

	
	@Override
	public void onDestroy() {
		mV.onDestroy();
		super.onDestroy();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getSherlockActivity().getSupportActionBar().setNavigationMode(
				ActionBar.NAVIGATION_MODE_STANDARD);
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mV.onLowMemory();
	}

	@Override
	public void onResume() {
		super.onResume();
		mV.onResume();
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
}
