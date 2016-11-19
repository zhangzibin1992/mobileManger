package com.example.lib;

import android.util.Log;

public class UtilActivity {
	private static boolean isrun = false;
		public static void D(String msg){
			if(isrun){
			Log.d("DDD", msg);
		}
	}

}
