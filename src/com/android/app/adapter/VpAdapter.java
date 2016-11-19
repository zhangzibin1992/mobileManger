package com.android.app.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class VpAdapter extends PagerAdapter{
	private ArrayList<View> arrayview = new ArrayList<View>();
	private Context context;
	public VpAdapter(Context context) {
		this.context = context;
	}
	
	public void addViewToAdapter(View view){
		arrayview.add(view);
	}
	
	public ArrayList<View> getvp(){
		return arrayview;
		
	}
	
	
	public void delData(){
		arrayview.clear();
	}
	
	public void updata(){
		notifyDataSetChanged();
	}
	
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		View view = arrayview.get(position);
		container.removeView(view);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View view = arrayview.get(position);
		container.addView(view);
		return view;
	}
	
	@Override
	public int getCount() {
		return arrayview.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
