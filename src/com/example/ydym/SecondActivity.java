package com.example.ydym;


import com.example.lib.BaseActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SecondActivity extends BaseActivity {
	ImageView imageView;
	Animation animation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		imageView = (ImageView) findViewById(R.id.imageView1);
		animation = AnimationUtils.loadAnimation(this, R.anim.touming);
		animation.setAnimationListener(animationListener);
		imageView.startAnimation(animation);
	}

	private AnimationListener animationListener = new AnimationListener() {
		
		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onAnimationEnd(Animation animation) {
			Tiaozhuanyemian(ThirdActivity.class);
			finish();//跳转其他页面并关闭当前页面
		}
	};

}
