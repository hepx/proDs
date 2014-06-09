package com.web4j.bdgplatform.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import weibo4j.util.WeiboConfig;
import cn.jpush.api.DeviceEnum;
import cn.jpush.api.JPushClient;

import com.web4j.jpush.AbstractJpushUtil;

@Service("bdgPushImptModel")
@Scope("prototype")
public class BdgPushImptModel extends AbstractJpushUtil {
	
	public BdgPushImptModel(){
		JPushClient	jpush =new JPushClient(WeiboConfig.getValue("bdg_mastersecret"),WeiboConfig.getValue("bdg_appkey"), 10800, DeviceEnum.Android);
		setJpush(jpush);
	}

}
