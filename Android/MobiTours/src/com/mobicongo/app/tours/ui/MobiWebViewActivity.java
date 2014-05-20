package com.mobicongo.app.tours.ui;

import android.os.Bundle;
import android.webkit.WebView;

import com.actionbarsherlock.app.SherlockActivity;
import com.mobicongo.app.tours.R;

public class MobiWebViewActivity extends SherlockActivity{

	
	String myLocalURL;    /*="file:///android_asset/webapp.html";*/
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mobi_wb_layout);
		
		WebView wb=(WebView)findViewById(R.id.mywebview);
		
		myLocalURL=this.getIntent().getStringExtra("ItemURL");
		
		//Turns on JavaScript and loads our local HTML page
		//WebSettings settings = wb.getSettings();
		//settings.setJavaScriptEnabled(true);
		//wb.addJavascriptInterface(new AppJavaScriptInterface(this), "Android");			
		//wb.loadUrl("file:///android_asset/webapp.html");
		
		//Load our local file
		wb.loadUrl(myLocalURL);
	}
	
	
	@Override
	public void onBackPressed() {
		WebView wb = (WebView) findViewById(R.id.mywebview);
		// If there is history in the web view, go back
	    if (wb.canGoBack()) {
	    	wb.goBack();
	        return;
	    }
	    //Handle anything else
	    super.onBackPressed();
	}
	
	
	
}
