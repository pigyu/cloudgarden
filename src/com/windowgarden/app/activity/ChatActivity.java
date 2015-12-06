package com.windowgarden.app.activity;

import java.util.ArrayList;
import java.util.List;

import com.windowgarden.app.R;
import com.windowgarden.app.model.Message;
import com.windowgarden.app.util.MessageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class ChatActivity extends Activity {

	private ListView messageListView;
	
	private Button send;
	
	private EditText inputText;
	
	private MessageAdapter adapter;
	
	private List<Message> messageList = new ArrayList<Message>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_chat);
		
		String friendName = getIntent().getStringExtra("friend_name");
		int friendImageId = getIntent().getIntExtra("friend_image_id", R.drawable.friend_boy);
		
		initMessages(friendName, friendImageId);
		
		adapter = new MessageAdapter(ChatActivity.this, R.layout.message_item,
				messageList);
		messageListView = (ListView) findViewById(R.id.message_list_view);
		messageListView.setAdapter(adapter);
		
		ImageView friendImage =  (ImageView) findViewById(R.id.friend_chat_image);
		//friendImage.setImageResource(friendImageId);
		
		send = (Button) findViewById(R.id.send_chat_message);
		inputText = (EditText) findViewById(R.id.chat_input_text);
		inputText.setHint("和好友" + friendName + "畅聊");
		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			    String content = inputText.getText().toString();
				if(!"".equals(content)) {
					Message message = new Message(content, Message.TYPE_SENT, 0);
					messageList.add(message);
					// then notify, and refresh the ListView.
					adapter.notifyDataSetChanged();
					// locate the ListView at the last row.
					messageListView.setSelection(messageList.size());
					inputText.setText("");
				}
			}
			
		});
	}

	private void initMessages(String name, int imageId) {
		Message message_1 = new Message("嘿兄弟，你家植物最近怎么样？ ", 
				Message.TYPE_RESEIVED, imageId);
		messageList.add(message_1);
		Message message_2 = new Message("几株都挺好~你呢，" + name +"？", 
				Message.TYPE_SENT, 0);
		messageList.add(message_2);
		Message message_3 = new Message("不能更赞！简直良心App啊。", 
				Message.TYPE_RESEIVED, imageId);
		messageList.add(message_3);
		Message message_4 = new Message("自从用了CloudGarden，咱再也不用担心没空种植啦！", 
				Message.TYPE_RESEIVED, imageId);
		messageList.add(message_4);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
