package com.mobicongo.app.tours.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.adapter.SubCityToursAdapter;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.ui.CityParentActivity;
import com.mobicongo.app.tours.ui.CityWebViewActivity;
import com.mobicongo.app.tours.ui.HotelListOrMapActivity;


public class CityToursFragment extends SherlockFragment implements OnClickListener{

	TextView tours;
	GridView gridview;
	public int idc=0;
	MobiToursBD db;
	
	Button btresto,bthotel,btnature,btculture;
	
	static final String[] subcitytoursvalues=new String[]{
		"Bank","Hospital","ATM","Airport","Shopping","Gym",
		"Pharmacy","Travel Agency","Books store","Petrol Station","Church"
	};
	
		
	public View onCreateView(LayoutInflater inflater,ViewGroup container,
			Bundle savedInstanceState){
				
		View vi=inflater.inflate(R.layout.citytours,container,false);
		
		db=new MobiToursBD(getActivity());
		db.open();
				
		tours=(TextView)vi.findViewById(R.id.txttours);
		btresto=(Button)vi.findViewById(R.id.btn_resto);
		bthotel=(Button)vi.findViewById(R.id.btn_hotel);
		btnature=(Button)vi.findViewById(R.id.btn_nature);
		btculture=(Button)vi.findViewById(R.id.btn_culture);
	
		btresto.setOnClickListener(this);
		bthotel.setOnClickListener(this);
		btnature.setOnClickListener(this);
		btculture.setOnClickListener(this);
		
		/*idc=((HomeCityActivity)getActivity()).getResults();*/
		idc=((CityParentActivity)getSherlockActivity()).getResultFromIntent();
		Cursor cr=db.returnOneCity(idc);
		tours.setText("City of " + cr.getString(cr.getColumnIndexOrThrow("nameCity")));
		
		/*Test the city id value
		if(idc==1){
			tours.setText("City of Goma");
		}*/
		
		gridview=(GridView)vi.findViewById(R.id.subgridview1);
		
		/*set the adapter and the onItemClickListener to the define GridView View*/
		gridview.setAdapter(new SubCityToursAdapter(getSherlockActivity(),subcitytoursvalues));
		
		gridview.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
				/*
				String selectedvalue=((TextView)v.findViewById(R.id.grid_item_label)).getText().toString();
				check the selected value and do a given action
				if(selectedvalue.equals("Bank")){
					
				}else if(selectedvalue.equals("Hospital")){
					
				}else if(selectedvalue.equals("ATM")){
					
				}else if(selectedvalue.equals("Airport")){
					
				}else if(selectedvalue.equals("Shopping")){
					
				}else if(selectedvalue.equals("Gym")){
					
				}else if(selectedvalue.equals("Pharmacy")){
					
				}else if(selectedvalue.equals("Travel Agency")){
					
				}else if(selectedvalue.equals("Books store")){
					
				}else if(selectedvalue.equals("Petrol Station")){
					
				}
				*/
				
				Toast.makeText(getActivity(),
						((TextView)v.findViewById(R.id.grid_item_label)).getText(), 
						Toast.LENGTH_LONG)
						.show();
			}
			
		});
		
		
		//not need the database so close it
		db.close();
		//return the view
		return vi;
		
	}


	@Override
	public void onClick(View v) {
		
		if(v.getId()==R.id.btn_hotel){
			Intent i=new Intent(getSherlockActivity(),HotelListOrMapActivity.class);
			i.putExtra("id", idc);
			startActivity(i);
		}
		else if(v.getId()==R.id.btn_resto){
			Toast.makeText(getSherlockActivity(), "Restaurant", Toast.LENGTH_SHORT).show();
		}
		else if(v.getId()==R.id.btn_nature){
			Toast.makeText(getSherlockActivity(), "Nature", Toast.LENGTH_SHORT).show();
		}
		else if(v.getId()==R.id.btn_culture){
			Toast.makeText(getSherlockActivity(), "Culture", Toast.LENGTH_SHORT).show();
		}
		
	}
	

	
}
