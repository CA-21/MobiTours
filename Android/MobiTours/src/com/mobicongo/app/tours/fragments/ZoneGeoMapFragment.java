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
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.ui.ZoneFragmentActivity;

/**
 * @author Mishka
 *
 */

public class ZoneGeoMapFragment extends SherlockFragment{

	MapView mv;
	GoogleMap gm;
	Marker marker;
	LatLng iPos;
	//MobiToursBD mb_database;
	private final static String LOG_TAG=ZoneGeoMapFragment.class.getName();
	
	//Null constructor
	public ZoneGeoMapFragment(){
		//nothing
	}
		
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		setRetainInstance(true);
		View v=inflater.inflate(R.layout.fragment_map, container,false);
		
		getActivity().setTitle(R.string.app_name);
		//setHasOptionsMenu(true);
		
		mv=(MapView)v.findViewById(R.id.mapview);
		mv.onCreate(savedInstanceState);
		
		/*mb_database=new MobiToursBD(getSherlockActivity());
		mb_database.open();*/
	
		setUpMapIfNeeded();
	
		loadGeoZoneFromDatabase();
		
		return v;
	}

	private void setUpMapIfNeeded() {
		
		iPos=new LatLng(-2.24064,23.818359);
		gm=mv.getMap();
		gm.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		gm.getUiSettings().setCompassEnabled(true);
		gm.getUiSettings().setMyLocationButtonEnabled(true);
		
		try{
			MapsInitializer.initialize(this.getSherlockActivity());
		}catch(GooglePlayServicesNotAvailableException e){
			e.printStackTrace();
		}
		
		gm.moveCamera(CameraUpdateFactory.newLatLngZoom(iPos, 10));
		gm.animateCamera(CameraUpdateFactory.zoomIn());
		gm.animateCamera(CameraUpdateFactory.zoomTo(10),2000,null);
		//add marker for the initial value
		marker=gm
				.addMarker(new MarkerOptions()
				.title("DRC")
				.visible(true)
				.position(iPos)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_init))
				);
		CameraPosition cameraPosition=new CameraPosition.Builder()
				.target(iPos)
				.zoom(8)
				.bearing(90)
				.tilt(30)
				.build();
		gm.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
	}

	private void loadGeoZoneFromDatabase() {
		
		Double lat=0.0;
		Double lng=0.0;
		String zone="";
				
		Cursor c=((ZoneFragmentActivity)getSherlockActivity()).mb_database.getAllZoneLocations();
		c.moveToFirst();
		for(int i=0;i<c.getCount();i++){
			lat=c.getDouble(c.getColumnIndex(MobiToursBD.KEY_LAT_ZONE));
			lng=c.getDouble(c.getColumnIndex(MobiToursBD.KEY_LNG_ZONE));
			zone=c.getString(c.getColumnIndex(MobiToursBD.KEY_NAME_ZG));
			
			gm.addMarker(new MarkerOptions()
				.title(c.getString(c.getColumnIndex(MobiToursBD.KEY_NAME_ZG)))
				.position(new LatLng(
						c.getDouble(c.getColumnIndex(MobiToursBD.KEY_LAT_ZONE)),
						c.getDouble(c.getColumnIndex(MobiToursBD.KEY_LNG_ZONE))
						))
				.visible(true)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_n_i))
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
		//mb_database.close();
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
