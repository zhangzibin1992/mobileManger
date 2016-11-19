package com.zhuoxin.app.zhangzibin.base.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;


public class BitmapUtil {
	public static Bitmap loadBitmap(String pathName, SizeMessage sizeMessage) {
		// 锟斤拷锟截碉拷图锟斤拷目锟斤拷锟叫�
		int targetw = sizeMessage.getW();
		int targeth = sizeMessage.getH();
		Context context = sizeMessage.getContext();
		Options options = new Options();
		options.inJustDecodeBounds = true; // 锟斤拷"锟竭界处锟斤拷"
		BitmapFactory.decodeFile(pathName, options);
		int resw = options.outWidth;
		int resh = options.outHeight;
		if (resw <= targetw && resh <= targeth) {
			options.inSampleSize = 1; // 锟斤拷锟矫硷拷锟斤拷时锟斤拷锟斤拷源锟斤拷锟斤拷
		}
		// 锟斤拷锟斤拷锟斤拷锟�
		else {
			int scalew = resw / targetw;
			int scaleh = resh / targeth;
			int scale = scalew > scaleh ? scalew : scaleh;
			options.inSampleSize = scale;// 锟斤拷锟矫硷拷锟斤拷时锟斤拷锟斤拷源锟斤拷锟斤拷
		}
		options.inJustDecodeBounds = false;// 锟截憋拷"锟竭界处锟斤拷"
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, options);
		return bitmap;
	}
	public static Bitmap loadBitmap(int resID, SizeMessage sizeMessage) {
		// 锟斤拷锟截碉拷图锟斤拷目锟斤拷锟叫�
		int targetw = sizeMessage.getW();
		int targeth = sizeMessage.getH();
		Context context = sizeMessage.getContext();
		Options options = new Options();
		options.inJustDecodeBounds = true; // 锟斤拷"锟竭界处锟斤拷"
		BitmapFactory.decodeResource(context.getResources(), resID, options);
		int resw = options.outWidth;
		int resh = options.outHeight;
		if (resw <= targetw && resh <= targeth) {
			options.inSampleSize = 1; // 锟斤拷锟矫硷拷锟斤拷时锟斤拷锟斤拷源锟斤拷锟斤拷
		}
		// 锟斤拷锟斤拷锟斤拷锟�
		else {
			int scalew = resw / targetw;
			int scaleh = resh / targeth;
			int scale = scalew > scaleh ? scalew : scaleh;
			options.inSampleSize = scale;// 锟斤拷锟矫硷拷锟斤拷时锟斤拷锟斤拷源锟斤拷锟斤拷
		}
		options.inJustDecodeBounds = false;// 锟截憋拷"锟竭界处锟斤拷"
		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resID, options);
		return bitmap;
	}

	public static class SizeMessage {
		private int w;
		private int h;
		private Context context;

		/**
		 * 锟斤拷小锟斤拷息
		 * 
		 * @param context
		 * @param isPx
		 *            锟角凤拷为锟斤拷锟截碉拷位
		 * @param w
		 * @param h
		 */
		public SizeMessage(Context context, boolean isPx, int w, int h) {
			this.context = context;
			if (!isPx) {
				w = DeviceUtil.dp2px(context, w);
				h = DeviceUtil.dp2px(context, h);
			}
			this.w = w;
			this.h = h;
		}

		public Context getContext() {
			return context;
		}

		public int getW() {
			return w;
		}

		public void setW(int w) {
			this.w = w;
		}

		public int getH() {
			return h;
		}

		public void setH(int h) {
			this.h = h;
		}
	}
}
