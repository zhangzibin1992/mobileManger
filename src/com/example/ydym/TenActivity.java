package com.example.ydym;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.android.app.adapter.DeleteAdapter;
import com.android.app.adapter.Fileshowadapter;
import com.android.app.adapter.MyactionBar;
import com.android.app.tools.CommonUtil;
import com.android.app.tools.DbClearPathManager;
import com.android.app.tools.FileManager;
import com.example.lib.BaseActivity;
import com.zhuoxin.app.zhangzibin.entity.RubbishFileInfo;

import android.media.DeniedByServerException;
import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class TenActivity extends BaseActivity {
	private MyactionBar myactionBar;
	private TextView tv_rubbishNumber;
	private ProgressBar pgb_loading;
	private ListView lv_listview;
	private DeleteAdapter deleteAdapter;
	private Thread thread;
	private long totalSize= 0;
	private CheckBox cb_rubbish;
	private Button btn_rubbish;
	private ArrayList<RubbishFileInfo> arrayList=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ten);
		findId();
		setAction();
		try {
			loading();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void findId() {
		myactionBar=(MyactionBar) findViewById(R.id.my_action);
		tv_rubbishNumber = (TextView) findViewById(R.id.tv_rubbishNumber);
		pgb_loading = (ProgressBar) findViewById(R.id.pgb_loading);
		lv_listview = (ListView) findViewById(R.id.lv_listview);
		cb_rubbish = (CheckBox) findViewById(R.id.cb_rubbish);
		btn_rubbish = (Button) findViewById(R.id.btn_rubbish);
	}

	private void setAction() {
		myactionBar.changeTheme("垃圾清理", R.drawable.btn_homeasup_default, -1, listener);
		deleteAdapter = new DeleteAdapter(getApplicationContext());
		lv_listview.setAdapter(deleteAdapter);
		cb_rubbish.setOnCheckedChangeListener(checkedChangeListener);
		btn_rubbish.setOnClickListener(listener);
		
	}
	
	private OnCheckedChangeListener checkedChangeListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			List<RubbishFileInfo> list = deleteAdapter.getDataList();
			for (RubbishFileInfo rubbishFileInfo : list) {
				rubbishFileInfo.setSelect(isChecked);
			}
			deleteAdapter.notifyDataSetChanged();
		}
	};
	
	
	private void loading() throws Exception{
		InputStream path = getResources().getAssets().open("db/clearpath.db");
		DbClearPathManager.readUpdateDB(path);
		final ArrayList<RubbishFileInfo> arrayList = DbClearPathManager.getPhoneRubbishfile(getApplicationContext());
		totalSize=0;
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (RubbishFileInfo rubbishFileInfo : arrayList) {
					File file = new File(rubbishFileInfo.getFilepath());
					long size = FileManager.getFileSize(file);
					rubbishFileInfo.setSize(size);
					
					Message msg = mainHandler.obtainMessage();
					msg.what=1;
					msg.obj=size;
					mainHandler.sendMessage(msg);
				}
				Message msg = mainHandler.obtainMessage();
				msg.what=2;
				msg.obj = arrayList;
				mainHandler.sendMessage(msg);
			}
		});
		thread.start();
	}
	
	
	protected void myHandleMessage(Message msg){
		if(msg.what==1){
			long size = (Long) msg.obj;
			totalSize+=size;
			tv_rubbishNumber.setText(CommonUtil.getFileSize(totalSize));
		}
		if(msg.what==2){
			ArrayList<RubbishFileInfo> arrayList = (ArrayList<RubbishFileInfo>) msg.obj;
			pgb_loading.setVisibility(View.INVISIBLE);
			lv_listview.setVisibility(View.VISIBLE);
			deleteAdapter = new DeleteAdapter(TenActivity.this);
			lv_listview.setAdapter(deleteAdapter);
			deleteAdapter.addAllDataToMyadapter(arrayList);
			deleteAdapter.notifyDataSetChanged();
			
		}
	}
	//错误，不能用
/*	private void delete(){
		List<RubbishFileInfo> list = new ArrayList<RubbishFileInfo>();
		for (int i = 0; i < deleteAdapter.getDataList().size(); i++) {
			RubbishFileInfo rubbishFileInfo =  arrayList.get(i);
			if(rubbishFileInfo.isSelect()){
				list.add(rubbishFileInfo);
			}
		}
		
		for (int i = 0; i < list.size(); i++) {
			RubbishFileInfo rubbishFileInfo = list.get(i);
			if(rubbishFileInfo.isSelect()){
				deleteAdapter.getDataList().remove(rubbishFileInfo);
			}
		}
		deleteAdapter.notifyDataSetChanged();
		
	}*/
	
	private View.OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_left:
				finish();
				break;
				
				
			//清理垃圾	
			case R.id.btn_rubbish:
				
				break;
			}
		}
	};

	

}
