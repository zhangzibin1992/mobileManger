package com.zhuoxin.app.zhangzibin.entity;

import android.content.pm.PackageInfo;

public class AppInfo {
	private PackageInfo packageInfo;
	private boolean isclear;
	public AppInfo(PackageInfo packageInfo, boolean isclear) {
		super();
		this.packageInfo = packageInfo;
		this.isclear = isclear;
	}
	public AppInfo(PackageInfo packageInfo) {
		super();
		this.packageInfo = packageInfo;
		isclear=false;
	}
	
	public PackageInfo getPackageInfo() {
		return packageInfo;
	}
	public void setPackageInfo(PackageInfo packageInfo) {
		this.packageInfo = packageInfo;
	}
	public boolean isClear() {
		return isclear;
	}
	public void setClear(boolean isclear) {
		this.isclear = isclear;
	}
	

}
