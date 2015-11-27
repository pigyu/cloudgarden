package com.windowgarden.app.view;
import com.windowgarden.app.R;

import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

public class TitleLayout extends LinearLayout {

	Button titleBackButton;
	
	public TitleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title, this);
		titleBackButton = (Button) findViewById(R.id.title_back);
		if(!isInEditMode()) {
			titleBackButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					((Activity) getContext()).finish();
				}
			});
		}

	}
}
