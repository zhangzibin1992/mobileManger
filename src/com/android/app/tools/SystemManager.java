package com.android.app.tools;

import android.os.Build;

public class SystemManager {
	public static String getPhoneName() {
		return Build.BRAND;
	}
	
	public static String getPhoneModelName() {
		// �������� PRODUCT
		return Build.MODEL+" Android��" + getPhoneSystemVersion();
		}
	
	public static String getPhoneSystemVersion(){
		return Build.VERSION.RELEASE;
	}
	
}

