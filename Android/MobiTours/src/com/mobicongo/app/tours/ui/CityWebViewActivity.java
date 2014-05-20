package com.mobicongo.app.tours.ui;

import android.os.Bundle;
import android.webkit.WebView;

import com.actionbarsherlock.app.SherlockActivity;
import com.mobicongo.app.tours.R;

public class CityWebViewActivity extends SherlockActivity{
	
	String cityURL;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mobi_wb_layout);
		
		WebView wb=(WebView)findViewById(R.id.mywebview);
		cityURL=this.getIntent().getStringExtra("ItemURL");
		wb.loadUrl(cityURL);
	}
	
	
	@Override
	public void onBackPressed() {
		WebView wb = (WebView) findViewById(R.id.mywebview);

	    if (wb.canGoBack()) {
	    	wb.goBack();
	        return;
	    }
	    //Handle anything else
	    super.onBackPressed();
	}
	
	

}
