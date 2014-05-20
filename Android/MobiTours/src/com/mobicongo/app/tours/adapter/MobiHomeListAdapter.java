package com.mobicongo.app.tours.adapter;

import java.util.ArrayList;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.model.HomeItem;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MobiHomeListAdapter extends BaseAdapter{

	private static ArrayList<HomeItem> homeItemArrayList;
	
	private Integer[] imgId={
		R.drawable.wiki,
		R.drawable.globetown,
		R.drawable.info,
		R.drawable.logoont,
		R.drawable.ic_action_map
	};
	
	private LayoutInflater m_inflater;
	
	public MobiHomeListAdapter(Context context, ArrayList<HomeItem> data){
		homeItemArrayList=data;
		m_inflater=LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return homeItemArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return homeItemArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView=m_inflater.inflate(R.layout.home_list_row, null);
			holder=new ViewHolder();
			holder.itemImage=(ImageView)convertView.findViewById(R.id.list_image);
			holder.txt_title=(TextView)convertView.findViewById(R.id.title);
			holder.txt_description=(TextView)convertView.findViewById(R.id.details);
			
			convertView.setTag(holder);
		}else {
			holder=(ViewHolder)convertView.getTag();
		}
		
		holder.itemImage.setImageResource(imgId[homeItemArrayList.get(position).getImageNumber()-1]);
		holder.txt_title.setText(homeItemArrayList.get(position).getTitle());
		holder.txt_description.setText(homeItemArrayList.get(position).getDescription());
		
		return convertView;
	}

	static class ViewHolder{
		TextView txt_title;
		TextView txt_description;
		ImageView itemImage;
	}
	
}
