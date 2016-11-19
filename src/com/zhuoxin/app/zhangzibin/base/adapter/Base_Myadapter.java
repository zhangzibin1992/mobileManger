package com.zhuoxin.app.zhangzibin.base.adapter;

import java.util.ArrayList;
import java.util.List;

import com.zhuoxin.app.zhangzibin.entity.AppInfo;

import android.content.Context;
import android.renderscript.Element.DataType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Base_Myadapter<E> extends BaseAdapter{
	public Context context;
	
	protected LayoutInflater layoutInflater;//获取布局填充器
	
	public ArrayList<E> arrayList = new ArrayList<E>();//声明一个集合用来存放列表
	
	public ArrayList<E> getDataList() {
		return arrayList;
	}
	
	public void addDataToMyadapter(E e){
		arrayList.add(e);//增加一个列表
	}
	
	public void addAllDataToMyadapter(List<E> e){
		arrayList.clear();
		arrayList.addAll(e);//增加一个列表
	}
	
	public void updataMyadapter(){
		notifyDataSetChanged();//更新列表
	}
	
	public void delDataFromMyadapter(){//删除新增加的列表
		arrayList.remove(arrayList.size()-1);
		
	}
	
	public void delDataALL(){//一键清空
		//arrayList.removeAll(arrayList);
		arrayList.clear();
	}
	//						context获取上下文
	public Base_Myadapter(Context context){
		this.context = context;
		//layoutInflater=(LayoutInflater) context.getSystemService("layout_inflater");//获取它的适配器
		layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);//获取它的适配器
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();//返回列表个数
	}

	@Override
	public E getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList.get(position);		//获取列表的角标
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;	//获取列表项的ID
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
