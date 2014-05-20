package com.mobicongo.app.tours.adapter;

import java.util.ArrayList;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.model.City;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CityAdapter extends BaseAdapter{
	
	private ArrayList<City> myCities;
	private Integer i;
	LayoutInflater inflater;
	
/*	String cachePath;*/
	
	
	public CityAdapter(Context context,ArrayList<City> myCitie){
		inflater=LayoutInflater.from(context);
		myCities=myCitie;
	}
	

	@Override
	public int getCount() {
		return myCities.size();
	}

	@Override
	public Object getItem(int position) {
		return myCities.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
/*	public void setCities(List<City> items){
		this.items=items;
	}*/
	
	private class CityHolder{
		TextView cityname;
		TextView cityDesc;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	
		CityHolder city;
		
		if(convertView==null){
			city=new CityHolder();
			convertView=inflater.inflate(R.layout.city_item,null);
			
			city.cityname=(TextView)convertView.findViewById(R.id.cityName);
			city.cityDesc=(TextView)convertView.findViewById(R.id.citydesc);

			convertView.setTag(city);
		}else{
			city=(CityHolder)convertView.getTag();
		}
		
		city.cityname.setText(myCities.get(position).getCityname());
		city.cityDesc.setText(myCities.get(position).getCityDesc());

		return convertView;
	}

}
