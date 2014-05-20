/**
 * 
 */
package com.mobicongo.app.tours.adapter;

import java.util.List;
import com.mobicongo.app.tours.R;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author Mishka
 *
 */
public class PagePagerAdapter extends FragmentPagerAdapter{

	private final List<Fragment> fragments;
	protected static String[] CONTENT;
	private Context ctx;
	
	public PagePagerAdapter(Context ctx,FragmentManager fm,List<Fragment> fragments) {
		super(fm);
		this.ctx=ctx;
		this.fragments=fragments;
		CONTENT=new String[]{ctx.getResources().getString(R.string.liste),
							 ctx.getResources().getString(R.string.map)
							};	
	}

	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return CONTENT[position];
	}

	
	
}
