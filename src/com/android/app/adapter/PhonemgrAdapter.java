package com.android.app.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.app.tools.CommonUtil;
import com.android.app.tools.MemoryManager;
import com.android.app.tools.PhoneManager;
import com.example.ydym.R;
import com.zhuoxin.app.zhangzibin.base.adapter.Base_Myadapter;
import com.zhuoxin.app.zhangzibin.entity.PhoneInfo;

public class PhonemgrAdapter extends Base_Myadapter<PhoneInfo>{

	public PhonemgrAdapter(Context context) {
		super(context);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView =layoutInflater.inflate(R.layout.shoujijiance_layout, null);
			}
			
			ImageView iv_shoujijiance01 = (ImageView)convertView.findViewById(R.id.iv_shoujijiance01);
			TextView tv_theme01 = (TextView)convertView.findViewById(R.id.tv_theme01);
			TextView tv_theme02 = (TextView) convertView.findViewById(R.id.tv_theme02);
			
			PhoneInfo phoneInfo = getItem(position);
			
			iv_shoujijiance01.setImageDrawable(phoneInfo.getIcon());
			tv_theme01.setText(phoneInfo.getTitle());
			tv_theme02.setText(phoneInfo.getText());
			
			
			switch (position % 3) {
			case 0:
				iv_shoujijiance01.setBackgroundResource(R.drawable.notification_information_progress_green);
			break;
			case 1:
				iv_shoujijiance01.setBackgroundResource(R.drawable.notification_information_progress_red);
			break;
			case 2:
			default:
				iv_shoujijiance01.setBackgroundResource(R.drawable.notification_information_progress_yellow);
			break;
			}
					
		return convertView;
	}
	
	
	
	

}
