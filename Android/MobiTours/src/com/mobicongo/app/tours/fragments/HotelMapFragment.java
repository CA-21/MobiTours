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
import com.mobicongo.app.tours.ui.HotelParentActivity;



public class HotelMapFragment extends SherlockFragment {

	GoogleMap mMap;
	MapView mV;
	LatLng mPos;
	Marker mMarker;
	int hotelid;
	
	//private final Random mRandom=new Random();
	

	public HotelMapFragment(){
		//null constructor
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		hotelid=((HotelParentActivity)getSherlockActivity()).getResultFromIntent();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		View vi=inflater.inflate(R.layout.fragment_poi_site_map, container, false);
		
		getSherlockActivity().setTitle(R.string.app_name);
		
		mV=(MapView)vi.findViewById(R.id.poimapview);
		mV.onCreate(savedInstanceState);
		
		double lat=0.0;
		double lng=0.0;
		String title="";
		
		Cursor h=((HotelParentActivity)getSherlockActivity()).db.fetchingOneHotel(hotelid);
		
		lat=h.getDouble(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LATITUDE));
		lng=h.getDouble(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LONGITUDE));
		title=h.getString(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_TITLE));
		
		/*mPos=new LatLng(
				h.getDouble(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LATITUDE)),
				h.getDouble(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LONGITUDE))
				);*/
		
		mPos=new LatLng(lat,lng);
		
		mMap=mV.getMap();
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mMap.getUiSettings().setCompassEnabled(true);
		mMap.getUiSettings().setMyLocationButtonEnabled(true);
		mMap.getUiSettings().setRotateGesturesEnabled(true);
		mMap.getUiSettings().setScrollGesturesEnabled(true);
		mMap.getUiSettings().setTiltGesturesEnabled(true);
		mMap.getUiSettings().setAllGesturesEnabled(true);
		
		try{
			MapsInitializer.initialize(this.getSherlockActivity());
		}catch(GooglePlayServicesNotAvailableException e){
			Log.v("Could not show the map", "Error -- GooglePlayService not installed");
			e.printStackTrace();
		}
		
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mPos, 16));
		mMap.animateCamera(CameraUpdateFactory.zoomIn());
		mMap.animateCamera(CameraUpdateFactory.zoomTo(17),2000,null);
		
		mMarker=mMap
				.addMarker(new MarkerOptions()
				.visible(true)
				.position(mPos)
				.title(title)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin_hotel))
				);
	
		CameraPosition cameraPosition=new CameraPosition.Builder()
		.target(mPos)
		.zoom(18f)
		.bearing(90)
		.tilt(75)
		.build();
		
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			
		return vi;
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
	

	
	
	
	
}
