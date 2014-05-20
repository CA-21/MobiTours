/**
 * 
 */
package com.mobicongo.app.tours.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.controller.MobiToursBD;
import com.mobicongo.app.tours.utils.ImageLoader;

/**
 * @author Mishka
 *
 */

public class VillesListAdapter extends BaseAdapter{

	private Activity activity;
	private ArrayList<HashMap<String,String>> data;
	private static LayoutInflater inflater=null;
	public ImageLoader imageLoader;
	
	public VillesListAdapter(Activity a,ArrayList<HashMap<String,String>> d)
	{
		activity=a;
		data=d;
		inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader=new ImageLoader(activity.getApplicationContext());
	}
	
	public int getCount(){
		return data.size();
	}
	
	public Object getItem(int position){
		return position;
	}
		
	public long getItemId(int position){
		return position;
	}
	
	public View getView(int position, View convertView,ViewGroup parent)
	{
		View vi=convertView;
		if(convertView==null)
			vi=inflater.inflate(R.layout.city_item, null);
			
		TextView cityname=(TextView)vi.findViewById(R.id.cityName);
		TextView citydesc=(TextView)vi.findViewById(R.id.citydesc);
		ImageView thumb_image=(ImageView)vi.findViewById(R.id.cityImg);
		
		TextView id_c=(TextView)vi.findViewById(R.id.idcity);
		
		
		HashMap<String,String> ville_recordset=new HashMap<String,String>();
		ville_recordset=data.get(position);
		
		cityname.setText(ville_recordset.get(MobiToursBD.key_name_city));
		citydesc.setText(ville_recordset.get(MobiToursBD.key_desc_city));
		id_c.setText(ville_recordset.get(MobiToursBD.key_idcity));

		
		imageLoader.DisplayImage(ville_recordset.get
				(MobiToursBD.cityurlimage), thumb_image);
		
		return vi;
		
	}
	
}
