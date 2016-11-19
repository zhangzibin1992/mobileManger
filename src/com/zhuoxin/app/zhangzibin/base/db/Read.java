package com.zhuoxin.app.zhangzibin.base.db;

import java.io.File;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.zhuoxin.app.zhangzibin.entity.TelclassInfo;
import com.zhuoxin.app.zhangzibin.entity.TelclassTable;

public class Read {
	public static File telfile;
	static{
		String sdfile = "data/data/com.example.ydym/";
		//每个手机都有data文件夹，如果没有内存卡，则会写入到机身的data的文件夹中
		File file = new File(sdfile);
		file.mkdirs();
		telfile = new File(sdfile, "commonnum.db");//commonnum.db文件名和数据库名字必须一样
	}

	public static boolean isFile(){
		File isfile = Read.telfile;
		if(!isfile.exists()||isfile.length()<=0){
			return false;
		}
		return true;
	}
	public static ArrayList<TelclassInfo> readlistdb() throws Exception {
		
		ArrayList<TelclassInfo> telinfo = new ArrayList<TelclassInfo>();
		SQLiteDatabase db = null;
		Cursor cursor = null;
		
		try {
			
			db = SQLiteDatabase.openOrCreateDatabase(telfile, null);
			cursor = db.rawQuery("select * from classlist", null);
			if(cursor.moveToFirst()){
				do {
					String name = cursor.getString(cursor.getColumnIndex("name"));
					int idx = cursor.getInt(cursor.getColumnIndex("idx"));
					TelclassInfo iff = new TelclassInfo(name, idx);
					telinfo.add(iff);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			cursor.close();
			db.close();
		}
		return telinfo;
	}
	
	public static ArrayList<TelclassTable> readlistTable(int idx) throws Exception{
		ArrayList<TelclassTable> table = new ArrayList<TelclassTable>();
		String sqls = "select*from table"+idx;
		SQLiteDatabase db = null;//操作数据库自带功能
		Cursor cursor = null;
		
		
		try {
			db = SQLiteDatabase.openOrCreateDatabase(telfile, null);
			cursor = db.rawQuery(sqls, null);
			if(cursor.moveToFirst()){
				do {
					String name = cursor.getString(cursor.getColumnIndex("name"));
					String number = cursor.getString(cursor.getColumnIndex("number"));
					TelclassTable iff2 = new TelclassTable(name, number);
					table.add(iff2);
				} while (cursor.moveToNext());
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			cursor.close();
			db.close();
		}
		return table;
	}
	

}
