/**
 * 
 */
package com.mobicongo.app.tours;

import android.app.Application;
import android.content.res.Configuration;

/**
 * @author Mishka
 *
 */
public class MobitoursApplication  extends Application{

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	
	
}
