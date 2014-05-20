/**
 * 
 */
package com.mobicongo.app.tours.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusClient.OnAccessRevokedListener;
import com.google.android.gms.plus.model.people.Person;
import com.google.gson.Gson;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.model.User;


/**
 * @author Mishka
 *
 */
public class LoginFragment extends SherlockFragment implements
		ConnectionCallbacks, OnConnectionFailedListener,
		OnAccessRevokedListener, OnClickListener,
		PlusClient.OnPersonLoadedListener {

	private static final String Save_user_url="";
	private static final String TAG_LOGIN="Login Fragment";
	private static final int REQUEST_CODE_RESOLVE_ERR = 9000;
	
	PlusClient mPlusClient;
	private ConnectionResult mConnectionResult;
	private ProgressDialog mConnectionProgressDialog;
	private SignInButton mPlusOneButton;
	
	private User user;
	OnSignedInListener mSignedCallback;
	
	//Container Activity must implements this interface
	public interface OnSignedInListener{
		public void onSignedIn(User user,ProgressDialog p);
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		View rootView=inflater.inflate(R.layout.fragment_login, container,false);
		getActivity().setTitle(getString(R.string.login));
		mPlusOneButton=(SignInButton)rootView.findViewById(R.id.sign_in_button);
		
		mPlusOneButton.setOnClickListener(this);
		
		return rootView;
	}
	
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getSherlockActivity().getSupportActionBar().setNavigationMode(
				ActionBar.NAVIGATION_MODE_STANDARD);
		
		mPlusClient=new PlusClient.Builder(this.getActivity()
				.getApplicationContext(), this, this)
				.setVisibleActivities("http://schemas.google.com/AddActivity",
						"http://schemas.google.com/BuyActivity")
				.setScopes(Scopes.PLUS_LOGIN, Scopes.PLUS_PROFILE).build();
		
		mConnectionProgressDialog=new ProgressDialog(this.getActivity());
		mConnectionProgressDialog.setTitle("Login");
		
		mConnectionProgressDialog.setMessage(getSherlockActivity().
							getString(R.string.google_login_));
		
	}


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try{
			mSignedCallback=(OnSignedInListener) activity;
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString()
					+ " must implement OnSignedInLister");
		}
	}

@Override
public void onDestroy(){
	super.onDestroy();
	if(mPlusClient.isConnected())
		mPlusClient.disconnect();
}

@Override
public void onDetach(){
	super.onDetach();
	if(mPlusClient.isConnected())
		mPlusClient.disconnect();
}

@Override
public void onAccessRevoked(ConnectionResult res) {
	Log.e(TAG_LOGIN, res.toString() + "");

	Toast.makeText(this.getActivity(), "Access revoked!", Toast.LENGTH_LONG)
			.show();
}

@Override
public void onConnectionFailed(ConnectionResult res) {
	if (mConnectionProgressDialog.isShowing()) {

		if (res.hasResolution()) {
			try {
				res.startResolutionForResult(this.getActivity(),
						REQUEST_CODE_RESOLVE_ERR);
			} catch (SendIntentException e) {
				mPlusClient.connect();
			}
		}
	}
	// Save the intent so that we can start an activity when the user clicks
	// the sign-in button.
	mConnectionResult = res;
}
	

@Override
public void onActivityResult(int requestCode, int responseCode,
		Intent intent) {
	if (requestCode == REQUEST_CODE_RESOLVE_ERR
			&& responseCode == Activity.RESULT_OK) {
		mConnectionResult = null;
		mPlusClient.connect();
	}
}

//@Override
public void onConnected(Bundle connectionHint) {
	/*if (mConnectionProgressDialog.isShowing()) {
		mConnectionProgressDialog.dismiss();
	}*/

	String accountName = mPlusClient.getAccountName();
	mPlusClient.loadPerson(this, "me");
	Log.d(TAG_LOGIN, "Display Name: " + accountName);
	Toast.makeText(this.getActivity(), "Connected:" + accountName,
			Toast.LENGTH_LONG).show();

}

@Override
public void onDisconnected() {

}



@Override
	public void onPersonLoaded(ConnectionResult status, Person person) {
	if (status.getErrorCode() == ConnectionResult.SUCCESS) {

		user = new User();
		String fullName = person.getDisplayName();
		user.setFirstname(fullName.substring(0, fullName.indexOf(' ')));
		user.setLastname(fullName.substring(fullName.indexOf(' ')));
		user.setEmail(mPlusClient.getAccountName());
		user.setBiography(person.getTagline());
		user.setCity((person.getCurrentLocation() == null) ? "N/A" : person
				.getCurrentLocation());
		user.setCountry((person.getCurrentLocation() == null) ? "N/A"
				: person.getCurrentLocation());
		user.setCompany(person.getOrganizations().get(0).getName());
		user.setPhoto(getBestPictureSize(person.getImage().getUrl()));
		user.setWebsite(person.getUrl());
		user.setPassword(getFakePassword());
		user.setPhone("N/A");

		registerUser();

	} else {
		// TODO handle this
	}
		
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.sign_in_button && !mPlusClient.isConnected()) {
			if (mConnectionResult == null) {
				mPlusClient.connect();
				mConnectionProgressDialog.show();
			} else {
				try {
					mConnectionResult.startResolutionForResult(
							this.getActivity(), REQUEST_CODE_RESOLVE_ERR);

				} catch (SendIntentException e) {
					// Try connecting again.
					mConnectionResult = null;
					mPlusClient.connect();
				}
			}
		}
		
	}

	/**
	 * G+ return pic size of 50, we need a bigger size, say 200 Url are like :
	 * https://lh6.googleusercontent.com/-MgVQQ8F_Buk/AAAAAAAAAAI/AAAAAAAACSQ/8
	 * mfy3fB3xcs/photo.jpg?sz=50
	 * 
	 * @param url
	 * @return
	 */
	private String getBestPictureSize(String url) {
		String url_param = url.substring(0, url.indexOf('=') + 1);
		String best = url_param + "200";
		return best;
	}

	/**
	 * The backend need a not null password
	 * 
	 * @return
	 */
	private String getFakePassword() {
		return "vfdvbdpfjvjvperj5455vre";
	}

	
	private void registerUser() {
		Activity activity = getActivity();
		

		// Here we are going to place our REST call parameters.
		Bundle params = new Bundle();
		String payload = new Gson().toJson(user, User.class);
		
	}
	
	/*@Override
	public void onRESTResult(int code, Bundle resultData) {
		Activity activity = getActivity();
		// Here is where we handle our REST response.
		// Check to see if we got an HTTP 200 code and have some data.
		String result = resultData.getString(RESTService.REST_RESULT);
		if (code == 200 && result != null) {

			mSignedCallback.onSignedIn(user,mConnectionProgressDialog);

			Log.d(TAG_LOGIN, result);
			Toast.makeText(activity, "Successfully Registered !",
					Toast.LENGTH_SHORT).show();
		} else if (code == 409 && result != null) {
			mSignedCallback.onSignedIn(user,mConnectionProgressDialog);
			Toast.makeText(activity, "Welcome back, " + user.getFirstname(),
					Toast.LENGTH_SHORT).show();
		} else

		if (activity != null) {
			Log.d(TAG_LOGIN, result);
			Toast.makeText(activity, "Failed to Register.Try again.",
					Toast.LENGTH_SHORT).show();
		}

	}*/

	//@Override
	public void onUserEdited(User p) {
		this.user = p;
		registerUser();
	}
	
	
	@Override
	public void onConnected() {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
