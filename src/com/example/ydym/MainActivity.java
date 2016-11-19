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
	
	
	
	
	//��ȡ�ؼ�
	public void getAndSetView(){
		textView = (TextView) findViewById(R.id.tv_jump);
		imageViews[0] = (ImageView) findViewById(R.id.iv_huidian1);
		imageViews[1] = (ImageView) findViewById(R.id.iv_huidian2);
		imageViews[2] = (ImageView) findViewById(R.id.iv_huidian3);
		textView.setVisibility(View.INVISIBLE);
		textView.setOnClickListener(this);
		
		//���������
		viewPager = (ViewPager) findViewById(R.id.viewpage_id);
		vpAdapter = new VpAdapter(this);
		viewPager.setAdapter(vpAdapter);
		viewPager.setOnPageChangeListener(listener);
	}
	
	
		//��ӻ������ּ���ͼ
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
			
			//�˴�arg0ָ���ϽǱ���ֵ
			textView.setVisibility(View.INVISIBLE); //��ֱ���������ı�����ʾ
			if (arg0 == 2) {
				textView.setVisibility(View.VISIBLE); //�����������ҳ��ʱ�ı���ʾ
				}
			
			
			//�ñ����ķ��������еĵ��ɻ�ɫ
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[i].setImageResource(R.drawable.adware_style_default); 
				}
			//�ٽ���ǰҳ��ĵ����ó���ɫ
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
