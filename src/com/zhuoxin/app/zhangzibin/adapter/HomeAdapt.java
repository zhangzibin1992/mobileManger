package com.zhuoxin.app.zhangzibin.adapter;

import java.util.ArrayList;

import com.example.ydym.R;
import com.zhuoxin.app.zhangzibin.entity.TelclassInfo;
import com.zhuoxin.app.zhangzibin.entity.TelclassTable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HomeAdapt extends BaseAdapter{
protected LayoutInflater layoutInflater;//获取布局填充器
	public ArrayList<TelclassTable> arrayList2 = new ArrayList<TelclassTable>();//声明一个集合用来存放列表
	
	public void addDataToMyadapter(TelclassTable e){
		arrayList2.add(e);//增加一个列表
	}
	
	public void addDataToMyadapterAll(ArrayList<TelclassTable> list){
		arrayList2.addAll(list);
	}
	
	public void updataMyadapter(){
		notifyDataSetChanged();//更新列表
	}
	
	public void delDataFromMyadapter(){//删除新增加的列表
		arrayList2.remove(arrayList2.size()-1);
	}
	
	public void cleanAdapt(){//清理列表
		arrayList2.clear();
		
	}
	
	
	public void delDataALL(){//一键清空
		//arrayList.removeAll(arrayList);
		arrayList2.clear();
	}
	
	
	
	
	
	//						context获取上下文
	public HomeAdapt(Context context){
		//layoutInflater=(LayoutInflater) context.getSystemService("layout_inflater");//获取它的适配器
		layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);//获取它的适配器
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList2.size();//返回列表个数
	}

	@Override
	public TelclassTable getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList2.get(position);		//获取列表的角标
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;	//获取列表项的ID
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = layoutInflater.inflate(R.layout.inflact_h, null);
		TextView name = (TextView) view.findViewById(R.id.tv_inflacth1);
		TextView number = (TextView) view.findViewById(R.id.tv_inflacth2);
		TelclassTable text = getItem(position);
		name.setText(text.name);
		number.setText(text.number);
		return view;
	}
	
}
