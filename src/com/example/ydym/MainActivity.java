package com.example.ydym;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.app.adapter.VpAdapter;
import com.example.lib.BaseActivity;

public class MainActivity extends BaseActivity{
	private VpAdapter vpAdapter;
	private ViewPager viewPager;
	private TextView textView;
	private boolean isrun;
	private boolean isFromThirdActivity = false;
	private ImageView[] imageViews = new ImageView[3];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		
		if(name!=null&&name.equals("ThirdActivity")){
			isFromThirdActivity = true;
		}
		
		
		SharedPreferences preferences = getSharedPreferences("data1", Context.MODE_PRIVATE);
		 isrun = preferences.getBoolean("context", true);
		if(!isrun){
			isrun = false;
			Tiaozhuanyemian(SecondActivity.class);
			finish();
		}else if(isrun){
			getAndSetView();
			setlayout();
			
		}
		
		
	}
	
	
	
	
	//获取控件
	public void getAndSetView(){
		textView = (TextView) findViewById(R.id.tv_jump);
		imageViews[0] = (ImageView) findViewById(R.id.iv_huidian1);
		imageViews[1] = (ImageView) findViewById(R.id.iv_huidian2);
		imageViews[2] = (ImageView) findViewById(R.id.iv_huidian3);
		textView.setVisibility(View.INVISIBLE);
		textView.setOnClickListener(this);
		
		//添加适配器
		viewPager = (ViewPager) findViewById(R.id.viewpage_id);
		vpAdapter = new VpAdapter(this);
		viewPager.setAdapter(vpAdapter);
		viewPager.setOnPageChangeListener(listener);
	}
	
	
		//添加滑动布局及视图
	public void setlayout(){
		ImageView imageView = null;
		imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, null);
		imageView.setImageResource(R.drawable.adware_style_applist);
		vpAdapter.addViewToAdapter(imageView);
		
		imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, null);
		imageView.setImageResource(R.drawable.adware_style_banner);
		vpAdapter.addViewToAdapter(imageView);
		
		imageView = (ImageView) getLayoutInflater().inflate(R.layout.image_layout, null);
		imageView.setImageResource(R.drawable.adware_style_creditswall);
		vpAdapter.addViewToAdapter(imageView);
		
		vpAdapter.updata();
	}
	
	public ViewPager.OnPageChangeListener listener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			
			//此处arg0指集合角标数值
			textView.setVisibility(View.INVISIBLE); //“直接跳过”文本不显示
			if (arg0 == 2) {
				textView.setVisibility(View.VISIBLE); //当到达第三个页面时文本显示
				}
			
			
			//用遍历的方法将所有的点变成灰色
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[i].setImageResource(R.drawable.adware_style_default); 
				}
			//再将当前页面的点设置成绿色
			imageViews[arg0].setImageResource(R.drawable.adware_style_selected); 
			
			
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	public void secondRun(){
		SharedPreferences preferences = getSharedPreferences("data1", Context.MODE_PRIVATE);
		Editor editor=preferences.edit();
		editor.putBoolean("context", false);
		editor.commit();
	}

	@Override
 	public void onClick(View v) {
		secondRun();
		if(isFromThirdActivity){
			Tiaozhuanyemian(ThirdActivity.class);
			finish();
		}else{
			isrun = false;
			Tiaozhuanyemian(SecondActivity.class);
			finish();
		}
		}

}
