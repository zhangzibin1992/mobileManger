package com.example.ydym;

import java.util.ArrayList;

import com.android.app.adapter.AppAdapter;
import com.android.app.adapter.MyactionBar;
import com.android.app.tools.AppInfoManager;
import com.zhuoxin.app.zhangzibin.entity.AppInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.OnScrollListener;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;

public class AppShowActivity extends Activity {
	private MyactionBar myactionBar;
	private String text;
	private ProgressBar progressBar;
	private AppAdapter appAdapter;
	private ListView listView;
	private ArrayList<AppInfo> appInfos = null;
	private int ids;
	private CheckBox checkBox;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_show);
		findId();
		Intent intent = getIntent();
		ids = intent.getIntExtra("key", R.id.tv_guanli_Allrj);
		switch (ids) {
		case R.id.tv_guanli_Allrj:
			text = "所有程序";
			break;
		case R.id.tv_guanli_Systemrj:
			text = "系统程序";
			break; 
		case R.id.tv_guanli_Userrj:
			text = "用户程序";
			break; 
			
		}
		
		
		
		myactionBar.changeTheme(text, R.drawable.btn_homeasup_default, -1, listener);
		appAdapter = new AppAdapter(this);
		
		listView.setAdapter(appAdapter);
		listView.setOnScrollListener(onScrollListener);
		asynLoadApp();
		checkBox.setOnCheckedChangeListener(changeListener);
		
		
	}
	
	
	private OnScrollListener onScrollListener = new OnScrollListener() {
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			AppAdapter appAdapter = (AppAdapter) listView.getAdapter();
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_FLING:
				appAdapter.isLoading(true);
				
				break;

			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				appAdapter.isLoading(false);
				break;
				
			case OnScrollListener.SCROLL_STATE_IDLE:
				appAdapter.isLoading(false);
				appAdapter.notifyDataSetChanged();
				break;
			}
		}
		
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			
		}
	};
	
	private OnCheckedChangeListener changeListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			AppAdapter adapter = (AppAdapter) listView.getAdapter();
			ArrayList<AppInfo> list = adapter.getDataList();
			for (AppInfo appInfo : appInfos) {
				appInfo.setClear(isChecked);
			}
			adapter.notifyDataSetChanged();
		}
	};
	
	
	
	
	private void findId() {
		checkBox = (CheckBox) findViewById(R.id.cb_appshow);
		listView = (ListView) findViewById(R.id.lv_applistview);
		
		progressBar = (ProgressBar) findViewById(R.id.pr_1);
		myactionBar = (MyactionBar) findViewById(R.id.my_action);
	}
	
	
	View.OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_left:
				finish();
				break;

			default:
				break;
			}
		}
	};
	
	
	public void asynLoadApp() {
		progressBar.setVisibility(View.VISIBLE);
		listView.setVisibility(View.INVISIBLE);
		new Thread(new Runnable() {
			
			@Override 
			public void run() {
				
				switch (ids) {
				case R.id.tv_guanli_Allrj:
				appInfos = AppInfoManager.getAppInfoManager(getApplicationContext()).getAllPackageInfo(true);
				break;
				case R.id.tv_guanli_Systemrj:
				appInfos = AppInfoManager.getAppInfoManager(getApplicationContext()).getSystemPackageInfo(true);
				break; 
				case R.id.tv_guanli_Userrj:
				appInfos = AppInfoManager.getAppInfoManager(getApplicationContext()).getUserPackageInfo(true);
				break; 
				} 
				
				runOnUiThread(new Runnable() {
					@Override 
					public void run() {
						progressBar.setVisibility(View.INVISIBLE);
						listView.setVisibility(View.VISIBLE);
						appAdapter.addAllDataToMyadapter(appInfos);
						appAdapter.notifyDataSetChanged(); 
						} 
					}); 
				
				} 
			}).start(); 
		} 

	

}
