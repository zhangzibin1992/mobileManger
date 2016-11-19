package com.android.app.tools;

import java.util.ArrayList;
import java.util.List;

import com.zhuoxin.app.zhangzibin.entity.AppInfo;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
/**
 * 应用程序管理类
 * */
public class AppInfoManager {
	private Context context;
	private PackageManager packageManager;
	private ActivityManager activityManager;
	public AppInfoManager(Context context) {
		this.context = context;
		packageManager = context.getPackageManager();
		activityManager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
	}
	
	private ArrayList<AppInfo> allPackageInfos = new ArrayList<AppInfo>();
	private ArrayList<AppInfo> userPackageInfos = new ArrayList<AppInfo>();
	private ArrayList<AppInfo> systemPackageInfos = new ArrayList<AppInfo>();
	
	private static AppInfoManager appManager = null;
	
	public static AppInfoManager getAppInfoManager(Context context){
		if (appManager==null) {
			synchronized (context) {
				if (appManager==null) {
					appManager = new AppInfoManager(context);
				}
			}
		}
		return appManager;
	}
	
	/**所有软件*/
	public ArrayList<AppInfo> getAllPackageInfo(boolean isclear){
		if(isclear){
			loadAllActivityPackager();
		}
		return allPackageInfos;
	}
	
	/**用户软件*/
	public ArrayList<AppInfo> getUserPackageInfo(boolean isclear){
		if(isclear){
			loadAllActivityPackager();
			userPackageInfos.clear();
		}
		for (AppInfo appInfo : allPackageInfos) {
			if((appInfo.getPackageInfo().applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)!=0){
			}else{
				userPackageInfos.add(appInfo);
			}
		}
		return userPackageInfos;
	}
	
	/**系统软件*/
	public ArrayList<AppInfo> getSystemPackageInfo(boolean isclear){
		if(isclear){
			loadAllActivityPackager();
			userPackageInfos.clear();
		}
		for (AppInfo appInfo : allPackageInfos) {
			if((appInfo.getPackageInfo().applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)!=0){
				systemPackageInfos.add(appInfo);
			}
		}
		return systemPackageInfos;
	}
	
	
	private void loadAllActivityPackager() {
		//获取安卓系统中应用程序的信息
		List<PackageInfo> infos = packageManager.getInstalledPackages(PackageManager.GET_ACTIVITIES | PackageManager.GET_UNINSTALLED_PACKAGES);
		
		allPackageInfos.clear();
		for (PackageInfo packageInfo : infos) {
		allPackageInfos.add(new AppInfo(packageInfo)); 
		} 
	}
	

}
