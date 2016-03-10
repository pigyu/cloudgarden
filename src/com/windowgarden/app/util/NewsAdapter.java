package com.windowgarden.app.util;

import java.util.List;

import com.windowgarden.app.R;
import com.windowgarden.app.model.News;
import com.windowgarden.app.util.FriendAdapter.ViewHolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<News> {

	private int resourceId;
	
	public NewsAdapter(Context context, int textViewResourceId,
			List<News> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		News news = getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);			
			viewHolder = new ViewHolder();
			viewHolder.icon = (ImageView) view.findViewById(R.id.news_icon);
			viewHolder.title = (TextView) view.findViewById(R.id.news_title);
			viewHolder.userName = (TextView) view.findViewById(R.id.news_user_name);
			viewHolder.postDate = (TextView) view.findViewById(R.id.news_post_date);
			viewHolder.replyNum = (TextView) view.findViewById(R.id.news_reply_num);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		
		if(news.getType() == News.HOT_ITEM) {
			viewHolder.icon.setImageResource(R.drawable.hot_news_icon);
		} else if (news.getType() == News.RECENT_ITEM){
			viewHolder.icon.setImageResource(R.drawable.recent_news_icon);
		} else {
			viewHolder.icon.setVisibility(View.GONE);
		}
		viewHolder.title.setText(news.getTitle());
		viewHolder.userName.setText(news.getUserName());
		viewHolder.postDate.setText(news.getPostDate());
		viewHolder.replyNum.setText(news.getReplyNum());
		
		return view;
	}
	
	class ViewHolder {
		
		ImageView icon;
		
		TextView title;
		
		TextView userName;
		
		TextView postDate;
		
		TextView replyNum;
	}
}
