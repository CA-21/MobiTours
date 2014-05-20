/**
 * 
 */
package com.mobicongo.app.tours.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.database.Cursor;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiDatabaseContract;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.json.DirectionsJSONParser;

/**
 * @author Mishka
 *
 */


public class AdvancedMapActionActivity extends SherlockFragmentActivity implements OnClickListener,LocationListener{

	public int poi_id;
	public String type_poi,title;
	public MobiToursBD db;
	private static final float CAMERA_ZOOM=17.75f;
	private GoogleMap mGoogleMap;
	private Marker mMarker;
	LatLng mLatLng;
	LatLng mLatLngFrom;
	LatLng mLatLngTo;
	private MapView mMapView;
	ImageButton btn_get_direction,btn_current_pos;
	ArrayList<LatLng> mMarkerPoints;
	public LatLng MishkaHouse=new LatLng(-1.67033,29.20494);
	double latitude,lat;
	double longitude,lng;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map_advanced_layout);
		ActionBar actionBar=getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		
		actionBar.setTitle(R.string.app_name);
		
		getResultFromIntent();
		getTypePoiResultFromIntent();
		
		db=new MobiToursBD(this);
		db.open();
		
		btn_get_direction=(ImageButton)findViewById(R.id.btn_get_direction);
		btn_current_pos=(ImageButton)findViewById(R.id.btn_current_pos);
		
		/*mMapView=(MapView)findViewById(R.id.mapdata);
		mMapView.onCreate(savedInstanceState);*/
		SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapview);
		mGoogleMap=fm.getMap();
		
		setUpMap(poi_id);
		
		btn_get_direction.setOnClickListener(this);
		btn_current_pos.setOnClickListener(this);
		
	}
	
		
  private void setUpMap(int idpoi) {
	  
	  //if(type_poi=="hotel") {
	  
	   Cursor h=db.fetchingOneHotel(poi_id);
	   // Cursor h=db.fetchingOneHotel(1);
		lat=h.getDouble(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LATITUDE));
		lng=h.getDouble(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LONGITUDE));
		title=h.getString(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_TITLE));
		
		mLatLngTo=new LatLng(
				h.getDouble(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LATITUDE)),
				h.getDouble(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_LONGITUDE))
				);
		Toast.makeText(getApplicationContext(), lat+" "+lng, Toast.LENGTH_LONG).show();
	//  }
	  
		//mLatLngTo=mLatLng; //Define the POI as the destination LatLng
				
		//mGoogleMap=mMapView.getMap();
		mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		mGoogleMap.getUiSettings().setCompassEnabled(true);
		mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
		
		try{
			MapsInitializer.initialize(this);
		}catch(GooglePlayServicesNotAvailableException e){
			Log.v("Could not show the map", "Error -- GooglePlayService not installed");
			e.printStackTrace();
		}
		
		mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLngTo, CAMERA_ZOOM));
		mGoogleMap.animateCamera(CameraUpdateFactory.zoomIn());
		mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(CAMERA_ZOOM),2000,null);
		
		mMarker=mGoogleMap
				.addMarker(new MarkerOptions()
				.visible(true)
				.position(mLatLngTo)
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.drc_hotel))
				);
	
		CameraPosition cameraPosition=new CameraPosition.Builder()
		.target(mLatLngTo)
		.zoom(18f)
		.bearing(90)
		.tilt(85)
		.build();
		mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
		//mLatLngFrom=mLatLng;
		
		LOGD("Advanced map","Map setup complete");
		
	}

  private void LOGD(String string, String string2) {
		Log.d(string, string2);	
	}
  
  //Get the poi ID from the Intent
  	public int getResultFromIntent(){
  		poi_id=this.getIntent().getIntExtra("id", 0);
  		return poi_id;
  	}
  //Get the poi type from the Intent
  	public String getTypePoiResultFromIntent(){
  		type_poi=this.getIntent().getStringExtra("type_poi");
  		return type_poi;
  	}
	
  	 	
  	/*
	 * Allow to get the current direction
	 */
	private void getCurrentUserLocation() {

		 mGoogleMap.setMyLocationEnabled(true);
		 LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		 Criteria criteria=new Criteria();//creating criteria object to retrieve provider
		 //Getting the name of the best provider
		 String provider=locationManager.getBestProvider(criteria, true);
		 //Getting current Location
		 Location location=locationManager.getLastKnownLocation(provider);
		 
		 if(location!=null){
             onLocationChanged(location);
     }

     locationManager.requestLocationUpdates(provider, 20000, 0, this);
		
	}


	/*
	 * Allow to draw the direction from the user curren location to the POI Location
	 */
	private void getDirectionToPoi(LatLng from, LatLng to) {
				
		/*LatLng origin=MishkaHouse;
		LatLng dest=mLatLngTo;*/
		
		//Getting URL to the Google Direction API
		String url=getDirectionsUrl(from,to);
		
		DownloadTask downloadTask=new DownloadTask();
		//Start downloading json data from Google Directions API
		downloadTask.execute(url);
		
	}


	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.btn_get_direction){
			getDirectionToPoi(MishkaHouse,mLatLngTo);
		}
		else if (v.getId()==R.id.btn_current_pos){
			getCurrentUserLocation();
		}		
	}


	@Override
	public void onLocationChanged(Location location) {
		// Getting latitude of the current location
		double latitude=location.getLatitude();
		// Getting longitude of the current location
		double longitude=location.getLongitude();
		//set the current location in LatLng
		mLatLngFrom=new LatLng(latitude,longitude);
		
		mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(mLatLngFrom));
		//Zoom in the google map
		mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
		
	}

	private String getDirectionsUrl(LatLng origin,LatLng dest){
		
		// Origin of route
		String str_origin = "origin="+origin.latitude+","+origin.longitude;
		// Destination of route
		String str_dest = "destination="+dest.latitude+","+dest.longitude;				
		// Sensor enabled
		String sensor = "sensor=false";			
		// Building the parameters to the web service
		String parameters = str_origin+"&"+str_dest+"&"+sensor;
		// Output format
		String output = "json";
		// Building the url to the web service
		String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
		
		return url;
	}
	
	/** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
                URL url = new URL(strUrl);
                // Creating an http connection to communicate with url 
                urlConnection = (HttpURLConnection) url.openConnection();
                // Connecting to url 
                urlConnection.connect();
                // Reading data from url 
                iStream = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
                StringBuffer sb  = new StringBuffer();
                String line = "";
                while( ( line = br.readLine())  != null){
                        sb.append(line);
                }
                data = sb.toString();
                br.close();

        }catch(Exception e){
                Log.d("Exception while downloading url", e.toString());
        }finally{
                iStream.close();
                urlConnection.disconnect();
        }
        return data;
     }
    
 // Fetches data from url passed
 	private class DownloadTask extends AsyncTask<String, Void, String>{			
 				
 		// Downloading data in non-ui thread
 		@Override
 		protected String doInBackground(String... url) {
 				
 			// For storing data from web service
 			String data = "";
 					
 			try{
 				// Fetching the data from web service
 				data = downloadUrl(url[0]);
 			}catch(Exception e){
 				Log.d("Background Task",e.toString());
 			}
 			return data;		
 		}
 		
 		// Executes in UI thread, after the execution of
 		// doInBackground()
 		@Override
 		protected void onPostExecute(String result) {			
 			super.onPostExecute(result);			
 			
 			ParserTask parserTask = new ParserTask();
 			
 			// Invokes the thread for parsing the JSON data
 			parserTask.execute(result);
 				
 		}		
 	}
 	
 	/** A class to parse the Google Places in JSON format */
     private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
     	
     	// Parsing the data in non-ui thread    	
 		@Override
 		protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
 			
 			JSONObject jObject;	
 			List<List<HashMap<String, String>>> routes = null;			           
             
             try{
             	jObject = new JSONObject(jsonData[0]);
             	DirectionsJSONParser parser = new DirectionsJSONParser();
             	
             	// Starts parsing data
             	routes = parser.parse(jObject);    
             }catch(Exception e){
             	e.printStackTrace();
             }
             return routes;
 		}
 		
 		// Executes in UI thread, after the parsing process
 		@Override
 		protected void onPostExecute(List<List<HashMap<String, String>>> result) {
 			ArrayList<LatLng> points = null;
 			PolylineOptions lineOptions = null;
 			MarkerOptions markerOptions = new MarkerOptions();
 			
 			// Traversing through all the routes
 			for(int i=0;i<result.size();i++){
 				points = new ArrayList<LatLng>();
 				lineOptions = new PolylineOptions();
 				
 				// Fetching i-th route
 				List<HashMap<String, String>> path = result.get(i);
 				
 				// Fetching all the points in i-th route
 				for(int j=0;j<path.size();j++){
 					HashMap<String,String> point = path.get(j);					
 					double lat = Double.parseDouble(point.get("lat"));
 					double lng = Double.parseDouble(point.get("lng"));
 					LatLng position = new LatLng(lat, lng);	
 					points.add(position);						
 				}
 				
 				// Adding all the points in the route to LineOptions
 				lineOptions.addAll(points);
 				lineOptions.width(2);
 				lineOptions.color(Color.RED);	
 				
 			}
 			// Drawing polyline in the Google Map for the i-th route
 			mGoogleMap.addPolyline(lineOptions);							
 		}			
     }   
     
	
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}


	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}
	  	
	
	
}
