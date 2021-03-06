package com.zhan.aoyoustore.utils;

import android.os.Environment;

import com.zhan.aoyoustore.base.App;

import java.io.File;
import java.math.BigDecimal;

/**
 * 作者：伍岳 on 15-7-21 22:16
 * 邮箱：wuyue8512@163.com
 //
 //         .............................................
 //                  美女坐镇                  BUG辟易
 //         .............................................
 //
 //                       .::::.
 //                     .::::::::.
 //                    :::::::::::
 //                 ..:::::::::::'
 //              '::::::::::::'
 //                .::::::::::
 //           '::::::::::::::..
 //                ..::::::::::::.
 //              ``::::::::::::::::
 //               ::::``:::::::::'        .:::.
 //              ::::'   ':::::'       .::::::::.
 //            .::::'      ::::     .:::::::'::::.
 //           .:::'       :::::  .:::::::::' ':::::.
 //          .::'        :::::.:::::::::'      ':::::.
 //         .::'         ::::::::::::::'         ``::::.
 //     ...:::           ::::::::::::'              ``::.
 //    ```` ':.          ':::::::::'                  ::::..
 //                       '.:::::'                    ':'````..
 //
 */
public class PathUtils {
	//头像
	private final static String PIC = "PIC";
	//临时目录
	private final static String TEMP = "TEMP";

	public static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state);
	}

	private static File getAvailableCacheDir() {
		if (isExternalStorageWritable()) {
			return App.getInstance().getExternalCacheDir();
		} else {
			return App.getInstance().getCacheDir();
		}
	}

	public static String checkAndMkdirs(String dir) {
		File file = new File(dir);
		if (file.exists() == false) {
			file.mkdirs();
		}
		return dir;
	}

	public static String getAvatarCropPath() {
		return new File(getAvailableCacheDir(), "avatar_crop").getAbsolutePath();
	}

	public static String getAvatarTmpPath() {
		return new File(getAvailableCacheDir(), "avatar_tmp").getAbsolutePath();
	}

	
	public static File getExternalPicFilesDir() {
		if (isExternalStorageWritable()) {
			return App.getInstance().getExternalFilesDir(PIC);
		} else {
			return null;
		}
	}

	public static File getExternalTempFilesDir() {
		if (isExternalStorageWritable()) {
			return App.getInstance().getExternalFilesDir(TEMP);
		} else {
			return null;
		}
	}
	
	public static String getUserAvatarPath() {
		if(getExternalPicFilesDir()==null){
			return null;
		}
		return getExternalPicFilesDir().getPath()+ "/userAvatar.png";
	}
	
	/**
     * 递归删除文件和文件夹
     * @param file    要删除的根目录
     */
	public static long recursionDeleteFile(File file) {
		if (file == null) {
			return 0;
		}
		long fileSize = 0;
		if (file.isFile()) {
			fileSize = file.length();
			file.delete();
			return fileSize;
		}
		if (file.isDirectory()) {
			File[] childFile = file.listFiles();
			if (childFile == null || childFile.length == 0) {
				file.delete();
				return 0;
			}
			for (File f : childFile) {
				fileSize += recursionDeleteFile(f);
			}
			file.delete();
		}
		return fileSize;
	}
	
	/** 
     * byte(字节)根据长度转成kb(千字节)和mb(兆字节) 
     *  
     * @param bytes 
     * @return 
     */  
	public static String bytes2kb(long bytes) {
		BigDecimal filesize = new BigDecimal(bytes);
		BigDecimal megabyte = new BigDecimal(1024 * 1024);
		float returnValue = filesize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
		if (returnValue > 1)
			return (returnValue + "MB");
		BigDecimal kilobyte = new BigDecimal(1024);
		returnValue = filesize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
		return (returnValue + "KB");
	} 
}
