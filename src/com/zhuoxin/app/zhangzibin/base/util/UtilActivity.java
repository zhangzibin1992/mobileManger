package com.zhuoxin.app.zhangzibin.base.util;

import android.util.Log;

public class UtilActivity {
	private static boolean isrun = true;
		public static void D(String msg){
			if(isrun){
			Log.d("DDD", msg);
		}
	}

}
