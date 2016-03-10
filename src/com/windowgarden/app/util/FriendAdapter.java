package com.windowgarden.app.util;

import java.util.List;

import com.windowgarden.app.R;
import com.windowgarden.app.model.Friend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FriendAdapter extends ArrayAdapter<Friend> {

	private int resourceId;
	
	public FriendAdapter(Context context, int textViewResourceId,
			List<Friend> objects) {
		super(context, textViewResourceId, objects);
		resourceId = textViewResourceId;
	}

	class ViewHolder {
		
		ImageView friendImage;
		
		TextView friendName;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Friend friend = getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.friendImage = (ImageView) view.findViewById(R.id.friend_image);
			viewHolder.friendName = (TextView) view.findViewById(R.id.friend_name);
			view.setTag(viewHolder);
		} else {
			view = convertView;
		}
		
		viewHolder = (ViewHolder) view.getTag();
		viewHolder.friendImage.setImageResource(friend.getImageId());
		viewHolder.friendName.setText(friend.getName());
		return view;
	}
	
}
