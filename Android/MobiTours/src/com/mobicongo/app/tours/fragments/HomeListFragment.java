package com.mobicongo.app.tours.fragments;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.adapter.MobiHomeListAdapter;
import com.mobicongo.app.tours.model.HomeItem;
import com.mobicongo.app.tours.ui.GeneralTourismMapActivity;
import com.mobicongo.app.tours.ui.WikiCongoActivity;
import com.mobicongo.app.tours.ui.ZoneFragmentActivity;


public class HomeListFragment extends SherlockFragment{
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,
			Bundle savedInstanceState){
				
		ArrayList<HomeItem> image_item=GetResults();
		View vi=inflater.inflate(R.layout.home_main,container,false);
		
		final ListView lv1=(ListView)vi.findViewById(R.id.main_listv);
		
		/*You can use getActivity() here to refer to the the Activity where the fragment is attached */
		lv1.setAdapter(new MobiHomeListAdapter(getActivity(),image_item));
		
		//Define setOnItemClickListener ==> for the list
		lv1.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				Object o=lv1.getItemAtPosition(position);
				HomeItem obj_item=(HomeItem)o;
				String val=obj_item.getTitle().toString();
				/*Toast.makeText(getActivity(), "Vous avez choisi : " + " " + obj_item.getTitle(), Toast.LENGTH_LONG)
				.show();*/
				
				if(val=="Destinations"){
					startActivity(new Intent(getSherlockActivity(),ZoneFragmentActivity.class));
				}
				else if(val=="WikiCongo"){
					startActivity(new Intent(getSherlockActivity(),WikiCongoActivity.class));
				}
				else if(val=="Informations Pratiques"){
					//startActivity(new Intent(getSherlockActivity(),AdvancedMapActionActivity.class));
				}
				else if(val=="O.N.T"){
					
				}
				else if(val=="Maps"){
					startActivity(new Intent(getSherlockActivity(),GeneralTourismMapActivity.class));
				}
			}
			
		});
		
		Log.v("HomeListFragment","onCreate");
		return vi;
	}
	

	private ArrayList<HomeItem> GetResults(){
		
		ArrayList<HomeItem> result=new ArrayList<HomeItem>();
		
		HomeItem hi=new HomeItem();
		// First value : CongoPedia
		hi.setImageNumber(1);
		hi.setTitle("WikiCongo");
		hi.setDescription("General Overview of the DRC");
		result.add(hi);
		
		hi=new HomeItem();
		hi.setImageNumber(2);
		hi.setTitle("Destinations");
		hi.setDescription("Possible destinations in the DRC");
		result.add(hi);
		
		hi=new HomeItem();
		hi.setImageNumber(3);
		hi.setTitle("Informations Pratiques");
		hi.setDescription("Various information on destinations on DRC");
		result.add(hi);
		
		hi=new HomeItem();
		hi.setImageNumber(4);
		hi.setTitle("O.N.T");
		hi.setDescription("National Tourism Office");
		result.add(hi);
		
		hi=new HomeItem();
		hi.setImageNumber(5);
		hi.setTitle("Maps");
		hi.setDescription("General Map of DRC");
		result.add(hi);
		
		return result;
	}
	
}
