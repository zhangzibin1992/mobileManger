package com.example.ydym;

import com.android.app.adapter.MyactionBar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class AboutUSActivity extends Activity {
	
	private MyactionBar myactionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_us);
		
		myactionBar = (MyactionBar) findViewById(R.id.my_action);
		myactionBar.changeTheme("关于我们", R.drawable.btn_homeasup_default, -1, listener);
		
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

	
}
