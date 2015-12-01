package com.windowgarden.app.activity;

import com.windowgarden.app.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

	//SlideAndDragListView<?> listView;

	
	private String[] data = { "friend_1", "friend_2", "friend_3", "friend_4", "friend_5", 
			"friend_6", "friend_7", "friend_8", "friend_9", "friend_10" };
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_friends, container, false);
		
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, data);
		ListView listView = (ListView) view.findViewById(R.id.list_view);
		listView.setAdapter(adapter);
	
		//listView = (SlideAndDragListView<?>) view.findViewById(R.id.listView);
		//createMemuAndItems(listView);
		
		return view;
	}

}	
	




	

