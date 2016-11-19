package com.android.app.tools;

import java.io.File;
import java.util.ArrayList;

import com.zhuoxin.app.zhangzibin.entity.FileInfo;


/**
 * �ļ�������<br>
 * ��Ҫ����ļ���С�Ķ�ȡ�������Լ�ɾ��
 * @author Administrator
 *
 */

public class FileManager {
	/** ���ô洢��Ŀ¼(����Ϊnull) */
	public static File inSdcardDir = null;
	/** ���ô洢��Ŀ¼(����Ϊnull) */
	public static File outSdcardDir = null;

	static {
		// ������ֻ�����sdcard��·��,��������Fielʵ����(��ֹFile file = new File(null))
		if (MemoryManager.getPhoneInSDCardPath() != null) {
			inSdcardDir = null;
			inSdcardDir = new File(MemoryManager.getPhoneInSDCardPath());
		}
		// ������ֻ�����sdcard��·��,��������Fielʵ����(��ֹFile file = new File(null))
		if (MemoryManager.getPhoneOutSDCardPath() != null) {
			outSdcardDir = null;
			outSdcardDir = new File(MemoryManager.getPhoneOutSDCardPath());
		}
	}

	// ����(��̬)--------------------------------------------------------------------------------------
	private FileManager() {
	}

	private static FileManager fileManager = new FileManager();
	/**����ģʽ*/
	public static FileManager getFileManager() {
		return fileManager;
	}

	// ��ֹ�����Ŀ���------------------------------------------------------------------------------------

	/** �Ƿ�ֹͣ���� */
	private boolean isStopSearch = false;
	/**
	 * {@link #isStopSearch}
	 * @return
	 */
	public boolean isStopSearch() {
		return isStopSearch;
	}
	/**
	 * {@link #isStopSearch}
	 * @return
	 */
	public void setStopSearch(boolean isStopSearch) {
		this.isStopSearch = isStopSearch;
	}

	// �ļ�����-----------------------------------------------------------------------
	/** �����ļ�(��Ŀ¼)���� */
	private ArrayList<FileInfo> anyFileList = new ArrayList<FileInfo>();
	private long anyFileSize; // �����ļ��ܴ�С(�����������У�ʵʱ���ӣ������ܴ�С)
	/** �ĵ��ļ����� */
	private ArrayList<FileInfo> txtFileList = new ArrayList<FileInfo>();
	private long txtFileSize; // �ĵ��ļ��ܴ�С(ͬ��)
	/** ��Ƶ�ļ����� */
	private ArrayList<FileInfo> videoFileList = new ArrayList<FileInfo>();
	private long videoFileSize; // ��Ƶ�ļ��ܴ�С(ͬ��)
	/** �����ļ����� */
	private ArrayList<FileInfo> audioFileList = new ArrayList<FileInfo>();
	private long audioFileSize; // �����ļ��ܴ�С(ͬ��)
	/** ͼ���ļ����� */
	private ArrayList<FileInfo> imageFileList = new ArrayList<FileInfo>();
	private long imageFileSize; // ͼ���ļ��ܴ�С(ͬ��)
	/** ZIP�ļ����� */
	private ArrayList<FileInfo> zipFileList = new ArrayList<FileInfo>();
	private long zipFileSize; // ZIP�ļ��ܴ�С(ͬ��)
	/** APK�ļ����� */
	private ArrayList<FileInfo> apkFileList = new ArrayList<FileInfo>();
	private long apkFileSize; // APK�ļ��ܴ�С(ͬ��)

	// ��ʼ����ر���(��ÿ�����¿�ʼ����ǰ,��searchSDCardFile())------------------------------------------------
	private void initData() {
		isStopSearch = false;
		anyFileSize = 0;
		txtFileSize = 0;
		videoFileSize = 0;
		audioFileSize = 0;
		imageFileSize = 0;
		zipFileSize = 0;
		apkFileSize = 0;
		anyFileList.clear();
		txtFileList.clear();
		videoFileList.clear();
		audioFileList.clear();
		imageFileList.clear();
		zipFileList.clear();
		apkFileList.clear();
	}

	/** �����洢��Ŀ¼�µ������ļ�,���ʵʱ������ {@link #anyFileList}�� */
	public void searchSDCardFile() {
		if (anyFileList == null || anyFileList.size() <= 0) {
			initData();
			searchFile(inSdcardDir, false); // ����false���, �������㷴������
			searchFile(outSdcardDir, true); // ����true���, �����㷴������
		} else {
			// ֱ�ӻص����쳣����
			callbackSearchFileListenerEnd(false);
		}
	}

	/** (δ�õ�)����ָ��Ŀ¼�µ������ļ�,���ʵʱ������ {@link #anyFileList}�� */
	public void searchFile(File file) {
		initData();
		searchFile(file, true);
	}

