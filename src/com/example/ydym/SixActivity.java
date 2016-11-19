package com.example.ydym;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.app.adapter.AppAdapter;
import com.android.app.adapter.AppInfoManager2;
import com.android.app.adapter.MyactionBar;
import com.android.app.adapter.SpeedAdapter;
import com.android.app.tools.AppInfoManager;
import com.android.app.tools.CommonUtil;
import com.android.app.tools.MemoryManager;
import com.android.app.tools.SystemManager;
import com.zhuoxin.app.zhangzibin.entity.AppInfo;
import com.zhuoxin.app.zhangzibin.entity.RuningAppInfo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.app.Activity;

public class SixActivity extends Activity {
	private MyactionBar myactionBar;
	private TextView tv_xinghao,tv_banbenhao,tv_size;
	private Button btn_qingli,btn_jingcheng;
	private ProgressBar pgb_qingli,pgb_loading;
	private CheckBox chb_quanbuxuanze;
	private ListView lv_listview;
	public SpeedAdapter speedAdapter;
	private SystemManager systemManager;
	private Map<Integer, List<RuningAppInfo>> map = null;
	private ArrayList<RuningAppInfo> runingAppInfo = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_six);
		findId();
		speedAdapter = new SpeedAdapter(this);
		lv_listview.setAdapter(speedAdapter);
		myactionBar.changeTheme("�ֻ�����", R.drawable.btn_homeasup_default, -1, listener);
		setViewAction();
		loadData();
		
	}
	
	OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_left:
				finish();
				break;
				
			case R.id.btn_qingli:
				List<RuningAppInfo> appInfos = speedAdapter.getDataList();
				for (RuningAppInfo runingAppInfo : appInfos) {
					if (runingAppInfo.isClear()) {
						String packageName = runingAppInfo.getPackageName();
						AppInfoManager2.getAppInfoManager(getApplicationContext()).killProcesses(packageName);
					}
				}
				
				//���btn_jingcheng�����ť�ϵ������û����̣��������������ʾ�û����̵�listview,��֮����ʾϵͳ���̵�listview
				if(btn_jingcheng.getText().equals("��ʾϵͳ����")){
					loadData();
				}else{
					speedAdapter.delDataALL();
					map = AppInfoManager2.getAppInfoManager(getApplicationContext()).getRuningAppInfos();
					speedAdapter.addAllDataToMyadapter(map.get(AppInfoManager2.RUNING_APP_TYPE_SYS));
					//                                                                 0
					//speedAdapter.setState(SpeedAdapter.STATE_SHOW_ALL);��Ĭ��ֵ�жϴ���
					//                                    2
				}
				chb_quanbuxuanze.setChecked(false);
				speedAdapter.notifyDataSetChanged();
				break;
				
				
			case R.id.btn_jingcheng:
				
				if(btn_jingcheng.getText().equals("��ʾϵͳ����")){
					speedAdapter.delDataALL();
					map = AppInfoManager2.getAppInfoManager(getApplicationContext()).getRuningAppInfos();
					speedAdapter.addAllDataToMyadapter(map.get(AppInfoManager2.RUNING_APP_TYPE_SYS));
					//                                                                 0
					//speedAdapter.setState(SpeedAdapter.STATE_SHOW_ALL);��Ĭ��ֵ�жϴ���
					//                                    2
					speedAdapter.notifyDataSetChanged();
					btn_jingcheng.setText(getResources().getString(R.string.btn_title01));
				}else{
					loadData();
					btn_jingcheng.setText(getResources().getString(R.string.btn_title02));
				}
				break;
			}
		}

		
	};
	
	
	//����߳���ģ���������л����������ֻ�������OK�����δ���á�
	private void sysProgress(){
		pgb_loading.setVisibility(View.VISIBLE);
		lv_listview.setVisibility(View.INVISIBLE);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				speedAdapter.delDataALL();
				map = AppInfoManager2.getAppInfoManager(getApplicationContext()).getRuningAppInfos();
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						neiCun();
						pgb_loading.setVisibility(View.INVISIBLE);
						lv_listview.setVisibility(View.VISIBLE);
						speedAdapter.addAllDataToMyadapter(map.get(AppInfoManager2.RUNING_APP_TYPE_SYS));
						//                                                                 0
						//                                    2
						speedAdapter.notifyDataSetChanged();
					}
				});
			}
		}).start();
		
		
		
		
	}
	
	
	private void loadData() {
		pgb_loading.setVisibility(View.VISIBLE);
		lv_listview.setVisibility(View.INVISIBLE);
			new Thread(new Runnable() {
				@Override
				public void run() {
					map = AppInfoManager2.getAppInfoManager(getApplicationContext()).getRuningAppInfos();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							neiCun();
							pgb_loading.setVisibility(View.INVISIBLE);
							lv_listview.setVisibility(View.VISIBLE);
							speedAdapter.addAllDataToMyadapter(map.get(AppInfoManager2.RUNING_APP_TYPE_USER));
							//                                                                 1
							speedAdapter.setState(SpeedAdapter.STATE_SHOW_USER);
							//                                     0
							speedAdapter.notifyDataSetChanged();
						}
					});
				}
			}).start();
	}

	private void findId() {
		myactionBar = (MyactionBar) findViewById(R.id.my_action);
		
		tv_xinghao = (TextView) findViewById(R.id.tv_xinghao);
		tv_banbenhao = (TextView) findViewById(R.id.tv_banbenhao);
		tv_size = (TextView) findViewById(R.id.tv_size);
		
		btn_qingli = (Button) findViewById(R.id.btn_qingli);
		btn_jingcheng = (Button) findViewById(R.id.btn_jingcheng);
		
		pgb_qingli = (ProgressBar) findViewById(R.id.pgb_qingli);
		pgb_loading = (ProgressBar) findViewById(R.id.pgb_loading);
		
		chb_quanbuxuanze = (CheckBox) findViewById(R.id.chb_quanbuxuanze);
		
		lv_listview = (ListView) findViewById(R.id.lv_listview);
		
		
	}
	
	private void setViewAction() {
		chb_quanbuxuanze.setOnCheckedChangeListener(changeListener);
		
		
		tv_xinghao.setText("�ֻ��ͺţ�"+systemManager.getPhoneName().toUpperCase());
		tv_banbenhao.setText("�ֻ�ϵͳ�汾��"+systemManager.getPhoneModelName());
		neiCun();
		
		btn_qingli.setOnClickListener(listener);
		btn_jingcheng.setOnClickListener(listener);
	}
	
	
	private void neiCun(){
		float freeRunSpace = MemoryManager.getPhoneFreeRamMemory(this);//����
		float intRunSpace = MemoryManager.getPhoneTotalRamMemory();//����
		float AlreadyRunSpace = intRunSpace-freeRunSpace;
		float usedb = AlreadyRunSpace/intRunSpace;
		int use100 = (int) (usedb*100);
		
		pgb_qingli.setMax(100);
		pgb_qingli.setProgress(use100);
		
		tv_size.setText("�����ڴ棺"+CommonUtil.getFileSize((long)AlreadyRunSpace)+"/"+CommonUtil.getFileSize((long)intRunSpace));
		
		
	}
	
	
	
private OnCheckedChangeListener changeListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			List<RuningAppInfo> list = speedAdapter.getDataList();
			for (RuningAppInfo appInfo : list) {
				appInfo.setClear(isChecked);
			}
			
			speedAdapter.notifyDataSetChanged();
		}
	};
	
	

	
}
