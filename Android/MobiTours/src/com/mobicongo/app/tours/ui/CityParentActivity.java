/**
 * 
 */
package com.mobicongo.app.tours.ui;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.fragments.CityDescFragment;
import com.mobicongo.app.tours.fragments.CityMapFragment;
import com.mobicongo.app.tours.fragments.CityToursFragment;
import com.mobicongo.app.tours.fragments.LoginFragment;
import com.mobicongo.app.tours.model.User;

/**
 * @author Mishka (misamuna@gmail.com)
 *
 */


public class CityParentActivity extends SherlockFragmentActivity implements
		LoginFragment.OnSignedInListener {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	
	public MobiToursBD db;
	
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mMenuTitles;

	public String namecity;
	public int idcity=0;
	
	private User user;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		
		setContentView(R.layout.city_main_layout);
		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		db=new MobiToursBD(getApplicationContext());
		db.open();
		
		mTitle = mDrawerTitle = getTitle();
		mMenuTitles = getResources().getStringArray(R.array.menu_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		
		mDrawerList.setAdapter(new DrawerListAdapter());
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		
		// ActionBarDrawerToggle ties together the the proper interactions
				// between the sliding drawer and the action bar app icon
				mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
				mDrawerLayout, /* DrawerLayout object */
				R.drawable.ic_navigation_drawer, /*
												 * nav drawer image to replace 'Up'
												 * caret
												 */
				R.string.drawer_open, /* "open drawer" description for accessibility */
				R.string.drawer_close /* "close drawer" description for accessibility */
				) {
					public void onDrawerClosed(View view) {
						getSupportActionBar().setTitle(mTitle);
						supportInvalidateOptionsMenu(); // creates call to
														// onPrepareOptionsMenu()
					}

					public void onDrawerOpened(View drawerView) {
						getSupportActionBar().setTitle(mDrawerTitle);

					}
				};
				mDrawerLayout.setDrawerListener(mDrawerToggle);

				if (savedInstanceState == null) {
					init();
				}
				if (firstLaunch()) {
					selectItem(8);
				}
			
	}

	void init() {
		selectItem(0);
		mDrawerLayout.setVisibility(View.VISIBLE);
		mDrawerLayout.openDrawer(Gravity.LEFT);
	}
	
	private boolean firstLaunch() {
		//return new CategorieProvider(this).getLabels().length == 0;
		return false; // to modify
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}

		}
		return true;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getSupportMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			view.setSelected(true);
			selectItem(position);
		}
	}
	
	@Override
	public void onBackPressed() {

		if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
			mDrawerLayout.closeDrawer(Gravity.LEFT);
		} else {
			super.onBackPressed();
		}

	}
	
	private void selectItem(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;

		switch (position) {
		case 0:
			if (getLoggedInUser() == null) {
				fragment = new LoginFragment();
			} else {
				//fragment = new ProfileFragment();
			}
			break;
		case 1:
			fragment = new CityDescFragment();
			break;
		case 2:
			fragment = new CityToursFragment();
			break;
		case 3:
			fragment = new CityMapFragment();
			break;

		case 4:
			//startActivity(new Intent(MainActivity.this, AboutFragmentActivity.class));
			break;
		case 8:
			//fragment = new SynchronizationFragment();
/*
 * Must add an execution of synchronization script here
 */
			// mDrawerLayout.setVisibility(View.INVISIBLE);
			break;
		default:
			break;
		}

		FragmentManager fragmentManager = getSupportFragmentManager();
		if (fragment == null) {
			return;
		}

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerList.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(mDrawerList);
		
	
	}

		
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}
	
	private User getLoggedInUser() {
		if (user == null) {
			/*userProvider = new UserProvider(this);*/
			List<User> list = null;
			/*if (list != null && !list.isEmpty()) {
				user = list.get(0);
			}*/
	
		}
		return user;
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	
	//Class for the DrawerList
	class DrawerListAdapter extends BaseAdapter {

		private static final int TYPE_CITY = 0;
		private static final int TYPE_ITEM = 1;
		private LayoutInflater mInflater;

		//private Participant user;

		public int getCount() {
			return mMenuTitles.length + 1;
		}

		public DrawerListAdapter() {
			mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public Object getItem(int position) {
			if (position == 0) {
				return 0;
			} else
				return mMenuTitles[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			int type = getItemViewType(position);
			if (convertView == null) {
				holder = new ViewHolder();
				switch (type) {
				case TYPE_ITEM:
					convertView = mInflater.inflate(R.layout.drawer_list_item,
							null);
					holder.textView = (TextView) convertView
							.findViewById(R.id.tv_drawer_item);

					holder.imgView = (ImageView) convertView
							.findViewById(R.id.img_drawer);
					int index = position;
					switch (index) {
					case 1:
						holder.imgView.setImageDrawable(getResources()
								.getDrawable(R.drawable.ic_action_list));
						break;
					case 2:
						holder.imgView.setImageDrawable(getResources()
								.getDrawable(R.drawable.ic_action_user));
						break;
				
					case 3:
						holder.imgView.setImageDrawable(getResources()
								.getDrawable(R.drawable.ic_action_map));
						break;

					case 4:
						holder.imgView.setImageDrawable(getResources()
								.getDrawable(R.drawable.ic_action_help));
						break;
					default:
						break;
					}
					holder.textView.setText(mMenuTitles[index - 1]);

					break;
				case TYPE_CITY:
					convertView = mInflater
							.inflate(R.layout.item_city, null);
					holder.textView = (TextView) convertView
							.findViewById(R.id.tv_username);
					holder.imgView = (ImageView) convertView
							.findViewById(R.id.img_profile);

					idcity=getResultFromIntent();
					Cursor cr=db.returnOneCity(idcity);
					holder.textView.setText(cr.getString(cr.getColumnIndexOrThrow("nameCity")));


					break;
				}
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			return convertView;
		}

		@Override
		public int getItemViewType(int position) {
			return (position == 0) ? TYPE_CITY : TYPE_ITEM;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		public class ViewHolder {
			public TextView textView;
			public ImageView imgView;
		}

	}

	//Get the city ID from the Intent
	public int getResultFromIntent(){
		idcity=this.getIntent().getIntExtra("Cid", 0);
		return idcity;
	}

	@Override
	public void onSignedIn(User user, ProgressDialog p) {
		// TODO Auto-generated method stub
		
	}
	
	/*public void onButtonClicker(View v){
		
	}*/
	
	
//*****************************************************
}
