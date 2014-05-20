package com.mobicongo.app.tours.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.adapter.VillesListAdapter;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.ui.CitiesFragmentActivity;
import com.mobicongo.app.tours.ui.CityParentActivity;

public class TownListFragment extends SherlockFragment{

	MobiToursBD db;
	SimpleCursorAdapter cityAdapter;
	ListView lv;
	TextView header;
	String zname;
	public int idzone;
	ArrayList<HashMap<String,String>> c_rs;
	VillesListAdapter v_adapter;  
	Cursor city;
	
	private static final String TAG_NAME=MobiToursBD.key_name_city;
	private static final String TAG_DESC=MobiToursBD.key_desc_city;
	private static final String TAG_ID=MobiToursBD.key_idcity;
	private static final String TAG_LINK_IMAGE=MobiToursBD.cityurlimage;
	

	public TownListFragment(){
		//null constructor
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		idzone=((CitiesFragmentActivity)getSherlockActivity()).getResultFromIntent();
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		
		View rv=inflater.inflate(R.layout.citylayout, container,false);
		
		getActivity().setTitle(R.string.app_name);
		//setHasOptionsMenu(true);
		
		lv=(ListView)rv.findViewById(R.id.city_lsv);
		
		/*db=new MobiToursBD(getSherlockActivity());
		db.open();*/
				
		//Cursor c=((CitiesFragmentActivity)getSherlockActivity()).mobidb.getAllCitiesLocationByZone(idzone);
				
		c_rs=new ArrayList<HashMap<String,String>>();	
		
		loadVillesInList();
		
		return rv;
	}

	
	public void setListViewAdapter(ArrayList<HashMap<String,String>> data){
		v_adapter=new VillesListAdapter(getSherlockActivity(),data);
		lv.setAdapter(v_adapter);
		v_adapter.notifyDataSetChanged();
	}
	
	
	private void loadVillesInList(){
		
		city=((CitiesFragmentActivity)getSherlockActivity()).mobidb.getAllCitiesLocationByZone(idzone);
		city.moveToFirst();
		for(int i=0;i<city.getCount();i++){
			String cname=city.getString(city.getColumnIndex(TAG_NAME));
			String cdesc=city.getString(city.getColumnIndex(TAG_DESC));
			String cid=city.getString(city.getColumnIndex(TAG_ID));
			String curl=city.getString(city.getColumnIndex(TAG_LINK_IMAGE));
			
			HashMap<String,String> map=new HashMap<String,String>();
			
			map.put(TAG_ID, cid);
			map.put(TAG_NAME, cname);
			map.put(TAG_DESC, cdesc);
			map.put(TAG_LINK_IMAGE, curl);
			
			c_rs.add(map);
			
			city.moveToNext();
			
			setListViewAdapter(c_rs);
			
			lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> ls, View view,
						int position, long id) {
					//idzone=0;
					
					String val=((TextView)view.findViewById(R.id.idcity)).getText().toString();
					String xname=((TextView)view.findViewById(R.id.cityName)).getText().toString();
					int xid=Integer.parseInt(val);
					
					Intent i=new Intent(getActivity(),CityParentActivity.class);
					i.putExtra("CName", xname);
					i.putExtra("Cid", xid);
					//database is not needed, close it
					((CitiesFragmentActivity)getSherlockActivity()).mobidb.close();
					//launch the intent
					startActivity(i);
					
				}
				
			});
			Log.v("CitiesListFragment","onCreate");
			
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
		super.onDestroy();
		//mobidb.close();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		city=((CitiesFragmentActivity)getSherlockActivity()).mobidb.getAllCitiesLocationByZone(idzone);
	}


	
}
