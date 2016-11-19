package com.example.ydym;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.android.app.adapter.Fileshowadapter;
import com.android.app.adapter.MyactionBar;
import com.android.app.tools.CommonUtil;
import com.android.app.tools.FileManager;
import com.android.app.tools.FileTypeUtil;
import com.zhuoxin.app.zhangzibin.entity.FileInfo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FilemgrActivity extends Activity{
	private MyactionBar myactionBar;
	private String title ;
	private TextView tv_tvnumber2,tv_space2;
	private ListView lv_fileshow;
	private Fileshowadapter fileshowadapter;
	private ArrayList<FileInfo> arrayList = null;
	private long fileSize = 0;
	private long fileNumber = 0;
	private Button btn_del;
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filemgr);
		findId();
		setAction();
		adapterShow();
		
	}

	private void findId() {
		myactionBar = (MyactionBar) findViewById(R.id.my_action);
		tv_tvnumber2 = (TextView) findViewById(R.id.tv_tvnumber2);
		tv_space2 = (TextView) findViewById(R.id.tv_space2);
		lv_fileshow = (ListView) findViewById(R.id.lv_fileshow);
		btn_del = (Button) findViewById(R.id.btn_del);
	}

	private void setAction() {
		arrayList = new ArrayList<FileInfo>();
		
		Intent intent = getIntent();
		id = intent.getIntExtra("id", -1);
		if(id==R.id.View_all){
			fileSize = FileManager.getFileManager().getAnyFileSize();
			arrayList = FileManager.getFileManager().getAnyFileList();
			title = "全部";
		}else if(id==R.id.View_wendang){
			fileSize = FileManager.getFileManager().getTxtFileSize();
			arrayList = FileManager.getFileManager().getTxtFileList();
			title = "文档";
		}else if(id==R.id.View_shipin){
			fileSize = FileManager.getFileManager().getVideoFileSize();
			arrayList = FileManager.getFileManager().getVideoFileList();
			title = "视频";
		}else if(id==R.id.View_yinpin){
			fileSize = FileManager.getFileManager().getAudioFileSize();
			arrayList = FileManager.getFileManager().getAudioFileList();
			title = "音频";
		}else if(id==R.id.View_tuxiang){
			fileSize = FileManager.getFileManager().getImageFileSize();
			arrayList = FileManager.getFileManager().getImageFileList();
			title = "图像";
		}else if(id==R.id.View_yasuobao){
			fileSize = FileManager.getFileManager().getZipFileSize();
			arrayList = FileManager.getFileManager().getZipFileList();
			title = "压缩包";
		}else if(id==R.id.View_chengxubao){
			fileSize = FileManager.getFileManager().getApkFileSize();
			arrayList = FileManager.getFileManager().getApkFileList();
			title = "程序包";
		}
		fileNumber = arrayList.size();
		tv_tvnumber2.setText(fileNumber+"个");
		tv_space2.setText(CommonUtil.getFileSize(fileSize));
		myactionBar.changeTheme(title, R.drawable.btn_homeasup_default, -1, listener);
		
		btn_del.setOnClickListener(listener);
	}
	
	private void adapterShow(){
		fileshowadapter = new Fileshowadapter(this);
		fileshowadapter.addAllDataToMyadapter(arrayList);
		lv_fileshow.setAdapter(fileshowadapter);
//		lv_fileshow.setOnItemClickListener(itemClickListener);
		lv_fileshow.setOnItemClickListener(new ItemClickEvent());
		fileshowadapter.notifyDataSetChanged();
		
		
		
	}
	
	
	

	
	
	private OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_left:
				finish();
				break;
				
			case R.id.btn_del:
				delFile();
				break;

			default:
				break;
			}
			
		}

	};

/*	private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Log.i("TAG","SSS");
			FileInfo fileInfo = fileshowadapter.getItem(position);
			File file = fileInfo.getFile();
			
			String type = FileTypeUtil.getMIMEType(file);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(file), type);
			startActivity(intent);
			Log.d("DDD", ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
			builder.setTitle("123");
			builder.show();
			
		}
	};*/
	
	
	
	private void delFile() {
		
		List<FileInfo> list = new ArrayList<FileInfo>();
		for (int i = 0; i <fileshowadapter.getDataList().size() ; i++) {
			FileInfo fileInfo = arrayList.get(i);
			if(fileInfo.isSelect()){
				list.add(fileInfo);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			FileInfo fileInfo = list.get(i);
			File file = fileInfo.getFile();
			long size = file.length();
			if(file.delete()){
				fileshowadapter.getDataList().remove(fileInfo);
				FileManager.getFileManager().getAnyFileList().remove(fileInfo);
				FileManager.getFileManager().setAnyFileSize(FileManager.getFileManager().getAnyFileSize()-size);
				fileSize = FileManager.getFileManager().getAnyFileSize();
				switch (id) {
				
				
				case R.id.View_wendang:
					FileManager.getFileManager().getTxtFileList().remove(fileInfo);
					FileManager.getFileManager().setTxtFileSize(FileManager.getFileManager().getTxtFileSize()-size);
					fileSize = FileManager.getFileManager().getTxtFileSize();
					break;
				case R.id.View_shipin:
					FileManager.getFileManager().getVideoFileList().remove(fileInfo);
					FileManager.getFileManager().setVideoFileSize(FileManager.getFileManager().getVideoFileSize()-size);
					fileSize = FileManager.getFileManager().getVideoFileSize();
					break;
				case R.id.View_yinpin:
					FileManager.getFileManager().getAudioFileList().remove(fileInfo);
					FileManager.getFileManager().setAudioFileSize(FileManager.getFileManager().getAudioFileSize()-size);
					fileSize = FileManager.getFileManager().getAudioFileSize();
					break;
				case R.id.View_tuxiang:
					FileManager.getFileManager().getImageFileList().remove(fileInfo);
					FileManager.getFileManager().setImageFileSize(FileManager.getFileManager().getImageFileSize()-size);
					fileSize = FileManager.getFileManager().getImageFileSize();
					break;
				case R.id.View_yasuobao:
					FileManager.getFileManager().getZipFileList().remove(fileInfo);
					FileManager.getFileManager().setZipFileSize(FileManager.getFileManager().getZipFileSize()-size);
					fileSize = FileManager.getFileManager().getZipFileSize();
					break;
				case R.id.View_chengxubao:
					FileManager.getFileManager().getApkFileList().remove(fileInfo);
					FileManager.getFileManager().setApkFileSize(FileManager.getFileManager().getApkFileSize()-size);
					fileSize = FileManager.getFileManager().getApkFileSize();
					break;

				default:
					break;
				}
				
			}
		}
		
		fileshowadapter.notifyDataSetChanged();
		fileNumber = fileshowadapter.getDataList().size();
		tv_tvnumber2.setText(fileNumber+"个");
		tv_space2.setText(CommonUtil.getFileSize(fileSize));
		
		System.gc();
		//放弃线程当前执行权
		Thread.yield();
		
		
		
	}
	
	private final class ItemClickEvent implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			FileInfo fileInfo = fileshowadapter.getItem(position);
			File file = fileInfo.getFile();
			
			String type = FileTypeUtil.getMIMEType(file);
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_VIEW);
			intent.setDataAndType(Uri.fromFile(file), type);
			startActivity(intent);
			
		}
		
	}
	

}

