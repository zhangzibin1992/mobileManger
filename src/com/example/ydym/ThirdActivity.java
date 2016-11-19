package com.example.ydym;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.app.adapter.AppInfoManager2;
import com.android.app.adapter.MyactionBar;
import com.android.app.adapter.SpeedAdapter;
import com.android.app.tools.MemoryManager;
import com.android.app.tools.SpeedActivityView;
import com.zhuoxin.app.zhangzibin.entity.RuningAppInfo;

public class ThirdActivity extends Activity implements OnClickListener{
	private TextView tv_rocket,tv_soft,tv_phonemgr,tv_telmgr,tv_file,tv_clean,tv_neicun2;
	private MyactionBar myactionBar;
	private ImageView iv_left,iv_right,imv_jiasu;
	private SpeedActivityView speedActivityView;
	private Button btn_neicun2;
	private Map<Integer, List<RuningAppInfo>> map = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
		myactionBar = (MyactionBar) findViewById(R.id.my_action);
		findId();
		myactionBar.changeTheme("安卓手机管家", R.drawable.ic_launcher2, R.drawable.ic_child_configs, listener);
		setOnClick();
		neicun2();
		
	}
	
	private void neicun2(){
		float freeRunSpace = MemoryManager.getPhoneFreeRamMemory(this);//空闲
		float intRunSpace = MemoryManager.getPhoneTotalRamMemory();//完整
		float AlreadyRunSpace = intRunSpace-freeRunSpace;
		float usedb = AlreadyRunSpace/intRunSpace;
		int use100 = (int) (usedb*100);
		int angle = (int) (usedb*360);
		tv_neicun2.setText(use100+"%");
		speedActivityView.setAngleWithAnim(angle);
	}
	
	
	
	
	View.OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent;
			switch (v.getId()) {
			case R.id.iv_left:
				intent = new Intent(ThirdActivity.this, AboutUSActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.toumingdu02, R.anim.toumingdu03);
				break;
				
			case R.id.iv_right:
				intent = new Intent(ThirdActivity.this, SettingActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.weiyiend, R.anim.weiyistart);
				break;
			case R.id.imv_jiasu:
			case R.id.btn_neicun2:
			case R.id.tv_neicun2:
				AppInfoManager2.getAppInfoManager(getApplicationContext()).killALLProcesses();
				neicun2();
				break;

			default:
				break;
			}
		}
	};
	
	
	public void findId(){
		tv_rocket = (TextView) findViewById(R.id.tv_rocket);
		tv_soft = (TextView) findViewById(R.id.tv_soft);
		tv_phonemgr = (TextView) findViewById(R.id.tv_phonemgr);
		tv_telmgr = (TextView) findViewById(R.id.tv_telmgr);
		tv_file = (TextView) findViewById(R.id.tv_file);
		tv_clean = (TextView) findViewById(R.id.tv_clean);
		
		iv_left = (ImageView) findViewById(R.id.iv_left);
		iv_right = (ImageView) findViewById(R.id.iv_right);
		
		speedActivityView = (SpeedActivityView) findViewById(R.id.view_jiasu);
		imv_jiasu = (ImageView) findViewById(R.id.imv_jiasu);
		btn_neicun2 = (Button) findViewById(R.id.btn_neicun2);
		tv_neicun2 = (TextView) findViewById(R.id.tv_neicun2);
	}
	
	public void setOnClick(){
		tv_rocket.setOnClickListener(this);
		tv_soft.setOnClickListener(this);
		tv_phonemgr.setOnClickListener(this);
		tv_telmgr.setOnClickListener(this);
		tv_file.setOnClickListener(this);
		tv_clean.setOnClickListener(this);
		
		imv_jiasu.setOnClickListener(listener);
		btn_neicun2.setOnClickListener(listener);
		tv_neicun2.setOnClickListener(listener);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.tv_rocket:
			 intent = new Intent(ThirdActivity.this, SixActivity.class);
			startActivity(intent);
			break;
			
		case R.id.tv_soft:
			 intent = new Intent(ThirdActivity.this, SevenActivity.class);
			startActivity(intent);
			break;
			
		case R.id.tv_phonemgr:
			 intent = new Intent(ThirdActivity.this, EightActivity.class);
			startActivity(intent);
			break;
			
		case R.id.tv_telmgr:
			 intent = new Intent(ThirdActivity.this, FourActivity.class);
			startActivity(intent);
			break;
			
		case R.id.tv_file:
			 intent = new Intent(ThirdActivity.this, NineActivity.class);
			startActivity(intent);
			break;
			
		case R.id.tv_clean:
			 intent = new Intent(ThirdActivity.this, TenActivity.class);
			startActivity(intent);
			break;
		}
	}
}
