package com.mobicongo.app.tours.fragments;

import java.util.ArrayList;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.adapter.MobiHomeListAdapter;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.model.HomeItem;
import com.mobicongo.app.tours.ui.CityParentActivity;
import com.mobicongo.app.tours.utils.ImageLoader;

public class CityDescFragment extends SherlockFragment{

	ImageView cflipper;
	ListView lv1;
	ArrayList<HomeItem> image_item=GetResults();
	public int idc=0;
	String url_img_city="";
	ImageLoader imageLoader;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		idc=((CityParentActivity)getSherlockActivity()).getResultFromIntent();	
	}


	public View onCreateView(LayoutInflater inflater,ViewGroup container,
			Bundle savedInstanceState){
		setRetainInstance(true);
		View view=inflater.inflate(R.layout.citydesc,container,false);
		//ImageView imgv = (ImageView) view.findViewById(R.id.image_box);
		
		cflipper=(ImageView)view.findViewById(R.id.cityflipper);
		lv1=(ListView)view.findViewById(R.id.citylistv);
			
		Cursor cr=((CityParentActivity)getSherlockActivity()).db.returnOneCity(idc);
		url_img_city=cr.getString(cr.getColumnIndexOrThrow("cityurlimage"));
		
		imageLoader=new ImageLoader(getSherlockActivity());
		imageLoader.DisplayImage(url_img_city, cflipper);
				
		lv1.setAdapter(new MobiHomeListAdapter(getActivity(),image_item));
		
		//Define setOnItemClickListener ==> for the list
		lv1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Object o=lv1.getItemAtPosition(position);
				HomeItem obj_item=(HomeItem)o;
				String val=obj_item.getTitle().toString();
				Toast.makeText(getActivity(), "Vous avez choisi : " + " " + obj_item.getTitle(), Toast.LENGTH_LONG)
				.show();
				
				if(val=="CityPedia"){
					//something here
				}
				
				else if(val=="Informations Pratiques"){
					//startActivity(new Intent(getActivity(),DrcMapActivity.class));
				}
			}
			
		});
		
		
		return view;
		
	}

			
	private ArrayList<HomeItem> GetResults(){
		
		ArrayList<HomeItem> result=new ArrayList<HomeItem>();
		
		HomeItem hi=new HomeItem();
		// First value : CongoPedia
		hi.setImageNumber(1);
		hi.setTitle("CityPedia");
		hi.setDescription("Give an overview of the city");
		result.add(hi);
		
		hi=new HomeItem();
		hi.setImageNumber(2);
		hi.setTitle("Informations Pratiques");
		hi.setDescription("Find all extra informations about the city");
		result.add(hi);
				
		return result;
	}
	
	

	
	
}
