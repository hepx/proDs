package com.web4j.qdtgplatform.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.web4j.common.dao.AbstractDao;
import com.web4j.qdtgplatform.entity.TbQdtgChannel;

@Repository("qdtgChannelDao")
public class QdtgChannelDao extends AbstractDao<TbQdtgChannel> {

	public List<TbQdtgChannel> queryTbQdtgChannels(Integer start,Integer limit) throws Exception{
		String hql="from TbQdtgChannel t order by t.channelId desc";
		return list(hql,start,limit);
	}
	
	public List<TbQdtgChannel> queryAllTbQdtgChannels(Long productId)throws Exception{
		StringBuffer hql=new StringBuffer("select new TbQdtgChannel(channelId,channelNo,fileName) " +
				"from TbQdtgChannel t");
		if(productId!=null){
			hql.append(" where t.tbQdtgProduct.productId="+productId);
		}
		hql.append(" order by t.channelId desc");
		return list(hql.toString());
	}
}
