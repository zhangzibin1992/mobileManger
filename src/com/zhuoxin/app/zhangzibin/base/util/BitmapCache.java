package com.zhuoxin.app.zhangzibin.base.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Hashtable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
//经过软引用实现图片缓存防止内存溢出
public class BitmapCache {
	static private BitmapCache cache;
	//用于cache内容存储
	private Hashtable<Integer, MySoftRef> hashRefs;
	
	//垃圾 Reference队列   如果对象被回收则放在此引用存队列中
	private ReferenceQueue<Bitmap> q;
//是的每一个实例  都具有可识别的标识
	private class MySoftRef extends SoftReference<Bitmap> {
		private Integer _key = 0;

		public MySoftRef(Bitmap bmp, ReferenceQueue<Bitmap> q, int key) {
			super(bmp, q);
			_key = key;
		}
	}
	
	
	
	

	private BitmapCache() {
		hashRefs = new Hashtable<Integer, MySoftRef>();
		q = new ReferenceQueue<Bitmap>();
	}

	/**
	 * 用来取得缓存器实例
	 */
	public static BitmapCache getInstance() {
		if (cache == null) {
			cache = new BitmapCache();
		}
		return cache;
	}

	/** 
	   * 以软引用的方式对一个 Bitmap对象的实例进行引用并保存该引用 
	   */ 
	public  void addCacheBitmap(Bitmap bmp, Integer key) {
		cleanCache();// �����������
		MySoftRef ref = new MySoftRef(bmp, q, key);
		hashRefs.put(key, ref);
	}
	
	
	
	/** 
	   * 依据所指定的 drawable下的图片资源 ID号（可以根据自己的需要从网络或本地path下获取），重新获取相应 Bitmap对象的实例 
	   */ 
	public Bitmap getBitmap(int resId, Context context) {
		Bitmap bmp = null;
		// 缓存中是否有该 Bitmap实例的软引用，如果有，从软引用中取得。 
		if (hashRefs.containsKey(resId)) {
			MySoftRef ref = (MySoftRef) hashRefs.get(resId);
			bmp = (Bitmap) ref.get();
		}
		// 如果没有软引用，或者从软引用中得到的实例是 null，重新构建一个实例，
		// 并保存对这个新建实例的软引用 
		if (bmp == null) {
			// 传说 decodeStream直接调用 JNI>>nativeDecodeAsset()来完成   decode，
			// 无需再使用 java层的 createBitmap，从而节省了 java层的空间。
			bmp = BitmapFactory.decodeStream(context.getResources()
					.openRawResource(resId));
			this.addCacheBitmap(bmp, resId);
		}
		return bmp;
	}

	private void cleanCache() {
		MySoftRef ref = null;
		while ((ref = (MySoftRef) q.poll()) != null) {
			hashRefs.remove(ref._key);
		}
	}
	/** 
	* 清除 Cache内的全部内容 
	*/ 
	public void clearCache() {
		cleanCache();
		hashRefs.clear();
		System.gc();
		System.runFinalization();
	}
}
