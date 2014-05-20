package com.mobicongo.app.tours.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.json.JSONParser;
import com.mobicongo.app.tours.utils.MyConstants;

public class TestLoadImageFromMySql extends SherlockFragmentActivity{

	JSONParser jParser;
	JSONArray jArray=null;
	String idpoi=null;
	
	TextView tv1;
	TextView tv2;
	ImageView imv;
	
	private static final String myurl="http://192.168.56.1:8080/mobitours/ws/get_image_by_poi_in_db.php";
	
	private static final String TAG="image";
	private static final String column_poiid="pointodinterestid";
	private static final String column_name="name";
	private static final String column_image="image";
	private static final String tag_image="image";
	
	ArrayList<HashMap<String,String>> imgdata_array;
	
	@Override
	protected void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.testloadimage);
		
		imgdata_array=new ArrayList<HashMap<String,String>>();
		
		 tv1=(TextView)findViewById(R.id.test_poi);
		 tv2=(TextView)findViewById(R.id.test_nom);
		 imv=(ImageView)findViewById(R.id.test_image);
		
		new loadImageFromMySql().execute();
		
	}
	
	public class loadImageFromMySql extends AsyncTask<String,String,String>{

		@Override
		protected String doInBackground(String... args) {
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("pointofinterestid", "1001"));
			
			JSONObject jObj=JSONParser.makeHttpRequest(myurl,"GET", params);
			
			try{
				int success=jObj.getInt(MyConstants.TAG_SUCCESS);
				if(success==1){
					jArray=jObj.getJSONArray(tag_image);
					for(int i=0;i<jArray.length();i++){
						JSONObject jo=jArray.getJSONObject(i);
						int poiid=jo.getInt(column_poiid);
						String name=jo.getString(column_name);
						String limage=jo.getString(column_image);
						
					/*put values in views*/
						tv1.setText(Integer.toString(poiid));
						tv2.setText(name);
												
						/*Decode the image*/
			             byte[] rawImage = Base64.decode(limage, Base64.DEFAULT);
                         Bitmap bmp = BitmapFactory.decodeByteArray(rawImage, 0, rawImage.length); 
                         imv.setImageBitmap(bmp);
					}
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			return null;
		}
		
		
	}

	
	
	
}
