package com.mobicongo.app.tours.fragments;

import com.mobicongo.app.tours.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class SelectMapTypeDialog extends DialogFragment{
	
	private MapTypeDialogListener mListener;
	public static final int TYPE_NORMAL=0;
	public static final int TYPE_HYBRID=1;
	public static final int TYPE_SATELLITE=2;
	public static final int TYPE_TERRAIN=3;
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		//build the dialog
		AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.map_select_type_dialog);
		builder.setItems(R.array.maptypes, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mListener.onSelectMapTypeDialogSelected(which);
			}
		});
		return builder.create();
	}

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (MapTypeDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement MapTypeDialogListener");
        }
	}

	/**
     * Interface used by callbacks from the SelectMapTypeDialog when the user makes a selection.
     */
    public interface MapTypeDialogListener {
        public void onSelectMapTypeDialogSelected(int type);
    }

	public void show(FragmentManager fragmentManager, String string) {
		// TODO Auto-generated method stub
		
	}
}
