package com.mobicongo.app.tours.ui;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.adapter.PagePagerAdapter;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.fragments.SynchronizationFragment;
import com.viewpagerindicator.TitlePageIndicator;

public class SyncParentActivity extends SherlockFragmentActivity{

	Fragment synchronize;

	
	PagePagerAdapter mAdapter;
	public MobiToursBD database;
	
	private List<Fragment> fragments;
	
	public SyncParentActivity(){
		super();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_pager_layout);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		getSupportActionBar().setNavigationMode(
				com.actionbarsherlock.app.ActionBar.NAVIGATION_MODE_STANDARD);
		
		database=new MobiToursBD(this);
		database.open();
		
		synchronize=new SynchronizationFragment();
	//	zonegeomap=new ZoneGeoMapFragment();
		
		fragments=new ArrayList<Fragment>();
		fragments.add(synchronize);
		//fragments.add(zonegeomap);
		
		mAdapter=new PagePagerAdapter(this,super.getSupportFragmentManager(),fragments);
		
		ViewPager pager=(ViewPager)findViewById(R.id.viewpager_zone);
		pager.setAdapter(mAdapter);
		
		TitlePageIndicator titleIndicator=(TitlePageIndicator)findViewById(R.id.vpi_zone);
		titleIndicator.setViewPager(pager);
		titleIndicator.setBackgroundColor(Color.WHITE);
		titleIndicator.setFooterColor(getResources().getColor(R.color.body_text_1_negative));
		titleIndicator.setTextColor(getResources().getColor(R.color.body_text_1_negative));
		titleIndicator.setSelectedColor(getResources().getColor(R.color.body_text_1_negative));
		titleIndicator.setSelectedBold(true);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
}
