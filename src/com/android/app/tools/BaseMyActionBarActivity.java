package com.android.app.tools;

import android.view.View.OnClickListener;

import com.android.app.adapter.MyactionBar;
import com.example.lib.BaseActivity;
import com.example.ydym.R;

public class BaseMyActionBarActivity extends BaseActivity{
	private MyactionBar myactionBar;
		public void actionBar(String title,int leftImage,int rightImage,OnClickListener listener) {
			try {
				myactionBar = (MyactionBar) findViewById(R.id.my_action);
				myactionBar.changeTheme(title, leftImage, rightImage, listener);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
}
