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

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.ui.CitiesFragmentActivity;
import com.mobicongo.app.tours.ui.ZoneFragmentActivity;

public class ZoneGeoListFragment extends SherlockFragment{

	//private MobiToursBD mobiHelper;
	private SimpleCursorAdapter dbAdapter;
	ListView lv1;
	
	public ZoneGeoListFragment(){
		//null constructor
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,
			Bundle savedInstanceState){
		setRetainInstance(true);
		
		View vi=inflater.inflate(R.layout.zonefgm_list,container,false);
		
		getActivity().setTitle(R.string.app_name);
		//setHasOptionsMenu(true);
		
		final ListView lv1=(ListView)vi.findViewById(R.id.zg_listv);
		
		/*mobiHelper=new MobiToursBD(getActivity());
			mobiHelper.open();*/
		
	//	InitDbase();
		
		((ZoneFragmentActivity)getSherlockActivity()).mb_database.open();
		
		Cursor c=((ZoneFragmentActivity)getSherlockActivity()).mb_database.getAllZoneLocations();
		String[] cols=new String[]{
			MobiToursBD.KEY_NAME_ZG,
			MobiToursBD.KEY_DESC_ZG
		};
		
		int[] to=new int[]{
			R.id.zonename, R.id.etendu	
		};
		
		dbAdapter=new SimpleCursorAdapter(vi.getContext(),R.layout.zone_item1,
				c,
				cols,
				to,
				0);
		
		lv1.setAdapter(dbAdapter);
		
		lv1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> lv, View v, int position,
					long id) {
				Cursor cc=(Cursor)lv.getItemAtPosition(position);
				String zName=cc.getString(cc.getColumnIndexOrThrow("nameZoneGeo"));
				int zId=cc.getInt(cc.getColumnIndexOrThrow("zoneGeoId"));
					Intent i=new Intent(getActivity(),CitiesFragmentActivity.class);
					i.putExtra("myZoneid", zId);
					i.putExtra("myZonename", zName);
					//database is not needed again
					((ZoneFragmentActivity)getSherlockActivity()).mb_database.close();
					//launch the intent
					startActivity(i);				
			}
			
		});
		
		Log.v("ZoneGeoListFragment","onCreate");
		return vi;
	}
	
	/*private void InitDbase(){
		Cursor cr=((ZoneFragmentActivity)getSherlockActivity()).mb_database.getAllZoneLocations();
		if(cr==null){
			((ZoneFragmentActivity)getSherlockActivity()).mb_database.deleteAllZoneGeo();
			((ZoneFragmentActivity)getSherlockActivity()).mb_database.createInitZone();
		}
	}*/

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getSherlockActivity().getSupportActionBar().setNavigationMode(
				ActionBar.NAVIGATION_MODE_STANDARD);

	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	
}
