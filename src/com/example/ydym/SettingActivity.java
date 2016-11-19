package com.example.ydym;

import com.android.app.adapter.MyactionBar;
import com.android.app.tools.NotificationUtil;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SettingActivity extends Activity implements OnClickListener{
	private MyactionBar myactionBar;
	private TextView tv_help,tv_advice,tv_share,tv_update,tv_aboutus;
	private ToggleButton tgbtn_kaijiqidong,tgbtn_tongzhitubiao,tgbtn_xiaoxituisong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		myactionBar = (MyactionBar) findViewById(R.id.my_action);
		myactionBar.changeTheme("…Ë÷√", R.drawable.btn_homeasup_default, -1, listener);
		
		findid2();
		
		
		/*if(NotificationUtil.isRunning()){
			tgbtn_xiaoxituisong.setChecked(true);
		}else if(NotificationUtil.isRunning()){
			tgbtn_xiaoxituisong.setChecked(false);
		}*/
		
		
	}
	
	
		
		private void findid2() {
			tv_help = (TextView) findViewById(R.id.tv_help);
			tv_advice = (TextView) findViewById(R.id.tv_advice);
			tv_share = (TextView) findViewById(R.id.tv_share);
			tv_update = (TextView) findViewById(R.id.tv_update);
			tv_aboutus = (TextView) findViewById(R.id.tv_aboutus);
			
			tgbtn_kaijiqidong = (ToggleButton) findViewById(R.id.tgbtn_kaijiqidong);
			tgbtn_tongzhitubiao = (ToggleButton) findViewById(R.id.tgbtn_tongzhitubiao);
			tgbtn_xiaoxituisong = (ToggleButton) findViewById(R.id.tgbtn_xiaoxituisong);
			
			tv_help.setOnClickListener(this);
			tv_advice.setOnClickListener(this);
			tv_share.setOnClickListener(this);
			tv_update.setOnClickListener(this);
			tv_aboutus.setOnClickListener(this);
			tgbtn_xiaoxituisong.setOnCheckedChangeListener(toggleButtonlistener);
			
			tgbtn_xiaoxituisong.setChecked(NotificationUtil.isOpenNotification(getApplicationContext()));

		}
		
		OnCheckedChangeListener toggleButtonlistener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					NotificationUtil.showAppIconNotification(getApplicationContext());
				}else{
					NotificationUtil.cancelAppIconNotification(getApplicationContext());
				}
				
			}
		};
		
		
		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			NotificationUtil.setOpenNotification(getApplicationContext(), tgbtn_xiaoxituisong.isChecked());
			
			
		}
		
		
	
	
	
		View.OnClickListener listener = new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent;
				switch (v.getId()) {
				case R.id.iv_left:
					finish();
					break;
					
				
				default:
					break;
				}
			}
		};

		@Override
		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {
		case R.id.tv_help:
			SharedPreferences preferences = getSharedPreferences("data1", Context.MODE_PRIVATE);
			Editor editor = preferences.edit();
			editor.putBoolean("context", true);
			editor.commit();
			Bundle bundle = new Bundle();
			bundle.putString("name", "ThirdActivity");
			intent = new Intent(this, MainActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
			
		case R.id.tv_advice:
			
			break;
		case R.id.tv_share:
			
			break;
		case R.id.tv_update:

			break;

		case R.id.tv_aboutus:
			intent = new Intent(this, AboutUSActivity.class);
			startActivity(intent);
			break;
			}
		}
	
		
		
		
}
