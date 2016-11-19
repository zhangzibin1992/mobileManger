package com.zhuoxin.app.zhangzibin.base.util;

import android.content.Context;
import android.widget.Toast;


public class UtilToast {
	private static boolean isrun = false;
	public static void createToast(Context context,String text, int duration) {
		if(isrun){
			Toast.makeText(context, text, duration).show();
		}
	}
}