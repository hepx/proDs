package com.web4j.qdtgplatform.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.web4j.common.action.AbstractActionSupport;
import com.web4j.exception.DoErrorException;
import com.web4j.qdtgplatform.model.QdtgChannelModel;
import com.web4j.qdtgplatform.pojo.QdtgChannel;
import com.web4j.util.LoginUserUtils;
import com.web4j.util.SysConfig;

@Controller
@Scope("prototype")
@ParentPackage("pm-main")
@Namespace("/qdtg/channel")
public class QdtgChannelAction extends AbstractActionSupport {

	private static Logger log=Logger.getLogger(QdtgChannelAction.class);
	
	@Autowired
	private QdtgChannelModel qdtgChannelModel;
	
	private List<QdtgChannel>qdtgChannels;
	
	private QdtgChannel qdtgChannel;
	
	private Long productId;
	
	@Action("queryQdtgChannel")
	public String queryQdtgChannel(){
		try{
			qdtgChannels=qdtgChannelModel.queryQdtgChannels(start, limit);
			if(total==null){
				total=qdtgChannelModel.queryQdtgChannelCount();
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("queryAllQdtgChannel")
	public String queryAllQdtgChannel(){
		try{
			Long userId=null;
			//如果登录用户的角色不属于管理员以上级别
			int roleType=LoginUserUtils.getRoleType();
			if(roleType>2 && roleType!=SysConfig.ROLE_TYPE_YY){
				userId=LoginUserUtils.getUserId();
			}
			qdtgChannels=qdtgChannelModel.queryAllQdtgChannels(userId,productId);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("saveOrUpdateQdtgChannel")
	public String saveOrUpdateQdtgChannel(){
		try{
			if(qdtgChannel!=null){
				qdtgChannelModel.saveOrUpdateQdtgChannel(qdtgChannel);
			}else{
				throw new DoErrorException("缺少必要参数！");
			}
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}
	
	@Action("deleteQdtgChannel")
	public String deleteQdtgChannel(){
		try{
			qdtgChannelModel.deleteQdtgChannels(qdtgChannels);
		}catch(Exception e){
			setErrorMessage(e);
			log.error(e.getMessage(), e);
		}
		return JSON;
	}

	public List<QdtgChannel> getQdtgChannels() {
		return qdtgChannels;
	}

	public void setQdtgChannels(List<QdtgChannel> qdtgChannels) {
		this.qdtgChannels = qdtgChannels;
	}

	public QdtgChannel getQdtgChannel() {
		return qdtgChannel;
	}

	public void setQdtgChannel(QdtgChannel qdtgChannel) {
		this.qdtgChannel = qdtgChannel;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
