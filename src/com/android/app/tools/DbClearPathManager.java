package com.android.app.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.example.ydym.R;
import com.zhuoxin.app.zhangzibin.entity.RubbishFileInfo;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Environment;

public class DbClearPathManager {
	private static final String FILE_NAME = "clearpath.db"; 
	private static final String PACKAGE_NAME = "com.example.ydym"; 
	private static final String FILE_PATH = "/data" + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME;

	private static ArrayList<RubbishFileInfo> softdetailInfos;

	public static ArrayList<RubbishFileInfo> getPhoneRubbishfile(Context context) {
		if (softdetailInfos == null) {
			softdetailInfos = readSoftdetailTable(); 
		}
		ArrayList<RubbishFileInfo> phontSoftdetailInfos = new ArrayList<RubbishFileInfo>();
		for (RubbishFileInfo info : softdetailInfos) {
			File file = new File(info.getFilepath());
			if (file.exists()) {
				Drawable icon = null;
				try {
					icon = context.getPackageManager().getApplicationIcon(info.getApkname());
				} catch (NameNotFoundException e) {
					icon = context.getResources().getDrawable(R.drawable.icon_file);
				}
				info.setIcon(icon);
				phontSoftdetailInfos.add(info);
			}
		}
		return phontSoftdetailInfos;
	}

	private static ArrayList<RubbishFileInfo> readSoftdetailTable() {
		ArrayList<RubbishFileInfo> softdetailInfos = new ArrayList<RubbishFileInfo>();
		String path = FILE_PATH + "/" + FILE_NAME;
		SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(path, null);
		String sql = "select * from softdetail"; 
		Cursor cursor = database.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			do {
				int _id = cursor.getInt(cursor.getColumnIndex("_id"));
				String softChinesename = cursor.getString(cursor.getColumnIndex("softChinesename"));
				String softEnglishname = cursor.getString(cursor.getColumnIndex("softEnglishname"));
				String apkname = cursor.getString(cursor.getColumnIndex("apkname"));
				String filepath = cursor.getString(cursor.getColumnIndex("filepath"));
				filepath = Environment.getExternalStorageDirectory().getPath() + filepath;
				RubbishFileInfo info = new RubbishFileInfo(_id, softChinesename, softEnglishname, apkname, filepath);
				softdetailInfos.add(info);
			} while (cursor.moveToNext());
		}
		cursor.close();
		database.close();
		return softdetailInfos;
	}

	public static void readUpdateDB(InputStream path) {
		File toFile = new File(FILE_PATH + "/" + FILE_NAME);
		if (toFile.exists()) {
			return;
		}
		try {
			BufferedInputStream buffInputStream = new BufferedInputStream(path);
			FileOutputStream outputStream = new FileOutputStream(toFile, false);
			BufferedOutputStream buffOutputStream = new BufferedOutputStream(outputStream);

			int len = 0;
			byte[] buff = new byte[5 * 1024];
			while ((len = buffInputStream.read(buff)) != -1) {
				buffOutputStream.write(buff, 0, len);
			}
			buffOutputStream.flush();
			buffOutputStream.close();
			buffInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
