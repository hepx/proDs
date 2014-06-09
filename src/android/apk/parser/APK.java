package android.apk.parser;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.web4j.util.FileUtil;

/**
 * 增加
 * 
 * @author xixi
 * @date 2013-2-1
 * 
 */
public class APK implements Serializable {

	private static final long serialVersionUID = 995400520528834348L;
	private String packageName; // apk包名称
	private int appVersionCode; // versionCode
	private String appVersionName; // versionName
	private String filename; // apk文件名称
	private String md5; // 文件MD5
	private String appSize; // 文件大小(字节)
	private String appName;//apk应用名
	private String sdkSupport;//适应平台
	private String appPic;
	private String appPath;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public int getAppVersionCode() {
		return appVersionCode;
	}

	public void setAppVersionCode(int appVersionCode) {
		this.appVersionCode = appVersionCode;
	}

	public String getAppVersionName() {
		return appVersionName;
	}

	public void setAppVersionName(String appVersionName) {
		this.appVersionName = appVersionName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getAppSize() {
		return appSize;
	}

	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getSdkSupport() {
		return sdkSupport;
	}

	public void setSdkSupport(String sdkSupport) {
		this.sdkSupport = sdkSupport;
	}

	public String getAppPic() {
		return appPic;
	}

	public void setAppPic(String appPic) {
		this.appPic = appPic;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	@Override
	public String toString() {
		return "APK [packageName=" + packageName + ", versionCode="
				+ appVersionCode + ", versionName=" + appVersionName + ", filename="
				+ filename + ", md5=" + md5 + ", fileSize=" + appSize
				+ ", appName=" + appName + ",sdkSpport=" + sdkSupport + "]";
	}

}
