package com.example.ydym;

import java.util.ArrayList;

import com.zhuoxin.app.zhangzibin.adapter.Myadapter;
import com.zhuoxin.app.zhangzibin.base.db.CopyDataToFill;
import com.zhuoxin.app.zhangzibin.base.db.Read;
import com.zhuoxin.app.zhangzibin.base.util.UtilActivity;
import com.zhuoxin.app.zhangzibin.entity.TelclassInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FourActivity extends Activity {
	private ListView listView;
	private Myadapter myadapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_four);
		
		listView = (ListView) findViewById(R.id.listView_main);
		myadapter = new Myadapter(this);
		listView.setAdapter(myadapter);
		listView.setOnItemClickListener(listener);
		
	}
	
		@Override
		protected void onResume() {
			super.onResume();
			istrue();
			myadapter.cleanAdapt();
			myadapter.addDataToMyadapter(new TelclassInfo("本地电话", 0));
			try {
				
				ArrayList<TelclassInfo> list = Read.readlistdb();
				myadapter.addDataToMyadapterAll(list);
				
			} catch (Exception e) {
				UtilActivity.D("Read.readlistdb()获取异常");
			}
			myadapter.updataMyadapter();
		}
			
		public void istrue(){
			
			if(!Read.isFile()){
				try {
					CopyDataToFill.CopyData(getApplicationContext(), "db/commonnum.db", Read.telfile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		public OnItemClickListener listener = new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TelclassInfo info = myadapter.getItem(position);
				if(info.idx==0){
					Intent intent = new Intent();
					intent.setAction(Intent.ACTION_DIAL);
					startActivity(intent);
				}else{
				Intent intent = new Intent(FourActivity.this, FiveActivity.class);
				intent.putExtra("key", info.idx);
				startActivity(intent);
				}
			}
		};

}
