package com.windowgarden.app.activity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.windowgarden.app.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
//import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	/**
	 * 种植界面的fragment
	 */
	private PlantFragment plantFragment;

	/**
	 * 好友界面的ragment
	 */
	private FriendsFragment FriendsFragment;

	/**
	 * 空间界面的Fragment
	 */
	private SpaceFragment spaceFragment;

	/**
	 * PagerSlidingTabStrip的实例
	 */
	private PagerSlidingTabStrip tabs;

	/**
	 * 获取当前屏幕的密度
	 */
	private DisplayMetrics dm;
	
	/**
	 * 获取账号id
	 */
	public static String id = "1";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		id = getIntent().getStringExtra("id");
		
		setOverflowShowingAlways();
		dm = getResources().getDisplayMetrics();
		ViewPager pager = (ViewPager) findViewById(R.id.pager);
		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
		tabs.setViewPager(pager);
		setTabsValue();
	}

	/**
	 * 对PagerSlidingTabStrip的各项属性进行赋值
	 */
	private void setTabsValue() {
		// 设置Tab是自动填充满屏幕的 
		tabs.setShouldExpand(true);
		// 设置Tab的分割线是透明的
		tabs.setDividerColor(Color.TRANSPARENT);
		// 设置Tab底部线的高度
		tabs.setUnderlineHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 1, dm));
		// 设置Tab Indicator的高度
		tabs.setIndicatorHeight((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, dm));
		// 设置Tab标题文字的大小
		tabs.setTextSize((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_SP, 16, dm));
		// 设置Tab Indicator的颜色 
		tabs.setIndicatorColor(Color.parseColor("#45c01a"));
		// 设置选中Tab文字的颜色 (自定义的方法)
		tabs.setSelectedTextColor(Color.parseColor("#45c01a"));
		// 取消点击Tab时的背景色
		tabs.setTabBackground(0);
	}

	public class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		private final String[] titles = { "植物", "好友", "圈子" };

		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}

		@Override
		public int getCount() {
			return titles.length;
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				if (plantFragment == null) {
					plantFragment = new PlantFragment();
				}
				return plantFragment;
			case 1:
				if (FriendsFragment == null) {
					FriendsFragment = new FriendsFragment();
				}
				return FriendsFragment;
			case 2:
				if (spaceFragment == null) {
					spaceFragment = new SpaceFragment();
				}
				return spaceFragment;
			default:
				return null;
			}
		}

	}

	/**
	 * 加载menu资源，所有Action按钮都以item格式定义在其中（main.xml）
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
		//MenuItem searchItem = menu.findItem(R.id.action_search);
		//SearchView searchView = (SearchView) 
		//MenuItemCompat.getActionView(searchItem);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.action_search:
			Toast.makeText(MainActivity.this, "查找", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_activity:
			Toast.makeText(MainActivity.this, "活动", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_collection:
			Toast.makeText(MainActivity.this, "我的收藏", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_test:
			Toast.makeText(MainActivity.this, "数据自测", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_settings:
			Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.action_feedback:
			Toast.makeText(MainActivity.this, "联系我们", Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	/**
	 * 使overflow中的Action按钮也可以显示图标
	 */
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
