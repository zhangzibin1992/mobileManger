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
	protected LayoutInflater layoutInflater;//��ȡ���������
	
	public ArrayList<TelclassInfo> arrayList = new ArrayList<TelclassInfo>();//����һ��������������б�
	
	public void addDataToMyadapter(TelclassInfo e){
		arrayList.add(e);//����һ���б�
	}
	
	public void addDataToMyadapterAll(ArrayList<TelclassInfo> list){
		arrayList.addAll(list);
	}
	
	public void updataMyadapter(){
		notifyDataSetChanged();//�����б�
	}
	
	public void delDataFromMyadapter(){//ɾ�������ӵ��б�
		arrayList.remove(arrayList.size()-1);
	}
	
	public void cleanAdapt(){//�����б�
	
		arrayList.clear();
	}
	
	
	public void delDataALL(){//һ�����
		//arrayList.removeAll(arrayList);
		arrayList.clear();
	}
	
	
	
	
	//private Context context;
	//						context��ȡ������
	public BaseMyadapter(Context context){
//		this.context=context;
		//layoutInflater=(LayoutInflater) context.getSystemService("layout_inflater");//��ȡ����������
		layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);//��ȡ����������
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();//�����б����
	}

	@Override
	public TelclassInfo getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList.get(position);		//��ȡ�б�ĽǱ�
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;	//��ȡ�б����ID
	}

	
	
	
	
	
	
}
