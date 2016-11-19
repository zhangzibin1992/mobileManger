package com.zhuoxin.app.zhangzibin.base.db;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.Context;

public class CopyDataToFill {
	public static void CopyData(Context context, String path, File toFile) throws Exception{
		InputStream inputStream = null;
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		try {
			
			inputStream = context.getAssets().open(path);
			bufferedInputStream = new BufferedInputStream(inputStream);
			bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(toFile, false));
			
			int len = 0;
			byte[] array = new byte[1024];
			while((len = bufferedInputStream.read(array))!=-1){
				
				bufferedOutputStream.write(array, 0, len);
			}
			bufferedOutputStream.flush();
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			bufferedInputStream.close();
			bufferedOutputStream.close();
			inputStream.close();
		}
		
		
		
		
	}
}
