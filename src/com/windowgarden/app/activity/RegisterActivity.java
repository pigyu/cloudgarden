package com.windowgarden.app.activity;

import com.windowgarden.app.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener{
	
	private static int PASSWORD_OK = 0;
	
	private static int PASSWORD_NOT_EQUAL = 1;
	
	private static int PASSWORD_VOID = 2;
	
	private Button register;
	
	private Button help;
	
	private EditText password;
	
	private EditText affirmPassword;
	
	private String passwordString;
	
	private String affirmPasswordString;
	
	private int check;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		
		password = (EditText) findViewById(R.id.register_password);
		affirmPassword = (EditText) findViewById(R.id.affirm_password);
		
		register = (Button) findViewById(R.id.affirm_register);
		help = (Button) findViewById(R.id.register_help);
		register.setOnClickListener(this);
		help.setOnClickListener(this);	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.affirm_register:
			
			setCheckValue(); 
			
			if (check == PASSWORD_NOT_EQUAL) {
				Toast.makeText(RegisterActivity.this, "两次密码输入不一致", 
						Toast.LENGTH_SHORT).show();
			} else if (check == PASSWORD_VOID) {
				Toast.makeText(RegisterActivity.this, "密码不能为空", 
						Toast.LENGTH_SHORT).show();
			} else {
				showDialog();
			}
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

	private void showDialog() {
		AlertDialog.Builder dialog = new AlertDialog.
				Builder(RegisterActivity.this);
		dialog.setTitle("CloudGarden注册提示框");
		dialog.setMessage("注册成功！");
		dialog.setCancelable(false);
		dialog.setPositiveButton("好的", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				finish();
			}
		});
		dialog.show();
	}

	private void setCheckValue() {
		passwordString = password.getText().toString();
		affirmPasswordString = affirmPassword.getText().toString();
		
		if(TextUtils.isEmpty(passwordString) || TextUtils.isEmpty(affirmPasswordString)) {
			check = PASSWORD_VOID;
			return;
		} else if (passwordString.compareTo(affirmPasswordString) != 0) {
			check = PASSWORD_NOT_EQUAL;
			return;
		} else {
			check = PASSWORD_OK;
		}
		
	}
}
