package com.windowgarden.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.windowgarden.app.R;
import com.windowgarden.app.model.News;
import com.windowgarden.app.util.NewsAdapter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SpaceFragment extends Fragment {

	PullToRefreshListView pullToRefreshView;
	
	private List<News> newsList = new ArrayList<News>();
	
	//private String data[] = {"sss", "ssssss", "1111", "fffaa", "iiii","sssssss"};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_space, container, false);
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), 
		//		android.R.layout.simple_list_item_1, data);
		//ListView listView = (ListView) view.findViewById(R.id.news_listview);
		
		
		initNews();
		
		NewsAdapter adapter = new NewsAdapter(getActivity(),
				R.layout.news_item, newsList);
		ListView listView = (ListView) view.findViewById(R.id.news_listview);
		listView.setAdapter(adapter);
		
		return view;
	}

		
		/*

		pullToRefreshView = (PullToRefreshListView) view.findViewById(R.id.
				news_pull_to_refresh_listview);
		
		pullToRefreshView.setAdapter(adapter);
	
		
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
		*/	
	
	private void initNews() {
		News news_1 = new News("CloudGarden产品在美国上市，内测版体验，多图", 
				News.HOT_ITEM, "Tom", "一分钟前", "240回复");
		newsList.add(news_1);
		News news_2 = new News("求教多肉植物营养液选取的问题！在线等，挺急的", 
				News.NORMAL_ITEM, "Amy", "五分钟前", "38回复");
		newsList.add(news_2);
		News news_3 = new News("CG装置个性化定制D.I.Y全攻略~~（独家）", 
				News.RECENT_ITEM, "Bob", "十分钟前", "20回复");
		newsList.add(news_3);
		News news_4 = new News("CloudGarden产品在美国上市，内测版体验，多图", 
				News.HOT_ITEM, "Tom", "二十分钟前", "310回复");
		newsList.add(news_4);
		News news_5 = new News("求教多肉植物营养液选取的问题！在线等，挺急的", 
				News.NORMAL_ITEM, "Amy", "三十五分钟前", "98回复");
		newsList.add(news_5);
		News news_6 = new News("CG装置个性化定制D.I.Y全攻略~~（独家）", 
				News.RECENT_ITEM, "Amy", "五十分钟前", "6回复");
		newsList.add(news_6);
		News news_7 = new News("CloudGarden产品在美国上市，内测版体验，多图", 
				News.HOT_ITEM, "Tom", "一小时前", "590回复");
		newsList.add(news_7);
		News news_8 = new News("求教多肉植物营养液选取的问题！在线等，挺急的", 
				News.NORMAL_ITEM, "Amy", "一小时前", "67回复");
		newsList.add(news_8);
		News news_9 = new News("CG装置个性化定制D.I.Y全攻略~~（独家）", 
				News.RECENT_ITEM, "Amy", "两小时前", "92回复");
		newsList.add(news_9);
	}
	
}
	