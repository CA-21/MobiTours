package com.mobicongo.app.tours.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.model.WikiItem;

public class MobiWikiListAdapter extends BaseAdapter{

	private static ArrayList<WikiItem> wikiItemArrayList;
	
	private Integer[] wkImgId={
			R.drawable.bg,
			R.drawable.wiki,
			R.drawable.wiki,
			R.drawable.eat,
			R.drawable.wiki
	};
	
	private LayoutInflater w_inflater;
	
	public MobiWikiListAdapter(Context wctx, ArrayList<WikiItem> dt){
		wikiItemArrayList=dt;
		w_inflater=LayoutInflater.from(wctx);
	}
	
	@Override
	public int getCount() {
		return wikiItemArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return wikiItemArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WikiHolder holder;
		if(convertView==null){
			convertView=w_inflater.inflate(R.layout.wikilist_row, null);
			holder=new WikiHolder();
			holder.wikiItemImg=(ImageView)convertView.findViewById(R.id.wiki_img);
			holder.wikiTitle=(TextView)convertView.findViewById(R.id.wikititle);
			convertView.setTag(holder);
		}else
		{
			holder=(WikiHolder)convertView.getTag();
		}
		
		holder.wikiItemImg.setImageResource(wkImgId[wikiItemArrayList.get(position).getWikiImageNb()-1]);
		holder.wikiTitle.setText(wikiItemArrayList.get(position).getWikititle());
		
		return convertView;
	}
	
	static class WikiHolder{
		TextView wikiTitle;
		ImageView wikiItemImg;
	}

}
