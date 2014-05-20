package com.mobicongo.app.tours.fragments;

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
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.ui.CitiesFragmentActivity;
import com.mobicongo.app.tours.ui.CityParentActivity;

public class CitiesListFragment extends SherlockFragment{

	MobiToursBD db;
	SimpleCursorAdapter cityAdapter;
	ListView lv;
	TextView header;
	String zname;
	int idzone;
	

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
				
		Cursor c=((CitiesFragmentActivity)getSherlockActivity()).mobidb.getAllCitiesLocationByZone(idzone);
		
		String[] colonnes=new String[]{
			MobiToursBD.key_name_city,
			MobiToursBD.key_desc_city
		};
		
		int[] to=new int[]{
			R.id.cityName,R.id.citydesc	
		};
		
		cityAdapter=new SimpleCursorAdapter(rv.getContext(),R.layout.city_item,
				c,
				colonnes,
				to,
				0
				);
		
		lv.setAdapter(cityAdapter);
		cityAdapter.notifyDataSetChanged();
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> lvi, View v, int pos,
					long id) {
				
					idzone=getSherlockActivity().getIntent().getIntExtra("myZoneid", 0);
				
					Cursor c=(Cursor)lvi.getItemAtPosition(pos);
					String xname=c.getString(c.getColumnIndex("nameCity"));
					int xid=c.getInt(c.getColumnIndex("idcity"));
				
				//Toast.makeText(getSherlockActivity(), "Ceci a ete clicke "+ xname , Toast.LENGTH_LONG).show();
					
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
		
		return rv;
	}

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		idzone=((CitiesFragmentActivity)getSherlockActivity()).getIntent().getIntExtra("myZoneid", 0);
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
	}


	
}
