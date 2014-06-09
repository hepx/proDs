package com.web4j.jpush;

import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;

public abstract class AbstractJpushUtil {

	private JPushClient jpush;

	public void setJpush(JPushClient jpush) {
		this.jpush = jpush;
	}
	
	public JPushClient getJpush() {
		return jpush;
	}

	/**
	 * 是否启动ssl安全连接
	 * @param enableSSL
	 */
	public void setEnableSSL(boolean enableSSL){
		jpush.setEnableSSL(enableSSL);
	}
	
	/**
	 * 发送通知给AppKey的所有用户
	 * @param sendNo(发送编号)
	 * @param msgTitle(通知标题)
	 * @param msgContent(通知内容)
	 * @return
	 */
	public MessageResult sendNotificationWithAppKey(int sendNo,String msgTitle,String msgContent){
		return sendNotificationWithAppKey(sendNo, msgTitle, msgContent,0,null);
	}
	
	/**
	 * 自定义通知栏(没有则填写0)以及传递附属信息 
	 * @param sendNo(发送编号)
	 * @param msgTitle(通知标题)
	 * @param msgContent(通知内容)
	 * @param builderId(自定义通知栏样式Id) 
	 * @param extra (附加信息)
	 * @return
	 */
	public MessageResult sendNotificationWithAppKey(int sendNo,String msgTitle,String msgContent,int builderId,Map<String,Object>extra){
		return jpush.sendNotificationWithAppKey(sendNo, msgTitle, msgContent, builderId, extra);
	}
	
	/**
	 * 发送带IMEI的通知
	 * @param sendNo(发送编号)
	 * @param imei(IMEI字符串)
	 * @param msgTitle(通知标题)
	 * @param msgContent(通知内容)
	 * @return
	 */
	public MessageResult sendNotificationWithImei(int sendNo,String imei,String msgTitle,String msgContent){
		return sendNotificationWithImei(sendNo, imei, msgTitle, msgContent, 0, null);
	}

	/**
	 * 自定义通知栏(没有则填写0)以及传递附属信息
	 * @param sendNo(发送编号)
	 * @param imei(IMEI字符串)
	 * @param msgTitle(通知标题)
	 * @param msgContent(通知内容)
	 * @param builderId(自定义通知栏样式Id) 
	 * @param extra (附加信息)
	 * @return
	 */
	public MessageResult sendNotificationWithImei(int sendNo,String imei,String msgTitle,String msgContent,int builderId,Map<String, Object>extra){
		return jpush.sendNotificationWithImei(sendNo, imei, msgTitle, msgContent, builderId, extra);

	}
	
	/**
	 * 发送带Tag的通知
	 * @param sendNo
	 * @param tag (Tag字符串)
	 * @param msgTitle
	 * @param msgContent
	 * @return
	 */
	public MessageResult sendNotificationWithTag(int sendNo,String tag,String msgTitle,String msgContent){
		return sendNotificationWithTag(sendNo, tag, msgTitle, msgContent, 0, null);
	}
	
	/**
	 * 自定义通知栏(没有则填写0)以及传递附属信息
	 * @param sendNo
	 * @param tag
	 * @param msgTitle
	 * @param msgContent
	 * @param builderId
	 * @param extra
	 * @return
	 */
	public MessageResult sendNotificationWithTag(int sendNo,String tag,String msgTitle,String msgContent,int builderId ,Map<String, Object>extra){
		return jpush.sendNotificationWithTag(sendNo, tag, msgTitle, msgContent, builderId, extra);
	}
	
	/**
	 * 发送带Alias的通知
	 * @param sendNo
	 * @param alias(Alias字符串) 
	 * @param msgTitle
	 * @param msgContent
	 * @return
	 */
	public MessageResult sendNotificationWithAlias(int sendNo,String alias,String msgTitle,String msgContent){
		return sendNotificationWithAlias(sendNo, alias, msgTitle, msgContent, 0, null);
	}
	
	/**
	 * 自定义通知栏(没有则填写0)以及传递附属信息 
	 * @param sendNo
	 * @param alias
	 * @param msgTitle
	 * @param msgContent
	 * @param builderId
	 * @param extra
	 * @return
	 */
	public MessageResult sendNotificationWithAlias(int sendNo,String alias,String msgTitle,String msgContent,int builderId,Map<String, Object>extra){
		return jpush.sendNotificationWithAlias(sendNo, alias, msgTitle, msgContent, builderId, extra);
	}
}
