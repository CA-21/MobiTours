package com.mobicongo.app.tours.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.adapter.HotelListAdapter;
import com.mobicongo.app.tours.controller.MobiDatabaseContract;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.json.JSONParser;
import com.mobicongo.app.tours.ui.HotelListOrMapActivity;
import com.mobicongo.app.tours.ui.HotelParentActivity;
import com.mobicongo.app.tours.utils.MyConstants;

public class TestListHotelsFragment extends SherlockFragment {

	MobiToursBD bd;
	SimpleCursorAdapter hadapter;
	ListView lv;
	public int townid=0;
	private ProgressDialog pDialog;
	JSONParser jParser;
	
	ArrayList<HashMap<String,String>> hotels_rs;
	//HashMap<String,String> map;
	
	HotelListAdapter hhadapter;
	
	private static String url_hotels_rs=MyConstants.url_get_all_hotel;
	
	private static final String TAG_MIN_PRICE=MobiDatabaseContract.Hotel.COLUMN_ROOMPRICEMIN;
	private static final String TAG_MAX_PRICE=MobiDatabaseContract.Hotel.COLUMN_ROOMPRICEMAX;
	private static final String TAG_TITLE=MobiDatabaseContract.Hotel.COLUMN_TITLE;
	private static final String TAG_ADDRESS=MobiDatabaseContract.Hotel.COLUMN_ADRESS;
	private static final String TAG_STAR=MobiDatabaseContract.Hotel.COLUMN_STAR;
	private static final String TAG_LINK_IMAGE=MobiDatabaseContract.Hotel.COLUMN_PICTUREURL;
	private static final String TAG_ID_HOTEL=MobiDatabaseContract.Hotel.COLUMN_POIID;
	
	public TestListHotelsFragment(){
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

		hotels_rs=new ArrayList<HashMap<String,String>>();
		
		loadHotelsInList();
		//loadHotelsInListBeta();
		
		
		return rv;
	}

	public void SetListViewAdapter(ArrayList<HashMap<String,String>> data){
		hhadapter=new HotelListAdapter(getSherlockActivity(),data);
		lv.setAdapter(hhadapter);
	}
	
	private void loadHotelsInList() {
		 
		Cursor h=bd.fetchingAllHotels(townid);
		h.moveToFirst();
		for(int i=0;i<h.getCount();i++){
	
			String hminprice=h.getString(h.getColumnIndex(TAG_MIN_PRICE));
			String hmaxprice=h.getString(h.getColumnIndex(TAG_MAX_PRICE));
			String htitle=h.getString(h.getColumnIndexOrThrow(TAG_TITLE));
			String haddress=h.getString(h.getColumnIndexOrThrow(TAG_ADDRESS));
			String hlinkimg=h.getString(h.getColumnIndexOrThrow(TAG_LINK_IMAGE));
			String hstar=h.getString(h.getColumnIndexOrThrow(TAG_STAR));
			
			String hidhotel=h.getString(h.getColumnIndexOrThrow(TAG_ID_HOTEL)); //id de l'hotel
			
			HashMap<String,String> map=new HashMap<String,String>();
			
			map.put(TAG_MIN_PRICE, hminprice);
			map.put(TAG_MAX_PRICE, hmaxprice);
			map.put(TAG_TITLE, htitle);
			map.put(TAG_ADDRESS, haddress);
			map.put(TAG_LINK_IMAGE, hlinkimg);
			map.put(TAG_STAR, hstar);
			map.put(TAG_ID_HOTEL, hidhotel); //Id de l'hotel
			
			hotels_rs.add(map);
		
			h.moveToNext();
		}

		SetListViewAdapter(hotels_rs);

		lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> list, View view, int pos,
					long id) {
				//Cursor c=(Cursor)list.getItemAtPosition(pos);
				/*String val=((TextView)v.findViewById(R.id.tv_id_h)).toString();
				int idhotel=Integer.parseInt(val);*/
				//int idhotel=Integer.parseInt(c.getString(c.getColumnIndexOrThrow("pointinterestid")));
				//int idhotel=c.getInt(c.getColumnIndex("pointofinterestid"));
				
				String val=((TextView)view.findViewById(R.id.tv_id_h)).getText().toString();
				int idhotel=Integer.parseInt(val);
				
				Intent i=new Intent(getSherlockActivity(),HotelParentActivity.class);
				i.putExtra("id", idhotel);
				startActivity(i);
			}
		});
		
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
	
	//Load data from database
		private void loadHotelsInListBeta() {
			
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

	
	
	
}