	/** (δ�õ�)�������ô洢��Ŀ¼�µ������ļ�,���ʵʱ������ {@link #anyFileList}�� */
	public void searchINSDCardFile() {
		initData();
		searchFile(inSdcardDir, true);
	}

	/** (δ�õ�)�������ô洢��Ŀ¼�µ������ļ�,���ʵʱ������ {@link #anyFileList}�� */
	public void searchOUTSDCardFile() {
		initData();
		searchFile(outSdcardDir, true);
	}

	/**
	 * �ݹ����������ļ�
	 * 
	 * @param file
	 * @param flag
	 *            Ϊ�ݹ�����ж�׼��,ÿ�ε��÷���ջ�ڵ�flagֵ����һ��,��һ�ε��õĽ���������������(�Ż���÷�����������)
	 */
	private void searchFile(File file, boolean isFirstRunFlag) {
		// ----��ֹ����------
		if (isStopSearch) {
			// ���״����еĽ���(��������)
			if (isFirstRunFlag) {
				callbackSearchFileListenerEnd(true);// �ص��ӿ�end()����,��������(�쳣����)
			}
			return;
		}
		// #1 �ų�"������"�ļ�
		if (file == null || !file.canRead() || !file.exists()) {
			// ���״����еĽ���(��������)
			if (isFirstRunFlag) {
				callbackSearchFileListenerEnd(true);// �ص��ӿ�end()����,��������(�쳣����)
			}
			return;
		}
		// #2 ������ļ�(��Ŀ¼)
		if (!file.isDirectory()) {
			// �ж��ļ���С
			if (file.length() <= 0) {
				return;
			}
			//����ļ�������û�С�.��  δ֪�ļ�����
			if (file.getName().lastIndexOf('.') == -1) {
				return;
			}
			//��ȡͼ���Լ��ļ�����
			String[] iconAndTypeNames = FileTypeUtil
					.getFileIconAndTypeName(file);
			final String iconName = iconAndTypeNames[0]; // ���ļ�ʹ��ʲôͼ��(��Res�е�ͼ���ļ�����)
			final String typeName = iconAndTypeNames[1]; // ���ļ�����ʲô���͵�
			// ��ӵ������ļ��ļ���
			FileInfo fileInfo = new FileInfo(file, iconName, typeName);
			anyFileList.add(fileInfo);
			// ���Ӽ������ļ���С
			anyFileSize += file.length();
			// ����
			if (typeName.equals(FileTypeUtil.TYPE_APK)) {
				apkFileSize += file.length();
				apkFileList.add(fileInfo);
			} else if (typeName.equals(FileTypeUtil.TYPE_AUDIO)) {
				audioFileSize += file.length();
				audioFileList.add(fileInfo);
			} else if (typeName.equals(FileTypeUtil.TYPE_IMAGE)) {
				imageFileSize += file.length();
				imageFileList.add(fileInfo);
			} else if (typeName.equals(FileTypeUtil.TYPE_TXT)) {
				txtFileSize += file.length();
				txtFileList.add(fileInfo);
			} else if (typeName.equals(FileTypeUtil.TYPE_VIDEO)) {
				videoFileSize += file.length();
				videoFileList.add(fileInfo);
			} else if (typeName.equals(FileTypeUtil.TYPE_ZIP)) {
				zipFileSize += file.length();
				zipFileList.add(fileInfo);
			}
			// �ص��ӿ�searching����(����֪ͨ���������ݸ�����)
			callbackSearchFileListenerSearching(typeName);
			return;
		}
		// #3 �����Ŀ¼
		File[] files = file.listFiles();
		if (files == null || files.length <= 0) {
			return;
		}
		for (int i = 0; i < files.length; i++) {
			File tmpFile = files[i];
			searchFile(tmpFile, false);// �ݹ�,�Ժ�ķ���ջ�ڵ�flagֵ��Ϊfalse,��ʾ�����ǵ�һ�εĵ���
		}
		// ���״����еĽ���(��������)
		if (isFirstRunFlag) {
			callbackSearchFileListenerEnd(false);// �ص��ӿ�end()����,������������ɣ����쳣����
		}
	}

