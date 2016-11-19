package com.zhuoxin.app.zhangzibin.entity;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

public class PhoneInfo {
	private String title;
	String text;
	Drawable icon;
	
	
	@Override
	public String toString() {
		return "PhoneInfo [title=" + title + ", text=" + text + ", icon="
				+ icon + "]";
	}
	public PhoneInfo(String title, String text, Drawable icon) {
		super();
		this.title = title;
		this.text = text;
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	
}
