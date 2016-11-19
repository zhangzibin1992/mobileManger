package com.example.ydym;


import java.util.ArrayList;

import com.android.app.tools.BaseMyActionBarActivity;
import com.android.app.tools.CommonUtil;
import com.android.app.tools.MemoryManager;
import com.android.app.tools.PiechartView;
import com.zhuoxin.app.zhangzibin.entity.AppInfo;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SevenActivity extends BaseMyActionBarActivity{
	private TextView tv_guanli_Allrj,tv_guanli_Systemrj,tv_guanli_Userrj,tv_phocolor,tv_SDcolor;
	private ProgressBar bar1,bar2;
	private TextView tv_guanli_01,tv_guanli_02;
	private PiechartView piechartView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seven);
		
		actionBar("软件管理", R.drawable.btn_homeasup_default, -1, listener);
		findId();
		neiCunphone();
		setOnclick();
		
	}
	
	private  void neiCunphone(){
		float freeRunSpace = MemoryManager.getPhoneSelfFreeSize();//空闲
		float intRunSpace = MemoryManager.getPhoneSelfSize();//完整
		float alreadyUse = intRunSpace-freeRunSpace;
		
		float neizhiFreeSD = MemoryManager.getPhoneSelfSDCardFreeSize();
		float neizhiAllSD = MemoryManager.getPhoneSelfSDCardSize();
		float neizhiAlready = neizhiAllSD-neizhiFreeSD;
		
		float freeSD = MemoryManager.getPhoneOutSDCardFreeSize(getApplicationContext());
		float intSD = MemoryManager.getPhoneOutSDCardSize(getApplicationContext());
		float alreadyUseSD = intSD-freeSD;
		
		float AllAlready=alreadyUse+neizhiAlready;
		float Allchucun=intRunSpace+neizhiAllSD;
				
		float usedb = AllAlready/Allchucun;
		int use100 = (int) (usedb*100);
		
		bar1.setMax(100);
		bar1.setProgress(use100);
		
		tv_guanli_01.setText("已用储存空间："+CommonUtil.getFileSize((long)AllAlready)+"/"+CommonUtil.getFileSize((long)Allchucun));
		
		
		float unusedb = alreadyUseSD/intSD;
		int unuse100 = (int) (unusedb*100);
		
		bar2.setMax(100);
		bar2.setProgress(unuse100);
		tv_guanli_02.setText("已用储存空间："+CommonUtil.getFileSize((long)alreadyUseSD)+"/"+CommonUtil.getFileSize((long)intSD));
		

		
		tv_phocolor.setText("手机内存："+CommonUtil.getFileSize((long)Allchucun));
		tv_SDcolor.setText("外置SD内存："+CommonUtil.getFileSize((long)intSD));
		//piechartView.setProportion(Allchucun, intSD);
		piechartView.setLoadingProportion(Allchucun, intSD);
	}
	
	

	

	private void findId() {
		bar1 = (ProgressBar) findViewById(R.id.progressBar1);
		bar2 = (ProgressBar) findViewById(R.id.progressBar2);
		
		tv_guanli_01 = (TextView) findViewById(R.id.tv_guanli_01);
		tv_guanli_02 = (TextView) findViewById(R.id.tv_guanli_02);
		
		tv_guanli_Allrj = (TextView) findViewById(R.id.tv_guanli_Allrj);
		tv_guanli_Systemrj = (TextView) findViewById(R.id.tv_guanli_Systemrj);
		tv_guanli_Userrj = (TextView) findViewById(R.id.tv_guanli_Userrj);
		
		tv_phocolor = (TextView) findViewById(R.id.tv_phocolor);
		tv_SDcolor = (TextView) findViewById(R.id.tv_SDcolor);
		
		piechartView = (PiechartView) findViewById(R.id.bingxingtu);
	}
	
	private void setOnclick() {
		tv_guanli_Allrj.setOnClickListener(this);
		tv_guanli_Systemrj.setOnClickListener(this);
		tv_guanli_Userrj.setOnClickListener(this);
	}

	View.OnClickListener listener = new OnClickListener() {
		
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
		int ids = v.getId();
		switch (ids) {
		case R.id.tv_guanli_Allrj:
		case R.id.tv_guanli_Systemrj:
		case R.id.tv_guanli_Userrj:
			Intent intent = new Intent(this, AppShowActivity.class);
			intent.putExtra("key", ids);
			startActivity(intent);
			break;

		
		}
		
	}
	

}
