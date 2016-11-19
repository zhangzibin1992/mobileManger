package com.example.ydym;

import com.android.app.adapter.MyactionBar;
import com.android.app.tools.CommonUtil;
import com.android.app.tools.FileManager;
import com.android.app.tools.FileTypeUtil;
import com.example.lib.BaseActivity;

import android.os.Bundle;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class NineActivity extends BaseActivity implements OnClickListener{
	
	private Thread thread;
	private FileManager fileManager;
	
	private MyactionBar myactionBar;
	private TextView tv_loading_title_all,tv_loading_text_context;
	private TextView tv_loading_title_wendang,tv_loading_text_context2;
	private TextView tv_loading_title_shiping,tv_loading_text_context3;
	private TextView tv_loading_title_yinpin,tv_loading_text_context4;
	private TextView tv_loading_title_tuxiang,tv_loading_text_context5;
	private TextView tv_loading_title_yasuobao,tv_loading_text_context6;
	private TextView tv_loading_title_chengxubao,tv_loading_text_context7;
	
	private TextView tv_wenjianguanli_number;
	
	private ImageView iv_loading_jiantou01;
	private ImageView iv_loading_jiantou02;
	private ImageView iv_loading_jiantou03;
	private ImageView iv_loading_jiantou04;
	private ImageView iv_loading_jiantou05;
	private ImageView iv_loading_jiantou06;
	private ImageView iv_loading_jiantou07;
	
	private ProgressBar pgb_loading01;
	private ProgressBar pgb_loading02;
	private ProgressBar pgb_loading03;
	private ProgressBar pgb_loading04;
	private ProgressBar pgb_loading05;
	private ProgressBar pgb_loading06;
	private ProgressBar pgb_loading07;
	
	private View View_all;
	private View View_wendang;
	private View View_shipin;
	private View View_yinpin;
	private View View_tuxiang;
	private View View_yasuobao;
	private View View_chengxubao;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nine);
		findId();
		setAction();
		loadData();
	}
	
	private void loadData() {
		fileManager = FileManager.getFileManager();
		fileManager.setSearchFileListener(searchFileListener);
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				fileManager.searchSDCardFile();
			}
		});
		thread.start();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		fileManager.setStopSearch(true);
		thread.interrupt();
		thread = null;
	}
	
	protected void myHandleMessage(Message msg){
		if(msg.what == 1){
			tv_wenjianguanli_number.setText(CommonUtil.getFileSize(fileManager.getAnyFileSize()));
			tv_loading_text_context.setText(CommonUtil.getFileSize(fileManager.getAnyFileSize()));
			String typeName = (String) msg.obj;
			
			if(typeName.equals(FileTypeUtil.TYPE_TXT)){
				tv_loading_text_context2.setText(CommonUtil.getFileSize(fileManager.getTxtFileSize()));
			}else if(typeName.equals(FileTypeUtil.TYPE_VIDEO)){
				tv_loading_text_context3.setText(CommonUtil.getFileSize(fileManager.getVideoFileSize()));
			}else if(typeName.equals(FileTypeUtil.TYPE_AUDIO)){
				tv_loading_text_context4.setText(CommonUtil.getFileSize(fileManager.getAudioFileSize()));
			}else if(typeName.equals(FileTypeUtil.TYPE_IMAGE)){
				tv_loading_text_context5.setText(CommonUtil.getFileSize(fileManager.getImageFileSize()));
			}else if(typeName.equals(FileTypeUtil.TYPE_ZIP)){
				tv_loading_text_context6.setText(CommonUtil.getFileSize(fileManager.getZipFileSize()));
			}else if(typeName.equals(FileTypeUtil.TYPE_APK)){
				tv_loading_text_context7.setText(CommonUtil.getFileSize(fileManager.getApkFileSize()));
			}
			
			
		}
		
		
		if(msg.what == 2){
			tv_wenjianguanli_number.setText(CommonUtil.getFileSize(fileManager.getAnyFileSize()));
			tv_loading_text_context.setText(CommonUtil.getFileSize(fileManager.getAnyFileSize()));
			tv_loading_text_context2.setText(CommonUtil.getFileSize(fileManager.getTxtFileSize()));
			tv_loading_text_context3.setText(CommonUtil.getFileSize(fileManager.getVideoFileSize()));
			tv_loading_text_context4.setText(CommonUtil.getFileSize(fileManager.getAudioFileSize()));
			tv_loading_text_context5.setText(CommonUtil.getFileSize(fileManager.getImageFileSize()));
			tv_loading_text_context6.setText(CommonUtil.getFileSize(fileManager.getZipFileSize()));
			tv_loading_text_context7.setText(CommonUtil.getFileSize(fileManager.getApkFileSize()));
			
			pgb_loading01.setVisibility(View.GONE);
			pgb_loading02.setVisibility(View.GONE);
			pgb_loading03.setVisibility(View.GONE);
			pgb_loading04.setVisibility(View.GONE);
			pgb_loading05.setVisibility(View.GONE);
			pgb_loading06.setVisibility(View.GONE);
			pgb_loading07.setVisibility(View.GONE);
			iv_loading_jiantou01.setVisibility(View.VISIBLE);
			iv_loading_jiantou02.setVisibility(View.VISIBLE);
			iv_loading_jiantou03.setVisibility(View.VISIBLE);
			iv_loading_jiantou04.setVisibility(View.VISIBLE);
			iv_loading_jiantou05.setVisibility(View.VISIBLE);
			iv_loading_jiantou06.setVisibility(View.VISIBLE);
			iv_loading_jiantou07.setVisibility(View.VISIBLE);
			
			
		}
	}
	
	private FileManager.SearchFileListener searchFileListener = new FileManager.SearchFileListener() {
		
		@Override
		public void searching(String typeName) {
			Message msg = mainHandler.obtainMessage();
			msg.what = 1;
			msg.obj = typeName;
			mainHandler.sendMessage(msg);
		}
		
		@Override
		public void end(boolean isExceptionEnd) {
			mainHandler.sendEmptyMessage(2);
			
		}
	};
	
	
	
	
	private void findId() {
		myactionBar = (MyactionBar) findViewById(R.id.my_action);
		
		tv_loading_title_all=(TextView) findViewById(R.id.tv_loading_title_all);
		tv_loading_title_wendang=(TextView) findViewById(R.id.tv_loading_title_wendang);
		tv_loading_title_shiping=(TextView) findViewById(R.id.tv_loading_title_shiping);
		tv_loading_title_yinpin=(TextView) findViewById(R.id.tv_loading_title_yinpin);
		tv_loading_title_tuxiang=(TextView) findViewById(R.id.tv_loading_title_tuxiang);
		tv_loading_title_yasuobao=(TextView) findViewById(R.id.tv_loading_title_yasuobao);
		tv_loading_title_chengxubao=(TextView) findViewById(R.id.tv_loading_title_chengxubao);
		
		tv_loading_text_context=(TextView) findViewById(R.id.tv_loading_text_context);
		tv_loading_text_context2=(TextView) findViewById(R.id.tv_loading_text_context2);
		tv_loading_text_context3=(TextView) findViewById(R.id.tv_loading_text_context3);
		tv_loading_text_context4=(TextView) findViewById(R.id.tv_loading_text_context4);
		tv_loading_text_context5=(TextView) findViewById(R.id.tv_loading_text_context5);
		tv_loading_text_context6=(TextView) findViewById(R.id.tv_loading_text_context6);
		tv_loading_text_context7=(TextView) findViewById(R.id.tv_loading_text_context7);
		
		iv_loading_jiantou01 = (ImageView) findViewById(R.id.iv_loading_jiantou01);
		iv_loading_jiantou02 = (ImageView) findViewById(R.id.iv_loading_jiantou02);
		iv_loading_jiantou03 = (ImageView) findViewById(R.id.iv_loading_jiantou03);
		iv_loading_jiantou04 = (ImageView) findViewById(R.id.iv_loading_jiantou04);
		iv_loading_jiantou05 = (ImageView) findViewById(R.id.iv_loading_jiantou05);
		iv_loading_jiantou06 = (ImageView) findViewById(R.id.iv_loading_jiantou06);
		iv_loading_jiantou07 = (ImageView) findViewById(R.id.iv_loading_jiantou07);
		
		pgb_loading01 = (ProgressBar) findViewById(R.id.pgb_loading01);
		pgb_loading02 = (ProgressBar) findViewById(R.id.pgb_loading02);
		pgb_loading03 = (ProgressBar) findViewById(R.id.pgb_loading03);
		pgb_loading04 = (ProgressBar) findViewById(R.id.pgb_loading04);
		pgb_loading05 = (ProgressBar) findViewById(R.id.pgb_loading05);
		pgb_loading06 = (ProgressBar) findViewById(R.id.pgb_loading06);
		pgb_loading07 = (ProgressBar) findViewById(R.id.pgb_loading07);
		
		View_all = findViewById(R.id.View_all);
		View_wendang = findViewById(R.id.View_wendang);
		View_shipin = findViewById(R.id.View_shipin);
		View_yinpin = findViewById(R.id.View_yinpin);
		View_tuxiang = findViewById(R.id.View_tuxiang);
		View_yasuobao = findViewById(R.id.View_yasuobao);
		View_chengxubao = findViewById(R.id.View_chengxubao);
		
		tv_wenjianguanli_number = (TextView) findViewById(R.id.tv_wenjianguanli_number);
		
		
	}
	
	private void setAction(){
		myactionBar.changeTheme("文件管理", R.drawable.btn_homeasup_default, -1, listener);
		
	}
	
	private View.OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_left:
				finish();
				break;
				
			}
		}
	};


	@Override
	public void onClick(View v) {
		
		int ViewId = v.getId();
		switch (ViewId) {
		case R.id.View_all:
		case R.id.View_wendang:
		case R.id.View_shipin:
		case R.id.View_yinpin:
		case R.id.View_tuxiang:
		case R.id.View_yasuobao:
		case R.id.View_chengxubao:
			Intent intent = new Intent(this, FilemgrActivity.class);
			intent.putExtra("id", ViewId);
			startActivityForResult(intent, 110);
			break;
	}
}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (requestCode == 110) {
			tv_wenjianguanli_number.setText(CommonUtil.getFileSize(fileManager.getAnyFileSize()));
			tv_loading_text_context.setText(CommonUtil.getFileSize(fileManager.getAnyFileSize()));
			tv_loading_text_context7.setText(CommonUtil.getFileSize(fileManager.getApkFileSize()));
			tv_loading_text_context4.setText(CommonUtil.getFileSize(fileManager.getAudioFileSize()));
			tv_loading_text_context5.setText(CommonUtil.getFileSize(fileManager.getImageFileSize()));
			tv_loading_text_context2.setText(CommonUtil.getFileSize(fileManager.getTxtFileSize()));
			tv_loading_text_context3.setText(CommonUtil.getFileSize(fileManager.getVideoFileSize()));
			tv_loading_text_context6.setText(CommonUtil.getFileSize(fileManager.getZipFileSize()));
		}
	}
	
	
	

}
