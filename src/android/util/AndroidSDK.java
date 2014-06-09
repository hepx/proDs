package android.util;

import java.util.HashMap;
import java.util.Map;

public class AndroidSDK {

	private static Map<Integer,String> SDK_MAP=null;
	
	public static String getSDKVersion(Integer sdkCode){
		if(SDK_MAP==null){
			SDK_MAP=new HashMap<Integer, String>();
			SDK_MAP.put(7, "Android 2.1");
			SDK_MAP.put(8, "Android 2.2");
			SDK_MAP.put(10, "Android 2.3.3");
			SDK_MAP.put(11, "Android 3.0");
			SDK_MAP.put(12, "Android 3.1");
			SDK_MAP.put(13, "Android 3.2");
			SDK_MAP.put(14, "Android 4.0");
			SDK_MAP.put(15, "Android 4.0.3");
			SDK_MAP.put(16, "Android 4.1.2");
			SDK_MAP.put(17, "Android 4.2");
		}
		String v=SDK_MAP.get(sdkCode);
		if(v==null){
			v=SDK_MAP.get(7);
		}
		return v;
	}
}
