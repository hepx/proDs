package com.web4j.qdtgplatform.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.qdtgplatform.dao.QdtgChannelDao;
import com.web4j.qdtgplatform.entity.TbQdtgChannel;
import com.web4j.qdtgplatform.entity.TbQdtgCvs;
import com.web4j.qdtgplatform.entity.TbQdtgProduct;
import com.web4j.qdtgplatform.pojo.QdtgChannel;
import com.web4j.systemplatform.dao.userInfo.UserInfoDao;
import com.web4j.systemplatform.entity.TbUserInfo;

@Service("qdtgChannelModel")
public class QdtgChannelModel {

	@Autowired
	private QdtgChannelDao qdtgChannelDao;
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	public List<QdtgChannel> queryQdtgChannels(Integer start,Integer limit) throws Exception{
		List<TbQdtgChannel> tbQdtgChannels=qdtgChannelDao.queryTbQdtgChannels(start, limit);
		List<QdtgChannel> qdtgChannels=new ArrayList<QdtgChannel>();
		for(TbQdtgChannel source:tbQdtgChannels){
			QdtgChannel target=new QdtgChannel();
			BeanUtils.copyProperties(source, target);
			TbQdtgProduct product=source.getTbQdtgProduct();
			if(product!=null){
				target.setProductName(product.getProductName());
				target.setProductId(product.getProductId());
			}
			TbQdtgCvs cvs=source.getTbQdtgCvs();
			if(cvs!=null){
				target.setCvsId(cvs.getCvsId());
				target.setCvsName(cvs.getCvsName());
			}
			
			qdtgChannels.add(target);
		}
		return qdtgChannels;
	}
	
	public List<QdtgChannel> queryAllQdtgChannels(Long userId,Long productId) throws Exception{
		List<TbQdtgChannel> tbQdtgChannels=new ArrayList<TbQdtgChannel>();
		if(userId!=null){
			TbUserInfo userInfo=userInfoDao.get(userId);
			if(userInfo!=null){
				tbQdtgChannels=userInfo.getTbQdtgCvs().getTbQdtgChannels();
			}
		}else{
			tbQdtgChannels=qdtgChannelDao.queryAllTbQdtgChannels(productId);
		}
		List<QdtgChannel> qdtgChannels=new ArrayList<QdtgChannel>();
		for(TbQdtgChannel source:tbQdtgChannels){
			QdtgChannel target=new QdtgChannel();
			BeanUtils.copyProperties(source, target);
			qdtgChannels.add(target);
		}
		return qdtgChannels;
	}
	
	public Long queryQdtgChannelCount(){
		return qdtgChannelDao.counts();
	}
	
	public void saveOrUpdateQdtgChannel(QdtgChannel qdtgChannel) throws Exception{
		TbQdtgChannel tbQdtgChannel=new TbQdtgChannel();
		BeanUtils.copyProperties(qdtgChannel, tbQdtgChannel);
		if(qdtgChannel.getProductId()!=null){
			tbQdtgChannel.setTbQdtgProduct(new TbQdtgProduct(qdtgChannel.getProductId()));
		}
		if(qdtgChannel.getCvsId()!=null){
			tbQdtgChannel.setTbQdtgCvs(new TbQdtgCvs(qdtgChannel.getCvsId()));
		}
		qdtgChannelDao.saveOrUpdate(tbQdtgChannel);
	}
	
	public void deleteQdtgChannels(List<QdtgChannel> qdtgChannels) throws Exception{
		for(QdtgChannel chn:qdtgChannels){
			if(chn.getChannelId()!=null){
				qdtgChannelDao.deleteById(chn.getChannelId());
			}
		}
	}
}
