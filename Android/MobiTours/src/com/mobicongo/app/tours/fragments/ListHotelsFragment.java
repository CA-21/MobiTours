/**
 * 
 */
package com.mobicongo.app.tours.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiDatabaseContract;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.ui.HotelListOrMapActivity;
import com.mobicongo.app.tours.ui.HotelParentActivity;

/**
 * @author Mishka
 *
 */


public class ListHotelsFragment extends SherlockFragment{

	MobiToursBD bd;
	SimpleCursorAdapter hadapter;
	ListView lv;
	public int townid=0;
	
	public ListHotelsFragment(){
		//null constructor
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		townid=((HotelListOrMapActivity)getSherlockActivity()).getResultFromIntent();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		setRetainInstance(true);
		
		View rv=inflater.inflate(R.layout.list_hotel, container, false);
		
		getActivity().setTitle(R.string.app_name);
		
		lv=(ListView)rv.findViewById(R.id.lv_hotel);
					
		bd=new MobiToursBD(getSherlockActivity());
		bd.open();

		loadHotelsInList();
		
		return rv;
	}

	//Load data from database
	private void loadHotelsInList() {
		
		/*Cursor h=((HotelListOrMapActivity)getSherlockActivity()).bd.fetchingAllHotels(townid);*/
		Cursor h=bd.fetchingAllHotels(townid);
		
		String[] col=new String[] {
				MobiDatabaseContract.Hotel.COLUMN_ROOMPRICEMIN,
				MobiDatabaseContract.Hotel.COLUMN_ROOMPRICEMAX,
				MobiDatabaseContract.Hotel.COLUMN_TITLE,
				MobiDatabaseContract.Hotel.COLUMN_ADRESS,
				MobiDatabaseContract.Hotel.COLUMN_STAR
		};
		
		int[] to=new int[]{
				R.id.tv_min_price,
				R.id.tv_max_price,
				R.id.tv_title,
				R.id.tv_adresse,
				R.id.tv_star
		};
		
		hadapter=new SimpleCursorAdapter(getSherlockActivity(),R.layout.item_hotel,
				h,
				col,
				to,
				0
				);
		
		lv.setAdapter(hadapter);
		
		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> list, View v, int pos,
					long id) {
				Cursor c=(Cursor)list.getItemAtPosition(pos);
				int idhotel=c.getInt(c.getColumnIndexOrThrow("pointinterestid"));
				Intent i=new Intent(getSherlockActivity(),HotelParentActivity.class);
				i.putExtra("id", idhotel);
				startActivity(i);
			}
		});
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getSherlockActivity().getSupportActionBar().setNavigationMode(
				ActionBar.NAVIGATION_MODE_STANDARD);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	
}