	/** ��ȡ�����ļ��ļ��б�(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public ArrayList<FileInfo> getAnyFileList() {
		return anyFileList;
	}

	/** ��ȡ�ĵ��ļ��б�(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public ArrayList<FileInfo> getTxtFileList() {
		return txtFileList;
	}

	/** ��ȡ��Ƶ�ļ��ļ��б�(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public ArrayList<FileInfo> getVideoFileList() {
		return videoFileList;
	}

	/** ��ȡ�����ļ��ļ��б�(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public ArrayList<FileInfo> getAudioFileList() {
		return audioFileList;
	}

	/** ��ȡͼ���ļ��ļ��б�(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public ArrayList<FileInfo> getImageFileList() {
		return imageFileList;
	}

	/** ��ȡAPK�ļ��б�(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public ArrayList<FileInfo> getApkFileList() {
		return apkFileList;
	}

	/** ��ȡzip�ļ��б�(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public ArrayList<FileInfo> getZipFileList() {
		return zipFileList;
	}

	/** ��ȡAPK�ļ���ǰ�ܴ�С(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public long getApkFileSize() {
		return apkFileSize;
	}

	/** ��ȡzip�ļ���ǰ�ܴ�С(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public long getZipFileSize() {
		return zipFileSize;
	}

	/** ��ȡ���������ļ���ǰ�ܴ�С(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public long getAnyFileSize() {
		return anyFileSize;
	}

	/** ��ȡ�ı��ļ���ǰ�ܴ�С(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public long getTxtFileSize() {
		return txtFileSize;
	}

	/** ��ȡ��Ƶ�ļ���ǰ�ܴ�С(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public long getVideoFileSize() {
		return videoFileSize;
	}

	/** ��ȡ��Ƶ�ļ���ǰ�ܴ�С(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public long getAudioFileSize() {
		return audioFileSize;
	}

	/** ��ȡͼ���ļ���ǰ�ܴ�С(����������{@link #searchSDCardFile()}ʵʱ�ڱ仯) */
	public long getImageFileSize() {
		return imageFileSize;
	}

	public void setAnyFileSize(long anyFileSize) {
		this.anyFileSize = anyFileSize;
		if (this.anyFileSize < 0) {
			this.anyFileSize = 0;
		}
	}

	public void setTxtFileSize(long txtFileSize) {
		this.txtFileSize = txtFileSize;
		if (this.txtFileSize < 0) {
			this.txtFileSize = 0;
		}
	}

	public void setVideoFileSize(long videoFileSize) {
		this.videoFileSize = videoFileSize;
		if (this.videoFileSize < 0) {
			this.videoFileSize = 0;
		}
	}

	public void setAudioFileSize(long audioFileSize) {
		this.audioFileSize = audioFileSize;
		if (this.audioFileSize < 0) {
			this.audioFileSize = 0;
		}
	}

	public void setImageFileSize(long imageFileSize) {
		this.imageFileSize = imageFileSize;
		if (this.imageFileSize < 0) {
			this.imageFileSize = 0;
		}
	}

	public void setZipFileSize(long zipFileSize) {
		this.zipFileSize = zipFileSize;
		if (this.zipFileSize < 0) {
			this.zipFileSize = 0;
		}
	}

	public void setApkFileSize(long apkFileSize) {
		this.apkFileSize = apkFileSize;
		if (this.apkFileSize < 0) {
			this.apkFileSize = 0;
		}
	}

	// �������̼���-----------------------------------------------------------------------------------
	private SearchFileListener listener; // �������̼���

	/**
	 * �������̵�ʵʱ����{@link #setSearchFileListener(SearchFileListener listener)},
	 * ������������ʵʱ�����ļ���Ϣ
	 */
	public interface SearchFileListener {
		/**
		 * �����������У�ÿ������һ���ļ�������
		 * 
		 * @see ��Ҫ��ȡ��ǰ���ݼ��ϼ�������������getApkFileSize()�� getApkFileList()ʵʱ��ȡ
		 */
		void searching(String typeName);

		/** ����������ʱ������ */
		void end(boolean isExceptionEnd);
	}
	/** �����ļ����Ҽ���*/
	public void setSearchFileListener(SearchFileListener listener) {
		this.listener = listener;
	}

	/** �����ص� SearchFileListener�ӿ��ڷ��� */
	private void callbackSearchFileListenerSearching(String typeName) {
		if (listener != null) {
			listener.searching(typeName);
		}
	}

	/** �����ص� SearchFileListener�ӿ��ڷ��� */
	private void callbackSearchFileListenerEnd(boolean isExceptionEnd) {
		if (listener != null) {
			listener.end(isExceptionEnd);
		}
	}

	/** ȡ���ļ����ļ��д�С */
	public static long getFileSize(File file) {
		long size = 0;
		if (!file.isDirectory()) { // �ļ�
			return file.length();
		}
		File files[] = file.listFiles(); // �ļ��У��ݹ飩
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				size = size + getFileSize(files[i]);
			} else {
				size = size + files[i].length();
			}
		}
		return size;
	}

	/** ɾ���ļ� **/
	public void deleteFile(File f) {
		if (f.isDirectory()) {
			File[] files = f.listFiles();
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; ++i) {
					deleteFile(files[i]);
				}
			}
		}
		f.delete();
	}
}