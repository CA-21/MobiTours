package com.mobicongo.app.tours.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobicongo.app.tours.R;

public class SubCityToursAdapter extends BaseAdapter{

	private Context context;
	private final String[] subValues;
	
	public SubCityToursAdapter(Context ctx, String[] sValues){
		this.context=ctx;
		this.subValues=sValues;
	}
	
	@Override
	public int getCount() {
		return subValues.length;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater=(LayoutInflater)context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		View gridView;
		if(convertView==null){
			gridView=new View(context);
			
			//get layout from subcitytours.xml
			gridView=inflater.inflate(R.layout.subcitytours, null);
			//set value into TextView
			TextView textview=(TextView)gridView.findViewById(R.id.grid_item_label);
			textview.setText(subValues[position]);
			
			//set image based on selected text
			ImageView imageview=(ImageView)gridView.findViewById(R.id.grid_item_image);
			
			String thevalues=subValues[position];
			
			if(thevalues.equals("Bank")){
				imageview.setImageResource(R.drawable.bank);
			} else if(thevalues.equals("Hospital")){
				imageview.setImageResource(R.drawable.hospital);
			} else if(thevalues.equals("ATM")){
				imageview.setImageResource(R.drawable.atm);
			}  else if(thevalues.equals("Airport")){
				imageview.setImageResource(R.drawable.airport);
			} else if(thevalues.equals("Shopping")){
				imageview.setImageResource(R.drawable.shopping);
			} else if(thevalues.equals("Gym")){
				imageview.setImageResource(R.drawable.gym);
			} else if(thevalues.equals("Pharmacy")){
				imageview.setImageResource(R.drawable.pharmacy);
			} else if(thevalues.equals("Travel Agency")){
				imageview.setImageResource(R.drawable.travelagency2);
			} else if(thevalues.equals("Books store")){
				imageview.setImageResource(R.drawable.bookstore);
			} else if(thevalues.equals("Petrol Station")){
				imageview.setImageResource(R.drawable.petrolstation);
			}else if(thevalues.equals("Church")){
			imageview.setImageResource(R.drawable.petrolstation);
		}
			
		} else {
			gridView=(View)convertView;
		}
		return gridView;
	}

}
