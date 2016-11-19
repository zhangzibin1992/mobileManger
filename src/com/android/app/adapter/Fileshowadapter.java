package com.android.app.adapter;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.test.suitebuilder.annotation.Suppress;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.app.tools.CommonUtil;
import com.android.app.tools.FileTypeUtil;
import com.example.ydym.R;
import com.zhuoxin.app.zhangzibin.base.adapter.Base_Myadapter;
import com.zhuoxin.app.zhangzibin.base.util.BitmapUtil;
import com.zhuoxin.app.zhangzibin.entity.FileInfo;

public class Fileshowadapter extends Base_Myadapter<FileInfo>{
	private CheckBox cb_fileshow;
	private ImageView iv_fileshow;
	private TextView tv_fileshow_title,tv_fileshow_time,tv_fileshow_size;
	private LruCache<String, Bitmap> lruCache=null;

	public Fileshowadapter(Context context) {
		super(context);
		
		if(Build.VERSION.SDK_INT>=12){
			lruCache = new LruCache<String, Bitmap>(1*1024*1024){
				@SuppressLint("NewApi")
				
				@Override
				protected int sizeOf(String key, Bitmap value) {
					return value.getByteCount();
				}
			};
		}else{
			lruCache= new LruCache<String, Bitmap>(100);
		}
	}
	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = layoutInflater.inflate(R.layout.listview_fileshow, null);
		}
		iv_fileshow = (ImageView) convertView.findViewById(R.id.iv_fileshow);
		tv_fileshow_title = (TextView) convertView.findViewById(R.id.tv_fileshow_title);
		tv_fileshow_time = (TextView) convertView.findViewById(R.id.tv_fileshow_time);
		tv_fileshow_size = (TextView) convertView.findViewById(R.id.tv_fileshow_size);
		
		cb_fileshow = (CheckBox) convertView.findViewById(R.id.cb_fileshow);
		cb_fileshow.setOnCheckedChangeListener(changeListener);
		cb_fileshow.setTag(position);
		
		FileInfo fileInfo = getItem(position);
		boolean isSelect = fileInfo.isSelect();
		
		tv_fileshow_title.setText(fileInfo.getFile().getName());
		tv_fileshow_time.setText(CommonUtil.getStrTime(fileInfo.getFile().lastModified()));
		tv_fileshow_size.setText(CommonUtil.getFileSize(fileInfo.getFile().length()));
		cb_fileshow.setChecked(isSelect);
		
		
		Bitmap fileIcon = getFileIcon(fileInfo, iv_fileshow);
		if(fileIcon!=null){
			iv_fileshow.setImageBitmap(fileIcon);
		}
		
		return convertView;
	}
	
	private OnCheckedChangeListener changeListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			int position = (Integer) buttonView.getTag();
			getItem(position).setSelect(isChecked);
			
		}
	};
	
	
	private Bitmap getFileIcon(FileInfo fileInfo,ImageView iv_fileshow){
		Bitmap bitmap = null;
		String filePath = fileInfo.getFile().getPath();
		
		bitmap=lruCache.get(filePath);
		if(bitmap!=null){
			return bitmap;
		}else if(fileInfo.getFileType().equals(FileTypeUtil.TYPE_IMAGE)){
			bitmap = BitmapUtil.loadBitmap(filePath, new BitmapUtil.SizeMessage(context, true, 43, 43));
			if(bitmap!=null){
				lruCache.put(filePath, bitmap);
				return bitmap;
			}
		}
		Resources res = context.getResources();
		int icon = res.getIdentifier(fileInfo.getIconName(), "drawable", context.getPackageName());
		if(icon<=0)
			icon = R.drawable.icon_file;
		bitmap = BitmapFactory.decodeResource(context.getResources(), icon);
		
		return bitmap;
		
	}

}
