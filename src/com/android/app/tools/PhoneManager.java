package com.android.app.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

/**
 * ���������ֻ�ϵͳ�����ҵ���߼������ݴ���
 * 
 * @author yxchao ����3:12:30
 */
public class PhoneManager {

	private Context context;
	
//	private ActivityManager am;
	private TelephonyManager telManager;
	private ConnectivityManager connManager;
	private WifiManager wifiManager;

	private static PhoneManager systemManager;

	private PhoneManager(Context context) {
		this.context = context;
//		am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	}

	public static PhoneManager getPhoneManage(Context context) {
		if (systemManager == null) {
			systemManager = new PhoneManager(context);
		}
		return systemManager;
	}

	/** �豸Wifi���� */
	public String getPhoneWifiName() {
		WifiInfo info = wifiManager.getConnectionInfo();
		return info.getSSID() + "";
	}

	/** �豸Wifi��IP */
	public String getPhoneWifiIP() {
		WifiInfo info = wifiManager.getConnectionInfo();
		long ip = info.getIpAddress();
		return longToIP(ip);
	}

	/** �豸Wifi���ٶ� */
	public String getPhoneWifiSpeed() {
		WifiInfo info = wifiManager.getConnectionInfo();
		return info.getLinkSpeed() + "";
	}

	/** �豸Wifi��MAC */
	public String getPhoneWifiMac() {
		WifiInfo info = wifiManager.getConnectionInfo();
		return info.getMacAddress() + "";
	}

	private String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		// ����24λ��0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		sb.append(".");
		// ����1λ��0��Ȼ������8λ
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// ����8λ��0��Ȼ������16λ
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// ֱ������24λ
		sb.append(String.valueOf((longIp >>> 24)));
		return sb.toString();
	}

	/** �豸������������ (OFFLINE ? WIFI ? MOBILE) permission.ACCESS_NETWORK_STATE */
	public String getPhoneNetworkType() {
		NetworkInfo info = connManager.getActiveNetworkInfo();
		if (!isOnline(info)) {
			return "OFFLINE";
		}
		if (info != null) {
			return info.getTypeName();
		} else {
			return "OFFLINE";
		}
	}

	private boolean isOnline(NetworkInfo info) {
		if (info != null && info.isConnected()) {
			return true;
		}
		return false;
	}

	/** �豸�绰���� (�������ж����õ�����Ӫ�̽��ֻ�������д�뵽sim���еľ���) Permission: READ_PHONE_STATE */
	public String getPhoneNumber() {
		if(telManager.getLine1Number().equals("")){
			return "δ��ȡ����������";
		}
		return telManager.getLine1Number();
		
	}

	/** �豸��Ӫ������ (�й��ƶ����й���ͨ��) */
	public String getPhoneTelSimName() {
		return telManager.getSimOperatorName();
	}

	/** �豸���� permission.READ_PHONE_STATE */
	public String getPhoneIMEI() {
		// ����Ƿ���Ȩ��
		if (PackageManager.PERMISSION_GRANTED == context.getPackageManager().checkPermission(Manifest.permission.READ_PHONE_STATE, context.getPackageName())) {
			return telManager.getDeviceId();
		} else {
			return null;
		}
	}

	/** �豸ϵͳ�����汾 */
	public String getPhoneSystemBasebandVersion() {
		return Build.RADIO;
	}

	/** �豸ϵͳ�汾�� (4.1.2?) */
	public String getPhoneSystemVersion() {
		return Build.VERSION.RELEASE;
	}

	/** �豸ϵͳSDK�汾�� (16?) */
	public int getPhoneSystemVersionSDK() {
		return Build.VERSION.SDK_INT;
	}

	/** �豸���ð汾�� */
	public String getPhoneSystemVersionID() {
		// Build.DISPLAY
		return Build.ID;
	}

	/** �豸CPU�������� (Ʒ�ƣ�) */
	public String getPhoneCPUName() {
		return Build.CPU_ABI;
	}

	/** �豸Ʒ��(moto?) */
	public String getPhoneName1() {
		return Build.BRAND;
	}

	/** �豸������(moto?) */
	public String getPhoneName2() {
		return Build.MANUFACTURER;
	}

	/** �豸�ͺ�����(xt910) */
	public String getPhoneModelName() {
		// �������� PRODUCT
		return Build.MODEL;
	}

