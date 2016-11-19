package com.android.app.tools;

import com.example.ydym.R;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.RemoteViews;

public class NotificationUtil {

	private static NotificationManager manager;
	private static Notification notification;
	private static boolean isRunning=false;//判断点击状态，自选
	public static final int NOTIFI_APPICON_ID = 1;

	
	//点击状态
	public static boolean isOpenNotification(Context context) {
		SharedPreferences preferences = context.getSharedPreferences("notifi", Context.MODE_PRIVATE);
		return preferences.getBoolean("open", true);
	}

	//设置点击状态
	public static void setOpenNotification(Context context, boolean open) {
		SharedPreferences preferences = context.getSharedPreferences("notifi", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putBoolean("open", open);
		editor.commit();
	}

	//取消消息通知
	public static void cancelAppIconNotification(Context context) {
		if (manager == null) {
			manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		}
		manager.cancel(NOTIFI_APPICON_ID);
		isRunning=false;
	}

	
	//添加消息通知
	public static void showAppIconNotification(Context context) {
		if (notification == null) {
			notification = new Notification();
		}
		notification.flags = Notification.FLAG_NO_CLEAR;
		notification.icon = R.drawable.ic_launcher;
		notification.tickerText = "新通知";
		notification.when = System.currentTimeMillis();
		RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.layout_notification);
		notification.contentView = contentView;
		Intent intent = new Intent("android.intent.action.shoujiguanjia");
		PendingIntent contentIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		notification.contentIntent = contentIntent;
		if (manager == null) {
			manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		}
		manager.notify(NOTIFI_APPICON_ID, notification);
		isRunning=true;
	}

	public static boolean isRunning() {
		return isRunning;
	}

	public static void setRunning(boolean isRunning) {
		NotificationUtil.isRunning = isRunning;
	}
}
