package com.android.app.adapter;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ydym.R;

public class MyactionBar extends LinearLayout{
	private ImageView iv_left,iv_right;
	private TextView tv_title;

	public MyactionBar(Context context, AttributeSet attrs) {
		super(context, attrs);
	//	LayoutInflater.from(context).inflate(R.layout.layout_actionbar, this);
		//作用与下面一句相同
		inflate(context, R.layout.layout_actionbar, this);//填充布局页面
		
		iv_left = (ImageView) findViewById(R.id.iv_left);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		tv_title = (TextView) findViewById(R.id.tv_title);
		
	}
	
	public void changeTheme(String title,int imageleft,int imageright,OnClickListener listener){
		tv_title.setText(title);
		if (imageleft==-1) {
			iv_left.setVisibility(View.INVISIBLE);
		}else{
			iv_left.setImageResource(imageleft);
			iv_left.setOnClickListener(listener);
		}
		
		
		if (imageright==-1) {
			iv_right.setVisibility(View.INVISIBLE);
		}else{
			iv_right.setImageResource(imageright);
			iv_right.setOnClickListener(listener);
		}
	}

}
