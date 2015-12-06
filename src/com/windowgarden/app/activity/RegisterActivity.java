package com.windowgarden.app.activity;

import com.windowgarden.app.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener{
	
	private static int PASSWORD_NOT_EQUAL = 1;
	
	private Button register;
	
	private Button help;
	
	private EditText password;
	
	private EditText affirmPassword;
	
	private int check;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		password = (EditText) findViewById(R.id.password);
		affirmPassword = (EditText) findViewById(R.id.affirm_password);
		String passwordString = password.getText().toString();
		String affirmPasswordString = affirmPassword.getText().toString();
		if(passwordString != null && 
				passwordString.compareTo(affirmPasswordString) != 0) {
			check = PASSWORD_NOT_EQUAL;
		} 
		
		register.setOnClickListener(this);
		help.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.affirm_register:
			if (check == PASSWORD_NOT_EQUAL) {
				Toast.makeText(RegisterActivity.this, "两次密码输入不一致", 
						Toast.LENGTH_SHORT).show();
				break;
			}
			Toast.makeText(RegisterActivity.this, "注册成功", 
					Toast.LENGTH_SHORT).show();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finish();
			break;
			
		case R.id.register_help:
			Intent helpIntent = new Intent(Intent.ACTION_VIEW);
			helpIntent.setData(Uri.parse("http://www.baidu.com"));
			startActivity(helpIntent);
			break;
			
		default:
			break;
		}
	}
}
