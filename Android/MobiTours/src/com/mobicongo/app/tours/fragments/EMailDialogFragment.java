package com.mobicongo.app.tours.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockDialogFragment;
import com.mobicongo.app.tours.R;

public class EMailDialogFragment extends SherlockDialogFragment {

	private EditText address;
	private EditText subject;
	private EditText body;
	private Button btsend;
	
	private String mAddress;
	private String mSubject;
	
	public EMailDialogFragment(){
		//Empty constructor required for the dialogfragment
	}

	public interface CommentDialogFragmentListener{
		void onFinishCommentDialog(String inputText);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view=inflater.inflate(R.layout.emaildialog, container);
		
		address=(EditText)view.findViewById(R.id.address);
		subject=(EditText)view.findViewById(R.id.subject);
		body=(EditText)view.findViewById(R.id.body);
		btsend=(Button)view.findViewById(R.id.send);
		
		getDialog().setTitle("Send Email to broker");
		
		getStringArguments();
		address.setText(mAddress);
		subject.setText(mSubject);
		//show automatically the keyboard
		body.requestFocus();
		getDialog().getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);
		
		btsend.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				sendEmail();
				getDialog().dismiss();
			}
			
		});
		
		
		return view;
	}
	
	public void getStringArguments(){
		Bundle mArgs=getArguments();
		mAddress=mArgs.getString("email");
		mSubject=mArgs.getString("sujet");
	}

	public void sendEmail(){
		 
        if(!address.getText().toString().trim().equalsIgnoreCase("")){
          final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
          emailIntent.setType("plain/text");
          emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ address.getText().toString()});
          emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject.getText().toString());
          emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body.getText());
          EMailDialogFragment.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
        else{
            Toast.makeText(getSherlockActivity(), "Please enter an email address..", Toast.LENGTH_LONG).show();
        }
      }	
}
