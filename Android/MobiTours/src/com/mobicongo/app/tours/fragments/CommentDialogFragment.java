package com.mobicongo.app.tours.fragments;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.json.JSONPars;
import com.mobicongo.app.tours.ui.HotelParentActivity;
import com.mobicongo.app.tours.utils.MyConstants;

public class CommentDialogFragment extends SherlockDialogFragment {

	private EditText messageComment;
	private Button btsave,btcancel ;
	private int idsite;
	private int rate;
	private int iduser;
	private Button btrate;
	private String mUrl;
	private int mIdhotel;
	JSONPars jParser=new JSONPars();
	
	private String mPhoneNumber;

	
	public CommentDialogFragment(){
		//Empty constructor required for the dialogfragment
	}

	public interface CommentDialogFragmentListener{
		void onFinishCommentDialog(String inputText);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view=inflater.inflate(R.layout.comment_dialog, container);
		
		messageComment=(EditText)view.findViewById(R.id.messageComment);
		btsave=(Button)view.findViewById(R.id.btSaveComment);
		btcancel=(Button)view.findViewById(R.id.btCancelComment);
		
		getDialog().setTitle("Comment the hotel");
		
		getHotelArguments();
		idsite=0;
		iduser=0;
		//show automatically the keyboard
		messageComment.requestFocus();
		getDialog().getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
		btsave.setOnClickListener(new OnClickListener(){

			public void onClick(View view) {			  			  
	  			  if(messageComment.getText().toString().trim().length() == 0) { 
	  				  Toast.makeText(getSherlockActivity(), "Please enter a comment", Toast.LENGTH_LONG).show();
	  				  return;
	  			  }
	  			  else {
	  				  new InsertCommentOnDb().execute();
	  				  getDialog().dismiss();
	  			  }
	  			  
	  		  }
	        });
		
		btcancel.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				getDialog().dismiss();
			}
		});
		
		getDialog().dismiss();
		return view;
	}
	
		
	 //Get ID from the Intent
	public void getHotelArguments(){
		Bundle mArgs=getArguments();
		mIdhotel=mArgs.getInt("idhotel");
		mUrl=mArgs.getString("url");
	}


	//Asynctask for inserting in comment table
		public class InsertCommentOnDb extends AsyncTask<String,String,String>{

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected String doInBackground(String... args) {
				
				String hotelID=String.valueOf(mIdhotel);
			
				//Building parameters
				List<NameValuePair> params=new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("text",messageComment.getText().toString()));
				params.add(new BasicNameValuePair("pointofinterestid",hotelID));
				
				JSONObject json=jParser.makeHttpRequest(mUrl, "POST", params);
				Log.d("Insert new comments",json.toString());
				
				try{
					int success=json.getInt(MyConstants.TAG_SUCCESS);
					if(success==1){
						Log.d("AsyncTask_Comment","Inserting comment success");
					}
					/*else{
						//in case it failed
						Log.d("AsyncTask_Comment","Error Inserting Comment");
					}*/
				}catch(JSONException e){
					e.printStackTrace();
				}
				
				return null;
			}
			
			@Override
			protected void onPostExecute(String file_url) {
				//
			}

		}
		

  	
	
}
