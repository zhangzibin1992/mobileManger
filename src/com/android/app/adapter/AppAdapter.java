package com.android.app.adapter;

import junit.runner.Version;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ydym.R;
import com.zhuoxin.app.zhangzibin.base.adapter.Base_Myadapter;
import com.zhuoxin.app.zhangzibin.base.util.BitmapCache;
import com.zhuoxin.app.zhangzibin.base.util.BitmapUtil;
import com.zhuoxin.app.zhangzibin.base.util.BitmapUtil.SizeMessage;
import com.zhuoxin.app.zhangzibin.entity.AppInfo;

public class AppAdapter extends Base_Myadapter<AppInfo>{
	
	
	/**
	 * @author ’≈Ë˜±Ú
	 * 
	 * º”‘ÿÕº∆¨
	 * œ¬
	 * 
	 * 
	 * */
	private BitmapCache bitmapCache;
	private Bitmap defIconBitmap;
	private boolean isLoading;
	
	
	public boolean isLoading(boolean isLoading){
		return isLoading;
	}
	
	public  Bitmap mBitmap(){
		if (isLoading) {
			return defIconBitmap;
			
		}
		return defIconBitmap;
	}
	

	public AppAdapter(Context context) {
		super(context);
		SizeMessage sizeMessage = new SizeMessage(context, false, 50, 50);
		defIconBitmap = BitmapUtil.loadBitmap(R.drawable.pro_xuanzhuan, sizeMessage);
		bitmapCache=BitmapCache.getInstance();
		mBitmap();
	}
	
	/**
	 * …œ
	 * º”‘ÿÕº∆¨*/

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = layoutInflater.inflate(R.layout.app_show_listview, null);
		
		CheckBox cb_listview = (CheckBox) view.findViewById(R.id.cb_listview);
		
		ImageView img_listview = (ImageView) view.findViewById(R.id.img_listview);
		TextView tv_listview_title = (TextView) view.findViewById(R.id.tv_listview_title);
		TextView tv_listview_pack = (TextView) view.findViewById(R.id.tv_listview_pack);
		TextView tv_listview_version = (TextView) view.findViewById(R.id.tv_listview_version);
		
		AppInfo appInfo = getItem(position);
		
		String title = appInfo.getPackageInfo().applicationInfo.loadLabel(context.getPackageManager()).toString();
		
		String pack = appInfo.getPackageInfo().packageName;
		String version = appInfo.getPackageInfo().versionName;
		boolean isclear = appInfo.isClear();
		
		Bitmap bitmap=( (BitmapDrawable) appInfo.getPackageInfo().applicationInfo.loadIcon(context.getPackageManager())).getBitmap();
		bitmapCache.addCacheBitmap(bitmap, position);
		img_listview.setImageBitmap(bitmapCache.getBitmap(position, context));
		
		tv_listview_title.setText(title);
		tv_listview_pack.setText(pack);
		tv_listview_version.setText(version);
		cb_listview.setOnCheckedChangeListener(checkedChangeListener);
		cb_listview.setTag(position);
		cb_listview.setChecked(isclear);
		return view;
	}
	
	private CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
		@Override public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			int position = (Integer) buttonView.getTag();
			getItem(position).setClear(isChecked);
			} 
		};

}
