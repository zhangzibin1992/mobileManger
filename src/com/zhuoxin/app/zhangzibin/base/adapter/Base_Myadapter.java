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
	
	protected LayoutInflater layoutInflater;//��ȡ���������
	
	public ArrayList<E> arrayList = new ArrayList<E>();//����һ��������������б�
	
	public ArrayList<E> getDataList() {
		return arrayList;
	}
	
	public void addDataToMyadapter(E e){
		arrayList.add(e);//����һ���б�
	}
	
	public void addAllDataToMyadapter(List<E> e){
		arrayList.clear();
		arrayList.addAll(e);//����һ���б�
	}
	
	public void updataMyadapter(){
		notifyDataSetChanged();//�����б�
	}
	
	public void delDataFromMyadapter(){//ɾ�������ӵ��б�
		arrayList.remove(arrayList.size()-1);
		
	}
	
	public void delDataALL(){//һ�����
		//arrayList.removeAll(arrayList);
		arrayList.clear();
	}
	//						context��ȡ������
	public Base_Myadapter(Context context){
		this.context = context;
		//layoutInflater=(LayoutInflater) context.getSystemService("layout_inflater");//��ȡ����������
		layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);//��ȡ����������
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();//�����б����
	}

	@Override
	public E getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList.get(position);		//��ȡ�б�ĽǱ�
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;	//��ȡ�б����ID
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
