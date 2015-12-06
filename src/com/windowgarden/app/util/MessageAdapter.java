package com.windowgarden.app.util;

import java.util.List;

import com.windowgarden.app.R;
import com.windowgarden.app.model.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MessageAdapter extends ArrayAdapter<Message> {

	private int ResourceId;
	
	public MessageAdapter(Context context, int textViewResourceId,
			List<Message> objects) {
		super(context, textViewResourceId, objects);
		ResourceId = textViewResourceId;
	}
	
	@Override
	public View getView(int postion, View convertView, ViewGroup parent) {
		
		View view;
		ViewHolder viewHolder;
		Message message = getItem(postion);
		
		if(convertView == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(getContext()).inflate(ResourceId, null);
			viewHolder.leftMessageLayout = (LinearLayout) view.findViewById(R.id.left_message_layout);
			viewHolder.rightMessageLayout = (LinearLayout) view.findViewById(R.id.right_message_layout);
			viewHolder.leftMessage = (TextView) view.findViewById(R.id.left_message);
			viewHolder.rightMessage = (TextView) view.findViewById(R.id.right_message);
			viewHolder.leftImage = (ImageView) view.findViewById(R.id.friend_chat_image);
			//viewHolder.rightImage = (ImageView) view.findViewById(R.id.my_chat_image);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		if(message.getType() == Message.TYPE_RESEIVED) {
			viewHolder.leftMessageLayout.setVisibility(View.VISIBLE);
			viewHolder.rightMessageLayout.setVisibility(View.GONE);
			viewHolder.leftMessage.setText(message.getContent());
			// set the friend's Image.
			viewHolder.leftImage.setImageResource(message.getImageId());
		
			
		} else if(message.getType() == Message.TYPE_SENT) {
			//((LinearLayout)view.findViewById(R.id.right_message_layout)).setVisibility(View.VISIBLE);
			//((LinearLayout) view.findViewById(R.id.left_message_layout)).setVisibility(View.GONE);
			
			viewHolder.rightMessageLayout.setVisibility(View.VISIBLE);
			viewHolder.leftMessageLayout.setVisibility(View.GONE);
			
			viewHolder.rightMessage.setText(message.getContent());
		}
		
		return view;
	}
	
	class ViewHolder {
		
		LinearLayout leftMessageLayout;
		LinearLayout rightMessageLayout;
		TextView leftMessage;
		TextView rightMessage;
		ImageView leftImage;
		//ImageView rightImage;
	}
	
	
}
