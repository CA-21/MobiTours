/**
 * 
 */
package com.mobicongo.app.tours.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.fragments.ListHotelsFragment;
import com.mobicongo.app.tours.fragments.MapHotelsFragment;
import com.mobicongo.app.tours.fragments.TestListHotelsFragment;

/**
 * @author Mishka (misamuna@gmail.com)
 *
 */

public class HotelListOrMapActivity extends SherlockFragmentActivity implements OnClickListener {

	ImageButton img_bt_list;
	ImageButton img_bt_map;
	public int townid=0;
	public MobiToursBD bd;
	
	FragmentManager fm;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hotel_home_layout);
		
		ActionBar actionBar=getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		
		img_bt_list=(ImageButton)findViewById(R.id.imgbtlist);
		img_bt_map=(ImageButton)findViewById(R.id.imgbtmap);
		
		getResultFromIntent();
		bd=new MobiToursBD(getApplicationContext());
		bd.open();
		
		img_bt_list.setOnClickListener(this);
		img_bt_map.setOnClickListener(this);
			
		fm=getSupportFragmentManager();
		
		//Load the default fragment
		TestListHotelsFragment defaultList=new TestListHotelsFragment();
		//ListHotelsFragment defaultList=new ListHotelsFragment();
		FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
		ft.add(R.id.fraglisthotel, defaultList);
		ft.commit();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.imgbtlist){
			FragmentTransaction ft=fm.beginTransaction();
			ft.setCustomAnimations(R.anim.slide_up_right, R.anim.slide_up_left);

			TestListHotelsFragment lhf=new TestListHotelsFragment();
			//ListHotelsFragment lhf=new ListHotelsFragment();
			ft.replace(R.id.fraglisthotel, lhf);
			ft.addToBackStack(null);
			ft.commit();
		}
		else if(v.getId()==R.id.imgbtmap){
			FragmentTransaction ft=fm.beginTransaction();
			ft.setCustomAnimations(R.anim.slide_up_right, R.anim.slide_up_left);

			MapHotelsFragment lmf=new MapHotelsFragment();
			ft.replace(R.id.fraglisthotel, lmf);
			ft.addToBackStack(null);
			ft.commit();
		}
		
	}
	
	
	//Get the city ID from the Intent
		public int getResultFromIntent(){
			townid=this.getIntent().getIntExtra("id", 0);
			return townid;
		}
	
}
