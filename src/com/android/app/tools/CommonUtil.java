package com.android.app.tools;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	
	
	public static String getStrTime(long filetime){
		if(filetime==0){
			return "δ֪";
		}
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");
		String ftime = sformat.format(new Date(filetime));
		return ftime;
		
	}
	
	
	public static String getStrTime(){
		SimpleDateFormat sformat = new SimpleDateFormat("yyyy��MM��dd�� hh:mm:ss");
		String ftime = sformat.format(System.currentTimeMillis());
		return ftime;
	}
	
	public static boolean equals(Object a,Object b){
		return (a==b)||(a==null ? false:a.equals(b));
	}
	
	//�ֽ�ת��Ϊ�ַ�������ȡ2λС��
	private static DecimalFormat decimalFormat = new DecimalFormat("#.00");
	
	public static String getFileSize(long filesize){
		
		StringBuffer buffer = new StringBuffer();
		if(filesize<1024){
			buffer.append(filesize);
			buffer.append(" B");
		}else if(filesize<(1024*1024)){
			buffer.append(decimalFormat.format((double) filesize/1024));
			buffer.append(" KB");
		}else if(filesize<(1024*1024*1024)){
			buffer.append(decimalFormat.format((double) filesize/(1024*1024)));
			buffer.append(" MB");
		}else{
			buffer.append(decimalFormat.format((double) filesize/(1024*1024*1024)));
			buffer.append(" GB");
		}
		return buffer.toString();
		
	}

}
