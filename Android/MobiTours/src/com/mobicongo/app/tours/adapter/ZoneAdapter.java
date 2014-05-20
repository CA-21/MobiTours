package com.mobicongo.app.tours.adapter;

import java.util.List;

import com.mobicongo.app.tours.model.ZoneGeo;

import com.mobicongo.app.tours.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ZoneAdapter extends BaseAdapter{

	List<ZoneGeo> items;
	LayoutInflater inflater;
	String cachePath;
	
	
	public ZoneAdapter(Context context,List<ZoneGeo> items,
			String cachePath) {
		this.items = items;
		this.inflater = LayoutInflater.from(context);
		this.cachePath = cachePath;
	}

	public ZoneAdapter(Context context,List<ZoneGeo> items) {
		this.items = items;
		this.inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return items.size();
	}


	public Object getItem(int position) {
		return items.get(position);
	}

	public long getItemId(int position) {
		return position;
	}
	
	public void setZones(List<ZoneGeo> items){
		this.items=items;
	}
	
	public class ViewHolder{
		ImageView image;
		TextView name;
		TextView desc;
	}

	
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		
		if(convertView == null){
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.zone_item, null);
			
			holder.image=(ImageView)convertView.findViewById(R.id.zoneImg);
			holder.name=(TextView)convertView.findViewById(R.id.zoneName);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		holder.name.setText(items.get(position).getNameZoneGeo());
		
//		String fileURL = items.get(position).getCoverPictureURL();
//		String fileName = ToolBox.cacheFile(fileURL, cachePath);
//			
//		if(fileName!=null){
//			Bitmap myBitmap = BitmapFactory.decodeFile(fileName);
//		    holder.image.setImageBitmap(myBitmap);
//		} 
				
		return convertView;
	}

}
