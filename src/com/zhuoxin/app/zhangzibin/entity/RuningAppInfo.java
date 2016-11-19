package com.zhuoxin.app.zhangzibin.entity;

import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;

public class RuningAppInfo {
	private PackageInfo packageInfo;
	
	private String packageName; //安装包包名
	private Drawable icon;//图标
	private String lableName;//系统包包名
	private long size;//应用大小
	private boolean isClear;//是否需要清除
	private boolean isSystem;//是否是系统应用
	public RuningAppInfo(String packageName, Drawable icon, String lableName,
			long size) {
		super();
		this.packageName = packageName;
		this.icon = icon;
		this.lableName = lableName;
		this.size = size;
		
		this.isClear = false;
		this.isSystem = false;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Drawable getIcon() {
		return icon;
	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public String getLableName() {
		return lableName;
	}
	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public boolean isClear() {
		return isClear;
	}
	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}
	public boolean isSystem() {
		return isSystem;
	}
	public void setSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}
	public PackageInfo getPackageInfo() {
		return packageInfo;
	}
	public void setPackageInfo(PackageInfo packageInfo) {
		this.packageInfo = packageInfo;
	}

}