	/** �豸CPU���Ƶ�� */
	public String getPhoneCpuMaxFreq() {
		String result = "";
		ProcessBuilder cmd;
		try {
			String[] args = { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq" };
			cmd = new ProcessBuilder(args);
			Process process = cmd.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[24];
			while (in.read(re) != -1) {
				result = result + new String(re);
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			result = "N/A";
		}
		return result.trim();
	}

	/** �豸CPU��СƵ�� */
	public String getPhoneCpuMinFreq() {
		String result = "";
		ProcessBuilder cmd;
		try {
			String[] args = { "/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq" };
			cmd = new ProcessBuilder(args);
			Process process = cmd.start();
			InputStream in = process.getInputStream();
			byte[] re = new byte[24];
			while (in.read(re) != -1) {
				result = result + new String(re);
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			result = "N/A";
		}
		return result.trim();
	}

	/** �豸CPU��ǰƵ�� */
	public String getPhoneCpuCurrentFreq() {
		String result = "N/A";
		try {
			FileReader fr = new FileReader("/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq");
			BufferedReader br = new BufferedReader(fr);
			String text = br.readLine();
			result = text.trim();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/** �豸CPU���� */
	public String getPhoneCpuName() {
		try {
			FileReader fr = new FileReader("/proc/cpuinfo");
			BufferedReader br = new BufferedReader(fr);
			String text = br.readLine();
			String[] array = text.split(":\\s+", 2);
			return array[1];
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** �豸CPU���� */
	public int getPhoneCpuNumber() {
		class CpuFilter implements FileFilter {
			public boolean accept(File pathname) {
				if (Pattern.matches("cpu[0-9]", pathname.getName())) {
					return true;
				}
				return false;
			}
		}
		try {
			File dir = new File("/sys/devices/system/cpu/");
			File[] files = dir.listFiles(new CpuFilter());
			return files.length;
		} catch (Exception e) {
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * �жϵ�ǰ�ֻ��Ƿ���ROOTȨ��
	 * 
	 * @return
	 */
	public boolean isRoot() {
		boolean bool = false;

		try {
			if ((!new File("/system/bin/su").exists()) && (!new File("/system/xbin/su").exists())) {
				bool = false;
			} else {
				bool = true;
			}
		} catch (Exception e) {

		}
		return bool;
	}

	/**
	 * ��ȡ�ֻ��ֱ���
	 */
	public String getResolution() {
		String resolution = "";
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		resolution = metrics.widthPixels + "*" + metrics.heightPixels;
		return resolution;
	}

	/**
	 * ��ȡ��Ƭ���ֱ���
	 */
	public String getMaxPhotoSize() {
		String maxSize = "";
		Camera camera = Camera.open();
		Camera.Parameters parameters = camera.getParameters();
		List<Size> sizes = parameters.getSupportedPictureSizes();
		Size size = null;
		for (Size s : sizes) {
			if (size == null) {
				size = s;
			} else if (size.height * s.width < s.height * s.width) {
				size = s;
			}
		}
		maxSize = size.width + "*" + size.height;
		camera.release();
		return maxSize;
	}

	/**
	 * ��ȡ������ߴ�
	 */
	public String getCameraResolution() {
		String cameraResolution = "";
		Camera camera = Camera.open();
		Camera.Parameters parameters = camera.getParameters();
		List<Size> sizes = parameters.getSupportedPictureSizes();
		Size size = null;
		for (Size s : sizes) {
			if (size == null) {
				size = s;
			} else if (size.height * s.width < s.height * s.width) {
				size = s;
			}
		}
		cameraResolution = (size.width * size.height) / 10000 + "������";
		camera.release();
		return cameraResolution;
	}

	/**
	 * ��ȡ�����״̬
	 */
	public String getFlashMode() {
		String flashMode = "";
		Camera camera = Camera.open();
		Camera.Parameters parameters = camera.getParameters();
		flashMode = parameters.getFlashMode();
		camera.release();
		return flashMode;
	}

	/**
	 * ��ȡ�����ܶ�
	 */
	public float getPixDensity() {
		float density = 0;
		density = context.getResources().getDisplayMetrics().density;
		return density;
	}

	/**
	 * �ж��豸�Ƿ�֧�ֶ�㴥��
	 */
	public boolean isSupportMultiTouch() {
		PackageManager pm = context.getPackageManager();
		boolean isSupportMultiTouch = pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH);
		return isSupportMultiTouch;
	}

	/**
	 * ��ȡ��������״̬
	 */
	public String getBlueToothState() {
		BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
		if (bAdapter == null) {
			return "�豸��֧������";
		}
		int state = bAdapter.getState();
		switch (state) {
		case BluetoothAdapter.STATE_TURNING_OFF:
			return "�����ر���";
		case BluetoothAdapter.STATE_TURNING_ON:
			return "����������";
		case BluetoothAdapter.STATE_OFF:
			return "�����ر�";
		case BluetoothAdapter.STATE_ON:
			return "��������";
		}
		return "δ֪";
	}
}
