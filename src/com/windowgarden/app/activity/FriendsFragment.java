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

	
	
	/*
		//设置侧滑监听器
		listView.setOnSlideListener(new SlideAndDragListView.OnSlideListener() {
            @Override
            public void onSlideOpen(View view, View parentView, 
            		int position, int direction) {
            		Toast.makeText(MyApplication.getContext(), 
            				"Slide", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSlideClose(View view, View parentView, 
            		int position, int direction) {
            		Toast.makeText(MyApplication.getContext(), 
            				"Slide", Toast.LENGTH_SHORT).show();
            }
        });
		//设置菜单Item点击监听器
		listView.setOnMenuItemClickListener(new SlideAndDragListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(View v, int itemPosition, 
            		int buttonPosition, int direction) {
                switch (direction) {
                    case MenuItem.DIRECTION_LEFT:
                        switch (buttonPosition) {
                            case 0://One
                            	Toast.makeText(MyApplication.getContext(), 
                            			"MenuItemClick/DIRECTION_LEFT", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        break;
                    case MenuItem.DIRECTION_RIGHT:
                        switch (buttonPosition) {
                            case 0://icon
                            	Toast.makeText(MyApplication.getContext(), 
                            			"MenuItemClick/DIRECTION_RIGHT", Toast.LENGTH_SHORT).show();
                                return false;
                        }
                        break;
                }
				return false;
            }
        });
		
		//设置拖拽监听器
		listView.setOnDragListener(new SlideAndDragListView.OnDragListener() {
            @Override
            public void onDragViewStart(int position) {
            		
            }

            @Override
            public void onDragViewMoving(int position) {

            }

            @Override
            public void onDragViewDown(int position) {

            }
        }, mDataList);
		//设置列表条目点击监听器
		listView.setOnListItemClickListener(new SlideAndDragListView.OnListItemClickListener() {
            @Override
            public void onListItemClick(View v, int position) {
            	Toast.makeText(MyApplication.getContext(), 
            			"ListItemClick", Toast.LENGTH_SHORT).show();
            }
        });
		
		return view;
	}
	
	private void createMemuAndItems(SlideAndDragListView<?> listView) {
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
	*/
	
}
