package com.mobicongo.app.tours.fragments;

import java.util.ArrayList;
import android.os.Bundle;
import android.telephony.SmsManager;
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

public class SmsDialogFragment extends SherlockDialogFragment {

	private EditText phoneNumber, messageText;
	private Button btsend,btcancel ;
	public String numero;
	
	private String mPhoneNumber;

	
	public SmsDialogFragment(){
		//Empty constructor required for the dialogfragment
	}

	public interface SmsDialogFragmentListener{
		void onFinishCommentDialog(String inputText);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view=inflater.inflate(R.layout.sendsms_activity, container);
		
		phoneNumber=(EditText)view.findViewById(R.id.phoneNumber);
		messageText=(EditText)view.findViewById(R.id.messageText);
		btsend=(Button)view.findViewById(R.id.ButtonSend);
		btcancel=(Button)view.findViewById(R.id.ButtonCancel);
		
		getDialog().setTitle("Send sms to broker");
		
		phoneNumber.setText(getResultFromIntent());
		//show automatically the keyboard
		messageText.requestFocus();
		getDialog().getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
		btsend.setOnClickListener(new OnClickListener(){

			public void onClick(View view) {			  			  
	  			  if(phoneNumber.getText().toString().trim().length() == 0) { 
	  				  Toast.makeText(getSherlockActivity(), "Please enter a Phone Number.", Toast.LENGTH_LONG).show();
	  				  return;
	  			  }
	  			  
	  			  if(messageText.getText().toString().trim().length() == 0) { 
	  				  Toast.makeText(getSherlockActivity(), "Please enter your message.", Toast.LENGTH_LONG).show();
	  				  return;
	  			  }
	  			  
	  			  if(messageText.getText().toString().trim().length() > 160) {
	  				  sendLongSMS()	;		  
	  			  }
	  			  else {
	  				  sendSMS();
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
  	public String getResultFromIntent(){
  		Bundle mArgs=getArguments();
  		mPhoneNumber=mArgs.getString("numero");
  		return mPhoneNumber;
  	}

  	public void sendSMS() {

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNumber.getText().toString(), null, messageText.getText().toString(), null, null);
        
        Toast.makeText(getSherlockActivity(), "Message Sent!", Toast.LENGTH_LONG).show();
    }
    
    public void sendLongSMS() {    	 

        SmsManager smsManager = SmsManager.getDefault();
        ArrayList<String> parts = smsManager.divideMessage(messageText.getText().toString()); 
        smsManager.sendMultipartTextMessage(phoneNumber.getText().toString(), null, parts, null, null);
        
        Toast.makeText(getSherlockActivity(), "Message Sent!", Toast.LENGTH_LONG).show();
    }
	
}
