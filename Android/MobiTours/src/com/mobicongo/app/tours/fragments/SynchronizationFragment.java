/**
 * 
 */
package com.mobicongo.app.tours.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.json.JSONParser;
import com.mobicongo.app.tours.ui.ZoneFragmentActivity;
import com.mobicongo.app.tours.utils.MyConstants;

/**
 * @author Mishka ( misamuna@gmail.com)
 */

public class SynchronizationFragment extends SherlockFragment{

	//Initialization of variables
	//Database
	MobiToursBD database;
	//Other
	private ProgressDialog pDialog;
	JSONParser jParser=new JSONParser();

	private ProgressBar pb_sync;
	private TextView tv_sync;
	
	//List of Array
	JSONArray cities=null;
	
	//List of query parameters
	String id_zone;
	String id_city;
	String id_user;
	
	private static final String url_get_geozone=MyConstants.BASE_URL+"";
	private static final String url_get_cities=MyConstants.BASE_URL+"get_city_by_zone.php";
	private static final String url_get_hotel=MyConstants.BASE_URL+"";
	private static final String url_get_restaurant=MyConstants.BASE_URL+"";
	private static final String url_get_sitenaturel=MyConstants.BASE_URL+"";
	private static final String urg_get_pointofinterest=MyConstants.BASE_URL+"";
	
	public SynchronizationFragment(){
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		View rv=inflater.inflate(R.layout.sync_layout, container,false);
		getActivity().setTitle(R.string.app_name);
		
		pb_sync=(ProgressBar)rv.findViewById(R.id.pb_sync);
		tv_sync=(TextView)rv.findViewById(R.id.tv_sync);
		tv_sync.setText(R.string.synchronizing);
		
		database=new MobiToursBD(getSherlockActivity());
		database.open();
			
		saveToCache();
		
		return rv;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getSherlockActivity().getSupportActionBar().setNavigationMode(
				ActionBar.NAVIGATION_MODE_STANDARD);

		//put something here
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	private void saveToCache(){
		new LoadCitiesFromUrl().execute(); //City Table
	}
	
	public void setOnPreExecute(){
		pDialog=new ProgressDialog(getSherlockActivity());
		pDialog.setMessage("Downloading in process ... ");
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(true);
		pDialog.show();
	}
	
	//TABLE CITY IN CACHE
	//Thread to Load City Data from MySql Server and add them to the local database 
	class LoadCitiesFromUrl extends AsyncTask<String,String,String>{

		@Override
		protected void onPreExecute() {	
			super.onPreExecute();
			setOnPreExecute();
		}
		
		@Override
		protected String doInBackground(String... args) {
	
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			//for each query where it need to provide a value you must add a params basicnamevaluepair
			params.add(new BasicNameValuePair("idzone", "1"));
			JSONObject json=jParser.makeHttpRequest(url_get_cities, "GET", params);
			
			Log.d("All cities :",json.toString());
			
			try{
				int success=json.getInt(MyConstants.TAG_SUCCESS);
				if(success==1){
					cities=json.getJSONArray(MyConstants.TAG_CITY);
					//Execute the json and add data in the database
					database.loadCities(cities);
					}
			}catch(JSONException e){
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
		}
	}
	//TABLE COMMENTS IN CACHE
	//Thread to Load Comments Data from MySql Server and add them to the local database
	
	
}
