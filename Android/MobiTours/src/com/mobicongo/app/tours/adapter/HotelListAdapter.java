/**
 * 
 */
package com.mobicongo.app.tours.adapter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiDatabaseContract;
import com.mobicongo.app.tours.utils.ImageLoader;

/**
 * @author Mishka
 *
 */
public class HotelListAdapter extends BaseAdapter{

	private Activity activity;
	private ArrayList<HashMap<String,String>> data;
	private static LayoutInflater inflater=null;
	public ImageLoader imageLoader;
	
	public HotelListAdapter(Activity a,ArrayList<HashMap<String,String>> d)
	{
		activity=a;
		data=d;
		inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader=new ImageLoader(activity.getApplicationContext());
	}
	
	public int getCount(){
		return data.size();
	}
	
	public Object getItem(int position){
		return position;
	}
	
	public HashMap getItemAtPosition(int position){
		return data.get(position);
	}
	
	public long getItemId(int position){
		return position;
	}
	
	public View getView(int position, View convertView,ViewGroup parent)
	{
		View vi=convertView;
		if(convertView==null)
			vi=inflater.inflate(R.layout.b_item_hotel, null);
			
		TextView minprice=(TextView)vi.findViewById(R.id.tv_min_price1);
		TextView maxprice=(TextView)vi.findViewById(R.id.tv_max_price1);
		TextView title=(TextView)vi.findViewById(R.id.tv_title1);
		TextView address=(TextView)vi.findViewById(R.id.tv_adresse1);
		TextView star=(TextView)vi.findViewById(R.id.tv_star1);
		ImageView thumb_image=(ImageView)vi.findViewById(R.id.imghotel1);
		
		TextView id_h=(TextView)vi.findViewById(R.id.tv_id_h);
		
		
		HashMap<String,String> hotel_recordset=new HashMap<String,String>();
		hotel_recordset=data.get(position);
		
		minprice.setText(hotel_recordset.get(MobiDatabaseContract.Hotel.COLUMN_ROOMPRICEMIN));
		maxprice.setText(hotel_recordset.get(MobiDatabaseContract.Hotel.COLUMN_ROOMPRICEMAX));
		title.setText(hotel_recordset.get(MobiDatabaseContract.Hotel.COLUMN_TITLE));
		address.setText(hotel_recordset.get(MobiDatabaseContract.Hotel.COLUMN_ADRESS));
		star.setText(hotel_recordset.get(MobiDatabaseContract.Hotel.COLUMN_STAR));
		
		id_h.setText(hotel_recordset.get(MobiDatabaseContract.Hotel.COLUMN_POIID));
		
		imageLoader.DisplayImage(hotel_recordset.get
				(MobiDatabaseContract.Hotel.COLUMN_PICTUREURL), thumb_image);
		
		return vi;
		
	}
	
}
