package com.web4j.datacapture.jobs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.util.WeiboConfig;

import com.web4j.bdgplatform.entity.TbBdgBrRecord;
import com.web4j.bdgplatform.model.BdgBrRecordModel;
import com.web4j.datacapture.entity.TbWeiboRecord;
import com.web4j.datacapture.entity.TbWeiboUser;
import com.web4j.datacapture.model.WeiboRecordModel;
import com.web4j.datacapture.model.WeiboUserModel;
import com.web4j.util.SysConfig;

@Service("weiboUserBean")
public class WeiboUserBean {

	@Autowired
	private WeiboUserModel weiboUserModel;
	@Autowired
	private WeiboRecordModel weiboRecordModel;
	@Autowired
	private BdgBrRecordModel bdgBrRecordModel;
	
	private List<TbWeiboUser> weiboUsers=null;

	private void loadWeiboUsers(){
		try{
			weiboUsers=weiboUserModel.queryAllWeiboUser();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<TbWeiboUser> getWeiboUsers(){
		if(weiboUsers==null){
			loadWeiboUsers();
		}
		return weiboUsers;
	}
	
	public List<TbWeiboUser> getWeiboUsers(String type){
		List<TbWeiboUser> temWeiboUsers=new ArrayList<TbWeiboUser>();
		for(TbWeiboUser weiboUser : getWeiboUsers()){
			if(type.equals(weiboUser.getType())){
				temWeiboUsers.add(weiboUser);
			}
		}
		return temWeiboUsers;
	}
	
	public void clearWeiboUsers(){
		weiboUsers=null;
	}
	
	/**
	 * sina datacaptrue
	 * @throws Exception
	 */
	public void sinaCaptrue() throws Exception{
		List<TbWeiboUser> weiboUsers=getWeiboUsers(SysConfig.SINA_WEIBO_TYPE);
		String access_token = WeiboConfig.getValue("access_token");
		Timeline tm = new Timeline();
		tm.client.setToken(access_token);
		for(TbWeiboUser weiboUser : weiboUsers){
			StatusWapper status = null;
			//用户ID存在优先用UID查询//没有的情况下用NAME
			if(weiboUser.getUid()!=null&&!"".equals(weiboUser.getUid())){
				status=tm.getUserTimelineByUid(weiboUser.getUid(),
						weiboUser.getMaxRecordId(), weiboUser.getFeature(),
						weiboUser.getLimits());
			}else{
				status=tm.getUserTimelineByName(weiboUser.getScreenName(),
						weiboUser.getMaxRecordId(), weiboUser.getFeature(),
						weiboUser.getLimits());				
			}

			List<Status> statuslist=status.getStatuses();
			//入库APPID
			String appId=weiboUser.getAppId();
			if(appId==null||"".equals(appId)){
				List<TbWeiboRecord>records=new ArrayList<TbWeiboRecord>();
				for(int i=0;i<statuslist.size();i++){
					Status s =statuslist.get(i);
					TbWeiboRecord record=new TbWeiboRecord(s, SysConfig.SINA_WEIBO);
					records.add(record);
					if(i==0){
						weiboUser.setMaxRecordId(s.getId());
					}
				}
				weiboRecordModel.saveOrUpdateWeiboRecords(records);
			}else if(SysConfig.APP_BDG.equals(appId)){//吊丝不寂寞
				List<TbBdgBrRecord>records=new ArrayList<TbBdgBrRecord>();
				for(int i=0;i<statuslist.size();i++){
					Status s =statuslist.get(i);
					TbBdgBrRecord record=new TbBdgBrRecord(s, SysConfig.SINA_WEIBO);
					records.add(record);
					if(i==0){
						weiboUser.setMaxRecordId(s.getId());
					}
				}
				bdgBrRecordModel.saveOrUpdateBdgBrRecords(records);
			}
		}
		weiboUserModel.saveOrUpdateWeiboUsers(weiboUsers);
	}
}
