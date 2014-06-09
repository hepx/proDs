package android.apk.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import android.apk.parser.APK;
import android.apk.parser.APKParser;

import com.web4j.util.FileUtil;

/**
 * 根据APK路径获取APK的ICON
 * 
 * @author hztom
 * 
 */
public class IconUtil {

	public static void main(String[] args) {
		try{
			String apkPath = "C:/Users/xixi/Desktop/newsreader-wyyy_news.apk";
			File file=new File(apkPath);
			APK apk=APKParser.getApk(file);
			FileUtil.writeFile(file, apk.getFilename(), "D:/apache-tomcat-6.0.33/webapps/attachments/market/apps/"+apk.getPackageName());
			String path = IconUtil.getIcon(file, "icon.png", "D:/apache-tomcat-6.0.33/webapps/attachments/market/apps/com.netease.newsreader.activity/icon_72.png");
			System.out.println(path);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param apkFile
	 * @param iconName
	 * @param exportIconPath
	 * @return
	 */
	public static String getIcon(File apkFile, String iconName,
			String exportIconPath) {
		FileInputStream in = null;
		FileOutputStream out = null;
		ZipInputStream zipin = null;
		String apkImg = "";
		try {
			in = new FileInputStream(apkFile);
			zipin = new ZipInputStream(in);
			ZipEntry entry = null;
			while ((entry = zipin.getNextEntry()) != null) {
				String name = entry.getName().toLowerCase();
				if (name.endsWith("/" + iconName)
						&& name.contains("drawable")
						&& name.contains("res")) {
					apkImg = exportIconPath;
					out = new FileOutputStream(new File(exportIconPath));
					byte[] buff = new byte[1024];
					int n = 0;
					while ((n = zipin.read(buff, 0, buff.length)) != -1) {
						out.write(buff, 0, n);
					}
					break;
				} else if (name.endsWith("/app_" + iconName)
						&& name.contains("drawable")
						&& name.contains("res")) {
					apkImg = exportIconPath;
					out = new FileOutputStream(new File(exportIconPath));
					byte[] buff = new byte[1024];
					int n = 0;
					while ((n = zipin.read(buff, 0, buff.length)) != -1) {
						out.write(buff, 0, n);
					}
					break;
				}
			}
			out = null;
			zipin.closeEntry();
			entry = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (zipin != null) {
					zipin.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return apkImg;
	}

	/**
	 * 根据APK路径获取APK的ICON
	 * 
	 * @param srcApkPath
	 * @param iconName
	 * @param exportIconPath
	 *            apk图标输出的地址
	 * @return
	 */
	public static String getIcon(String srcApkPath, String iconName,
			String exportIconPath) {
		File apkFile = new File(srcApkPath);
		return getIcon(apkFile, iconName, exportIconPath);
	}
}