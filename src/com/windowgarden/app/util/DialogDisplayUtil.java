package com.windowgarden.app.util;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class DialogDisplayUtil {

	public static void showAlertDialog(String message) {
		AlertDialog.Builder dialog = new AlertDialog.
				Builder(MyApplication.getContext());
		dialog.setTitle("警示信息对话框");
		dialog.setMessage(message);
		dialog.setCancelable(false);
		dialog.setPositiveButton("知道啦", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		dialog.show();
		
		/*
		dialog.setNegativeButton("重试", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		*/
	}
}
