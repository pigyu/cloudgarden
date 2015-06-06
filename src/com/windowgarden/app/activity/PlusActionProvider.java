package com.windowgarden.app.activity;

import com.windowgarden.app.R;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

public class PlusActionProvider extends ActionProvider {

	private Context context;

	public PlusActionProvider(Context context) {
		super(context);
		this.context = context;
	}

	@Override
	public View onCreateActionView() {
		return null;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		subMenu.clear();
		subMenu.add(context.getString(R.string.plus_plaza))
				.setIcon(R.drawable.plaza_icon)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(context, "广场", Toast.LENGTH_SHORT).show();
						return true;
					}
				});
		subMenu.add(context.getString(R.string.plus_add_friend))
				.setIcon(R.drawable.add_icon)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(context, "添加朋友", Toast.LENGTH_SHORT).show();
						return false;
					}
				});
		subMenu.add(context.getString(R.string.plus_weather))
				.setIcon(R.drawable.weather_icon)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(context, "天气信息", Toast.LENGTH_SHORT).show();
						return false;
					}
				});
		subMenu.add(context.getString(R.string.plus_plant_info))
				.setIcon(R.drawable.around_info_icon)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(context, "种植周边", Toast.LENGTH_SHORT).show();
						return false;
					}
				});
		subMenu.add(context.getString(R.string.plus_take_photo))
				.setIcon(R.drawable.pic_share_icon)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(context, "拍照分享", Toast.LENGTH_SHORT).show();
						return false;
					}
				});
	}

	@Override
	public boolean hasSubMenu() {
		return true;
	}

}