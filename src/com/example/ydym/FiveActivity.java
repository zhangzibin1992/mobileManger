package com.example.ydym;

import java.util.ArrayList;

import com.zhuoxin.app.zhangzibin.adapter.HomeAdapt;
import com.zhuoxin.app.zhangzibin.base.db.Read;
import com.zhuoxin.app.zhangzibin.base.util.UtilActivity;
import com.zhuoxin.app.zhangzibin.entity.TelclassTable;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

public class FiveActivity extends Activity {
	private ListView listView2;
	private int idx;
	private HomeAdapt adapt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_five);
		
		Intent intent = getIntent();
		idx = intent.getIntExtra("key", 0);
		listView2 = (ListView) findViewById(R.id.listView_home);
		adapt = new HomeAdapt(this);
		listView2.setAdapter(adapt);
		listView2.setOnItemClickListener(listener);
	}
	
	
	public	OnItemClickListener listener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			//String name = adapt.getItem(position).name;
			String number = adapt.getItem(position).number;
			call(number);
		}
		};
	
		
		protected void call(final String number) {
			AlertDialog.Builder builder = new Builder(this);
			builder.setTitle("警告");
			builder.setMessage("是否拨打该电话");
			builder.setPositiveButton("否", null);
			builder.setNegativeButton("是", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(Intent.ACTION_CALL);
					intent.setData(Uri.parse("tel://" + number));
					startActivity(intent);
					
				}
			});
			builder.show();
			
		}
	
	
	
	
		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			
			adapt.cleanAdapt();
			try {
				ArrayList<TelclassTable> list = Read.readlistTable(idx);
				//Log.d("DDD", "list.size======="+list.size());
				adapt.addDataToMyadapterAll(list);
				
			} catch (Exception e) {
				UtilActivity.D("Read.readlistdb()获取异常");
			}
			adapt.updataMyadapter();
		}





		
	

}
