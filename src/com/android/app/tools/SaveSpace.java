package com.android.app.tools;

import java.util.Map;

import android.os.Environment;
public class SaveSpace {
	//��ȡ���ô���·��
	public static String getInSDPath(){
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}else{
			return null;
		}
	}
	
	//��ȡ���ô���·��
	public static String getOutSDPath(){
		Map<String, String> map = System.getenv();
		if(map.containsKey("SECONDARY_STORAGE")){
			String paths = map.get("SECONDARY_STORAGE");
			String[] path = paths.split(":");
			if(path==null||path.length<=0){
				return null;
			}
			return path[0];
		}
		return null;
		
	}
	
	
}
