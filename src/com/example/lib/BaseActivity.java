package com.example.lib;

import java.util.Stack;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

public class BaseActivity extends Activity implements OnClickListener{
	
	public static Stack<Activity> stacklist = new Stack<Activity>();
	public void StackActivity() {
		int len = stacklist.size();
		for (int i = 0; i < len; i++) {
			Activity activity = stacklist.pop();
			activity.finish();
		}

	}
	
	
	protected Handler mainHandler = new Handler() {
			
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			myHandleMessage(msg);
		};
		
	};
	
	protected void myHandleMessage(Message msg){
		
	}
	
	
	
	
	
	
	public void Tiaozhuanyemiandaidonghua(Class<?> cls,int SecondAnim,int FirstAnim){
		Intent intent = new Intent(this, cls);
		startActivity(intent);
		overridePendingTransition(SecondAnim, FirstAnim);
	}
	
	public void Tiaozhuanyemian(Class<?> cls) {
		Intent intent = new Intent(this, cls);
		startActivity(intent);
	}

	public void TiaozhuanANDclose(Class<?> cls) {
		Intent intent = new Intent(this, cls);
		startActivity(intent);
		this.finish();
	}

	public void OpenNewActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent(this, cls);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	public void OpenNewActivityAndCloseThis(Class<?> cls,Bundle bundle) {
		Intent intent = new Intent(this, cls);
		 intent.putExtras(bundle);
		startActivity(intent);
		finish();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	// 创建页面
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UtilActivity.D(getClass().getSimpleName() + "onCreate()的方法");
		stacklist.push(this);
		

	}

	// 启动页面
	@Override
	protected void onStart() {
		super.onStart();
		// Log.d("DDD", getClass().getSimpleName()+"onStart()的方法");
		UtilActivity.D(getClass().getSimpleName() + "onStart()的方法");
	}

	// 获取焦点
	@Override
	protected void onResume() {
		super.onResume();
		UtilActivity.D(getClass().getSimpleName() + "onResume()的方法");
	}

	// 舍弃焦点
	@Override
	protected void onPause() {
		super.onPause();
		UtilActivity.D(getClass().getSimpleName() + "onPause()的方法");
	}

	// 重新启动页面
	@Override
	protected void onRestart() {
		super.onRestart();
		UtilActivity.D(getClass().getSimpleName() + "onRestart()的方法");
	}

	// 停止页面
	@Override
	protected void onStop() {
		super.onStop();
		UtilActivity.D(getClass().getSimpleName() + "onStop()的方法");
	}

	// 销毁焦点
	@Override
	protected void onDestroy() {
		super.onDestroy();
		UtilActivity.D(getClass().getSimpleName() + "onDestroy()的方法");
		if(stacklist.contains(this)){
			stacklist.remove(this);
		}
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
