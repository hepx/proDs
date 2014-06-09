package com.web4j.util;

import java.util.Map;

public class SysConfig {

	public static final String LOGIN_USER="login_user";
	
	public static final String USER_STATE_OK="正常";
	
	public static final String LOGS_TYPE_USERLOGIN="登录系统";
	
	public static final String TIMEOUT_MESSAGE_PAGE="timeout.message.page";//请求超时
	
	//角色类型。
	public static final String ROLE_TYPE="roleType";
	//普通用户
	public static final String ROLE_TYPE_3="3";
	
	public static Map<String,String>vmap=null;
	//手机请求的默认开始
	public static final int PHONE_REQ_START=0;
	//手机请求的默认条数
	public static final int PHONE_REQ_LIMIT=5;
	
	public static final String SINA_WEIBO="新浪微博";
	
	public static final String TX_WEIBO="腾讯微博";
	
	public static final String SINA_WEIBO_TYPE="1";
	
	public static final String TX_WEIBO_TYPE="2";
	//吊丝不寂寞
	public static final String APP_BDG="1";
	//未发布
	public static final int RECORD_STATUS_UNRELEASE=0;
	//已编辑
	public static final int RECORD_STATUS_EDITED=1;
	//已发布
	public static final int RECORD_STATUS_RELEASE=2;
	
	//渠道推广统计类型
	public static final int QDTG_STAT_DAY=1;
	public static final int QDTG_STAT_MONTH=2;
	
	//角色类型
	public static final int ROLE_TYPE_SUPERADMIN=1;
	public static final int ROLE_TYPE_ADMIN=2;
	public static final int ROLE_TYPE_YY=5;
	
}
