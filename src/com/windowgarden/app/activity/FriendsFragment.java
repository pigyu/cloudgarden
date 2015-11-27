package com.windowgarden.app.activity;

import com.windowgarden.app.R;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FriendsFragment extends Fragment {
	
	/**
	 * set layout style using code instead of XML file. 
	 * 
	 * 	@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		FrameLayout fl = new FrameLayout(getActivity());
		fl.setLayoutParams(params);
		DisplayMetrics dm = getResources().getDisplayMetrics();
		final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);
		TextView v = new TextView(getActivity());
		params.setMargins(margin, margin, margin, margin);
		v.setLayoutParams(params);
		v.setLayoutParams(params);
		v.setGravity(Gravity.CENTER);
		v.setText("Friends ListView");
		v.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, dm));
		fl.addView(v);
		return fl;
	    }
	 */
	
	
	@SuppressWarnings("rawtypes")
	SlideAndDragListView listView;

	@SuppressWarnings("rawtypes")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_friends, container, false);
		
		listView = (SlideAndDragListView) view.findViewById(R.id.listView);
		createMemuAndItems(listView);
		
		return view;
	}

	@SuppressWarnings("rawtypes")
	private void createMemuAndItems(SlideAndDragListView listView) {
		//the third parameter is whether can slide over
		Menu menu = new Menu((int) getResources().
				getDimension(R.dimen.slv_item_height), new ColorDrawable(Color.WHITE), true);
		menu.addItem(new MenuItem.Builder().setWidth(90)//set Width
		                .setBackground(new ColorDrawable(Color.RED))// set background
		                .setText("One")//set text string
		                .setTextColor(Color.GRAY)//set text color
		                .setTextSize(20)//set text color
		                .build());
		menu.addItem(new MenuItem.Builder().setWidth(120)
		                .setBackground(new ColorDrawable(Color.BLACK))
		                .setDirection(MenuItem.DIRECTION_RIGHT)//set direction (default DIRECTION_LEFT)
		                .setIcon(getResources().getDrawable(R.drawable.garden_logo_2))// set icon
		                .build());
		//set in sdlv
		listView.setMenu(menu);
	}
	
}
