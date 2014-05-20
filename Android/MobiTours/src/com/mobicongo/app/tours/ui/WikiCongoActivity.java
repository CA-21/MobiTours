package com.mobicongo.app.tours.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.mobicongo.app.tours.R;
import com.mobicongo.app.tours.adapter.MobiWikiListAdapter;
import com.mobicongo.app.tours.model.WikiItem;

public class WikiCongoActivity extends SherlockFragmentActivity{

	String wikiItemURL;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wikilayout);
		
		ArrayList<WikiItem> wiki_items=getWikiItem();
		
		final ListView lvw=(ListView)findViewById(R.id.wiki_listv);
		
		lvw.setAdapter(new MobiWikiListAdapter(getBaseContext(),wiki_items));
		
		lvw.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> aa, View vi, int position,
					long id) {
				Object w=lvw.getItemAtPosition(position);
				WikiItem w_obj=(WikiItem)w;
				String val=w_obj.getWikititle().toString();
				
				if(val=="Intro"){
					wikiItemURL="file:///android_asset/wikicongo/about/introcongo.html";
					Intent i=new Intent(getBaseContext(),MobiWebViewActivity.class);
					i.putExtra("ItemURL", wikiItemURL);
					startActivity(i);
				}
				
				if(val=="Histoire"){
					wikiItemURL="file:///android_asset/wikicongo/about/historiacongo.html";
					Intent i=new Intent(getBaseContext(),MobiWebViewActivity.class);
					i.putExtra("ItemURL", wikiItemURL);
					startActivity(i);
				}
				
				if(val=="Culture"){
					wikiItemURL="file:///android_asset/wikicongo/about/culturecongo.html";
					Intent i=new Intent(getBaseContext(),MobiWebViewActivity.class);
					i.putExtra("ItemURL", wikiItemURL);
					startActivity(i);
				}
				
				if(val=="Nourriture"){
					wikiItemURL="file:///android_asset/wikicongo/about/boufcongo.html";
					Intent i=new Intent(getBaseContext(),MobiWebViewActivity.class);
					i.putExtra("ItemURL", wikiItemURL);
					startActivity(i);
				}
				
				if(val=="Musique"){
					wikiItemURL="file:///android_asset/wikicongo/about/musiccongo.html";
					Intent i=new Intent(getBaseContext(),MobiWebViewActivity.class);
					i.putExtra("ItemURL", wikiItemURL);
					startActivity(i);
				}
				
			}
			
		});
	}
	
		
	private ArrayList<WikiItem> getWikiItem(){
		ArrayList<WikiItem> res=new ArrayList<WikiItem>();
		
		WikiItem wk=new WikiItem();
		wk=new WikiItem();
		wk.setWikiImageNb(1);
		wk.setWikititle("Intro");
		res.add(wk);
		
		wk=new WikiItem();
		wk.setWikiImageNb(2);
		wk.setWikititle("Histoire");
		res.add(wk);
		
		wk=new WikiItem();
		wk.setWikiImageNb(3);
		wk.setWikititle("Culture");
		res.add(wk);
		
		wk=new WikiItem();
		wk.setWikiImageNb(4);
		wk.setWikititle("Nourriture");
		res.add(wk);
		
		wk=new WikiItem();
		wk.setWikiImageNb(5);
		wk.setWikititle("Musique");
		res.add(wk);
		
		return res;
	}
	
}
