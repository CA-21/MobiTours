/**
 * 
 */
package com.mobicongo.app.tours.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.actionbarsherlock.app.SherlockFragment;
import com.mobicongo.app.tours.R;

/**
 * @author Mishka
 *
 */


public class HotelOverviewFragment extends SherlockFragment{

	
	
	public HotelOverviewFragment(){
		//null constructor
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		
		View vi=inflater.inflate(R.layout.hotel_overview_fragment, container, false);
		
		
		
		return vi;
	}

	
	
	
	
}
