package com.zhuoxin.app.zhangzibin.base.adapter;

import java.util.ArrayList;
import java.util.Collection;

import com.zhuoxin.app.zhangzibin.entity.TelclassInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class BaseMyadapter extends BaseAdapter{
	protected LayoutInflater layoutInflater;//获取布局填充器
	
	public ArrayList<TelclassInfo> arrayList = new ArrayList<TelclassInfo>();//声明一个集合用来存放列表
	
	public void addDataToMyadapter(TelclassInfo e){
		arrayList.add(e);//增加一个列表
	}
	
	public void addDataToMyadapterAll(ArrayList<TelclassInfo> list){
		arrayList.addAll(list);
	}
	
	public void updataMyadapter(){
		notifyDataSetChanged();//更新列表
	}
	
	public void delDataFromMyadapter(){//删除新增加的列表
		arrayList.remove(arrayList.size()-1);
	}
	
	public void cleanAdapt(){//清理列表
	
		arrayList.clear();
	}
	
	
	public void delDataALL(){//一键清空
		//arrayList.removeAll(arrayList);
		arrayList.clear();
	}
	
	
	
	
	//private Context context;
	//						context获取上下文
	public BaseMyadapter(Context context){
//		this.context=context;
		//layoutInflater=(LayoutInflater) context.getSystemService("layout_inflater");//获取它的适配器
		layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);//获取它的适配器
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();//返回列表个数
	}

	@Override
	public TelclassInfo getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList.get(position);		//获取列表的角标
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;	//获取列表项的ID
	}

	
	
	
	
	
	
}
