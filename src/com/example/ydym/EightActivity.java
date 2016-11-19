package com.example.ydym;

import com.android.app.adapter.MyactionBar;
import com.android.app.adapter.PhonemgrAdapter;
import com.android.app.tools.CommonUtil;
import com.android.app.tools.MemoryManager;
import com.android.app.tools.PhoneManager;
import com.zhuoxin.app.zhangzibin.entity.PhoneInfo;

import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class EightActivity extends Activity implements OnClickListener{
	private MyactionBar myactionBar;
	private ProgressBar pgb_dianchi,pgb_loading_shoujijiance;
	private TextView tv_dianchi;
	private BatteryBroadcastReceiver broadcastReceiver;
	private PhonemgrAdapter phonemgrAdapter;
	private ListView lv_jiance;
	private int temperatureBattery;
	
	
	public class BatteryBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED))
			{
				Bundle bundle = intent.getExtras();
			float maxBattery = (Integer)bundle.get(BatteryManager.EXTRA_SCALE); // �ܵ���
			float currentBattery = (Integer) bundle.get(BatteryManager.EXTRA_LEVEL); // ��ǰ����
			 temperatureBattery = (Integer)bundle.get(BatteryManager.EXTRA_TEMPERATURE); // ����¶�
			 
			pgb_dianchi.setMax((int) maxBattery);
			pgb_dianchi.setProgress((int)currentBattery);
			int current100 = (int) (currentBattery/maxBattery*100);
			tv_dianchi.setText(current100 + "%");//TextView ��ʾ����
					}
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eight);
		findId();
		setAction();
		initMainButton();
		phonemgrAdapter = new PhonemgrAdapter(this);
		lv_jiance.setAdapter(phonemgrAdapter);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				initAdapterData();
			}
		}).start();
	}
	
	public void initAdapterData() {
		pgb_loading_shoujijiance.setVisibility(View.VISIBLE);
		lv_jiance.setVisibility(View.INVISIBLE);
		
		PhoneManager manager = PhoneManager.getPhoneManage(this);
		String title;
		String text;
		Drawable icon;
		title = "�豸����:" + manager.getPhoneName1();
		text = "ϵͳ�汾:" + manager.getPhoneSystemVersion();
		icon = getResources().getDrawable(R.drawable.setting_info_icon_version);
		final PhoneInfo phoneInfo1 = new PhoneInfo(title, text, icon);
		
		
		title = "ȫ�������ڴ�" +CommonUtil.getFileSize(MemoryManager.getPhoneTotalRamMemory());
		text = "ʣ�������ڴ�" +CommonUtil.getFileSize(MemoryManager.getPhoneFreeRamMemory(this));
		icon = getResources().getDrawable(R.drawable.setting_info_icon_space);
		final PhoneInfo phoneInfo2 = new PhoneInfo(title, text, icon);
		
			
		title = "cpu ����:" + manager.getPhoneCpuName();
		text = "cpu ����:" + manager.getPhoneCpuNumber();
		icon = getResources().getDrawable(R.drawable.setting_info_icon_cpu);
		final PhoneInfo phoneInfo3 = new PhoneInfo(title, text, icon);
		
			
		title = "�ֻ��ֱ���:" + manager.getResolution();
		text = "����ֱ���:" + manager.getMaxPhotoSize();
		icon = getResources().getDrawable(R.drawable.setting_info_icon_camera);
		final PhoneInfo phoneInfo4 = new PhoneInfo(title, text, icon);
		
			
		title = "�����汾:" + manager.getPhoneSystemBasebandVersion();
		text = "�Ƿ�ROOT:" + (manager.isRoot() ? "��" : "��");
		icon = getResources().getDrawable(R.drawable.setting_info_icon_root);
		final PhoneInfo phoneInfo5 = new PhoneInfo(title, text, icon);
		
		title = "@author��������   ";
		text = "׿����Ȥ�Ͼ��ֹ�˾";
		icon = getResources().getDrawable(R.drawable.tianshi);
		final PhoneInfo phoneInfo6 = new PhoneInfo(title, text, icon);
		
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					phonemgrAdapter.addDataToMyadapter(phoneInfo1);
					phonemgrAdapter.addDataToMyadapter(phoneInfo2);
					phonemgrAdapter.addDataToMyadapter(phoneInfo3);
					phonemgrAdapter.addDataToMyadapter(phoneInfo4);
					phonemgrAdapter.addDataToMyadapter(phoneInfo5);
					phonemgrAdapter.addDataToMyadapter(phoneInfo6);
					phonemgrAdapter.notifyDataSetChanged();
					pgb_loading_shoujijiance.setVisibility(View.INVISIBLE);
					lv_jiance.setVisibility(View.VISIBLE);
				}
			});
			
		}
	
	
	//��ֹ����ָ���쳣
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(broadcastReceiver);
	}
	
	
	private void initMainButton() {
		// ע���ص����㲥������(���ڿؼ�findView ����)
		broadcastReceiver = new BatteryBroadcastReceiver();
		IntentFilter filter = new
		IntentFilter(Intent.ACTION_BATTERY_CHANGED);
		registerReceiver(broadcastReceiver, filter);
		}
	
	private void findId() {
		myactionBar = (MyactionBar) findViewById(R.id.my_action);
		pgb_dianchi = (ProgressBar) findViewById(R.id.pgb_dianchi);
		tv_dianchi = (TextView) findViewById(R.id.tv_dianchi);
		lv_jiance = (ListView) findViewById(R.id.lv_jiance);
		pgb_loading_shoujijiance = (ProgressBar) findViewById(R.id.pgb_loading_shoujijiance);
	}

	private void setAction() {
		myactionBar.changeTheme("�ֻ����", R.drawable.btn_homeasup_default, -1, listener);
	}
	
	private View.OnClickListener listener = new View.OnClickListener() {
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

	@Override
	public void onClick(View v) {
	switch (v.getId()) {
		case R.id.pgb_dianchi:
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("����¶�");
			builder.setMessage("����¶ȣ�"+temperatureBattery/10+" ��C");
			builder.show();
			break;

		default:
			break;
		}
		
	}
	

}
