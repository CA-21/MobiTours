/**
 * 
 */
package com.mobicongo.app.tours.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiDatabaseContract;
import com.mobicongo.app.tours.ui.HotelParentActivity;
import com.mobicongo.app.tours.utils.ActionItem;
import com.mobicongo.app.tours.utils.ImageLoader;
import com.mobicongo.app.tours.utils.QuickAction;

/**
 * @author Mishka (misamuna@gmail.com)
 *
 */


public class HotelIntroFragment extends SherlockFragment implements OnClickListener {

	ListView lvhotel;
	ListView lvcourtier;
	ImageButton img_bt_map;
	ImageView img_poi;
	TextView tv_title; // tv_star, tv_address, tv_mail, tv_roomdispo;
	int idhotel=0;
	SimpleCursorAdapter hadapter;
	SimpleCursorAdapter coadapter;
	String title;
	QuickAction mQuickAction;
	String num_phone;
	String msg_mail="";
	String sms="";
	String mSubject;
	String adresseMail;
	ImageLoader imageLoader;
	
	public HotelIntroFragment(){
		//null constructor
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		idhotel=((HotelParentActivity)getSherlockActivity()).getResultFromIntent();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		setRetainInstance(true);
		
		View vi=inflater.inflate(R.layout.hotel_details_fragment, container, false);
		
		getActivity().setTitle(R.string.app_name);
		
		lvhotel=(ListView)vi.findViewById(R.id.list_details_hotel);
		lvcourtier=(ListView)vi.findViewById(R.id.list_courtier);
		img_poi=(ImageView)vi.findViewById(R.id.hotel_details_img);
		img_bt_map=(ImageButton)vi.findViewById(R.id.btn_maphotel);
		tv_title=(TextView)vi.findViewById(R.id.hotel_name);
		img_poi=(ImageView)vi.findViewById(R.id.hotel_details_img);
		
		/*tv_star=(TextView)vi.findViewById(R.id.hotel_star1);
		tv_address=(TextView)vi.findViewById(R.id.hotel_adresse1);
		tv_mail=(TextView)vi.findViewById(R.id.hotel_email1);
		tv_roomdispo=(TextView)vi.findViewById(R.id.hotel_roomdispo1);*/
		
		imageLoader=new ImageLoader(getSherlockActivity());
		
		img_bt_map.setOnClickListener(this);
		
		//QuickAction
		implementQuickAction();
		
		/*
		 *  create a cursor to load data for the current hotel to ListView
		 */
		Cursor h=((HotelParentActivity)getSherlockActivity()).db.fetchingOneHotel(idhotel);
		
		//Set the image
		String imgUrl=h.getString(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_PICTUREURL));
		imageLoader.DisplayImage(imgUrl, img_poi);
		
		/*
		 * set the name of the Hotel
		 */
		
		tv_title.setText(h.getString(h.getColumnIndexOrThrow(MobiDatabaseContract.Hotel.COLUMN_TITLE)));
		
		mSubject="Concerne:Reservation chambre a " + tv_title.getText().toString();
		
		String[] colonnes=new String[]{
				MobiDatabaseContract.Hotel.COLUMN_STAR,
				MobiDatabaseContract.Hotel.COLUMN_ADRESS,
				MobiDatabaseContract.Hotel.COLUMN_NBROOM_DISPO,
				MobiDatabaseContract.Hotel.COLUMN_MAIL,
		};
		
		int[] to=new int[]{
			R.id.hotel_star, R.id.hotel_adresse, R.id.hotel_roomdispo, R.id.hotel_email 
		};
		
		hadapter=new SimpleCursorAdapter(vi.getContext(),R.layout.item_detail_hotel,
				h,
				colonnes,
				to,
				0
				);
		lvhotel.setAdapter(hadapter);
				
		/*
		 *  create a cursor to load data from courtier to listview
		 */
		Cursor co=((HotelParentActivity)getSherlockActivity()).db.fetchingAllBrokers();
		
		String[] cols=new String[]{
				MobiDatabaseContract.Courtier.COLUMN_NAME,
				MobiDatabaseContract.Courtier.COLUMN_WEBSITE
		};
		
		int[] to_brok=new int[]{
				R.id.tv_courtier_name, R.id.tv_courtier_website
		};
		
		coadapter=new SimpleCursorAdapter(vi.getContext(),R.layout.item_courtier,
				co,
				cols,
				to_brok,
				0
				);
		
		lvcourtier.setAdapter(coadapter);
		coadapter.notifyDataSetChanged();
		
		//lvcourtier.setOnItemClickListener(this);
		
		lvcourtier.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> list, View v, int position,
					long id) {
				//Implement the quick action here
				Cursor cursor=(Cursor)list.getItemAtPosition(position);
				num_phone=cursor.getString(cursor.getColumnIndexOrThrow(MobiDatabaseContract.Courtier.COLUMN_PHONE));
				adresseMail=cursor.getString(cursor.getColumnIndexOrThrow(MobiDatabaseContract.Courtier.COLUMN_EMAIL));
				Toast.makeText(getSherlockActivity(), num_phone, Toast.LENGTH_SHORT).show();
				mQuickAction.show(v);
				mQuickAction.setAnimStyle(QuickAction.ANIM_GROW_FROM_CENTER);
			}
		});
				
		return vi;
	}

	/*
	 * Implement the quickaction
	 */
	public void implementQuickAction(){
		
		ActionItem mailAction=new ActionItem();
		mailAction.setTitle("e-Mail");
		mailAction.setIcon(getResources().getDrawable(R.drawable.email));
		
		ActionItem callAction=new ActionItem();
		callAction.setTitle("Call");
		callAction.setIcon(getResources().getDrawable(R.drawable.makecall));
		
		ActionItem smsAction=new ActionItem();
		smsAction.setTitle("sms");
		smsAction.setIcon(getResources().getDrawable(R.drawable.sms));
		
		mQuickAction=new QuickAction(getSherlockActivity());
		
		mQuickAction.addActionItem(mailAction);
		mQuickAction.addActionItem(callAction);
		mQuickAction.addActionItem(smsAction);
		
		mQuickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					
					@Override
					public void onItemClick(int pos) {
						if(pos==0){
							//Toast.makeText(getSherlockActivity(), "Option mail ", Toast.LENGTH_SHORT).show();
							Bundle args=new Bundle();
							args.putString("email", adresseMail);
							args.putString("sujet", mSubject);
							EMailDialogFragment emdf=new EMailDialogFragment();
							emdf.setArguments(args);
							emdf.show(getFragmentManager(), "Dialog Email");
						}
						else if(pos==1){
							makeCall(num_phone);
						}
						else if(pos==2){
							/*Intent i=new Intent(getActivity(),SmsActivity.class);
							i.putExtra("phone", num_phone);
							startActivity(i);*/
							Bundle margs=new Bundle();
							margs.putString("numero", num_phone);
							SmsDialogFragment sdf=new SmsDialogFragment();
							sdf.setArguments(margs);
							sdf.show(getFragmentManager(),"Dialog Sms");
						}	
					}
				});
		
	}
	

	@Override
	public void onClick(View v) {

		if(v.getId()==R.id.btn_maphotel){
			((HotelParentActivity)getSherlockActivity()).actionBar.selectTab(((HotelParentActivity)getSherlockActivity()).tab2);
		}
	}
	/*
	 * Intent for making call
	 */
	public void makeCall(String phone_number){
		Intent callIntent=new Intent(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+phone_number));
		startActivity(callIntent);
	}

	
	
}
