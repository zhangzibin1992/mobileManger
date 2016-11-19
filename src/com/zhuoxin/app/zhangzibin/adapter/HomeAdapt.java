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
protected LayoutInflater layoutInflater;//��ȡ���������
	public ArrayList<TelclassTable> arrayList2 = new ArrayList<TelclassTable>();//����һ��������������б�
	
	public void addDataToMyadapter(TelclassTable e){
		arrayList2.add(e);//����һ���б�
	}
	
	public void addDataToMyadapterAll(ArrayList<TelclassTable> list){
		arrayList2.addAll(list);
	}
	
	public void updataMyadapter(){
		notifyDataSetChanged();//�����б�
	}
	
	public void delDataFromMyadapter(){//ɾ�������ӵ��б�
		arrayList2.remove(arrayList2.size()-1);
	}
	
	public void cleanAdapt(){//�����б�
		arrayList2.clear();
		
	}
	
	
	public void delDataALL(){//һ�����
		//arrayList.removeAll(arrayList);
		arrayList2.clear();
	}
	
	
	
	
	
	//						context��ȡ������
	public HomeAdapt(Context context){
		//layoutInflater=(LayoutInflater) context.getSystemService("layout_inflater");//��ȡ����������
		layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);//��ȡ����������
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList2.size();//�����б����
	}

	@Override
	public TelclassTable getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList2.get(position);		//��ȡ�б�ĽǱ�
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;	//��ȡ�б����ID
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
