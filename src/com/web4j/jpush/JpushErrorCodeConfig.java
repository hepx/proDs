package com.web4j.jpush;

import java.util.HashMap;
import java.util.Map;

public class JpushErrorCodeConfig {

	private static Map<Integer,String> errorCodeMap=null;
	
	public static String getErrorMsg(Integer errorCode){
		if(errorCodeMap==null){
			errorCodeMap=new HashMap<Integer,String>();
			//没有错误，发送成功
			errorCodeMap.put(0,"发送成功");
		    //系统内部错误
			errorCodeMap.put(10,"系统内部错误");
		    //不支持GET请求
			errorCodeMap.put(1001,"不支持GET请求");
		    //缺少必须参数
			errorCodeMap.put(1002,"缺少必须参数");
		    //参数值不合法
			errorCodeMap.put(1003,"参数值不合法");
		    //验证失败
			errorCodeMap.put(1004,"验证失败");
		    //消息体太大
			errorCodeMap.put(1005,"消息体太大");
		    //IMEI不合法
			errorCodeMap.put(1007,"IMEI不合法");
		    //appkey不合法
			errorCodeMap.put(1008,"appkey不合法");
		    //msg_content不合法
			errorCodeMap.put(1010,"msg_content不合法");
		    //没有满足条件的推送目标
			errorCodeMap.put(1011,"没有满足条件的推送目标");
		    //IOS不支持自定义消息
			errorCodeMap.put(1012,"IOS不支持自定义消息");
		}
		return errorCodeMap.get(errorCode);
	}
	
}
