package com.zhuoxin.app.zhangzibin.adapter;


import com.example.ydym.R;
import com.zhuoxin.app.zhangzibin.base.adapter.BaseMyadapter;
import com.zhuoxin.app.zhangzibin.entity.TelclassInfo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Myadapter extends BaseMyadapter{

	

	public Myadapter(Context context) {
		super(context);//获取上下文
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = layoutInflater.inflate(R.layout.inflact_m, null);
		TextView textView = (TextView) view.findViewById(R.id.tv_inflactm);
		TelclassInfo text = getItem(position);
		textView.setText(text.name);
		return view;
	}

}
