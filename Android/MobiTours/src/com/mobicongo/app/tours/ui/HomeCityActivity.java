package com.mobicongo.app.tours.ui;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.adapter.CustomTabListener;
import com.mobicongo.app.tours.fragments.CityDescFragment;
import com.mobicongo.app.tours.fragments.CityToursFragment;

public class HomeCityActivity extends SherlockFragmentActivity{
	
	public String namecity;
	public int idcity=0;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Getting an instance of action bar
        ActionBar actionBar = getSupportActionBar();
        
        // Enabling Tab Navigation mode for this action bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        // Enabling Title
        actionBar.setDisplayShowTitleEnabled(true);
                      
        // Creating Android Tab
        Tab tab1 = actionBar.newTab()
        					.setText("CityPedia")
        					.setTabListener(new CustomTabListener<CityDescFragment>(this, "citypedia", CityDescFragment.class) )
        					.setIcon(R.drawable.ic_eat);
        
        // Adding Android Tab to action bar
        actionBar.addTab(tab1);
        
        // Creating Apple Tab
        Tab tab2 = actionBar.newTab()
				.setText("CityTours")
				.setTabListener(new CustomTabListener<CityToursFragment>(this, "citytours", CityToursFragment.class))
				.setIcon(R.drawable.ic_wildlife);
        
        // Adding Apple Tab to action bar
        actionBar.addTab(tab2);        
        
        // Orientation Change Occurred
        if(savedInstanceState!=null){
        	int currentTabIndex = savedInstanceState.getInt("tab_index");
        	actionBar.setSelectedNavigationItem(currentTabIndex);
        }
        
    }
    	
	public int getResults(){
		 idcity=this.getIntent().getIntExtra("Cid", 0);
	   //  namecity=this.getIntent().getStringExtra("CName");
		 return idcity;
	}
	
	
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	int currentTabIndex = getSupportActionBar().getSelectedNavigationIndex();
    	outState.putInt("tab_index", currentTabIndex);
    	super.onSaveInstanceState(outState);
    }
	
	
    
    public void onButtonClicker(View v){
		switch(v.getId()){
		case(R.id.btn_resto):
			Toast.makeText(getApplicationContext(), "Restaurant", Toast.LENGTH_LONG)
			.show();
			break;
		case(R.id.btn_hotel):
			Toast.makeText(getApplicationContext(), "Hotel", Toast.LENGTH_LONG)
			.show();
			break;
		case(R.id.btn_nature):
			Toast.makeText(getApplicationContext(), "Nature", Toast.LENGTH_LONG)
			.show();
			break;
		case(R.id.btn_culture):
			Toast.makeText(getApplicationContext(), "Culture", Toast.LENGTH_LONG)
			.show();
			break;
		default:
			break;
		}
	}
    
}
