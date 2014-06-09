package com.web4j.jpush;

import java.util.Map;

/**
 * JPUSH 接口
 * @author xixi
 * @date 2013-3-7
 *
 */
public interface JpushInterface {
	
	/**
	 * 是否启动ssl安全连接
	 * @param enableSSL
	 */
	public void setEnableSSL(boolean enableSSL);
	
	/**
	 * 发送通知给AppKey的所有用户
	 * @param sendNo(发送编号)
	 * @param msgTitle(通知标题)
	 * @param msgContent(通知内容)
	 */
	public void sendNotificationWithAppKey(int sendNo,String msgTitle,String msgContent);
	
	/**
	 * 自定义通知栏(没有则填写0)以及传递附属信息 
	 * @param sendNo(发送编号)
	 * @param msgTitle(通知标题)
	 * @param msgContent(通知内容)
	 * @param builderId(自定义通知栏样式Id) 
	 * @param extra (附加信息)
	 */
	public void sendNotificationWithAppKey(int sendNo,String msgTitle,String msgContent,int builderId,Map<String,Object>extra);
	
	/**
	 * 发送带IMEI的通知
	 * @param sendNo(发送编号)
	 * @param imei(IMEI字符串)
	 * @param msgTitle(通知标题)
	 * @param msgContent(通知内容)
	 */
	public void sendNotificationWithImei(int sendNo,String imei,String msgTitle,String msgContent);

	/**
	 * 自定义通知栏(没有则填写0)以及传递附属信息
	 * @param sendNo(发送编号)
	 * @param imei(IMEI字符串)
	 * @param msgTitle(通知标题)
	 * @param msgContent(通知内容)
	 * @param builderId(自定义通知栏样式Id) 
	 * @param extra (附加信息)
	 */
	public void sendNotificationWithImei(int sendNo,String imei,String msgTitle,String msgContent,int builderId,Map<String, Object>extra);
	
	/**
	 * 发送带Tag的通知
	 * @param sendNo
	 * @param tag (Tag字符串)
	 * @param msgTitle
	 * @param msgContent
	 */
	public void sendNotificationWithTag(int sendNo,String tag,String msgTitle,String msgContent);
	
	/**
	 * 自定义通知栏(没有则填写0)以及传递附属信息
	 * @param sendNo
	 * @param tag
	 * @param msgTitle
	 * @param msgContent
	 * @param builderId
	 * @param extra
	 */
	public void sendNotificationWithTag(int sendNo,String tag,String msgTitle,String msgContent,int builderId ,Map<String, Object>extra);
	
	/**
	 * 发送带Alias的通知
	 * @param sendNo
	 * @param alias(Alias字符串) 
	 * @param msgTitle
	 * @param msgContent
	 */
	public void sendNotificationWithAlias(int sendNo,String alias,String msgTitle,String msgContent);
	
	/**
	 * 自定义通知栏(没有则填写0)以及传递附属信息 
	 * @param sendNo
	 * @param alias
	 * @param msgTitle
	 * @param msgContent
	 * @param builderId
	 * @param extra
	 */
	public void sendNotificationWithAlias(int sendNo,String alias,String msgTitle,String msgContent,int builderId,Map<String, Object>extra);
}
