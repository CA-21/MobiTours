package com.mobicongo.app.tours.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.json.JSONPars;
import com.mobicongo.app.tours.utils.MyConstants;

public class RatingDialogFragment extends SherlockDialogFragment {

	private int idsite;
	private int rate;
	private int iduser;
	private Button btrate;
	private Button btcancel;
	
	private RadioButton radio_button1;
	private RadioButton radio_button2;
	private RadioButton radio_button3;
	private RadioButton radio_button4;
	private RadioButton radio_button5;
	JSONPars jParser=new JSONPars();
	
	private String mUrl;
	private int mIdhotel;
	
	public RatingDialogFragment(){
		//Empty constructor required for the dialogfragment
	}

	public interface RatingDialogFragmentListener{
		void onFinishRatingDialog(String inputText);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view=inflater.inflate(R.layout.rate_dialog, container);
				
		btrate=(Button)view.findViewById(R.id.rate);
		btcancel=(Button)view.findViewById(R.id.cancelrate);
		radio_button1=(RadioButton)view.findViewById(R.id.radio1);
		radio_button2=(RadioButton)view.findViewById(R.id.radio2);
		radio_button3=(RadioButton)view.findViewById(R.id.radio3);
		radio_button4=(RadioButton)view.findViewById(R.id.radio4);
		radio_button5=(RadioButton)view.findViewById(R.id.radio5);
		
		getDialog().setTitle("Rate the hotel");
		
		getHotelArguments();
		idsite=0;
		iduser=0;

		//show automatically the keyboard
		
		btrate.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(radio_button1.isChecked()){
					rate=1;
				}else if(radio_button2.isChecked()){
					rate=2;
				}
				else if(radio_button3.isChecked()){
					rate=3;
				}
				else if(radio_button4.isChecked()){
					rate=4;
				}
				else if(radio_button5.isChecked()){
					rate=5;
				}
				
				new InsertRateOnDb().execute();
								
				Toast.makeText(getSherlockActivity(), "data : "+ String.valueOf(rate) + "/" +
						String.valueOf(mIdhotel) + "/" + String.valueOf(idsite) + "/" + String.valueOf(iduser), Toast.LENGTH_LONG).show();
				getDialog().dismiss();
			}
			
		});
		
		btcancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				getDialog().dismiss();
			}
		});
		
		return view;
	}
	
	public void getHotelArguments(){
		Bundle mArgs=getArguments();
		mIdhotel=mArgs.getInt("idhotel");
		mUrl=mArgs.getString("url");
	}

	
	public class InsertRateOnDb extends AsyncTask<String,String,String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
		}

		@Override
		protected String doInBackground(String... args) {
			
						
			//Building parameters
			List<NameValuePair> params=new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("rating",String.valueOf(rate)));
			params.add(new BasicNameValuePair("pointofinterestid",String.valueOf(mIdhotel)));
			params.add(new BasicNameValuePair("idsite",String.valueOf(idsite)));
			params.add(new BasicNameValuePair("userid",String.valueOf(iduser)));
			
			JSONObject js=jParser.makeHttpRequest(mUrl, "POST", params);
			Log.d("Insert new rates",js.toString());
			
			try{
				int success=js.getInt(MyConstants.TAG_SUCCESS);
				if(success==1){
					Log.d("AsyncTask_rate","Inserting rate success");
				}else{
					//in case it failed
					Log.d("AsyncTask_Rate","Error Inserting Rates");
				}
			}catch(JSONException e){
				e.printStackTrace();
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(String file_url) {
			//pDialog.dismiss();
		}
		
	}
	
	
	
}
