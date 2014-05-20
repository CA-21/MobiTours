/**
 * 
 */
package com.mobicongo.app.tours.ui;

import org.json.JSONArray;
import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.adapter.CustomTabListener;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.fragments.CommentDialogFragment;
import com.mobicongo.app.tours.fragments.HotelIntroFragment;
import com.mobicongo.app.tours.fragments.HotelMapFragment;
import com.mobicongo.app.tours.fragments.HotelOverviewFragment;
import com.mobicongo.app.tours.fragments.RatingDialogFragment;
import com.mobicongo.app.tours.json.JSONPars;
import com.mobicongo.app.tours.utils.MyConstants;

/**
 * @author Mishka
 *
 */


public class HotelParentActivity extends SherlockFragmentActivity {

	public int idhotel;
	public String typepoi;
	public MobiToursBD db;
	public ActionBar actionBar;
	public Tab tab2;
	public String comm;
	JSONPars jParser=new JSONPars();
	JSONArray cArray;
	JSONArray rArray;
	public static String url_to_parse_comments=MyConstants.url_onInsert_new_comment;
	public static String url_to_parse_rates=MyConstants.url_onInsert_new_rate;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//setContentView();
		actionBar=getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setDisplayUseLogoEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
		db=new MobiToursBD(getApplicationContext());
		db.open();
		
		getResultFromIntent();
		typepoi="hotel";
		
		Tab tab1=actionBar.newTab()
				.setText(" Details")
				.setTabListener(new CustomTabListener<HotelIntroFragment>(this,"Details",HotelIntroFragment.class))
				.setIcon(R.drawable.ic_wildlife);
		
		actionBar.addTab(tab1);
		
		tab2=actionBar.newTab()
				.setText(" Map")
				.setTabListener(new CustomTabListener<HotelMapFragment>(this,"Map",HotelMapFragment.class))
				.setIcon(R.drawable.ic_action_map);
		
		actionBar.addTab(tab2);
		
		Tab tab3=actionBar.newTab()
				.setText(" Overview")
				.setTabListener(new CustomTabListener<HotelOverviewFragment>(this,"Overview",HotelOverviewFragment.class))
				.setIcon(R.drawable.ic_action_list);
		
		actionBar.addTab(tab3);
		
		
		// Orientation Change Occurred
        if(savedInstanceState!=null){
        	int currentTabIndex = savedInstanceState.getInt("tab_index");
        	actionBar.setSelectedNavigationItem(currentTabIndex);
        }
        
   }
    	
	
        
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	int currentTabIndex = getSupportActionBar().getSelectedNavigationIndex();
    	outState.putInt("tab_index", currentTabIndex);
    	super.onSaveInstanceState(outState);
    }
	
	
  //Get the city ID from the Intent
  	public int getResultFromIntent(){
  		idhotel=this.getIntent().getIntExtra("id", 0);
  		return idhotel;
  	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater=getSupportMenuInflater();
		inflater.inflate(R.menu.map_poi_menu, menu);
		return true;
	}
	
		
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	      case R.id.menu_actions:
	         launchMapActionsActivity();
	         return true;
	      case R.id.menu_about:
	         // do s.th.
	         return true;
	      case R.id.menu_comment:
		         // do s.th.
		    	 //showCommentDialog();
	    	  Bundle zargs=new Bundle();
	    	  zargs.putInt("idhotel", idhotel);
	    	  zargs.putString("url", url_to_parse_comments);
	    	  CommentDialogFragment cdf=new CommentDialogFragment();
	    	  cdf.setArguments(zargs);
	    	  cdf.show(getSupportFragmentManager(), "Dialog Comment");
		         return true;
	      case R.id.menu_rating:
		         /*RatingDialog rd=new RatingDialog();
		         rd.show(getSupportFragmentManager(), "Rate dialog");*/
	    	  Bundle args=new Bundle();
	    	  args.putInt("idhotel", idhotel);
	    	  args.putString("url", url_to_parse_rates);
	    	  RatingDialogFragment rdf=new RatingDialogFragment();
	    	  rdf.setArguments(args);
	    	  rdf.show(getSupportFragmentManager(), "Dialog Email");
		         return true;
	      case android.R.id.home:
		      onBackPressed();
		      return true;
	      default:
	         return super.onOptionsItemSelected(item);
	   }
	}


	/*private void showCommentDialog() {
		FragmentManager fm=getSupportFragmentManager();
		CommentDialogFragment commentDialog=new CommentDialogFragment();
		commentDialog.show(fm, "User Comments");
	}*/


	//Launch new activity by passing it the POI's id
	private void launchMapActionsActivity() {
		
		Intent i=new Intent(HotelParentActivity.this,AdvancedMapActionActivity.class);
		i.putExtra("id", idhotel);
		i.putExtra("type_poi", typepoi);
		startActivity(i);
		
	}


	/*@Override
	public void onFinishCommentDialog(String inputText) {
		
		Toast.makeText(this, "the comment is : "+ inputText, Toast.LENGTH_LONG).show();
		comm=inputText;	
		new InsertCommentOnDb().execute();
	}
*/
	

}
