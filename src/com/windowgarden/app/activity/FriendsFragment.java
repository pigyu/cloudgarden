package com.windowgarden.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.windowgarden.app.R;
import com.windowgarden.app.model.Friend;
import com.windowgarden.app.util.FriendAdapter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import android.widget.AdapterView.OnItemClickListener;

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


	PullToRefreshListView pullToRefreshView;
	
	private List<Friend> friendList = new ArrayList<Friend>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_friends, container, false);
		
		initFriends();
		
		FriendAdapter adapter = new FriendAdapter(getActivity(), 
				R.layout.friend_item, friendList);
		
		pullToRefreshView = (PullToRefreshListView) view.
				findViewById(R.id.pull_to_refresh_listview);
		
		pullToRefreshView.setAdapter(adapter);
		
		pullToRefreshView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				//instead of using adapter, we use parent here to avoid error.
				Friend friend = (Friend) parent.getAdapter().getItem(position); 
				
				Intent intent = new Intent(getActivity(), ChatActivity.class);
				intent.putExtra("friend_name", friend.getName());
				intent.putExtra("friend_image_id", friend.getImageId());
				startActivity(intent);
			}
		});
		
		// Set a listener to be invoked when the list should be refreshed.
		pullToRefreshView.setOnRefreshListener(new OnRefreshListener<ListView>() {
		    @Override
		    public void onRefresh(PullToRefreshBase<ListView> refreshView) {
		    	// Do work to refresh the list here.
		    	new GetDataTask().execute();
		    }
		});
		
		pullToRefreshView.setOnPullEventListener(new OnPullEventListener<ListView>() {

			@Override
			public void onPullEvent(PullToRefreshBase<ListView> refreshView,
					State state, Mode direction) {
				 if (state.equals(State.PULL_TO_REFRESH)) {  
		                refreshView.getLoadingLayoutProxy().
		                setPullLabel(getString(R.string.pull_to_refresh));  
		                refreshView.getLoadingLayoutProxy().
		                setReleaseLabel(getString(R.string.release_to_refresh));  
		                refreshView.getLoadingLayoutProxy().
		                setRefreshingLabel(getString(R.string.loading));  
		  
		                String label = DateUtils.formatDateTime(getActivity(),  
		                        System.currentTimeMillis(),  
		                        DateUtils.FORMAT_SHOW_TIME | 
		                        DateUtils.FORMAT_SHOW_DATE | 
		                        DateUtils.FORMAT_ABBREV_ALL);  
		                // Update the LastUpdatedLabel  
		                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(  
		                        getString(R.string.updated) + " : " + label);  
		            }  
			}
			
		});
		
		return view;
	}

	
	private class GetDataTask extends AsyncTask<Void, Void, String[]> {
	    
	    @Override
	    protected void onPostExecute(String[] result) {
	        // Call onRefreshComplete when the list has been refreshed.
	        pullToRefreshView.onRefreshComplete();
	        super.onPostExecute(result);
	    }

		@Override
		protected String[] doInBackground(Void... arg0) {
			return null;
		}
	}
	
	
	private void initFriends() {
		Friend friend_1 = new Friend("Tom", R.drawable.friend_boy);
		friendList.add(friend_1);
		Friend friend_2 = new Friend("Amy", R.drawable.friend_girl);
		friendList.add(friend_2);
		Friend friend_3 = new Friend(" Bush", R.drawable.friend_ladin);
		friendList.add(friend_3);
		Friend friend_4 = new Friend("Alice", R.drawable.friend_girl_2);
		friendList.add(friend_4);
		Friend friend_5 = new Friend("James", R.drawable.friend_oldman);
		friendList.add(friend_5);
		Friend friend_6 = new Friend("Jerry", R.drawable.friend_boy_2);
		friendList.add(friend_6);
		Friend friend_7 = new Friend("Watson", R.drawable.friend_girl_3);
		friendList.add(friend_7);
		Friend friend_8 = new Friend("Jimmy", R.drawable.friend_kid);
		friendList.add(friend_8);
		Friend friend_9 = new Friend("Michael", R.drawable.friend_cartoon);
		friendList.add(friend_9);
		Friend friend_10 = new Friend("Albert", R.drawable.friend_cat);
		friendList.add(friend_10);
	}

}	
	




	

