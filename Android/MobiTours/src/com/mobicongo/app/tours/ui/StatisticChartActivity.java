package com.mobicongo.app.tours.ui;

import java.util.Random;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.mobicongo.app.tours.R;

public class StatisticChartActivity extends SherlockFragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graphs);
		
		
		// init example series data 1
		Random rand = new Random();
		int size = 20;
		GraphViewData[] data = new GraphViewData[size];
		for (int i=0; i<size; i++) {
			data[i] = new GraphViewData(i, rand.nextInt(20));
		}
		GraphViewSeries exampleSeries1 = new GraphViewSeries(data);

		
		
		// init example series data 2
		GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
				new GraphViewData(1, 2.0d)
				, new GraphViewData(2, 1.5d)
				, new GraphViewData(2.5, 3.0d) // another frequency
				, new GraphViewData(3, 2.5d)
				, new GraphViewData(4, 1.0d)
				, new GraphViewData(5, 3.0d)
		});
		
		GraphView graphView;
				
		graphView=new BarGraphView(this,"Rates Chart");
		
		((LineGraphView) graphView).setDrawBackground(true);
		((LineGraphView) graphView).setBackgroundColor(Color.rgb(80, 30, 30));	
		
		// custom static labels
		graphView.setHorizontalLabels(new String[] {"2 days ago", "yesterday", "today", "tomorrow"});
		graphView.setVerticalLabels(new String[] {"high", "middle", "low"});
		graphView.addSeries(exampleSeries); // data

		LinearLayout layout = (LinearLayout) findViewById(R.id.graphic);
		layout.addView(graphView);
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	
	
	
}


