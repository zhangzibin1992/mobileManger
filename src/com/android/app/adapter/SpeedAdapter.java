package com.android.app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.app.tools.CommonUtil;
import com.example.ydym.R;
import com.zhuoxin.app.zhangzibin.base.adapter.Base_Myadapter;
import com.zhuoxin.app.zhangzibin.base.util.BitmapCache;
import com.zhuoxin.app.zhangzibin.base.util.BitmapUtil;
import com.zhuoxin.app.zhangzibin.base.util.BitmapUtil.SizeMessage;
import com.zhuoxin.app.zhangzibin.entity.RuningAppInfo;

public class SpeedAdapter extends Base_Myadapter<RuningAppInfo>{
	
	private int state = 0; //显示用户进程
	public static final int STATE_SHOW_USER = 0;//显示用户进程
	public static final int STATE_SHOW_ALL = 1;//显示全部进程
	public static final int STATE_SHOW_SYS = 2;//显示系统进程
	public int getState() {
		return state;
		}
		public void setState(int state) {
		this.state = state;
		}
	

	private Context context;
	public SpeedAdapter(Context context) {
		super(context);
		this.context = context;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		/*if(convertView==null){
			convertView = layoutInflater.inflate(R.layout.listview_layout02, null);
		}*/
		View view = layoutInflater.inflate(R.layout.listview_layout02, null);
		CheckBox ckb_select = (CheckBox) view.findViewById(R.id.ckb_select);
		ImageView igv_icon = (ImageView) view.findViewById(R.id.igv_icon);
		TextView tv_biaoti = (TextView) view.findViewById(R.id.tv_biaoti);
		TextView tv_size = (TextView) view.findViewById(R.id.tv_size);
		TextView tv_jincheng = (TextView) view.findViewById(R.id.tv_jincheng);
		
		ckb_select.setOnCheckedChangeListener(changeListener);
		RuningAppInfo info = getItem(position);
		ckb_select.setTag(position);
		
		boolean isclear = info.isClear();
		ckb_select.setChecked(isclear);
		
		igv_icon.setImageDrawable(info.getIcon());
		tv_biaoti.setText(info.getLableName());
		tv_size.setText("内存"+CommonUtil.getFileSize(getItem(position).getSize()));
		tv_jincheng.setText(info.isSystem()?"系统进程":"");
		
		return view;
	}
	
	private OnCheckedChangeListener changeListener = new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			int position = (Integer) buttonView.getTag();
			getItem(position).setClear(isChecked);
		}
	};
	

}
