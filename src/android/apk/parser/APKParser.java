package android.apk.parser;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.xmlpull.v1.XmlPullParser;

import com.web4j.util.FileUtil;

import android.apk.utils.MD5Checksum;
import android.content.res.AXmlResourceParser;
import android.util.AndroidSDK;
import android.util.TypedValue;

/**
 * Apk包解析入口
 * 
 * @author hztom
 * 
 */
public class APKParser {
  private static final String DEFAULT_XML = "AndroidManifest.xml";

  public static void main(String[] arguments) {
    String apkPath = "E:/phone_workspace/ImgRead/bin/ImgRead.apk";
    APK apk = getApk(apkPath);
    System.out.println(apk);
  }

  public static APK getApk(String apkPath) {
      File apkFile = new File(apkPath);
      return getApk(apkFile);
  }
  
  public static APK getApk(File apkFile) {
	    APK apk = new APK();
	    ZipFile file = null;
	    try {
	      apk.setFilename(apkFile.getName());
	      // start: 取apk文件大小
	      FileInputStream fis = null;
	      fis = new FileInputStream(apkFile);
	      apk.setAppSize(FileUtil.formatFileSizeToStr(fis.available()));
	      // end: 取apk文件大小
	      apk.setMd5(MD5Checksum.getMD5Checksum(apkFile));

	      file = new ZipFile(apkFile, ZipFile.OPEN_READ);
	      ZipEntry entry = file.getEntry(DEFAULT_XML);
	      AXmlResourceParser parser = new AXmlResourceParser();
	      parser.open(file.getInputStream(entry));
	      while (true) {
	        int type = parser.next();
	        if (type == XmlPullParser.END_DOCUMENT) {
	          break;
	        }
	        switch (type) {
	        case XmlPullParser.START_DOCUMENT: {
	          break;
	        }
	        case XmlPullParser.START_TAG: {
	          for (int i = 0; i != parser.getAttributeCount(); ++i) {
	            String attributeName = parser.getAttributeName(i);
	            if ("versioncode".equals(attributeName.toLowerCase())) {
	              apk.setAppVersionCode(Integer.valueOf(getAttributeValue(parser, i)));
	            }
	            if ("versionname".equals(attributeName.toLowerCase())) {
	              apk.setAppVersionName(getAttributeValue(parser, i));
	            }
	            if ("package".equals(attributeName.toLowerCase())) {
	              apk.setPackageName(getAttributeValue(parser, i));
	            }
	            if("minsdkversion".equals(attributeName.toLowerCase())){
	            	String v=AndroidSDK.getSDKVersion(Integer.parseInt(getAttributeValue(parser, i)));
	            	apk.setSdkSupport(v);
	            }
//	            if ("label".equals(attributeName.toLowerCase())){
//	            	apk.setAppName(getAttributeValue(parser, i));
//	            }
	          }
	          break;
	        }
	        case XmlPullParser.END_TAG: {
	          break;
	        }
	        case XmlPullParser.TEXT: {
	          break;
	        }
	        }
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }

	    return apk;
	  }

  private static String getAttributeValue(AXmlResourceParser parser, int index) {
    int type = parser.getAttributeValueType(index);
    int data = parser.getAttributeValueData(index);
    if (type == TypedValue.TYPE_STRING) {
      return parser.getAttributeValue(index);
    }
    if (type == TypedValue.TYPE_ATTRIBUTE) {
      return String.format("?%s%08X", getPackage(data), data);
    }
    if (type == TypedValue.TYPE_REFERENCE) {
      return String.format("@%s%08X", getPackage(data), data);
    }
    if (type == TypedValue.TYPE_FLOAT) {
      return String.valueOf(Float.intBitsToFloat(data));
    }
    if (type == TypedValue.TYPE_INT_HEX) {
      return String.format("0x%08X", data);
    }
    if (type == TypedValue.TYPE_INT_BOOLEAN) {
      return data != 0 ? "true" : "false";
    }
    if (type == TypedValue.TYPE_DIMENSION) {
      return Float.toString(complexToFloat(data)) + DIMENSION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
    }
    if (type == TypedValue.TYPE_FRACTION) {
      return Float.toString(complexToFloat(data)) + FRACTION_UNITS[data & TypedValue.COMPLEX_UNIT_MASK];
    }
    if (type >= TypedValue.TYPE_FIRST_COLOR_INT && type <= TypedValue.TYPE_LAST_COLOR_INT) {
      return String.format("#%08X", data);
    }
    if (type >= TypedValue.TYPE_FIRST_INT && type <= TypedValue.TYPE_LAST_INT) {
      return String.valueOf(data);
    }
    return String.format("<0x%X, type 0x%02X>", data, type);
  }

  private static String getPackage(int id) {
    if (id >>> 24 == 1) {
      return "android:";
    }
    return "";
  }

  public static float complexToFloat(int complex) {
    return (float) (complex & 0xFFFFFF00) * RADIX_MULTS[(complex >> 4) & 3];
  }

  private static final float  RADIX_MULTS[]     = { 0.00390625F, 3.051758E-005F, 1.192093E-007F, 4.656613E-010F };
  private static final String DIMENSION_UNITS[] = { "px", "dip", "sp", "pt", "in", "mm", "", "" };
  private static final String FRACTION_UNITS[]  = { "%", "%p", "", "", "", "", "", "" };
}