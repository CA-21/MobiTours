package com.mobicongo.app.tours.adapter;

import com.mobicongo.app.tours.R;
import com.viewpagerindicator.IconPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MobiHomeImageAdapter extends FragmentPagerAdapter implements
	IconPagerAdapter {

	private int[] Images=new int[]{R.drawable.ic_launcher, 
			R.drawable.ic_launcher	
	};
	
	private static final int[] ICONS=new int[]{R.drawable.ic_launcher,
		R.drawable.ic_launcher
	};
	
	private int myCount=Images.length;
	
	public MobiHomeImageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getIconResId(int index) {
		
		return ICONS[index % ICONS.length];
	}

	@Override
	public Fragment getItem(int position) {
		return null;
		/*return new ImageFragment(Images[position]);*/
		
	}

	@Override
	public int getCount() {
		return myCount;
	}
	
	public void setCount(int count){
		if(count>0 && count <=12){
			myCount=count;
			notifyDataSetChanged();
		}
	}

}
