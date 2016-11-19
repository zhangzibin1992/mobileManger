package com.android.app.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
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
import com.zhuoxin.app.zhangzibin.entity.RubbishFileInfo;

public class DeleteAdapter extends Base_Myadapter<RubbishFileInfo>{
	private LruCache<String, Bitmap> lruCache=null;
	private CheckBox cb_fileshow;
	private ImageView iv_fileshow;
	private TextView tv_fileshow_title,tv_fileshow_size;

	public DeleteAdapter(Context context) {
		super(context);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = layoutInflater.inflate(R.layout.del_layout, null);
		}
		 cb_fileshow = (CheckBox) convertView.findViewById(R.id.cb_fileshow);
		 iv_fileshow = (ImageView) convertView.findViewById(R.id.iv_fileshow);
		 tv_fileshow_title = (TextView) convertView.findViewById(R.id.tv_fileshow_title);
		 tv_fileshow_size = (TextView) convertView.findViewById(R.id.tv_fileshow_size);
		
		 RubbishFileInfo rubbishFileInfo = getItem(position);
		
		boolean isClear = rubbishFileInfo.isSelect();
		
		tv_fileshow_title.setText(rubbishFileInfo.getSoftChinesename());
		tv_fileshow_size.setText(CommonUtil.getFileSize(rubbishFileInfo.getSize()));
		cb_fileshow.setChecked(isClear);
		cb_fileshow.setTag(position);
		
		cb_fileshow.setOnCheckedChangeListener(listener);
		iv_fileshow.setImageDrawable(rubbishFileInfo.getIcon());
		
		
		return convertView;
	}
	
	
		
	
	
	private OnCheckedChangeListener listener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			int position = (Integer) buttonView.getTag();
			getItem(position).setSelect(isChecked);
		}
	};
	
	

}
